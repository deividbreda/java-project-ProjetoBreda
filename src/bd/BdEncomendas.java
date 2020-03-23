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
import vo.Encomendas;

/**
 *
 * @author deivi
 */
public class BdEncomendas {
    public void insere(Encomendas m){
        //Teste
        String sql = "insert into metodo(codigo,nome,quantidade,nomecliente,uf,cidade,rua,status,numero,complemento) values(?,?,?,?,?,?,?,?,?,?)";
        try{
           PreparedStatement ps = Bd.getCon().prepareStatement(sql); 
           ps.setInt(1, m.getCodigo());
           ps.setString(2, m.getNomeProduto());
           ps.setInt(3, m.getQuantidade());
           ps.setString(4, m.getNomeCliente());
           ps.setString(5, m.getUf());
           ps.setString(6, m.getCidade());
           ps.setString(7, m.getRua());
           ps.setString(8, m.getStatus());
           ps.setInt(9, m.getNumero());
           ps.setString(10, m.getComplemento());
    
           ps.execute();    
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
    
    public void atualiza(Encomendas m) {
        String sql = "update metodo set nome=?,quantidade=?,nomecliente=?,uf=?,cidade=?,rua=?,status=?,numero=?,complemento=? where codigo=?";
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(10, m.getCodigo());           
            ps.setString(1, m.getNomeProduto());
            ps.setInt(2, m.getQuantidade());
            ps.setString(3, m.getNomeCliente());
            ps.setString(4, m.getUf());
           ps.setString(5, m.getCidade());
            ps.setString(6, m.getRua());
            ps.setString(7, m.getStatus());
            ps.setInt(8, m.getNumero());
            ps.setString(9, m.getComplemento());
           
          
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
    
    public Encomendas localiza(int codigo) {
        String sql = "select * from metodo where codigo=?";
        Encomendas registro = new Encomendas();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery(); 
            if (rs.next()) {
            registro.setCodigo(rs.getInt("codigo"));
            registro.setNomeProduto(rs.getString("nome"));                  
            registro.setQuantidade(rs.getInt("quantidade"));
            registro.setNomeCliente(rs.getString("nomecliente"));
            registro.setUf(rs.getString("uf"));
            registro.setCidade(rs.getString("cidade"));
            registro.setRua(rs.getString("rua"));
            registro.setStatus(rs.getString("status"));
            registro.setNumero(rs.getInt("numero"));
            registro.setComplemento(rs.getString("complemento"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
       return registro;
    }
    
    public List pesquisa(String busca) {
        String sql = "select * from metodo where nome like ?";
        List lista = new ArrayList();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setString(1, "%" + busca + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            Encomendas registro = new Encomendas();
            registro.setCodigo(rs.getInt("codigo"));
            registro.setNomeProduto(rs.getString("nome"));
            registro.setQuantidade(rs.getInt("quantidade"));
            registro.setNomeCliente(rs.getString("nomecliente"));
            registro.setUf(rs.getString("uf"));
            registro.setCidade(rs.getString("cidade"));
            registro.setRua(rs.getString("rua"));
            registro.setStatus(rs.getString("status"));
            registro.setNumero(rs.getInt("numero"));
            registro.setComplemento(rs.getString("complemento"));
            
            lista.add(registro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
      return lista;
    }
    
    public void exclui(int codigo) {
        String sql = "delete from metodo where codigo=?";
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
}
