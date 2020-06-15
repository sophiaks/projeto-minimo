package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Notifications extends AppCompatActivity {
    public ArrayList<ExampleNotification> notifications = new ArrayList<>();
    Button buttonnot;
    String title;
    public DatabaseManager db;
    public Integer totalNotificacoes = 0;
    public Integer idAtual = 0;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        loadNotification();
        buildRecyclerView();
        buttonnot = findViewById(R.id.buttonnot);
        buttonnot.setOnClickListener((view) -> {
            refreshNotification();
            buildRecyclerView();
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
    public void refreshNotification(){

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
