package com.example.forca_integrado;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class TelaJogo extends AppCompatActivity implements View.OnClickListener {
    private ImageView imagem;
    private ArrayList<Integer> listaImagens, listaIdsButtons;
    private ArrayList<String> listaPalavras;
    private int indiceListaImagens;
    private TextView texto;
    private String palavra;
    private char[] estado;
    private Button b1;

    // ta olhando uq curioso 🤨




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_jogo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imagem = findViewById(R.id.imageView2);
        indiceListaImagens = -1;
        listaImagens = new ArrayList<Integer>();
        listaImagens.add(R.drawable.forca_1_9);
        listaImagens.add(R.drawable.forca_2_9);
        listaImagens.add(R.drawable.forca_3_9);
        listaImagens.add(R.drawable.forca_4_9);
        listaImagens.add(R.drawable.forca_5_9);
        listaImagens.add(R.drawable.forca_6_9);
        listaImagens.add(R.drawable.forca_7_9);
        // imagem 8 é igual à 7
        listaImagens.add(R.drawable.forca_9_9);
        listaImagens.add(R.drawable.forca_10_9);
        listaImagens.add(R.drawable.forca_11_9);

        b1 = findViewById(R.id.id2);
        b1.setOnClickListener(this);

        listaPalavras = new ArrayList<String>();
        listaPalavras.add("PARAPROPALAEHOPLOPHORUS");
        listaPalavras.add("VIDEOGAME");
        listaPalavras.add("MATERNIDADE");
        listaPalavras.add("CARIDADE");
        listaPalavras.add("CRUZ");
        listaPalavras.add("ROBO");
        listaPalavras.add("COMPANHEIRO");
        listaPalavras.add("PRUDENCIA");
        listaPalavras.add("COSMOS");
        listaPalavras.add("PENUMBRA");

        texto = findViewById(R.id.textView3);

        listaIdsButtons = new ArrayList<Integer>();
        listaIdsButtons.add(R.id.id2);
        listaIdsButtons.add(R.id.id3);
        listaIdsButtons.add(R.id.id4);
        listaIdsButtons.add(R.id.id5);
        listaIdsButtons.add(R.id.id6);
        listaIdsButtons.add(R.id.id7);
        listaIdsButtons.add(R.id.id8);
        listaIdsButtons.add(R.id.id9);
        listaIdsButtons.add(R.id.id10);
        listaIdsButtons.add(R.id.id11);
        listaIdsButtons.add(R.id.id12);
        listaIdsButtons.add(R.id.id13);
        listaIdsButtons.add(R.id.id14);
        listaIdsButtons.add(R.id.id15);
        listaIdsButtons.add(R.id.id16);
        listaIdsButtons.add(R.id.id17);
        listaIdsButtons.add(R.id.id18);
        listaIdsButtons.add(R.id.id19);
        listaIdsButtons.add(R.id.id20);
        listaIdsButtons.add(R.id.id21);
        listaIdsButtons.add(R.id.id22);
        listaIdsButtons.add(R.id.id23);
        listaIdsButtons.add(R.id.id24);
        listaIdsButtons.add(R.id.id25);
        listaIdsButtons.add(R.id.id26);
        listaIdsButtons.add(R.id.id27);

        for(int j = 0; j < listaIdsButtons.size(); j++) {
            Button b = findViewById(listaIdsButtons.get(j));
            b.setOnClickListener(this);
        }
    }
    public void inicializaJogo() {
        imagem.setImageResource(R.drawable.forca_0_9);
        palavra = sorteiaPalavra();
        estado = new char[palavra.length()];

        for(int i = 0; i < estado.length; i++) {
            estado[i] = '_';
        }
        atualizaTexto();
    }
    public String sorteiaPalavra() {
        String palavra = new String();
        Collections.shuffle(listaPalavras);
        palavra = listaPalavras.get(0);

        return palavra;
    }

    public void atualizaTexto(){
        String temporaria = new String();
        temporaria = "";

        for(int i = 0; i < estado.length; i++) {
            temporaria += estado[i] + " ";
        }
        texto.setText(temporaria);
    }

    public void atualizaForca() {
        indiceListaImagens++;
        imagem.setImageResource(listaImagens.get(indiceListaImagens));
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        texto.setText(b.getText().toString());
    }
}