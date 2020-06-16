package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;

public class Home extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue mQueue;


    public ArrayList<ExampleVideo> exampleVideos = new ArrayList<>();
    String response;
    Intent loginPage;
    JSONObject login;
    Bundle extras;
    String serie;
    String id;
    EditText search;
    ImageButton bell;
    String loginUser;
    String username;
    ImageButton config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        config = findViewById(R.id.configButton);

        bell = findViewById(R.id.bell);




        // Busca as informações do Post do login
        loginPage = getIntent();
        extras = loginPage.getExtras();
        if (extras != null){
            try {
                login = new JSONObject(loginPage.getStringExtra("response")); // pega as infos como um JSONObject
                loginUser = loginPage.getStringExtra("response");
                username = loginPage.getStringExtra("username");
            } catch(JSONException e) {
                e.printStackTrace();
            };
        }
        try {
            serie = login.getString("serie").toString();
            id = login.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("id", serie + " " + id);

        mQueue = Volley.newRequestQueue(this);

        jsonParse();
        search = findViewById(R.id.search);

        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);}
            }
        });

        config.setOnClickListener(view ->{
            goToConfig(loginPage.getStringExtra("response"));
        });
        bell.setOnClickListener((view) -> {
            goToNot(loginPage.getStringExtra("response"));
        });
    }
    //Vai pra página de configurações
    private void goToConfig(String response) {
        Log.d("loginUser", loginUser);
        Log.d("username", username);
        Intent intent = new Intent(this, ConfigActivity.class);
        intent.putExtra("response", response);
        intent.putExtra("loginUser", loginUser);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    //Vai pra página de configurações
    private void goToNot(String response) {
        Log.d("loginUser", loginUser);
        Log.d("username", username);
        Intent intent = new Intent(this, Notifications.class);
        intent.putExtra("response", response);
        intent.putExtra("loginUser", loginUser);
        intent.putExtra("username", username);
        startActivity(intent);
    }


    // Esconde o teclado quando clica fora do EditText
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    private void jsonParse(){
        String url= "http://sistema.programasemente.com.br/dashboard/index_mobile/"+serie+"/"+id+"/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("Response", response.toString());
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++){
                                // Data assume a chave dos dicionários dentro do value da key "data"
                                JSONObject data = jsonArray.getJSONObject(i);

//                                Capture name and add in a linked List
                                String nome = data.getString("nome");

//                                Capture url vimeo and add in a linked List
                                String vimeo = data.getString("vimeo");

//                                Capture category and add in a linked List
                                String categoria = data.getString("categoria");

//                                Capture thumb and add in a linked List
                                String thumb = data.getString("thumbnail");
                                //                                Capture thumb and add in a linked List
                                String vimeoid = data.getString("url");


                                ExampleVideo exampleVideo = new ExampleVideo(vimeoid,nome,categoria);
                                exampleVideos.add(exampleVideo);
                            }

                            // Pass results to ViewPagerAdapter Class
                            buildRecyclerView();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextViewResult.setText("ERROR");
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recycleVideos);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new VideoAdapter(exampleVideos);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


}
