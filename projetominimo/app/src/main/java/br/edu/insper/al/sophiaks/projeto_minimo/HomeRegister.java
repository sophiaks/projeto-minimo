package br.edu.insper.al.sophiaks.projeto_minimo;

import android.os.Bundle;
import android.widget.Button;

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
    }

}
