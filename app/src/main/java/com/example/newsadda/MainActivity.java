package com.example.newsadda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     private NewsAdapter newsAdapter;
     private ArrayList<News> newsList=new ArrayList<News>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                "https://api.currentsapi.services/v1/latest-news?%20language=en&apiKey=NR10QNpBfV2irlGd4-BL_oOQdHazvQ4iIY9pybmltdmsZQP0", null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray articles = response.getJSONArray("news");
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject newsObject = articles.getJSONObject(i);
                        String title=newsObject.getString("title");
                        String authors=newsObject.getString("author");
                        String image=newsObject.getString("image");
                        String newsUrl=newsObject.getString("url");
                        String timePublished=newsObject.getString("published");
                        newsList.add(new News(title,image,authors,newsUrl,timePublished));
                        ListView listView=findViewById(R.id.listView);
                        newsAdapter=new NewsAdapter(getApplicationContext(),newsList);
                        listView.setAdapter(newsAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String url = newsList.get(position).getURL() ;
                                Uri uri=Uri.parse(url);
                                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Error inside",Toast.LENGTH_SHORT).show();
                }
            }
           },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}