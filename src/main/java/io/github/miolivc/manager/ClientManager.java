package io.github.miolivc.manager;

import io.github.miolivc.dao.ClienteDao;
import io.github.miolivc.entities.Cliente;
import io.github.miolivc.entities.Endereco;
import io.github.miolivc.factories.DaoFactory;
import io.github.miolivc.factories.FactoryDaoInterface;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ClientManager {
    FactoryDaoInterface factory = null;
    ClienteDao clienteDao = null;
    
    public ClientManager() {
        factory = DaoFactory.createFactory(DaoFactory.DAO_BD);
        try {
            clienteDao = factory.createClienteDao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean add(String cpf, String telefone, String nome, String rg, LocalDate dataNasc, String email,
            String senha,String rua, String bairro, String cidade, String cep, String uf, String numero, String cnpj, String foto){
        boolean sucess = false;
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setNome(nome);
        cliente.setRg(rg);
        cliente.setDataNasc(dataNasc);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        cliente.setCnpj(cnpj);
        cliente.setFoto(foto);
        
        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setCep(cep);
        endereco.setUf(uf);
        endereco.setNumero(numero);

        cliente.setEndereco(endereco);
        
        sucess = clienteDao.add(cliente);
        
        return sucess;
    }
    
    public boolean delete(String cpf){
        boolean sucess = false;
        sucess = clienteDao.delete(cpf);
        return sucess; 
    }
    
    public List<Cliente> list(){
        List<Cliente> clientes = clienteDao.list();
        return clientes;
    }
    
    public Cliente find(String cpf){
        Cliente cliente = clienteDao.find(cpf);
        return cliente;
    }

    public boolean update(String cpf, String telefone, String nome, String rg, LocalDate dataNasc, String email,
            String senha,String rua, String bairro, String cidade, String cep, String uf, String numero, String cnpj, String foto){
        boolean sucess = false;
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setNome(nome);
        cliente.setRg(rg);
        cliente.setDataNasc(dataNasc);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        cliente.setCnpj(cnpj);
        cliente.setFoto(foto);
        
        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setCep(cep);
        endereco.setUf(uf);
        endereco.setNumero(numero);
        
        cliente.setEndereco(endereco);
        
        sucess = clienteDao.update(cliente);
        return sucess;
    }

}
