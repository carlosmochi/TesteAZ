# Nome do Projeto

Teste_AZ - Gerenciamento de Tarefas Simples

## Descrição

Este projeto é um criado como um teste para o processo de seleção da "AZ - Tecnologia em Gestão". O raiz possuem dias pastas:
   -api: Possui o Backend criado com SpringBoot e Maven e possui 5 Endpoints para conectar o frontend ao Banco de Dados em PostGres

## Estrutura do Projeto

A estrutura de diretórios do projeto é organizada da seguinte forma:

```
/api
  └── [documentação da API e especificações de endpoints]
/app
  └── [código-fonte e configuração da aplicação]
README.md
```

- **/app**: Contém o código-fonte principal da aplicação. Aqui você encontrará os controladores, serviços, repositórios e outras classes relacionadas à lógica do negócio.
- **/api**: Contém a documentação da API e as especificações dos endpoints. Esta pasta é onde você pode encontrar informações detalhadas sobre como interagir com a aplicação através da API REST.
- **README.md**: Este arquivo. Contém informações básicas sobre o projeto, como configurar e executar a aplicação.

## Como Configurar

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/usuario/repositorio.git
   ```

2. **Navegue até o diretório do projeto:**

   ```bash
   cd repositorio
   ```

3. **Compile e execute a aplicação com Maven:**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

## Documentação da API

A documentação da API está localizada na pasta `/api`. Nela, você encontrará informações sobre os endpoints disponíveis, parâmetros necessários e exemplos de uso.
