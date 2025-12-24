package com.exemplo.conversor.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.exemplo.conversor.config.ApiCambioClient;

@Service
public class CambioService {

    private final ApiCambioClient apiCambioClient;

    public CambioService(ApiCambioClient apiCambioClient) {
        this.apiCambioClient = apiCambioClient;
    }

    
    public Map<String, Double> obterTaxas() {
        return apiCambioClient.obterTaxas();
    }
    
    
    public double converter(String origem, String destino, double valor, Map<String, Double> taxas) {
        if (taxas == null || taxas.isEmpty()) {
            throw new RuntimeException("Não foi possível carregar as taxas de câmbio.");
        }

        double taxaOrigem = taxas.getOrDefault(origem, 0.0);
        double taxaDestino = taxas.getOrDefault(destino, 0.0);

        if (taxaOrigem == 0.0 || taxaDestino == 0.0) {
            throw new IllegalArgumentException("Moeda de origem ou destino não encontrada nas taxas da API.");
        }

        
        double valorEmUSD = valor / taxaOrigem;
        
        
        return valorEmUSD * taxaDestino;
    }
    

    public double converter(String origem, String destino, double valor) {
        
        Map<String, Double> taxas = obterTaxas(); 
        return converter(origem, destino, valor, taxas); 
    }
}