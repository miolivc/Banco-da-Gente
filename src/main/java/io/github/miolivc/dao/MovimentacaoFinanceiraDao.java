package io.github.miolivc.dao;

import io.github.miolivc.entities.MovimentacaoFinanceira;
import java.util.List;

public interface MovimentacaoFinanceiraDao {
    public void add(MovimentacaoFinanceira movimentacao);
    public void delete(int codigo);
    public List<MovimentacaoFinanceira> list();
    public MovimentacaoFinanceira find(int codigo);
    public void update(MovimentacaoFinanceira movimentacao);
}
