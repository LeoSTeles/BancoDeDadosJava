/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.postgresql.Driver;
/**
 *
 * @author leona
 */
public class Conexao {
    public Connection getConecction() throws SQLException{
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cursojava", "postgres", "admin");
        return conexao;
    }
}
