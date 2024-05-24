package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.ItensVendas;
import br.com.sistema.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe representa o DAO (Data Access Object) para manipulação dos itens de vendas no banco de dados.
 */

/**
 *
 * @author henri
 */
public class ItensVendasDAO {

    private Connection conn;
    
    /**
     * Construtor padrão que inicializa a conexão com o banco de dados.
     */

    public ItensVendasDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }
    
    /**
     * Salva um item de venda no banco de dados.
     * 
     * @param obj O objeto representando o item de venda a ser salvo.
     * @throws RuntimeException Se ocorrer um erro ao salvar os itens da venda.
     */

    public void salvar(ItensVendas obj) {
        try {
            String sql = "insert into tb_itensvendas (venda_id,produto_id,qtd,subtotal) values(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getVendas().getId());
            stmt.setInt(2, obj.getProdutos().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException("erro ao salvar os itens da minha venda!");
        }

    }
    
    /**
     * Lista os itens de uma venda específica.
     * 
     * @param venda_id O ID da venda para a qual se deseja obter os itens.
     * @return Uma lista de itens de venda.
     * @throws RuntimeException Se ocorrer um erro ao criar a lista de itens.
     */
    
  public List<ItensVendas>listaItens(int venda_id){
      try {
          List<ItensVendas>lista = new ArrayList<>();
          String sql = "select p.id,p.descricao,i.qtd,p.preco,i.subtotal from tb_itensvendas as i inner "
                  + "join tb_produtos as p on(i.produto_id=p.id) where i.venda_id=?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1, venda_id);
          ResultSet rs = stmt.executeQuery();
          while(rs.next()){
              ItensVendas item = new ItensVendas();
              Produtos p = new Produtos();
              p.setId(rs.getInt("p.id"));
              item.setProdutos(p);
              p.setDescricao(rs.getString("p.descricao"));
              item.setProdutos(p);
              item.setQtd(rs.getInt("i.qtd"));
              p.setPreco(rs.getDouble("p.preco"));
              item.setProdutos(p);
              item.setSubtotal(rs.getInt("i.subtotal"));
              lista.add(item);
          }
          return lista;
      } catch (SQLException e) {
          throw new RuntimeException("erro ao criar a lista de itens!"+e);
      }
  
  }
}
