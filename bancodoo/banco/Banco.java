package bancodoo.banco;

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
import java.util.LinkedList;
import java.util.List;

/**
 * Classe que tem a funcao de manipular contas e clientes 
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Polimorfismo (Overloading)
 * - Collections
 * 
 * @author Vinicius Corbellini
 */
public class Banco {
    //Numero de contas cadastradas no banco
    private int num_contas;
    
    //Atributo para a criacao de identificadores para as contas
    private Long ids;

    //Lista de clientes do banco
    private List<Cliente> clientes;

    //===== Operacoes envolvendo clientes
    /**
     * adiciona um cliente a lista de clientes
     *
     * @param c cliente a ser inserido
     *
     * @return true se o cliente for corretamente adicionado
     *
     * @throws InputInvalidoException caso o cliente possuir informacoes
     * invalidas
     */
    public boolean adicionarCliente(Cliente c) throws InputInvalidoException {
        validarCliente(c);
        if (c.getContas() != null) {
            num_contas += c.getContas().size();
        }
        return clientes.add(c);
    }

    /**
     * remove um cliente da lista de clientes
     *
     * @param c cliente a ser removido
     *
     * @return true se o cliente for corretamente removido
     */
    public boolean removerCliente(Cliente c) {
        if (c.getContas() != null) {
            num_contas -= c.getContas().size();
        }
        return clientes.remove(c);
    }

    /**
     * remove um cliente de acordo com seu documento
     *
     * @param doc documento do cliente a ser removido
     *
     * @return true se o cliente for corretamente removido
     *
     * @throws ClienteNotFoundException caso nao exista cliente com tal
     * documento
     */
    public boolean removerCliente(String doc) throws ClienteNotFoundException {
        Cliente c = procurarCliente(doc);
        return removerCliente(c);
    }

    /**
     * procura um cliente com base na String documento
     *
     * @param documento parametro para a busca de cliente
     *
     * @return c: cliente existente na lista cujo documento e igual ao parametro
     * passado
     *
     * @throws ClienteNotFoundException caso nao exista um cliente com tal
     * documento
     */
    public Cliente procurarCliente(String documento) throws ClienteNotFoundException {
        for (Cliente c : clientes) {
            if (c instanceof PessoaFisica pf
                    && pf.getDocumento().equalsIgnoreCase(documento)) {
                return c;
            }

            if (c instanceof PessoaJuridica pj
                    && pj.getDocumento().equalsIgnoreCase(documento)) {
                return c;
            }
        }
        throw new ClienteNotFoundException();
    }

    /**
     * lista todos os clientes (independentemente de seu tipo) presentes na
     * lista de clientes
     */
    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    /**
     * lista todas as contas de um determinado cliente
     *
     * @param c cliente dono das contas
     *
     * Obs: c.listarContas() é uma chamada de funcao da classe abstrata cliente.
     */
    public void listarContas(Cliente c) {
        c.listarContas();
    }

    /**
     * lista as contas de um determinado cliente encontrado por um documento
     *
     * @param doc documento do cliente
     *
     * @throws ClienteNotFoundException se nao houver um cliente com tal
     * documento
     */
    public void listarContas(String doc) throws ClienteNotFoundException {
        Cliente c = procurarCliente(doc);
        listarContas(c);
    }

    /**
     * lista a movimentacao de uma determinada conta
     *
     * @param ct conta na qual as movimentacoes ocorreram
     */
    public void listarMovimentacoes(Conta ct) {
        ct.getMovimentacoes().forEach(mov -> System.out.println(mov.toString()));

    }

    /**
     * lista as movimentacoes de uma conta de um determinado cliente
     *
     * @param doc documento do cliente
     *
     * @param id identificador da conta do cliente
     *
     * @throws ClienteNotFoundException caso nao houver um cliente com tal
     * documento
     *
     * @throws ContaNotFoundException caso nao houver uma conta com tal id
     */
    public void listarMovimentacoes(String doc, Long id) throws ClienteNotFoundException, ContaNotFoundException {
        Cliente c = procurarCliente(doc);
        Conta ct = c.procurarConta(id);
        listarMovimentacoes(ct);
    }

    //===== Operacoes envolvendo contas
    /**
     * aplica o rendimento em todas as contas de todos os clientes
     */
    public void aplicarRendimento() {
        for (Cliente cliente : clientes) {
            for (Conta c : cliente.getContas()) {
                if (c instanceof Poupanca p) {
                    p.aplicarRendimento();
                }
            }
        }
    }

