package bancodoo.exceptions;

/**
 * Excecao para entradas invalidas 
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Heranca
 * - Tratamento de excecoes (Excecao personalizada)
 * 
 * @author Vinicius Corbellini
 */
public class InputInvalidoException extends Exception{

    /**
     * Construtor vazio com mensagem padrao da excecao
     */
    public InputInvalidoException() {
        super("Entrada invalida.");
    }

    /**
     * Construtor personalizado da excecao
     * 
     * @param message mensagem personalizada
     */
    public InputInvalidoException(String message) {
        super("Entrada invalida: " + message);
    }
}
