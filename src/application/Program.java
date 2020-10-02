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

        Department dep = new Department();
        dep.setName("Foods and grocery");
        dep.setId(10);

        List<Department> list = dp.findAll();
        list.forEach(System.out::println);
    }
}
