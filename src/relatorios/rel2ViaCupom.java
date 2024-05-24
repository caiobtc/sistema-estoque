/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import br.com.sistema.jdbc.ConexaoBanco;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author teste
 */
public class rel2ViaCupom {
 private Connection con;

    public rel2ViaCupom(int id) {
    this.con = new ConexaoBanco().pegarConexao();
    
     try {
            String jasper = "C:\\Users\\henri\\OneDrive\\Documentos\\NetBeansProjects\\sistema estoque\\src\\relatorios\\rel2viaCupom.jasper";
//            Connection conexao = null;
           
//            Map<String, Object> param = new HashMap<>();
//            param.put("id",new Integer(id));
          Map<String, Object> param = new HashMap<>();
            param.put("id",id);
          

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, param,con);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
            jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"deu erro"+ e);
        }
    }
        

 
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
