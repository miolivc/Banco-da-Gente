package io.github.miolivc.dao;

import io.github.miolivc.entities.Conta;
import io.github.miolivc.entities.Cliente;
import java.util.List;

public interface ContaDao {
    public boolean add(Conta conta);
    public boolean delete(int numero);
    public List<Conta> list();
    public List<Cliente> list(Conta conta);
    public Conta find(int numero);
    public boolean update(Conta cliente);
}
