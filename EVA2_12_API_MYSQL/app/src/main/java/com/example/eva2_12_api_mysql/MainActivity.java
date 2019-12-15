package com.example.eva2_12_api_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lstJSON;
    ArrayList miLista = new ArrayList<JSONObject>();
    TextView txtVwDatos;
    Button btnMos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //txtVwDatos = findViewById(R.id.txtVwDatos);
        btnMos = findViewById(R.id.bntMostrar);

    }

    public void onClick(View view ){
        new MYSQLAPIconecction().execute();
    }

    //CREAR A LA CONDEXIÓN
    class MYSQLAPIconecction extends AsyncTask<String, Void, String>{
        final String url="http://10.8.17.0:3000/Tasks/";//hay que cambiar la ip cada que se corra a la del host actual

        @Override
        protected String doInBackground(String... strings) {

            String sResu=null;
            try {
                URL ruta = new URL(url);
                HttpURLConnection httpCon = (HttpURLConnection) ruta.openConnection();
                /*
                if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader isr = new InputStreamReader(httpCon.getInputStream());
                    BufferedReader br = new  BufferedReader(isr);
                    sResu = br.readLine();*/
                httpCon.setRequestMethod("POST");
                httpCon.setDoInput(true);
                httpCon.setDoOutput(true);
                httpCon.setRequestProperty("Content-Type","application/json;charset=utf-8");
                httpCon.connect();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Product name", "qqqqqqqqq");
                jsonObject.put("UnitPrice", 555555);

                DataOutputStream escribir = new DataOutputStream(httpCon.getOutputStream());
                escribir.write(jsonObject.toString().getBytes());
                escribir.flush();
                escribir.close();

                InputStream inputStream = httpCon.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer resu = new StringBuffer();
                while((sResu = br.readLine()) != null){
                    resu.append(sResu);
                    resu.append("\n");
                }

            } catch (ProtocolException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            return sResu;
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, "onPostExetu", Toast.LENGTH_SHORT).show();
            if(s != null){
                try{
                JSONArray jsonArray = new JSONArray(s);
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject jsProducObject = jsonArray.getJSONObject(i);
                    miLista.add(jsProducObject);
                    txtVwDatos.append("ID: "+ jsProducObject.getString("ProductoID") + "\n");
                    txtVwDatos.append("Product: "+ jsProducObject.getString("ProductName") + "\n");
                    txtVwDatos.append("Quantity: "+ jsProducObject.getString("QuantityPerUnit") + "\n");
                    txtVwDatos.append("Price: "+ jsProducObject.getString("UnitPrice") + "\n");
                    }
                lstJSON.setAdapter(new MiArrayAdapter(MainActivity.this, R.layout.mi_layout, miLista));
                }catch (JSONException e) {
                    e.printStackTrace();
                }

                txtVwDatos.setText(s);
            }
        }
    }

}
