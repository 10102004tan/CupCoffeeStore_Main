/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.DriverManager
;

import com.mysql.jdbc.Connection;

/**
 *
 * @author Administrator
 */
public class DataBaseUtil {
    //khoi tao connection
    public static Connection getConnection()
    {
        Connection con = null;
        //try cacth
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/cup_coffee_store_db?useUnicode=yes&characterEncoding=UTF-8","root","");
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return con;
    }
    

    

            
}
