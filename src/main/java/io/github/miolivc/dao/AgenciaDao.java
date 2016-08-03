package io.github.miolivc.dao;

import io.github.miolivc.entities.Agencia;
import java.util.List;

public interface AgenciaDao {
    public boolean add(Agencia agencia);
    public boolean delete(int codigo);
    public List<Agencia> list();
    public Agencia find(String name);
    public Agencia find(int codigo);
    public boolean update(Agencia agencia);
}
