package bancodoo.conta;

import bancodoo.cliente.Cliente;
import bancodoo.exceptions.LimiteExcedidoException;
import bancodoo.exceptions.SaldoInsuficienteException;
import bancodoo.exceptions.ValorInvalidoException;
import bancodoo.movimentacao.Movimentacao;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que implementa a interface Transacao e define os atributos e
 * metodos basicos de todas as contas
 *
 * Exigencias do trabalho implementadas por essa classe:
 * - heranca
 * - polimorfismo (entre metodos herdados sobrescritos)
 * - Collections  
 * 
 * @author Vinicius Corbellini
 */
public abstract class Conta implements Transacao {

    //Identificador da conta
    protected Long id;

    //O cliente dono da conta, sendo que: cliente 1:n contas
    protected Cliente titular;

    //Atributo basico de todas as contas
    protected Double saldo;

    //Movimentacoes da conta
    protected List<Movimentacao> movimentacoes;

    /**
     * retirada de dinheiro da conta
     *
     * @param valor: valor a ser sacado
     * 
     * @throws bancodoo.exceptions.SaldoInsuficienteException
     *
     * @throws bancodoo.exceptions.ValorInvalidoException
     *
     * @throws bancodoo.exceptions.LimiteExcedidoException
     */
    @Override
    public abstract void sacar(Double valor) throws SaldoInsuficienteException, ValorInvalidoException, LimiteExcedidoException;

    /**
     * insercao de dinheiro na conta
     *
     * @param valor: valor a ser depositado
     * 
     * @throws bancodoo.exceptions.ValorInvalidoException
     */
    @Override
    public abstract void depositar(Double valor) throws ValorInvalidoException;

    /**
     * transferencia de dinheiro de uma conta para outra
     *
     * @param c conta de destino da transferencia
     *
     * @param valor valor a ser transferido
     *
     * @throws bancodoo.exceptions.SaldoInsuficienteException
     *
     * @throws bancodoo.exceptions.ValorInvalidoException
     *
     * @throws bancodoo.exceptions.LimiteExcedidoException
     */
    @Override
    public abstract void transferir(Conta c, Double valor) throws SaldoInsuficienteException, ValorInvalidoException, LimiteExcedidoException;

    /**
     * lista as movimentacoes da conta no terminal (sysout) ou mostra um aviso
     * se nao houver nenhuma movimentacao na conta
     */
    public void listarMovimentacoes() {
        if (this.movimentacoes.isEmpty()) {
            System.out.println("Nao ha movimentacoes para listar>>>");
        }

        for (Movimentacao m : movimentacoes) {
            System.out.println(m.toString());
        }
    }

    //===== Getters, setters, construtor e toString()
    public Conta(Cliente titular, Double saldo, Long id) {
        this.titular = titular;
        this.saldo = saldo;
        this.id = id;
        this.movimentacoes = new ArrayList<>();
    }

    public Conta(Cliente titular, Double saldo) {
        this.titular = titular;
        this.saldo = saldo;
        this.movimentacoes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Conta{"
                + "id: " + id
                + ", titular: " + titular.getNome()
                + ", saldo: " + saldo
                + ", movimentacoes: " + movimentacoes
                + '}';
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}
