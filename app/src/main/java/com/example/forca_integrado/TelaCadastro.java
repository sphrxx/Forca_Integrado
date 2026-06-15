package com.example.forca_integrado;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaCadastro extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    public EditText textoDaPalavra, textoDica;
    private Button btnCadastrar, btnListar;
    private RadioGroup grupo;
    private BD bd;
    private String categoriaSelecionada, palavra, categoria, nivel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bd = new BD(TelaCadastro.this);
        categoria = new String();
        textoDaPalavra = findViewById(R.id.textPalavra);
        textoDica = findViewById(R.id.editTextDica);
        btnCadastrar = findViewById(R.id.button2);
        btnCadastrar.setOnClickListener(this);
        btnListar = findViewById(R.id.button3);
        btnListar.setOnClickListener(this);
        grupo = findViewById(R.id.id_grupo);
        grupo.setOnCheckedChangeListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v == btnCadastrar) {
            String texto = textoDaPalavra.getText().toString().toUpperCase();
            String dica = textoDica.getText().toString();
            String nivel;

            // Vamos testar os textos pra ver se tem algum selecionado
            boolean temTextoDigitado = false;
            if(texto.isEmpty()) {
                Toast.makeText(this, "Faltou palavra, DOIDÃO", Toast.LENGTH_SHORT).show();
            }
            else {
                temTextoDigitado = true;
            }

            boolean temDicaDigitado = false;
            if(dica.isEmpty()) {
                Toast.makeText(this, "Tem que dar alguma dica, CARA.", Toast.LENGTH_SHORT).show();
            }
            else {
                temDicaDigitado = true;
            }

            if(texto.length() <= 4) {
                nivel = "FACIL";
            }
            else if(texto.length() <= 7) {
                nivel = "MEDIO";
            }
            else {
                nivel = "DIFICIL";
            }

            RadioButton r1 = findViewById(R.id.radioButton);
            RadioButton r2 = findViewById(R.id.radioButton2);
            RadioButton r3 = findViewById(R.id.radioButton3);
            RadioButton r4 = findViewById(R.id.radioButton4);
            RadioButton r5 = findViewById(R.id.radioButton5);

            boolean temRadioChecado = false;
            if(r1.isChecked()||r1.isChecked()||r2.isChecked()||r3.isChecked()||r4.isChecked()||r5.isChecked()) {
                temRadioChecado = true;
            }
            else {
                Toast.makeText(this, "Qual a categoria, BURRÓIDE?", Toast.LENGTH_SHORT).show();
            }

            if(temTextoDigitado && temDicaDigitado && temRadioChecado) {
                // aqui salva no BD.
                Palavra palavra1 = new Palavra();
                palavra1.setPalavraDigitada(texto);
                palavra1.setDica(dica);
                palavra1.setNivel(nivel);
                palavra1.setCategoria(categoria);
                Toast.makeText(this, "Deu certo, BOA!", Toast.LENGTH_SHORT).show();
                bd.salvarPalavra(palavra1);
            }

        }
        if(v == btnListar) {
            startActivity(new Intent(this, TelaRecycler.class));
        }
    }

    @Override
    public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
        if(group == grupo) {
            RadioButton temporario = findViewById(checkedId);
            categoria = temporario.getText().toString();
            Toast.makeText(TelaCadastro.this, temporario.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}