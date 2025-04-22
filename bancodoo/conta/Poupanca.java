package bancodoo.conta;

import bancodoo.cliente.Cliente;
import bancodoo.exceptions.LimiteExcedidoException;
import bancodoo.exceptions.SaldoInsuficienteException;
import bancodoo.exceptions.ValorInvalidoException;
import bancodoo.movimentacao.Movimentacao;
import java.time.LocalDateTime;

/**
 * Uma conta com funcoes limitadas de saque, deposito e transferencia que possui
 * o proposito de obter um rendimento adicional e herda da classe abstrata Conta
 *
 * Exigencias do trabalho implementadas por essa classe: 
 * - heranca (subclasse) 
 * - polimorfismo (entre metodos herdados sobrescritos)
 *
 * @author Vinicius Corbellini
 */
public class Poupanca extends Conta {

    //Limite inteiro de operacoes que a conta poupanca pode realizar
    private Integer limite_operacoes;

    //Operacoes realizadas
    private Integer operacoes;

    //rendimento mensal representado por um valor decimal (EX: 5% = 0.05)
    private Double rendimento_mensal;

    /**
     * aplica o rendimento mensal ao saldo da poupanca
     */
    public void aplicarRendimento() {
        double valor = rendimento_mensal * saldo;

        super.saldo += valor;
        movimentacoes.add(
                new Movimentacao("Rendimento",
                        valor,
                        LocalDateTime.now(),
                        ("Rendimento de poupanca na conta de " + super.titular.getNome() + " id: " + super.id),
                        super.saldo
                ));
    }

    /**
     * saca uma determinada quantia do saldo da conta poupanca
     *
     * @param valor: valor a ser sacado
     *
     * @throws SaldoInsuficienteException se o valor for maior que o saldo
     *
     * @throws ValorInvalidoException se o valor do saque for invalido
     *
     * @throws LimiteExcedidoException se o numero de operacoes realizadas for
     * => ao limite de operacoes da conta
     */
    @Override
    public void sacar(Double valor) throws SaldoInsuficienteException, ValorInvalidoException, LimiteExcedidoException {
        if (limite_operacoes <= operacoes) {
            throw new LimiteExcedidoException("saque bloqueado!");
        }

        if (valor <= 0) {
            throw new ValorInvalidoException("menor ou igual a zero!");
        }

        if (super.saldo < valor) {
            throw new SaldoInsuficienteException("o valor nao pode ser superior ao saldo!");
        }

        super.saldo -= valor;
        operacoes++;
        super.movimentacoes.add(
                new Movimentacao("Saque",
                        valor,
                        LocalDateTime.now(),
                        ("Saque na conta poupanca de " + super.titular.getNome() + " id: " + super.id),
                        super.saldo
                ));
    }

    /**
     * deposita um determinado valor na conta
     *
     * @param valor valor a ser depositado
     *
     * @throws ValorInvalidoException se o valor do deposito for invalido
     */
    @Override
    public void depositar(Double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("menor ou igual a zero!");
        }

        super.saldo += valor;
        super.movimentacoes.add(
                new Movimentacao("Deposito",
                        valor,
                        LocalDateTime.now(),
                        ("Deposito na conta poupanca de " + super.titular.getNome() + " id: " + super.id),
                        super.saldo
                ));
    }

    /**
     * transferencia de um determinado valor para uma conta destinataria 
     *
     * @param c conta do destinatario
     *
     * @param valor valor a ser transferido
     *
     * @throws SaldoInsuficienteException se o valor for maior que o saldo
     *
     * @throws ValorInvalidoException se o valor da transacao for invalido
     * 
     * @throws LimiteExcedidoException se o numero de operacoes realizadas for
     * => ao limite de operacoes da conta
     */
    @Override
    public void transferir(Conta c, Double valor) throws SaldoInsuficienteException, ValorInvalidoException, LimiteExcedidoException {
        if (limite_operacoes <= operacoes) {
            throw new LimiteExcedidoException("transferencia bloqueada!");
        }

        if (valor <= 0) {
            throw new ValorInvalidoException("menor ou igual a zero!");
        }

        if (super.saldo < valor) {
            throw new SaldoInsuficienteException("o valor nao pode ser superior ao saldo!");
        }

        super.saldo -= valor;
        c.saldo += valor;
        operacoes++;

        c.movimentacoes.add(
                new Movimentacao("Recebimento de Transferencia",
                        valor,
                        LocalDateTime.now(),
                        ("Transferencia da conta poupanca de id:" + super.id + " para id:" + c.id),
                        c.saldo
                )
        );

        super.movimentacoes.add(
                new Movimentacao("Transferencia",
                        valor,
                        LocalDateTime.now(),
                        ("Transferencia da conta poupanca de id:" + super.id + " para id:" + c.id),
                        super.saldo
                ));
    }

    //===== Construtor, toString, getters e setters
    public Poupanca(Integer limite_operacoes, Double rendimento_mensal, Cliente titular, Double saldo, Long id) {
        super(titular, saldo, id);
        this.limite_operacoes = limite_operacoes;
        this.operacoes = 0;
        this.rendimento_mensal = rendimento_mensal;
    }

    public Poupanca(Integer limite_operacoes, Double rendimento_mensal, Cliente titular, Double saldo) {
        super(titular, saldo);
        this.limite_operacoes = limite_operacoes;
        this.operacoes = 0;
        this.rendimento_mensal = rendimento_mensal;
    }

    public Poupanca(Integer limite_operacoes, Double rendimento_mensal, Double saldo) {
        super(null, saldo);
        this.limite_operacoes = limite_operacoes;
        this.operacoes = 0;
        this.rendimento_mensal = rendimento_mensal;
    }

    @Override
    public String toString() {
        return "Poupanca{"
                + "id: " + id
                + ", titular: " + titular.getNome()
                + ", saldo: " + saldo
                + ", movimentacoes: " + movimentacoes
                + ", limite_operacoes: " + limite_operacoes
                + ", operacoes: " + operacoes
                + '}';
    }

    public Integer getLimite_operacoes() {
        return limite_operacoes;
    }

    public void setLimite_operacoes(Integer limite_operacoes) {
        this.limite_operacoes = limite_operacoes;
    }

    public Integer getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(int operacoes) {
        this.operacoes = operacoes;
    }

    public Double getRendimento_mensal() {
        return rendimento_mensal;
    }

    public void setRendimento_mensal(Double rendimento_mensal) {
        this.rendimento_mensal = rendimento_mensal;
    }
}
