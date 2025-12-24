package com.exemplo.conversor.dto;

public class ConversaoResponseDTO {

    private final String moedaOrigem;
    private final String moedaDestino;
    private final double valorOriginal;
    private final double valorConvertido;

    public ConversaoResponseDTO(String moedaOrigem, String moedaDestino,
                                double valorOriginal, double valorConvertido) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valorOriginal = valorOriginal;
        this.valorConvertido = valorConvertido;
    }
    
    public String getMoedaOrigem() { return moedaOrigem; }
    public String getMoedaDestino() { return moedaDestino; }
    public double getValorOriginal() { return valorOriginal; }
    public double getValorConvertido() { return valorConvertido; }
}