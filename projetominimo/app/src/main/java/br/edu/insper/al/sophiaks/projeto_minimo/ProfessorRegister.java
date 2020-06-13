package br.edu.insper.al.sophiaks.projeto_minimo;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;

public class ProfessorRegister extends AppCompatActivity {
    RequestQueue queue;
    final String LOG_TAG = "myLogs";
    ListView optProfessor;
    String[] genlist;
    String[] ptypes;
    TextView textOptProf;

    EditText nameProf;
    String genValue;
    String optValue;
    EditText emailProf;
    EditText passwordProf ;
    EditText passwordConfProf;
    Button btnValid;
    Button btnregister;
    EditText codProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        Spinner listaGenero = findViewById(R.id.listaGenero);
        Spinner optProfessor = findViewById(R.id.optProfessor);
        nameProf = findViewById(R.id.nameProf);
        TextView textgen = findViewById(R.id.textgeneroProf);
        TextView textdataProf = findViewById(R.id.textdataProf);
        textOptProf = findViewById(R.id.textOptProf);
        emailProf = findViewById(R.id.emailprof);
        String hint_email = emailProf.getText().toString();
        passwordProf = findViewById(R.id.passwordProf);
        String hint_password = passwordProf.getText().toString();
        passwordConfProf = findViewById(R.id.passwordConfProf);
        btnValid = findViewById(R.id.validBtton);
        btnregister = findViewById(R.id.btnFinish);
        ImageButton buttonHomep = findViewById(R.id.button_homep);
        String hint_name = nameProf.getText().toString();

        // get array from resources file
        genlist = getResources().getStringArray(R.array.names);
        ptypes = getResources().getStringArray(R.array.ptypes);
        // create adapter using array from resources file
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genlist);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ptypes);

        listaGenero.setAdapter(adapter);
        optProfessor.setAdapter(adapter2);

        codProf = findViewById(R.id.cod_prof);
        queue = Volley.newRequestQueue(this);

        buttonHomep.setOnClickListener((view) -> {
            Intent intentRegister = new Intent(this, MainActivity.class);
            startActivity(intentRegister);
            //finish();
        });

        listaGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                genValue = listaGenero.getItemAtPosition(position).toString(); //
                //getter method
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        optProfessor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                optValue = optProfessor.getItemAtPosition(position).toString(); //
                //getter method
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        btnValid.setOnClickListener((view) -> {
                String codProfessor = codProf.getText().toString();
                String url = "http://plataformasementedev.minimo.com.br/escolas/api/list?format=json&codigo_professores=" + codProfessor;
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        textdataProf.setVisibility(View.VISIBLE);
                        textgen.setVisibility(View.VISIBLE);
                        nameProf.setVisibility(View.VISIBLE);
                        listaGenero.setVisibility(View.VISIBLE);
                        textOptProf.setVisibility(View.VISIBLE);
                        optProfessor.setVisibility(View.VISIBLE);
                        emailProf.setVisibility(View.VISIBLE);
                        passwordProf.setVisibility(View.VISIBLE);
                        passwordConfProf.setVisibility(View.VISIBLE);
                        btnregister.setVisibility(View.VISIBLE);
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
                        message = "Código da Escola Inválido";
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
        btnregister.setOnClickListener((view) -> {
            if (nameProf.getText().toString().equals(hint_name) || emailProf.getText().toString().equals(hint_email)){
                Toast.makeText(ProfessorRegister.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            } else {
                if (passwordProf.getText().toString().equals(hint_password)){
                    Toast.makeText(ProfessorRegister.this, "Defina uma senha!", Toast.LENGTH_LONG).show();
                }
                else if (passwordConfProf.getText().toString().equals(passwordProf.getText().toString())){
                    doRegister();
                    Intent intentRegister = new Intent(this, MainActivity.class);
                    startActivity(intentRegister);
                } else {
                    Toast.makeText(ProfessorRegister.this, "As duas senhas precisam ser iguais!", Toast.LENGTH_LONG).show();
                }
            }


        });

        //Esconde o teclado quando clica fora do EditText
        codProf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        nameProf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !emailProf.hasFocus()) {
                    hideKeyboard(v);
                }
            }
        });

        emailProf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !passwordProf.hasFocus()) {
                    hideKeyboard(v);
                }
            }
        });

        passwordProf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !passwordConfProf.hasFocus()) {
                    hideKeyboard(v);
                }
            }
        });

        passwordConfProf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    //Esconde o teclado quando clica fora do EditText
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void doRegister(){
        // Instantiate the RequestQueue.
        String url = "http://plataformasementedev.minimo.com.br/services/cadastro_mobile";;
        // Request a string response from the provided URL.
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ProfessorRegister.this, response, Toast.LENGTH_LONG).show();
//                    if (!response.contains("False")) {
//                        goToPage(response);
//                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY", error.toString());
                Toast.makeText(ProfessorRegister.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("perfil", optValue);
                params.put("codigo_professores", codProf.getText().toString());
                params.put("first_name", nameProf.getText().toString());
                params.put("serie_id", "");
                params.put("email_responsavel", emailProf.getText().toString());
                params.put("email_responsavel2", "");
                params.put("responsavel", "");
                params.put("genero", genValue);
                params.put("password", passwordProf.getText().toString());
                params.put("username", emailProf.getText().toString());
                Log.v("json professor", params.toString());
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(postRequest);

    }
}

