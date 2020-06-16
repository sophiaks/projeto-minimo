package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Notifications extends AppCompatActivity {
    public ArrayList<ExampleNotification> notifications = new ArrayList<>();
    Button buttonnot;
    String title;
    Intent loginPage;
    Bundle extras;
    JSONObject loginUser;
    String response;
    String username;
    public DatabaseManager db;
    public Integer totalNotificacoes = 0;
    public Integer idAtual = 0;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button button_token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        loginPage = getIntent();
        extras = loginPage.getExtras();
        if (extras != null){
            try {
                loginUser = new JSONObject(loginPage.getStringExtra("loginUser")); // pega as infos como um JSONObject
                username = loginPage.getStringExtra("username");
                response = loginPage.getStringExtra("response");
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }

        db = new DatabaseManager(this,"base",null,1);
        loadNotification();
        buildRecyclerView();
        buttonnot = findViewById(R.id.buttonnot);
        buttonnot.setOnClickListener((view) -> {
            deleteNotifications();
            buildRecyclerView();
        });

        ImageButton back = findViewById(R.id.back);

        back.setOnClickListener((view) -> {
            Intent intent = new Intent(this, Home.class);
            intent.putExtra("response", response);
            intent.putExtra("username", username);
            startActivity(intent);
        });
        button_token = findViewById(R.id.button_token);
        button_token.setOnClickListener((view) -> {
            // Get token
            // [START retrieve_current_token]
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w("MainActivity", "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();

                            // Log and toast
//                            String msg = getString(R.string.msg_token_fmt, token);
                            Log.v("TOKEN", token);
                            Toast.makeText(Notifications.this, token, Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }
    public void loadNotification(){

        Cursor c = db.listaTodasNotificacoes();

        if(c.getCount() > 0){
            c.moveToFirst();
            totalNotificacoes = c.getCount();
            idAtual = 0;

            notifications.clear();

            do{
                ExampleNotification exampleNotification = new ExampleNotification(c.getString(c.getColumnIndex("TITULO")),
                        c.getString(c.getColumnIndex("CORPO")));
                notifications.add(exampleNotification);

            }while(c.moveToNext());
        }
    }
    public void deleteNotifications(){
        for (ExampleNotification exampleNotification: notifications){
            db.removeNotificacao(exampleNotification.getText1());
        }
        notifications.clear();
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NotificationAdapter(notifications);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
