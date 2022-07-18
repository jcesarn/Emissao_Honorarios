package dao;

import classes.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.ConnectionFactory;

public class DAOcliente{

    
    // BUSCA TODOS CLIENTES POR EMPRESA
    public List<Cliente> buscarTodosClientes(int id){
        List<Cliente> lista_clientes = new ArrayList<>();        
        String sql = "SELECT * FROM clientes where id_empresa = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();                       
            while(rs.next()){ 
                Cliente cliente = new Cliente(); 
                cliente.setId(rs.getInt("id"));
                cliente.setId_empresa(rs.getInt("id_empresa"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));  
                cliente.setLatitude(rs.getString("latitude"));
                cliente.setLongitude(rs.getString("longitude"));
                lista_clientes.add(cliente);
            }
            Collections.sort(lista_clientes);
            return lista_clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
    }
    
    // BUSCA CLIENTE POR EMPRESA
    public Cliente buscarCliente(int id){      
        String sql = "SELECT * FROM clientes where id = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); 
            Cliente cliente = new Cliente(); 
            while(rs.next()){                 
                cliente.setId(rs.getInt("id"));
                cliente.setId_empresa(rs.getInt("id_empresa"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setLatitude(rs.getString("latitude"));
                cliente.setLongitude(rs.getString("longitude"));
            }            
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
    }
    
    // VERIFICAR SE JA EXISTE CLIENTE COM O MESMO NOME
    public boolean verNomeClientes(String nome){        
        String sql = "SELECT * FROM clientes where nome = ? and id_empresa = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, nome);
            stmt.setInt(2, classes.OperadorAutenticado.operadorAutenticado.getEmpresa().getId());
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = new Cliente();            
            while(rs.next()){                
                return true;
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    
    public boolean verEnderClientes(String ender){        
        String sql = "SELECT * FROM clientes where endereco = ? and id_empresa = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, ender);
            stmt.setInt(2, classes.OperadorAutenticado.operadorAutenticado.getEmpresa().getId());
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = new Cliente();            
            while(rs.next()){                
                return true;
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    
    // CADASTRAR CLIENTE
    public boolean cadastrarCliente(Cliente cliente){
        String sql = "INSERT INTO clientes (id_empresa,nome,endereco,latitude,longitude) values (?,?,?,?,?)";          
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId_empresa());
            stmt.setString(2, cliente.getNome());            
            stmt.setString(3, cliente.getEndereco());            
            stmt.setString(4, cliente.getLatitude());            
            stmt.setString(5, cliente.getLongitude());            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // ATUALIZAR CLIENTE
    public boolean atualizarCliente(Cliente cl){
        String sql = "update clientes set nome = ?,endereco = ?, latitude = ?, longitude = ? where id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cl.getNome());
            stmt.setString(2, cl.getEndereco());
            stmt.setString(3, cl.getLatitude());
            stmt.setString(4, cl.getLongitude());
            stmt.setInt(5, cl.getId());
            stmt.executeUpdate();
            return true;            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // remover cliente
    public boolean removerCliente(int id){
        PreparedStatement ps = null;
        String sql = "DELETE FROM clientes WHERE id = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            Cliente cl = this.buscarCliente(id);
            if(new dao.DAOhonorario().excluirPeloCliente(cl.getId())){
                stmt.executeUpdate();
                return true;
            }else{
                return false;
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    //retorna nomes dos clientes registrados
    public ArrayList<String> getNomesClientes() {
        PreparedStatement ps = null;
        String sql = "SELECT nome FROM clientes";
        
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> clientes = new ArrayList();
            
            while(rs.next()) 
                clientes.add(rs.getString(1));
            Collections.sort(clientes);
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //busca clientes por nome
    public Cliente buscarClientesNome(String nome, int id) {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM clientes where nome = ? and id_empresa = ?";
        
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = new Cliente();
            
            while(rs.next()){                
                cliente.setId(rs.getInt("id"));
                cliente.setId_empresa(rs.getInt("id_empresa"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setLatitude(rs.getString("latitude"));
                cliente.setLongitude(rs.getString("longitude"));
            }
            
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //busca clientes por id
    public Cliente buscarClientesId(int id) {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM clientes where id = ?";
        
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = new Cliente();
            
            while(rs.next()){                
                cliente.setId(rs.getInt("id"));
                cliente.setId_empresa(rs.getInt("id_empresa"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setLatitude(rs.getString("latitude"));
                cliente.setLongitude(rs.getString("longitude"));
            }
            
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

