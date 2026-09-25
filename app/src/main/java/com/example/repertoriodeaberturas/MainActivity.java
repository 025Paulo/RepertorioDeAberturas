package com.example.repertoriodeaberturas;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.repertoriodeaberturas.model.Abertura;
import com.example.repertoriodeaberturas.model.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome, edtObservacoes;
    private RadioGroup radioGroupCor;
    private CheckBox checkGambito;
    private Spinner spinnerCategoria;
    private Button btnSalvar, btnLimpar;
    private AppDatabase db;
    private Abertura aberturaEditando = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.titulo_cadastro));
        }

        edtNome = findViewById(R.id.edtNome);
        edtObservacoes = findViewById(R.id.edtObservacoes);
        radioGroupCor = findViewById(R.id.radioGroupCor);
        checkGambito = findViewById(R.id.checkGambito);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);

        db = AppDatabase.getInstance(this);

        String[] categorias = {
                getString(R.string.cat_open),
                getString(R.string.cat_semi_open),
                getString(R.string.cat_closed),
                getString(R.string.cat_indian_defense),
                getString(R.string.cat_other)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categorias
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        if (getIntent().hasExtra("abertura_id")) {
            int id = getIntent().getIntExtra("abertura_id", -1);
            aberturaEditando = db.aberturaDao().buscarPorId(id);

            if (aberturaEditando != null) {
                edtNome.setText(aberturaEditando.getNome());
                edtObservacoes.setText(aberturaEditando.getObservacoes());
                checkGambito.setChecked(aberturaEditando.isGambito());

                for (int i = 0; i < radioGroupCor.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) radioGroupCor.getChildAt(i);
                    if (rb.getText().toString().equals(aberturaEditando.getCor())) {
                        rb.setChecked(true);
                        break;
                    }
                }

                for (int i = 0; i < categorias.length; i++) {
                    if (categorias[i].equals(aberturaEditando.getCategoria())) {
                        spinnerCategoria.setSelection(i);
                        break;
                    }
                }

                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(getString(R.string.titulo_editar));
                }
            }
        }

        btnLimpar.setOnClickListener(v -> {
            edtNome.setText("");
            edtObservacoes.setText("");
            radioGroupCor.clearCheck();
            checkGambito.setChecked(false);
            spinnerCategoria.setSelection(0);
            Toast.makeText(this, getString(R.string.formulario_limpo), Toast.LENGTH_SHORT).show();
        });

        btnSalvar.setOnClickListener(v -> salvarAbertura());
    }

    private void salvarAbertura() {
        String nome = edtNome.getText().toString().trim();
        String observacoes = edtObservacoes.getText().toString().trim();
        boolean gambito = checkGambito.isChecked();
        String categoria = spinnerCategoria.getSelectedItem().toString();

        if (nome.isEmpty()) {
            Toast.makeText(this, getString(R.string.erro_nome), Toast.LENGTH_SHORT).show();
            edtNome.requestFocus();
            return;
        }

        int radioId = radioGroupCor.getCheckedRadioButtonId();
        if (radioId == -1) {
            Toast.makeText(this, getString(R.string.erro_cor), Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton radioSelecionado = findViewById(radioId);
        String cor = radioSelecionado.getText().toString();

        if (aberturaEditando != null) {
            aberturaEditando.setNome(nome);
            aberturaEditando.setCor(cor);
            aberturaEditando.setCategoria(categoria);
            aberturaEditando.setGambito(gambito);
            aberturaEditando.setObservacoes(observacoes);

            db.aberturaDao().atualizar(aberturaEditando);
            Toast.makeText(this, getString(R.string.abertura_atualizada), Toast.LENGTH_SHORT).show();
        } else {
            Abertura abertura = new Abertura(nome, cor, categoria, gambito, observacoes);
            db.aberturaDao().inserir(abertura);
            Toast.makeText(this, getString(R.string.abertura_salva), Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}