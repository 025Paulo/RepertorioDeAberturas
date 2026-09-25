package com.example.repertoriodeaberturas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repertoriodeaberturas.model.Abertura;

import java.util.List;

public class AberturaAdapter extends RecyclerView.Adapter<AberturaAdapter.AberturaViewHolder> {

    private List<Abertura> lista;
    private final ListaAberturasActivity activity;

    public AberturaAdapter(List<Abertura> lista, ListaAberturasActivity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AberturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_abertura, parent, false);
        return new AberturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AberturaViewHolder holder, int position) {
        Abertura a = lista.get(position);

        holder.txtNome.setText(a.getNome());
        holder.txtCor.setText(activity.getString(R.string.item_color, a.getCor()));
        holder.txtEco.setText(activity.getString(R.string.item_category, a.getCategoria()));
        holder.txtVariante.setText(
                a.isGambito()
                        ? activity.getString(R.string.item_with_gambit)
                        : activity.getString(R.string.item_without_gambit)
        );
        holder.txtDificuldade.setText(
                activity.getString(
                        R.string.item_notes,
                        a.getObservacoes().isEmpty() ? "-" : a.getObservacoes()
                )
        );

        holder.btnEditar.setContentDescription(
                activity.getString(R.string.editar) + " " + a.getNome()
        );
        holder.btnExcluir.setContentDescription(
                activity.getString(R.string.excluir) + " " + a.getNome()
        );

        holder.btnEditar.setOnClickListener(v -> activity.editarAbertura(a));
        holder.btnExcluir.setOnClickListener(v -> activity.confirmarExclusao(a));

        holder.itemView.setOnLongClickListener(v -> {
            activity.iniciarMenuContextual(a);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public void atualizarLista(List<Abertura> novaLista) {
        this.lista = novaLista;
        notifyDataSetChanged();
    }

    static class AberturaViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtCor, txtEco, txtVariante, txtDificuldade;
        ImageButton btnEditar, btnExcluir;

        public AberturaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtCor = itemView.findViewById(R.id.txtCor);
            txtEco = itemView.findViewById(R.id.txtEco);
            txtVariante = itemView.findViewById(R.id.txtVariante);
            txtDificuldade = itemView.findViewById(R.id.txtDificuldade);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}