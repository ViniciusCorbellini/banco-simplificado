package bancodoo.exceptions;

/**
 * Excecao de saldos insuficientes para transacoes
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Heranca
 * - Tratamento de excecoes (Excecao personalizada)
 * 
 * @author Vinicius Corbellini
 */
public class SaldoInsuficienteException extends Exception{
    
    /**
     * Construtor vazio com mensagem padrao da excecao
     */
    public SaldoInsuficienteException() {
        super("Saldo insuficiente.");
    }
    
    /**
     * Construtor personalizado da excecao
     * 
     * @param message mensagem personalizada
     */
    public SaldoInsuficienteException(String message) {
        super("Saldo insuficiente: " + message);
    }
}
