package br.edu.insper.al.sophiaks.projeto_minimo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class HomeRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_register);

        Button buttonProfessor = findViewById(R.id.button_professor);
        Button buttonStudent = findViewById(R.id.button_student);
        ImageButton buttonHome = findViewById(R.id.button_home);

        buttonHome.setOnClickListener((view) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        buttonProfessor.setOnClickListener((view) -> {
            Intent intent = new Intent(this, ProfessorRegister.class);
            startActivity(intent);
        });

        buttonStudent.setOnClickListener((view) -> {
            Intent intent = new Intent(this, StudentRegister.class);
            startActivity(intent);
        });
    }
}
