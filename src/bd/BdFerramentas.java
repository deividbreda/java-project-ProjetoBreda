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

/**
 *
 * @author deivi
 */
public class BdFerramentas {
    public void insere(Ferramentas f){
        String sql = "insert into ferramentas(codigo, nome, descricao, quantidade, preco) values(?,?,?,?,?)";
        try{
           PreparedStatement ps = Bd.getCon().prepareStatement(sql); 
           ps.setInt(1, f.getCodigo());
           ps.setString(2, f.getNomeProduto());
           ps.setString(3, f.getDescricao());
           ps.setInt(4, f.getQuantidade());
           ps.setInt(5, f.getPreco());
             
           ps.execute();    
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
    
    public void atualiza(Ferramentas f) {
        String sql = "update ferramentas set nome=?,descricao=?,quantidade=?,preco=? where codigo=?";
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(5, f.getCodigo());
            ps.setString(1, f.getNomeProduto());
            ps.setString(2, f.getDescricao());
            ps.setInt(3, f.getQuantidade());
            ps.setInt(4, f.getPreco());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
    
    public void salva(Ferramentas f){
        String sql = "update ferramentas set quantidade=? where codigo=?" ;
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, f.getQuantidade());
            ps.setInt(2, f.getCodigo());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
    
    public Ferramentas localiza(int codigo) {
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
            registro.setPreco(rs.getInt("preco"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
       return registro;
    }
    
    public List pesquisa(String busca) {
        String sql = "select * from ferramentas where nome like ?";
        List lista = new ArrayList();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setString(1, "%" + busca + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            Ferramentas registro = new Ferramentas();
            registro.setCodigo(rs.getInt("codigo"));
            registro.setNomeProduto(rs.getString("nome"));
            registro.setDescricao(rs.getString("descricao"));
            registro.setQuantidade(rs.getInt("quantidade"));
            registro.setPreco(rs.getInt("preco"));
            lista.add(registro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
      return lista;
    }
    
     public void exclui(int codigo) {
        String sql = "delete from ferramentas where codigo=?";
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
}


