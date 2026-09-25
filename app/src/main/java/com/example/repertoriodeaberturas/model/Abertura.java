package com.example.repertoriodeaberturas.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "aberturas")
public class Abertura {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;
    private String cor;
    private String categoria;
    private boolean gambito;
    private String observacoes;

    public Abertura(String nome, String cor, String categoria, boolean gambito, String observacoes) {
        this.nome = nome;
        this.cor = cor;
        this.categoria = categoria;
        this.gambito = gambito;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isGambito() {
        return gambito;
    }

    public void setGambito(boolean gambito) {
        this.gambito = gambito;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}