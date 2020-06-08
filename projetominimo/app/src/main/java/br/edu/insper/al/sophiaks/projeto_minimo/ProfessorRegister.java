package br.edu.insper.al.sophiaks.projeto_minimo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ProfessorRegister extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    ListView listaGenero;
    ListView optProfessor;
    String[] names;
    String[] ptypes;
    String codigo;
    String postRequest;
    private Boolean showQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        listaGenero = (ListView) findViewById(R.id.listaGenero);
        optProfessor = (ListView) findViewById(R.id.optProfessor);
        // here we adjust list elements choice mode
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
        codProf.setOnEditorActionListener((v, actionId, event) -> {
            String codProfessor = codProf.getText().toString();
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://plataformasementedev.minimo.com.br/escolas/api/list?format=json&codigo_professores=" + codProfessor;
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, (Response.Listener<String>) response -> {
                if (response != null) {
                    showQuestions = true;
                } else {
                    Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VOLLEY", error.toString());
                    Toast.makeText(ProfessorRegister.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            });
            return false;
        });
    }
}
