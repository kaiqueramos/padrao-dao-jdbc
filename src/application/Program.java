package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sl = DaoFactory.createSellerDao();
        DepartmentDao dp = DaoFactory.createDepartmentDao();
        //As operações estão disponíveis a partir dos objetos acima.
    }
}
