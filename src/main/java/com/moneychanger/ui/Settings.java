/**
 * **********************************************************
 * -----BEGIN PGP SIGNED MESSAGE----- Hash: SHA256
 *
 * M O N E Y C H A N G E R
 *
 * Open Transactions: Financial Cryptography and Digital Cash Library, Protocol,
 * API, Server, and GUI
 *
 * -- Anonymous Numbered Accounts. -- Untraceable Digital Cash. -- Triple-Signed
 * Receipts. -- Cheques, Vouchers, Transfers, Inboxes. -- Basket Currencies,
 * Markets, Payment Plans. -- Signed, XML, Ricardian-style Contracts.
 *
 * Copyright (C) 2010-2012 by "Fellow Traveler" (A pseudonym)
 *
 * EMAIL: FellowTraveler@rayservers.net
 *
 * FINGERPRINT: 9DD5 90EB 9292 4B48 0484 7910 0308 00ED F951 BB8E
 *
 * BITCOIN: 1NtTPVVjDsUfDWybS4BwvHpG2pdS9RnYyQ
 *
 * OFFICIAL PROJECT WIKI: https://github.com/FellowTraveler/Moneychanger
 * https://github.com/FellowTraveler/Open-Transactions/wiki
 *
 * WEBSITE: http://www.OpenTransactions.org/
 *
 * Components and licensing: -- Moneychanger..A Java client
 * GUI.....LICENSE:.....GPLv3 -- OTLib.........A class
 * library.......LICENSE:...LAGPLv3 -- OT-API........A client
 * API..........LICENSE:...LAGPLv3 -- testwallet....Command-line
 * client...LICENSE:...LAGPLv3 -- OT-Server.....Server
 * Application....LICENSE:....AGPLv3
 * Github.com/FellowTraveler/Open-Transactions/wiki/Components
 *
 * All of the above OT components were designed and written by Fellow Traveler,
 * with the exception of Moneychanger, which was contracted out to Vicky C
 * (livewire_3001@yahoo.com).
 *
 * -----------------------------------------------------
 *
 * LICENSE: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see: http://www.gnu.org/licenses/
 *
 * If you would like to use this software outside of the free software license,
 * please contact FellowTraveler. (Unfortunately many will run anonymously and
 * untraceably, so who could really stop them?)
 *
 * DISCLAIMER: This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * -----BEGIN PGP SIGNATURE----- Version: GnuPG v1.4.11 (Darwin)
 *
 * iQIcBAEBCAAGBQJOj7gfAAoJEAMIAO35UbuOs3sP/2rrjSdYu/AsXcgLK9/9CP4a
 * lIJfw3KLvybKZjZW5r5j+4xUlCYIqPZSI66PGDChGPMPFcZQN6M4Ddpn9kbctymS
 * sdTXvbdFhpbV6k2wSa1Fz97ygfXJc/7MDTmHYbZ53hVV8AoUBrCHWtVttkQD31o3
 * Pn/qGmy+jOgTvjEXhjEpV66pDkMWze1SiI1MArHUziCYoxItuM45x0EfzwQIqlo3
 * ku2R7rRTtqm47Dgea12psWrjbPS5XRL1Q8Hs38Z1J0JdFlfn6cJYe52Iiluzof6M
 * kCLhy6FH8QfIADfrKkFP48EIhnVquDlkV9AlJ1r217K3cpK2jEjlZUnGBECMAMEo
 * pSXXk1BLNgxsa4yaXCgHY92/MhgtcdCMLkcCq6MWUTGZsLGiWIiQGmO9mwBfNIlY
 * SawlIviuS5DiE/D16A290Byxhha/5e144cIiKm27fSQra8eogUXNfZdZeuv6n69v
 * t8QjeBjoLhe5/KnRNoGLpSXhPphsWLRSJBru77ZU2msHfmkNfcP2UoEUCfNTfTbE
 * XpyRfeyRVowVKeKunV9KUSHgdD5wa6RUeyodAbaHvWrFpIpNkaFIP9OwhRULpjx0
 * arwVNYucbX1qb2I8HBm2u+IRWQTONp74TFFjU0/CVAXu2DeJKY5mL4zDej35c5j9
 * AK+ZirdWhhoHeWR1tAkN =RcXP -----END PGP SIGNATURE-----
 * ************************************************************
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Settings.java
 *
 * Created on 4 Apr, 2011, 9:20:06 AM
 */
