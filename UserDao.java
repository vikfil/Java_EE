package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements WriteAndReadUser {
    private static final String URL = "jdbc:mysql://localhost:3306/java_ee_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "6G2GfyWMF-6";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,user,password);
        }catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    @Override
    public void write(User user) {
            try {
                Connection connection = UserDao.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into user(name,password,email,country) values (?,?,?,?)");
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getCountry());

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }catch (Exception ex) {ex.printStackTrace();}

    }

    @Override
    public  List<User> readUser() {
        List<User> list = new ArrayList<>();
        try {
            Connection connection = UserDao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from  user");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User e = new User();
                e.setId(resultSet.getInt(1));
                e.setName(resultSet.getString(2));
                e.setPassword(resultSet.getString(3));
                e.setEmail(resultSet.getString(4));
                e.setCountry(resultSet.getString(5));
                list.add(e);
            }
            connection.close();
        }catch (Exception e) {e.printStackTrace();}
        return list;
    }
}
