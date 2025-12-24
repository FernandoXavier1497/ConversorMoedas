package com.exemplo.conversor.config;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiCambioClient {

    private final RestTemplate restTemplate;
    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";

    public ApiCambioClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


public Map<String, Double> obterTaxas() {
    
    Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);
    
    if (response != null && response.containsKey("rates")) {
        
        Map<String, Object> ratesMap = (Map<String, Object>) response.get("rates");
        
        
        Map<String, Double> taxasCorrigidas = new java.util.HashMap<>(); 

        
        for (java.util.Map.Entry<String, Object> entry : ratesMap.entrySet()) {
            Object valor = entry.getValue();
            
            
            if (valor instanceof Integer) {
                
                taxasCorrigidas.put(entry.getKey(), ((Integer) valor).doubleValue());
            } else if (valor instanceof Double) {
                
                taxasCorrigidas.put(entry.getKey(), (Double) valor);
            } else {
                
                System.err.println("Aviso: Valor da taxa para " + entry.getKey() + " não é um número e foi ignorado.");
            }
        }
        
        return taxasCorrigidas;
    } else {
         throw new RuntimeException("Falha ao obter taxas de câmbio da API externa. Chave 'rates' não encontrada.");
    }
}

}