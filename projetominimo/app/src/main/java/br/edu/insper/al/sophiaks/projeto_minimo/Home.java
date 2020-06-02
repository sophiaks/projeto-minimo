package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.pager);

        mQueue = Volley.newRequestQueue(this);
        linkedTitle = new LinkedList<String>();
        linkedVimeo= new LinkedList<String>();
        linkedThumb= new LinkedList<String>();
        linkedCategory= new LinkedList<String>();

        jsonParse();


    }

    private void jsonParse(){
        String url= "http://sistema.programasemente.com.br/dashboard/index_mobile/5/54829/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            mTextViewResult.setText(response.toString());
                            Log.d("Response", response.toString());
                            JSONArray jsonArray = response.getJSONArray("data");
//                            JSONObject dataa = jsonArray.getJSONObject(0);
//                            String videoUrl = dataa.getString("vimeo");
//                            String titlee = dataa.getString("nome");
//                            titleView.setText(titlee);
//                            String videoId = dataa.getString("url");
//                            Log.d("Video", videoUrl);
//                            webView.loadUrl("https://vimeo.com/24577973");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                String nome = data.getString("nome");
                                linkedTitle.add(nome);
                                String vimeo = data.getString("vimeo");
                                linkedVimeo.add(vimeo);
                                String categoria = data.getString("categoria");
                                linkedCategory.add(categoria);
                                String thumb = data.getString("thumbnail");
                                linkedThumb.add(thumb);
                            }

                            // Pass results to ViewPagerAdapter Class
                            adapter = new ViewPagerAdapter(Home.this, linkedTitle, linkedVimeo, linkedCategory, linkedThumb);
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
