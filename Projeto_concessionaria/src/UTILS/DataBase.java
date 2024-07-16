/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    public Connection connection = null;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String dbName = "projeto_concessionaria"; //insert database name
    private final String local = "jdbc:mysql://localhost:3306/"+dbName;
    private final String login = "root"; //insert login name
    // private final String password = "root"; //insert login password
    private final String password = "zyhua1U#oyox"; //insert login password
    
    public boolean getConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(local, login, password);
            System.out.println("Data base founded!");
            return true;
        }catch(ClassNotFoundException notFoundError){
            System.out.println("Driver not found: "+notFoundError.toString());
            return false;
        }catch(SQLException error){
            System.out.println("Error to connect in data base: "+error.toString());
            return false;
        }
    }
}
