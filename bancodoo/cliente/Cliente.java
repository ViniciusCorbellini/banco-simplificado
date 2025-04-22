package bancodoo.cliente;

import bancodoo.conta.Conta;
import bancodoo.exceptions.ContaNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata (Conceito exigido para o trabalho) que define os atributos e
 * metodos basicos de todas as contas
 *
 * Exigencias do trabalho implementadas por essa classe: 
 * - Classe Abstrata
 * - Heranca (superclasse)
 * - Collections
 *
 * @author Vinicius Corbellini
 */
public abstract class Cliente {

    //Nome do cliente
    protected String nome;

    //Email do cliente
    protected String email;

    //Lista de contas de um cliente, sendo que: Cliente 1:n Contas
    protected List<Conta> contas;

    /**
     * Metodo necessario para obter CPF ou CNPJ, de acordo com a classe do
     * cliente
     *
     * @return cpf se cliente instanceof PessoaFisica || cnpj se cliente
     * instanceof PessoaJuridica
     */
    public abstract String getDocumento();

    /**
     * adiciona uma conta a lista de contas do cliente
     *
     * @param c conta a ser inserida
     *
     * @return true se a conta for inserida corretamente
     */
    public boolean adicionarConta(Conta c) {
        c.setTitular(this);
        return contas.add(c);
    }

    /**
     * remove uma conta de acordo com um determinado identificador
     *
     * @param id identificador da conta
     *
     * @return true se a conta for corretamente removida
     *
     * @throws ContaNotFoundException se a conta nao existir
     */
    public boolean removerConta(Long id) throws ContaNotFoundException {
        Conta c = procurarConta(id);
        return contas.remove(c);
    }

    /**
     * procura uma conta na lista de contas do cliente
     *
     * @param id identificador a ser buscado
     *
     * @return objeto conta se o id existir
     *
     * @throws ContaNotFoundException se nao houver nenhuma conta com tal id
     */
    public Conta procurarConta(Long id) throws ContaNotFoundException {
        for (Conta c : contas) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        throw new ContaNotFoundException("o cliente " + this.nome + " nao possui nenhuma conta com id " + id);
    }

    /**
     * lista todas as contas do cliente
     */
    public void listarContas() {
        for (Conta c : contas) {
            System.out.println(c.toString());
        }
    }

    //===== Construtor, toString, getters e setters
    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.contas = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Cliente{"
                + ", nome: " + nome
                + ", email: " + email
                + ", contas: " + contas
                + '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}
