package com.codegym.dao;

import com.codegym.model.Product;
import config.SingletonConnection;

import javax.servlet.RequestDispatcher;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/product?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234567890";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products (name, description, producer, price) VALUE (?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT id, name, description, producer, price from products where id = ?";
    private static final String SELECT_ALL_PRODUCT = "SELECT * from products;";
    private static final String DELETE_PRODUCT_SQL = "DELETE from products WHERE id = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "UPDATE products set name = ?, description = ?, producer = ?, price = ? WHERE id = ?;";
    private static final String SELECT_PRODUCT_BY_PRODUCER = "SELECT * from products where producer = ?;";
private static final String SELECT_PRODUCT_BY_DESCRIPTION = "SELECT * from products where description = ?;";
private static final String SORT_BY_NAME = "SELECT * from products order by name;";

    public ProductDAO() {
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            //load driver và đăng kí nó với ứng dụng (Đăng ký gọi phương thức Class.forName("driverName")
            Class.forName("com.mysql.jdbc.Driver");
            //tạo kết nối (connection)
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getProducer());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        try  {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String producer = rs.getString("producer");
                double price = rs.getDouble("price");
                product = new Product(id,name, description, producer, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> productList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String producer  = rs.getString("producer");
                double price = rs.getDouble("price");
                productList.add(new Product(id, name, description, producer, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    @Override
    public boolean deleteProduct(int id)  {
        boolean rowDelete = false;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }

    @Override
    public boolean updateProduct(Product product)  {
        boolean rowUpdate = false;

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCTS_SQL);
            ps.setInt(5, product.getId());
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getProducer());
            ps.setDouble(4, product.getPrice());
            rowUpdate = ps.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdate;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @Override
    public List<Product> selectProductByProducer(String producer) {
        List<Product> productList =  new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_PRODUCER);
            preparedStatement.setString(1, producer);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                productList.add(new Product(id, name, description, producer, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }
   @Override
    public List<Product> selectProductByDescription(String description) {
        List<Product> productList =  new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_DESCRIPTION);
            preparedStatement.setString(1, description);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                description = rs.getString("description");
                String producer = rs.getString("producer");
                double price = rs.getDouble("price");
                productList.add(new Product(id, name, description, producer, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> sortByName() {
        List<Product> productList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SORT_BY_NAME);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String producer = rs.getString("producer");
                double price = rs.getDouble("price");
                productList.add(new Product(id, name, description, producer, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }
}
