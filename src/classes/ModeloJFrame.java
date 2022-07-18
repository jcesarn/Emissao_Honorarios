/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos
 */

public abstract class ModeloJFrame extends javax.swing.JFrame {
    
    private javax.swing.JFrame back_window;
    
    public ModeloJFrame(javax.swing.JFrame back_w){
        setBack_window( back_w != null ? back_w : null );
        
        // BLOQUEAR ARRASTAR COMPONENTE
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentMoved(java.awt.event.ComponentEvent e) {
                setEnabled(false);
                setEnabled(true);
            }
        });
        
        // FECHAR E CHAMAR FRAME ANTERIOR
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                if(back_window == null){
                    if(javax.swing.JOptionPane.showConfirmDialog(null,"Deseja fechar o programa?","Atenção",javax.swing.JOptionPane.YES_NO_OPTION) == 0)
                        System.exit(0);
                }else{
                    dispose();
                    back_window.setVisible(true);
                }
            }
        });
    }
    
    public void alteracoes(String title){
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setTitle(title);
        pack();
        setLocationRelativeTo(null);
        
        URL url = getClass().getClassLoader().getResource("imagens/favicon.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        setIconImage(iconeTitulo);
    }
        
    public javax.swing.JFrame getBack_window() {
        return this.back_window;
    }

    public void setBack_window(javax.swing.JFrame back_window) {
        this.back_window = back_window;
    }
            
}
