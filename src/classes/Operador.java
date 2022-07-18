/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Objects;

/**
 *
 * @author Carlos
 */
public class Operador {
    
    private int id;
    private String nome;
    private String usuario;
    private String senha;
    private classes.Empresa empresa;
    
    public Operador(){
        
    }
    
    public Operador(String nome, String usuario, String senha, classes.Empresa empresa){
        setNome(nome);
        setUsuario(usuario);
        setSenha(senha);
        setEmpresa(empresa);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }        

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public classes.Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(classes.Empresa empresa) {
        this.empresa = empresa;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Operador other = (Operador) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Operador{" + "nome=" + nome + ", usuario=" + usuario + ", empresa=" + empresa + '}';
    }
        
}
