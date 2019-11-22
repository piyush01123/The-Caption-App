package com.example.thecaptionapp;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TooSlow extends AppCompatActivity {

    private TextView tv;
    private TextView tv2;
    private View pb;
    private Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "all ok");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tooslow);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv = (TextView) findViewById(R.id.tvtooslow);
        tv2 = (TextView) findViewById(R.id.testRes);
        testBtn = (Button) findViewById(R.id.testServer);

        pb = (View) findViewById(R.id.progbar2);
        pb.setVisibility(View.GONE);


        testBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        testTheServer();
                    }
                }
        );
    }

    private void testTheServer(){
        pb.setVisibility(View.VISIBLE);
        String cap_url = "https://image-caption-app.herokuapp.com/";
        StringRequest sreq = new StringRequest(Request.Method.GET, cap_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volleyResponse", response.toString());
                tv2.setText("Test Successful");
                pb.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
                tv2.setText("Test Fail :(");
            }
        });
        int MY_SOCKET_TIMEOUT_MS=100000; //100 seconds timeout
        sreq.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(sreq);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_app) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.tooSlow) {
            Intent intent = new Intent(this, TooSlow.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.bonus) {
            Intent intent = new Intent(this, BonusActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
