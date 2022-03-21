package dao;

/**
 *
 * @author Thiágo
 */
import entidade.Quarto;
import entidade.AluguelQuarto;
import entidade.Hospede;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AluguelDAO {
    
    public void adicionarAluguel(AluguelQuarto aluguel){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();          
            stmt = conexao.prepareCall("INSERT INTO ALUGUEL(CODIGO, DATAENTRADA, HORAENTRADA, DATASAIDA, HORASAIDA, FK_HOSPEDE, FK_QUARTO) VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1, aluguel.getCodigo());
            stmt.setString(2, aluguel.getEntradaDia());
            stmt.setString(3, aluguel.getEntradaHora());
            stmt.setString(4, aluguel.getSaidaDia());
            stmt.setString(5, aluguel.getSaidaHora());
            stmt.setInt(6, aluguel.getHospede().getCodigo());
            stmt.setInt(7, aluguel.getQuarto().getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            
        }
    }
    
    public List<AluguelQuarto> listarAlugueis(){
        List<AluguelQuarto> listaRetorno = new LinkedList<>();
        
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM ALUGUEL INNER JOIN HOSPEDE ON ALUGUEL.FK_HOSPEDE = HOSPEDE.CODIGO INNER JOIN QUARTO ON ALUGUEL.FK_QUARTO = QUARTO.CODIGO");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Hospede hospede = new Hospede(rs.getInt("CODIGO"),
                rs.getString("NOME"),
                rs.getString("CPF"),
                rs.getString("ENDERECO"), 
                rs.getString("CELULAR"),
                rs.getString("TELEFONE"), 
                rs.getString("EMAIL"));
                
                Quarto quarto = new Quarto(rs.getInt("CODIGO"),
                rs.getDouble("VALORDIARIA"),
                rs.getInt("CAPACIDADE"),
                rs.getBoolean("ARCONDICIONADO"), 
                rs.getBoolean("HIDROMASSAGEM"),
                rs.getInt("FK_RESIDENCIA"));
                
                AluguelQuarto aluguel = new AluguelQuarto(rs.getInt("CODIGO"),
                rs.getString("DATAENTRADA"),
                rs.getString("HORAENTRADA"),
                rs.getString("DATASAIDA"), 
                rs.getString("HORASAIDA"),
                hospede,
                quarto
                );
                
                listaRetorno.add(aluguel);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(rs, stmt, conexao);
        }
        
        return listaRetorno;
    }
    
    public AluguelQuarto getAluguelPeloCodigo(int codigo){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM ALUGUEL INNER JOIN HOSPEDE ON ALUGUEL.FK_HOSPEDE = HOSPEDE.CODIGO INNER JOIN QUARTO ON ALUGUEL.FK_QUARTO = QUARTO.CODIGO WHERE ALUGUEL.CODIGO=?");
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            
            if (rs.next()){
                Hospede hospede = new Hospede(rs.getInt("CODIGO"),
                rs.getString("NOME"),
                rs.getString("CPF"),
                rs.getString("ENDERECO"), 
                rs.getString("CELULAR"),
                rs.getString("TELEFONE"), 
                rs.getString("EMAIL"));
                
//                ArrayList<Quarto> quartos = new ArrayList();
//                Residencia residencia = new Residencia(rs.getInt("CODIGO"),
//                rs.getString("RUA"),
//                rs.getInt("NUMERO"),
//                rs.getString("BAIRRO"),
//                rs.getString("CEP"),
//                rs.getString("TELEFONE"),
//                rs.getString("EMAIL"),
//                quartos);
                
                Quarto quarto = new Quarto(rs.getInt("CODIGO"),
                rs.getDouble("VALORDIARIA"),
                rs.getInt("CAPACIDADE"),
                rs.getBoolean("ARCONDICIONADO"), 
                rs.getBoolean("HIDROMASSAGEM"),
                rs.getInt("FK_RESIDENCIA"));
                
                AluguelQuarto aluguel = new AluguelQuarto(rs.getInt("CODIGO"),
                rs.getString("DATAENTRADA"),
                rs.getString("HORAENTRADA"),
                rs.getString("DATASAIDA"), 
                rs.getString("HORASAIDA"),
                hospede,
                quarto
                );
                
                return aluguel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            postgres.close(rs, stmt, conexao);
        }
        
        return null;
    }
    
    public void updateAluguel(AluguelQuarto aluguel){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE ALUGUEL SET DATAENTRADA=?, HORAENTRADA=?, DATASAIDA=?, HORASAIDA=?, FK_HOSPEDE=?, FK_QUARTO=? WHERE CODIGO=?");
            stmt.setString(1, aluguel.getEntradaDia());
            stmt.setString(2, aluguel.getEntradaHora());
            stmt.setString(3, aluguel.getSaidaDia());
            stmt.setString(4, aluguel.getSaidaHora());
            stmt.setInt(5, aluguel.getHospede().getCodigo());
            stmt.setInt(6, aluguel.getQuarto().getCodigo());
            stmt.setInt(7, aluguel.getCodigo());
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(stmt, conexao);
        }
    }
    
    public void deletarAluguel(int codigo){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM ALUGUEL WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
}
