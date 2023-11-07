package com.example.aplicacaobancodedados;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ExcluirDados extends AppCompatActivity {

    TextView txtstatus_registro, txtnome, txttelefone, txtemail;
    SQLiteDatabase db;
    ImageView imgprimeiro, imganterior, imgproximo, imgultimo;
    Button btexcluirdados;
    int indice;
    int numreg;
    Cursor c;
    DialogInterface.OnClickListener diExluirRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_dados);

        IniciarDados();

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);

            CarregarDados();

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

            diExluirRegistro = (dialogInterface, i) -> {
                db.execSQL("DELETE FROM usuarios WHERE numreg = " + numreg);
                CarregarDados();
                MostrarMensagem("Dados excluidos com sucesso!");
            };

            btexcluirdados.setOnClickListener(view -> {
                if (c.getCount() > 0) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle("Confirma");
                    dialogo.setMessage("Deseja excluir esse registro ?");
                    dialogo.setNegativeButton("Não", null);
                    dialogo.setPositiveButton("Sim", diExluirRegistro);
                    dialogo.show();
                } else {
                    MostrarMensagem("Não existem registro para excluir");
                }
            });

        } catch (Exception e){
            MostrarMensagem("Erro: " + e.toString());
        }
    }

    public void CarregarDados(){
        c = db.query("usuarios", new String[]{"numreg", "nome", "telefone", "email"}, null, null, null, null, null);

        txtnome.setText("");
        txttelefone.setText("");
        txtemail.setText("");

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

    private void MostrarMensagem(String str) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
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
        btexcluirdados = findViewById(R.id.btexcluirdados);
        txtnome.setText("");
        txttelefone.setText("");
        txtemail.setText("");
    }
}