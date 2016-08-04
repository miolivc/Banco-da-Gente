package io.github.miolivc.manager;

import io.github.miolivc.dao.AgenciaDao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import io.github.miolivc.dao.FuncionarioDao;
import io.github.miolivc.entities.Agencia;
import io.github.miolivc.entities.Endereco;
import io.github.miolivc.entities.Funcionario;
import io.github.miolivc.factories.DaoFactory;
import io.github.miolivc.factories.FactoryDaoInterface;

public class FuncionarioManager {
    FactoryDaoInterface factory = null;
    FuncionarioDao funcionarioDao = null;
    AgenciaDao agenciaDao = null;
    
    public FuncionarioManager() {
        factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        try {
            funcionarioDao = factory.createFuncionarioDao();
            agenciaDao = factory.createAgenciaDao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean add(String cpf, String telefone, String nome, String rg, LocalDate dataNasc, String email,
            String senha,String rua, String bairro, String cidade, String cep, String uf, String numero, 
            String tipo, int codigo){
        boolean sucess = false;
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(cpf);
        funcionario.setTelefone(telefone);
        funcionario.setNome(nome);
        funcionario.setRg(rg);
        funcionario.setDataNasc(dataNasc);
        funcionario.setEmail(email);
        funcionario.setSenha(senha);
        funcionario.setTipo(tipo);
        
        Agencia agencia = agenciaDao.find(cpf);
        funcionario.setAgencia(agencia);
        
        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setCep(cep);
        endereco.setUf(uf);
        endereco.setNumero(numero);

        funcionario.setEndereco(endereco);
        
        sucess = funcionarioDao.add(funcionario);
        
        return sucess;
    }
    
    public boolean delete(String cpf){
        boolean sucess = false;
        sucess = funcionarioDao.delete(cpf);
        return sucess; 
    }
    
    public List<Funcionario> list(){
        List<Funcionario> funcionarios = funcionarioDao.list();
        return funcionarios;
    }
    
    public Funcionario find(String cpf){
        Funcionario funcionario = funcionarioDao.find(cpf);
        return funcionario;
    }

    public boolean update(String cpf, String telefone, String nome, String rg, LocalDate dataNasc, String email,
            String senha,String rua, String bairro, String cidade, String cep, String uf, String numero, String tipo, int codigo){
        boolean sucess = false;
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(cpf);
        funcionario.setTelefone(telefone);
        funcionario.setNome(nome);
        funcionario.setRg(rg);
        funcionario.setDataNasc(dataNasc);
        funcionario.setEmail(email);
        funcionario.setSenha(senha);
        funcionario.setTipo(tipo);
        
        Agencia agencia = new Agencia();
        agencia.setCodigo(codigo);
        
        funcionario.setAgencia(agencia); 
        
        funcionario.setAgencia(agencia);
        
        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setCep(cep);
        endereco.setUf(uf);
        endereco.setNumero(numero);
        
        funcionario.setEndereco(endereco);
        
        sucess = funcionarioDao.update(funcionario);
        return sucess;
    }

}
