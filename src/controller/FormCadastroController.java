/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import view.FormCadastroView;

/**
 *
 * @author leona
 */
public class FormCadastroController {
    private FormCadastroView view;

    public FormCadastroController(FormCadastroView view) {
        this.view = view;
    }
    
    public void salvarUsuario(){
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordField().getText();
        Usuario usuarioInsert = new Usuario(usuario, senha);
        try {
            Connection conexao = new Conexao().getConecction();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            usuarioDAO.insert(usuarioInsert);
            
            JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(FormCadastroView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
