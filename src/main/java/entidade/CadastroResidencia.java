package entidade;

/**
 *
 * @author Thiágo
 */
import java.util.ArrayList;

public class CadastroResidencia {
    private ArrayList<Residencia> residencias = new ArrayList();

    public CadastroResidencia(ArrayList<Residencia> residencias){
        this.residencias = residencias;
    }

    public void cadastrarResidencia(Residencia residencia){
      residencias.add(residencia);
    }
}
