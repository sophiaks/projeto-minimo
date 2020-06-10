package br.edu.insper.al.sophiaks.projeto_minimo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class StudentRegister extends AppCompatActivity {
    RequestQueue queue;
    final String LOG_TAG = "myLogs";

    String[] names;
    String[] ptypes;
    EditText nameStudent;
    String genValue;
    String serieValue;
    EditText username;
    EditText nameRes;
    EditText emailRes1;
    EditText emailRes2;
    EditText passwordStudent ;
    EditText passwordConfStudent;
    Button btnValid;
    Button btnregister;
    EditText codAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        ListView listaGenero = findViewById(R.id.listaGeneroStudent);
        ListView listaSerie = findViewById(R.id.listaSerie);
        nameStudent = findViewById(R.id.nameStudent);

        username = findViewById(R.id.nomeusuario);
        nameRes = findViewById(R.id.nomeres);
        TextView textGen = findViewById(R.id.textgeneroStudent);
        TextView textSerie = findViewById(R.id.textSerie);
        ImageButton buttonHomea = findViewById(R.id.button_homea);
        emailRes1 = findViewById(R.id.emailres1);
        emailRes2 = findViewById(R.id.emailres2);
        passwordStudent = findViewById(R.id.passwordStudent);
        passwordConfStudent = findViewById(R.id.passwordConfStudent);
        btnValid = findViewById(R.id.validBtton_student);
        btnregister = findViewById(R.id.btnFinish_Student);
        listaGenero.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listaSerie.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // create adapter using array from resources file
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names,
                android.R.layout.simple_list_item_single_choice);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.seriestypes,
                android.R.layout.simple_list_item_single_choice);
        listaGenero.setAdapter(adapter);
        listaSerie.setAdapter(adapter2);
        // get array from resources file
        names = getResources().getStringArray(R.array.names);
        ptypes = getResources().getStringArray(R.array.ptypes);
        codAluno = findViewById(R.id.cod_student);
        queue = Volley.newRequestQueue(this);

        buttonHomea.setOnClickListener((view) -> {
            Intent intentRegister = new Intent(this, MainActivity.class);
            startActivity(intentRegister);
            //finish();
        });

        listaGenero.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                CharSequence gen_value = (CharSequence) listaGenero.getItemAtPosition(position); //
                genValue = gen_value.toString(); //getter method
            }
        });
        listaSerie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                serieValue = String.valueOf(position); //getter method
                if (position ==0){
                    serieValue = "19";;
                }
                else if (position ==1){
                    serieValue = "20";
                }
                else if (position ==2){  //EFI 1 ano
                    serieValue = "5";
                }
                else if (position ==3){
                    serieValue = "6";
                }
                else if (position ==4){
                    serieValue = "7";
                }
                else if (position ==5){
                    serieValue = "8";
                }
                else if (position ==6){
                    serieValue = "9";
                }
                else if (position ==7){
                    serieValue = "1";
                }
                else if (position ==8){
                    serieValue = "2";
                }
                else if (position ==9){
                    serieValue = "3";
                }
                else if (position ==10){
                    serieValue = "4";
                }
                else if (position ==11){
                    serieValue = "10";
                }
                else if (position ==12){
                    serieValue = "11";
                }
                else if (position ==13){
                    serieValue = "12";
                }
            }
        });


        btnValid.setOnClickListener((view) -> {
            String codStudent = codAluno.getText().toString();
            String url = "http://plataformasementedev.minimo.com.br/escolas/api/list?format=json&codigo_alunos=" + codStudent;
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        textGen.setVisibility(View.VISIBLE);
                        nameStudent.setVisibility(View.VISIBLE);
                        listaGenero.setVisibility(View.VISIBLE);
                        textSerie.setVisibility(View.VISIBLE);
                        listaSerie.setVisibility(View.VISIBLE);
                        emailRes1.setVisibility(View.VISIBLE);
                        emailRes2.setVisibility(View.VISIBLE);
                        username.setVisibility(View.VISIBLE);
                        btnregister.setVisibility(View.VISIBLE);
                        passwordConfStudent.setVisibility(View.VISIBLE);
                        passwordStudent.setVisibility(View.VISIBLE);
                        nameRes.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(StudentRegister.this, "Código inválido", Toast.LENGTH_SHORT).show();
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
        btnregister.setOnClickListener((view) -> {
            doRegister();
            Intent intentRegister = new Intent(this, MainActivity.class);
            startActivity(intentRegister);
        });
    }
    public void doRegister(){
        // Instantiate the RequestQueue.
        String url = "http://plataformasementedev.minimo.com.br/services/cadastro_mobile";;
        // Request a string response from the provided URL.
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(StudentRegister.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY", error.toString());
                Toast.makeText(StudentRegister.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("perfil", "Aluno");
                params.put("codigo_alunos", codAluno.getText().toString());
                params.put("first_name", nameStudent.getText().toString());
                params.put("serie_id", serieValue);
                params.put("email_responsavel", emailRes1.getText().toString());
                params.put("email_responsavel2", emailRes2.getText().toString());
                params.put("responsavel", nameRes.getText().toString());
                params.put("genero", genValue);
                params.put("password", passwordStudent.getText().toString());
                params.put("username", username.getText().toString());
                Log.v("json aluno", params.toString());
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(postRequest);

    }
}
