package com.example.anik.amarbangladesh;

import android.content.Intent;
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
    //final ArrayList<String> details = new ArrayList<String>();
    //final ArrayList<String> images = new ArrayList<String>();
    final ArrayList<String> ids=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixty_four_zilla);



        requestQueue = Volley.newRequestQueue(this);
        //final TextView result = (TextView) findViewById(R.id.anik);
        listView = (ListView) findViewById(R.id.listView);
        searchView= (SearchView) findViewById(R.id.serchViewMain);

        getData();
    }



    private static final String country_list = "http://192.168.0.101/amarBangladesh/district.php";

    public void getData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, country_list, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        //name.add(jsonObject);
                        String title = (String) jsonObject.get("name");
                        //String story = (String) jsonObject.get("story");
                        //String image = (String) jsonObject.get("image");
                        String id=(String) jsonObject.get("id");


                        list.add(title);
                        //details.add(story);
                        //images.add(image);
                        ids.add(id);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(MainActivity.this, ""+list, Toast.LENGTH_SHORT).show();
                    //result.setText(valueOf(name));
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(sixtyFourZilla.this, R.layout.array_view, R.id.textView1, list);
                    listView.setAdapter(adapter);


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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String userdata = details.get(position);
                //String userImage = images.get(position);
                String contentId=ids.get(position);
                //Toast.makeText(MainActivity.this, contentId, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(sixtyFourZilla.this, details.class);
                //intent.putExtra("data", userdata);
                //intent.putExtra("images", userImage);
                intent.putExtra("id",contentId);

                startActivity(intent);
            }
        });
    }

}
