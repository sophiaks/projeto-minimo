package br.edu.insper.al.sophiaks.projeto_minimo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Notifications extends AppCompatActivity {
    ArrayList<ExampleNotification> mExampleList;
    TextView testenot;
    Button buttonnot;
    SharedPreferences sharedPreferences;
    Set<String> setTitle;
    Set<String> setBody;
    String title;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        mExampleList = new ArrayList<>();
        loadNotification();
        buildRecyclerView();
        buttonnot = findViewById(R.id.buttonnot);
        buttonnot.setOnClickListener((view) -> {
            refreshNotification();
            buildRecyclerView();
        });
    }
    public void loadNotification(){
        sharedPreferences = getSharedPreferences("Notifications", Context.MODE_PRIVATE);
        setTitle = sharedPreferences.getStringSet("listTitle",null);
        setBody = sharedPreferences.getStringSet("listBody",null);
        if (setTitle != null){
            int n = setTitle.size();
            String[] arraytitle = new String[n];
            String[] arraybody = new String[n];
            arraytitle = setTitle.toArray(arraytitle);
            arraybody = setBody.toArray(arraybody);
            for (int i = 0; i < arraytitle.length; i++){
                mExampleList.add(new ExampleNotification(arraytitle[i], arraybody[i]));
            }
            sharedPreferences.edit().clear().apply();
        }
    }
    public void refreshNotification(){
        sharedPreferences = getSharedPreferences("Notifications", Context.MODE_PRIVATE);
        setTitle = sharedPreferences.getStringSet("listTitle",null);
        setBody = sharedPreferences.getStringSet("listBody",null);
        if (setTitle != null){
            int n = setTitle.size();
            String[] arraytitle = new String[n];
            String[] arraybody = new String[n];
            arraytitle = setTitle.toArray(arraytitle);
            arraybody = setBody.toArray(arraybody);
            for (int i = 0; i < arraytitle.length; i++){
                mExampleList.add(new ExampleNotification(arraytitle[i], arraybody[i]));
            }
            sharedPreferences.edit().clear().apply();
        }
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NotificationAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
