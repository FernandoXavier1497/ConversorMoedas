# 💰 Conversor de Moedas (API Externa - Console)

Este é um projeto de console desenvolvido em Java com Spring Boot, utilizando o Apache Maven para gestão de dependências. O objetivo é consumir uma API externa de taxas de câmbio para realizar conversões entre diferentes moedas em tempo real.

## ⚙️ Tecnologias Utilizadas

* **Linguagem:** Java 21+
* **Framework:** Spring Boot
* **Gerenciador de Dependências:** Apache Maven
* **API Externa:** Open Exchange Rates API (ou similar)

## 🚀 Como Executar o Projeto

### Pré-requisitos
* JDK 21 ou superior instalado.
* Apache Maven instalado e configurado nas variáveis de ambiente.

### Execução via Terminal

1.  Navegue até o diretório raiz do projeto (`/ConversorMoedas`).
2.  Execute o comando Maven para compilar e iniciar a aplicação no modo console:

    ```bash
    mvn clean install spring-boot:run
    ```

3.  O menu interativo será exibido no terminal, permitindo que o usuário escolha a conversão e insira o valor.

## 💡 Estrutura de Pastas

A lógica do conversor está concentrada nas seguintes classes:

* **`CambioService.java`**: Contém a regra de negócio para calcular a conversão.
* **`ApiCambioClient.java`**: Responsável por fazer a requisição HTTP à API externa e tratar a resposta JSON.
* **`ConversorConsole.java`**: Classe principal que implementa o menu interativo no terminal.
