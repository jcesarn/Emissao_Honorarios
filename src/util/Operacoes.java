/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Julio Cesar Nomelini
 */
public enum Operacoes {
    NOVO(1), BUSCAR(2), EDITAR(3), EXCLUIR(4); 
    
    private final Integer operacao;
    
    private Operacoes(Integer operacao){
        this.operacao = operacao;
    }
    
    public Integer getOperacao(){
        return operacao;
    }
}
