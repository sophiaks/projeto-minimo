package br.edu.insper.al.sophiaks.projeto_minimo;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public String url= "https://sistema.programasemente.com.br/profile/auth_view_mobile/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        setContentView(R.layout.activity_main);
        Button buttonLogin = findViewById(R.id.button_login);
        final EditText textUsername = findViewById(R.id.text_username);
        final EditText textPassword = findViewById(R.id.text_password);
        //          METODO DE Login
        buttonLogin.setOnClickListener((view) -> {
            String username = textUsername.getText().toString();
            String password = textPassword.getText().toString();
            if(validateLogin(username, password)) {
                //do login
                doLogin(username, password);
            }
        });
    }
    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void doLogin(final String username,final String password) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams .put("username",username);
            jsonParams .put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Request a string response from the provided URL.
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //getJsonData();
//                JSONArray jsonArray = null;
//                try {
//                    jsonArray = new JSONArray(response);
//                } catch (JSONException e){
//                    e.printStackTrace();
//                }
                Log.d("VOLLEY", response);
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response", error.toString());
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                7000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        // Add the request to the RequestQueue.
        queue.add(postRequest);
    }

}

