package io.github.miolivc.dao;

import io.github.miolivc.entities.Cliente;
import io.github.miolivc.factories.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClienteDaoDB implements ClienteDao{
    private final Connection conn;

    public ClienteDaoDB() throws SQLException, ClassNotFoundException {
        conn = ConnectionFactory.getConnection();
    }
    
    @Override
    public boolean add(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (CPF,CNPJ,NOME,RG,DATANASC,EMAIL,SENHA,FOTO) VALUES (?,?,?,?,?,?,?,?);"
                    + "INSERT INTO ENDERECO_CLIENTE (CPF,RUA,BAIRRO,CIDADE,CEP,NUMERO,UF) VALUES (?,?,?,?,?,?,?);"
                    + "INSERT INTO TELEFONE_CLIENTE (CPF,TELEFONE) VALUES (?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,cliente.getCpf());
            stmt.setString(2, cliente.getCnpj());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getRg());
            stmt.setDate(5, Date.valueOf(cliente.getDataNasc())); 
            stmt.setString(6, cliente.getEmail());
            stmt.setString(8, cliente.getSenha());
            stmt.setString(9, cliente.getFoto());
            
            stmt.setString(10, cliente.getCpf());
            stmt.setString(11, cliente.getEndereco().getRua());
            stmt.setString(12, cliente.getEndereco().getBairro());
            stmt.setString(13, cliente.getEndereco().getCidade());
            stmt.setString(14, cliente.getEndereco().getCep());
            stmt.setString(15, cliente.getEndereco().getNumero());
            stmt.setString(16, cliente.getEndereco().getUf());
            
            stmt.setString(17, cliente.getCpf());
            stmt.setString(18, cliente.getTelefone());
            
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
        String sql = "DELETE FROM TELEFONE_CLIENTE WHERE CPF ILIKE " + cpf + ";"
                    + "DELETE FROM ENDERECO_CLIENTE WHERE CPF ILIKE " + cpf + ";"
                    + "DELETE FROM CLIENTE WHERE CPF ILIKE " + cpf + ";";
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
    public List<Cliente> list() {
        List<Cliente> clientes = null;
        String sql = "SELECT * FROM CLIENTE NATURAL JOIN TELEFONE_CLIENTE NATURAL JOIN ENDERECO_CLIENTE";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setCnpj(rs.getString("cnpj"));
                cliente.setNome(rs.getString("nome"));
                cliente.setDataNasc(rs.getDate("datanasc").toLocalDate());
                cliente.setRg(rs.getString("rg"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setFoto(rs.getString("foto"));
                cliente.getEndereco().setRua(rs.getString("rua"));
                cliente.getEndereco().setBairro(rs.getString("bairro"));
                cliente.getEndereco().setCidade(rs.getString("cidade"));
                cliente.getEndereco().setCep(rs.getString("cep"));
                cliente.getEndereco().setNumero(rs.getString("numero"));
                cliente.getEndereco().setUf(rs.getString("uf"));
                
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente find(String cpf) {
        Cliente cliente = null;
        String sql = "SELECT * FROM CLIENTE  NATURAL JOIN TELEFONE_CLIENTE"
                + " NATURAL JOIN ENDERECO_CLIENTE WHERE CPF ILIKE" + cpf;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setCnpj(rs.getString("cnpj"));
                cliente.setNome(rs.getString("nome"));
                cliente.setDataNasc(rs.getDate("datanasc").toLocalDate());
                cliente.setRg(rs.getString("rg"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setFoto(rs.getString("foto"));
                cliente.getEndereco().setRua(rs.getString("rua"));
                cliente.getEndereco().setBairro(rs.getString("bairro"));
                cliente.getEndereco().setCidade(rs.getString("cidade"));
                cliente.getEndereco().setCep(rs.getString("cep"));
                cliente.getEndereco().setNumero(rs.getString("numero"));
                cliente.getEndereco().setUf(rs.getString("uf"));
                stmt.close();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }

    @Override
    public boolean update(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET CPF = ?, CNPJ = ?, NOME = ?, RG = ?,"
                    + " DATANASC = ?, EMAIL = ?, SENHA = ?, FOTO = ? WHERE CPF ILIKE " + cliente.getCpf() + ";" 
                    + "UPDATE ENDERECO_CLIENTE SET CPF = ?, RUA = ?, BAIRRO = ?,CIDADE = ?,"
                    + "CEP = ?,NUMERO = ?,UF = ? WHERE CPF ILIKE " + cliente.getCpf() + ";"
                    + "UPDATE TELEFONE_CLIENTE SET CPF = ?, TELEFONE  = ? WHERE CPF ILIKE " + cliente.getCpf() + ";";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,cliente.getCpf());
            stmt.setString(2, cliente.getCnpj());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getRg());
            stmt.setDate(5, Date.valueOf(cliente.getDataNasc())); 
            stmt.setString(6, cliente.getEmail());
            stmt.setString(8, cliente.getSenha());
            stmt.setString(9, cliente.getFoto());
            
            stmt.setString(10, cliente.getCpf());
            stmt.setString(11, cliente.getEndereco().getRua());
            stmt.setString(12, cliente.getEndereco().getBairro());
            stmt.setString(13, cliente.getEndereco().getCidade());
            stmt.setString(14, cliente.getEndereco().getCep());
            stmt.setString(15, cliente.getEndereco().getNumero());
            stmt.setString(16, cliente.getEndereco().getUf());
            
            stmt.setString(17, cliente.getCpf());
            stmt.setString(18, cliente.getTelefone());
            
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
