/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import vo.Ferramentas;
import vo.Usuarios;

/**
 *
 * @author deivi
 */
public class BdUsuarios {
  public void insere(Usuarios u){
        String sql = "insert into usuarios(codigo, nome, telefone, cpf, endereco) values(?,?,?,?,?)";
        try{
           PreparedStatement ps = Bd.getCon().prepareStatement(sql); 
           ps.setInt(1, u.getCodigo());
           ps.setString(2, u.getNome());
           ps.setString(3, u.getTelefone());
           ps.setString(4, u.getCpf());          
           ps.setString(5, u.getEndereco());
             
           ps.execute();    
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }  
  
  public void atualiza(Usuarios u) {
        String sql = "update usuarios set nome=?,telefone=?,cpf=?,endereco=? where codigo=?";
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(5, u.getCodigo());
            ps.setString(1, u.getNome());
            ps.setString(2, u.getTelefone());
            ps.setString(3, u.getCpf());      
            ps.setString(4, u.getEndereco());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
  
  public Usuarios localiza2(String login){
        String sql = "select * from usuarios where nome=?"; 
        Usuarios registro = new Usuarios();
        try{
            PreparedStatement ps = Bd.getCon().prepareStatement(sql); 
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery(); 
            if (rs.next()) {
                registro.setCodigo(rs.getInt("codigo")); 
            registro.setNome(rs.getString("nome"));
            registro.setTelefone(rs.getString("telefone"));
            registro.setCpf(rs.getString("cpf"));       
            registro.setEndereco(rs.getString("endereco"));
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
        return registro;
    }
  
    public Usuarios localiza(int codigo) {
        String sql = "select * from usuarios where codigo=?";
        Usuarios registro = new Usuarios();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery(); 
            if (rs.next()) {
            registro.setCodigo(rs.getInt("codigo")); 
            registro.setNome(rs.getString("nome"));
            registro.setTelefone(rs.getString("telefone"));
            registro.setCpf(rs.getString("cpf"));        
            registro.setEndereco(rs.getString("endereco"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
       return registro;
    }
    
    public Ferramentas localizaItem(int codigo) {
        String sql = "select * from ferramentas where codigo=?";
        Ferramentas registro = new Ferramentas();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery(); 
            if (rs.next()) {
            registro.setCodigo(rs.getInt("codigo")); 
            registro.setNomeProduto(rs.getString("nome"));
            registro.setDescricao(rs.getString("descricao"));
            registro.setQuantidade(rs.getInt("quantidade"));
          
            
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
       return registro;
    }
    
    public List pesquisa(String busca) {
        String sql = "select * from usuarios where codigo and nome like ?";
        List lista = new ArrayList();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setString(1, "%" + busca + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            Usuarios registro = new Usuarios();
            registro.setCodigo(rs.getInt("codigo"));
            registro.setNome(rs.getString("nome"));
            registro.setTelefone(rs.getString("telefone"));
            registro.setCpf(rs.getString("cpf"));      
            registro.setEndereco(rs.getString("endereco"));
            lista.add(registro);         
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
      return lista;
    }
    
    public void exclui(int codigo) {
        String sql = "delete from usuarios where codigo=?";
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
}
