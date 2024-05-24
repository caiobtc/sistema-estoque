package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Clientes;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.model.Funcionarios;
import br.com.sistema.view.AreaTrabalho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe responsável por realizar operações de CRUD para fornecedores no banco de dados.
 * Utiliza a conexão fornecida pela classe ConexaoBanco.
 * 
 * @autor henri
 */

public class FornecedoresDAO {
  private Connection conn;
  
  /**
   * Construtor que inicializa a conexão com o banco de dados.
   */
  
  public FornecedoresDAO(){
  this.conn = new ConexaoBanco().pegarConexao();
  }
  
  /**
   * Salva um novo fornecedor no banco de dados.
   * 
   * @param obj Fornecedor a ser salvo.
   */
  
  
  public void Salvar(Fornecedores obj){
      try {
          //1º criar o SQL
          String sql = "insert into tb_fornecedores (nome,cnpj, email,telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                  + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
          //2º preparar conexao SQL para se conectar com o banco
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1,obj.getNome());
          stmt.setString(2,obj.getCnpj());
          stmt.setString(3,obj.getEmail());
          stmt.setString(4,obj.getTelefone());
          stmt.setString(5,obj.getCelular());
          stmt.setString(6,obj.getCep());
          stmt.setString(7,obj.getEndereco());
          stmt.setInt(8,obj.getNumero());
          stmt.setString(9,obj.getComplemento());
          stmt.setString(10,obj.getBairro());
          stmt.setString(11,obj.getCidade());
          stmt.setString(12,obj.getEstado());
          //3º executar sql
          stmt.execute();
          //4º fechar conexão
          stmt.close();
          JOptionPane.showMessageDialog(null, "fornecedor salvo com sucesso!");
      } catch (SQLException erro) {
          JOptionPane.showMessageDialog(null,"erro ao salvar o fornecedor"+ erro);
      }
  }
  
  /**
   * Edita um fornecedor existente no banco de dados.
   * 
   * @param obj Fornecedor com os dados atualizados.
   */
  
  public void Editar(Fornecedores obj){
      try {
          //1º criar o SQL
          String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,endereco=?,"
                  + "numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
          //2º preparar conexao SQL para se conectar com o banco
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1,obj.getNome());
          stmt.setString(2,obj.getCnpj());
          stmt.setString(3,obj.getEmail());
          stmt.setString(4,obj.getTelefone());
          stmt.setString(5,obj.getCelular());
          stmt.setString(6,obj.getCep());
          stmt.setString(7,obj.getEndereco());
          stmt.setInt(8,obj.getNumero());
          stmt.setString(9,obj.getComplemento());
          stmt.setString(10,obj.getBairro());
          stmt.setString(11,obj.getCidade());
          stmt.setString(12,obj.getEstado());
          stmt.setInt(13,obj.getId());
          //3º executar sql
          stmt.execute();
          //4º fechar conexão
          stmt.close();
          JOptionPane.showMessageDialog(null, "fornecedor editado com sucesso!");
      } catch (SQLException erro) {
          JOptionPane.showMessageDialog(null,"erro ao editar o funcionário"+ erro);
      }
  }
  
  /**
   * Exclui um fornecedor do banco de dados.
   * 
   * @param obj Fornecedor a ser excluído.
   */
    
    public void Excluir(Fornecedores obj){
        try {
            String sql = "delete from tb_fornecedores where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "fornecedor excluído com sucesso");
            
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"erro ao excluir o fornecedor"+ e);
        }
    }
    
    /**
   * Busca um fornecedor no banco de dados pelo nome.
   * 
   * @param nome Nome do fornecedor a ser buscado.
   * @return Fornecedor encontrado ou null se não encontrado.
   */
    
  public Fornecedores BuscarFornecedor(String nome){
      try {
          String sql = "select * from tb_fornecedores where nome = ?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1, nome);
          ResultSet rs = stmt.executeQuery();
          Fornecedores obj = new Fornecedores();
          if(rs.next()){
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setCnpj(rs.getString("cnpj"));
              obj.setEmail(rs.getString("email"));
              obj.setTelefone(rs.getString("telefone"));
              obj.setCelular(rs.getString("celular"));
              obj.setCep(rs.getString("cep"));
              obj.setEndereco(rs.getString("endereco"));
              obj.setNumero(rs.getInt("numero"));
              obj.setComplemento(rs.getString("complemento"));
              obj.setBairro(rs.getString("bairro"));
              obj.setCidade(rs.getString("cidade"));
              obj.setEstado(rs.getString("estado"));
                       
          }
          return obj;
          
      } catch (SQLException erro) {
      JOptionPane.showMessageDialog(null,"erro ao buscar fornecedor"+ erro);
      }
      return null;
  }
  
  /**
   * Lista todos os fornecedores cadastrados no banco de dados.
   * 
   * @return Lista de fornecedores.
   */
  
public List<Fornecedores>Listar(){
List<Fornecedores> lista = new ArrayList<>();
    try {
        String sql = "select * from tb_fornecedores";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Fornecedores obj = new Fornecedores();
             obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setCnpj(rs.getString("cnpj"));
              obj.setEmail(rs.getString("email"));
              obj.setTelefone(rs.getString("telefone"));
              obj.setCelular(rs.getString("celular"));
              obj.setCep(rs.getString("cep"));
              obj.setEndereco(rs.getString("endereco"));
              obj.setNumero(rs.getInt("numero"));
              obj.setComplemento(rs.getString("complemento"));
              obj.setBairro(rs.getString("bairro"));
              obj.setCidade(rs.getString("cidade"));
              obj.setEstado(rs.getString("estado"));
              lista.add(obj);
            
        }
        return lista;
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "erro ao criar a lista: "+e);
    }
    return null;
}

/**
   * Filtra fornecedores no banco de dados pelo nome.
   * 
   * @param nome Nome ou parte do nome do fornecedor a ser filtrado.
   * @return Lista de fornecedores que correspondem ao filtro.
   */

public List<Fornecedores>Filtar(String nome){
List<Fornecedores> lista = new ArrayList<>();
    try {
        String sql = "select * from tb_fornecedores where nome like ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Fornecedores obj = new Fornecedores();
             obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setCnpj(rs.getString("cnpj"));
              obj.setEmail(rs.getString("email"));
              obj.setTelefone(rs.getString("telefone"));
              obj.setCelular(rs.getString("celular"));
              obj.setCep(rs.getString("cep"));
              obj.setEndereco(rs.getString("endereco"));
              obj.setNumero(rs.getInt("numero"));
              obj.setComplemento(rs.getString("complemento"));
              obj.setBairro(rs.getString("bairro"));
              obj.setCidade(rs.getString("cidade"));
              obj.setEstado(rs.getString("estado"));
              lista.add(obj);
            
        }
        return lista;
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "erro ao criar a lista: "+e);
    }
    return null;
}

}
