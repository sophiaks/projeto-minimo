package br.edu.insper.al.sophiaks.projeto_minimo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {


    public DatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLIENTE");

        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE(\n" +
                "\tTITULO VARCHAR(70) NOT NULL,\n" +
                "\tCORPO VARCHAR(300) NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLIENTE");
    }

    //insert
    public void inserirNotificacao(String titulo,String corpo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("TITULO",titulo);
        cv.put("CORPO",corpo);
        db.insert("CLIENTE","TITULO",cv);
 // Esta ordenando pelo titulo

    }

    //select
    Cursor listaTodasNotificacoes(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT TITULO,CORPO FROM CLIENTE",null);
        return cur;
    }

    //update
    public void atualizaNotificacao(String titulo, String corpo){ // NÃO SEI SOBRE O CLIENT ID
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("TITULO",titulo);
        cv.put("CORPO",corpo);
        db.update("CLIENTE",cv,"TITULO=?",new String[]{titulo});
    }

    //delete
    public void removeNotificacao(String titulo){ // DECIDI DELETAR PELO TITULO.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CLIENTE","TITULO=?",new String[]{titulo});
    }




}

