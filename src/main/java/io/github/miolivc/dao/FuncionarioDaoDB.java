package io.github.miolivc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import io.github.miolivc.entities.Agencia;
import io.github.miolivc.entities.Funcionario;
import io.github.miolivc.factories.ConnectionFactory;
import java.sql.Date;

public class FuncionarioDaoDB implements FuncionarioDao{
    private final Connection conn;

    public FuncionarioDaoDB() throws SQLException, ClassNotFoundException {
        conn = ConnectionFactory.getConnection();
    }
    
    @Override
    public boolean add(Funcionario funcionario) {
        String sql = "INSERT INTO FUNCIONARIO (CPF,TIPO,NOME,RG,DATANASC,EMAIL,SENHA,CODAG) VALUES (?,?,?,?,?,?,?,?);"
                    + "INSERT INTO ENDERECO_FUNCIONARIO (CPF,RUA,BAIRRO,CIDADE,CEP,NUMERO,UF) VALUES (?,?,?,?,?,?,?);"
                    + "INSERT INTO TELEFONE_FUNCIONARIO (CPF,TELEFONE) VALUES (?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,funcionario.getCpf());
            stmt.setString(2, funcionario.getTipo());
            stmt.setString(3, funcionario.getNome());
            stmt.setString(4, funcionario.getRg());
            stmt.setDate(5, Date.valueOf(funcionario.getDataNasc())); 
            stmt.setString(6, funcionario.getEmail());
            stmt.setString(8, funcionario.getSenha());
            stmt.setInt(9, funcionario.getAgencia().getCodigo());

            stmt.setString(10, funcionario.getCpf());
            stmt.setString(11, funcionario.getEndereco().getRua());
            stmt.setString(12, funcionario.getEndereco().getBairro());
            stmt.setString(13, funcionario.getEndereco().getCidade());
            stmt.setString(14, funcionario.getEndereco().getCep());
            stmt.setString(15, funcionario.getEndereco().getNumero());
            stmt.setString(16, funcionario.getEndereco().getUf());
            
            stmt.setString(17, funcionario.getCpf());
            stmt.setString(18, funcionario.getTelefone());
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }    
        return false;
    }

    @Override
    public boolean delete(String cpf) {
        String sql = "DELETE FROM TELEFONE_FUNCIONARIO WHERE CPF ILIKE " + cpf + ";"
                    + "DELETE FROM ENDERECO_FUNCIONARIO WHERE CPF ILIKE " + cpf + ";"
                    + "DELETE FROM FUNCIONARIO WHERE CPF ILIKE " + cpf + ";";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
                ex.printStackTrace(); 
        }
        return false;
    }

    @Override
    public List<Funcionario> list() {
        List<Funcionario> funcionarios = null;
        String sql = "SELECT * FROM FUNCIONARIO NATURAL JOIN TELEFONE_FUNCIONARIO NATURAL JOIN ENDERECO_FUNCIONARIO";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
            	Funcionario funcionario = new Funcionario();
            	Agencia agencia = new Agencia();
            	agencia.setCodigo(rs.getInt("codag"));            	
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTipo(rs.getString("tipo"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataNasc(rs.getDate("datanasc").toLocalDate());
                funcionario.setRg(rs.getString("rg"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setAgencia(agencia);
                funcionario.getEndereco().setRua(rs.getString("rua"));
                funcionario.getEndereco().setBairro(rs.getString("bairro"));
                funcionario.getEndereco().setCidade(rs.getString("cidade"));
                funcionario.getEndereco().setCep(rs.getString("cep"));
                funcionario.getEndereco().setNumero(rs.getString("numero"));
                funcionario.getEndereco().setUf(rs.getString("uf"));
                
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return funcionarios;
    }

    @Override
    public Funcionario find(String cpf) {
    	Funcionario funcionario = null;
    String sql = "SELECT * FROM FUNCIONARIO NATURAL JOIN TELEFONE_FUNCIONARIO"
                + " NATURAL JOIN ENDERECO_FUNCIONARIO WHERE CPF ILIKE" + cpf;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
            	funcionario = new Funcionario();
            	Agencia agencia = new Agencia();
            	agencia.setCodigo(rs.getInt("codag"));
            	funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTipo(rs.getString("tipo"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataNasc(rs.getDate("datanasc").toLocalDate());
                funcionario.setRg(rs.getString("rg"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setAgencia(agencia);
                funcionario.getEndereco().setRua(rs.getString("rua"));
                funcionario.getEndereco().setBairro(rs.getString("bairro"));
                funcionario.getEndereco().setCidade(rs.getString("cidade"));
                funcionario.getEndereco().setCep(rs.getString("cep"));
                funcionario.getEndereco().setNumero(rs.getString("numero"));
                funcionario.getEndereco().setUf(rs.getString("uf"));
                stmt.close();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return funcionario;
    }

    @Override
    public boolean update(Funcionario funcionario) {
        String sql = "UPDATE FUNCIONARIO SET CPF = ?, TIPO = ?, NOME = ?, RG = ?,"
                    + " DATANASC = ?, EMAIL = ?, SENHA = ?, CODAG = ? WHERE CPF ILIKE " + funcionario.getCpf() + ";" 
                    + "UPDATE ENDERECO_FUNCIONARIO SET CPF = ?, RUA = ?, BAIRRO = ?,CIDADE = ?,"
                    + "CEP = ?,NUMERO = ?,UF = ? WHERE CPF ILIKE " + funcionario.getCpf() + ";"
                    + "UPDATE TELEFONE_FUNCIONARIO SET CPF = ?, TELEFONE  = ? WHERE CPF ILIKE " + funcionario.getCpf() + ";";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,funcionario.getCpf());
            stmt.setString(2, funcionario.getTipo());
            stmt.setString(3, funcionario.getNome());
            stmt.setString(4, funcionario.getRg());
            stmt.setDate(5, Date.valueOf(funcionario.getDataNasc()));
            stmt.setString(6, funcionario.getEmail());
            stmt.setString(8, funcionario.getSenha());
            stmt.setInt(9, funcionario.getAgencia().getCodigo());    
            stmt.setString(10, funcionario.getCpf());
            stmt.setString(11, funcionario.getEndereco().getRua());
            stmt.setString(12, funcionario.getEndereco().getBairro());
            stmt.setString(13, funcionario.getEndereco().getCidade());
            stmt.setString(14, funcionario.getEndereco().getCep());
            stmt.setString(15, funcionario.getEndereco().getNumero());
            stmt.setString(16, funcionario.getEndereco().getUf());
            
            stmt.setString(17, funcionario.getCpf());
            stmt.setString(18, funcionario.getTelefone());
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
