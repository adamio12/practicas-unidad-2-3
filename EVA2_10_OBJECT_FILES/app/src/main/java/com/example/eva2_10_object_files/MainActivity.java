package com.example.eva2_10_object_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    EditText edtTxtNom, edtTxtAp;
    TextView txtVwDatos;
    RadioButton rdBtnM,rdBtnF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxtNom = findViewById(R.id.edtTxtNom);
        edtTxtAp = findViewById(R.id.edtTxtAp);
        txtVwDatos = findViewById(R.id.txtVwDatos);
        rdBtnM = findViewById(R.id.rdBtnMas);
        rdBtnF = findViewById(R.id.rdBtnFem);
    }

    public void onClick(View view){
        Guardar();
        Leer();
    }

    public void Guardar(){
        int igen;
        if(rdBtnM.isChecked()) {
             igen = 0;
        }else if (rdBtnF.isChecked()){
             igen=1;
        }else
             igen=2;
        Personas pObj= new Personas(edtTxtNom.getText().toString(),edtTxtAp.getText().toString(),
                igen);
        try {
            FileOutputStream fos = openFileOutput("datos.xxx",0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pObj);
            oos.writeObject(new Personas());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Leer(){
            ObjectInputStream ois=null;
        try{
        FileInputStream fis = openFileInput("datos.xxx");
         ois = new ObjectInputStream(fis);
        Personas pObj = (Personas) ois.readObject();
        while(true){
            String gen="error";
            if(pObj.getGenero()==0){
                 gen = "masculino" ;
            }else if (pObj.getGenero()==1){
                 gen = "femenino";
            }

            txtVwDatos.append("Nombre " + pObj.getNombre() + " " + pObj.getApellido() + "\n");
            //txtVwDatos.append("Genero " + pObj.getGenero()+ "\n");
            txtVwDatos.append("Genero " + gen + "\n");
            txtVwDatos.append("---------------\n");
            pObj = (Personas) ois.readObject();
        }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            if (ois != null){
                try{
                    ois.close();
                }
                 catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Personas implements Serializable {
    private String nombre;
    private String apellido;
    private int genero;

    public Personas() {
        this.nombre = "mob";
        this.apellido = "kagueyama";
        this.genero = 0 ;

    }

    public Personas(String nombre, String apellido, int genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
}