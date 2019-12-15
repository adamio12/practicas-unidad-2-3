package com.example.eva2_7_sqlitesdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //OBTENER LA RUTA DE LA SD CARD
        String sRuta = Environment.getExternalStorageDirectory().getPath();
        Toast.makeText(this, sRuta,Toast.LENGTH_SHORT).show();
        String sDir = "eva2_7_sqlite_sdcard";
        String sdb = "prueba";
        File fPath  = new File(sRuta+"/"+sDir+"/");
        if(!fPath.exists()){
            fPath.mkdir();//primera vez, se cerea la ruta
        }
        String sPath = sRuta+"/"+sDir+"/"+sdb;
        SQLiteDatabase sDB = SQLiteDatabase.openDatabase(sPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

    }
}
