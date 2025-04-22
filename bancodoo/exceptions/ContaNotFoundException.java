package bancodoo.exceptions;

/**
 * Excecao para contas nao encontradas
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Heranca
 * - Tratamento de excecoes (Excecao personalizada)
 * 
 * @author Vinicius Corbellini
 */
public class ContaNotFoundException extends Exception{

    /**
     * Construtor vazio com mensagem padrao da excecao
     */
    public ContaNotFoundException() {
        super("Conta nao encontrada.");
    }

    /**
     * Construtor personalizado da excecao
     * 
     * @param message mensagem personalizada
     */
    public ContaNotFoundException(String message) {
        super("Conta nao encontrada: " + message);
    }
}
