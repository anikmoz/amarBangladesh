package com.example.anik.amarbangladesh;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.example.anik.amarbangladesh.ads.InterstitialAds;
import com.example.anik.amarbangladesh.ads.InterstitialAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class details extends AppCompatActivity {


    TextView titleText;
    TextView textView;
    /*
        ScrollView scrollView;
    */
    ImageView imageView;
    AdView mAdView;
    public static final String GETBYIDLINK = "http://learnfromgame.com/amarBangladesh/get_by_id.php";
    String gotId;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        requestQueue = Volley.newRequestQueue(this);


//        MobileAds.initialize(this, " ca-app-pub-8724215326824300~2768235041");
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
//        mAdView.loadAd(adRequest);


        textView = (TextView) findViewById(R.id.detailsView);
        imageView = (ImageView) findViewById(R.id.imageView);
        titleText=(TextView) findViewById(R.id.titleTextView);

        InterstitialAds customAds = new InterstitialAds();
        customAds.adLoad(getApplicationContext(),getString(R.string.interstial_ad_unit));
/*
        scrollView = (ScrollView) findViewById(R.id.detailsScroll);
*/

        Bundle bundle = getIntent().getExtras();
        System.out.println(bundle);

        if (bundle != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading");
            progressDialog.show();
            //String value = bundle.getString("data");
            //String userImage = bundle.getString("images");
            //textView.setText(Html.fromHtml(value));
            // Toast.makeText(details.this, userImage, Toast.LENGTH_SHORT).show();
            //byte[] data = Base64.decode(userImage, Base64.DEFAULT);
            // Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            //imageView.setImageBitmap(bmp);
            //System.out.println(data);

            gotId = (String) bundle.getString("id");
            int convertid = Integer.parseInt(gotId);
            //Toast.makeText(details.this, gotId + convertid, Toast.LENGTH_SHORT).show();
            //System.out.println(gotId);

            //textView.setText(gotId + convertid);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, GETBYIDLINK, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    progressDialog.dismiss();

//                    home task tor json string theke parse kore kivabe
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            String title = (String) jsonObject.get("name");
                            String story = (String) jsonObject.get("story");
                            String image = (String) jsonObject.get("image");
                            //Toast.makeText(details.this, image, Toast.LENGTH_SHORT).show();

                            byte[] data = Base64.decode(image, Base64.DEFAULT);
                            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                            titleText.setText(title);
                            imageView.setImageBitmap(bmp);
                            //Log.e("app", "status" );
                            //System.out.println(story);
                            textView.setText(Html.fromHtml(story));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                  /*  System.out.println("got id in get parrams");
                    System.out.println("got id in get parrams");
                    System.out.println(gotId);
                    System.out.println(gotId);
                    System.out.println(gotId);*/
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", gotId);
                    return params;
                }
            };
            requestQueue.add(stringRequest); //run
        }
    }
}
