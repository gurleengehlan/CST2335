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
 * List adapter for showing Search results
 * */
public class DeezerArtistListAdapter extends BaseAdapter {

    /**
     * Artists data
     * */
    ArrayList<Artist> artistArrayList;
    /** Layout inlfater to bind the layout with the adapter class */
    static LayoutInflater inflater;

    public DeezerArtistListAdapter(Context context, ArrayList<Artist> artists){
        artistArrayList = artists;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return artistArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return artistArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.deezer_artist_list, parent, false);
        }
        //displaying the data to the text view
        TextView textArtistName = convertView.findViewById(R.id.textArtistName);
        textArtistName.setText(artistArrayList.get(position).getName());
        return convertView;
    }
}
