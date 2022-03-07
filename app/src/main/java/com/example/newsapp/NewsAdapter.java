package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        News currNews = getItem(position);

        ImageView newsThumbnail = (ImageView) convertView.findViewById(R.id.imageView);
        try{
            URL imageURL = new URL(currNews.getThumbnail());
            Glide.with(getContext()).load(imageURL).into(newsThumbnail);}
        catch (IOException e){
            e.printStackTrace();
        }

        TextView heading = (TextView) listItemView.findViewById(R.id.heading);
        heading.setText(currNews.getHeading());

        TextView time = (TextView) listItemView.findViewById(R.id.time);
        heading.setText(currNews.getTime());

        TextView source = (TextView) listItemView.findViewById(R.id.source);
        heading.setText(currNews.getSource());

        return listItemView;

    }
}
