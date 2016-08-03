package io.github.miolivc.factories;

import io.github.miolivc.dao.AgenciaDao;
import io.github.miolivc.dao.AgenciaDaoDB;
import io.github.miolivc.dao.ClienteDao;
import io.github.miolivc.dao.ClienteDaoDB;
import io.github.miolivc.dao.ContaDao;
import io.github.miolivc.dao.ContaDaoDB;
import io.github.miolivc.dao.FuncionarioDao;
import io.github.miolivc.dao.FuncionarioDaoDB;
import java.sql.SQLException;

public class DaoFactoryDB implements FactoryDaoInterface{

    @Override
    public AgenciaDao createAgenciaDao() throws SQLException, ClassNotFoundException {
        return new AgenciaDaoDB();
    }

    @Override
    public ClienteDao createClienteDao() throws SQLException, ClassNotFoundException {
        return new ClienteDaoDB();
    }

    @Override
    public ContaDao creaContaDao() throws SQLException, ClassNotFoundException {
        return new ContaDaoDB();
    }

    @Override
    public FuncionarioDao createFuncionarioDao() throws SQLException, ClassNotFoundException {
        return new FuncionarioDaoDB();
    }

}
