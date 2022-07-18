/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionFactory;

/**
 *
 * @author Carlos
 */
public class DAOempresa {
    
    // ATUALIZAR EMPRESA E RETORNAR TRUE OU FALSE
    public boolean atualizarEmp(classes.Empresa emp){
        String sql = "update empresa set nome = ?,endereco = ? where id = ?";
        String sql2 = "update tel_empresa set telefone = ? where id_empresa = ?";

        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            PreparedStatement stmt2 = connection.prepareStatement(sql2)) {
            
            stmt.setString(1, emp.getRazaosocial());
            stmt.setString(2, emp.getEndereco());
            stmt.setInt(3, emp.getId());
            stmt2.setString(1, emp.getTelefone());
            stmt2.setInt(2, emp.getId());
            
            stmt.executeUpdate();
            stmt2.executeUpdate();
            
            return true;            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // INSERIR EMPRESA NO BANCO E RETORNAR TRUE OU FALSE
    public boolean inserirEmp(classes.Empresa emp){
        String sql = "INSERT INTO empresa (nome,endereco,cnpj) values (?,?,?)";      
        String sql2 = "INSERT INTO tel_empresa (id_empresa,telefone) values (?,?)";      
        ResultSet rs = null;
        int id = -1;        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmt2 = connection.prepareStatement(sql2)) {
            stmt.setString(1, emp.getRazaosocial());
            stmt.setString(2, emp.getEndereco());            
            stmt.setString(3, emp.getCnpj()); 
            stmt.execute();
            rs = stmt.getGeneratedKeys();
            if(rs != null && rs.next()){                
                stmt2.setInt(1, rs.getInt(1));
                stmt2.setString(2, emp.getTelefone());
                stmt2.execute();
                return true;
            }else{
                connection.rollback();
                return false;
            }      
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // VERIFICAR SE A EMPRESA EXISTE PELO CNPJ
    public boolean verEmpExiste(String cnpj){
        String sql = "SELECT * FROM empresa WHERE cnpj = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, cnpj);  
            ResultSet rs = stmt.executeQuery();                          
            return rs.next() != false;            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // BUSCAR TODAS AS EMPRESAS NO BANCO E RETORNAR LISTA
    public List<classes.Empresa> buscarListaEmpresa(){
        List<classes.Empresa> lista_empresas = new ArrayList<>();
        String sql = "SELECT * FROM empresa";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){  
            ResultSet rs = stmt.executeQuery();            
            while(rs.next()){
                classes.Empresa emp = new Empresa(); 
                emp.setId(rs.getInt("id"));
                emp.setRazaosocial(rs.getString("nome"));
                emp.setEndereco(rs.getString("endereco"));
                emp.setCnpj(rs.getString("cnpj"));
                lista_empresas.add(emp);
            }
            return lista_empresas;            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // BUSCAR TODAS AS EMPRESA PELO CNPJ
    public classes.Empresa buscarEmpCNPJ(String cnpj){
        String sql = "SELECT * FROM empresa WHERE cnpj = ?";        
        String sql2 = "SELECT * FROM tel_empresa WHERE id_empresa = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            PreparedStatement stmt2 = connection.prepareStatement(sql2)){
            stmt.setString(1, cnpj);  
            ResultSet rs = stmt.executeQuery();                          
            classes.Empresa emp = new Empresa();
            while(rs.next()){                
                emp.setId(rs.getInt("id"));
                emp.setRazaosocial(rs.getString("nome"));
                emp.setEndereco(rs.getString("endereco"));
                emp.setCnpj(rs.getString("cnpj"));
                
                stmt2.setInt(1, emp.getId());  
                ResultSet rs2 = stmt2.executeQuery(); 
                
                while(rs2.next()){
                    emp.setTelefone(rs2.getString("telefone"));
                }
            }
            return emp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // BUSCAR TODAS AS EMPRESA PELO ID
    public classes.Empresa buscarEmpId(int id){
        String sql = "SELECT * FROM empresa WHERE id = ?";        
        String sql2 = "SELECT * FROM tel_empresa WHERE id_empresa = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            PreparedStatement stmt2 = connection.prepareStatement(sql2)){
            stmt.setInt(1, id);  
            ResultSet rs = stmt.executeQuery();                          
            classes.Empresa emp = new Empresa();
            while(rs.next()){                
                emp.setId(rs.getInt("id"));
                emp.setRazaosocial(rs.getString("nome"));
                emp.setEndereco(rs.getString("endereco"));
                emp.setCnpj(rs.getString("cnpj"));
                
                stmt2.setInt(1, emp.getId());  
                ResultSet rs2 = stmt2.executeQuery(); 
                
                while(rs2.next()){
                    emp.setTelefone(rs2.getString("telefone"));
                }
            }
            return emp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
