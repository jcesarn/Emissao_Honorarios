/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Julio Cesar Nomelini
 */

public class Relatorio {
    private int id;
    private int idOperador;
    private int idCliente;
    private String descricao;
    private LocalDate data;
    private ArrayList<Honorario> honorarios;
	
    public Relatorio(int id, int idFuncionario, int idCliente, String descricao, LocalDate data) {
	this.id = id;
	this.idOperador = idFuncionario;
	this.idCliente = idCliente;
	this.descricao = descricao;
	this.data = data;
    }
	
    public void addHonorario(Honorario honorario) {
        this.honorarios.add(honorario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ArrayList<Honorario> getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(ArrayList<Honorario> honorarios) {
        this.honorarios = honorarios;
    }

    @Override
    public String toString() {
        return "Relatorio{" + "id=" + id + ", idOperador=" + idOperador + ", idCliente=" + idCliente + ", descricao=" + descricao + ", data=" + data + ", honorarios=" + honorarios + '}';
    }

}

