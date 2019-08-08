package com.example.session5_task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.session5_task.MovieClass.MovieClass;
import com.example.session5_task.MovieClass.Search;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button btnSearch;
    EditText edtSearch;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch = findViewById(R.id.btnSearch);
        edtSearch = findViewById(R.id.edtSearch);
        recyclerView = findViewById(R.id.recyclerView);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://www.omdbapi.com/?s=" + edtSearch.getText().toString() + "&apikey=40376d37";
                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            Gson gson = new Gson();
                            MovieClass movieClass = gson.fromJson(response.toString(), MovieClass.class);
                            List<Search> movies = movieClass.getSearch();
                            if(movies.size()==0)
                            {

                                Toast.makeText(MainActivity.this,"No Movies Found",Toast.LENGTH_LONG).show();
                                 return;
                            }

                            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(movies);
                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
            }
        });


    }
}

