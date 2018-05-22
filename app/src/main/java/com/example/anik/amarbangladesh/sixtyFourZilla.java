package com.example.anik.amarbangladesh;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class sixtyFourZilla extends AppCompatActivity {

    RequestQueue requestQueue;
    TextView textView;
    ListView listView;
    SearchView searchView;
    final ArrayList<String> list = new ArrayList<String>();
    final ArrayList<String> ids = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixty_four_zilla);
        requestQueue = Volley.newRequestQueue(this);
        listView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.serchViewMain);
        if (connectionCheck()) { // checking when connection is online
            //Toast.makeText(bashaAndolon.this, "Connected", Toast.LENGTH_SHORT).show();
            getData(); // After checking internet connection if connection is success i am calling data function
        } else { // checking when connection is not online
            //Toast.makeText(bashaAndolon.this, "Notconnected", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(sixtyFourZilla.this);
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

    private static final String country_list = "http://learnfromgame.com/amarBangladesh/district.php";

    public void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, country_list, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                progressDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        String title = (String) jsonObject.get("name");
                        String id = (String) jsonObject.get("id");
                        list.add(title);
                        ids.add(id);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(sixtyFourZilla.this, R.layout.array_view, R.id.textView1, list);
                    listView.setAdapter(adapter);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                adapter.getFilter().filter(newText);
                                return false;
                            }
                        });
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });
        requestQueue.add(jsonArrayRequest);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contentId = ids.get(position);
                Intent intent = new Intent(sixtyFourZilla.this, details.class);
                intent.putExtra("id", contentId);
                startActivity(intent);
            }
        });
    }

}
