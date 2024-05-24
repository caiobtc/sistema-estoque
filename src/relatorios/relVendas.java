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
public class relVendas {
    ConexaoBancoRelatorios conexao = new ConexaoBancoRelatorios();
    public relVendas() {
        try {
            conexao.conecta();
            conexao.executeSQL("select i.venda_id,p.descricao,p.preco,i.qtd,i.subtotal from tb_itensvendas as i inner join tb_produtos as p on(i.produto_id=p.id) where venda_id =(select max(id) from tb_vendas)");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(conexao.resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\henri\\OneDrive\\Documentos\\NetBeansProjects\\sistema estoque\\src\\relatorios\\relVendas.jasper", new HashMap(),jrRS);
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
