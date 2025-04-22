package bancodoo.cliente;

/**
 * PessoaFisica herda a classe abstrata Cliente e define os metodos e atributos
 * da pessoa fisica
 * 
 * Exigencias do trabalho implementadas por essa classe:
 * - heranca (subclasse)
 * - polimorfismo (entre metodos herdados sobrescritos)
 *
 * @author Vinicius Corbellini
 */
public class PessoaFisica extends Cliente {

    //Cpf do cliente pf
    private String cpf;

    //telefone do cliente pf
    private String telefone;

    /**
     * getter para o documento do cliente
     * 
     * @return cpf da Pessoa Fisica
     */
    @Override
    public String getDocumento() {
        return this.cpf;
    }

    //===== Construtor, toString, getters e setter
    public PessoaFisica(String cpf, String telefone, String nome, String email) {
        super(nome, email);
        this.cpf = cpf;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "PessoaFisica{"
                + "nome: " + nome
                + ", email: " + email
                + ", cpf: " + cpf
                + ", telefone: " + telefone
                + ", contas: " + contas
                + '}';
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
