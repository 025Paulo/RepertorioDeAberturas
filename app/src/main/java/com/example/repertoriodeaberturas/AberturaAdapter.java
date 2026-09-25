package com.example.repertoriodeaberturas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repertoriodeaberturas.model.Abertura;

import java.util.List;

public class AberturaAdapter extends RecyclerView.Adapter<AberturaAdapter.AberturaViewHolder> {

    private List<Abertura> lista;

    public AberturaAdapter(List<Abertura> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public AberturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_abertura, parent, false);
        return new AberturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AberturaViewHolder holder, int position) {
        Abertura abertura = lista.get(position);

        holder.txtNome.setText(abertura.getNome());
        holder.txtCor.setText("Cor: " + abertura.getCor());
        holder.txtEco.setText("ECO: " + abertura.getEco());
        holder.txtVariante.setText("Variante: " + abertura.getVariante());
        holder.txtDificuldade.setText("Dificuldade: " + abertura.getDificuldade());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void atualizarLista(List<Abertura> novaLista) {
        this.lista = novaLista;
        notifyDataSetChanged();
    }

    static class AberturaViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtCor, txtEco, txtVariante, txtDificuldade;

        public AberturaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtCor = itemView.findViewById(R.id.txtCor);
            txtEco = itemView.findViewById(R.id.txtEco);
            txtVariante = itemView.findViewById(R.id.txtVariante);
            txtDificuldade = itemView.findViewById(R.id.txtDificuldade);
        }
    }
}