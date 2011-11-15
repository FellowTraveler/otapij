/************************************************************
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA256

 *                 M O N E Y C H A N G E R
 *
 *  Open Transactions:
 *       Financial Cryptography and Digital Cash
 *       Library, Protocol, API, Server, and GUI 
 *    
 *    	 -- Anonymous Numbered Accounts.
 *    	 -- Untraceable Digital Cash.
 *    	 -- Triple-Signed Receipts.
 *    	 -- Cheques, Vouchers, Transfers, Inboxes.
 *    	 -- Basket Currencies, Markets, Payment Plans.
 *    	 -- Signed, XML, Ricardian-style Contracts.
 *    
 *  Copyright (C) 2010-2012 by "Fellow Traveler" (A pseudonym)
 *
 *  EMAIL:
 *  FellowTraveler@rayservers.net
 *  
 *  FINGERPRINT:
 *  9DD5 90EB 9292 4B48 0484  7910 0308 00ED F951 BB8E
 *
 *  BITCOIN:  1NtTPVVjDsUfDWybS4BwvHpG2pdS9RnYyQ
 *
 *  OFFICIAL PROJECT WIKI:
 *  https://github.com/FellowTraveler/Moneychanger
 *  https://github.com/FellowTraveler/Open-Transactions/wiki
 *
 *  WEBSITE:
 *  http://www.OpenTransactions.org/
 *    
 *  Components and licensing:
 *   -- Moneychanger..A Java client GUI.....LICENSE:.....GPLv3
 *   -- OTLib.........A class library.......LICENSE:...LAGPLv3 
 *   -- OT-API........A client API..........LICENSE:...LAGPLv3
 *   -- testwallet....Command-line client...LICENSE:...LAGPLv3
 *   -- OT-Server.....Server Application....LICENSE:....AGPLv3
 *  Github.com/FellowTraveler/Open-Transactions/wiki/Components
 *
 *  All of the above OT components were designed and written by
 *  Fellow Traveler, with the exception of Moneychanger, which
 *  was contracted out to Vicky C (livewire_3001@yahoo.com).
 *
 *  -----------------------------------------------------
 *
 *   LICENSE:
 *   This program is free software: you can redistribute it
 *   and/or modify it under the terms of the GNU General
 *   Public License as published by the Free Software
 *   Foundation, either version 3 of the License, or (at your
 *   option) any later version.
 *
 *   You should have received a copy of the GNU General
 *   Public License along with this program.  If not, see:
 *   http://www.gnu.org/licenses/
 *
 *   If you would like to use this software outside of the free
 *   software license, please contact FellowTraveler.
 *   (Unfortunately many will run anonymously and untraceably,
 *   so who could really stop them?)
 *   
 *   DISCLAIMER:
 *   This program is distributed in the hope that it will be
 *   useful, but WITHOUT ANY WARRANTY; without even the implied
 *   warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 *   PURPOSE.  See the GNU General Public License for more
 *   details.

-----BEGIN PGP SIGNATURE-----
Version: GnuPG v1.4.11 (Darwin)

iQIcBAEBCAAGBQJOj7gfAAoJEAMIAO35UbuOs3sP/2rrjSdYu/AsXcgLK9/9CP4a
lIJfw3KLvybKZjZW5r5j+4xUlCYIqPZSI66PGDChGPMPFcZQN6M4Ddpn9kbctymS
sdTXvbdFhpbV6k2wSa1Fz97ygfXJc/7MDTmHYbZ53hVV8AoUBrCHWtVttkQD31o3
Pn/qGmy+jOgTvjEXhjEpV66pDkMWze1SiI1MArHUziCYoxItuM45x0EfzwQIqlo3
ku2R7rRTtqm47Dgea12psWrjbPS5XRL1Q8Hs38Z1J0JdFlfn6cJYe52Iiluzof6M
kCLhy6FH8QfIADfrKkFP48EIhnVquDlkV9AlJ1r217K3cpK2jEjlZUnGBECMAMEo
pSXXk1BLNgxsa4yaXCgHY92/MhgtcdCMLkcCq6MWUTGZsLGiWIiQGmO9mwBfNIlY
SawlIviuS5DiE/D16A290Byxhha/5e144cIiKm27fSQra8eogUXNfZdZeuv6n69v
t8QjeBjoLhe5/KnRNoGLpSXhPphsWLRSJBru77ZU2msHfmkNfcP2UoEUCfNTfTbE
XpyRfeyRVowVKeKunV9KUSHgdD5wa6RUeyodAbaHvWrFpIpNkaFIP9OwhRULpjx0
arwVNYucbX1qb2I8HBm2u+IRWQTONp74TFFjU0/CVAXu2DeJKY5mL4zDej35c5j9
AK+ZirdWhhoHeWR1tAkN
=RcXP
-----END PGP SIGNATURE-----
 **************************************************************/

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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Vicky C and Cameron
 */
