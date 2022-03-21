package entidade;

/**
 *
 * @author Thiágo
 */
import entidade.Hospede;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AluguelQuarto {
    private int codigo;
    private String entradaDia, entradaHora, saidaDia, saidaHora;
    private Hospede hospede;
    private Quarto quarto;
    private long diarias;
    
    public AluguelQuarto(){
        
    }

    public AluguelQuarto(int codigo, String entradaDia, String entradaHora, String saidaDia, String saidaHora, Hospede hospede, Quarto quarto) {
        this.codigo = codigo;
        this.entradaDia = entradaDia;
        this.entradaHora = entradaHora;
        this.saidaDia = saidaDia;
        this.saidaHora = saidaHora;
        this.hospede = hospede;
        this.quarto = quarto;
        this.diarias = 0;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public String getEntradaDia() {
        return entradaDia;
    }

    public void setEntradaDia(String entradaDia) {
        this.entradaDia = entradaDia;
    }

    public String getEntradaHora() {
        return entradaHora;
    }

    public void setEntradaHora(String entradaHora) {
        this.entradaHora = entradaHora;
    }

    public String getSaidaDia() {
        return saidaDia;
    }

    public void setSaidaDia(String saidaDia) {
        this.saidaDia = saidaDia;
    }

    public String getSaidaHora() {
        return saidaHora;
    }

    public void setSaidaHora(String saidaHora) {
        this.saidaHora = saidaHora;
    }
    
    

    public long calcularDiarias(){
        LocalDate entrada = LocalDate.now();
        LocalTime eh = LocalTime.now();
        
        LocalDate saida = LocalDate.now();
        LocalTime sh = LocalTime.now();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        entrada = LocalDate.parse(entradaDia, dtf);
        eh = LocalTime.parse(entradaHora);
        
        saida = LocalDate.parse(saidaDia, dtf);
        sh = LocalTime.parse(saidaHora);
        
        System.out.println("Entrada: " + entrada.format(dtf) + " as " + eh);
        System.out.println("Saida: " + saida.format(dtf) + " as " + sh);
        
        diarias = ChronoUnit.DAYS.between(entrada, saida);
        
        String[] hora = saidaHora.split(":");
        
        if (Integer.parseInt(hora[0]) >= 12){
            diarias += 1;
        }
        
        return diarias;
    }

    public double valorDiarias(){     
        return calcularDiarias()*quarto.getValorDiaria();
    }
    
}
