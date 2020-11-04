package Principal;

import Capture.RegisterPerson;
import Data.Dados;
import Recognizer.Recognizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Frederico Roberto Parreira
 */
public class Menu extends javax.swing.JFrame {
//iniciando os valores e recebendo o user como parâmetro, para saber qual usuário estamos usando
    public Menu(String user) {
        initComponents();
        txt_title_menu.setText("Welcome, " + user);
        //verifique se o usuário tem permissões
        verify(user);
    }
    //string global, que seta o user como um usuário normal
    public String result = "usuario";

    private Menu() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txt_title_menu = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Security System - Menu");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(97, 124, 206));
        jLabel3.setText("Escolha uma opção");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));

        jButton1.setBackground(new java.awt.Color(93, 200, 119));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Reconhecimento");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 200, 150));

        jButton2.setBackground(new java.awt.Color(200, 193, 92));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Dados");
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 200, 150));

        jButton3.setBackground(new java.awt.Color(92, 151, 200));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Câmera");
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setOpaque(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 200, 150));

        txt_title_menu.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt_title_menu.setForeground(new java.awt.Color(104, 194, 98));
        txt_title_menu.setText("Bem-vindo, user!");
        jPanel1.add(txt_title_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel1.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 80, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 400));

        setSize(new java.awt.Dimension(1002, 454));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //chamando a tela de reconhecimento facial
        new Recognizer().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //verificando se o usuário é admin
        if(result.equals("admin")){
            new Dados().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Você precisa de permissão para executar esta ação.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //verificando se o usuário é diretor ou admin
        if(result.equals("diretor") || result.equals("admin")) {
        new RegisterPerson().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Você precisa de permissão para executar esta ação.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        //chamando a tela de login
        new Login().setVisible(true);
        //fechando a tela atual
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    
    public void verify(String user) {
        //verificando a patente do usuário
        if(user.equals("admin")) {
            result = "admin";
            return;
        } else if(user.equals("diretor") || user.equals("admin")) {
              result = "diretor";
              return;
          } else {
              return;
          }
    }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSair;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txt_title_menu;
    // End of variables declaration//GEN-END:variables
}
