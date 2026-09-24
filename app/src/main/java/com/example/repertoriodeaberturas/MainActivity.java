package com.example.repertoriodeaberturas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome, edtObservacoes;
    private RadioGroup radioGroupCor;
    private CheckBox checkGambito;
    private Spinner spinnerCategoria;
    private Button btnSalvar, btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View root = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        edtNome = findViewById(R.id.edtNome);
        edtObservacoes = findViewById(R.id.edtObservacoes);
        radioGroupCor = findViewById(R.id.radioGroupCor);
        checkGambito = findViewById(R.id.checkGambito);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);

        String[] categorias = {
                "Aberta",
                "Semiaberta",
                "Fechada",
                "Defesa Índia",
                "Outra"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categorias
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNome.setText("");
                edtObservacoes.setText("");
                radioGroupCor.clearCheck();
                checkGambito.setChecked(false);
                spinnerCategoria.setSelection(0);

                Toast.makeText(MainActivity.this, "Formulário limpo com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString().trim();
                String observacoes = edtObservacoes.getText().toString().trim();
                boolean gambito = checkGambito.isChecked();
                String categoria = spinnerCategoria.getSelectedItem().toString();

                if (nome.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Erro: informe o nome da abertura.", Toast.LENGTH_SHORT).show();
                    edtNome.requestFocus();
                    return;
                }

                int radioSelecionadoId = radioGroupCor.getCheckedRadioButtonId();
                if (radioSelecionadoId == -1) {
                    Toast.makeText(MainActivity.this, "Erro: selecione a cor do repertório.", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton radioSelecionado = findViewById(radioSelecionadoId);
                String cor = radioSelecionado.getText().toString();

                android.content.Intent resultado = new android.content.Intent();
                resultado.putExtra("nome", nome);
                resultado.putExtra("cor", cor);
                resultado.putExtra("categoria", categoria);
                resultado.putExtra("observacao", observacoes);
                resultado.putExtra("gambito", gambito);

                setResult(RESULT_OK, resultado);
                finish();
            }
        });
    }
}