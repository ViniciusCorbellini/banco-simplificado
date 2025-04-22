package bancodoo.cliente;

/**
 * PessoaJuridica herda a classe abstrata Cliente e define os metodos e atributos
 * da pessoa juridica
 * 
 * Exigencias do trabalho implementadas por essa classe:
 * - heranca (subclasse)
 * - polimorfismo (entre metodos herdados sobrescritos)
 *
 * @author Vinicius Corbellini
 */
public class PessoaJuridica extends Cliente{
    //Cnpj 
    private String cnpj;
    
    //Razao social
    private String razao_social;

    /**
     * getter para o documento do cliente 
     * 
     * @return cnpj da pessoa fisica
     */
    @Override
    public String getDocumento() {
        return cnpj;
    }
    
    //===== Construtor, toString, getters e setter
    public PessoaJuridica(String cnpj, String razao_social, String nome, String email) {
        super(nome, email);
        this.cnpj = cnpj;
        this.razao_social = razao_social;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" 
                + "nome: " + nome 
                + ", email: " + email 
                + ", cnpj: " + cnpj 
                + ", razao social: " + razao_social 
                + ", contas: " + contas
                + '}';
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razao_social;
    }

    public void setRazaoSocial(String razao_social) {
        this.razao_social = razao_social;
    }
}
