package bancodoo;

import bancodoo.banco.Banco;
import bancodoo.cliente.Cliente;
import bancodoo.cliente.PessoaFisica;
import bancodoo.cliente.PessoaJuridica;
import bancodoo.conta.Conta;
import bancodoo.conta.Corrente;
import bancodoo.conta.Poupanca;
import bancodoo.exceptions.ClienteNotFoundException;
import bancodoo.exceptions.ContaNotFoundException;
import bancodoo.exceptions.InputInvalidoException;
import bancodoo.exceptions.LimiteExcedidoException;
import bancodoo.exceptions.SaldoInsuficienteException;
import bancodoo.exceptions.ValorInvalidoException;
import java.util.Scanner;

/**
 * Classe que representa a interface do programa com o usuario
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Tratamento de excecoes (bloco try-catch)
 * 
 * @author Vinicius Corbellini
 */
public class Main {

    //Scanner para receber e ler os inputs do usuario
    static Scanner input = new Scanner(System.in);
    
    //Representacao do banco
    static Banco banco = new Banco();

    /**
     * Loop principal do programa
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.print("===== Banco =====");
        boolean running = true;

        while (running) {
            try {
                int opcao = menuInicial();

                switch (opcao) {
                    case 1 -> {
                        addCliente();
                        System.out.println("Cliente adicionado com sucesso >>>");
                    }
                    case 2 -> {
                        addConta();
                        System.out.println("Conta adicionada com sucesso >>>");
                    }
                    case 3 -> {
                        listagem();
                        System.out.println("Listagem finalizada >>>");
                    }
                    case 4 -> {
                        saque();
                        System.out.println("Saque realizado com sucesso >>>");
                    }
                    case 5 -> {
                        deposito();
                        System.out.println("Deposito realizado com sucesso >>>");
                    }
                    case 6 -> {
                        transferencia();
                        System.out.println("Transferencia realizada com sucesso >>>");
                    }
                    case 7 -> {
                        banco.aplicarRendimento();
                        System.out.println("Rendimento aplicado >>>");
                    }
                    case 8 -> {
                        System.out.println("Encerrando...");
                        running = false;
                    }
                    default -> throw new InputInvalidoException("Opcao invalida >>>");
                }
            } catch (Exception e) {
                System.out.println("Erro -> " + e.getMessage());
            }
            System.out.println("=================");
        }
    }
    
    private static int menuInicial() {
        System.out.println("\n=== Operacoes ===");
        System.out.println("1. Adicionar Cliente");
        System.out.println("2. Adicionar Conta");
        System.out.println("3. Listar Clientes, Contas ou Movimentacoes de uma conta");
        System.out.println("4. Sacar");
        System.out.println("5. Depositar");
        System.out.println("6. Transferir");
        System.out.println("7. Aplicar Rendimento");
        System.out.println("8. Sair");
        System.out.println("=================");
        System.out.print("Escolha uma opcao: ");
        return Integer.parseInt(input.nextLine());
    }

    private static void addCliente() throws InputInvalidoException {
        System.out.println("\n=================");
        System.out.println("Adicionando Cliente >>>");
        Cliente c = receberInfoCliente();
        banco.adicionarCliente(c);
    }

    private static Cliente receberInfoCliente() throws InputInvalidoException {
        System.out.print("Tipo de cliente (1 - Pessoa Fisica | 2 - Pessoa Juridica): ");
        int tipo = Integer.parseInt(input.nextLine());

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("email: ");
        String email = input.nextLine();

        switch (tipo) {
            case 1 -> {
                System.out.print("Documento (CPF): ");
                String cpf = input.nextLine();

                System.out.print("Telefone: ");
                String telefone = input.nextLine();

                return new PessoaFisica(cpf, telefone, nome, email);

            }
            case 2 -> {
                System.out.print("Documento (CNPJ): ");
                String cnpj = input.nextLine();

                System.out.print("Razao Social: ");
                String razaoSocial = input.nextLine();

                return new PessoaJuridica(cnpj, razaoSocial, nome, email);
            }
            default -> {
                throw new InputInvalidoException("tipo invalido de cliente!");
            }
        }
    }

    private static void addConta() throws InputInvalidoException, ClienteNotFoundException {
        System.out.println("=================");
        System.out.println("Adicionando conta >>>");
        System.out.print("Documento do cliente: ");
        String doc = input.nextLine();
        Conta c = receberInfoConta();
        banco.adicionarConta(doc, c);
    }

    private static Conta receberInfoConta() throws InputInvalidoException {
        System.out.print("Tipo de conta (1 - Conta Corrente | 2 - Conta Poupanca): ");
        int tipo = Integer.parseInt(input.nextLine());

        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(input.nextLine());

        switch (tipo) {
            case 1 -> {
                System.out.print("Limite da conta corrente: ");
                double limite = Double.parseDouble(input.nextLine());

                return new Corrente(limite, saldo);
            }
            case 2 -> {
                System.out.print("Limite de operacoes mensais: ");
                int limite = Integer.parseInt(input.nextLine());

                System.out.print("Rendimento mensal (em casas decimais): ");
                double rendimento = Double.parseDouble(input.nextLine());

                return new Poupanca(limite, rendimento, saldo);
            }
            default ->
                throw new InputInvalidoException("tipo invalido de conta!");
        }
    }

    private static void listagem() throws InputInvalidoException, ClienteNotFoundException, ContaNotFoundException {
        System.out.println("=================");
        System.out.print("Listagem (1 para clientes | 2 para contas de um cliente | 3 para movimentacoes): ");
        int listagem = Integer.parseInt(input.nextLine());

        switch (listagem) {
            case 1 -> {
                System.out.println("Listando clientes>>> ");
                banco.listarClientes();
            }
            case 2 -> {
                System.out.print("Insira o documento do cliente: ");
                String doc = input.nextLine();

                System.out.println("Listando contas >>> ");
                banco.listarContas(doc);
            }
            case 3 -> {
                System.out.print("Insira o documento do cliente: ");
                String doc = input.nextLine();
                
                System.out.print("Insira o id da conta: ");
                long id = Long.parseLong(input.nextLine());

                System.out.println("Listando Movimentacoes >>> ");
                banco.listarMovimentacoes(doc, id);
            }
            default ->
                throw new InputInvalidoException("Tipo invalido de listagem!");
        }
    }

    private static void saque()
            throws ClienteNotFoundException, ContaNotFoundException,
            SaldoInsuficienteException, ValorInvalidoException,
            LimiteExcedidoException {
        System.out.println("=================");
        System.out.println("Saque >>>");

        System.out.print("Documento: ");
        String doc = input.nextLine();

        System.out.print("ID da conta: ");
        long id = Long.parseLong(input.nextLine());

        System.out.print("Valor do saque: ");
        double valor = Double.parseDouble(input.nextLine());

        banco.sacar(doc, id, valor);
    }

    private static void deposito()
            throws ClienteNotFoundException, ContaNotFoundException,
            SaldoInsuficienteException, ValorInvalidoException,
            LimiteExcedidoException {
        System.out.println("===============");
        System.out.println("Deposito >>>");
        System.out.print("Documento: ");
        String doc = input.nextLine();

        System.out.print("Id da conta: ");
        long id = Long.parseLong(input.nextLine());

        System.out.print("Valor do deposito: ");
        double valor = Double.parseDouble(input.nextLine());

        banco.depositar(doc, id, valor);
    }

    private static void transferencia()
            throws ClienteNotFoundException, ContaNotFoundException,
            SaldoInsuficienteException, ValorInvalidoException,
            LimiteExcedidoException {
        System.out.println("===============");
        System.out.println("Transferencia >>>");
        System.out.print("Documento do remetente: ");
        String doc_rem = input.nextLine();

        System.out.print("Id da conta do remetente: ");
        long id_rem = Long.parseLong(input.nextLine());

        System.out.print("Documento do destinatario: ");
        String doc_dest = input.nextLine();

        System.out.print("Id da conta do destinatario: ");
        long id_dest = Long.parseLong(input.nextLine());

        System.out.print("Valor: ");
        double valor = Double.parseDouble(input.nextLine());

        banco.transferir(doc_rem, doc_dest, id_rem, id_dest, valor);
    }
}
