package com.example.aplicacaobancodedados;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AlterarDados2 extends AppCompatActivity {

    EditText txtnome, txttelefone, txtemail;
    TextView txtstatus_registro;
    SQLiteDatabase db;
    ImageView imgprimeiro, imganterior, imgproximo, imgultimo;
    Button btalterardados;
    int indice;
    int numreg;
    Cursor c;
    DialogInterface.OnClickListener diAlteraInformacoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados2);

        IniciarDados();

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);

            c = db.query("usuarios", new String[]{"numreg", "nome", "telefone", "email"}, null, null, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();
                indice = 1;
                numreg = c.getInt(0);
                txtnome.setText(c.getString(1));
                txttelefone.setText(c.getString(2));
                txtemail.setText(c.getString(3));

                txtstatus_registro.setText(indice + " / " + c.getCount());
            } else {
                txtstatus_registro.setText("Nenhum Registro");
            }
        }
        catch (Exception e){

        }

        imgprimeiro.setOnClickListener(view -> {
            if (c.getCount() > 0) {
                c.moveToFirst();
                indice = 1;
                numreg = c.getInt(0);
                txtnome.setText(c.getString(1));
                txttelefone.setText(c.getString(2));
                txtemail.setText(c.getString(3));
                txtstatus_registro.setText(indice + " / " + c.getCount());
            }
        });

        imganterior.setOnClickListener(view -> {
            if (c.getCount() > 0) {
                if (indice > 1) {
                    indice--;
                    c.moveToPrevious();
                    numreg = c.getInt(0);
                    txtnome.setText(c.getString(1));
                    txttelefone.setText(c.getString(2));
                    txtemail.setText(c.getString(3));
                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });

        imgproximo.setOnClickListener(view -> {
            if (c.getCount() > 0) {
                if (indice != c.getCount()) {
                    indice++;
                    c.moveToNext();
                    numreg = c.getInt(0);
                    txtnome.setText(c.getString(1));
                    txttelefone.setText(c.getString(2));
                    txtemail.setText(c.getString(3));
                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });

        imgultimo.setOnClickListener(view -> {
            if (c.getCount() > 0) {
                c.moveToLast();
                indice = c.getCount();
                numreg = c.getInt(0);
                txtnome.setText(c.getString(1));
                txttelefone.setText(c.getString(2));
                txtemail.setText(c.getString(3));
                txtstatus_registro.setText(indice + " / " + c.getCount());
            }
        });
        diAlteraInformacoes = (dialogInterface, i) -> {
            String nome = txtnome.getText().toString();
            String telefone = txttelefone.getText().toString();
            String email = txtemail.getText().toString();


            try {
                ContentValues valor = new ContentValues();

                valor.put("nome", nome);
                valor.put("telefone", telefone);
                valor.put("email", email);

                db.update("usuarios", valor, "numreg=" + numreg,null );


                MostrarMensagem("Dados Alterados Com Sucesso");
            }
            catch (Exception e){
                MostrarMensagem("Erro: " + e.toString());
            }
        };

        btalterardados.setOnClickListener(view -> {
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("Confirma");
            dialogo.setMessage("Deseja alterar as informações?");
            dialogo.setNegativeButton("Não", null);
            dialogo.setPositiveButton("Sim", diAlteraInformacoes);
            dialogo.show();
        });
    }

    private void MostrarMensagem(String str) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(AlterarDados2.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
    private void IniciarDados() {
        txtnome = findViewById(R.id.txtnome);
        txttelefone = findViewById(R.id.txttelefone);
        txtemail = findViewById(R.id.txtemail);
        txtstatus_registro = findViewById(R.id.txtstatus_registro);
        imgprimeiro = findViewById(R.id.imgprimeiro);
        imganterior = findViewById(R.id.imganterior);
        imgproximo = findViewById(R.id.imgproximo);
        imgultimo = findViewById(R.id.imgultimo);
        btalterardados = findViewById(R.id.btalterardados);
    }
}