package com.example.cst2335.deezer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cst2335.R;

import java.util.ArrayList;

/**
 * List adapter for showing Favourite songs
 */
public class FavouriteListAdapter extends BaseAdapter {

    /**
     * Layout inlfater to bind the layout with the adapter class
     */
    static LayoutInflater inflater;
    ArrayList<Song> songs;

    /**
     * @param context Context to be passed to initialize the inflater
     * @param songs List of songs to be displayed
     * */
    FavouriteListAdapter(Context context, ArrayList<Song> songs) {
        this.songs = songs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.favourite_song_list, parent, false);
        }

        //set the data to list item
        TextView textSongTitle = convertView.findViewById(R.id.textSongTitle);
        TextView textAlbumTitle = convertView.findViewById(R.id.textAlbumTitle);
        String songTitle = songs.get(position).getTitle();
        String album = songs.get(position).getAlbumTitle();
        textSongTitle.setText(songTitle);
        textAlbumTitle.setText(album);
        return convertView;
    }
}
