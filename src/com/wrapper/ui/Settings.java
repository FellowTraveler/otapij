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
 *************************************************************
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
package com.wrapper.ui;

import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import com.wrapper.ui.custom.CustomMenu;
import com.wrapper.ui.dialogs.PathDialog;
import com.wrapper.ui.panels.OpenTransactionAccountTopPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import org.jvnet.substance.SubstanceProgressBarUI;

/**
 *
 * @author Vicky C and Cameron
 */
// <editor-fold desc="MainClass">>
public class Settings extends javax.swing.JFrame {

    private JFileChooser dataFolderChooser;
    private JFileChooser imageChooser;
    
    private boolean m_bSettingsLoaded;
    private boolean m_bComponentsInitialized;
    
    private static Load.JavaPaths javaPaths;
    
    private void loadSettings() {
        if (false == m_bSettingsLoaded) {
            javaPaths = new Load.JavaPaths();
            javaPaths.addDefaultPath(Load.getOS());
            if (false == m_bComponentsInitialized) {
                initComponents();
                m_bComponentsInitialized = true;
            }
            Utility.setObj(this);
            setLocation(Utility.getLocation(this.getSize()));
            initFileChooser();
            setPath();
            m_bSettingsLoaded = true;
        }
    }

    /**
     * Creates new form Settings
     */
    public Settings() {
        m_bSettingsLoaded = false;
        m_bComponentsInitialized = false;
       //Try and load without settings dilogue:
//        try {
////          Utility.setSettingsObj(this);
//
//            loadSettings();
//
//            Load.loadOTAPI();
//
//            Load.loadAllAppDataButWallet(this);
////          Load.loadAppData(this);
//
//            Load.setTimeout();
////            new MainPage().setVisible(true);
//        } catch (Load.ApiNotLoadedException e) {
//            StringBuilder error = new StringBuilder();
//            error.append("Autoload of the Java Path failed: ");
//            error.append(System.getProperty("line.separator"));
//            error.append(e.getError());
//            System.out.println(error.toString());
//            //JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
//            loadSettings();
//        } catch (Load.AppDataNotLoadedException e) {
//            StringBuilder error = new StringBuilder();
//            error.append("AutoLoad of your MoneyChanger user data failed; Choose the location here: ");
//            error.append(e.getError().replace(":", System.getProperty("line.separator")));
//            System.out.println(error.toString());
//            //JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
//            loadSettings();
//        } catch (Load.InvalidTimeOutException e) {
//            StringBuilder error = new StringBuilder();
//            error.append("Auto-Timout is invalid; you should never see this message: please contact us for support!");
//            error.append(e.getError());
//            System.out.println(error.toString());
//            //JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
//            loadSettings();
//        } //        catch (Load.ImageNotLoadedException e) {
//        //            StringBuilder error = new StringBuilder();
//        //            error.append("Autoload of image failed: ");
//        //            error.append(System.getProperty("line.separator"));
//        //            error.append(e.getError());
//        //            System.out.println(error.toString());
//        //            //JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
//        //        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        Utility.setSettingsObj(this);
        loadSettings();

     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_SettingTitle = new javax.swing.JLabel();
        jLabel_Timeout = new javax.swing.JLabel();
        jTextField_Timeout = new javax.swing.JTextField();
        jLabel_TimoutUnit = new javax.swing.JLabel();
        jLabel_DataFolder = new javax.swing.JLabel();
        jTextField_DataFolder = new javax.swing.JTextField();
        jButton_DataFolder = new javax.swing.JButton();
        jLabel_WalletFile = new javax.swing.JLabel();
        jTextField_WalletFile = new javax.swing.JTextField();
        jLabel_JavaPath = new javax.swing.JLabel();
        jTextField_JavaPath = new javax.swing.JTextField();
        jButton_JavaPath = new javax.swing.JButton();
        jButton_LoadWallet = new javax.swing.JButton();
        jTextField_ImagePath = new javax.swing.JTextField();
        jLabel_JavaPath1 = new javax.swing.JLabel();
        jButton_DataFolder1 = new javax.swing.JButton();
        jMenuBar_Setting = new javax.swing.JMenuBar();
        jMenu1 = new com.wrapper.ui.custom.CustomMenu("Look & Feel");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(Settings.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel_SettingTitle.setFont(resourceMap.getFont("jLabel_SettingTitle.font")); // NOI18N
        jLabel_SettingTitle.setText(resourceMap.getString("jLabel_SettingTitle.text")); // NOI18N
        jLabel_SettingTitle.setName("jLabel_SettingTitle"); // NOI18N

        jLabel_Timeout.setText(resourceMap.getString("jLabel_Timeout.text")); // NOI18N
        jLabel_Timeout.setName("jLabel_Timeout"); // NOI18N

        jTextField_Timeout.setText(resourceMap.getString("jTextField_Timeout.text")); // NOI18N
        jTextField_Timeout.setName("jTextField_Timeout"); // NOI18N

        jLabel_TimoutUnit.setText(resourceMap.getString("jLabel_TimoutUnit.text")); // NOI18N
        jLabel_TimoutUnit.setName("jLabel_TimoutUnit"); // NOI18N

        jLabel_DataFolder.setText(resourceMap.getString("jLabel_DataFolder.text")); // NOI18N
        jLabel_DataFolder.setName("jLabel_DataFolder"); // NOI18N

        jTextField_DataFolder.setBackground(resourceMap.getColor("jTextField_DataFolder.background")); // NOI18N
        jTextField_DataFolder.setEditable(false);
        jTextField_DataFolder.setText(resourceMap.getString("jTextField_DataFolder.text")); // NOI18N
        jTextField_DataFolder.setName("jTextField_DataFolder"); // NOI18N

        jButton_DataFolder.setText(resourceMap.getString("jButton_DataFolder.text")); // NOI18N
        jButton_DataFolder.setName("jButton_DataFolder"); // NOI18N
        jButton_DataFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DataFolderActionPerformed(evt);
            }
        });

        jLabel_WalletFile.setText(resourceMap.getString("jLabel_WalletFile.text")); // NOI18N
        jLabel_WalletFile.setName("jLabel_WalletFile"); // NOI18N

        jTextField_WalletFile.setText(resourceMap.getString("jTextField_WalletFile.text")); // NOI18N
        jTextField_WalletFile.setName("jTextField_WalletFile"); // NOI18N

        jLabel_JavaPath.setText(resourceMap.getString("jLabel_JavaPath.text")); // NOI18N
        jLabel_JavaPath.setName("jLabel_JavaPath"); // NOI18N

        jTextField_JavaPath.setBackground(resourceMap.getColor("jTextField_JavaPath.background")); // NOI18N
        jTextField_JavaPath.setEditable(false);
        jTextField_JavaPath.setText(resourceMap.getString("jTextField_JavaPath.text")); // NOI18N
        jTextField_JavaPath.setName("jTextField_JavaPath"); // NOI18N

        jButton_JavaPath.setText(resourceMap.getString("jButton_JavaPath.text")); // NOI18N
        jButton_JavaPath.setName("jButton_JavaPath"); // NOI18N
        jButton_JavaPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_JavaPathActionPerformed(evt);
            }
        });

        jButton_LoadWallet.setText(resourceMap.getString("jButton_LoadWallet.text")); // NOI18N
        jButton_LoadWallet.setName("jButton_LoadWallet"); // NOI18N
        jButton_LoadWallet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoadWalletActionPerformed(evt);
            }
        });

        jTextField_ImagePath.setEditable(false);
        jTextField_ImagePath.setName("jTextField_ImagePath"); // NOI18N

        jLabel_JavaPath1.setText(resourceMap.getString("jLabel_JavaPath1.text")); // NOI18N
        jLabel_JavaPath1.setName("jLabel_JavaPath1"); // NOI18N

        jButton_DataFolder1.setText(resourceMap.getString("jButton_DataFolder1.text")); // NOI18N
        jButton_DataFolder1.setName("jButton_DataFolder1"); // NOI18N
        jButton_DataFolder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DataFolder1ActionPerformed(evt);
            }
        });

        jMenuBar_Setting.setName("jMenuBar_Setting"); // NOI18N

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N
        jMenuBar_Setting.add(jMenu1);

        setJMenuBar(jMenuBar_Setting);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jLabel_SettingTitle)
                .addContainerGap(223, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Timeout)
                    .addComponent(jLabel_WalletFile)
                    .addComponent(jLabel_JavaPath)
                    .addComponent(jLabel_DataFolder)
                    .addComponent(jLabel_JavaPath1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField_Timeout)
                        .addComponent(jTextField_JavaPath)
                        .addComponent(jTextField_WalletFile)
                        .addComponent(jTextField_DataFolder, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addComponent(jTextField_ImagePath, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_JavaPath)
                    .addComponent(jButton_DataFolder)
                    .addComponent(jLabel_TimoutUnit)
                    .addComponent(jButton_DataFolder1))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jButton_LoadWallet)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_SettingTitle)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Timeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_TimoutUnit)
                    .addComponent(jLabel_Timeout))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_DataFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_DataFolder)
                    .addComponent(jLabel_DataFolder))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_WalletFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_WalletFile))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_JavaPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_JavaPath)
                    .addComponent(jLabel_JavaPath))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_ImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_JavaPath1)
                    .addComponent(jButton_DataFolder1))
                .addGap(27, 27, 27)
                .addComponent(jButton_LoadWallet)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LoadWalletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadWalletActionPerformed
        //Atempt to load with modifed settings:
        try {
            Utility.setSettingsObj(this);

            System.out.println(javaPaths.toString());

            Load.loadOTAPI(javaPaths);
            Load.loadAppData(this, jTextField_DataFolder.getText(), jTextField_WalletFile.getText());

            Load.setTimeout(jTextField_Timeout.getText());
            // ---------------------------

            String path = jTextField_ImagePath.getText();
            BufferedImage image = null;

            if ((null == path) || (path.length() <= 0)) {
                path = Configuration.getImagePath();
            }
            // ---------------------------------

            if ((null != path) && (path.length() > 0)) {
                try {
                    File f = new File(path);

                    if (null == f) {
                        System.out.println("jButton_LoadWalletActionPerformed: new File(path) returned null. Path: " + path);
                    } else {
                        image = ImageIO.read(f);
                        image.getWidth();
                        // --------------------------------
                        boolean status = Utility.saveImagePath(path);
                        System.out.println("Status of image path persistence:" + status);
                        // -------------------------
                        new MainPage().setVisible(true);
             //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                                    // UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

//  com.wrapper.ui.ProgressBar progressBar = new com.wrapper.ui.ProgressBar();
//                        new Thread(progressBar).start();
//                        progressBar.setVisible(true);
//                        progressBar.pack();
                    }
                    // ------------------------------------------------------
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Invalid image file. Please select proper image", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } catch (Load.ApiNotLoadedException e) {
            StringBuilder error = new StringBuilder();
            error.append("Unable to load your Java Path!");
            error.append(System.getProperty("line.separator"));
            error.append(e.getError());
            JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Load.AppDataNotLoadedException e) {
            StringBuilder error = new StringBuilder();
            error.append("Loading your MoneyChanger user data failed; Please Choose the correct location:");
            error.append(e.getError().replace(":", System.getProperty("line.separator")));
            JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Load.InvalidTimeOutException e) {
            StringBuilder error = new StringBuilder();
            error.append("Auto-Timout is invalid; you should never see this message: please contact us for support!");
            error.append(e.getError());
            JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }



// <editor-fold defaultstate="collapsed" desc="//Old Code">
//        try {
//            /* OTCaller g_theCaller = new OTCaller();
//            OTCallback g_theCallback = new JavaCallback();
//            Utility.setG_theCallback(g_theCallback);
//            Utility.setG_theCaller(g_theCaller);*/
//            boolean check = true;
//            long waitTime = Configuration.getWaitTime();
//            if (!check) {
//                /*Utility.addDirToRuntime("C:\\~\\Open-Transactions\\testwallet");
//                Utility.setDataFolder("C:\\~\\Open-Transactions\\testwallet\\data_folder");
//                System.loadLibrary("libzmq");*/
//                Utility.addDirToRuntime("/Users/administrator/Open-Transactions/testwallet");
//                //Utility.setDataFolder("/Users/Administrator/.ot/client_data");
//                Utility.setDataFolder("~/.ot/client_data");
//                System.loadLibrary("otapi");
//
//                otapi.OT_API_Init("~/.ot/client_data");
//                //otapi.OT_API_Init("/Users/Administrator/.ot/client_data");
//
//                //otapi.OT_API_Init("/Users/administrator/Open-Transactions/testwallet/data_folder");
//                //otapi.InitDefaultStorage(StorageType.STORE_FILESYSTEM, PackType.PACK_PROTOCOL_BUFFERS, "C:\\~\\Open-Transactions\\testwallet\\data_folder", "wallet.xml");
//
//                OTCaller g_theCaller = new OTCaller();
//                OTCallback g_theCallback = new JavaCallback();
//                g_theCaller.setCallback(g_theCallback);
//                otapi.OT_API_Set_PasswordCallback(g_theCaller);
//                Utility.setG_theCallback(g_theCallback);
//                Utility.setG_theCaller(g_theCaller);
//
//                otapi.OT_API_LoadWallet("wallet.xml");
//            } else {
//                try {
//
//
//                    //Java Path
//                    Utility.addDirToRuntime(jTextField_JavaPath.getText(), true);
//                    System.out.println("PATH:" + System.getProperty("java.library.path"));
//                    if (System.getProperty("os.name") != null && (System.getProperty("os.name").startsWith("windows")
//                            || System.getProperty("os.name").startsWith("Windows"))) {
//
//                        System.loadLibrary("libzmq");
//                    }
//                    System.out.println(" Before otapi load");
//                    System.loadLibrary("otapi");
//                    System.out.println(" After otapi load");
//                    boolean success = otapi.OT_API_Init(jTextField_DataFolder.getText()) == 1 ? true : false;
//                    // Uncomment below after fix
//                    /*if (!success) {
//                    JOptionPane.showMessageDialog(this, "Invalid Data Folder", "Initialization Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                    
//                    }*/
//
//                    OTCaller g_theCaller = new OTCaller();
//                    OTCallback g_theCallback = new JavaCallback();
//                    g_theCaller.setCallback(g_theCallback);
//                    otapi.OT_API_Set_PasswordCallback(g_theCaller);
//                    Utility.setG_theCallback(g_theCallback);
//                    Utility.setG_theCaller(g_theCaller);
//
//                    success = otapi.OT_API_LoadWallet(jTextField_WalletFile.getText()) == 1 ? true : false;
//
//                    if (!success) {
//                        JOptionPane.showMessageDialog(this, "Invalid Wallet File", "Initialization Error", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (java.lang.UnsatisfiedLinkError use) {
//                    JOptionPane.showMessageDialog(this, "Invalid library path", "Initialization Error", JOptionPane.ERROR_MESSAGE);
//                    use.printStackTrace();
//                    return;
//                }
//                Utility.setDataFolder(jTextField_DataFolder.getText());
//
//            }
//            Configuration.setWaitTime(waitTime);
//
//            //Utility.setDataFolder(jTextField1.getText());
//            Utility.setSettingsObj(this);
//            //this.dispose();
//            /*Utility.addDirToRuntime("/opt/local/lib");
//            Utility.addDirToRuntime("/Users/Chris/Projects/Open-Transactions/testwallet");
//            System.loadLibrary("otapi");
//            otapi.OT_API_Init("/Users/Chris/Projects/Open-Transactions/testwallet/data_folder");
//            otapi.OT_API_LoadWallet("wallet.xml");*/
//
//            new MainPage().setVisible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
// </editor-fold>
    }//GEN-LAST:event_jButton_LoadWalletActionPerformed

    private void jButton_DataFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DataFolderActionPerformed

        int returnVal = dataFolderChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // file = contractFileChooser.getSelectedFile();
            //This is where a real application would open the file.
            jTextField_DataFolder.setText(dataFolderChooser.getSelectedFile().getPath());

        } else {
            System.out.println("Cancelled");
        }
    }//GEN-LAST:event_jButton_DataFolderActionPerformed

    private void jButton_JavaPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_JavaPathActionPerformed
        new PathDialog(this, true, javaPaths).setVisible(true);
    }//GEN-LAST:event_jButton_JavaPathActionPerformed

    public void setImagePath(String strPath) {
        jTextField_ImagePath.setText(strPath);
    }

    private void jButton_DataFolder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DataFolder1ActionPerformed

        int returnVal = imageChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // file = contractFileChooser.getSelectedFile();
            //This is where a real application would open the file.

            jTextField_ImagePath.setText(imageChooser.getSelectedFile().getPath());
            Configuration.setImagePath(imageChooser.getSelectedFile().getPath());

            // --------------------------------
            if (Load.IsOTInitialized()) {
                boolean status = Utility.saveImagePath(jTextField_ImagePath.getText());
                System.out.println("jButton_DataFolder1ActionPerformed: Status of image path persistence:" + status);
            } else {
                System.out.println("jButton_DataFolder1ActionPerformed: Status of image path persistence: UNABLE to save (at this point) because OTAPI isn't loaded yet.");
            }
            // -------------------------

        } else {
            System.out.println("Cancelled");
        }
    }//GEN-LAST:event_jButton_DataFolder1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Settings().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_DataFolder;
    private javax.swing.JButton jButton_DataFolder1;
    private javax.swing.JButton jButton_JavaPath;
    private javax.swing.JButton jButton_LoadWallet;
    private javax.swing.JLabel jLabel_DataFolder;
    private javax.swing.JLabel jLabel_JavaPath;
    private javax.swing.JLabel jLabel_JavaPath1;
    private javax.swing.JLabel jLabel_SettingTitle;
    private javax.swing.JLabel jLabel_Timeout;
    private javax.swing.JLabel jLabel_TimoutUnit;
    private javax.swing.JLabel jLabel_WalletFile;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar_Setting;
    private javax.swing.JTextField jTextField_DataFolder;
    private static javax.swing.JTextField jTextField_ImagePath;
    private static javax.swing.JTextField jTextField_JavaPath;
    private javax.swing.JTextField jTextField_Timeout;
    private javax.swing.JTextField jTextField_WalletFile;
    // End of variables declaration//GEN-END:variables

    private void initFileChooser() {

        StringBuilder sb = new StringBuilder();

        sb.append(Load.appdataDirectory(Load.getOS()));
        sb.append("/.ot/client_data");

        jTextField_DataFolder.setText(sb.toString());

        dataFolderChooser = new JFileChooser();

        dataFolderChooser.setFileHidingEnabled(false);
        dataFolderChooser.setCurrentDirectory(new java.io.File("."));
        dataFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        imageChooser = new JFileChooser();

        imageChooser.setFileHidingEnabled(false);
        imageChooser.setCurrentDirectory(new java.io.File("."));
        imageChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        jMenu1 = new CustomMenu("Look & Feel");
        jTextField_Timeout.setText(String.valueOf(Configuration.getWaitTime()));
    }

    public static void setPath() {
        jTextField_JavaPath.setText(javaPaths.toString());
    }

 }
// </editor-fold>

