package entidade;

/**
 *
 * @author Thiágo
 */
public class Hospede {
    private int codigo;
    private String nome, cpf, endereco, celular, telefone, email;
    
    public Hospede(){
        
    }
    
    public Hospede(int codigo, String nome, String cpf, String endereco, String celular, String telefone, String email){
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
