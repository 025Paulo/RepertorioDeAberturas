package com.example.repertoriodeaberturas.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "aberturas")
public class Abertura {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;
    private String cor;
    private String eco;
    private String variante;
    private String dificuldade;
    private String observacoes;

    public Abertura(String nome, String cor, String eco, String variante, String dificuldade, String observacoes) {
        this.nome = nome;
        this.cor = cor;
        this.eco = eco;
        this.variante = variante;
        this.dificuldade = dificuldade;
        this.observacoes = observacoes;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getEco() { return eco; }
    public void setEco(String eco) { this.eco = eco; }

    public String getVariante() { return variante; }
    public void setVariante(String variante) { this.variante = variante; }

    public String getDificuldade() { return dificuldade; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}