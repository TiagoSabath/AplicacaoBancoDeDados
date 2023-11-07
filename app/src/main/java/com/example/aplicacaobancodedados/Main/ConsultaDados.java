package com.example.aplicacaobancodedados.Main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplicacaobancodedados.R;

public class ConsultaDados extends AppCompatActivity {

    TextView txtnome, txttelefone, txtemail, txtstatus_registro;
    SQLiteDatabase db;
    ImageView imgprimeiro, imganterior, imgproximo, imgultimo;
    int indice;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_dados);

        Iniciar();

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);

            c = db.query("usuarios", new String[]{"nome", "telefone", "email"}, null,null,null,null,null);


        if (c.getCount() > 0) {
            c.moveToFirst();
            indice = 1;
            txtnome.setText(c.getString(0));
            txttelefone.setText(c.getString(1));
            txtemail.setText(c.getString(2));

            txtstatus_registro.setText(indice + " / " + c.getCount());
        }else {
            txtstatus_registro.setText("Nenhum Registro");
        }

        imgprimeiro.setOnClickListener(view -> {
            if (c.getCount() > 0) {
                c.moveToFirst();
                indice = 1;
                txtnome.setText(c.getString(0));
                txttelefone.setText(c.getString(1));
                txtemail.setText(c.getString(2));
                txtstatus_registro.setText(indice + " / " + c.getCount());
            }
        });

        imganterior.setOnClickListener(view -> {
            if (c.getCount() > 0) {
                if (indice > 1) {
                    indice--;
                    c.moveToPrevious();
                    txtnome.setText(c.getString(0));
                    txttelefone.setText(c.getString(1));
                    txtemail.setText(c.getString(2));
                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });

        imgproximo.setOnClickListener(view -> {
            if (c.getCount() > 0) {
                if (indice != c.getCount()) {
                    indice++;
                    c.moveToNext();
                    txtnome.setText(c.getString(0));
                    txttelefone.setText(c.getString(1));
                    txtemail.setText(c.getString(2));
                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });

        imgultimo.setOnClickListener(view -> {
            if (c.getCount() > 0) {
                c.moveToLast();
                indice = c.getCount();
                txtnome.setText(c.getString(0));
                txttelefone.setText(c.getString(1));
                txtemail.setText(c.getString(2));
                txtstatus_registro.setText(indice + " / " + c.getCount());
            }
        });

        }catch (Exception e){
            MostraMensagem("Erro: " + e.toString());
        }
    }

    private void MostraMensagem(String str) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

    private void Iniciar() {

        txtnome= findViewById(R.id.txtnome);
        txttelefone= findViewById(R.id.txttelefone);
        txtemail= findViewById(R.id.txtemail);
        imgprimeiro= findViewById(R.id.imgprimeiro);
        imganterior= findViewById(R.id.imganterior);
        imgproximo= findViewById(R.id.imgproximo);
        imgultimo= findViewById(R.id.imgultimo);
        txtstatus_registro= findViewById(R.id.txtstatus_registro);

        txtnome.setText("");
        txttelefone.setText("");
        txtemail.setText("");
    }
}