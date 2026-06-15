package com.example.forca_integrado;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class TelaJogo extends AppCompatActivity implements View.OnClickListener {
    private ImageView imagem;
    private ArrayList<Integer> listaImagens, listaIdsButtons;
    private ArrayList<Palavra> listaObjetos, listaCategorias, listaFacil, listaMedia, listaDificil;
    private int indiceListaImagens, contaAcerto, contaErro, indiceLista, contadorVitoria;
    private TextView texto, txAcerto, txErro;
    private String palavra, nivelAtual;
    private char[] estado;
    private Button b1, btnDica;
    private BD databasePalavra;
    private SharedPreferences shared;

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
        txAcerto = findViewById(R.id.numAcerto);
        txErro = findViewById(R.id.numErro);

        contaAcerto = 0;
        contaErro = 0;
        contadorVitoria = 0;
        indiceLista = 0;




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

        btnDica = findViewById(R.id.buttonDica);
        btnDica.setOnClickListener(this);

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
        atualizaNivel();

    }
    //a
    public void inicializaJogo(String nivel) {
        inicializaBanco();

        String temp;
        listaFacil = new ArrayList<Palavra>();
        listaMedia = new ArrayList<Palavra>();
        listaDificil = new ArrayList<Palavra>();
        for(int i = 0; i < listaObjetos.size(); i++) {
            temp = listaObjetos.get(i).getNivel();
            if(temp.compareToIgnoreCase("FACIL") == 0) {
                listaFacil.add(listaObjetos.get(i));
            }
            else if(temp.compareToIgnoreCase("MEDIO") == 0) {
                listaMedia.add(listaObjetos.get(i));
            }
            else if(temp.compareToIgnoreCase("DIFICIL") == 0) {
                listaDificil.add(listaObjetos.get(i));
            }
        }

        btnDica.setVisibility(View.INVISIBLE);

        imagem.setImageResource(R.drawable.forca_0_9);
        indiceListaImagens = 0;

        palavra = new String();
        if(nivel == "FACIL") {
            palavra = listaFacil.get(indiceLista).getPalavraDigitada();
        }
        else if(nivel == "MEDIO") {
            palavra = listaMedia.get(indiceLista).getPalavraDigitada();
        }
        else if (nivel == "DIFICIL") {
            palavra = listaDificil.get(indiceLista).getPalavraDigitada();
        }

        estado = new char[palavra.length()];

        for(int i = 0; i < estado.length; i++) {
            estado[i] = '_';
        }
        contaErro = 0;
        contaAcerto = 0;
        txAcerto.setText(Integer.toString(contaAcerto));
        txErro.setText(Integer.toString(contaErro)+"/"+Integer.toString(listaImagens.size()));
        atualizaTexto();

        for(int j = 0; j < listaIdsButtons.size(); j++) {
            Button b = findViewById(listaIdsButtons.get(j));
            b.setEnabled(true);
        }

        Toast.makeText(this, "A categoria da palavra é: " + listaObjetos.get(indiceLista).getCategoria(), Toast.LENGTH_SHORT).show();
    }

    public void inicializaBanco() {
        listaObjetos = new ArrayList<Palavra>();
        databasePalavra = new BD(this);
        listaObjetos = databasePalavra.listarPalavras();
        palavra = listaObjetos.get(indiceLista).getPalavraDigitada();
        Collections.shuffle(listaObjetos);

        indiceLista++;

    }

    public void atualizaTexto(){
        String temporaria = new String();
        temporaria = "";

        for(int i = 0; i < estado.length; i++) {
            temporaria += estado[i] + " ";
        }
        texto.setText(temporaria);
    }

    public void atualizaNivel() {
        contadorVitoria++;
        nivelAtual = new String();

        if(contadorVitoria <= 3) {
            nivelAtual = "FACIL";
        }
        else if(contadorVitoria <= 6) {
            nivelAtual = "MEDIO";
        }
        else {
            nivelAtual = "DIFICIL";
        }

        inicializaJogo(nivelAtual);
    }

    public void atualizaForca() {
        imagem.setImageResource(listaImagens.get(indiceListaImagens));
        indiceListaImagens++;
    }

    public void verificaLetra(char c) {
        boolean status = false;
        Toast.makeText(this, palavra+ " "+c, Toast.LENGTH_SHORT).show();
        for(int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == c) {
                status = true;
                estado[i] = c;
            }
        }

        if(!status) {
            atualizaForca();
            contaErro++;
            txErro.setText(Integer.toString(contaErro)+"/"+Integer.toString(listaImagens.size()));
        }
        else {
            atualizaTexto();
            contaAcerto++;
            txAcerto.setText(Integer.toString(contaAcerto));
        }

        if(contaErro >= 2) {
            btnDica.setVisibility(View.VISIBLE);
        }

        checaSeTerminou();
    }
    public void checaSeTerminou() {
        boolean verifica = false;
        for(int i = 0; i < estado.length; i++) {
            if(estado[i] == '_') {
                // se der true, é porque ainda tem jogo
                verifica = true;
            }
        }
        // senão, é porque completou o jogo
        if(!verifica) {
            AlertDialog.Builder caixa = new AlertDialog.Builder(this);
            caixa.setTitle("Você venceu!!!");
            caixa.setMessage("Você deseja jogar novamente?");
            caixa.setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    atualizaNivel();
                }
            });
            caixa.show();


        }
        if(contaErro >= listaImagens.size()) {
            AlertDialog.Builder caixa = new AlertDialog.Builder(this);
            caixa.setTitle("Você perdeu, BOBÃO.");
            caixa.setMessage("Deseja jogar novamente?");
            caixa.setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    atualizaNivel();
                }
            });
            caixa.show();
        }
    }

    @Override
    public void onClick(View v) {

        if(v == btnDica) {
            Toast.makeText(this, "Dica: " + listaObjetos.get(indiceLista).getDica(), Toast.LENGTH_SHORT).show();
        }else {
            Button b = (Button) v;
            verificaLetra(b.getText().toString().charAt(0));
            b.setEnabled(false);

        }
    }
}