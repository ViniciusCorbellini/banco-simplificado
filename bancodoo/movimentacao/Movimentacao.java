package bancodoo.movimentacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa uma movimentacao monetaria de uma conta
 * 
 * Exigencias do trabalho implementadas por essa classe: 
 * - Polimorfismo (toString)
 * 
 * @author Vinicius Corbellini
 */
public class Movimentacao {
    //Tipo da movimentacao. Ex: saque, deposito, etc
    private String tipo;
   
    //valor
    private Double valor;
    
    //Data e hora da transacao
    private LocalDateTime dt_hora;
    
    //Detalhes da movimentacao. Ex: nome do destinatario de uma tranferencia
    private String detalhes;
    
    //Saldo atual da conta
    private Double saldo_atual;

    
    //===== Construtor e toString
    public Movimentacao(String tipo, Double valor, LocalDateTime dt_hora, String detalhes, Double saldo_atual) {
        this.tipo = tipo;
        this.valor = valor;
        this.dt_hora = dt_hora;
        this.detalhes = detalhes;
        this.saldo_atual = saldo_atual;
    }

    @Override
    public String toString() {
        return String.format("Movimentacao -> [%s] %s de R$%.2f (%s :: Saldo atual R$%.2f)", dt_hora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")), tipo, valor, detalhes, saldo_atual);
    }
}
