/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import br.com.sistema.jdbc.ConexaoBancoRelatorios;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author henri
 */
public class relClientes {
    ConexaoBancoRelatorios conexao = new ConexaoBancoRelatorios();
    public relClientes() {
        try {
            conexao.conecta();
            conexao.executeSQL("select * from tb_clientes");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(conexao.resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\henri\\OneDrive\\Documentos\\NetBeansProjects\\sistema estoque\\src\\relatorios\\relClientes.jasper", new HashMap(),jrRS);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"erro no meu ireport"+ erro.getMessage());
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
