package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Clientes;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe responsável por operações de acesso a dados relacionadas aos Produtos.
 */

/**
 *
 * @author henri
 */
public class ProdutosDAO {

    private Connection conn;
    
    /**
     * Construtor padrão que inicializa a conexão com o banco de dados.
     */

    public ProdutosDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }
    
    /**
     * Método para salvar um produto no banco de dados.
     * 
     * @param obj O objeto Produto a ser salvo.
     */

    public void Salvar(Produtos obj) {
        try {
            //1º criar o SQL
            String sql = "insert into tb_produtos (descricao,preco,qtd_estoque,for_id)"
                    + "values(?,?,?,?)";
            //2º preparar conexao SQL para se conectar com o banco
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedores().getId());

            //3º executar sql
            stmt.execute();
            //4º fechar conexão
            stmt.close();
            JOptionPane.showMessageDialog(null, "produto salvo com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro ao salvar o produto!" + erro);
        }
    }
    
    /**
     * Método para editar um produto no banco de dados.
     * 
     * @param obj O objeto Produto a ser editado.
     */

    public void Editar(Produtos obj) {
        try {
            //1º criar o SQL
            String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? where id=?";
            //2º preparar conexao SQL para se conectar com o banco
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedores().getId());
            stmt.setInt(5, obj.getId());

            //3º executar sql
            stmt.execute();
            //4º fechar conexão
            stmt.close();
            JOptionPane.showMessageDialog(null, "produto editado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro ao editar o produto!" + erro);
        }
    }
    
    /**
     * Método para excluir um produto do banco de dados.
     * 
     * @param obj O objeto Produto a ser excluído.
     */

    public void Excluir(Produtos obj) {
        try {
            String sql = "delete from tb_produtos where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "produto excluído com sucesso");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao excluir o produto!" + e);
        }
    }
    
    /**
     * Método para buscar um produto pelo nome.
     * 
     * @param nome O nome do produto a ser buscado.
     * @return O objeto Produto encontrado, ou null se não encontrado.
     */

    public Produtos BuscarProdutos(String nome) {
        try {
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join "
                    + "tb_fornecedores as f on(p.for_id=f.id) where p.descricao = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();
            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);

            }
            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro ao buscar o produto!" + erro);
        }
        return null;
    }
    
    /**
     * Método para buscar um produto pelo código.
     * 
     * @param id O código do produto a ser buscado.
     * @return O objeto Produto encontrado, ou null se não encontrado.
     */
    
     public Produtos BuscarProdutosCodigo(int id) {
        try {
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join "
                    + "tb_fornecedores as f on(p.for_id=f.id) where p.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();
            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);

            }
            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro ao buscar o produto!" + erro);
        }
        return null;
    }
     
    /**
     * Método para listar todos os produtos.
     * 
     * @return Uma lista contendo todos os produtos.
     */ 

    public List<Produtos> Listar() {
        List<Produtos> lista = new ArrayList<>();
        try {
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);
                lista.add(obj);

            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao criar a lista: " + e);
        }
        return null;
    }
    
    /**
     * Método para filtrar produtos pelo nome.
     * 
     * @param nome O nome do produto a ser filtrado.
     * @return Uma lista contendo os produtos filtrados pelo nome.
     */

    public List<Produtos> Filtar(String nome) {
        List<Produtos> lista = new ArrayList<>();
        try {
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join"
                    + " tb_fornecedores as f on(p.for_id=f.id) where p.descricao like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);
                lista.add(obj);

            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao criar a lista: " + e);
        }
        return null;
    }
    
    /**
     * Método para adicionar uma quantidade específica ao estoque de um produto.
     * 
     * @param id O ID do produto.
     * @param qtd_nova A quantidade a ser adicionada ao estoque.
     */
    
   public void adicionarEstoque(int id, int qtd_nova){
       try {
           String sql = "update tb_produtos set qtd_estoque=? where id=?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(1, qtd_nova);
           stmt.setInt(2, id);
           stmt.execute();
           stmt.close();
           JOptionPane.showMessageDialog(null,"adicionado com sucesso ao estoque!");
       } catch (Exception e) {
         JOptionPane.showMessageDialog(null,"erro ao adicionar ao estoque!"+e);

       }
   
   }
   
   /**
     * Método para dar baixa em uma quantidade específica no estoque de um produto.
     * 
     * @param id O ID do produto.
     * @param qtd_nova A quantidade a ser retirada do estoque.
     */
   public void baixaEstoque(int id, int qtd_nova){
       try {
           String sql = "update tb_produtos set qtd_estoque=? where id=?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(1, qtd_nova);
           stmt.setInt(2, id);
           stmt.execute();
           stmt.close();
         //  JOptionPane.showMessageDialog(null,"baixa no estoque efetuada com sucesso!");
       } catch (Exception e) {
         JOptionPane.showMessageDialog(null,"erro ao dar baixa no estoque!"+e);

       }
   
   }
   
   /**
     * Método para retornar a quantidade atual em estoque de um produto.
     * 
     * @param id O ID do produto.
     * @return A quantidade atual em estoque do produto.
     */
   
  public int retornaQtdAtualEstoque(int id){
      try {
          int qtd_atual_estoque = 0;
          String sql = "select qtd_estoque from tb_produtos where id=?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setInt(1, id);
          ResultSet rs = stmt.executeQuery();
          if(rs.next()){
              qtd_atual_estoque = (rs.getInt("qtd_estoque"));
              
          }
          return qtd_atual_estoque;
      } catch (SQLException e) {
          throw new RuntimeException("erro ao retornar a quantidade atual do estoque!"+e);
      }
  
  }
   
}
