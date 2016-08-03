package io.github.miolivc.manager;

import io.github.miolivc.dao.ClienteDao;
import java.sql.SQLException;
import io.github.miolivc.factories.FactoryDaoInterface;

public class ClientManager {
    FactoryDaoInterface factory;
    ClienteDao clienteDao;
    
    public ClientManager() throws SQLException, ClassNotFoundException{
        factory.createClienteDao();
        
    }
    
}
