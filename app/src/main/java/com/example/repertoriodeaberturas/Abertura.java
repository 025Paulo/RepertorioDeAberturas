package com.example.repertoriodeaberturas;

public class Abertura {
    private String nome;
    private String cor;
    private String categoria;
    private String observacao;
    private boolean gambito;

    public Abertura(String nome, String cor, String categoria, String observacao, boolean gambito) {
        this.nome = nome;
        this.cor = cor;
        this.categoria = categoria;
        this.observacao = observacao;
        this.gambito = gambito;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getObservacao() {
        return observacao;
    }

    public boolean isGambito() {
        return gambito;
    }
}