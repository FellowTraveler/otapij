/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PrefsPanel.java
 *
 * Created on 17/11/2011, 9:47:55 PM
 */
package com.ot.app.moneychanger.dialogs.panels;

import com.ot.app.moneychanger.controlers.PrefsController.ActionKeys;
import com.ot.app.moneychanger.controlers.PrefsController.FieldKeys;
import com.ot.app.moneychanger.models.viewmodel.IViewModel;
import java.awt.Color;
import net.sf.swinglib.actions.FieldValidationChange;

/**
 *
 * @author cameron
 */
public class PrefsPanel extends javax.swing.JPanel {
    
    private IViewModel<FieldKeys,ActionKeys> _viewModel;
    
    /** Creates new form PrefsPanel */
    public PrefsPanel(IViewModel<FieldKeys,ActionKeys> viewModel) {
        _viewModel = viewModel;
        initComponents();
        bindActions();
        bindFields();
        setToolTips();
    }
    
    private void bindActions()
    {
        jButton_OK.setAction(_viewModel.getButtonAction(ActionKeys.OK));
        jButton_Cancel.setAction(_viewModel.getButtonAction(ActionKeys.CANCEL));
        jButton_UserData.setAction(_viewModel.getButtonAction(ActionKeys.BROWSE));
        jButton_Libraries.setAction(_viewModel.getButtonAction(ActionKeys.LIBRARIES));
    }
    
    private void bindFields(){
        _viewModel.bindValidatedDoc(FieldKeys.TIMEOUT, jTextField_TimeOut.getDocument(), new FieldValidationChange(){
            @Override
            public void fieldValidationState(boolean valid) {
                if (valid)jTextField_TimeOut.setBackground(Color.white);
                if (!valid)jTextField_TimeOut.setBackground(Color.yellow);
            }
        });
        _viewModel.bindValidatedDoc(FieldKeys.USERDATA, jTextField_UserData.getDocument(), new FieldValidationChange(){
            @Override
            public void fieldValidationState(boolean valid) {
                if (valid)jTextField_UserData.setBackground(Color.white);
                if (!valid)jTextField_UserData.setBackground(Color.yellow);
            }
        });
        _viewModel.bindValidatedDoc(FieldKeys.WALLET, jTextField_Wallet.getDocument(), new FieldValidationChange(){
            @Override
            public void fieldValidationState(boolean valid) {
                if (valid)jTextField_Wallet.setBackground(Color.white);
                if (!valid)jTextField_Wallet.setBackground(Color.yellow);
            }
        });
        _viewModel.bindValidatedDoc(FieldKeys.OTLIB, jTextField_Libraries.getDocument(), new FieldValidationChange(){
            @Override
            public void fieldValidationState(boolean valid) {
                if (valid)jTextField_Libraries.setBackground(Color.white);
                if (!valid)jTextField_Libraries.setBackground(Color.yellow);
            }
        });
    }
    
    private void setToolTips()
    {
        jTextField_TimeOut.setToolTipText(_viewModel.getFieldInfo(FieldKeys.TIMEOUT));
        jTextField_Wallet.setToolTipText(_viewModel.getFieldInfo(FieldKeys.WALLET));
        jTextField_UserData.setToolTipText(_viewModel.getFieldInfo(FieldKeys.USERDATA));
        jTextField_Libraries.setToolTipText(_viewModel.getFieldInfo(FieldKeys.OTLIB));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        setMinimumSize(new java.awt.Dimension(400, 100));
        setName("PrefPanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 300));
        setLayout(new java.awt.GridBagLayout());

        jLabel_PrefPanel.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel_PrefPanel.setLabelFor(this);
        jLabel_PrefPanel.setText("Money Changer Settings");
        jLabel_PrefPanel.setName("jLabel_SettingsTitle"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        add(jLabel_PrefPanel, gridBagConstraints);

        jLabel_TimeOut.setLabelFor(jButton_UserData);
        jLabel_TimeOut.setText("TimeOut");
        jLabel_TimeOut.setName("jLabel_TimeOut"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        add(jLabel_TimeOut, gridBagConstraints);

        jTextField_TimeOut.setText("Set Timout Value (seconds)");
        jTextField_TimeOut.setName("jTextField_TimeOut"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 10);
        add(jTextField_TimeOut, gridBagConstraints);

        jLabel_Wallet.setLabelFor(jTextField_Wallet);
        jLabel_Wallet.setText("Wallet Name");
        jLabel_Wallet.setName("jLabel_Wallet"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        add(jLabel_Wallet, gridBagConstraints);

        jTextField_Wallet.setText("Wallet File Name (wallet.xml)");
        jTextField_Wallet.setName("jTextField_Wallet"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 10);
        add(jTextField_Wallet, gridBagConstraints);

        jLabel_UserData.setLabelFor(jLabel_Wallet);
        jLabel_UserData.setText("User Data Path");
        jLabel_UserData.setName("jLabel_UserData"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        add(jLabel_UserData, gridBagConstraints);

        jTextField_UserData.setEditable(false);
        jTextField_UserData.setText("User Data Path (Client_Data)");
        jTextField_UserData.setName("jTextField_UserData"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        add(jTextField_UserData, gridBagConstraints);

        jButton_UserData.setText("Browse");
        jButton_UserData.setName("jButton_UserData"); // NOI18N
        jButton_UserData.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        add(jButton_UserData, gridBagConstraints);

        jLabel_Libraries.setLabelFor(jLabel_UserData);
        jLabel_Libraries.setText("Library Paths");
        jLabel_Libraries.setName("jLabel_Libraries"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        add(jLabel_Libraries, gridBagConstraints);

        jTextField_Libraries.setEditable(false);
        jTextField_Libraries.setText("OTAPI-JAVA and ZMQ Locations");
        jTextField_Libraries.setName("jTextField_Libraries"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        add(jTextField_Libraries, gridBagConstraints);

        jButton_Libraries.setText("Add Path");
        jButton_Libraries.setName("jButton_Libraries"); // NOI18N
        jButton_Libraries.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        add(jButton_Libraries, gridBagConstraints);

        jButton_Cancel.setText("Cancel");
        jButton_Cancel.setName("jButton_Cancel"); // NOI18N
        jButton_Cancel.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jButton_Cancel, gridBagConstraints);

        jButton_OK.setText("OK");
        jButton_OK.setName("jButton_OK"); // NOI18N
        jButton_OK.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(jButton_OK, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JButton jButton_Cancel = new javax.swing.JButton();
    private final javax.swing.JButton jButton_Libraries = new javax.swing.JButton();
    private final javax.swing.JButton jButton_OK = new javax.swing.JButton();
    private final javax.swing.JButton jButton_UserData = new javax.swing.JButton();
    private final javax.swing.JLabel jLabel_Libraries = new javax.swing.JLabel();
    private final javax.swing.JLabel jLabel_PrefPanel = new javax.swing.JLabel();
    private final javax.swing.JLabel jLabel_TimeOut = new javax.swing.JLabel();
    private final javax.swing.JLabel jLabel_UserData = new javax.swing.JLabel();
    private final javax.swing.JLabel jLabel_Wallet = new javax.swing.JLabel();
    private final javax.swing.JTextField jTextField_Libraries = new javax.swing.JTextField();
    private final javax.swing.JTextField jTextField_TimeOut = new javax.swing.JTextField();
    private final javax.swing.JTextField jTextField_UserData = new javax.swing.JTextField();
    private final javax.swing.JTextField jTextField_Wallet = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
