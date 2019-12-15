package com.example.eva2_1_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //db es con lo que se conecta
        SQLiteDatabase db = openOrCreateDatabase( "miDb",MODE_PRIVATE,null);
        db.execSQL("create table hola(id int, nombre text)");
        db.execSQL("insert into hola values (1,'Jacobo')");
    }
}
