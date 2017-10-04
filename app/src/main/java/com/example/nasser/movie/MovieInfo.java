package com.example.nasser.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MovieInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        ImageView Poster = (ImageView) findViewById(R.id.pster);
        RatingBar ratingbar=(RatingBar)findViewById(R.id.ratingbar);

        TextView movienmae = (TextView) findViewById(R.id.namemovie);
        TextView info = (TextView) findViewById(R.id.info);
        TextView date = (TextView) findViewById(R.id.date);

        Intent myintent = getIntent();
        MovieDataModel movieModel = (MovieDataModel) myintent.getSerializableExtra("movie");



    }
}
