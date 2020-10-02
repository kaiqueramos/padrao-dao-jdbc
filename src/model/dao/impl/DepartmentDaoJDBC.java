package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO department " +
                            "(Name) " +
                            "VALUES " +
                            "(?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            int rows = st.executeUpdate();
            if(rows > 0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                    System.out.println("Id do registro inserido: " + id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Erro! Nenhuma linha foi modificada.");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE department " +
                            "SET Name = ? " +
                            "WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            int rows = st.executeUpdate();
            if (rows > 0){
                System.out.println("O departamento de id " + obj.getId() + " foi atualizado.");
            }else{
                throw new DbException("Erro! Nenhuma linha foi alterada.");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "DELETE FROM department " +
                            "WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            int rows = st.executeUpdate();
            if(rows > 0){
                System.out.println("O departamento de id " + id + " foi deletado.");
            }else{
                throw new DbException("Erro! Nenhuma linha foi alterada.");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department " +
                            "WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Department dp = instantiateDepartment(rs);
                return dp;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            List<Department> list = new ArrayList<>();
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();
            while (rs.next()){
                Department dp = new Department(rs.getInt("Id"), rs.getString("Name"));
                list.add(dp);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dp = new Department();
        dp.setId(rs.getInt("Id"));
        dp.setName(rs.getString("Name"));
        return dp;
    }
}
