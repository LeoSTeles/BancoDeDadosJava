/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            String sql = "INSERT INTO usuarios(usuario,senha) VALUES ('"+ usuario.getUsuario() +"','"+ usuario.getSenha() +"')";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
              connection.close();
    }
}
