package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
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
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;

public class Home extends AppCompatActivity {
    private RequestQueue mQueue;
    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;

    LinkedList<String> linkedTitle;
    LinkedList<String> linkedVimeo;
    LinkedList<String> linkedThumb;
    LinkedList<String> linkedCategory;
    LinkedList<String> linkedVimeoUrl;
    Intent loginPage;
    JSONObject login;
    Bundle extras;
    String serie;
    String id;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Locate the ViewPager in activity_home.xml
        viewPager = findViewById(R.id.pager);

        // Busca as informações do Post do login
        loginPage = getIntent();
        extras = loginPage.getExtras();
        if (extras != null){
            try {
                login = new JSONObject(loginPage.getStringExtra("response")); // pega as infos como um JSONObject
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
        linkedTitle = new LinkedList<String>();
        linkedVimeo= new LinkedList<String>();
        linkedThumb= new LinkedList<String>();
        linkedCategory= new LinkedList<String>();
        linkedVimeoUrl = new LinkedList<String>();
        jsonParse();
        search = findViewById(R.id.search);

        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                                linkedTitle.add(nome);

//                                Capture url vimeo and add in a linked List
                                String vimeo = data.getString("vimeo");
                                linkedVimeo.add(vimeo);

//                                Capture category and add in a linked List
                                String categoria = data.getString("categoria");
                                linkedCategory.add(categoria);

//                                Capture thumb and add in a linked List
                                String thumb = data.getString("thumbnail");
                                linkedThumb.add(thumb);
                                //                                Capture thumb and add in a linked List
                                String vimeoid = data.getString("url");
                                linkedVimeoUrl.add(vimeoid);
                            }

                            // Pass results to ViewPagerAdapter Class
                            adapter = new ViewPagerAdapter(Home.this, linkedTitle, linkedVimeo, linkedCategory, linkedThumb, linkedVimeoUrl);
                            // Binds the Adapter to the ViewPager
                            viewPager.setAdapter(adapter);
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
}
