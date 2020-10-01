package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sl = DaoFactory.createSellerDao();


        Department dp = new Department(2, null);
        Seller sel = new Seller();
        sel.setName("Jhon Yellow");
        sel.setEmail("jhonyell@gmail.com");
        sel.setBirthDate(new Date());
        sel.setBaseSalary(4400.00);
        sel.setDepartment(dp);

        sl.insert(sel);
    }
}
