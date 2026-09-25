package com.example.repertoriodeaberturas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repertoriodeaberturas.model.Abertura;
import com.example.repertoriodeaberturas.model.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListaAberturasActivity extends AppCompatActivity {

    private RecyclerView recyclerAberturas;
    private Button btnAdicionar;
    private AberturaAdapter adapter;
    private List<Abertura> lista = new ArrayList<>();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aberturas);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerAberturas = findViewById(R.id.recyclerAberturas);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        db = AppDatabase.getInstance(this);

        adapter = new AberturaAdapter(lista);
        recyclerAberturas.setLayoutManager(new LinearLayoutManager(this));
        recyclerAberturas.setAdapter(adapter);

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(ListaAberturasActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, ListaAberturasActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarAberturas();
    }

    private void carregarAberturas() {
        lista = db.aberturaDao().listarTodas();
        adapter.atualizarLista(lista);
    }
}