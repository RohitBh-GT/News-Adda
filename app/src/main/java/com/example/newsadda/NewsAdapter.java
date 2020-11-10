package com.example.newsadda;

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

import java.util.ArrayList;

class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(@NonNull Context context, ArrayList<News>arrayList) {
        super(context,0,arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView;
        listView=convertView;
        if(listView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.card,parent,false);
        }
        News news=getItem(position);

        TextView title=listView.findViewById(R.id.titleN);
        title.setText(news.getTitle());

        TextView authors=listView.findViewById(R.id.authors);
        authors.setText(news.getAuthors());

        ImageView image=listView.findViewById(R.id.imageView);
        Glide.with(listView.getContext()).load(news.getImageUrl()).into(image);

        TextView published=listView.findViewById(R.id.published);
        published.setText(news.getTime());

        return listView;
    }

}