    /**
     * adiciona uma conta a lista de contas de um determinado cliente encontrado
     * por seu documento
     *
     * @param doc documento do cliente
     *
     * @param c conta a ser inserida
     *
     * @return true se a conta for corretamente adicionada
     *
     * @throws ClienteNotFoundException caso nao houver um cliente com tal
     * documento
     *
     * @throws InputInvalidoException caso nao houver uma conta com tal id
     */
    public boolean adicionarConta(String doc, Conta c) throws ClienteNotFoundException, InputInvalidoException {
        Cliente cliente = procurarCliente(doc);
        c.setTitular(cliente);

        validarConta(c);
        ids++;
        num_contas++;
        c.setId(this.ids);

        return cliente.adicionarConta(c);
    }

    /**
     * remove uma conta - obtida pela busca de um determinado id - da lista de
     * contas de um cliente - obtido pela busca de um determinado documento
     *
     * @param documento documento do cliente
     *
     * @param id identificador da conta
     *
     * @return true se a conta for corretamente excluida
     *
     * @throws ClienteNotFoundException se o cliente nao existir
     *
     * @throws ContaNotFoundException se a conta nao existir
     */
    public boolean removerConta(String documento, Long id) throws ClienteNotFoundException, ContaNotFoundException {
        Cliente cliente = procurarCliente(documento);
        Conta c = procurarConta(id);
        return removerConta(cliente, c);
    }

    /**
     * Remove uma determinada conta da lista de contas de um determinado cliente
     * Obs: "cliente.removerConta(c.getId())" chama uma funcao da classe Cliente
     *
     * @param cliente cliente dono da conta
     *
     * @param c conta do cliente
     *
     * @return true se a conta por corretamente removida
     *
     * @throws ContaNotFoundException se a conta nao existir
     */
    public boolean removerConta(Cliente cliente, Conta c) throws ContaNotFoundException {
        cliente.removerConta(c.getId());
        num_contas--;
        return true;
    }

    /**
     * procura uma conta na lista de clientes do banco com base em um
     * determinado identificador
     *
     * @param id identificador a ser procurado
     *
     * @return c Objeto conta se o id existir
     *
     * @throws ContaNotFoundException se o id nao for encontrado
     */
    public Conta procurarConta(Long id) throws ContaNotFoundException {
        for (Cliente cliente : clientes) {
            for (Conta c : cliente.getContas()) {
                if (c.getId().equals(id)) {
                    return c;
                }
            }
        }
        throw new ContaNotFoundException();
    }

    /**
     * lista todas as contas de todos os clientes na lista de clientes do banco
     */
    public void listarTodasAsContas() {
        for (Cliente c : clientes) {
            listarContas(c);
        }
    }

    //===== Operacoes envolvendo contas
    /**
     * Saca um determinado valor de uma conta - obtida pela busca por
     * identificador - de um determinado cliente - obtido pela busca por
     * documento
     *
     * @param document documento do cliente
     *
     * @param id identificador da conta
     *
     * @param amount valor a ser sacado
     *
     * @throws ClienteNotFoundException se o cliente nao existir
     *
     * @throws ContaNotFoundException se a conta nao existir
     *
     * @throws SaldoInsuficienteException se o saldo da conta for insuficiente
     * para a transacao
     *
     * @throws ValorInvalidoException se o param amount for invalido
     *
     * @throws LimiteExcedidoException se o param amount for maior que o limite
     * da conta
     */
    public void sacar(String document, Long id, Double amount)
            throws ClienteNotFoundException, ContaNotFoundException,
            SaldoInsuficienteException, ValorInvalidoException,
            LimiteExcedidoException {

        Cliente cliente = procurarCliente(document);
        Conta conta = cliente.procurarConta(id);

        conta.sacar(amount);
    }

    /**
     * Deposita um determinado valor em uma conta - obtida pela busca por
     * identificador - de um determinado cliente - obtido pela busca por
     * documento
     *
     * @param document documento do cliente
     *
     * @param id identificador da conta
     *
     * @param amount valor a ser depositado
     *
     * @throws ClienteNotFoundException se o cliente nao existir
     *
     * @throws ContaNotFoundException se a conta nao existir
     *
     * @throws ValorInvalidoException se o param amount for invalido
     */
    public void depositar(String document, Long id, Double amount)
            throws ClienteNotFoundException, ContaNotFoundException,
            ValorInvalidoException {

        Cliente cliente = procurarCliente(document);
        Conta conta = cliente.procurarConta(id);

        conta.depositar(amount);
    }

