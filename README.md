# 💰 Banco Simplificado

Um projeto em Java que simula um sistema bancário com funcionalidades básicas.

## Cenário do Problema

O projeto representa uma aplicação de terminal para gerenciar um banco de forma simplificada.

### Funcionalidades disponíveis

- Adicionar cliente (PF ou PJ)
- Adicionar conta (Corrente ou Poupança)
- Listar clientes, contas ou movimentações
- Sacar valor de uma conta
- Depositar valor em uma conta
- Realizar transferência entre contas
- Aplicar rendimento mensal às contas poupança

Esse sistema foi desenvolvido para a matéria de Desenvolvimento de Sistemas Orientados a Objeto da 3ª fase do Curso Ciência da Computação IFSC-Lages
  
## Tecnologias utilizadas

- Java 21
- Padrões de Projeto (orientação a objetos, herança, polimorfismo)

## Estrutura do Projeto

- `Main.java`: contém o loop principal e o menu de operações.
- `Banco.java`: gerencia clientes, contas e movimentações.
- `Cliente.java`, `PessoaFisica.java`, `PessoaJuridica.java`: hierarquia de clientes.
- `Conta.java`, `Corrente.java`, `Poupanca.java`: hierarquia de contas.
- `Movimentacao.java`: Registro das movimentações.
- Exceções personalizadas: `InputInvalidoException`, `ClienteNotFoundException`, `ContaNotFoundException`, etc.

### Diagrama uml do sistema
![Diagrama do sistema](BancoDOO.png)

## Como executar (No terminal)

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/ViniciusCorbellini/banco-simplificado.git
   ```

2. **Mude o diretório para a pasta do projeto:**
   ```bash
   cd banco-simplificado
   ```
   
3. **Crie uma pasta para armazenar os arquivos compilados:**
   ```bash
   mkdir -p bin
   ```

3. **Compile o projeto:**
   ```bash
   javac -d bin $(find bancodoo -name "*.java")
   ```

4. **Execute o programa:**
   ```bash
   java -cp bin bancodoo.Main
   ```
**Se tudo estiver certo, você verá a seguinte tela**
```
===== Banco =====
=== Operacoes ===
1. Adicionar Cliente
2. Adicionar Conta
3. Listar Clientes, Contas ou Movimentacoes de uma conta
4. Sacar
5. Depositar
6. Transferir
7. Aplicar Rendimento
8. Sair
=================
Escolha uma opcao:
```

>Obs: É necessário que todos os arquivos .java estejam organizados conforme a estrutura de pacotes do código-fonte.

Feito por Vinícius S. Corbellini
