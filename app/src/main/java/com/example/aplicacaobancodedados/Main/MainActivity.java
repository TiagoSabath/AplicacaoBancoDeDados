package com.example.aplicacaobancodedados.Main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import com.example.aplicacaobancodedados.AlterarDados;
import com.example.aplicacaobancodedados.AlterarDados2;
import com.example.aplicacaobancodedados.ExcluirDados;
import com.example.aplicacaobancodedados.R;

public class MainActivity extends AppCompatActivity {

    Button btcriarbanco, btcadastrardados, btcadastrardados2, btconsultar, btAlterarDados, btAlterarDados2, btExcluirdados;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btcriarbanco = findViewById(R.id.btcriarbanco);
        btcadastrardados = findViewById(R.id.btcadastrardados);
        btcadastrardados2 = findViewById(R.id.btcadastrardados2);
        btconsultar = findViewById(R.id.btconsultar);
        btAlterarDados = findViewById(R.id.btAlterarDados);
        btAlterarDados2 = findViewById(R.id.btAlterarDados2);
        btExcluirdados = findViewById(R.id.btExcluirdados);

        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

        btcriarbanco.setOnClickListener(view -> {

            try {
                db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS usuarios (numreg INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " nome TEXT NOT NULL, telefone TEXT NOT NULL, email TEXT NOT NULL)");

                dialogo.setTitle("Aviso").setMessage("Banco de dados criados com sucesso!").setNeutralButton("OK", null).show();
            } catch (Exception e) {
                dialogo.setTitle("Erro").setMessage("Falha ao criar o banco de dados: " + e.getMessage()).setNeutralButton("OK", null).show();
            }
        });

        btcadastrardados.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GravaRegistros.class);
            startActivity(intent);
        });

        btcadastrardados2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GravaReistros2.class);
            startActivity(intent);
        });

        btconsultar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ConsultaDados.class);
            startActivity(intent);
        });

        btAlterarDados.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AlterarDados.class);
            startActivity(intent);
        });

        btAlterarDados2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AlterarDados2.class);
            startActivity(intent);
        });
        btExcluirdados.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExcluirDados.class);
            startActivity(intent);
        });


    }
}