package io.github.miolivc.manager;

import java.sql.SQLException;
import java.util.List;

import io.github.miolivc.dao.AgenciaDao;
import io.github.miolivc.entities.Agencia;
import io.github.miolivc.entities.Endereco;
import io.github.miolivc.factories.DaoFactory;
import io.github.miolivc.factories.FactoryDaoInterface;

public class AgenciaManager {
    FactoryDaoInterface factory = null;
    AgenciaDao agenciaDao = null;
    
    public AgenciaManager() {
        factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        try {
            agenciaDao = factory.createAgenciaDao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean add(int codigo, String nome, String telefone, String rua,
    		String bairro, String cidade, String cep, String uf, String numero){
        boolean sucess = false;
        Agencia agencia = new Agencia();
        agencia.setCodigo(codigo);
        agencia.setNome(nome);
        agencia.setTelefone(telefone);
        
        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setCep(cep);
        endereco.setUf(uf);
        endereco.setNumero(numero);

        agencia.setEndereco(endereco);
        
        sucess = agenciaDao.add(agencia);
        
        return sucess;
    }
    
    public boolean delete(int codigo){
        boolean sucess = false;
        sucess = agenciaDao.delete(codigo);
        return sucess; 
    }
    
    public List<Agencia> list(){
        List<Agencia> agencias = agenciaDao.list();
        return agencias;
    }
    
    public Agencia find(int codigo){
    	Agencia agencia = agenciaDao.find(codigo);
        return agencia;
    }

    public boolean update(int codigo, String nome, String telefone, String rua,
    		String bairro, String cidade, String cep, String uf, String numero){
        boolean sucess = false;
        Agencia agencia = new Agencia();
        agencia.setCodigo(codigo);
        agencia.setNome(nome);
        agencia.setTelefone(telefone);
        
        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setCep(cep);
        endereco.setUf(uf);
        endereco.setNumero(numero);

        agencia.setEndereco(endereco);
        
        sucess = agenciaDao.update(agencia);
        return sucess;
    }

}
