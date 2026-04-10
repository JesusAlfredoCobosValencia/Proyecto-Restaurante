/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;


import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/restaurante";
    private static final String USER = "restaurante";
    private static final String PASSWORD = "";
    
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    
}
