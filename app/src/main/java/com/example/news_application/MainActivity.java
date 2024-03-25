package com.example.news_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsItemClick {

    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(new ArrayList<>(), this);
        LinearLayoutManager layoutManger =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManger);
        recyclerView.setAdapter(adapter);

        fetchData();
    }

    private void fetchData() {
        String url = "https://newsapi.org/v2/everything?q=bitcoin&from=2024-03-24&to=2024-03-24&sortBy=popularity&apiKey=837a71e2e8294cfb8f545491c6a15030";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newsJsonArray = response.getJSONArray("articles");
                            ArrayList<News> newsList = new ArrayList<>();
                            for (int i = 0; i < newsJsonArray.length(); i++) {
                                JSONObject obj = newsJsonArray.getJSONObject(i);
                                News news = new News(
                                        obj.optString("title"),
                                        obj.optString("author"),
                                        obj.optString("url"),
                                        obj.optString("urlToImage")
                                );
                                newsList.add(news);
                            }
                            adapter.updateNews(newsList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                        error.printStackTrace();
                    }
                }
        );

        // Add the request to the RequestQueue (assuming you have a MySingleton class)
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }



    @Override
    public void onitemClicked(News item) {
        Toast.makeText(this,"Clicked"+item,Toast.LENGTH_LONG).show();
    }
}