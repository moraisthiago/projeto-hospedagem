package entidade;

/**
 *
 * @author Thiágo
 */
import java.util.ArrayList;

public class Residencia {
    private String rua, bairro, cep, telefone, email;
    private int numero, codigo;
    protected ArrayList<Quarto> quartos = new ArrayList();
    
    public Residencia(){
        
    }

    public Residencia(int codigo, String rua, int numero, String bairro, String cep, String telefone, String email, ArrayList<Quarto> quartos){
        this.codigo = codigo;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
        this.quartos = quartos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public ArrayList<Quarto> getQuartos() {
        return quartos;
    }

    public void setQuartos(ArrayList<Quarto> quartos) {
        this.quartos = quartos;
    }
}
