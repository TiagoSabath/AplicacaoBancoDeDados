package com.example.aplicacaobancodedados.Adapter;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacaobancodedados.R;
import com.example.aplicacaobancodedados.Usuarios;

import java.util.List;

public class UsuariosAdapter extends ArrayAdapter<Usuarios> {

    private Context context;
    private List<Usuarios> usuariosList;
    private SQLiteDatabase db ;

    public UsuariosAdapter(Context context, List<Usuarios> usuarios , SQLiteDatabase db) {
        super(context, 0, usuarios);
        this.context = context;
        this.usuariosList = usuarios;
        this.db = db;
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario, parent, false);
            }

            Usuarios usuario = usuariosList.get(position);

            TextView nomeTextView = convertView.findViewById(R.id.nomeTextView);
            TextView telefoneTextView = convertView.findViewById(R.id.telefoneTextView);
            TextView emailTextView = convertView.findViewById(R.id.emailTextView);
            Button btexcluir = convertView.findViewById(R.id.btexcluir);


            nomeTextView.setText(usuario.getNome());
            telefoneTextView.setText(usuario.getTelefone());
            emailTextView.setText(usuario.getEmail());

            final int itemPosition = position;

            btexcluir.setOnClickListener(view -> {
                if (itemPosition >= 0 && itemPosition < usuariosList.size()) {
                    int userId = usuariosList.get(itemPosition).getId(); // Obtenha o ID do usuário
                    Log.d("UsuariosAdapter", "Excluir usuário com ID: " + userId);
                    excluirUsuario(userId);
                }
            });

            btexcluir.setTag(position);
            return convertView;
        }

    private void excluirUsuario( int userId) {
        Log.d("MeuApp", "Tentando excluir usuário com ID: " + userId);

        if (userId >= 0) {
            String whereClause = "numreg = ?";
            String[] whereArgs = {String.valueOf(userId)};

            int rowsDeleted = db.delete("usuarios", whereClause, whereArgs);

            if (rowsDeleted > 0) {
                Usuarios usuarioExcluido = getUsuarioPorId(userId);
                if (usuarioExcluido != null) {
                    usuariosList.remove(usuarioExcluido);
                    notifyDataSetChanged();
                } else {

                    Log.e("MeuApp", "Usuário não encontrado na lista.");
                }
            } else {
                Log.e("MeuApp", "Falha ao excluir o usuário do banco de dados.");
                Toast.makeText(context, "Falha ao excluir o usuário", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("MeuApp", "ID de usuário inválido: " + userId);
            Toast.makeText(context, "ID de usuário inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private Usuarios getUsuarioPorId(int userId) {
        for (Usuarios usuario : usuariosList) {
            if (usuario.getId() == userId) {
                return usuario;
            }
        }
        return null;
    }

}
