package entidade;

/**
 *
 * @author Thiágo
 */
public class Quarto {
    private double valorDiaria;
    private int capacidade, codigo;
    private boolean arCondicionado, hidromassagem;
    private int codigoResidencia;
    
    public Quarto(){
        
    }

    public Quarto(int codigo, double valorDiaria, int capacidade, boolean arCondicionado, boolean hidromassagem, int codigoResidencia){
        this.codigo = codigo;
        this.valorDiaria = valorDiaria;
        this.capacidade = capacidade;
        this.arCondicionado = arCondicionado;
        this.hidromassagem = hidromassagem;
        this.codigoResidencia = codigoResidencia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean isHidromassagem() {
        return hidromassagem;
    }

    public void setHidromassagem(boolean hidromassagem) {
        this.hidromassagem = hidromassagem;
    }    

    public int getCodigoResidencia() {
        return codigoResidencia;
    }

    public void setCodigoResidencia(int codigoResidencia) {
        this.codigoResidencia = codigoResidencia;
    }

    
    
    
}
