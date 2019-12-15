package com.example.eva2_9_files_internal_space;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText edt;
    TextView txtVwMostrar;
    final String NOMBRE_ARCH= "mi_archivo.txt";
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwMostrar = findViewById(R.id.txtVw);
        edt = findViewById(R.id.editText);
        try{
            String sCade ;
            InputStream is = openFileInput(NOMBRE_ARCH);
            InputStreamReader osw = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(osw);
            while( (sCade = br.readLine()) != null){
                txtVwMostrar.append(sCade);
                txtVwMostrar.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        try{
            String[] sCade = edt.getText().toString().split("\n");
            OutputStream os = openFileOutput(NOMBRE_ARCH, 0);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            for (int i=0;i<sCade.length; i++){
                bw.append(sCade[i]);
                bw.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
