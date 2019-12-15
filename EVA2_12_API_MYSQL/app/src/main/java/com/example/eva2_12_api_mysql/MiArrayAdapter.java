package com.example.eva2_12_api_mysql;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

public class MiArrayAdapter extends ArrayAdapter<JSONObject> {
    Context context;
    int resource;
    List<JSONObject> objetcs;


    public MiArrayAdapter(@NonNull Context context, int resource, @NonNull List<JSONObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objetcs = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layout = ((Activity) context).getLayoutInflater();
            layout.inflate(resource, parent);
        }
        TextView txtVwProd, txtVwUnitP;
        txtVwProd = convertView.findViewById(R.id.txtVwProd);
        txtVwUnitP = convertView.findViewById(R.id.txtVwUnitP);
        return super.getView(position, convertView, parent);
    }

}
