package com.example.nasser.movie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.nasser.movie.R.id.radioGroupSortBy;

public class MainActivity extends AppCompatActivity {


    MovieDataModel[] MovieDataModelS;
    RadioGroup radioGroupSortBy;
    ListView lst;
    MovieAdapter movieAdapter;
    String url;
    String sortBy;
    String year;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        lst = (ListView) findViewById(R.id.lst);
        radioGroupSortBy = (RadioGroup) findViewById(R.id.radioGroupSortBy);
        editText = (EditText) findViewById(R.id.editsearch);
        Button buttonSearch = (Button) findViewById(R.id.buttonSearch);


        String url = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=4be1334b68f88f308d8d7a46397aa86c";
        final Gson gson = new Gson();
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), response,Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            MovieDataModelS = gson.fromJson(jsonArray.toString(), MovieDataModel[].class);
                            movieAdapter = new MovieAdapter(getApplicationContext(), MovieDataModelS);
                            lst.setAdapter(movieAdapter);
                            Toast.makeText(getApplicationContext(), MovieDataModelS[0].getOverview(), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myintent = new Intent(MainActivity.this, MovieInfo.class);
                myintent.putExtra("movie", MovieDataModelS);
                startActivity(myintent);
            }
        });
    }

    public void buttonSearchOnClick(View view) {
        switch (radioGroupSortBy.getCheckedRadioButtonId()) {
            case R.id.radioButtonReleaseDateAsc:
                sortBy = "&sort_by=release_date.asc";
                break;
            case R.id.radioButtonReleaseDateDsc:
                sortBy = "&sort_by=release_date.desc";
                break;
            case R.id.radioButtonPopularityAsc:
                sortBy = "&sort_by=popularity.asc";
                break;
            case R.id.radioButtonPopularityDes:
                sortBy = "&sort_by=popularity.desc";
                break;
            default:
                break;
        }
        url = url + sortBy;
        year = editText.getText().toString();
        editText.setText("");
        year = "&primary_release_year=" + year;
        url = url + year;

    }

}
