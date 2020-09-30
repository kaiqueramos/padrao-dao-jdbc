package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name AS depName " +
                            "FROM seller INNER JOIN department " +
                            "ON seller.DepartmentId = department.Id " +
                            "WHERE seller.Id = ?");
            st.setInt(1, id);
            System.out.println();
            rs = st.executeQuery();
            if(rs.next()){
                Department dp = instantiateDepartment(rs);
                Seller sl = instantiateSeller(rs, dp);
                return sl;
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }

    private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException{
        Seller sl = new Seller();
        sl.setId(rs.getInt("Id"));
        sl.setName(rs.getString("Name"));
        sl.setEmail(rs.getString("Email"));
        sl.setBirthDate(rs.getDate("BirthDate"));
        sl.setBaseSalary(rs.getDouble("BaseSalary"));
        sl.setDepartment(dp);
        return sl;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dp = new Department();
        dp.setId(rs.getInt("DepartmentId"));
        dp.setName(rs.getString("DepName"));
        return dp;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
