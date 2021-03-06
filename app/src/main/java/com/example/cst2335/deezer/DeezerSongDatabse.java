package com.example.cst2335.deezer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Database function handler class,
 * contains functions to perform database operations
 * */
public class DeezerSongDatabse {

    /**
     * SQLiteDatabase open helper class object
     * */
    DBHelper dbHelper;

    /**
     * Table and columns String constants
     * */
    private final String TABLE_FAVOURITE_SONGS = "favourite_songs";
    private final String COL_SONG_ID = "id";
    private final String COL_SONG_TITLE = "title";
    private final String COL_SONG_ALBUM_NAME = "album";
    private final String COL_SONG_DURATION = "duration";
    private final String COL_SONG_ALBUM_COVER_PATH = "cover_path";

    /**
     * Initializes the SQLiteDatabase helper class
     * @param context Context of an activity
     * */
    public DeezerSongDatabse(Context context) {
        dbHelper = new DBHelper(context);
    }


    class DBHelper extends SQLiteOpenHelper {

        DBHelper(Context context) {
            super(context, "DEEZER_SONG_DATABASE", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //create table
            String createQuery = "CREATE TABLE " + TABLE_FAVOURITE_SONGS + "(" +
                    COL_SONG_ID + " TEXT PRIMARY KEY," +
                    COL_SONG_TITLE + " TEXT," +
                    COL_SONG_ALBUM_NAME + " TEXT," +
                    COL_SONG_DURATION + " TEXT," +
                    COL_SONG_ALBUM_COVER_PATH + " TEXT" +
                    ")";
            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //drop table and create it again
            db.execSQL("DROP TABLE " + TABLE_FAVOURITE_SONGS);
            onCreate(db);
        }
    }

    /**
     * Helps to perform SELECT operation on the created database
     *
     * @return List of songs saved from local database
     * */
    public ArrayList<Song> getAllFavouriteSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        //get readable database object
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVOURITE_SONGS,
                new String[]{
                        COL_SONG_ID, COL_SONG_TITLE,
                        COL_SONG_ALBUM_NAME,
                        COL_SONG_DURATION,
                        COL_SONG_ALBUM_COVER_PATH
                },
                null, null,
                null, null, null, null);
        //check for the first item, if it exists or not
        if (cursor.moveToFirst()) {
            do {
                //read the database record from the cursor position
                Song song = new Song();
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String albumName = cursor.getString(2);
                String duration = cursor.getString(3);
                String album_cover = cursor.getString(4);
                //set the details to song object
                song.setId(id);
                song.setTitle(title);
                song.setCover_big(album_cover);
                song.setAlbumTitle(albumName);
                song.setDuration(duration);
                songs.add(song);
            } while (cursor.moveToNext()); //move the cursor to next row
        }

        //close cursor
        cursor.close();
        //close database connection
        db.close();
        return songs;
    }

    /**
     * Returns a single song details from the database
     * @param songId Id of a record which can distinguished from other records
     * */
    public Song getSongDetails(String songId) {
        Song song = new Song();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVOURITE_SONGS,
                new String[]{
                        COL_SONG_ID, COL_SONG_TITLE,
                        COL_SONG_ALBUM_NAME,
                        COL_SONG_DURATION,
                        COL_SONG_ALBUM_COVER_PATH
                },
                COL_SONG_ID + "=?", new String[]{songId},
                null, null, null, null);
        if (cursor.moveToFirst()) {
            String id = cursor.getString(0);
            String title = cursor.getString(1);
            String albumName = cursor.getString(2);
            String duration = cursor.getString(3);
            String album_cover = cursor.getString(4);
            song.setId(id);
            song.setTitle(title);
            song.setCover_big(album_cover);
            song.setAlbumTitle(albumName);
            song.setDuration(duration);
        }

        //close cursor
        cursor.close();
        //close database connection
        db.close();
        return song;
    }

    /**
     * Inserts the song details in the database
     * */
    public void insertSong(Song song) {
        //get writable database
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        //map the column name and it's corresponding values
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SONG_ID, song.getId());
        contentValues.put(COL_SONG_TITLE, song.getTitle());
        contentValues.put(COL_SONG_ALBUM_NAME, song.getAlbumTitle());
        contentValues.put(COL_SONG_ALBUM_COVER_PATH, song.getCover_big());
        contentValues.put(COL_SONG_DURATION, song.getDuration());
        //perform insertion
        sqLiteDatabase.insert(TABLE_FAVOURITE_SONGS, null, contentValues);
        //close database connection
        sqLiteDatabase.close();
    }

    /**
     * Removes the record from the database
     * @param songId Id of a record which can distinguished from other records
     * */
    public void deleteSong(String songId) {
        //get writable database to perform deletion
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_FAVOURITE_SONGS, COL_SONG_ID + " = ?",
                new String[]{songId});
        //close database connection
        sqLiteDatabase.close();
    }

    /**
     * Checks if the record is already inserted or not
     * @param songId Id of a record which can distinguished from other records
     * @return true if the record with same songId exists, false otherwise
     * */
    public boolean isInserted(String songId) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_FAVOURITE_SONGS,
                new String[]{COL_SONG_ID},
                COL_SONG_ID + "=?", new String[]{songId},
                null, null, null, null);
        boolean inserted = cursor.moveToFirst();
        //close cursor
        cursor.close();
        //close database connection
        sqLiteDatabase.close();
        return inserted;
    }


}
