package bancodoo.exceptions;

/**
 * Excecao para limites de conta excedidos
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Heranca
 * - Tratamento de excecoes (Excecao personalizada)
 * 
 * @author Vinicius Corbellini
 */
public class LimiteExcedidoException extends Exception{

    /**
     * Construtor vazio com mensagem padrao da excecao
     */
    public LimiteExcedidoException() {
        super("Limite de operacoes excedido.");
    }
    
    /**
     * Construtor personalizado da excecao
     * 
     * @param message mensagem personalizada
     */
    public LimiteExcedidoException(String message) {
        super("Limite de operacoes excedido: " + message);
    }
}
