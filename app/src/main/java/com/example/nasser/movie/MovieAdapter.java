package com.example.nasser.movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends ArrayAdapter<MovieDataModel>
{

    public MovieAdapter(@NonNull Context context, @NonNull MovieDataModel[] objects)
    {
        super(context, 0, objects);
    }

    String buildurl (String size , String imagepath)
    {
        return getContext().getString(R.string.base_image_url)+size+imagepath;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movies_listview_item_row , parent , false);
        }
        MovieDataModel movieModel = getItem(position);
        ImageView img = (ImageView)convertView.findViewById(R.id.imageViewListViewImage);
     // Picasso.with(getContext()).load("url").into(img);
        TextView textViewMovieNameListView =(TextView)convertView.findViewById(R.id.textViewMovieNameListView);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500/"+movieModel.getPosterPath()).into(img);

       // AutofitHelper.create();
        String url = buildurl("w300",movieModel.getPosterPath());
        TextView textViewMovieOverViewListView=(TextView)convertView.findViewById(R.id.textViewMovieOverViewListView);
        textViewMovieOverViewListView.setText(movieModel.getOverview());
        textViewMovieNameListView.setText(movieModel.getTitle());

        return convertView;
    }
}
