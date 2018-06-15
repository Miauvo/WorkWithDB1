package com.TestBD;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Calendar;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/testshema2";
    private static final String USER = "root";
    private static final String PASSWORD = "root1";

    private static final String INSERT_NEW = "insert into users values(?,?,?,?,?)";
    private static final String GET_ALL = "SELECT*FROM users";



    public static void main(String[] args) throws SQLException, Exception {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement=connection.prepareStatement(INSERT_NEW);
        if (!connection.isClosed()) {
            System.out.println("Соединение с БД Установлено!");
        }

        System.out.println("sendData");

        preparedStatement.setInt(1,321);
        preparedStatement.setInt(3,100);
        preparedStatement.setString(2,"names");
        preparedStatement.setString(4,"emails");
        preparedStatement.setBlob(5, new FileInputStream("q2.jpeg"));

        preparedStatement.execute();

        preparedStatement = connection.prepareStatement(GET_ALL);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Users user = new Users();
            user.setId(resultSet.getInt("id"));
            user.setAge(resultSet.getInt("Age"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setImg(resultSet.getBlob("iconsint"));

            System.out.println(user);
        }



        //new Date(Calendar.getInstance().getTimeInMillis());



      /*  String query = null;
        for (int i = 0; i < 10; i++) {
            query = "insert into users(name,age,email) values('Anna',20,'Aglaophot@gmail.com')";
            statement.addBatch(query);
        }

        query = "SELECT * FROM testshema2.users";

        statement.executeBatch();
        statement.clearBatch();
        //  statement.execute(query);
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Users user = new Users();
            user.setId(resultSet.getInt("id"));
            user.setAge(resultSet.getInt("Age"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));

            System.out.println(user);
        }
*/
        statement.close();
        connection.close();
        if (connection.isClosed()) {
            System.out.println("Соединение с БД Закрыто!");
        }


    }
}
