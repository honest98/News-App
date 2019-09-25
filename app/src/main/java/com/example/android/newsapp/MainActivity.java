package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.newsapp.model.News;
import com.example.android.newsapp.view.NewsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<News> arrayList;
    NewsAdapter adapter;

    final String URL_NEWS = "https://newsapi.org/v2/everything?q=bitcoin&from=2019-08-25&sortBy=publishedAt&apiKey=086bee9095ae4b32ad819e3e4fc83bac";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        arrayList = new ArrayList<>();

        jsonRead();

    }

    public void jsonRead(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL_NEWS,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                jsonParsing(response);

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
    }

    public void jsonParsing(String response){

        try {
            JSONObject jsonRoot = new JSONObject(response);
            JSONArray jsonArray = jsonRoot.getJSONArray("articles");
            for (int i=0; i<jsonArray.length(); i++ ){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                String imageUrl = jsonObject.getString("urlToImage");

                arrayList.add(new News(title, description, imageUrl));
            }

            adapter = new NewsAdapter(this, arrayList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
