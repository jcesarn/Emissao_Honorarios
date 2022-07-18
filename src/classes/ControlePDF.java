/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;

/**
 *
 * @author Carlos
 */
public class ControlePDF {

    // verificar se relatório pesquisado já existe
    public boolean verificarPDF(int tipo, String mes, String ano) {
        // tipo 1 = relatório - tipo 2 = honorário
        File arquivo = null;
        if (tipo == 1) {
            arquivo = new File("relatorio_" + mes + "_" + ano + ".pdf");
        } else {
            arquivo = new File("honorario_" + mes + "_" + ano + ".pdf");
        }
        return arquivo.exists();
    }
    
    // verificar se relatório pesquisado já existe (avulso)
    public boolean verificarPDF() {
        return new File("ultimo.pdf").exists();
    }
    
    // verificar se honorário pesquisado já existe (avulso)
    public boolean verificarPDFhono() {
        return new File("ultimo.pdf").exists();
    }

}
