package io.github.miolivc.factories;

public class DaoFactory {
    public static final int DAO_BD = 0;
    
    public static FactoryDaoInterface createFactory(int factoryType){
        if (factoryType == DAO_BD){
            return new DaoFactoryDB();
        }
        return null;
    }
}
