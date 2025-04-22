package bancodoo.conta;

/**
 * Classe que define os metodos base para contas que realizam transacoes
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Interface
 * 
 * @author Vinicius Corbellini
 */
public interface Transacao {
    /**
     * retirada de dinheiro da conta
     * 
     * @param valor valor a ser sacado
     * 
     * @throws Exception
     */
    public void sacar(Double valor) throws Exception;
    
    /**
     * insercao de dinheiro na conta
     * 
     * @param valor valor a ser depositado
     * 
     * @throws java.lang.Exception
     */
    public void depositar(Double valor) throws Exception;
    
    /**
     * transferencia de dinheiro de uma conta para outra
     * 
     * @param c conta de destino da transferencia
     * 
     * @param valor valor a ser transferido
     * 
     * @throws Exception
     */
    public void transferir(Conta c, Double valor) throws Exception;
}
