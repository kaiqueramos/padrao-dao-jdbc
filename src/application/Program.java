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

        Seller sel1 = sl.findById(9);

        sel1.setName("Teste Testado");

        System.out.println(sel1);

        sl.update(sel1);
        /*Department dp = new Department(2, null);
        Seller sel = new Seller();
        sel.setName("Jhon Yellow");
        sel.setEmail("jhonyell@gmail.com");
        sel.setBirthDate(new Date());
        sel.setBaseSalary(4400.00);
        sel.setDepartment(dp);

        sl.insert(sel);*/
    }
}
