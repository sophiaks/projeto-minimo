package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfigActivity extends AppCompatActivity {
    TextView configUsername = findViewById(R.id.configUsername);
    Intent loginPage;
    Bundle extras;
    JSONObject loginUser;
    String serie;
    String acesso;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        loginPage = getIntent();
        extras = loginPage.getExtras();
        if (extras != null){
            try {
                loginUser = new JSONObject(loginPage.getStringExtra("loginUser")); // pega as infos como um JSONObject
                username = loginPage.getStringExtra("username");
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            serie = loginUser.getString("serie").toString();
            acesso = loginUser.getString("acesso").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("id", serie + " " + acesso);
//        configUsername.setText(username);
    }
}
