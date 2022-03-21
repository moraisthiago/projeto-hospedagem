package entidade;

/**
 *
 * @author Thiágo
 */
import java.util.ArrayList;

public class CadastroAluguel {
    private ArrayList<AluguelQuarto> alugueis = new ArrayList<>();

    public CadastroAluguel() {
    }

    public CadastroAluguel(ArrayList<AluguelQuarto> alugueis){
        this.alugueis = alugueis;
    }

    public void cadastrarAluguel(AluguelQuarto aluguel){
      alugueis.add(aluguel);
    }
}
