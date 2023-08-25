/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import view.FormCadastroView;

/**
 *
 * @author leona
 */
public class UsuarioDAO {
    private final Connection connection;    

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Usuario usuario) throws SQLException{
            String sql = "INSERT INTO usuarios(usuario,senha) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            //Evitando Sql Injection
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.execute();
    }

    public boolean verificarSeExiste(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? and senha = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        //Evitando Sql Injection
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }



}
