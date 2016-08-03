package io.github.miolivc.dao;

import io.github.miolivc.entities.Cliente;
import java.util.List;

public interface ClienteDao {
    public boolean add(Cliente cliente);
    public boolean delete(String cpf);
    public List<Cliente> list();
    public Cliente find(String cpf);
    public boolean update(Cliente cliente);
}