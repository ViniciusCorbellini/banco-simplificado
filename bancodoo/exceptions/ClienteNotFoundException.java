package bancodoo.exceptions;

/**
 * Excecao para clientes nao encontrados
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Heranca
 * - Tratamento de excecoes (Excecao personalizada)
 * 
 * @author Vinicius Corbellini
 */
public class ClienteNotFoundException extends Exception{
    
    /**
     * Construtor vazio com mensagem padrao da excecao
     */
    public ClienteNotFoundException() {
        super("Cliente nao encontrado.");
    }
    
    /**
     * Construtor personalizado da excecao
     * 
     * @param message mensagem personalizada
     */
    public ClienteNotFoundException(String message) {
        super("Cliente nao encontrado: " + message);
    }
    
}