    /**
     * Trasfere um valor de uma conta - obtida pelo identificador do remetente -
     * de um determinado cliente - obtido pelo documento do remetente - para a
     * conta destinataria - obtida pelo identificador do destinatario - de um
     * cliente destinatario - obtido pelo documento do destinatario.
     *
     * @param doc_remet documento do remetente
     *
     * @param doc_dest documento do destinatario
     *
     * @param id_remet identificador do remetente
     *
     * @param id_dest identificador do destinatario
     *
     * @param amount valor a ser transferido
     *
     * @throws ClienteNotFoundException se o cliente destinatario ou remetente
     * nao existir
     *
     * @throws ContaNotFoundException se a conta do destinatario ou do remetente
     * nao existir
     *
     * @throws SaldoInsuficienteException se o valor da transacao for superior
     * ao saldo do remetente
     *
     * @throws ValorInvalidoException se o valor da transacao for invalido
     *
     * @throws LimiteExcedidoException se o limite do remetente for excedido
     */
    public void transferir(String doc_remet, String doc_dest, Long id_remet, Long id_dest, Double amount)
            throws ClienteNotFoundException, ContaNotFoundException,
            SaldoInsuficienteException, ValorInvalidoException, LimiteExcedidoException {

        Cliente c_remet = procurarCliente(doc_remet);
        Cliente c_dest = procurarCliente(doc_dest);

        Conta remetente = c_remet.procurarConta(id_remet);
        Conta destinatario = c_dest.procurarConta(id_dest);

        remetente.transferir(destinatario, amount);
    }

    //===== Validacoes
    /**
     * valida um objeto cliente de acordo com sua classe e atributos
     *
     * @throws InputInvalidoException caso o documento do cliente ja exista na
     * lista de clientes ou se algum atributo do cliente for invalido (vazio ou
     * em branco)
     */
    private void validarCliente(Cliente c) throws InputInvalidoException {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equalsIgnoreCase(c.getDocumento())) {
                throw new InputInvalidoException("O documento ja existe no sistema!");
            }
        }
        if (c.getNome().isBlank()) {
            throw new InputInvalidoException("Nome invalido!");
        }
        if (c.getEmail().isBlank()) {
            throw new InputInvalidoException("Email invalido!");
        }
        if (c.getDocumento().isBlank()) {
            throw new InputInvalidoException("Documento invalido!");
        }
        if (c instanceof PessoaFisica pf && pf.getTelefone().isBlank()) {
            throw new InputInvalidoException("Telefone invalido!");
        }
        if (c instanceof PessoaJuridica pj && pj.getRazaoSocial().isBlank()) {
            throw new InputInvalidoException("Razao social invalida!");
        }
    }

    /**
     * valida um objeto conta de acordo com sua classe e atributos
     *
     * @throws InputInvalidoException caso o titular nao exista ou se algum
     * atributo for invalido
     */
    private void validarConta(Conta c) throws InputInvalidoException {
        if (c.getTitular() == null) {
            throw new InputInvalidoException("O titular nao pode ser nulo!");
        }
        if (c.getSaldo() < 0) {
            throw new InputInvalidoException("Saldo inicial nao pode ser negativo!");
        }
        if (c instanceof Poupanca p && p.getRendimento_mensal() <= 0) {
            throw new InputInvalidoException("Rendimento menor ou igual a zero!");
        }
        if (c instanceof Poupanca p && p.getLimite_operacoes() < 0) {
            throw new InputInvalidoException("O limite de operacoes nao pode ser negativo!");
        }
        if (c instanceof Corrente corr && corr.getLimite() < 0) {
            throw new InputInvalidoException("Limite nao pode ser negativo!");
        }
    }

    //===== Construtor, getters e setters
    public Banco() {
        this.clientes = new LinkedList<>();
        this.num_contas = 0;
        this.ids = 0L;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public int getNum_clientes() {
        return this.clientes.size();
    }

    public int getNum_contas() {
        return num_contas;
    }

    public Long getIds() {
        return ids;
    }
}
