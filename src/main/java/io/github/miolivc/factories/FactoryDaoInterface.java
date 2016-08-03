package io.github.miolivc.factories;

import io.github.miolivc.dao.AgenciaDao;
import io.github.miolivc.dao.ClienteDao;
import io.github.miolivc.dao.ContaDao;
import io.github.miolivc.dao.FuncionarioDao;
import java.sql.SQLException;

public interface FactoryDaoInterface {
    
    public AgenciaDao createAgenciaDao() throws SQLException, ClassNotFoundException;
    public ClienteDao createClienteDao() throws SQLException, ClassNotFoundException;
    public ContaDao creaContaDao() throws SQLException, ClassNotFoundException;
    public FuncionarioDao createFuncionarioDao() throws SQLException, ClassNotFoundException;
//    public MovimentacaoFinanceiraDao createMovimentacaoFinanceiraDao() throws SQLException, ClassNotFoundException;
    
}