package com.moneychanger.ui;

import com.moneychanger.core.util.ConfigBean;
import com.moneychanger.core.util.Utility.ReturnAction;
import com.moneychanger.ui.Load.JavaPaths;
import com.moneychanger.ui.Load.typeOS;
import com.moneychanger.ui.LoadState.OutOfOrderException;
import com.moneychanger.ui.dialogs.PathDialog;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Vicky C and Cameron
 */
// <editor-fold desc="MainClass">>
public class Settings extends javax.swing.JFrame {

    private JFileChooser dataFolderChooser;
    private JFileChooser imageChooser;
    private ConfigBean _configBean;
    private JavaPaths _javaPaths;

    /**
     * Creates new form Settings
     */
    public Settings(ConfigBean configBean) {
        //Making everything statefull
        _configBean = configBean;

        // Pass The Config Bean To the Static Load Class
        try {
            Load.Init(_configBean);

            System.out.println("DISABLING AUTOLOAD FOR DEBUG!");
            _configBean.setConfig(ConfigBean.Keys.LastLoadSuccessfull, Boolean.FALSE.toString());

            // If we loaded successfull Last time... Attempt The Same Known Good Settings
            if (Boolean.parseBoolean(_configBean.getConfig(ConfigBean.Keys.LastLoadSuccessfull))) {
                try {
                    // Starting an autoload... lets set the Last Load Succssfull to false...
                    System.out.println("Last Load was Successful! Attempting Autoload!");
                    _configBean.setConfig(ConfigBean.Keys.LastLoadSuccessfull, Boolean.FALSE.toString());

                    Load.Attempt();
                    LoadMoneychangerGUI();
                } catch (Load.LoadFailedException e) {
                    System.out.println("We didn't load Successfuly, Showing Seetings...!");
                }
            } else {
                System.out.println("We didn't load Successfuly Last time, Showing Seetings...!");
            }

            // Load Up Settings Dialoug
            initSettings();
            loadSettings();

        } catch (OutOfOrderException e) {
            System.err.println(e.toString());
            System.out.println("SomethingBad Happend! We loaded up stuff in the wrong order!");
            System.exit(1);
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
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel_SettingTitle = new javax.swing.JLabel();
        jLabel_JavaPath = new javax.swing.JLabel();
        jTextField_JavaPath = new javax.swing.JTextField();
        jButton_JavaPath = new javax.swing.JButton();
        jLabel_ImagePath = new javax.swing.JLabel();
        jTextField_ImagePath = new javax.swing.JTextField();
        jButton_ImagePath = new javax.swing.JButton();
        jButton_LoadWallet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(Settings.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(700, 350));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel_SettingTitle.setFont(resourceMap.getFont("jLabel_SettingTitle.font")); // NOI18N
        jLabel_SettingTitle.setText(resourceMap.getString("jLabel_SettingTitle.text")); // NOI18N
        jLabel_SettingTitle.setName("jLabel_SettingTitle"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        getContentPane().add(jLabel_SettingTitle, gridBagConstraints);

        jLabel_JavaPath.setText(resourceMap.getString("jLabel_JavaPath.text")); // NOI18N
        jLabel_JavaPath.setName("jLabel_JavaPath"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        getContentPane().add(jLabel_JavaPath, gridBagConstraints);

        jTextField_JavaPath.setEditable(false);
        jTextField_JavaPath.setName("jTextField_JavaPath"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        getContentPane().add(jTextField_JavaPath, gridBagConstraints);

        jButton_JavaPath.setText(resourceMap.getString("jButton_JavaPath.text")); // NOI18N
        jButton_JavaPath.setName("jButton_JavaPath"); // NOI18N
        jButton_JavaPath.setPreferredSize(new java.awt.Dimension(100, 24));
        jButton_JavaPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_JavaPathActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        getContentPane().add(jButton_JavaPath, gridBagConstraints);

        jLabel_ImagePath.setText(resourceMap.getString("jLabel_ImagePath.text")); // NOI18N
        jLabel_ImagePath.setName("jLabel_ImagePath"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        getContentPane().add(jLabel_ImagePath, gridBagConstraints);

        jTextField_ImagePath.setEditable(false);
        jTextField_ImagePath.setName("jTextField_ImagePath"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        getContentPane().add(jTextField_ImagePath, gridBagConstraints);

        jButton_ImagePath.setText(resourceMap.getString("jButton_ImagePath.text")); // NOI18N
        jButton_ImagePath.setName("jButton_ImagePath"); // NOI18N
        jButton_ImagePath.setPreferredSize(new java.awt.Dimension(100, 24));
        jButton_ImagePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ImagePathActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        getContentPane().add(jButton_ImagePath, gridBagConstraints);

        jButton_LoadWallet.setText(resourceMap.getString("jButton_LoadWallet.text")); // NOI18N
        jButton_LoadWallet.setName("jButton_LoadWallet"); // NOI18N
        jButton_LoadWallet.setPreferredSize(new java.awt.Dimension(100, 24));
        jButton_LoadWallet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoadWalletActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        getContentPane().add(jButton_LoadWallet, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LoadWalletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadWalletActionPerformed

        // Stage: Settings Update
        try {
            updateSettings();
            this.setVisible(Boolean.FALSE);

            // Now That the Settings are Updated... Lets Try Loading again!
            Load.Attempt();
            LoadMoneychangerGUI();
        } catch (Load.LoadFailedException e) {
            System.err.println(e.toString());
            System.out.println("SomethingBad Happend! We couldn't load properly!");
            System.exit(1);
        } catch (OutOfOrderException e) {
            System.err.println(e.toString());
            System.out.println("SomethingBad Happend! We Loaded in the wrong order!");
            System.exit(1);
        }
    }//GEN-LAST:event_jButton_LoadWalletActionPerformed

    private void jButton_JavaPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_JavaPathActionPerformed
        new PathDialog(this, true, _javaPaths).setVisible(true);
        _configBean.setConfig(ConfigBean.Keys.JavaPathSet, Boolean.TRUE.toString());
    }//GEN-LAST:event_jButton_JavaPathActionPerformed

    private void jButton_ImagePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ImagePathActionPerformed

        int returnVal = imageChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            jTextField_ImagePath.setText(imageChooser.getSelectedFile().getPath());

        } else {
            System.out.println("Cancelled");
        }
    }//GEN-LAST:event_jButton_ImagePathActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Settings(new ConfigBean()).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ImagePath;
    private javax.swing.JButton jButton_JavaPath;
    private javax.swing.JButton jButton_LoadWallet;
    private javax.swing.JLabel jLabel_ImagePath;
    private javax.swing.JLabel jLabel_JavaPath;
    private javax.swing.JLabel jLabel_SettingTitle;
    private static javax.swing.JTextField jTextField_ImagePath;
    private static javax.swing.JTextField jTextField_JavaPath;
    // End of variables declaration//GEN-END:variables

    //<editor-fold defaultstate="collapsed" desc="Helpers">
    private void initSettings() throws OutOfOrderException {
        LoadState.Progress(LoadState.Stages.Opt_InitSettings);
        // Stage: Init
        initFileChoosers();
        initComponents();
        _javaPaths = new JavaPaths(new PathReturnAction());

        this.setLocationRelativeTo(null);
        this.setVisible(Boolean.TRUE);
        // All Done with Init!
        LoadState.setStageComplete();
    }

    private void initFileChoosers() {
        // Image Path
        imageChooser = new JFileChooser();

        imageChooser.setFileHidingEnabled(false);
        imageChooser.setCurrentDirectory(new java.io.File("."));
        imageChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    }

    // This method loads the configuration (if any) from the ConfigBean
    private void loadSettings() throws OutOfOrderException {

        // Set that are are going to Attempt to do this Stage now
        LoadState.Progress(LoadState.Stages.Opt_LoadSettings);
        if (LoadState.getStage() == LoadState.Stages.Opt_LoadSettings) {

            initFileChoosers();

            // User Data Path
            String userDataPath = _configBean.getConfig(ConfigBean.Keys.UserDataPath);
            if (userDataPath.isEmpty()) {
                userDataPath = new StringBuilder(Load.getUserAppDataLocation()).append("/.ot/client_data").toString();
            }
            
            // Java Path
            String javaPath = _configBean.getConfig(ConfigBean.Keys.JavaPath);
            
            System.out.println(System.getProperty("os.arch"));
            
            if (Boolean.parseBoolean(_configBean.getConfig(ConfigBean.Keys.JavaPathSet))) {
                if (null == javaPath || javaPath.isEmpty()) {
                    javaPath = "";
                }
            } else {
                if (Load.getOS() == typeOS.WIN) {
                    
                    File directory = new File (".");
                    
                    if (System.getProperty("os.arch").contentEquals("x86")) {
                        javaPath = ".\\lib\\Win32\\Release";
                    }

                    System.out.println(System.getProperty("os.arch"));
                    if (System.getProperty("os.arch").contentEquals("amd64")) {
                        javaPath = ".\\lib\\x64\\Release";
                    }
                } else {
                    javaPath = "";
                }
            }
                    

            Settings.jTextField_JavaPath.setText(javaPath);
            _javaPaths.addPaths(javaPath);

            // Password Image Path
            String imagePath = ConfigBean.Static.getKey(ConfigBean.Static.Keys.PasswordImagePath);
            if (imagePath == null
                    || imagePath.isEmpty()) {
                imagePath = "";
            }
            Settings.jTextField_ImagePath.setText(imagePath);

            // Set this Stage has Been Completed
            LoadState.setStageComplete();
        }
    }

    private void updateSettings() throws OutOfOrderException {
        LoadState.Progress(LoadState.Stages.Opt_UpdateSettings);

        _configBean.setConfig(ConfigBean.Keys.JavaPath, Settings.jTextField_JavaPath.getText());
        _configBean.setConfig(ConfigBean.Keys.JavaPathSet, "true");

        ConfigBean.Static.setKey(ConfigBean.Static.Keys.PasswordImagePath, Settings.jTextField_ImagePath.getText());;

        LoadState.setStageComplete();
    }

    private void LoadMoneychangerGUI() throws OutOfOrderException {
        LoadState.Progress(LoadState.Stages.LoadMoneychangerGUI);

        this.setVisible(Boolean.FALSE);
        ProgressBar progressBar = new ProgressBar();
        new Thread(progressBar).start();
        progressBar.setVisible(true);
        progressBar.pack();

        // since we have got this far... lets set the autoloader bit

        System.out.println("Looks like Load was Successful! Setting Autoload for next time!");
        _configBean.setConfig(ConfigBean.Keys.LastLoadSuccessfull, Boolean.TRUE.toString());


        this.dispose();
        LoadState.setStageComplete();
    }

    private class PathReturnAction implements ReturnAction {

        @Override
        public String getAction() {
            return Settings.jTextField_JavaPath.getText();
        }

        @Override
        public void returnAction(String returnValue) {
            Settings.jTextField_JavaPath.setText(returnValue);
        }
    }
    //</editor-fold>
}
