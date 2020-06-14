package br.edu.insper.al.sophiaks.projeto_minimo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    String[] types;
    boolean confirmPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Spinner listaGenero = findViewById(R.id.listaGeneroStudent);
        Spinner listaSerie = findViewById(R.id.listaSerie);
        nameStudent = findViewById(R.id.nameStudent);
        confirmPassword = false;
        username = findViewById(R.id.nomeusuario);
        nameRes = findViewById(R.id.nomeres);
        TextView textGen = findViewById(R.id.textgeneroStudent);
        TextView accessdata = findViewById(R.id.accessdata);
        TextView emailtext_resp = findViewById(R.id.emailtext_resp);
        TextView emailtext_resp2 = findViewById(R.id.emailtext_resp2);
        TextView respname = findViewById(R.id.respname);
        TextView textSerie = findViewById(R.id.textSerie);
        TextView name = findViewById(R.id.name);
        TextView usernametext = findViewById(R.id.usernametext);
        TextView passwordtext = findViewById(R.id.passwordtext);
        TextView passwordtextconf = findViewById(R.id.passwordtextconf);
        TextView resptext = findViewById(R.id.resptext);
        TextView dadosStudent = findViewById(R.id.dataStudent);
        ImageButton buttonHomea = findViewById(R.id.button_homea);
        emailRes1 = findViewById(R.id.emailres1);
        emailRes2 = findViewById(R.id.emailres2);
        passwordStudent = findViewById(R.id.passwordStudent);
        passwordConfStudent = findViewById(R.id.passwordConfStudent);
        btnValid = findViewById(R.id.validBtton_student);
        btnregister = findViewById(R.id.btnFinish_Student);

        types = new String[]{"Escolha a série", "E.I. - 4 anos", "E.I. - 5 anos", "E.F. I - 1° ano","E.F. I - 2° ano","E.F. I - 3° ano",
                "E.F. I - 4° ano","E.F. I - 5° ano", "E.F. II - 6° ano","E.F. II - 7° ano","E.F. II - 8° ano","E.F. II - 9° ano",
                "E.M. - 1ª Série","E.M. - 2ª Série","E.M. - 3ª Série"};
        //
        names = getResources().getStringArray(R.array.names);
        ptypes = getResources().getStringArray(R.array.ptypes);
        codAluno = findViewById(R.id.cod_student);
        queue = Volley.newRequestQueue(this);



//        create adapter using array from resources file
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, names);
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
//                this, R.array.seriestypes,
//                android.R.layout.simple_list_item_single_choice);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
//set the spinners adapter to the previously created one.
        listaGenero.setAdapter(adapter);
        listaSerie.setAdapter(adapter2);
        // get array from resources file

        buttonHomea.setOnClickListener((view) -> {
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
        listaSerie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position ==0){
                    serieValue = "0";
                }
                else if (position == 1){
                    serieValue = "19";
                }
                else if (position ==2){
                    serieValue = "20";
                }
                else if (position ==3){  //EFI 1 ano
                    serieValue = "5";
                }
                else if (position ==4){
                    serieValue = "6";
                }
                else if (position ==5){
                    serieValue = "7";
                }
                else if (position ==6){
                    serieValue = "8";
                }
                else if (position ==7){
                    serieValue = "9";
                }
                else if (position ==8){
                    serieValue = "1";
                }
                else if (position ==9){
                    serieValue = "2";
                }
                else if (position ==10){
                    serieValue = "3";
                }
                else if (position ==11){
                    serieValue = "4";
                }
                else if (position ==12){
                    serieValue = "10";
                }
                else if (position ==13){
                    serieValue = "11";
                }
                else if (position ==14){
                    serieValue = "12";
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        btnValid.setOnClickListener((view) -> {
            String codStudent = codAluno.getText().toString();
            String url = "http://plataformasementedev.minimo.com.br/escolas/api/list?format=json&codigo_alunos=" + codStudent;
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        respname.setVisibility(View.VISIBLE);
                        emailtext_resp.setVisibility(View.VISIBLE);
                        emailtext_resp2.setVisibility(View.VISIBLE);
                        usernametext.setVisibility(View.VISIBLE);
                        passwordtext.setVisibility(View.VISIBLE);
                        passwordtextconf.setVisibility(View.VISIBLE);
                        resptext.setVisibility(View.VISIBLE);
                        name.setVisibility(View.VISIBLE);
                        accessdata.setVisibility(View.VISIBLE);
                        dadosStudent.setVisibility(View.VISIBLE);
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
            if(validateRegister(nameStudent.getText().toString(), username.getText().toString(), nameRes.getText().toString(), emailRes1.getText().toString(), passwordStudent.getText().toString(), passwordConfStudent.getText().toString())) {
                if (passwordConfStudent.getText().toString().equals(passwordStudent.getText().toString())) {
                    doRegister();
                    Intent intentRegister = new Intent(this, MainActivity.class);
                    startActivity(intentRegister);
                } else {
                    Toast.makeText(StudentRegister.this, "As duas senhas precisam ser iguais!", Toast.LENGTH_LONG).show();
                }
            }


        });

        //Esconde o teclado quando clica fora do EditText
        codAluno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);}
            }
        });

        nameStudent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !username.hasFocus()) {
                    hideKeyboard(v);}
            }
        });

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !passwordStudent.hasFocus()) {
                    hideKeyboard(v);}
            }
        });

        passwordStudent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !passwordConfStudent.hasFocus()) {
                    hideKeyboard(v);}
            }
        });

        passwordConfStudent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !nameRes.hasFocus()) {
                    hideKeyboard(v);}
            }
        });

        nameRes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !emailRes1.hasFocus()) {
                    hideKeyboard(v);}
            }
        });

        emailRes1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !emailRes2.hasFocus()) {
                    hideKeyboard(v);}
            }
        });

        emailRes2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);}
            }
        });


    }


    // Esconde o teclado quando clica fora do EditText
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void doRegister(){
        // Instantiate the RequestQueue.
        String url = "http://plataformasementedev.minimo.com.br/services/cadastro_mobile";
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

    private boolean validateRegister(String nameStudent, String username, String nameRes, String emailRes1, String passwordStudent, String passwordConfStudent){
        if(nameStudent == null || nameStudent.trim().length() == 0){
            Toast.makeText(this, "Insira o seu nome", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Insira um user", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nameRes == null || nameRes.trim().length() == 0){
            Toast.makeText(this, "Insira o nome do responsável", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(emailRes1 == null || emailRes1.trim().length() == 0){
            Toast.makeText(this, "Insira o email do responsável", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(passwordStudent == null || passwordStudent.trim().length() == 0){
            Toast.makeText(this, "Insira uma senha", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(passwordConfStudent == null || passwordConfStudent.trim().length() == 0){
            Toast.makeText(this, "Confirme a senha", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(serieValue == "0") {
            Toast.makeText(this, "Escolha uma série", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
