package br.edu.insper.al.sophiaks.projeto_minimo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class ProfessorRegister extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    ListView optProfessor;
    String[] names;
    String[] ptypes;
    TextView textOptProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        ListView listaGenero = findViewById(R.id.listaGenero);
        ListView optProfessor = findViewById(R.id.optProfessor);
        EditText nameProf = findViewById(R.id.nameProf);
        textOptProf = findViewById(R.id.textOptProf);
        EditText emailProf = findViewById(R.id.emailProf);
        EditText passwordProf = findViewById(R.id.passwordProf);
        EditText passwordConfProf = findViewById(R.id.passwordConfProf);
        Button btnValid = findViewById(R.id.validBtton);

        listaGenero.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        optProfessor.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // create adapter using array from resources file
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names,
                android.R.layout.simple_list_item_single_choice);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.ptypes,
                android.R.layout.simple_list_item_single_choice);
        listaGenero.setAdapter(adapter);
        optProfessor.setAdapter(adapter2);

        // get array from resources file
        names = getResources().getStringArray(R.array.names);
        ptypes = getResources().getStringArray(R.array.ptypes);
        final EditText codProf = findViewById(R.id.cod_prof);
        RequestQueue queue = Volley.newRequestQueue(this);
        btnValid.setOnClickListener((view) -> {
                String codProfessor = codProf.getText().toString();
                String url = "http://plataformasementedev.minimo.com.br/escolas/api/list?format=json&codigo_professores=" + "CODP";
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        nameProf.setVisibility(View.VISIBLE);
                        listaGenero.setVisibility(View.VISIBLE);
                        textOptProf.setVisibility(View.VISIBLE);
                        optProfessor.setVisibility(View.VISIBLE);
                        emailProf.setVisibility(View.VISIBLE);
                        passwordProf.setVisibility(View.VISIBLE);
                        passwordConfProf.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(ProfessorRegister.this, "Código inválido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    String message = null;
                    if (volleyError instanceof NetworkError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (volleyError instanceof ServerError) {
                        message = "The server could not be found. Please try again after some time!!";
                    } else if (volleyError instanceof AuthFailureError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (volleyError instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                    } else if (volleyError instanceof NoConnectionError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (volleyError instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                    }
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

                });
            queue.add(getRequest);
        });
    }
}

