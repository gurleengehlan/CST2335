package com.example.cst2335;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cst2335.deezer.DeezerMainActivity;
import com.example.cst2335.geo_data_source.GeoMainActivity;
import com.example.cst2335.lyrics_ovh.LyricsMainActivity;
import com.example.cst2335.soccer.SoccerMatchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button geoButton = findViewById(R.id.geo_data_source_button);

        geoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GeoMainActivity.class);
                startActivity(intent);
            }
        });

        Button soccerButton = findViewById(R.id.buttonSoccer);
        soccerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SoccerMatchActivity.class);
                startActivity(intent);
            }
        });

        Button lyricsApp = findViewById(R.id.buttonLyricsSearch);
        lyricsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LyricsMainActivity.class);
                startActivity(intent);
            }
        });

        Button deezer = findViewById(R.id.deezer_song_search);
        deezer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeezerMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
