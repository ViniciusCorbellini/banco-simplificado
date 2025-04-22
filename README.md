# 💰 Banco Simplificado

Um projeto em Java que simula um sistema bancário com funcionalidades básicas.

## Cenário do Problema

O projeto representa uma aplicação de terminal para gerenciar um banco de forma simplificada. Os usuários podem:

- Cadastrar clientes (Pessoa Física e Pessoa Jurídica)
- Criar contas bancárias (Corrente ou Poupança)
- Realizar depósitos, saques e transferências
- Visualizar clientes, contas e movimentações
- Aplicar rendimento mensal às contas poupança

Esse sistema foi desenvolvido para a matéria de Desonvolvimento de Sistemas Orientados a Objeto do Curso Ciência da Computação IFSC-Lages

## Tecnologias utilizadas

- Java 21
- Padrões de Projeto (orientação a objetos, herança, polimorfismo)

## Estrutura do Projeto

- `Main.java`: contém o loop principal e o menu de operações.
- `Banco.java`: gerencia clientes, contas e movimentações.
- `Cliente.java`, `PessoaFisica.java`, `PessoaJuridica.java`: hierarquia de clientes.
- `Conta.java`, `Corrente.java`, `Poupanca.java`: hierarquia de contas.
- `Movimentação.java`: Registro das movimentações.
- Exceções personalizadas: `InputInvalidoException`, `ClienteNotFoundException`, `ContaNotFoundException`, etc.

## Como executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/ViniciusCorbellini/banco-simplificado.git
   cd banco-simplificado
   ```

2. **Compile o projeto:**
   ```bash
   javac Main.java
   ```

3. **Execute o programa:**
   ```bash
   java Main
   ```

>Obs: É necessário que todos os arquivos `.java` estejam no mesmo diretório ou organizados em pacotes corretamente com suas respectivas classes auxiliares.

## Funcionalidades disponíveis

- [x] Adicionar cliente
- [x] Adicionar conta (Corrente ou Poupança)
- [x] Listar clientes, contas ou movimentações
- [x] Sacar valor de uma conta
- [x] Depositar valor em uma conta
- [x] Realizar transferência entre contas
- [x] Aplicar rendimento mensal às contas poupança
- [x] Tratamento de exceções e validações

Feito por Vinícius S. Corbellini