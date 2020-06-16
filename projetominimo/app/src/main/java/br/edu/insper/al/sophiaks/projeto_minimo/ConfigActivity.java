package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfigActivity extends AppCompatActivity {
    TextView configUsername;
    TextView configSerie;
    TextView configAcesso;
    Button logout;
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
        configUsername = findViewById(R.id.configUsername);
        configAcesso = findViewById(R.id.configAcesso);
        configSerie = findViewById(R.id.configSerie);
        logout = findViewById(R.id.button_logout);
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
        Log.d("id",username);
        configUsername.setText(username);
        configAcesso.setText(acesso);
        configSerie.setText(serie);
        logout.setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