// <editor-fold desc="MainClass">>
public class Settings extends javax.swing.JFrame {

    private JFileChooser dataFolderChooser;
    private static Load.JavaPaths javaPaths;

    /** Creates new form Settings */
    public Settings() {
        
        //Try and load without settings dilogue:
        try {
            Load.loadOTAPI();
            Load.loadAppData();
            Load.setTimeout();
            Utility.setSettingsObj(this);
            new MainPage().setVisible(true);
        }
        catch (Load.ApiNotLoadedException e) {
            StringBuilder error = new StringBuilder();
            error.append("Autoload of from the Java Path failed!: ");
            error.append(System.getProperty("line.separator"));
            error.append(e.getError());
            System.out.println(error.toString());
            //JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
            loadSettings();
        } catch (Load.AppDataNotLoadedException e) {
            StringBuilder error = new StringBuilder();
            error.append("AutoLoad of your MoneyChanger user data failed; Choose the location here:");
            error.append(e.getError().replace(":", System.getProperty("line.separator")));
            System.out.println(error.toString());
            //JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
            loadSettings();
        } catch (Load.InvalidTimeOutException e) {
            StringBuilder error = new StringBuilder();
            error.append("Auto-Timout is invalid; you should never see this message: please contact us for support!");
            error.append(e.getError());
            System.out.println(error.toString());
            //JOptionPane.showMessageDialog(this, error, "Initialization Error", JOptionPane.ERROR_MESSAGE);
            loadSettings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        jButton_LoadWallet = new javax.swing.JButton();
        jTextField_JavaPath = new javax.swing.JTextField();
        jTextField_WalletFile = new javax.swing.JTextField();
        jLabel_JavaPath = new javax.swing.JLabel();
        jButton_DataFolder = new javax.swing.JButton();
        jLabel_WalletFile = new javax.swing.JLabel();
        jLabel_DataFolder = new javax.swing.JLabel();
        jTextField_DataFolder = new javax.swing.JTextField();
        jLabel_TimoutUnit = new javax.swing.JLabel();
        jTextField_Timeout = new javax.swing.JTextField();
        jLabel_Timeout = new javax.swing.JLabel();
        jLabel_SettingTitle = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(Settings.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        jPanel2.setName("jPanel_Settings"); // NOI18N

        jButton_LoadWallet.setText(resourceMap.getString("jButton_LoadWallet.text")); // NOI18N
        jButton_LoadWallet.setName("jButton_LoadWallet"); // NOI18N
        jButton_LoadWallet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoadWalletActionPerformed(evt);
            }
        });

        jTextField_JavaPath.setBackground(resourceMap.getColor("jTextField_JavaPath.background")); // NOI18N
        jTextField_JavaPath.setEditable(false);
        jTextField_JavaPath.setText(resourceMap.getString("jTextField_JavaPath.text")); // NOI18N
        jTextField_JavaPath.setName("jTextField_JavaPath"); // NOI18N

        jTextField_WalletFile.setText(resourceMap.getString("jTextField_WalletFile.text")); // NOI18N
        jTextField_WalletFile.setName("jTextField_WalletFile"); // NOI18N

        jLabel_JavaPath.setText(resourceMap.getString("jLabel_JavaPath.text")); // NOI18N
        jLabel_JavaPath.setName("jLabel_JavaPath"); // NOI18N

