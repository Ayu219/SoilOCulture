package com.example.soiloculture.recommend;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soiloculture.R;
import com.example.soiloculture.loadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RecommendActivity extends AppCompatActivity {
    EditText nitro,phos,potas,pH,state,city;
    Button predict;
    TextView result;
    String url = "https://mlmodelcrop.onrender.com/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.crop_recommend);
        final loadingDialog  loadingDialog = new loadingDialog(RecommendActivity.this);
        nitro=findViewById(R.id.nitro);
        potas=findViewById(R.id.potas);
        phos=findViewById(R.id.phos);
        pH=findViewById(R.id.pH);
        state=findViewById(R.id.state);
        city=findViewById(R.id.city);
        predict=findViewById(R.id.predict);
        result=findViewById(R.id.result);
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoading();
                //hit the API
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("crop");
                                    result.setText(data);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecommendActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("nitro",nitro.getText().toString());
                        params.put("potas",potas.getText().toString());
                        params.put("phos",phos.getText().toString());
                        params.put("pH",pH.getText().toString());
                        params.put("state",state.getText().toString());
                        params.put("city",city.getText().toString());



                        return  params;}

                };
                RequestQueue queue = Volley.newRequestQueue(RecommendActivity.this);
                queue.add(stringRequest);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                },5000);
            }
        });

    }

}
