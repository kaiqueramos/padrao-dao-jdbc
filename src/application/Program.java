package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
    public static void main(String[] args) {
        SellerDao sl = DaoFactory.createSellerDao();

        Seller seller = sl.findById(3);

        System.out.println(seller);
    }
}
