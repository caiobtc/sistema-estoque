/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author henri
 */
public class ConexaoBancoRelatorios {
   final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost:3306/sistemaestoque";
    final private String usuario = "root";
    final private String senha = "";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultSet;
    
    public boolean conecta(){
    boolean resultado = true;
        try {
           Class.forName(driver);
           conexao = DriverManager.getConnection(url,usuario,senha);
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não encontrado!"+Driver);
            resultado = false;
        }catch(SQLException fonte){
            JOptionPane.showMessageDialog(null, "erro na minha fonte de dados!"+fonte.getMessage());
           resultado = false;
        }
        return resultado;
    }
    public void desconecta(){
    boolean resultado = true;
        try {
            conexao.close();
            JOptionPane.showMessageDialog(null, "banco fechado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro ao desconectar com o banco de dados!");
            resultado = false;
        }
        
    }
    public void executeSQL(String sql){
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro de sql"+e.getMessage());
        }
    }
    
}
