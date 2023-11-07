package com.example.aplicacaobancodedados.Main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aplicacaobancodedados.R;

public class GravaReistros2 extends AppCompatActivity {
    Button btcadastrar2;
    EditText ednome, edtelefone, edemail;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grava_reistros2);

        btcadastrar2 = findViewById(R.id.btcadastrar2);
        ednome = findViewById(R.id.ednome);
        edtelefone = findViewById(R.id.edtelefone);
        edemail = findViewById(R.id.edemail);

        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
        }catch (Exception e){
            dialogo.setTitle("Erro").setMessage("Falha ao abrir o banco de dados: " + e.getMessage()).setNeutralButton("OK", null).show();
        }

        btcadastrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = ednome.getText().toString();
                String telefone = edtelefone.getText().toString();
                String email = edemail.getText().toString();

                ContentValues valor = new ContentValues();
                valor.put("nome", nome);
                valor.put("telefone", telefone);
                valor.put("email", email);


                try {
                    db.insert("usuarios", null,valor);
                    dialogo.setTitle("Aviso").setMessage("Dados cadastrados " ).setNeutralButton("OK", null).show();

                }catch (Exception e){
                    dialogo.setTitle("Erro").setMessage("Dados nao cadastrados " + e.getMessage()).setNeutralButton("OK", null).show();
                }
            }
        });

    }
}