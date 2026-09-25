package com.example.repertoriodeaberturas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repertoriodeaberturas.model.Abertura;
import com.example.repertoriodeaberturas.model.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListaAberturasActivity extends AppCompatActivity {

    private RecyclerView recyclerAberturas;
    private Button btnAdicionar, btnSobre;
    private AberturaAdapter adapter;
    private List<Abertura> lista = new ArrayList<>();
    private AppDatabase db;
    private SharedPreferences prefs;

    private androidx.appcompat.view.ActionMode actionMode;
    private Abertura aberturaSelecionada;

    public static final String PREFS_NAME = "config_prefs";
    public static final String PREF_ORDENACAO = "ordenacao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aberturas);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.titulo_lista));
        }

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        recyclerAberturas = findViewById(R.id.recyclerAberturas);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnSobre = findViewById(R.id.btnSobre);

        db = AppDatabase.getInstance(this);

        adapter = new AberturaAdapter(lista, this);
        recyclerAberturas.setLayoutManager(new LinearLayoutManager(this));
        recyclerAberturas.setAdapter(adapter);

        btnAdicionar.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class)));

        btnSobre.setOnClickListener(v ->
                startActivity(new Intent(this, SobreActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarAberturas();
    }

    private void carregarAberturas() {
        String ordenacao = prefs.getString(PREF_ORDENACAO, "nome");

        if ("categoria".equals(ordenacao)) {
            lista = db.aberturaDao().listarPorCategoria();
        } else {
            lista = db.aberturaDao().listarPorNome();
        }

        adapter.atualizarLista(lista);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_adicionar) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (id == R.id.action_configuracoes) {
            startActivity(new Intent(this, ConfiguracoesActivity.class));
            return true;
        } else if (id == R.id.action_sobre) {
            startActivity(new Intent(this, SobreActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void iniciarMenuContextual(Abertura abertura) {
        aberturaSelecionada = abertura;

        if (actionMode != null) {
            return;
        }

        actionMode = startSupportActionMode(actionModeCallback);
    }

    private final androidx.appcompat.view.ActionMode.Callback actionModeCallback =
            new androidx.appcompat.view.ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(androidx.appcompat.view.ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_contextual, menu);
                    mode.setTitle(getString(R.string.selecionado));
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(androidx.appcompat.view.ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(androidx.appcompat.view.ActionMode mode, MenuItem item) {
                    int id = item.getItemId();

                    if (aberturaSelecionada == null) return false;

                    if (id == R.id.action_editar) {
                        editarAbertura(aberturaSelecionada);
                        mode.finish();
                        return true;
                    } else if (id == R.id.action_excluir) {
                        confirmarExclusao(aberturaSelecionada);
                        mode.finish();
                        return true;
                    }

                    return false;
                }

                @Override
                public void onDestroyActionMode(androidx.appcompat.view.ActionMode mode) {
                    actionMode = null;
                    aberturaSelecionada = null;
                }
            };

    public void editarAbertura(Abertura abertura) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("abertura_id", abertura.getId());
        startActivity(intent);
    }

    public void confirmarExclusao(Abertura abertura) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.confirmar_exclusao))
                .setMessage(getString(R.string.mensagem_exclusao, abertura.getNome()))
                .setPositiveButton(getString(R.string.excluir), (dialog, which) -> {
                    db.aberturaDao().deletar(abertura);
                    carregarAberturas();
                })
                .setNegativeButton(getString(R.string.cancelar), null)
                .show();
    }
}