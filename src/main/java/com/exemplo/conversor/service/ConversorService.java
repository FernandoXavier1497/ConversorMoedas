package com.exemplo.conversor.service;

import com.exemplo.conversor.model.Moeda;
import org.springframework.stereotype.Service;

@Service
public class ConversorService {

    public double converter(double valor, Moeda origem, Moeda destino) {
        return (valor / origem.getTaxa()) * destino.getTaxa();
    }
}