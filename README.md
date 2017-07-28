# Teste Fluxo E-commerce

Pequeno fluxo de e-commerce baseado na comunicação entre duas APIs (apenas back-end).

- Pré-requisito para execução: Java 8.
- API de compras depende da API de produtos.
- Banco de Dados: H2 Embedded
- Configuração para MySQL pronta no arquivo "application.properties"

### Produtos
- Projeto: api-produtos
- Executavel: api-produtos-1.0.0.jar
- Porta: 8080
- Comando para executar: 
```sh
    java -jar api-produtos-1.0.0.jar
```
- Banco de Dados:
```sh
    Console: http://localhost:8080/h2-console
    JDBC URL: jdbc:h2:file:~/h2/api-produtos
    User name: sa
    Password: 
```
- Funcionalidades:
    - Busca de produtos:
        - URL: http://localhost:8080/produtos/{id}
        - Parâmetros: "id" -> ID do produto.
        - Método: GET
        
    - Cadastro de Produtos: 
        - URL: http://localhost:8080/produtos
        - Método: POST
        - Content-Type: application/json
        - Body:
        ```sh
        {"nome":"Produto Teste","preco":100,"quantidadeEstoque":50}
        ```
        - Retorno: 
        ```sh
         {
            "id": 6,
            "nome": "Produto Teste",
            "preco": 100,
            "quantidadeEstoque": 50
        }
        ```
    
    - Remover estoque:
        - URL: http://localhost:8080/produtos/{id}/removerEstoque/{quantidade}
        - Parâmetros: "id" -> ID do produto; "quantidade" -> Quantidade do produto.
        - Método: PUT
        
### Compras: 
- Projeto: api-compras
- Executavel: api-compras-1.0.0.jar
- Porta: 8081
- Comando para executar: 
```sh
    java -jar api-compras-1.0.0.jar
```
- Banco de Dados:
```sh
    Console: http://localhost:8081/h2-console
    JDBC URL: jdbc:h2:file:~/h2/api-compras
    User name: sa
    Password: 
```
- Funcionalidades:
    - Adicionar no carrinho: 
        - URL: http://localhost:8081/carrinho/{idProduto}/{quantidade}
        - Parâmetros: "idProduto" -> ID do produto; "quantidade" -> Quantidade
        - Método: POST
        - Content-Type: application/json
        - Body:
        ```sh
        {}
        ou
        O JSON do carrinho existente. Ex do JSON abaixo no retorno:
        ```
        - Retorno: 
        ```sh
        {
            "id": 1,
            "produtos": [
                {
                    "idProduto": 1,
                    "quantidade": 5
                }
            ],
            "realizouCheckout": false
        }
        ```
    - Checkout: 
        - URL: http://localhost:8081/carrinho/checkout
        - Método: POST
        - Content-Type: application/json
        - Body:
        ```sh
        {
            "id": 1,
            "produtos": [
                {
                    "idProduto": 1,
                    "quantidade": 5
                }
            ],
            "realizouCheckout": false
        }
        ```
        O checkout removerá os estoques dos produtos do carrinho.
