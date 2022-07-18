/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Cliente;
import classes.Honorario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.ConnectionFactory;

/**
 *
 * @author Julio Cesar Nomelini
 */
public class DAOhonorario{

    public boolean criar(Honorario honorario) {
        String sql = "insert into honorarios (id_cliente,data,desc_1,desc_2,desc_3,desc_4,valor_1,valor_2,valor_3,valor_4) values (?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, String.valueOf(honorario.getIdCliente()));
            stmt.setString(2, String.valueOf(honorario.getData()));
            stmt.setString(3, honorario.getDesc_1());
            stmt.setString(4, honorario.getDesc_2());
            stmt.setString(5, honorario.getDesc_3());
            stmt.setString(6, honorario.getDesc_4());
            stmt.setString(7, String.valueOf(honorario.getVal_1()));
            stmt.setString(8, String.valueOf(honorario.getVal_2()));
            stmt.setString(9, String.valueOf(honorario.getVal_3()));
            stmt.setString(10, String.valueOf(honorario.getVal_4()));
            
            stmt.execute();
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Honorario buscar(int id, int tipo, String mes, String ano) {
        //tipo 1 = id honorario, 2 = id_cliente
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "SELECT * FROM honorarios WHERE id = ?";
                break;
            case 2:
                sql = "SELECT * FROM honorarios WHERE id_cliente = ?";
                break;
            case 3:
                sql = "SELECT * FROM honorarios WHERE id_cliente = ? and year(data) = ? and month(data) = ?";
                break;
            default:
                return null;
        }
        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            if(tipo == 3){
                stmt.setString(2,ano);
                stmt.setString(3,mes);
            }
            ResultSet rs = stmt.executeQuery();
            Honorario honorario = new Honorario();
            
            Honorario hono = null;
            
            while(rs.next()){
                hono = new Honorario();
                hono.setId(rs.getInt("id"));
                hono.setIdCliente(rs.getInt("id_cliente"));
                hono.setData(rs.getDate("data").toLocalDate());
                hono.setDesc_1(rs.getString("desc_1"));
                hono.setDesc_2(rs.getString("desc_2"));
                hono.setDesc_3(rs.getString("desc_3"));
                hono.setDesc_4(rs.getString("desc_4"));
                hono.setVal_1(rs.getDouble("valor_1"));
                hono.setVal_2(rs.getDouble("valor_2"));
                hono.setVal_3(rs.getDouble("valor_3"));
                hono.setVal_4(rs.getDouble("valor_4"));
            }
            
            return hono;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean atualizar(Honorario honorario) {
        String sql = "update honorarios set id_cliente = ?,data = ?,desc_1 = ?,desc_2 = ?,desc_3 = ?,desc_4 = ?,valor_1 = ?,valor_2 = ?,valor_3 = ?,valor_4 = ? where id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, String.valueOf(honorario.getIdCliente()));
            stmt.setString(2, String.valueOf(honorario.getData()));
            stmt.setString(3, honorario.getDesc_1());
            stmt.setString(4, honorario.getDesc_2());
            stmt.setString(5, honorario.getDesc_3());
            stmt.setString(6, honorario.getDesc_4());
            stmt.setString(7, String.valueOf(honorario.getVal_1()));
            stmt.setString(8, String.valueOf(honorario.getVal_2()));
            stmt.setString(9, String.valueOf(honorario.getVal_3()));
            stmt.setString(10, String.valueOf(honorario.getVal_4()));
            stmt.setString(11, String.valueOf(honorario.getId()));
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM honorarios WHERE id = ?";
        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, id);   
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // REMOVE HONORARIOS PELO ID DO CLIENTE
    public boolean excluirPeloCliente(int id) {
        String sql = "DELETE FROM honorarios WHERE id_cliente = ?";        
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, id);  
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // VERIFICAR SE EXISTE ALGUM HONORARIO NO BANCO PELO MES E ANO RETORNAR BOOLEANO
    public boolean verExisteHono(String mes, String ano){
        String sql = "SELECT * FROM honorarios WHERE year(data) = ? and month(data) = ?";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,ano);
            stmt.setString(2,mes);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    
    // BUSCAR TODOS HONORARIOS NO BANCO PELO MES E ANO RETORNAR LISTA DELES
    public List<Honorario> buscarListaHonoData(String mes, String ano){
        List<Honorario> lista_honorarios = new ArrayList<>();
        String sql = "SELECT * FROM honorarios WHERE year(data) = ? and month(data) = ?";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,ano);
            stmt.setString(2,mes);
            ResultSet rs = stmt.executeQuery();            
            while(rs.next()){
                Honorario hono = new Honorario();
                hono.setId(rs.getInt("id"));
                hono.setIdCliente(rs.getInt("id_cliente"));
                hono.setData(rs.getDate("data").toLocalDate());
                hono.setDesc_1(rs.getString("desc_1"));
                hono.setDesc_2(rs.getString("desc_2"));
                hono.setDesc_3(rs.getString("desc_3"));
                hono.setDesc_4(rs.getString("desc_4"));
                hono.setVal_1(rs.getDouble("valor_1"));
                hono.setVal_2(rs.getDouble("valor_2"));
                hono.setVal_3(rs.getDouble("valor_3"));
                hono.setVal_4(rs.getDouble("valor_4"));
                lista_honorarios.add(hono);
            }
            return lista_honorarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // verificar se existe algum honorario no banco retornar boolean
    public boolean verExistHonoBanco(){
        String sql = "SELECT * FROM honorarios";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            return rs.next();            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // buscar os honorarios mais recentes (mes e ano) e retornar lista com eles
    public List<Honorario> buscarHonoRecentes(){
        List<Honorario> lista_honorarios = new ArrayList<>();
        String sql = "SELECT id,id_cliente,max(data) as data,desc_1,desc_2,desc_3,desc_4,valor_1,valor_2,valor_3,valor_4 FROM honorarios GROUP BY id_cliente";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();            
            while(rs.next()){
                Honorario hono = new Honorario();
                hono.setId(rs.getInt("id"));
                hono.setIdCliente(rs.getInt("id_cliente"));
                hono.setData(rs.getDate("data").toLocalDate());
                hono.setDesc_1(rs.getString("desc_1"));
                hono.setDesc_2(rs.getString("desc_2"));
                hono.setDesc_3(rs.getString("desc_3"));
                hono.setDesc_4(rs.getString("desc_4"));
                hono.setVal_1(rs.getDouble("valor_1"));
                hono.setVal_2(rs.getDouble("valor_2"));
                hono.setVal_3(rs.getDouble("valor_3"));
                hono.setVal_4(rs.getDouble("valor_4"));
                lista_honorarios.add(hono);
            }
            return lista_honorarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //adiciona honorarios automaticamente copiando dados de honorarios mais recentes
    public boolean honorarioAutomatico() {
        List<Honorario> lista_honorarios = buscarHonoRecentes();
        String sql = "insert into honorarios (id_cliente,data,desc_1,desc_2,desc_3,desc_4,valor_1,valor_2,valor_3,valor_4) values (?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            if(lista_honorarios.isEmpty())
                return false;
            else {
                for(Honorario h : lista_honorarios) {
                    stmt.setString(1, String.valueOf(h.getIdCliente()));
                    stmt.setString(2, String.valueOf(h.getData().plusMonths(1)));
                    stmt.setString(3, h.getDesc_1());
                    stmt.setString(4, h.getDesc_2());
                    stmt.setString(5, h.getDesc_3());
                    stmt.setString(6, h.getDesc_4());
                    stmt.setString(7, String.valueOf(h.getVal_1()));
                    stmt.setString(8, String.valueOf(h.getVal_2()));
                    stmt.setString(9, String.valueOf(h.getVal_3()));
                    stmt.setString(10, String.valueOf(h.getVal_4()));

                    stmt.execute();
                }
                return true;
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //verifica se existe honorario de tal cliente na data atual
    public boolean verificaHonoData(int id) {
        String sql = "SELECT * FROM honorarios WHERE id_cliente = ? and year(data) = ? and month(data) = ?";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.setInt(2,LocalDate.now().getYear());
            stmt.setInt(3,LocalDate.now().getMonth().getValue());
            ResultSet rs = stmt.executeQuery();
            return rs.first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // busca todos os anos disponíveis dos honorarios por empresa
    public List<String> anosDisponiveis(int id){
        List<Cliente> clientes_lista = new dao.DAOcliente().buscarTodosClientes(classes.OperadorAutenticado.operadorAutenticado.getEmpresa().getId());
        List<String> anos = new ArrayList<>();
        
        for(Cliente cl : clientes_lista){
            String sql = "SELECT * FROM honorarios WHERE id_cliente = ?";
            try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.setInt(1, cl.getId());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    if(!anos.contains(rs.getString("data").split("-")[0])){
                        anos.add(rs.getString("data").split("-")[0]);
                    }                    
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Collections.sort(anos);
        return anos;
    }
    
    // busca todos os anos disponíveis dos honorarios por empresa
    public List<String> mesesDisponiveis(int id){
        List<Cliente> clientes_lista = new dao.DAOcliente().buscarTodosClientes(classes.OperadorAutenticado.operadorAutenticado.getEmpresa().getId());
        List<String> meses = new ArrayList<>();
        List<String> meses_formatado = new ArrayList<>();
        for(Cliente cl : clientes_lista){
            String sql = "SELECT * FROM honorarios WHERE id_cliente = ?";
            try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.setInt(1, cl.getId());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    if(!meses.contains(rs.getString("data").split("-")[1])){
                        meses.add(rs.getString("data").split("-")[1]);
                    }                    
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        for(String m :meses){
            switch(m){
                case"01":
                    meses_formatado.add("1");
                    break;
                case"02":
                    meses_formatado.add("2");
                    break;
                case"03":
                    meses_formatado.add("3");
                    break;
                case"04":
                    meses_formatado.add("4");
                    break;
                case"05":
                    meses_formatado.add("5");
                    break;
                case"06":
                    meses_formatado.add("6");
                    break;
                case"07":
                    meses_formatado.add("7");
                    break;
                case"08":
                    meses_formatado.add("8");
                    break;
                case"09":
                    meses_formatado.add("9");
                    break;
            }
        }
        
        Collections.sort(meses_formatado);
        return meses_formatado;
    }
    
        
}
