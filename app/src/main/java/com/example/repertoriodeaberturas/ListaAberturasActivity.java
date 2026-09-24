package com.example.repertoriodeaberturas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaAberturasActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CADASTRO = 100;

    private ListView listViewAberturas;
    private ArrayList<Abertura> listaAberturas;
    private AberturaAdapter adapter;

    private Button btnAdicionar;
    private Button btnSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aberturas);

        View root = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Repertório de Aberturas");
        }

        listViewAberturas = findViewById(R.id.listViewAberturas);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnSobre = findViewById(R.id.btnSobre);

        listaAberturas = new ArrayList<>();
        adapter = new AberturaAdapter(this, listaAberturas);
        listViewAberturas.setAdapter(adapter);

        listViewAberturas.setOnItemClickListener((parent, view, position, id) -> {
            Abertura aberturaClicada = listaAberturas.get(position);
            Toast.makeText(
                    this,
                    "Clicou em: " + aberturaClicada.getNome() + " - " + aberturaClicada.getCor(),
                    Toast.LENGTH_SHORT
            ).show();
        });

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(ListaAberturasActivity.this, MainActivity.class);
            startActivityForResult(intent, REQUEST_CODE_CADASTRO);
        });

        btnSobre.setOnClickListener(v -> {
            Intent intent = new Intent(ListaAberturasActivity.this, SobreActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CADASTRO && resultCode == Activity.RESULT_OK && data != null) {

            String nome = data.getStringExtra("nome");
            String cor = data.getStringExtra("cor");
            String categoria = data.getStringExtra("categoria");
            String observacao = data.getStringExtra("observacao");
            boolean gambito = data.getBooleanExtra("gambito", false);

            Abertura novaAbertura = new Abertura(nome, cor, categoria, observacao, gambito);

            listaAberturas.add(novaAbertura);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Abertura adicionada: " + nome, Toast.LENGTH_SHORT).show();
        }
    }
}