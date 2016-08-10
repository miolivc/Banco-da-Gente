package io.github.miolivc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import io.github.miolivc.entities.Agencia;
import io.github.miolivc.factories.ConnectionFactory;

public class AgenciaDaoDB implements AgenciaDao{
    private final Connection conn;
    
    public AgenciaDaoDB() throws SQLException, ClassNotFoundException {
        conn = ConnectionFactory.getConnection();
    }
    
    @Override
    public boolean add(Agencia agencia) {
        String sql = "INSERT INTO AGENCIA (NOME) VALUES (?);"
        	+ "INSERT INTO ENDERECO_AGENCIA (CODAG,RUA,BAIRRO,CIDADE,CEP,NUMERO,UF) VALUES (?,?,?,?,?,?,?);"
                + "INSERT INTO TELEFONE_AGENCIA (CODAG,TELEFONE) VALUES (?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);            
            stmt.setString(1, agencia.getNome());
            stmt.setInt(2, agencia.getCodigo());
            stmt.setString(3, agencia.getEndereco().getRua());
            stmt.setString(4, agencia.getEndereco().getBairro());
            stmt.setString(5, agencia.getEndereco().getCidade());
            stmt.setString(6, agencia.getEndereco().getCep());
            stmt.setString(7, agencia.getEndereco().getNumero());
            stmt.setString(8, agencia.getEndereco().getUf());
            
            stmt.setInt(9, agencia.getCodigo());
            stmt.setString(10, agencia.getTelefone());
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }    
        return true;
    }

    @Override
    public boolean delete(int codigo) {
        String sql = "DELETE FROM TELEFONE_AGENCIA WHERE CODIGO ILIKE " + codigo + ";"
        	+ "DELETE FROM ENDERECO_AGENCIA WHERE CODAG ILIKE " + codigo + ";"
        	+ "DELETE FROM AGENCIA WHERE CODAG ILIKE " + codigo + ";";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }
        return true;
    }

    @Override
    public List<Agencia> list() {
        List<Agencia> agencias = null;
        String sql = "SELECT * FROM AGENCIA JOIN TELEFONE_AGENCIA NATURAL JOIN ENDERECO_AGENCIA WHERE (CODIGO = CODAG);";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
            	Agencia agencia = new Agencia();
            	
            	agencia.setCodigo(rs.getInt("codag"));
            	agencia.setNome(rs.getString("nome"));
                agencia.setTelefone(rs.getString("telefone"));
                agencia.getEndereco().setRua(rs.getString("rua"));
                agencia.getEndereco().setBairro(rs.getString("bairro"));
                agencia.getEndereco().setCidade(rs.getString("cidade"));
                agencia.getEndereco().setCep(rs.getString("cep"));
                agencia.getEndereco().setNumero(rs.getString("numero"));
                agencia.getEndereco().setUf(rs.getString("uf"));
                
                agencias.add(agencia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return agencias;
    }

    @Override
    public Agencia find(int codigo) {
    	Agencia agencia = null;
    String sql = "SELECT * FROM AGENCIA JOIN TELEFONE_AGENCIA"
                + " NATURAL JOIN ENDERECO_AGENCIA WHERE CODIGO ILIKE" + codigo;
        try {
        	Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){            	
            	agencia.setCodigo(rs.getInt("codigo"));
            	agencia.setNome(rs.getString("nome"));
                agencia.setTelefone(rs.getString("telefone"));
                agencia.getEndereco().setRua(rs.getString("rua"));
                agencia.getEndereco().setBairro(rs.getString("bairro"));
                agencia.getEndereco().setCidade(rs.getString("cidade"));
                agencia.getEndereco().setCep(rs.getString("cep"));
                agencia.getEndereco().setNumero(rs.getString("numero"));
                agencia.getEndereco().setUf(rs.getString("uf"));
                stmt.close();
                conn.close();;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return agencia;
    }
    
    @Override
    public Agencia find(String nome) {
    	Agencia agencia = null;
    String sql = "SELECT * FROM AGENCIA JOIN TELEFONE_AGENCIA NATURAL JOIN"
            + " ENDERECO_AGENCIA WHERE CODIGO = CODAG AND NOME ILIKE " + nome + ";";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){            	
            	agencia.setCodigo(rs.getInt("codigo"));
            	agencia.setNome(rs.getString("nome"));
                agencia.setTelefone(rs.getString("telefone"));
                agencia.getEndereco().setRua(rs.getString("rua"));
                agencia.getEndereco().setBairro(rs.getString("bairro"));
                agencia.getEndereco().setCidade(rs.getString("cidade"));
                agencia.getEndereco().setCep(rs.getString("cep"));
                agencia.getEndereco().setNumero(rs.getString("numero"));
                agencia.getEndereco().setUf(rs.getString("uf"));
                stmt.close();
                conn.close();;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return agencia;
    }
    
    @Override
    public boolean update(Agencia agencia) {
        String sql = "UPDATE AGENCIA SET NOME = ? WHERE CODIGO ILIKE " + agencia.getCodigo() + ";" 
                    + "UPDATE ENDERECO_AGENCIA SET CODAG = ?, RUA = ?, BAIRRO = ?,CIDADE = ?,"
                    + "CEP = ?,NUMERO = ?,UF = ? WHERE CODAG ILIKE " + agencia.getCodigo() + ";"
                    + "UPDATE TELEFONE_AGENCIA SET CODAG = ?, TELEFONE  = ? WHERE CODAG ILIKE " + agencia.getCodigo() + ";";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,agencia.getNome());
            
            stmt.setInt(2, agencia.getCodigo());
            stmt.setString(3, agencia.getEndereco().getRua());
            stmt.setString(4, agencia.getEndereco().getBairro());
            stmt.setString(5, agencia.getEndereco().getCidade());
            stmt.setString(6, agencia.getEndereco().getCep());
            stmt.setString(7, agencia.getEndereco().getNumero());
            stmt.setString(8, agencia.getEndereco().getUf());
            
            stmt.setInt(9, agencia.getCodigo());
            stmt.setString(10, agencia.getTelefone());
            
            //Falta contas
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
