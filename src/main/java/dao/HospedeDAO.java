package dao;

/**
 *
 * @author Thiágo
 */
import entidade.Hospede;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class HospedeDAO {
    
    public void adicionarHospede(Hospede hospede){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO HOSPEDE(CODIGO, NOME, CPF, ENDERECO, CELULAR, TELEFONE, EMAIL) VALUES (?,?,?,?,?,?,?)");
            stmt.setInt(1, hospede.getCodigo());
            stmt.setString(2, hospede.getNome());
            stmt.setString(3, hospede.getCpf());
            stmt.setString(4, hospede.getEndereco());
            stmt.setString(5, hospede.getCelular());
            stmt.setString(6, hospede.getTelefone());
            stmt.setString(7, hospede.getEmail());
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(stmt, conexao);
        }
    }
    
    public List<Hospede> listarHospedes(){
        List<Hospede> listaRetorno = new LinkedList();
        
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM HOSPEDE ORDER BY CODIGO");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Hospede hospede = new Hospede(rs.getInt("CODIGO"),
                rs.getString("NOME"),
                rs.getString("CPF"),
                rs.getString("ENDERECO"), 
                rs.getString("CELULAR"),
                rs.getString("TELEFONE"), 
                rs.getString("EMAIL"));
                
                listaRetorno.add(hospede);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(rs, stmt, conexao);
        }
        
        return listaRetorno;
    }
    
    public Hospede getHospedePeloCodigo(int codigo){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM HOSPEDE WHERE CODIGO=?");
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
                
                return hospede;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(rs, stmt, conexao);
        }
        
        return null;
    }
    
    public void updateHospede(Hospede hospede){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE HOSPEDE SET NOME=?, CPF=?, ENDERECO=?, CELULAR=?, TELEFONE=?, EMAIL=? WHERE CODIGO=?");
            stmt.setString(1, hospede.getNome());
            stmt.setString(2, hospede.getCpf());
            stmt.setString(3, hospede.getEndereco());
            stmt.setString(4, hospede.getCelular());
            stmt.setString(5, hospede.getTelefone());
            stmt.setString(6, hospede.getEmail());
            stmt.setInt(7, hospede.getCodigo());
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            postgres.close(stmt, conexao);
        }
    }
    
    public void deletarHospede(int codigo){
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        
        try{
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM HOSPEDE WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
}
