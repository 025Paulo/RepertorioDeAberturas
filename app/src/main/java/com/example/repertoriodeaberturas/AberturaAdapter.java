package com.example.repertoriodeaberturas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AberturaAdapter extends ArrayAdapter<Abertura> {

    private final Context context;
    private final ArrayList<Abertura> lista;

    public AberturaAdapter(Context context, ArrayList<Abertura> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_abertura, parent, false);
        }

        Abertura abertura = lista.get(position);

        TextView txtNome = convertView.findViewById(R.id.txtNome);
        TextView txtCor = convertView.findViewById(R.id.txtCor);
        TextView txtCategoria = convertView.findViewById(R.id.txtCategoria);
        TextView txtObservacao = convertView.findViewById(R.id.txtObservacao);

        txtNome.setText(abertura.getNome());
        txtCor.setText("Cor: " + abertura.getCor());
        txtCategoria.setText("Categoria: " + abertura.getCategoria());
        txtObservacao.setText("Obs: " + abertura.getObservacao());

        return convertView;
    }
}