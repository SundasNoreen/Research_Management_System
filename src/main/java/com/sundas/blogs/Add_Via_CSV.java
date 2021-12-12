package com.sundas.blogs;

import java.sql.*;
import java.io.*;

public class Add_Via_CSV

{
    // By AQSA AYAZ
    public static int insert(String path)
    {
        boolean flag=false;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "KchBhi@naam";
        String password = "Daal1234";
        int count = 0;
        int rv=0;
        String filePath=path;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            String sql = "insert into students(Reg_No,Name, Degree, Class, Field, FatherName,CNIC, DOB, ContactNumber, Email, LoginId ,Password,Gender) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String Reg_No = data[0];
                String Name = data[1];
                String Degree = data[2];
                String Class = data[3];
                String Field = data[4];
                String FatherName = data[5];
                String CNIC = data[6];
                String DOB = data[7];
                String ContactNumber = data[8];
                String Email = data[9];
                String LoginId = data[10];
                String Password = data[11];
                String Gender = data[12];

                statement.setString(1, Reg_No);
                statement.setString(2, Name);
                statement.setString(3, Degree);
                statement.setString(4, Class);
                statement.setString(5, Field);
                statement.setString(6, FatherName);
                statement.setString(7, CNIC);
                statement.setString(8, DOB);
                statement.setString(9, ContactNumber);
                statement.setString(10, Email);
                statement.setString(11, LoginId);
                statement.setString(12, Password);
                statement.setString(13, Gender);
                statement.addBatch();
                count++;
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data has been inserted successfully.");
            flag=true;
            if(flag) {rv=count;}
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    return rv;
        }
}


