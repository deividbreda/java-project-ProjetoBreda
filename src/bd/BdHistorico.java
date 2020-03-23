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
import vo.Notas;

/**
 *
 * @author deivi
 */
public class BdHistorico {   
    public void insere(Notas n){
        String sql = "insert into nota(codigo, nome, descricao, quantidade, preco, nomecliente, data, pagamento, vezes, parcelas, metodo) values(?,?,?,?,?,?,?,?,?,?,?)";
        try{
           PreparedStatement ps = Bd.getCon().prepareStatement(sql); 
           ps.setInt(1, n.getCodigo());
           ps.setString(2, n.getNome());
           ps.setString(3, n.getDescricao());
           ps.setInt(4, n.getQuantidade());
           ps.setInt(5, n.getPreco());
           ps.setString(6, n.getNomeCliente());
           ps.setDate(7,  new java.sql.Date(n.getData().getTime()));
           ps.setString(8, n.getPagamento());
           ps.setInt(9, n.getVezes());
           ps.setInt(10, n.getParcelas());
           ps.setString(11, n.getMetodo());
             
           ps.execute();    
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
    
    public Notas localiza(int codigo) {
        String sql = "select * from nota where codigo=?";
        Notas registro = new Notas();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery(); 
            if (rs.next()) {
            registro.setCodigo(rs.getInt("codigo")); 
            registro.setNome(rs.getString("nome"));
            registro.setDescricao(rs.getString("descricao"));
            registro.setQuantidade(rs.getInt("quantidade"));
            registro.setPreco(rs.getInt("preco"));
            //registro.setNomeCliente(rs.getString("nomecliente"));
            //registro.setData(rs.getDate("data"));
            registro.setPagamento(rs.getString("pagamento"));
            registro.setVezes(rs.getInt("vezes"));
            registro.setParcelas(rs.getInt("parcelas"));
            registro.setMetodo(rs.getString("metodo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
       return registro;
    }
    
    public List pesquisa(String busca) {
        String sql = "select * from nota where nome like ?";
        List lista = new ArrayList();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setString(1, "%" + busca + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            Notas registro = new Notas();
            registro.setCodigo(rs.getInt("codigo"));
            registro.setNome(rs.getString("nome"));
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
    
    public List pesquisa2(String busca) {
        String sql = "select * from nota where nomecliente like ?";
        List lista = new ArrayList();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setString(1, "%" + busca + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            Notas registro = new Notas();
            registro.setCodigo(rs.getInt("codigo"));
            registro.setNome(rs.getString("nome"));
            registro.setDescricao(rs.getString("descricao"));
            registro.setQuantidade(rs.getInt("quantidade"));
            registro.setPreco(rs.getInt("preco"));
            registro.setNomeCliente(rs.getString("nomecliente"));
            registro.setData(rs.getDate("data"));
            registro.setPagamento(rs.getString("pagamento"));
            registro.setVezes(rs.getInt("vezes"));
            registro.setMetodo(rs.getString("metodo"));
            lista.add(registro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
      return lista;
    }
    
    public void exclui(int codigo) {
        String sql = "delete from nota where codigo=?";
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        }
    }
}
