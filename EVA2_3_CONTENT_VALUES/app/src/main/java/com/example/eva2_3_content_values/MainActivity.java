package com.example.eva2_3_content_values;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase slDB;
    EditText edtTxtNom, edtTxtApe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxtNom = findViewById(R.id.edtTxtNom);
        edtTxtApe = findViewById(R.id.edtTxtApe);
        slDB = openOrCreateDatabase("db_prueba", MODE_PRIVATE,null);
        try{
            slDB.execSQL("CREATE table datos( datosID integer primary key autoincrement, nombre text, apellido text)");
        }catch(SQLiteException e){
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        ContentValues cvDatos=new ContentValues();
        cvDatos.put("nombre", edtTxtNom.getText().toString());
        cvDatos.put("apellidos", edtTxtApe.getText().toString());
        slDB.insert("datos", null, cvDatos);
        //cvDatos.clear();
    }
}
