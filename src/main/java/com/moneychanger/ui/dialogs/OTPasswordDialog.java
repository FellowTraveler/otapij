/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.ui.dialogs;

import com.moneychanger.core.util.Configuration;
import com.moneychanger.ui.custom.LoadImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.opentransactions.jni.core.OTPassword;

/**
 *
 * @author Cameron Garnham
 */
public class OTPasswordDialog extends javax.swing.JDialog {

    /**
     * Creates new form OTPasswordDialog
     */
    
    private static char[] password;
    private javax.swing.JPanel pasword_image;
    
    public OTPasswordDialog(java.awt.Frame parent, boolean modal, String passphrase) {
        super(parent, modal);
        
        boolean loadPasswordImageSuccess = setPasswordImage();
        if (!loadPasswordImageSuccess) this.dispose();
        
        initComponents();
        this.setLocationRelativeTo(null);
        
        jTextField_Info.setText(passphrase);
    }
    
    private boolean setPasswordImage() {
                String path = Configuration.getImagePath();
        if (null == path) {
            JOptionPane.showMessageDialog(this, "Image Path is Bad! Set In Config!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (3 > path.length()) {
            JOptionPane.showMessageDialog(this, "Image Path is Too Short! Set In Config!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        File filePath = null;

        try {
            filePath = new File(path);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Image Path is Invalid!", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }

        if (null == filePath) {
            JOptionPane.showMessageDialog(this, "Image Path is NULL!", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!filePath.exists()) {
            JOptionPane.showMessageDialog(this, "Image Path dosn't exist!", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        BufferedImage image = null;
        try {
            image = ImageIO.read(filePath);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Unable To Load Image!", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable To Read Image!", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        
        if (null == image) {
            JOptionPane.showMessageDialog(this, "Image Is Null!", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
        image.getWidth();
        pasword_image = new LoadImage(image);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to Load Image!!", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        
        if (null == pasword_image) {
            JOptionPane.showMessageDialog(this, "Our Password Image JPanel is NULL!", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public static boolean getPassword(OTPassword theOutput) {
        if (null == password) {
            return false;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < password.length; i++) {
            str.append(password[i]);
        }
        if (str.length() > 0) {
            theOutput.setPassword(str.toString(), str.length());
        }
        // TODO: Security implications of using String here? (Fix.)
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(300, 0), new java.awt.Dimension(32767, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel_Image = pasword_image;
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jTextField_Info = new javax.swing.JTextField();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPasswordField_Password = new javax.swing.JPasswordField();
        jButton_OK = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Please Enter Your Password");
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(600, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(filler2, gridBagConstraints);

        jPanel_Image.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jPanel_ImageLayout = new javax.swing.GroupLayout(jPanel_Image);
        jPanel_Image.setLayout(jPanel_ImageLayout);
        jPanel_ImageLayout.setHorizontalGroup(
            jPanel_ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
        );
        jPanel_ImageLayout.setVerticalGroup(
            jPanel_ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel_Image, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(filler5, gridBagConstraints);

        jTextField_Info.setText("Password Info");
        jTextField_Info.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jTextField_Info, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(filler6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(filler7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPasswordField_Password, gridBagConstraints);

        jButton_OK.setText("OK");
        jButton_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OKActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jButton_OK, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(filler8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(filler9, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OKActionPerformed
        password = jPasswordField_Password.getPassword();
        this.dispose();
    }//GEN-LAST:event_jButton_OKActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OTPasswordDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OTPasswordDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OTPasswordDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OTPasswordDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                OTPasswordDialog dialog = new OTPasswordDialog(new javax.swing.JFrame(), true, "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton_OK;
    private javax.swing.JPanel jPanel_Image;
    private javax.swing.JPasswordField jPasswordField_Password;
    private javax.swing.JTextField jTextField_Info;
    // End of variables declaration//GEN-END:variables
}
