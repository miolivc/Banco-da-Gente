package io.github.miolivc.dao;

import io.github.miolivc.entities.Cliente;
import io.github.miolivc.entities.Conta;
import io.github.miolivc.factories.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ContaDaoDB implements ContaDao{
    private static Connection conn;
    
    public ContaDaoDB() throws SQLException, ClassNotFoundException{
        conn = ConnectionFactory.getConnection();
    }

    @Override
    public boolean add(Conta conta) {
        String sql = "INSERT INTO CONTA(COD,SALDO,DATAOPEN,CODAG) VALUES (?,?,?,?);";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, conta.getNumero());
            stmt.setDouble(2, Double.parseDouble(conta.getSaldo().toString()));
            stmt.setDate(3, Date.valueOf(conta.getDataAbertura())); 
            stmt.setInt(4, conta.getAgencia().getCodigo());
            stmt.executeUpdate();
            stmt.close();
            sql = "INSERT INTO CONTA_BANCO(CODCLIENTE,CONTA) VALUES(?,?)";
            stmt = conn.prepareStatement(sql);
            for(Cliente titular : conta.getTitulares()){
                stmt.setString(1, titular.getCpf());
                stmt.setInt(2, conta.getNumero());
                stmt.executeUpdate();
            }
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int numero) {
        String sql = "DELETE FROM CONTA_BANCO WHERE NUMERO = " + numero + ";"
                + "DELETE FROM CONTA WHERE NUMERO = " + numero + ";";
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
    public List<Conta> list() {
        List<Conta> contas = null;
        String sql = "SELECT * FROM CONTA";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Conta conta = new Conta();
                conta.setNumero(rs.getInt("cod"));
                conta.setDataAbertura(rs.getDate("datopen").toLocalDate());
                conta.setSaldo(rs.getBigDecimal("saldo"));
                AgenciaDaoDB daoAgencia = new AgenciaDaoDB();
                conta.setAgencia(daoAgencia.find(rs.getInt("codigo")));
                conta.setTitulares(this.list(conta));
                contas.add(conta);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return contas;
    }
    
    @Override
    public List<Cliente> list(Conta conta) {
        List<Cliente> clientes = null;
        String sql = "SELECT * FROM CONTA_BANCO JOIN CLIENTE WHERE "
                + "(CODCLIENTE ILIKE CPF) AND (CONTA = " + conta.getNumero() + ")";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Cliente> listaCliente = new ClienteDaoDB().list();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                for(Cliente titular : listaCliente){
                    if(cliente.getCpf().equals(titular.getCpf())){
                        clientes.add(titular);
                    }
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Conta find(int numero) {
        Conta conta = null;
        String sql = "SELECT * FROM CONTA WHERE NUMERO = " + numero + ";";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                conta.setNumero(rs.getInt("cod"));
                conta.setDataAbertura(rs.getDate("dataopen").toLocalDate());
                conta.setSaldo(rs.getBigDecimal("saldo"));
                AgenciaDaoDB daoAgencia = new AgenciaDaoDB();
                conta.setAgencia(daoAgencia.find(rs.getInt("codigo")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conta;
    }

    @Override
    public boolean update(Conta conta) {
        String sql = "UPDATE CONTA SET COD = ?, DATAOPEN = ?, SALDO = ?, CODAG = ? WHERE COD = " + conta.getNumero() + ";";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, conta.getNumero());
            stmt.setDate(2, Date.valueOf(conta.getDataAbertura()));
            stmt.setBigDecimal(3, conta.getSaldo());
            stmt.setInt(4, conta.getAgencia().getCodigo());
            stmt.executeUpdate();
            sql = "UPDATE CONTA_BANCO SET CODCLIENTE = ?, CONTA = ? WHERE CONTA = " + conta.getNumero() + ";";
            stmt = conn.prepareStatement(sql);
            for(Cliente titular : conta.getTitulares()){
                stmt.setString(1, titular.getCpf());
                stmt.setInt(2, conta.getNumero());
                stmt.executeUpdate();
            }
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
