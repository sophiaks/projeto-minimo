package br.edu.insper.al.sophiaks.projeto_minimo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfessorRegister extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    ListView listaGenero;
    ListView optProfessor;
    String[] names;
    String[] ptypes;
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
    }
}
