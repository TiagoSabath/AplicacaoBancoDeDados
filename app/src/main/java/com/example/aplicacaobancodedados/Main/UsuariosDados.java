package com.example.aplicacaobancodedados.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.aplicacaobancodedados.Adapter.UsuariosAdapter;
import com.example.aplicacaobancodedados.R;
import com.example.aplicacaobancodedados.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDados extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);

        listView = findViewById(R.id.list_item);
        List<Usuarios> listaUsuarios = recuperarUsuarios();

        UsuariosAdapter adapter = new UsuariosAdapter(this, listaUsuarios, db);
        listView.setAdapter(adapter);
    }
    private List<Usuarios> recuperarUsuarios() {
        List<Usuarios> listaUsuarios = new ArrayList<>();
        String consultaSQL = "SELECT * FROM usuarios";
        Cursor cursor = db.rawQuery(consultaSQL, null);

        int numregColumnIndex = cursor.getColumnIndex("numreg");
        int nomeColumnIndex = cursor.getColumnIndex("nome");
        int telefoneColumnIndex = cursor.getColumnIndex("telefone");
        int emailColumnIndex = cursor.getColumnIndex("email");


        if (cursor.moveToFirst()) {
            do {
                int numreg = cursor.getInt(numregColumnIndex);
                String nome = cursor.getString(nomeColumnIndex);
                String telefone = cursor.getString(telefoneColumnIndex);
                String email = cursor.getString(emailColumnIndex);

                Usuarios usuario = new Usuarios(nome, telefone, email, numreg);
                listaUsuarios.add(usuario);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaUsuarios;
    }
}