package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

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
            String username = textUsername.getText().toString();
            String password = textPassword.getText().toString();

        });
    }


}
