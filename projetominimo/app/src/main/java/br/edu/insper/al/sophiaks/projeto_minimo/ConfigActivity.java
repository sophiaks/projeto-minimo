package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfigActivity extends AppCompatActivity {
    TextView configUsername;
    TextView configSerie;
    TextView configAcesso;
    Button logout;
    ImageButton back;
    Intent loginPage;
    Bundle extras;
    JSONObject loginUser;
    String serie;
    String acesso;
    String username;
    String response;
    String ano;
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
                response = loginPage.getStringExtra("response");
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

        if (serie.equals("1")){
            ano = "E.F. II - 6° ano";
        }
        if (serie.equals("2")){
            ano = "E.F. II - 7° ano";
        }
        if (serie.equals("3")){
            ano = "E.F. II - 8° ano";
        }
        if (serie.equals("4")){
            ano = "E.F. II - 9° ano";
        }
        if (serie.equals("5")){
            ano = "E.F. I - 1° ano";
        }
        else if (serie.equals("6")){
            ano = "E.F. I - 2° ano";
        }
        else if (serie.equals("7")){
            ano = "E.F. I - 3° ano";
        }
        else if (serie.equals("8")){
            ano = "E.F. I - 4° ano";
        }
        else if (serie.equals("9")){
            ano = "E.F. I - 5° ano";
        }
        else if (serie.equals("10")){
            ano = "E.M. - 1ª Série";
        }
        else if (serie.equals("11")){
            ano = "E.M. - 2ª Série";
        }
        else if (serie.equals("12")){
            ano = "E.M. - 3ª Série";
        }
        else if (serie.equals("19")){
            ano = "E.I. - 4 anos";
        }
        else if (serie.equals("20")){
            ano = "E.I. - 5 anos";
        }



        Log.d("id", serie + " " + acesso);
        Log.d("id",username);
        configUsername.setText(username);
        configAcesso.setText(acesso);
        configSerie.setText(ano);

        ImageButton back = findViewById(R.id.back);

        back.setOnClickListener((view) -> {
            Intent intent = new Intent(this, Home.class);
            intent.putExtra("response", response);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        logout.setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
