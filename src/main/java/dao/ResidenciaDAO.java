package dao;

/**
 *
 * @author Thiágo
 */
import entidade.Quarto;
import entidade.Residencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResidenciaDAO {
    
    public void adicionarResidencia(Residencia residencia){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO RESIDENCIA(CODIGO, RUA, NUMERO, BAIRRO, CEP, TELEFONE, EMAIL) VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1, residencia.getCodigo());
            stmt.setString(2, residencia.getRua());
            stmt.setInt(3, residencia.getNumero());
            stmt.setString(4, residencia.getBairro());
            stmt.setString(5, residencia.getCep());
            stmt.setString(6, residencia.getTelefone());
            stmt.setString(7, residencia.getEmail());
            stmt.executeUpdate();
            
            for (Quarto quarto : residencia.getQuartos()){
                stmt = conexao.prepareStatement("INSERT INTO QUARTO(CODIGO, VALORDIARIA, CAPACIDADE, ARCONDICIONADO, HIDROMASSAGEM, FK_RESIDENCIA) VALUES(?,?,?,?,?,?)");
                stmt.setInt(1, quarto.getCodigo());
                stmt.setDouble(2, quarto.getValorDiaria());
                stmt.setInt(3, quarto.getCapacidade());
                stmt.setBoolean(4, quarto.isArCondicionado());
                stmt.setBoolean(5, quarto.isHidromassagem());
                stmt.setInt(6, residencia.getCodigo());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            postgres.close(stmt, conexao);
        }
    }
    
    public List<Residencia> listarResidencias(){
        List<Residencia> listaRetorno = new LinkedList<>();
        
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        ResultSet rsQuartos = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM RESIDENCIA ORDER BY CODIGO");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                ArrayList<Quarto> quartos = new ArrayList();
                Residencia residencia = new Residencia(rs.getInt("CODIGO"),
                rs.getString("RUA"),
                rs.getInt("NUMERO"),
                rs.getString("BAIRRO"),
                rs.getString("CEP"),
                rs.getString("TELEFONE"),
                rs.getString("EMAIL"),
                quartos
                );
                
                stmt = conexao.prepareStatement("SELECT * FROM QUARTO WHERE FK_RESIDENCIA=?");
                stmt.setInt(1, residencia.getCodigo());
                rsQuartos = stmt.executeQuery();
                
                while (rsQuartos.next()){
                    
                    Quarto quarto = new Quarto(rsQuartos.getInt("CODIGO"),
                    rsQuartos.getDouble("VALORDIARIA"),
                    rsQuartos.getInt("CAPACIDADE"),
                    rsQuartos.getBoolean("ARCONDICIONADO"),
                    rsQuartos.getBoolean("HIDROMASSAGEM"),
                    rsQuartos.getInt("FK_RESIDENCIA"));
                    
                    quartos.add(quarto);
                }
                
                listaRetorno.add(residencia);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(rs, stmt, conexao);
        }
        
        return listaRetorno;
    }
    
    public Residencia getResidenciaPeloCodigo(int codigo){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        ResultSet rsQuartos = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM RESIDENCIA WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            
            if (rs.next()){
                ArrayList<Quarto> quartos = new ArrayList();
                Residencia residencia = new Residencia(rs.getInt("CODIGO"),
                rs.getString("RUA"),
                rs.getInt("NUMERO"),
                rs.getString("BAIRRO"),
                rs.getString("CEP"),
                rs.getString("TELEFONE"),
                rs.getString("EMAIL"),
                quartos);
                
                stmt = conexao.prepareStatement("SELECT * FROM QUARTO WHERE FK_RESIDENCIA=?");
                stmt.setInt(1, residencia.getCodigo());
                rsQuartos = stmt.executeQuery();
                
                while (rsQuartos.next()){
                    Quarto quarto = new Quarto(rsQuartos.getInt("CODIGO"),
                    rsQuartos.getDouble("VALORDIARIA"),
                    rsQuartos.getInt("CAPACIDADE"),
                    rsQuartos.getBoolean("ARCONDICIONADO"),
                    rsQuartos.getBoolean("HIDROMASSAGEM"),
                    rsQuartos.getInt("FK_RESIDENCIA"));
                    
                    quartos.add(quarto);
                }
                
                return residencia;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            postgres.close(rs, stmt, conexao);
        }
        
        return null;
    }
    
    public void updateResidencia(Residencia residencia){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE RESIDENCIA SET  RUA=?, NUMERO=?, BAIRRO=?, CEP=?, TELEFONE=?, EMAIL=? WHERE CODIGO=?");
            stmt.setString(1, residencia.getRua());
            stmt.setInt(2, residencia.getNumero());
            stmt.setString(3, residencia.getBairro());
            stmt.setString(4, residencia.getCep());
            stmt.setString(5, residencia.getTelefone());
            stmt.setString(6, residencia.getEmail());
            stmt.setInt(7, residencia.getCodigo());
            stmt.executeUpdate();
            
            for (Quarto quarto : residencia.getQuartos()){
                conexao = postgres.getConection();
                stmt = conexao.prepareStatement("UPDATE QUARTO SET VALORDIARIA=?, CAPACIDADE=?, ARCONDICIONADO=?, HIDROMASSAGEM=? WHERE CODIGO=?");
                stmt.setDouble(1, quarto.getValorDiaria());
                stmt.setInt(2, quarto.getCapacidade());
                stmt.setBoolean(3, quarto.isArCondicionado());
                stmt.setBoolean(4, quarto.isHidromassagem());
                stmt.setInt(5, quarto.getCodigo());
                stmt.executeUpdate();
            }
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(stmt, conexao);
        }
            
    }
    
    public void deletarResidencia(int codigo){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM RESIDENCIA WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
}
