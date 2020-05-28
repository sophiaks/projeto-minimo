package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Home extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextViewResult = findViewById(R.id.text_teste);
        Button button = findViewById(R.id.button_teste);

        mQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse(){
        String url= "http://sistema.programasemente.com.br/dashboard/index_mobile/5/54829/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
//                            mTextViewResult.setText(response.toString());
                        Log.d("Response", response.toString());
                        JSONArray jsonArray = response.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject data = jsonArray.getJSONObject(i);

                            String nome = data.getString("nome");
                            String vimeo = data.getString("vimeo");
                            String categoria = data.getString("categoria");


                            mTextViewResult.append(nome + ", "+ "url vimeo:"+ vimeo + ". Categoria :" + categoria + "\n");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    mTextViewResult.setText("ERROR");
                    error.printStackTrace();
                });

        mQueue.add(request);
    }
}
