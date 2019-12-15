package com.example.eva2_4_sqlite_adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MiCursorAdapter extends CursorAdapter {
    Context context;
    Cursor cursor;

    public MiCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
        this.cursor = cursor;
    }

    public MiCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public MiCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cursor_layout,parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtVwDatos;
        txtVwDatos = view.findViewById(R.id.textView);
        txtVwDatos.setText(cursor.getString(cursor.getColumnIndex("dato")));
    }
}
