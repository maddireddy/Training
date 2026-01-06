package ioPackage;

import java.sql.*;

public class DBConnection {

    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(Constat.url,"","");

            // Tightly coupling the External params inside your javas programs

            Statement statement = connection.prepareStatement(Constat.employeeQuery);
            //
            ResultSet resultSet = statement.getResultSet();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