        jButton_DataFolder.setText(resourceMap.getString("jButton_DataFolder.text")); // NOI18N
        jButton_DataFolder.setName("jButton_DataFolder"); // NOI18N
        jButton_DataFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DataFolderActionPerformed(evt);
            }
        });

        jLabel_WalletFile.setText(resourceMap.getString("jLabel_WalletFile.text")); // NOI18N
        jLabel_WalletFile.setName("jLabel_WalletFile"); // NOI18N

        jLabel_DataFolder.setText(resourceMap.getString("jLabel_DataFolder.text")); // NOI18N
        jLabel_DataFolder.setName("jLabel_DataFolder"); // NOI18N

        jTextField_DataFolder.setBackground(resourceMap.getColor("jTextField_DataFolder.background")); // NOI18N
        jTextField_DataFolder.setEditable(false);
        jTextField_DataFolder.setText(resourceMap.getString("jTextField_DataFolder.text")); // NOI18N
        jTextField_DataFolder.setName("jTextField_DataFolder"); // NOI18N

        jLabel_TimoutUnit.setText(resourceMap.getString("jLabel_TimoutUnit.text")); // NOI18N
        jLabel_TimoutUnit.setName("jLabel_TimoutUnit"); // NOI18N

        jTextField_Timeout.setText(resourceMap.getString("jTextField_Timeout.text")); // NOI18N
        jTextField_Timeout.setName("jTextField_Timeout"); // NOI18N

        jLabel_Timeout.setText(resourceMap.getString("jLabel_Timeout.text")); // NOI18N
        jLabel_Timeout.setName("jLabel_Timeout"); // NOI18N

        jLabel_SettingTitle.setFont(resourceMap.getFont("jLabel_SettingTitle.font")); // NOI18N
        jLabel_SettingTitle.setText(resourceMap.getString("jLabel_SettingTitle.text")); // NOI18N
        jLabel_SettingTitle.setName("jLabel_SettingTitle"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(50, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_DataFolder)
                            .addComponent(jLabel_Timeout)
                            .addComponent(jLabel_WalletFile)
                            .addComponent(jLabel_JavaPath))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton_DataFolder)
                        .addGap(47, 47, 47)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_SettingTitle)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Timeout)
                            .addComponent(jTextField_JavaPath)
                            .addComponent(jTextField_WalletFile)
                            .addComponent(jTextField_DataFolder, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jButton_LoadWallet))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_TimoutUnit)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_DataFolder)
                    .addComponent(jLabel_SettingTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Timeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_TimoutUnit)
                    .addComponent(jLabel_Timeout))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_DataFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_DataFolder))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_WalletFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_WalletFile))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_JavaPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_JavaPath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_LoadWallet)
                .addContainerGap())
        );

        jPanel2.setBounds(20, 20, 397, 237);
        jLayeredPane1.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setLabel(resourceMap.getString("jMenu_Settings.label")); // NOI18N
        jMenu1.setName("jMenu_Settings"); // NOI18N
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LoadWalletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadWalletActionPerformed
        //Atempt to load with modifed settings:
        try {
            Load.loadOTAPI(javaPaths);
            Load.loadAppData(jTextField_DataFolder.getText(),jTextField_WalletFile.getText());
            Load.setTimeout(jTextField_Timeout.getText());
            Utility.setSettingsObj(this);
            new MainPage().setVisible(true);     
        }
        catch (Load.ApiNotLoadedException e) {
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
    private javax.swing.JButton jButton_LoadWallet;
    private javax.swing.JLabel jLabel_DataFolder;
    private javax.swing.JLabel jLabel_JavaPath;
    private javax.swing.JLabel jLabel_SettingTitle;
    private javax.swing.JLabel jLabel_Timeout;
    private javax.swing.JLabel jLabel_TimoutUnit;
    private javax.swing.JLabel jLabel_WalletFile;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField_DataFolder;
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
//        jMenu_Settings = new CustomMenu("Look & Feel");
        jTextField_Timeout.setText(String.valueOf(Configuration.getWaitTime()));
    }

    public static void setPath() {
        jTextField_JavaPath.setText(javaPaths.toString());
    }

    private void loadSettings() {
        javaPaths = new Load.JavaPaths();
        javaPaths.addDefultPath(Load.getOS());
        initComponents();
        Utility.setObj(this);
        setLocation(Utility.getLocation(this.getSize()));
        initFileChooser();
        setPath();
    }
}
  // </editor-fold>