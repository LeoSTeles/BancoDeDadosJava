/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import view.LoginView;
import view.MenuView;

/**
 *
 * @author leona
 */
public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        //Buscar um usuário da view
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordField().getText();
        
        Usuario usuarioBusca = new Usuario(usuario,senha);
        
        
        //Verificar se o usuário existe no banco de dados
        Connection conexao = new Conexao().getConecction();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        
        boolean existe = usuarioDAO.verificarSeExiste(usuarioBusca);
        //Caso exista, redirecionar para o menu
        if(existe){
            MenuView telaDeMenu = new MenuView();
            telaDeMenu.setVisible(true);
        }
        //Senão, exiba um erro na tela
        else{
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
        }
    }
}
