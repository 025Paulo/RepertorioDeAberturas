package com.example.repertoriodeaberturas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ConfiguracoesActivity extends AppCompatActivity {

    private RadioGroup radioOrdenacao;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.titulo_configuracoes));
        }

        prefs = getSharedPreferences(ListaAberturasActivity.PREFS_NAME, MODE_PRIVATE);
        radioOrdenacao = findViewById(R.id.radioOrdenacao);

        String atual = prefs.getString(ListaAberturasActivity.PREF_ORDENACAO, "nome");

        if ("categoria".equals(atual)) {
            ((RadioButton) findViewById(R.id.radioCategoria)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.radioNome)).setChecked(true);
        }

        radioOrdenacao.setOnCheckedChangeListener((group, checkedId) -> {
            String valor = checkedId == R.id.radioCategoria ? "categoria" : "nome";
            prefs.edit().putString(ListaAberturasActivity.PREF_ORDENACAO, valor).apply();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}