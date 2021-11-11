package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;
    private SingletonConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                //load driver và đăng kí nó với ứng dụng (Đăng ký gọi phương thức Class.forName("driverName")
                Class.forName("com.mysql.jdbc.Driver");
                //tạo kết nối (connection)
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product?useSSL=false", "root", "1234567890");
                System.out.println("success");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
            return connection;
    }
}
