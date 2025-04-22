package bancodoo.exceptions;

/**
 * Excecao para valores de input invalidos
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Heranca
 * - Tratamento de excecoes (Excecao personalizada)
 * 
 * @author Vinicius Corbellini
 */
public class ValorInvalidoException extends Exception {
    
    /**
     * Construtor vazio com mensagem padrao da excecao
     */
    public ValorInvalidoException() {
        super("Valor invalido.");
    }
    
    /**
     * Construtor personalizado da excecao
     * 
     * @param message mensagem personalizada
     */
    public ValorInvalidoException(String message) {
        super("Valor invalido: " + message);
    }
}
