package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 * Classe que implementa o Data Access Object (DAO) para a entidade Clientes.
 * Responsável por realizar operações de CRUD (Create, Read, Update, Delete)
 * no banco de dados para a tabela tb_clientes.
 * 
 * @version 1.0
 * @since 2024
 */

/**
 *
 * @author henri
 */
public class ClientesDAO {
  private Connection conn;
  
  /**
     * Construtor que inicializa a conexão com o banco de dados.
     */
  
  public ClientesDAO(){
  this.conn = new ConexaoBanco().pegarConexao();
  }
  
  /**
     * Método para salvar um novo cliente no banco de dados.
     * 
     * @param obj Objeto Clientes a ser salvo.
     */
  
  public void Salvar(Clientes obj){
      try {
          //1º criar o SQL
          String sql = "insert into tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                  + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
          //2º preparar conexao SQL para se conectar com o banco
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1,obj.getNome());
          stmt.setString(2,obj.getRg());
          stmt.setString(3,obj.getCpf());
          stmt.setString(4,obj.getEmail());
          stmt.setString(5,obj.getTelefone());
          stmt.setString(6,obj.getCelular());
          stmt.setString(7,obj.getCep());
          stmt.setString(8,obj.getEndereco());
          stmt.setInt(9,obj.getNumero());
          stmt.setString(10,obj.getComplemento());
          stmt.setString(11,obj.getBairro());
          stmt.setString(12,obj.getCidade());
          stmt.setString(13,obj.getEstado());
          //3º executar sql
          stmt.execute();
          //4º fechar conexão
          stmt.close();
          JOptionPane.showMessageDialog(null, "cliente salvo com sucesso!");
      } catch (SQLException erro) {
          JOptionPane.showMessageDialog(null,"erro ao salvar o cliente"+ erro);
      }
  }
  
  /**
     * Método para editar um cliente existente no banco de dados.
     * 
     * @param obj Objeto Clientes a ser editado.
     */
  
  public void Editar(Clientes obj){
      try {
          //1º criar o SQL
          String sql = "update tb_clientes set nome=?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,endereco=?,"
                  + "numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
          //2º preparar conexao SQL para se conectar com o banco
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1,obj.getNome());
          stmt.setString(2,obj.getRg());
          stmt.setString(3,obj.getCpf());
          stmt.setString(4,obj.getEmail());
          stmt.setString(5,obj.getTelefone());
          stmt.setString(6,obj.getCelular());
          stmt.setString(7,obj.getCep());
          stmt.setString(8,obj.getEndereco());
          stmt.setInt(9,obj.getNumero());
          stmt.setString(10,obj.getComplemento());
          stmt.setString(11,obj.getBairro());
          stmt.setString(12,obj.getCidade());
          stmt.setString(13,obj.getEstado());
          stmt.setInt(14,obj.getId());
          //3º executar sql
          stmt.execute();
          //4º fechar conexão
          stmt.close();
          JOptionPane.showMessageDialog(null, "cliente editado com sucesso!");
      } catch (SQLException erro) {
          JOptionPane.showMessageDialog(null,"erro ao editar o cliente"+ erro);
      }
  }
  
  /**
     * Método para excluir um cliente do banco de dados.
     * 
     * @param obj Objeto Clientes a ser excluído.
     */
    
    public void Excluir(Clientes obj){
        try {
            String sql = "delete from tb_clientes where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "cliente excluído com sucesso");
            
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"erro ao excluir o cliente"+ e);
        }
    }
    
    /**
     * Método para buscar um cliente no banco de dados pelo nome.
     * 
     * @param nome Nome do cliente a ser buscado.
     * @return Objeto Clientes encontrado ou null se não encontrado.
     */
    
  public Clientes BuscarCliente(String nome){
      try {
          String sql = "select * from tb_clientes where nome = ?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1, nome);
          ResultSet rs = stmt.executeQuery();
          Clientes obj = new Clientes();
          if(rs.next()){
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
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
      JOptionPane.showMessageDialog(null,"erro ao buscar cliente"+ erro);
      }
      return null;
  }
  
  /**
     * Método para buscar um cliente no banco de dados pelo CPF.
     * 
     * @param cpf CPF do cliente a ser buscado.
     * @return Objeto Clientes encontrado ou null se não encontrado.
     */
  
   public Clientes BuscarClienteCPF(String cpf){
      try {
          String sql = "select * from tb_clientes where cpf = ?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1, cpf);
          ResultSet rs = stmt.executeQuery();
          Clientes obj = new Clientes();
          if(rs.next()){
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
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
      JOptionPane.showMessageDialog(null,"erro ao buscar cliente"+ erro);
      }
      return null;
  }
   
  /**
     * Método para listar todos os clientes do banco de dados.
     * 
     * @return Lista de objetos Clientes.
     */ 
   
public List<Clientes>Listar(){
List<Clientes> lista = new ArrayList<>();
    try {
        String sql = "select * from tb_clientes";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Clientes obj = new Clientes();
             obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
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
     * Método para filtrar clientes pelo nome no banco de dados.
     * 
     * @param nome Nome do cliente a ser filtrado.
     * @return Lista de objetos Clientes que correspondem ao filtro.
     */

public List<Clientes>Filtar(String nome){
List<Clientes> lista = new ArrayList<>();
    try {
        String sql = "select * from tb_clientes where nome like ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Clientes obj = new Clientes();
             obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
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
