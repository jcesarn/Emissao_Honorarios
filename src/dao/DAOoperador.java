/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Operador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionFactory;

/**
 *
 * @author Carlos
 */
public class DAOoperador {
    
    // ATUALIZAR OPERAR E RETORNAR TRUE OU FALSE
    public boolean atualizarOperador(classes.Operador ope){
        String sql = "update operadores set nome = ?,usuario = ?, senha = ? where id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, ope.getNome());
            stmt.setString(2, ope.getUsuario());
            stmt.setString(3, ope.getSenha());
            stmt.setInt(4, ope.getId());
            
            stmt.executeUpdate();
            
            return true;            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // INSERIR OPERADOR NO BANCO E RETORNAR TRUE OU FALSE
    public boolean inserirOpe(classes.Operador ope){
        String sql = "INSERT INTO operadores (nome,usuario,senha,id_empresa) values (?,?,?,?)";          
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ope.getNome());
            stmt.setString(2, ope.getUsuario());            
            stmt.setString(3, ope.getSenha());
            stmt.setInt(4, ope.getEmpresa().getId());             
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // VERIFICAR SE O OPERADOR EXISTE PELO USUARIO
    public boolean verOpeExiste(String user){
        String sql = "SELECT * FROM operadores WHERE usuario = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, user);  
            ResultSet rs = stmt.executeQuery();                          
            return rs.next() != false;            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // REMOVER OPERADOR
    public boolean excluir(int id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM operadores WHERE id = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);  
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Buscar operador pelo usuário e senha
    public classes.Operador buscarOperadorUserPass(String usuario, String senha){
        String sql = "SELECT * FROM operadores WHERE usuario = ? and senha = ?";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, usuario);  
            stmt.setString(2, senha);  
            ResultSet rs = stmt.executeQuery();                          
            classes.Operador op = null;
            while(rs.next()){
                op = new Operador();
                op.setId(rs.getInt("id"));
                op.setNome(rs.getString("nome"));
                op.setUsuario(rs.getString("usuario"));
                op.setSenha(rs.getString("senha"));
                op.setEmpresa(new dao.DAOempresa().buscarEmpId(rs.getInt("id_empresa")));
            }
            return op;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Buscar todos operadores de determinada empresa e retornar lista
    public List<classes.Operador> buscarTodosOperadorPorEmpresa(int id_emp){
        String sql = "SELECT * FROM operadores WHERE id_empresa = ?";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            List<classes.Operador> lista_op = new ArrayList<>();
            stmt.setInt(1, id_emp);   
            ResultSet rs = stmt.executeQuery();                          
            classes.Operador op = null;
            while(rs.next()){
                op = new Operador();
                op.setId(rs.getInt("id"));
                op.setNome(rs.getString("nome"));
                op.setUsuario(rs.getString("usuario"));
                op.setSenha(rs.getString("senha"));
                op.setEmpresa(new dao.DAOempresa().buscarEmpId(rs.getInt("id_empresa")));
                lista_op.add(op);
            }
            return lista_op;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
