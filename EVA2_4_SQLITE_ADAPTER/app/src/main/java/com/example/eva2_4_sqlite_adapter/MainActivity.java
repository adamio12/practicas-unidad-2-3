package com.example.eva2_4_sqlite_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sDB_adaptador;
    ListView lstVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstVw = findViewById(R.id.lstVw);
        sDB_adaptador = openOrCreateDatabase("db_adaptador", MODE_PRIVATE, null);
        try {
            sDB_adaptador.execSQL("create table prueba(id integer primary key autoincrement, dato text)");
        }catch (Exception e){
            e.printStackTrace();
        }

        sDB_adaptador.execSQL("insert into prueba(dato) values ('capa')");
        sDB_adaptador.execSQL("insert into prueba(dato) values ('huarache')");
        sDB_adaptador.execSQL("insert into prueba(dato) values ('túnica')");
        sDB_adaptador.execSQL("insert into prueba(dato) values ('calcetín')");

        Cursor cursor = sDB_adaptador.rawQuery("select id as _id, dato from prueba order by dato",null);
        lstVw.setAdapter(new MiCursorAdapter(this, cursor));

    }
}
