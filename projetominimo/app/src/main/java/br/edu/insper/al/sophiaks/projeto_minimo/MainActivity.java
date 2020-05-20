package br.edu.insper.al.sophiaks.projeto_minimo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonLogin = findViewById(R.id.button_login);
        final EditText textUsername = findViewById(R.id.text_username);
        final EditText textPassword = findViewById(R.id.text_password);

        //          METODO DE TRAÇO
        buttonLogin.setOnClickListener((view) -> {
            String url = "http://sistema.programasemente.com.br/profile/auth_view_mobile/";
            String username = textUsername.getText().toString();
            String password = textPassword.getText().toString();
            System.out.print("Clicou");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("true")){
                        startActivity(new Intent(MainActivity.this,Home.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.print("oops");
                }
            });
        });
    }
    }

