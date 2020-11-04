package Capture;

import Utils.ConnectDatabase;
import javax.swing.JOptionPane;

/**
 *
 * @author Fred
 */
public class RegisterPerson extends javax.swing.JFrame {
//conectando ao banco

    ConnectDatabase con = new ConnectDatabase();

    public RegisterPerson() {
        initComponents();
        //consultando o id do usuário
        showIdUser();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txt_id_label = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_cargo = new javax.swing.JTextField();
        txt_first_name = new javax.swing.JTextField();
        txt_last_name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_data = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register Person");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_id_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_id_label.setForeground(new java.awt.Color(70, 70, 70));
        txt_id_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_id_label.setText("1");
        jPanel2.add(txt_id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 570, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 560, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txt_cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 220, -1));
        jPanel3.add(txt_first_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 220, -1));
        jPanel3.add(txt_last_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 220, -1));

        jLabel2.setText("Nome");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel3.setText("Sobrenome");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jLabel4.setText("Data de nascimento");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel5.setText("Cargo");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        try {
            txt_data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel3.add(txt_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 220, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 560, 190));

        jButton1.setBackground(new java.awt.Color(104, 151, 197));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Next");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 150, 40));

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        jPanel1.add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 80, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 400));

        setSize(new java.awt.Dimension(588, 436));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//iniciando as variaveis
        String fName = txt_first_name.getText();
        String lName = txt_last_name.getText();
        String cargo = txt_cargo.getText();
        String data = txt_data.getText();
        int id = Integer.parseInt(txt_id_label.getText().replace("ID: ", ""));
//passando as variaveis como parametro para o componente capture, e o deixando visivel
        new Capture(id, fName, lName, cargo, data).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        //fechando a janela
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterPerson().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txt_cargo;
    private javax.swing.JFormattedTextField txt_data;
    private javax.swing.JTextField txt_first_name;
    private javax.swing.JLabel txt_id_label;
    private javax.swing.JTextField txt_last_name;
    // End of variables declaration//GEN-END:variables

    //procurando o id do usuário
    private void showIdUser() {
        //conectando ao banco
        con.connect();
        //executando a SQL
        con.executeSQL("SELECT * FROM person ORDER BY id DESC LIMIT 1");
        try {
            //pegando o primeiro resultado
            con.rs.first();
//pegando o ID que voltou do banco de dados
            int id = con.rs.getInt("id");
            //setando ele no label
            txt_id_label.setText("ID: " + String.valueOf(id));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao procurar usuário");
        }
    }
}
