package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sl = DaoFactory.createSellerDao();

        Seller seller = sl.findById(3);

        Department dp = new Department(2, null);

        List<Seller> list = sl.findAll();

        list.forEach(System.out::println);

        //System.out.println(seller);
    }
}
