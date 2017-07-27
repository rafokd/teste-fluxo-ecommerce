# Teste Fluxo E-commerce

Pequeno fluxo de e-commerce baseado na comunicação entre duas APIs (apenas back-end).

- Pré-requisito para execução: Java 8.
- API de compras depende da API de produtos.

### Produtos
- Projeto: api-produtos
- Executavel: api-produtos-1.0.0.jar
- Porta: 8080
- Comando para executar: 
```sh
    java -jar api-produtos-1.0.0.jar
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
        - URL: http://localhost:8080/produtos/removerEstoque/{quantidade}
        - Parâmetros: "quantidade" -> Quantidade do produto.
        - Método: POST
        - Content-Type: application/json
        - Body:
        ```sh
        {"id": 6}
        ```
        
### Compras: 
- Projeto: api-compras
- Executavel: api-compras-1.0.0.jar
- Porta: 8081
- Comando para executar: 
```sh
    java -jar api-compras-1.0.0.jar
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
                    "produto": {
                        "id": 1,
                        "nome": "Produto 1",
                        "preco": 100,
                        "quantidadeEstoque": 30
                    },
                    "quantidade": 5
                }
            ]
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
                    "produto": {
                        "id": 1,
                        "nome": "Produto 1",
                        "preco": 100,
                        "quantidadeEstoque": 30
                    },
                    "quantidade": 5
                }
            ]
        }
        ```
