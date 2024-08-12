# Nome do Projeto

Teste_AZ - Gerenciamento de Tarefas Simples

## Descrição

Este projeto é um criado como um teste para o processo de seleção da "AZ - Tecnologia em Gestão". É uma aplicação que realiza um gerenciamento simples de Tarefas. Na página inicial são mostradas todas as tarefas e um botão que, ao ser clickado, mostra um formulário para criar uma nova Tarefa. Cada tarefa está separada em um Card com nome, descrição, status (completo ou incompleto) e três botões para: Alterar a Tarefa, Deletar a tarefa e marcar a tarefa como completa.

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

   A aplicação estará disponível em [http://localhost:5173](http://localhost:5173).

## Documentação da API

A documentação da API está localizada na pasta `/api`. Nela, você encontrará informações sobre os endpoints disponíveis, parâmetros necessários e exemplos de uso.
