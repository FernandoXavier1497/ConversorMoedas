package com.exemplo.conversor.model;

public class Moeda {

    private final String codigo;
    private final Double taxa;

    public Moeda(String codigo, Double taxa) {
        this.codigo = codigo;
        this.taxa = taxa;
    }
    
    public String getCodigo() { return codigo; }
    public Double getTaxa() { return taxa; }
}