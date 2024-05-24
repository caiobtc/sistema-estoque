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
 * @author teste
 */
public class relFuncionarios {
    ConexaoBancoRelatorios conexao = new ConexaoBancoRelatorios();
    public relFuncionarios() {
        try {
            conexao.conecta();
            conexao.executeSQL("select id, nome, concat(endereco,\", \",numero,\", \",bairro,\", \",cidade,\"-\",estado) as endereco from tb_funcionarios");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(conexao.resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\henri\\OneDrive\\Documentos\\NetBeansProjects\\sistema estoque\\src\\relatorios\\relFuncionarios.jasper", new HashMap(),jrRS);
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
