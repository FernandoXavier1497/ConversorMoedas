package com.exemplo.conversor;

import com.exemplo.conversor.service.CambioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import java.util.Map;


@Component
public class ConversorConsole implements CommandLineRunner {

    
    private final CambioService cambioService;

    public ConversorConsole(CambioService cambioService) {
        this.cambioService = cambioService;
    }

    
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("\n*************************************************");
        System.out.println("Seja bem-vindo(a) ao Conversor de Moeda! [API Mode]");
        System.out.println("*************************************************\n");
        
        
        Map<String, Double> taxas = cambioService.obterTaxas();

        do {
            exibirMenu();
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                if (opcao == 7) {
                    System.out.println("Saindo do conversor. Até logo!");
                    break;
                }

                try {
                    processarOpcao(opcao, scanner, taxas);
                } catch (IllegalArgumentException e) {
                    System.err.println("\nERRO: " + e.getMessage());
                } catch (RuntimeException e) {
                     System.err.println("\nERRO AO CONECTAR À API: " + e.getMessage());
                }
            } else {
                System.out.println("\nEscolha inválida. Por favor, digite um número de 1 a 7.");
                scanner.nextLine(); 
            }
            
        } while (true);
    }

    private void exibirMenu() {
        System.out.println("\nEscolha uma opção de conversão:");
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileiro");
        System.out.println("4) Real brasileiro => Dólar");
        System.out.println("5) Dólar => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Sair");
        System.out.print("Escolha uma válida: ");
    }

    private void processarOpcao(int opcao, Scanner scanner, Map<String, Double> taxas) {
        String origem = "", destino = "";

        switch (opcao) {
            case 1: origem = "USD"; destino = "ARS"; break;
            case 2: origem = "ARS"; destino = "USD"; break;
            case 3: origem = "USD"; destino = "BRL"; break;
            case 4: origem = "BRL"; destino = "USD"; break;
            case 5: origem = "USD"; destino = "COP"; break;
            case 6: origem = "COP"; destino = "USD"; break;
            default:
                if (opcao != 7) {
                    System.out.println("Opção inválida.");
                }
                return;
        }

        System.out.print("\nDigite o valor que deseja converter: ");
        if (!scanner.hasNextDouble()) {
            scanner.nextLine(); 
            throw new IllegalArgumentException("Valor inválido. Por favor, digite um número.");
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); 

        
        double valorConvertido = cambioService.converter(origem, destino, valor);

        System.out.println("\n=======================================================");
        System.out.printf("Valor %.2f [%s] corresponde ao valor final de => %.2f [%s]\n",
                          valor, origem, valorConvertido, destino);
        System.out.println("=======================================================");
    }
}