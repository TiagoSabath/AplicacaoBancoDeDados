package com.example.aplicacaobancodedados.Main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aplicacaobancodedados.R;

public class GravaRegistros extends AppCompatActivity {

    Button btcadastrar, btDados;
    EditText ednome, edtelefone, edemail;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grava_registros);

        btcadastrar = findViewById(R.id.btcadastrar);
        btDados = findViewById(R.id.btDados);
        ednome = findViewById(R.id.ednome);
        edtelefone = findViewById(R.id.edtelefone);
        edemail = findViewById(R.id.edemail);

        AlertDialog.Builder dialogo = new AlertDialog.Builder(GravaRegistros.this);

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
        }catch (Exception e){
            dialogo.setTitle("Erro").setMessage("Falha ao abrir o banco de dados: " + e.getMessage()).setNeutralButton("OK", null).show();
        }

        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = ednome.getText().toString();
                String telefone = edtelefone.getText().toString();
                String email = edemail.getText().toString();

                try {
                    db.execSQL("INSERT INTO usuarios (nome, telefone, email) VALUES('" + nome + "','" + telefone + "','" + email + "')");
                    dialogo.setTitle("Aviso").setMessage("Dados cadastrados com sucesso ").setNeutralButton("OK", null).show();
                }catch (Exception e){
                    dialogo.setTitle("Erro").setMessage("Dados nao cadastrados " + e.getMessage()).setNeutralButton("OK", null).show();
                }
            }
        });
        btDados.setOnClickListener(view -> {
            Intent intent = new Intent(GravaRegistros.this, UsuariosDados.class);
            startActivity(intent);
        });

    }
}