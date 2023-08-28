/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    
    public void update(Usuario usuario) throws SQLException{
        String sql = "UPDATE INTO usuarios set usuario = ? and senha = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        //Evitando Sql Injection
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getSenha());
        statement.setInt(3, usuario.getId());
        statement.execute();
    }
    
    public void insertOrUpdate(Usuario usuario) throws SQLException{
        if(usuario.getId() > 0){
            this.update(usuario);
        }else{
            this.insert(usuario);
        }
    }
    
    public void delete(Usuario usuario)throws SQLException{
        if(usuario.getId() > 0){
            String sql = "DELETE FROM usuarios where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            //Evitando Sql Injection
            statement.setInt(1, usuario.getId());
            statement.execute();
        }else{
            JOptionPane.showMessageDialog(null, "O usuário não existe, portanto não pode ser deletado.");
        }
    }
    
    public ArrayList<Usuario> selectAll() throws SQLException{
        String sql = "SELECT * FROM usuarios";
        PreparedStatement statement = connection.prepareStatement(sql);
        return pesquisa(statement);
    }

    private ArrayList<Usuario> pesquisa(PreparedStatement statement) throws SQLException {
        //Criando array list de usuarios
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        statement.execute();
        //Recebendo uma lista com todos os usuarios encontrados no banco
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String usuario = resultSet.getString("usuario");
            String senha = resultSet.getString("senha");
            //Criando variável tipo usuário para armazenar dados da linha atual retornada do banco
            Usuario user = new Usuario(id, usuario, senha);
            //Armazenando o usuário da linha atual no array list criado
            usuarios.add(user);
        }
        return usuarios;
    }
    
    public Usuario selectById(Usuario usuario) throws SQLException{
        String sql = "SELECT * FROM usuarios where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        statement.execute();

        return pesquisa(statement).get(0);
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
