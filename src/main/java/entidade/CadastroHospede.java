package entidade;

/**
 *
 * @author Thiágo
 */
import entidade.Hospede;
import java.util.ArrayList;

public class CadastroHospede {
    private ArrayList<Hospede> hospedes = new ArrayList();

    public CadastroHospede() {
    }

    public CadastroHospede(ArrayList<Hospede> hospedes){
        this.hospedes = hospedes;
    }

    public void cadastrarHospede(Hospede hospede){
      hospedes.add(hospede);
    }
}
