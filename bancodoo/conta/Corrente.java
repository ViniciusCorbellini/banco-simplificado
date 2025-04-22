package bancodoo.conta;

import bancodoo.cliente.Cliente;
import bancodoo.exceptions.SaldoInsuficienteException;
import bancodoo.exceptions.ValorInvalidoException;
import bancodoo.movimentacao.Movimentacao;
import java.time.LocalDateTime;

/**
 * Uma conta corrente com operacoes ilimitadas de saque, deposito e
 * transferencia que herda a classe Abstrata Conta
 *
 * Exigencias do trabalho implementadas por essa classe: 
 * - heranca (subclasse) 
 * - polimorfismo (entre metodos herdados sobrescritos)
 *
 * @author Vinicius Corbellini
 */
public class Corrente extends Conta {

    /**
     * Atributo que define o valor limite que pode ser retirado da conta em um
     * conjunto de transacoes
     *
     * Obs: a manipulacao desse atributo é responsabilidade da Classse Banco
     */
    private Double limite;

    /**
     * saca uma determinada quantia do saldo da conta corrente
     *
     * @param valor: valor a ser sacado
     *
     * @throws SaldoInsuficienteException se o valor for maior que o saldo
     *
     * @throws ValorInvalidoException se o valor do saque for invalido
     */
    @Override
    public void sacar(Double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("menor ou igual a zero!");
        }

        if (limite < valor) {
            throw new ValorInvalidoException("o valor nao pode ser maior que o limite da conta!");
        }

        if (saldo < valor) {
            throw new SaldoInsuficienteException("o valor nao pode ser superior ao saldo!");
        }

        super.saldo -= valor;
        movimentacoes.add(
                new Movimentacao("Saque",
                        valor,
                        LocalDateTime.now(),
                        ("Saque na conta corrente de " + super.titular.getNome() + " id: " + super.id),
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
                        ("Deposito na conta corrente de " + super.titular.getNome() + " id: " + super.id),
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
     */
    @Override
    public void transferir(Conta c, Double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("menor ou igual a zero!");
        }

        if (limite < valor) {
            throw new ValorInvalidoException("o valor nao pode ser maior que o limite da conta!");
        }

        if (saldo < valor) {
            throw new SaldoInsuficienteException("o valor nao pode ser superior ao saldo!");
        }

        c.saldo += valor;
        super.saldo -= valor;

        c.movimentacoes.add(
                new Movimentacao("Recebimento de Transferencia",
                        valor,
                        LocalDateTime.now(),
                        ("Transferencia da conta corrente de id:" + super.id + " para id:" + c.id),
                        c.saldo
                )
        );
        movimentacoes.add(
                new Movimentacao("Transferencia",
                        valor,
                        LocalDateTime.now(),
                        ("Transferencia da conta corrente de id:" + super.id + " para id:" + c.id),
                        super.saldo
                ));
    }

    //===== Construtor, toString, getters e setters
    public Corrente(Double limite, Cliente titular, Double saldo, Long id) {
        super(titular, saldo, id);
        this.limite = limite;
    }

    public Corrente(Double limite, Cliente titular, Double saldo) {
        super(titular, saldo);
        this.limite = limite;
    }

    public Corrente(Double limite, Double saldo) {
        super(null, saldo);
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "Corrente{"
                + "id: " + id
                + ", titular: " + titular.getNome()
                + ", saldo: " + saldo
                + ", limite: " + limite
                + ", movimentacoes: " + movimentacoes
                + '}';
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

}
