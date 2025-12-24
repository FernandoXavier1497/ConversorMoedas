package com.exemplo.conversor.controller;

import com.exemplo.conversor.dto.ConversaoResponseDTO;
import com.exemplo.conversor.model.Moeda;
import com.exemplo.conversor.service.CambioService;
import com.exemplo.conversor.service.ConversorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conversor")
public class ConversorController {

    private final CambioService cambioService;
    private final ConversorService conversorService;

    public ConversorController(CambioService cambioService, ConversorService conversorService) {
        this.cambioService = cambioService;
        this.conversorService = conversorService;
    }

    @GetMapping("/converter")
    public ConversaoResponseDTO converter(
            @RequestParam String origem,
            @RequestParam String destino,
            @RequestParam double valor) {

        String codOrigem = origem.toUpperCase();
        String codDestino = destino.toUpperCase();

        Moeda moedaOrigem = cambioService.buscarMoeda(codOrigem);
        Moeda moedaDestino = cambioService.buscarMoeda(codDestino);

        double resultado = conversorService.converter(valor, moedaOrigem, moedaDestino);

        return new ConversaoResponseDTO(
                codOrigem,
                codDestino,
                valor,
                resultado
        );
    }
}