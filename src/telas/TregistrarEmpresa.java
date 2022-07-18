/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import classes.Empresa;
import classes.Operador;
import java.awt.Color;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class TregistrarEmpresa extends classes.ModeloJFrame {

    /**
     * Creates new form TregistrarEmpresa
     *
     * @param back_window
     */
    public TregistrarEmpresa(javax.swing.JFrame back_window) {
        super(back_window);
        initComponents();
        alteracoes("REGISTRAR EMPRESA");
        this.attComboBox();
    }
    
    private boolean validacaoCamposEmpresa(){
        txt_razaosocial.setBackground(new Color(255, 255, 255));
        txt_endereco.setBackground(new Color(255, 255, 255));
        txt_telefone.setBackground(new Color(255, 255, 255));
        txt_cnpj.setBackground(new Color(255, 255, 255));
        if(txt_razaosocial.getText().isEmpty() || txt_razaosocial.getText().startsWith(" ") || txt_razaosocial.equals("") || txt_razaosocial.getText() == null){
            javax.swing.JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
            txt_razaosocial.setBackground(new Color(255, 196, 196));
            txt_razaosocial.requestFocus(true);
            txt_razaosocial.setText("");
            return false;
        }
        if(txt_endereco.getText().isEmpty() || txt_endereco.getText().startsWith(" ") || txt_endereco.equals("") || txt_endereco.getText() == null){
            javax.swing.JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
            txt_endereco.setBackground(new Color(255, 196, 196));
            txt_endereco.requestFocus(true);
            txt_endereco.setText("");
            return false;
        }
        if(txt_telefone.getText().isEmpty() || txt_telefone.getText().startsWith(" ") || txt_telefone.equals("") || txt_telefone.getText().equals("(  )     -    ") || txt_telefone.getText() == null){
            javax.swing.JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
            txt_telefone.setBackground(new Color(255, 196, 196));
            txt_telefone.requestFocus(true);
            txt_telefone.setText("");
            return false;
        }
        if(txt_cnpj.getText().isEmpty() || txt_cnpj.getText().startsWith(" ") || txt_cnpj.equals("") || txt_cnpj.getText().equals("  .   .   /    -  ") || txt_cnpj.getText() == null){
            javax.swing.JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
            txt_cnpj.setBackground(new Color(255, 196, 196));
            txt_cnpj.requestFocus(true);
            txt_cnpj.setText("");
            return false;
        }
        return true;
    }
    
    private boolean validacaoCamposOperario(){
        txt_nome.setBackground(new Color(255, 255, 255));
        txt_usuario.setBackground(new Color(255, 255, 255));
        txt_senha.setBackground(new Color(255, 255, 255));
        if(txt_nome.getText().isEmpty() || txt_nome.getText().startsWith(" ") || txt_nome.equals("") || txt_nome.getText() == null){
            javax.swing.JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
            txt_nome.setBackground(new Color(255, 196, 196));
            txt_nome.requestFocus(true);
            txt_nome.setText("");
            return false;
        }
        if(txt_usuario.getText().isEmpty() || txt_usuario.getText().startsWith(" ") || txt_usuario.equals("") || txt_usuario.getText() == null){
            javax.swing.JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
            txt_usuario.setBackground(new Color(255, 196, 196));
            txt_usuario.requestFocus(true);
            txt_usuario.setText("");
            return false;
        }
        if(new String(txt_senha.getPassword()).isEmpty() || new String(txt_senha.getPassword()).startsWith(" ") || new String(txt_senha.getPassword()).equals("") || new String(txt_senha.getPassword()) == null){
            javax.swing.JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
            txt_senha.setBackground(new Color(255, 196, 196));
            txt_senha.requestFocus(true);
            txt_senha.setText("");
            return false;
        }
        return true;
    }

    private void attComboBox(){
        List<Empresa> lista_empresas = new dao.DAOempresa().buscarListaEmpresa();
        for(classes.Empresa emp : lista_empresas){
            jComboBox1.addItem(emp.getRazaosocial() + " - " + emp.getCnpj());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_razaosocial = new javax.swing.JTextField();
        txt_endereco = new javax.swing.JTextField();
        txt_cnpj = new javax.swing.JFormattedTextField();
        txt_telefone = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_nome = new javax.swing.JTextField();
        txt_usuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_senha = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        btn_registrar_ope = new javax.swing.JButton();
        btn_registrar_emp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "EMPRESA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(41, 38, 32))); // NOI18N

        txt_razaosocial.setForeground(new java.awt.Color(41, 38, 32));
        txt_razaosocial.setToolTipText("Razão social");

        txt_endereco.setForeground(new java.awt.Color(41, 38, 32));
        txt_endereco.setToolTipText("Endereço");

        txt_cnpj.setForeground(new java.awt.Color(41, 38, 32));
        try {
            txt_cnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cnpj.setToolTipText("Cnpj");

        txt_telefone.setForeground(new java.awt.Color(41, 38, 32));
        try {
            txt_telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_telefone.setToolTipText("Telefone");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/emp_raz.png"))); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/emp_end.png"))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/emp_tel.png"))); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/emp_cnpj.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_cnpj))
                    .addComponent(txt_razaosocial)
                    .addComponent(txt_endereco))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_razaosocial)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_cnpj)
                            .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPERADOR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(41, 38, 32))); // NOI18N

        txt_nome.setForeground(new java.awt.Color(41, 38, 32));
        txt_nome.setToolTipText("Nome");

        txt_usuario.setForeground(new java.awt.Color(41, 38, 32));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_login.png"))); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_login.png"))); // NOI18N

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pass_login.png"))); // NOI18N

        txt_senha.setForeground(new java.awt.Color(41, 38, 32));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/emp_raz.png"))); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione uma empresa" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(41, 38, 32));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/show_pass.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel9MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_nome)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_nome)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_usuario)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_senha)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_registrar_ope.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_ope.setForeground(new java.awt.Color(41, 38, 32));
        btn_registrar_ope.setText("REGISTRAR OPERADOR");
        btn_registrar_ope.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_opeActionPerformed(evt);
            }
        });

        btn_registrar_emp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_emp.setForeground(new java.awt.Color(41, 38, 32));
        btn_registrar_emp.setText("REGISTRAR EMPRESA");
        btn_registrar_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_empActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_registrar_ope, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_registrar_emp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_registrar_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_registrar_ope, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registrar_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_empActionPerformed
        if(validacaoCamposEmpresa()){
            // INSTANCIA NOVA EMPRESA        
            classes.Empresa nova_empresa = new Empresa();
            nova_empresa.setRazaosocial(txt_razaosocial.getText());
            nova_empresa.setEndereco(txt_endereco.getText());
            nova_empresa.setCnpj(txt_cnpj.getText());
            nova_empresa.setTelefone(txt_telefone.getText());

            // VERIFICA SE JA EXISTE A EMPRESA NO BD        
            if (new dao.DAOempresa().verEmpExiste(nova_empresa.getCnpj())) {
                javax.swing.JOptionPane.showMessageDialog(null, "Empresa já cadastrada", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                if (new dao.DAOempresa().inserirEmp(nova_empresa)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Empresa cadastrada", "Sucesso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new TregistrarEmpresa(new Tautenticacao(null)).setVisible(true);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Empresa não cadastrada", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btn_registrar_empActionPerformed

    private void btn_registrar_opeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_opeActionPerformed
        if(validacaoCamposOperario()){
            // VERIFICA SE JÁ EXISTE O OPERADOR            
            if (new dao.DAOoperador().verOpeExiste(txt_usuario.getText())) {
                javax.swing.JOptionPane.showMessageDialog(null, "Operador já cadastrado", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                /// verificar jcombo
                if(jComboBox1.getSelectedItem().toString().equals("Selecione uma empresa")){
                    javax.swing.JOptionPane.showMessageDialog(null, "Selecione uma empresa válida", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
                    jComboBox1.requestFocus(true);
                }else{
                    // INSTANCIA NOVO OPERADOR
                    classes.Operador novo_operador = new Operador();
                    novo_operador.setNome(txt_nome.getText());
                    novo_operador.setUsuario(txt_usuario.getText());
                    novo_operador.setSenha(new String(txt_senha.getPassword()));                
                    String[] pegarCNPJ = jComboBox1.getSelectedItem().toString().split(" - ");                
                    novo_operador.setEmpresa(new dao.DAOempresa().buscarEmpCNPJ(pegarCNPJ[1]));
                    if (new dao.DAOoperador().inserirOpe(novo_operador)) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Operador cadastrado", "Sucesso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new TregistrarEmpresa(new Tautenticacao(null)).setVisible(true);
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Operador não cadastrado", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_registrar_opeActionPerformed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        txt_senha.setEchoChar((char)0);
    }//GEN-LAST:event_jLabel9MousePressed

    private void jLabel9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseReleased
        txt_senha.setEchoChar('\u2022');
    }//GEN-LAST:event_jLabel9MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TregistrarEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TregistrarEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TregistrarEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TregistrarEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TregistrarEmpresa(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_registrar_emp;
    private javax.swing.JButton btn_registrar_ope;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFormattedTextField txt_cnpj;
    private javax.swing.JTextField txt_endereco;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_razaosocial;
    private javax.swing.JPasswordField txt_senha;
    private javax.swing.JFormattedTextField txt_telefone;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
