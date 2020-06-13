package br.edu.insper.al.sophiaks.projeto_minimo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public String url= "http://sistema.programasemente.com.br/profile/auth_view_mobile/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        setContentView(R.layout.activity_main);
        Button buttonLogin = findViewById(R.id.button_login);
        Button buttonRegister = findViewById(R.id.button_register);
        Button buttonToken = findViewById(R.id.button_token);
        final EditText textUsername = findViewById(R.id.text_username);
        final EditText textPassword = findViewById(R.id.text_password);

        //          METODO DE LOGIN
        buttonLogin.setOnClickListener((view) -> {
            String username = textUsername.getText().toString();
            String password = textPassword.getText().toString();
            if(validateLogin(username, password)) {
                doLogin(username, password);
            }
        });

        buttonRegister.setOnClickListener((view) -> {
            Intent intentRegister = new Intent(this, HomeRegister.class);
            startActivity(intentRegister);
            finish();
        });

        buttonToken.setOnClickListener((view) ->{
            // Get token
            // [START retrieve_current_token]
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w("MainActivity", "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();

                            // Log and toast
//                            String msg = getString(R.string.msg_token_fmt, token);
                            Log.v("TOKEN", token);
                            Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                        }
                    });
            // [END retrieve_current_token]
            Intent intent = new Intent(this, Notifications.class);
            startActivity(intent);
        });

        //Esconde o teclado quando clica fora do EditText
        textUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !textPassword.hasFocus()) {
                    hideKeyboard(v);}
            }
        });

        textPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !textUsername.hasFocus()) {
                    hideKeyboard(v);}
            }
        });

    }

    // Esconde o teclado quando clica fora do EditText
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //      CHECA SE FOI COLOCADO UM USERNAME E PASSWORD
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


    //       EFETUA UM LOGIN
    private void doLogin(final String username,final String password) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("VOLLEY", response);
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                if (!response.contains("False")) {
                    goToPage(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY", error.toString());
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

        // Add the request to the RequestQueue.
        queue.add(postRequest);
    }

    // METODO PRA PASSAR PARA PAGINA HOME
    private void goToPage(String response) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("response", response);
        startActivity(intent);
        finish();
    }
}

