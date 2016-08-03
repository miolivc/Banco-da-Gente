package io.github.miolivc.dao;

import io.github.miolivc.entities.Funcionario;
import java.util.List;

public interface FuncionarioDao {
    public boolean add(Funcionario funcionario);
    public boolean delete(String cpf);
    public List<Funcionario> list();
    public Funcionario find(String cpf);
    public boolean update(Funcionario funcionario);
}
