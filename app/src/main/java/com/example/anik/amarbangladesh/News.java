package com.example.anik.amarbangladesh;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.anik.amarbangladesh.domain.Domain;
import com.example.anik.amarbangladesh.domain.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class News extends AppCompatActivity {

    RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Domain> domains;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        requestQueue = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.mainRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        domains = new ArrayList<>();

        if (connectionCheck()) { // checking when connection is online
            //Toast.makeText(bashaAndolon.this, "Connected", Toast.LENGTH_SHORT).show();
            getData(); // After checking internet connection if connection is success i am calling data function
        } else { // checking when connection is not online
            //Toast.makeText(bashaAndolon.this, "Notconnected", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(News.this);
            builder.setMessage("Make sure your Internet Connection is on !");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }


    }

    private boolean connectionCheck() { // Thius function is for checking internet connection
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    private static final String newsList = "http://learnfromgame.com/amarBangladesh/news.php";


    public void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, newsList, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);

                progressDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        Domain data = new Domain(
                                jsonObject.getString("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("story"),
                                jsonObject.getString("image"),
                                jsonObject.getString("publish_date")
                        );
                        domains.add(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new MyAdapter(domains, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

}
