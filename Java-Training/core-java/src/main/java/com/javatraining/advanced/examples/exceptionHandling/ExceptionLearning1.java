package exceptionHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ExceptionLearning1 {

    public void display() throws Exception {
        System.out.println("Inside");
        FileInputStream fileInputStream = null;
        int i = 0;
        Date date = null;
        Connection connection = null;

        try {
            File file = new File("Presentation on java preety.pptx");
            fileInputStream = new FileInputStream(file);
            int count = fileInputStream.read();
            System.out.println(count + i);
            date = new Date();
            System.out.println(date);

            // Connect To DB

            connection = DriverManager.getConnection("");
            Statement statement = connection.createStatement();
            statement.execute("insert into table  LEARNING values()" + 1);

        } catch (IOException exception) {
            throw  new IOException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            try {
                System.out.println("Inside finally");
                fileInputStream.close();
                connection.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }
    }

}
