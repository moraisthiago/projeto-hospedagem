package dao;


import entidade.Quarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Thiágo
 */
public class QuartoDAO {
    
    public void adicionarQuarto(Quarto quarto){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO QUARTO(CODIGO, VALORDIARIA, CAPACIDADE, ARCONDICIONADO, HIDROMASSAGEM, FK_RESIDENCIA) VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, quarto.getCodigo());
            stmt.setDouble(2, quarto.getValorDiaria());
            stmt.setInt(3, quarto.getCapacidade());
            stmt.setBoolean(4, quarto.isArCondicionado());
            stmt.setBoolean(5, quarto.isHidromassagem());
            stmt.setInt(6, quarto.getCodigoResidencia());
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(stmt, conexao);
        }
    }
    
    public List<Quarto> listarQuartos(){
        List<Quarto> listaRetorno = new LinkedList();
        
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM QUARTO ORDER BY CODIGO");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Quarto quarto = new Quarto(rs.getInt("CODIGO"),
                rs.getDouble("VALORDIARIA"),
                rs.getInt("CAPACIDADE"),
                rs.getBoolean("ARCONDICIONADO"), 
                rs.getBoolean("HIDROMASSAGEM"),
                rs.getInt("FK_RESIDENCIA"));
                
                listaRetorno.add(quarto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(rs, stmt, conexao);
        }
        
        return listaRetorno;
    }
    
    public Quarto getQuartoPeloCodigo(int codigo){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM QUARTO WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            
            if (rs.next()){               
                Quarto quarto = new Quarto(rs.getInt("CODIGO"),
                rs.getDouble("VALORDIARIA"),
                rs.getInt("CAPACIDADE"),
                rs.getBoolean("ARCONDICIONADO"), 
                rs.getBoolean("HIDROMASSAGEM"),
                rs.getInt("FK_RESIDENCIA"));
                
                return quarto;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(rs, stmt, conexao);
        }
        
        return null;
    }
    
    public List<Quarto> getQuartosPeloCodigoResidencia(int codigo){
        List<Quarto> listaRetorno = new LinkedList();
        
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM QUARTO WHERE FK_RESIDENCIA=? ORDER BY CODIGO");
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            
            while (rs.next()){               
                Quarto quarto = new Quarto(rs.getInt("CODIGO"),
                rs.getDouble("VALORDIARIA"),
                rs.getInt("CAPACIDADE"),
                rs.getBoolean("ARCONDICIONADO"), 
                rs.getBoolean("HIDROMASSAGEM"),
                rs.getInt("FK_RESIDENCIA"));
                
                listaRetorno.add(quarto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(rs, stmt, conexao);
        }
        
        return listaRetorno;
    }
    
    public void updateQuarto(Quarto quarto){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE QUARTO SET VALORDIARIA=?, CAPACIDADE=?, ARCONDICIONADO=?, HIDROMASSAGEM=? WHERE CODIGO=?");
            stmt.setDouble(1, quarto.getValorDiaria());
            stmt.setInt(2, quarto.getCapacidade());
            stmt.setBoolean(3, quarto.isArCondicionado());
            stmt.setBoolean(4, quarto.isHidromassagem());
            stmt.setInt(5, quarto.getCodigo());
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(stmt, conexao);
        }
    }
    
    public void deletarQuarto(int codigo){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM QUARTO WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
}
