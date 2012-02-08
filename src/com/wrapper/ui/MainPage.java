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
 * MainPage.java
 *
 * Created on 13 Mar, 2011, 10:22:07 PM
 */
package com.wrapper.ui;
//Recurring, Deed/Title, Escrow, Ripple, Settings, Credits
import com.wrapper.core.Account;
import com.wrapper.core.Basket;
import com.wrapper.core.Contract;
import com.wrapper.core.Market;
import com.wrapper.core.NYM;
import com.wrapper.core.OpenTransactionAccount;
import com.wrapper.core.RippleAccount;
import com.wrapper.core.dataobjects.BitcoinDetails;
import com.wrapper.core.dataobjects.CashPurseDetails;
import com.wrapper.core.dataobjects.MarketDetails;
import com.wrapper.core.dataobjects.MarketTicker;
import com.wrapper.core.dataobjects.NymOfferDetails;
import com.wrapper.core.dataobjects.OTDetails;
import com.wrapper.core.datastore.StorageHelper;
import com.wrapper.core.util.ComboObject;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import com.wrapper.ui.dialogs.AccountAdditionDialog;
import com.wrapper.ui.dialogs.AccountEditDialog;
import com.wrapper.ui.dialogs.AddBasketDialog;
import com.wrapper.ui.dialogs.AddressBookDialog;
import com.wrapper.ui.dialogs.AssetContractEditDialog;
import com.wrapper.ui.dialogs.ContractAdditionDialog;
import com.wrapper.ui.dialogs.CreateMarketOrder;
import com.wrapper.ui.dialogs.DeleteNYMDialog;
import com.wrapper.ui.dialogs.ImportNYMDialog;
import com.wrapper.ui.dialogs.IssueAssetContractDialog;
import com.wrapper.ui.dialogs.NYMAdditionDialog;
import com.wrapper.ui.dialogs.NYMEditDialog;
import com.wrapper.ui.dialogs.NymBoxDetailsDialog;
import com.wrapper.ui.dialogs.OtherTabAccountEditDialog;
import com.wrapper.ui.dialogs.OtherTabServerAddDialog;
import com.wrapper.ui.dialogs.OtherTabServerEditDialog;
import com.wrapper.ui.dialogs.RegisterNYMDialog;
import com.wrapper.ui.dialogs.RegisterNymOnServerDialog;
import com.wrapper.ui.dialogs.SendMessageDialog;
import com.wrapper.ui.dialogs.ServerContractEditDialog;
import com.wrapper.ui.model.AccountTableModel;
import com.wrapper.ui.model.AssetContractTableModel;
import com.wrapper.ui.model.BasketTableModel;
import com.wrapper.ui.model.MarketAskTableModel;
import com.wrapper.ui.model.MarketBidTableModel;
import com.wrapper.ui.model.MarketOffersTableModel;
import com.wrapper.ui.model.MarketRecentTradesTableModel;
import com.wrapper.ui.model.MarketTableModel;
import com.wrapper.ui.model.MarketTradesTableModel;
import com.wrapper.ui.model.NYMBoxTableModel;
import com.wrapper.ui.model.NYMOutboxTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import com.wrapper.ui.model.NYMTableModel;
import com.wrapper.ui.model.OtherTabAccountModel;
import com.wrapper.ui.model.OtherTabServerTableModel;
import com.wrapper.ui.model.ServerContractTableModel;
import com.wrapper.ui.panels.BitcoinAccountBottomPanel;
import com.wrapper.ui.panels.BitcoinAccountTopPanel;
import com.wrapper.ui.panels.BlankPanel;
import com.wrapper.ui.panels.CashPurseAccountBottomPanel;
import com.wrapper.ui.panels.CashPurseAccountTopPanel;
import com.wrapper.ui.panels.OpenTransactionAccountBottomPanel;
import com.wrapper.ui.panels.OpenTransactionAccountTopPanel;
import com.wrapper.ui.panels.RippleAccountTopPanel;
import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;

/**
 *
 * @author Vicky C
 */
public class MainPage extends javax.swing.JFrame {

    public static void refreshMarketNym(String serverID) {
        System.out.println("In refreshMarketNym, serverID:" + serverID);
        String currentServerID = "ALL";

        if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
            currentServerID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
        }
        if (serverID != null && serverID.equals(currentServerID)) {
            nymRegisteredMap = new NYM().loadRegisteredNYM(serverID);
            Utility.populateComboWithoutAll(nymRegisteredMap, jComboBox6);
        }
    }
    private Map nymBox;
    private static Map nymOutBox;
    private static boolean isBasketInit = false;
    TrayIcon trayIcon;
    SystemTray tray;

    /** Creates new form MainPage */
    public MainPage() {
        // this.setExtendedState(MAXIMIZED_BOTH);
        super("SystemTray test");

        try {

            //SubstanceLookAndFeel laf = new SubstanceModerateLookAndFeel();
            //UIManager.setLookAndFeel(laf);
            
            if (false == System.getProperty("os.name").toLowerCase().contains("linux"))
                setToSystray();
            // ---------------------------------
            setTitle("Moneychanger");
            initComponents();
            initMainTab();
            initOtherTab();
            initNYMSTab();
            initContractsTab();
            initMarketsTab(); 
            //initBasketsTab();
            initSettingsTab();
            initCreditsTab();
            setResizable(false);
            setLocation(Utility.getLocation(this.getSize()));
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //((JFrame) Utility.getSettingsObj()).dispose();
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private void setToSystray() {

        Image image = null;

        System.out.println("creating instance");

        if (SystemTray.isSupported()) {
            System.out.println("system tray supported");
            tray = SystemTray.getSystemTray();

            ImageIcon image1 = new javax.swing.ImageIcon(getClass().getResource("/com/wrapper/ui/images/images.jpeg"));
            image = image1.getImage();
            ActionListener exitListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting....");
                    System.exit(0);
                }
            };
            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            defaultItem = new MenuItem("Open");
            defaultItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //tray.remove(trayIcon);
                    setVisible(true);
                    repaint();
                    setVisible(true);
                    System.out.println("Open");

                }
            });
            popup.add(defaultItem);
            trayIcon = new TrayIcon(image, "Moneychanger", popup);
            trayIcon.setImageAutoSize(true);
        } else {
            System.out.println("system tray not supported");
        }
        final String os = System.getProperty("os.name").toLowerCase();
        addWindowStateListener(new WindowStateListener() {

            @Override
            public void windowStateChanged(WindowEvent e) {
                System.out.println("-----:" + e.getNewState());
                if (e.getNewState() == ICONIFIED) {
                    try {
                        tray.add(trayIcon);
                        if (os.indexOf("nix") < 0 || os.indexOf("nux") < 0) {
                            setVisible(false);
                        }
                        System.out.println("added to SystemTray");
                    } catch (AWTException ex) {
                        System.out.println("unable to add to tray");
                    }
                }
                if (e.getNewState() == 7) {
                    try {
                        tray.add(trayIcon);
                        if (!(os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0)) {
                            setVisible(false);
                        }
                        System.out.println("added to SystemTray");
                    } catch (AWTException ex) {
                        System.out.println("unable to add to system tray");
                    }
                }
                if (e.getNewState() == MAXIMIZED_BOTH) {
                    tray.remove(trayIcon);
                    setVisible(true);
                    System.out.println("Max both");
                    System.out.println("Tray icon removed");
                }
                if (e.getNewState() == NORMAL) {
                    tray.remove(trayIcon);
                    setVisible(true);
                    System.out.println("Max NORMAL");

                    System.out.println("Tray icon removed");
                }
            }
        });
        //  setIconImage(Toolkit.getDefaultToolkit().getImage("Duke256.png"));
        if (image != null) {
            setIconImage(image);
        }

    }

    protected void setState() {
        this.setState(JFrame.NORMAL);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new com.wrapper.ui.custom.SteppedComboBox();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable5 = new com.wrapper.ui.custom.CustomTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new com.wrapper.ui.custom.SteppedComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane14 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jComboBox3 = new com.wrapper.ui.custom.SteppedComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new com.wrapper.ui.custom.CustomTable();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable8 = new com.wrapper.ui.custom.CustomTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable10 = new com.wrapper.ui.custom.CustomTable();
        jPanel7 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable7 = new com.wrapper.ui.custom.CustomTable();
        jPanel14 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable11 = new com.wrapper.ui.custom.CustomTable();
        jPanel5 = new javax.swing.JPanel();
        jComboBox7 = new com.wrapper.ui.custom.SteppedComboBox();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        jTable19 = new com.wrapper.ui.custom.CustomTable();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel54 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jPanel36 = new javax.swing.JPanel();
        jButton30 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jPanel30 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable15 = new com.wrapper.ui.custom.CustomTable();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTable17 = new com.wrapper.ui.custom.CustomTable();
        jPanel35 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTable18 = new com.wrapper.ui.custom.CustomTable();
        jLabel46 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable13 = new com.wrapper.ui.custom.CustomTable();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable14 = new com.wrapper.ui.custom.CustomTable();
        jComboBox5 = new com.wrapper.ui.custom.SteppedComboBox();
        jLabel24 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel32 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable16 = new com.wrapper.ui.custom.CustomTable();
        jLabel3 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jComboBox6 = new com.wrapper.ui.custom.SteppedComboBox();
        jLabel52 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new com.wrapper.ui.custom.CustomTable();
        jPanel25 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new com.wrapper.ui.custom.CustomTable();
        jPanel26 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane17 = new javax.swing.JScrollPane();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jPanel28 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel24 = new javax.swing.JPanel();

        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel12.setName("jPanel12"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainPage.class);
        jTextField6.setText(resourceMap.getString("jTextField6.text")); // NOI18N
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jScrollPane9.setName("jScrollPane9"); // NOI18N

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jTextArea5.setName("jTextArea5"); // NOI18N
        jScrollPane9.setViewportView(jTextArea5);

        jLabel10.setText("ID");
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText("Misc");
        jLabel11.setName("jLabel11"); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setName("jPanel1"); // NOI18N

        jComboBox1.setSelectedItem("ALL");
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jScrollPane12.setName("jScrollPane12"); // NOI18N

        jTable5.setModel(new AccountTableModel());
        jTable5.setName("jTable5"); // NOI18N
        jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable5);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setName("jSeparator3"); // NOI18N

        jLabel4.setText("NYMS");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Server Contracts");
        jLabel5.setName("jLabel5"); // NOI18N

        jComboBox2.setSelectedItem("ALL");
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jSeparator2.setName("jSeparator2"); // NOI18N

        jScrollPane14.setName("jScrollPane14"); // NOI18N

        jPanel8.setName("jPanel8"); // NOI18N
        jPanel8.setVisible(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        jScrollPane14.setViewportView(jPanel8);

        jScrollPane13.setName("jScrollPane13"); // NOI18N

        jPanel15.setName("jPanel15"); // NOI18N
        jPanel15.setVisible(false);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 735, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        jScrollPane13.setViewportView(jPanel15);

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setName("jPanel16"); // NOI18N

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wrapper/ui/images/pencil.jpg"))); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setToolTipText(resourceMap.getString("jButton10.toolTipText")); // NOI18N
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setToolTipText("Add");
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setToolTipText(resourceMap.getString("jButton12.toolTipText")); // NOI18N
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setText("Asset Contracts");
        jLabel16.setName("jLabel16"); // NOI18N

        jComboBox3.setSelectedItem("ALL");
        jComboBox3.setName("jComboBox3"); // NOI18N
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, 69, Short.MAX_VALUE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                        .addGap(37, 37, 37))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(128, 128, 128))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel9.setName("jPanel9"); // NOI18N

        jTextField4.setEditable(false);
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setEditable(false);
        jTextArea3.setRows(5);
        jTextArea3.setName("jTextArea3"); // NOI18N
        jScrollPane7.setViewportView(jTextArea3);

        jLabel6.setText("ID");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("Misc");
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.setName("jPanel10"); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wrapper/ui/images/pencil.jpg"))); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setToolTipText(resourceMap.getString("jButton4.toolTipText")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setToolTipText(resourceMap.getString("jButton5.toolTipText")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setToolTipText(resourceMap.getString("jButton6.toolTipText")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jButton5)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setModel(new NYMTableModel());
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jSeparator4.setName("jSeparator4"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N

        jTabbedPane2.setName("jTabbedPane2"); // NOI18N
        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jScrollPane15.setName("jScrollPane15"); // NOI18N

        jTable8.setModel(new NYMBoxTableModel());
        jTable8.setToolTipText(resourceMap.getString("jTable8.toolTipText")); // NOI18N
        jTable8.setName("jTable8"); // NOI18N
        jTable8.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable8MouseEntered(evt);
            }
        });
        jScrollPane15.setViewportView(jTable8);

        jTabbedPane2.addTab(resourceMap.getString("jScrollPane15.TabConstraints.tabTitle"), jScrollPane15); // NOI18N

        jScrollPane16.setName("jScrollPane16"); // NOI18N

        jTable10.setModel(new NYMOutboxTableModel());
        jTable10.setToolTipText(resourceMap.getString("jTable10.toolTipText")); // NOI18N
        jTable10.setName("jTable10"); // NOI18N
        jTable10.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable10MouseEntered(evt);
            }
        });
        jScrollPane16.setViewportView(jTable10);

        jTabbedPane2.addTab(resourceMap.getString("jScrollPane16.TabConstraints.tabTitle"), jScrollPane16); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setName("jPanel7"); // NOI18N

        jButton16.setText(resourceMap.getString("jButton16.text")); // NOI18N
        jButton16.setName("jButton16"); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16)
                .addContainerGap())
        );

        jButton17.setText(resourceMap.getString("jButton17.text")); // NOI18N
        jButton17.setName("jButton17"); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton26.setText(resourceMap.getString("jButton26.text")); // NOI18N
        jButton26.setName("jButton26"); // NOI18N
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jButton17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton26)))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(254, 254, 254)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(245, 245, 245)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(788, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton17)
                            .addComponent(jButton26)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(322, 322, 322)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(424, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane11.setName("jScrollPane11"); // NOI18N

        jTable9.setModel(new ServerContractTableModel());
        jTable9.setName("jTable9"); // NOI18N
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable9);

        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel11.setName("jPanel11"); // NOI18N

        jTextField7.setEditable(false);
        jTextField7.setName("jTextField7"); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jScrollPane8.setName("jScrollPane8"); // NOI18N

        jTextArea6.setColumns(20);
        jTextArea6.setEditable(false);
        jTextArea6.setRows(5);
        jTextArea6.setName("jTextArea6"); // NOI18N
        jScrollPane8.setViewportView(jTextArea6);

        jLabel8.setText("ID");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText("Misc");
        jLabel9.setName("jLabel9"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(26, 26, 26)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setName("jPanel13"); // NOI18N

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wrapper/ui/images/pencil.jpg"))); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setToolTipText("Add");
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8)
                        .addComponent(jButton9)))
                .addContainerGap())
        );

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTable7.setModel(new AssetContractTableModel());
        jTable7.setName("jTable7"); // NOI18N
        jTable7.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable7);

        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel14.setName("jPanel14"); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setName("jTextField8"); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jScrollPane10.setName("jScrollPane10"); // NOI18N

        jTextArea7.setColumns(20);
        jTextArea7.setEditable(false);
        jTextArea7.setRows(5);
        jTextArea7.setName("jTextArea7"); // NOI18N
        jScrollPane10.setViewportView(jTextArea7);

        jLabel12.setText("ID");
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setText("Misc");
        jLabel13.setName("jLabel13"); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.setName("jPanel17"); // NOI18N

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wrapper/ui/images/pencil.jpg"))); // NOI18N
        jButton13.setText(resourceMap.getString("jButton13.text")); // NOI18N
        jButton13.setToolTipText(resourceMap.getString("jButton13.toolTipText")); // NOI18N
        jButton13.setName("jButton13"); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
        jButton14.setToolTipText(resourceMap.getString("jButton14.toolTipText")); // NOI18N
        jButton14.setName("jButton14"); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText(resourceMap.getString("jButton15.text")); // NOI18N
        jButton15.setToolTipText(resourceMap.getString("jButton15.toolTipText")); // NOI18N
        jButton15.setName("jButton15"); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15)
                    .addComponent(jButton14))
                .addContainerGap())
        );

        jSeparator1.setName("jSeparator1"); // NOI18N

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        jTable11.setModel(new ServerContractTableModel());
        jTable11.setName("jTable11"); // NOI18N
        jTable11.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable11);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(426, 426, 426)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(428, 428, 428))))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N

        jComboBox7.setSelectedItem("ALL");
        jComboBox7.setToolTipText(resourceMap.getString("jComboBox7.toolTipText")); // NOI18N
        jComboBox7.setName("jComboBox7"); // NOI18N
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel53.setText(resourceMap.getString("jLabel53.text")); // NOI18N
        jLabel53.setName("jLabel53"); // NOI18N

        jScrollPane26.setName("jScrollPane26"); // NOI18N

        jTable19.setModel(new com.wrapper.ui.model.BasketTableModel());
        jTable19.setName("jTable19"); // NOI18N
        jTable19.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable19MouseClicked(evt);
            }
        });
        jScrollPane26.setViewportView(jTable19);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator10.setName("jSeparator10"); // NOI18N

        jLabel54.setText(resourceMap.getString("jLabel54.text")); // NOI18N
        jLabel54.setName("jLabel54"); // NOI18N

        jTextField9.setEditable(false);
        jTextField9.setName("jTextField9"); // NOI18N
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel55.setText(resourceMap.getString("jLabel55.text")); // NOI18N
        jLabel55.setName("jLabel55"); // NOI18N

        jTextField11.setEditable(false);
        jTextField11.setName("jTextField11"); // NOI18N
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jPanel36.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel36.setName("jPanel36"); // NOI18N

        jButton30.setText(resourceMap.getString("jButton30.text")); // NOI18N
        jButton30.setToolTipText("Add");
        jButton30.setName("jButton30"); // NOI18N
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wrapper/ui/images/pencil.jpg"))); // NOI18N
        jButton32.setToolTipText(resourceMap.getString("jButton32.toolTipText")); // NOI18N
        jButton32.setName("jButton32"); // NOI18N
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton32, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel62.setText(resourceMap.getString("jLabel62.text")); // NOI18N
        jLabel62.setName("jLabel62"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setDisabledTextColor(resourceMap.getColor("jTextField1.disabledTextColor")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jButton33.setText(resourceMap.getString("jButton33.text")); // NOI18N
        jButton33.setToolTipText(resourceMap.getString("jButton33.toolTipText")); // NOI18N
        jButton33.setEnabled(false);
        jButton33.setName("jButton33"); // NOI18N
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton31.setText(resourceMap.getString("jButton31.text")); // NOI18N
        jButton31.setToolTipText(resourceMap.getString("jButton31.toolTipText")); // NOI18N
        jButton31.setEnabled(false);
        jButton31.setName("jButton31"); // NOI18N
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jComboBox7, 0, 122, Short.MAX_VALUE))
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(27, 27, 27)
                .addComponent(jLabel62)
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(551, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(258, 258, 258))
            .addComponent(jSeparator10, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jPanel6.setName("jPanel6"); // NOI18N

        jPanel29.setName("jPanel29"); // NOI18N

        jScrollPane19.setName("jScrollPane19"); // NOI18N

        jTable12.setModel(new ServerContractTableModel());
        jTable12.setName("jTable12"); // NOI18N
        jTable12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable12MouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(jTable12);

        jPanel30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel30.setName("jPanel30"); // NOI18N

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N
        //jLabel25.setVisible(false);

        jLabel26.setName("jLabel26"); // NOI18N
        //jLabel26.setVisible(false);

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N
        //jLabel27.setVisible(false);

        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N
        //jLabel30.setVisible(false);

        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N
        //jLabel32.setVisible(false);

        jLabel39.setText(resourceMap.getString("jLabel39.text")); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N
        //jLabel39.setVisible(false);

        jLabel40.setText(resourceMap.getString("jLabel40.text")); // NOI18N
        jLabel40.setName("jLabel40"); // NOI18N
        //jLabel40.setVisible(false);

        jLabel41.setText(resourceMap.getString("jLabel41.text")); // NOI18N
        jLabel41.setName("jLabel41"); // NOI18N
        //jLabel41.setVisible(false);

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setName("jSeparator9"); // NOI18N

        jTabbedPane4.setName("jTabbedPane4"); // NOI18N
        jTabbedPane4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane4StateChanged(evt);
            }
        });

        jScrollPane20.setName("jScrollPane20"); // NOI18N

        jTable15.setModel(new com.wrapper.ui.model.MarketBidTableModel());
        jTable15.setToolTipText(resourceMap.getString("jTable15.toolTipText")); // NOI18N
        jTable15.setName("jTable15"); // NOI18N
        jTable15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable15MouseEntered(evt);
            }
        });
        jScrollPane20.setViewportView(jTable15);

        jTabbedPane4.addTab(resourceMap.getString("jScrollPane20.TabConstraints.tabTitle"), jScrollPane20); // NOI18N

        jScrollPane24.setName("jScrollPane24"); // NOI18N

        jTable17.setModel(new com.wrapper.ui.model.MarketAskTableModel());
        jTable17.setToolTipText(resourceMap.getString("jTable17.toolTipText")); // NOI18N
        jTable17.setName("jTable17"); // NOI18N
        jTable17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable17MouseEntered(evt);
            }
        });
        jScrollPane24.setViewportView(jTable17);

        jTabbedPane4.addTab(resourceMap.getString("jScrollPane24.TabConstraints.tabTitle"), jScrollPane24); // NOI18N

        jPanel35.setName("jPanel35"); // NOI18N

        jScrollPane25.setName("jScrollPane25"); // NOI18N

        jTable18.setModel(new com.wrapper.ui.model.MarketRecentTradesTableModel(jTable18));
        jTable18.setToolTipText(resourceMap.getString("jTable18.toolTipText")); // NOI18N
        jTable18.setName("jTable18"); // NOI18N
        jTable18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable18MouseEntered(evt);
            }
        });
        jScrollPane25.setViewportView(jTable18);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane4.addTab(resourceMap.getString("jPanel35.TabConstraints.tabTitle"), jPanel35); // NOI18N

        jLabel46.setText(resourceMap.getString("jLabel46.text")); // NOI18N
        jLabel46.setName("jLabel46"); // NOI18N
        //jLabel27.setVisible(false);

        jLabel56.setText(resourceMap.getString("jLabel56.text")); // NOI18N
        jLabel56.setName("jLabel56"); // NOI18N

        jLabel57.setText(resourceMap.getString("jLabel57.text")); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N

        jLabel58.setText(resourceMap.getString("jLabel58.text")); // NOI18N
        jLabel58.setName("jLabel58"); // NOI18N

        jLabel59.setText(resourceMap.getString("jLabel59.text")); // NOI18N
        jLabel59.setName("jLabel59"); // NOI18N

        jLabel60.setText(resourceMap.getString("jLabel60.text")); // NOI18N
        jLabel60.setName("jLabel60"); // NOI18N

        jLabel61.setText(resourceMap.getString("jLabel61.text")); // NOI18N
        jLabel61.setName("jLabel61"); // NOI18N

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel60)
                            .addComponent(jLabel59)
                            .addComponent(jLabel41)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel46))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel59))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jLabel60))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel61))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jScrollPane21.setName("jScrollPane21"); // NOI18N

        jTable13.setModel(new com.wrapper.ui.model.MarketTableModel());
        jTable13.setName("jTable13"); // NOI18N
        jTable13.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable13MouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(jTable13);

        jSeparator8.setName("jSeparator8"); // NOI18N

        jScrollPane23.setName("jScrollPane23"); // NOI18N

        jTable14.setModel(new com.wrapper.ui.model.MarketOffersTableModel());
        jTable14.setName("jTable14"); // NOI18N
        jTable14.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable14MouseClicked(evt);
            }
        });
        jScrollPane23.setViewportView(jTable14);

        jComboBox5.setSelectedItem("ALL");
        jComboBox5.setToolTipText(resourceMap.getString("jComboBox5.toolTipText")); // NOI18N
        jComboBox5.setName("jComboBox5"); // NOI18N
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jTabbedPane3.setName("jTabbedPane3"); // NOI18N

        jPanel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel32.setName("jPanel32"); // NOI18N

        jLabel42.setText(resourceMap.getString("jLabel42.text")); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N

        jLabel43.setText(resourceMap.getString("jLabel43.text")); // NOI18N
        jLabel43.setName("jLabel43"); // NOI18N

        jLabel44.setText(resourceMap.getString("jLabel44.text")); // NOI18N
        jLabel44.setName("jLabel44"); // NOI18N

        jLabel45.setText(resourceMap.getString("jLabel45.text")); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N

        jLabel47.setText(resourceMap.getString("jLabel47.text")); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N
        jLabel47.setVisible(false);

        jLabel48.setText(resourceMap.getString("jLabel48.text")); // NOI18N
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setVisible(false);

        jLabel49.setText(resourceMap.getString("jLabel49.text")); // NOI18N
        jLabel49.setName("jLabel49"); // NOI18N
        jLabel49.setVisible(false);

        jLabel50.setText(resourceMap.getString("jLabel50.text")); // NOI18N
        jLabel50.setName("jLabel50"); // NOI18N
        jLabel50.setVisible(false);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(jLabel45))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49)
                    .addComponent(jLabel47))
                .addContainerGap(523, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel47))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel48))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel49))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel50))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab(resourceMap.getString("jPanel32.TabConstraints.tabTitle"), jPanel32); // NOI18N

        jScrollPane22.setName("jScrollPane22"); // NOI18N

        jTable16.setModel(new com.wrapper.ui.model.MarketTradesTableModel());
        jTable16.setToolTipText(resourceMap.getString("jTable16.toolTipText")); // NOI18N
        jTable16.setName("jTable16"); // NOI18N
        jTable16.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane22.setViewportView(jTable16);

        jTabbedPane3.addTab(resourceMap.getString("jScrollPane22.TabConstraints.tabTitle"), jScrollPane22); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.setName("jLabel3"); // NOI18N

        jButton29.setText(resourceMap.getString("jButton29.text")); // NOI18N
        jButton29.setName("jButton29"); // NOI18N
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jComboBox6.setToolTipText(resourceMap.getString("jComboBox6.toolTipText")); // NOI18N
        jComboBox6.setName("jComboBox6"); // NOI18N
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jLabel52.setText(resourceMap.getString("jLabel52.text")); // NOI18N
        jLabel52.setName("jLabel52"); // NOI18N

        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel31.setName("jPanel31"); // NOI18N

        jButton27.setText(resourceMap.getString("jButton27.text")); // NOI18N
        jButton27.setToolTipText("Add");
        jButton27.setName("jButton27"); // NOI18N
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setText(resourceMap.getString("jButton28.text")); // NOI18N
        jButton28.setName("jButton28"); // NOI18N
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27)
                    .addComponent(jButton28))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel51.setText(resourceMap.getString("jLabel51.text")); // NOI18N
        jLabel51.setName("jLabel51"); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel52))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox5, 0, 125, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(14, 14, 14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton29))
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton29))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel24)))
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel52))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane23, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        jPanel18.setName("jPanel18"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addContainerGap(595, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel18.TabConstraints.tabTitle"), jPanel18); // NOI18N

        jPanel19.setName("jPanel19"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap(619, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel19.TabConstraints.tabTitle"), jPanel19); // NOI18N

        jPanel20.setName("jPanel20"); // NOI18N

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33");
        jLabel33.setVisible(false);

        jLabel34.setText(resourceMap.getString("jLabel34.text")); // NOI18N
        jLabel34.setName("jLabel34");
        jLabel34.setVisible(false);

        jLabel35.setText(resourceMap.getString("jLabel35.text")); // NOI18N
        jLabel35.setName("jLabel35");
        jLabel35.setVisible(false);

        jLabel36.setText(resourceMap.getString("jLabel36.text")); // NOI18N
        jLabel36.setName("jLabel36");
        jLabel36.setVisible(false);

        jLabel37.setText(resourceMap.getString("jLabel37.text")); // NOI18N
        jLabel37.setName("jLabel37");
        jLabel37.setVisible(false);

        jLabel38.setText(resourceMap.getString("jLabel38.text")); // NOI18N
        jLabel38.setName("jLabel38");
        jLabel38.setVisible(false);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel21)
                .addGap(4, 4, 4)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addGap(4, 4, 4)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addContainerGap(424, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel20.TabConstraints.tabTitle"), jPanel20); // NOI18N

        jPanel21.setName("jPanel21"); // NOI18N

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bitcoin", "Loom", "PKTP", "Ripple", "Truledger" }));
        jComboBox4.setToolTipText(resourceMap.getString("jComboBox4.toolTipText")); // NOI18N
        jComboBox4.setName("jComboBox4"); // NOI18N
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable3.setModel(new com.wrapper.ui.model.OtherTabAccountModel());
        jTable3.setName("jTable3"); // NOI18N
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel25.setName("jPanel25"); // NOI18N

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wrapper/ui/images/pencil.jpg"))); // NOI18N
        jButton19.setToolTipText(resourceMap.getString("jButton19.toolTipText")); // NOI18N
        jButton19.setName("jButton19"); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText(resourceMap.getString("jButton20.text")); // NOI18N
        jButton20.setToolTipText(resourceMap.getString("jButton20.toolTipText")); // NOI18N
        jButton20.setName("jButton20"); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText(resourceMap.getString("jButton21.text")); // NOI18N
        jButton21.setToolTipText(resourceMap.getString("jButton21.toolTipText")); // NOI18N
        jButton21.setName("jButton21"); // NOI18N
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jButton20)
                    .addComponent(jButton21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jTable4.setModel(new com.wrapper.ui.model.OtherTabServerTableModel());
        jTable4.setName("jTable4"); // NOI18N
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable4);

        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel26.setName("jPanel26"); // NOI18N

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wrapper/ui/images/pencil.jpg"))); // NOI18N
        jButton22.setToolTipText(resourceMap.getString("jButton22.toolTipText")); // NOI18N
        jButton22.setName("jButton22"); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText(resourceMap.getString("jButton23.text")); // NOI18N
        jButton23.setToolTipText(resourceMap.getString("jButton23.toolTipText")); // NOI18N
        jButton23.setName("jButton23"); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setText(resourceMap.getString("jButton24.text")); // NOI18N
        jButton24.setToolTipText(resourceMap.getString("jButton24.toolTipText")); // NOI18N
        jButton24.setName("jButton24"); // NOI18N
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton22, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jButton23)
                    .addComponent(jButton24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setName("jSeparator6"); // NOI18N

        jSeparator7.setName("jSeparator7"); // NOI18N

        jScrollPane17.setName("jScrollPane17"); // NOI18N

        jPanel27.setName("jPanel27"); // NOI18N
        jPanel27.setVisible(false);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 738, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 374, Short.MAX_VALUE)
        );

        jScrollPane17.setViewportView(jPanel27);

        jScrollPane18.setName("jScrollPane18"); // NOI18N

        jPanel28.setName("jPanel28"); // NOI18N
        jPanel28.setVisible(false);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        jScrollPane18.setViewportView(jPanel28);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox4, 0, 124, Short.MAX_VALUE))
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGap(248, 248, 248)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(785, Short.MAX_VALUE)))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel21.TabConstraints.tabTitle"), jPanel21); // NOI18N

        jPanel22.setName("jPanel22"); // NOI18N

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel33.border.title"))); // NOI18N
        jPanel33.setName("jPanel33"); // NOI18N

        jTextField5.setName("jTextField5"); // NOI18N

        jButton18.setText(resourceMap.getString("jButton18.text")); // NOI18N
        jButton18.setName("jButton18"); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(jButton18)
                .addContainerGap())
        );

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel34.border.title"))); // NOI18N
        jPanel34.setName("jPanel34"); // NOI18N

        jButton25.setText(resourceMap.getString("jButton25.text")); // NOI18N
        jButton25.setName("jButton25"); // NOI18N
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton25)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton25)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(408, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE))
                .addGap(579, 579, 579))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel22.TabConstraints.tabTitle"), jPanel22); // NOI18N

        jPanel23.setName("jPanel23"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel24.setName("jPanel24"); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel23.TabConstraints.tabTitle"), jPanel23); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
}//GEN-LAST:event_jTable2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (jTable2.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select NYM to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nymID = (String) jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 1);

        int userSelection = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete NYM", "NYM Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        System.out.println("userSelection:" + userSelection);
        NYM nym = new NYM();
        if (userSelection == 0) {

            try {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map registeredServers = new NYM().registeredServers(nymID);
                if (registeredServers == null || registeredServers.size() < 1) {
                    boolean status = new NYM().deleteWalletNym(nymID);
                    if (status) {
                        JOptionPane.showMessageDialog(this, "Nym deleted sucessfully from the wallet", "NYM deletion success", JOptionPane.INFORMATION_MESSAGE);
                        refreshNYMSList();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot delete nym from the wallet", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                setCursor(Cursor.getDefaultCursor());
                dispose();
            }

            new DeleteNYMDialog(this, true, nymID).setVisible(true);
        }
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new NYMAdditionDialog(this, true).setVisible(true);
        refreshNYMSList();
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jTable2.getSelectedRow() > -1) {
            new NYMEditDialog(this, true, (String) jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 1)).setVisible(true);
            refreshNYMSList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a NYM to edit.", "NYM Edit Error", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField4ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        System.out.println("In Action for Asset Combo main tab");
        /*String nymID = Utility.getKey(nymMap, (String) jComboBox1.getSelectedItem());
        String assetID = Utility.getKey(assetMap, (String) jComboBox3.getSelectedItem());
        String serverID = Utility.getKey(serverMap, (String) jComboBox2.getSelectedItem());*/
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox1.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox1.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox3.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox3.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBox2.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox2.getSelectedIndex() - 1))[1];
        }

        System.out.print("nymiiidL:" + nymID);
        loadAccount(assetID, serverID, nymID);
        clearDetailPage();
}//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (jTable5.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select account to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userSelection = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete account", "Delete Account", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        System.out.println("userSelection:" + userSelection);
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox1.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox1.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox3.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox3.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBox2.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox2.getSelectedIndex() - 1))[1];
        }

        // pass serverID and nymID here
        OpenTransactionAccount openTransaction = new OpenTransactionAccount(serverID, nymID);
        try {
            if (userSelection == 0) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                boolean status = openTransaction.deleteAccount((String) jTable5.getModel().getValueAt(jTable5.getSelectedRow(), 3));
                if (status) {
                    JOptionPane.showMessageDialog(this, "Account deleted successfully", "Account deletion", JOptionPane.INFORMATION_MESSAGE);

                    loadAccount(assetID, serverID, nymID);
                    clearDetailPage();
                } else {
                    JOptionPane.showMessageDialog(this, "Account cannot be deleted", "Account deletion", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
}//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox1.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox1.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox3.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox3.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBox2.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox2.getSelectedIndex() - 1))[1];
        }

        new AccountAdditionDialog(this, true, nymID, assetID, serverID, "OT", (String) jTable5.getModel().getValueAt(jTable5.getSelectedRow(), 3)).setVisible(true);
        System.out.print("assetID:" + assetID);
        System.out.print("serverID:" + serverID);
        System.out.print("nymiiidL:" + nymID);

}//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (jTable5.getSelectedRow() > -1) {
            new AccountEditDialog(this, true, (String) jTable5.getModel().getValueAt(jTable5.getSelectedRow(), 3), (String) jTable5.getModel().getValueAt(jTable5.getSelectedRow(), 2)).setVisible(true);
            System.out.println("ggggggggjTable5.getSelectedRow():" + jTable5.getSelectedRow());
            String nymID = "ALL";
            String assetID = "ALL";
            String serverID = "ALL";

            if (nymMap != null && nymMap.size() > 0 && jComboBox1.getSelectedIndex() > 0) {
                nymID = ((String[]) nymMap.get((Integer) jComboBox1.getSelectedIndex() - 1))[1];
            }

            if (assetMap != null && assetMap.size() > 0 && jComboBox3.getSelectedIndex() > 0) {
                assetID = ((String[]) assetMap.get((Integer) jComboBox3.getSelectedIndex() - 1))[1];
            }

            if (serverMap != null && serverMap.size() > 0 && jComboBox2.getSelectedIndex() > 0) {
                serverID = ((String[]) serverMap.get((Integer) jComboBox2.getSelectedIndex() - 1))[1];
            }
            loadAccount(assetID, serverID, nymID);
            //jTable5.setValueAt("", jTable5.getSelectedRow(), 0);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an account to edit.", "NYM Edit Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        System.out.println("In Action for Server Combo main tab");
        /*String nymID = Utility.getKey(nymMap, (String) jComboBox1.getSelectedItem());
        String assetID = Utility.getKey(assetMap, (String) jComboBox3.getSelectedItem());
        String serverID = Utility.getKey(serverMap, (String) jComboBox2.getSelectedItem());*/
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox1.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox1.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox3.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox3.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBox2.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox2.getSelectedIndex() - 1))[1];
        }

        System.out.print("serverID----------------------------:" + serverID);
        loadAccount(assetID, serverID, nymID);
        clearDetailPage();
}//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
}//GEN-LAST:event_jTable5MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        System.out.println("In Action for NYM Combo Main tab");
        /*String nymID = Utility.getKey(nymMap, (String) jComboBox1.getSelectedItem());
        String assetID = Utility.getKey(assetMap, (String) jComboBox3.getSelectedItem());
        String serverID = Utility.getKey(serverMap, (String) jComboBox2.getSelectedItem());*/
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox1.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox1.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox3.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox3.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBox2.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox2.getSelectedIndex() - 1))[1];
        }

        System.out.print(":" + nymID);
        System.out.print("serverID:" + serverID);
        System.out.print("assetID:" + assetID);
        loadAccount(assetID, serverID, nymID);
        clearDetailPage();
}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable9MouseClicked

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (jTable7.getSelectedRow() > -1) {
            new AssetContractEditDialog(this, true, (String) jTable7.getModel().getValueAt(jTable7.getSelectedRow(), 1)).setVisible(true);
            refreshAssetContractList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an Asset Contract to edit.", "Asset Contract Edit Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new ContractAdditionDialog(this, true, "Asset").setVisible(true);
        refreshAssetContractList();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        if (jTable7.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select Asset Contract to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userSelection = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Asset Contract", "Asset Contract Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        System.out.println("userSelection:" + userSelection);
        Contract contract = new Contract();
        if (userSelection == 0) {
            boolean status = contract.deleteAssetContract((String) jTable7.getModel().getValueAt(jTable7.getSelectedRow(), 1));
            if (status) {
                JOptionPane.showMessageDialog(this, "Asset Contract deleted successfully", "Asset Contract Deletion", JOptionPane.INFORMATION_MESSAGE);
                refreshAssetContractList();
            } else {
                JOptionPane.showMessageDialog(this, "Asset Contract cannot be deleted", "Asset Contract Deletion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MouseClicked

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if (jTable11.getSelectedRow() > -1) {
            new ServerContractEditDialog(this, true, (String) jTable11.getModel().getValueAt(jTable11.getSelectedRow(), 1)).setVisible(true);
            refreshServerContractList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a Server Contract to edit.", "Server Contract Edit Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        new ContractAdditionDialog(this, true, "Server").setVisible(true);
        refreshServerContractList();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable11MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ("".equalsIgnoreCase(jTextArea6.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Please select Asset Contract", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox1.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox1.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox3.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox3.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBox2.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox2.getSelectedIndex() - 1))[1];
        }

        System.out.print(":" + nymID);
        System.out.print("serverID:" + serverID);
        System.out.print("assetID:" + assetID);
        new IssueAssetContractDialog(this, true, jTextArea6.getText(), assetID, serverID, nymID).setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if ("".equalsIgnoreCase(jTextField4.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Please select NYM", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new SendMessageDialog(this, true, jTextField4.getText(), jTable10).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTextField4.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select NYM", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        NYM nym = new NYM();
        nymBox = nym.downloadNymBox(jTextField4.getText());
        ((NYMBoxTableModel) jTable8.getModel()).setValue(nymBox, jTable8);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if (jTable8.getSelectedRow() < 0 && jTable10.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select mail to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            NYM nym = new NYM();
            if (jTabbedPane2.getSelectedIndex() == 0) {
                boolean success = nym.deleteMail(jTextField4.getText(), Integer.parseInt((String) jTable8.getModel().getValueAt(jTable8.getSelectedRow(), 3)));
                int previousSelection = jTable8.getSelectedRow();
                if (success) {
                    //JOptionPane.showMessageDialog(this, "Mail deleted successfully", "Mail Deletion Success", JOptionPane.INFORMATION_MESSAGE);
                    ((NYMBoxTableModel) jTable8.getModel()).setValue(nym.loadNymBox(jTextField4.getText()), jTable8);
                    int newCount = jTable8.getModel().getRowCount();
                    if (newCount > 0) {
                        if (newCount > previousSelection) {
                            jTable8.setRowSelectionInterval(previousSelection, previousSelection);
                        } else {
                            jTable8.setRowSelectionInterval(previousSelection - 1, previousSelection - 1);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Mail deletion failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {

                boolean success = nym.deleteOutboxMail(jTextField4.getText(), Integer.parseInt((String) jTable10.getModel().getValueAt(jTable10.getSelectedRow(), 3)));
                int previousSelection = jTable10.getSelectedRow();
                if (success) {
                    //JOptionPane.showMessageDialog(this, "Mail deleted successfully", "Mail Deletion Success", JOptionPane.INFORMATION_MESSAGE);
                    ((NYMOutboxTableModel) jTable10.getModel()).setValue(nym.loadNymOutBox(jTextField4.getText()), jTable10);
                    int newCount = jTable10.getModel().getRowCount();
                    if (newCount > 0) {
                        if (newCount > previousSelection) {
                            jTable10.setRowSelectionInterval(previousSelection, previousSelection);
                        } else {
                            jTable10.setRowSelectionInterval(previousSelection - 1, previousSelection - 1);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Mail deletion failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        System.out.println("Item stt--");
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTable8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseEntered
}//GEN-LAST:event_jTable8MouseEntered

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked
        System.out.println("Count:" + evt.getClickCount());
        if (evt.getClickCount() == 2) {
            String key = (String) jTable8.getModel().getValueAt(jTable8.getSelectedRow(), 3);
            String subject = (String) jTable8.getModel().getValueAt(jTable8.getSelectedRow(), 0);
            System.out.println("In NYMBOX double clcik, key:" + key);
            String[] row = (String[]) nymBox.get(key);
            if (row != null) {
                NymBoxDetailsDialog nymDialog = new NymBoxDetailsDialog(this, true, row[7] == "true" ? "Verified" : "Not Verified", row[6], subject);
                nymDialog.setVisible(true);
            }
        }
}//GEN-LAST:event_jTable8MouseClicked

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
        System.out.println("Count:" + evt.getClickCount());
        if (evt.getClickCount() == 2) {
            String key = (String) jTable10.getModel().getValueAt(jTable10.getSelectedRow(), 3);
            String subject = (String) jTable10.getModel().getValueAt(jTable10.getSelectedRow(), 0);
            System.out.println("In NYM out BOX double clcik, key:" + key);
            String[] row = (String[]) nymOutBox.get(key);
            System.out.println("nymOutBox:" + nymOutBox.entrySet());
            System.out.println("row:" + row);
            if (row != null) {
                NymBoxDetailsDialog nymDialog = new NymBoxDetailsDialog(this, true, row[7] == "true" ? "Verified" : "Not Verified", row[6], subject);
                nymDialog.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTable10MouseClicked

    private void jTable10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseEntered
    }//GEN-LAST:event_jTable10MouseEntered

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        JTabbedPane pane = (JTabbedPane) evt.getSource();
        int sel = pane.getSelectedIndex();
        System.out.println("State changed:" + sel);
        if (sel == 0) {
            jPanel2.setVisible(true);
            repaint();
        } else {
            jPanel2.setVisible(false);
            repaint();
        }
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        long waitTime = Configuration.getWaitTime();
        try {
            waitTime = Long.parseLong(jTextField5.getText());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter valid timeout", "Timeout Setting Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (waitTime < 1) {
            JOptionPane.showMessageDialog(this, "Timeout should be >0", "Timeout Setting Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Configuration.setWaitTime(waitTime);
        JOptionPane.showMessageDialog(this, "Timeout applied successfully", "Timeout change", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if (jTable11.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select Server Contract to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userSelection = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Server Contract", "Server Contract Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        System.out.println("userSelection:" + userSelection);
        Contract contract = new Contract();
        if (userSelection == 0) {
            boolean status = contract.deleteServerContract((String) jTable11.getModel().getValueAt(jTable11.getSelectedRow(), 1));
            if (status) {
                JOptionPane.showMessageDialog(this, "Server Contract deleted successfully", "Server Contract Deletion", JOptionPane.INFORMATION_MESSAGE);
                refreshServerContractList();
            } else {
                JOptionPane.showMessageDialog(this, "Server Contract cannot be deleted", "Server Contract Deletion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        new ImportNYMDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        try {
            //server id at 2
            if (jTable4.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Please select server to edit label", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            new OtherTabServerEditDialog(this, true, (String) jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 0), (String) jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 2), (String) jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 1)).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        new OtherTabServerAddDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        try {
            if (jTable4.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Please select server to delete", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int userSelection = JOptionPane.showConfirmDialog(this, "Deleting server will remove all accounts associated with server.\nAre you sure you want to delete server", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            System.out.println("userSelection:" + userSelection);

            if (userSelection == 0) {
                boolean status = StorageHelper.removeOtherTabServer((String) jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 2), (String) jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 1));
                if (status) {
                    JOptionPane.showMessageDialog(null, "Server deleted successfully", "Server Delete Success", JOptionPane.INFORMATION_MESSAGE);
                    loadOtherTabServers();
                    ((OtherTabAccountModel) jTable3.getModel()).clearValue();
                    CardLayout topLayout = (CardLayout) (jPanel27.getLayout());
                    CardLayout bottomlayout = (CardLayout) (jPanel28.getLayout());
                    topLayout.show(jPanel27, "BlankTop");
                    bottomlayout.show(jPanel28, "BlankBottom");
                } else {
                    JOptionPane.showMessageDialog(null, "Server could not be deleted because of storage failure", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked

        if (SwingUtilities.isRightMouseButton(evt)) {
            Point p = evt.getPoint();
            int rowNumber = jTable4.rowAtPoint(p);
            ListSelectionModel model = jTable4.getSelectionModel();
            model.removeSelectionInterval(rowNumber, rowNumber);
        }

    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        new OtherTabAccountEditDialog(this, true, (String) jTable3.getModel().getValueAt(jTable3.getSelectedRow(), 0), jTable3.getSelectedRow(), (String) jTable3.getModel().getValueAt(jTable3.getSelectedRow(), 3), (String) jTable3.getModel().getValueAt(jTable3.getSelectedRow(), 2)).setVisible(true);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO addling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTable12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable12MouseClicked

    private void jTable13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable13MouseClicked
    }//GEN-LAST:event_jTable13MouseClicked

    private void jTable14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable14MouseClicked

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        new AddressBookDialog(this, true, 3).setVisible(true);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTable15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable15MouseClicked

    private void jTable15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable15MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable15MouseEntered

    private void jTable17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable17MouseClicked

    private void jTable17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable17MouseEntered

    private void jTabbedPane4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane4StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane4StateChanged

    private void jTable18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable18MouseClicked

    private void jTable18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable18MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable18MouseEntered

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        loadOtherTabServers();
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // Load the markets for this server
        String serverID = "ALL";
        String nymID = "ALL";

        if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
        }

        System.out.println("Mrkets tab - nym selected index - " + jComboBox6.getSelectedIndex());

        nymRegisteredMap = new NYM().loadRegisteredNYM(serverID);
        Utility.populateComboWithoutAll(nymRegisteredMap, jComboBox6);

        if (nymRegisteredMap != null && nymRegisteredMap.size() > 0 && jComboBox6.getSelectedIndex() > -1) {
            nymID = ((String[]) nymRegisteredMap.get((Integer) jComboBox6.getSelectedIndex()))[1];
        }

        System.out.println("Mrkets tab, serverID:" + serverID + " nymID:" + nymID);
        if (jComboBox6.getSelectedIndex() > -1 && jComboBox5.getSelectedIndex() > -1) {
            Map marketList = null;
            try {
                marketList = Market.loadMarketList(serverID, nymID);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (marketList != null) {
                ((MarketTableModel) jTable13.getModel()).setValue(marketList, jTable13);
            }

            if (marketList != null && marketList.size() > 0) {
                String marketID = (String) jTable13.getModel().getValueAt(jTable13.getSelectedRow(), 1);
                System.out.println("-----marketID----:" + marketID);

                boolean marketExists = false;
                for (int i = 0; i < jTable13.getRowCount(); i++) {
                    if (marketID != null && jTable13.getModel().getValueAt(jTable13.getSelectedRow(), 1) != null
                            && marketID.equals(jTable13.getModel().getValueAt(jTable13.getSelectedRow(), 1))) {
                        jTable13.setRowSelectionInterval(i, i);
                        marketListClick();
                        marketExists = true;
                        break;
                    }

                }
                if (jTable13.getRowCount() > 0 && !marketExists) {
                    jTable13.setRowSelectionInterval(0, 0);
                    marketListClick();
                }

            }
        }

    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed

        String serverID = "ALL";
        String nymID = "ALL";

        if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
        }

        if (nymRegisteredMap != null && nymRegisteredMap.size() > 0 && jComboBox6.getSelectedIndex() > -1) {
            nymID = ((String[]) nymRegisteredMap.get((Integer) jComboBox6.getSelectedIndex()))[1];
        }

        System.out.println("Mrkets tab, nym combo serverID:" + serverID + " nymID:" + nymID);
        if (jComboBox6.getSelectedIndex() > -1 && jComboBox5.getSelectedIndex() > -1) {
            Map marketList = null;

            try {
                marketList = Market.loadMarketList(serverID, nymID);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (marketList != null) {
                ((MarketTableModel) jTable13.getModel()).setValue(marketList, jTable13);
            }


            if (marketList != null && marketList.size() > 0) {
                String marketID = (String) jTable13.getModel().getValueAt(jTable13.getSelectedRow(), 1);
                System.out.println("-----marketID:" + marketID);

                boolean marketExists = false;
                for (int i = 0; i < jTable13.getRowCount(); i++) {
                    if (marketID != null && jTable13.getModel().getValueAt(jTable13.getSelectedRow(), 1) != null
                            && marketID.equals(jTable13.getModel().getValueAt(jTable13.getSelectedRow(), 1))) {
                        jTable13.setRowSelectionInterval(i, i);
                        marketListClick();
                        marketExists = true;
                        break;
                    }

                }
                if (jTable13.getRowCount() > 0 && !marketExists) {

                    jTable13.setRowSelectionInterval(0, 0);
                    marketListClick();
                }

            }
        }
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

        String serverID = "ALL";
        String nymID = "ALL";
        try {
            if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
                serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
            }

            if (nymRegisteredMap != null && nymRegisteredMap.size() > 0 && jComboBox6.getSelectedIndex() > -1) {
                nymID = ((String[]) nymRegisteredMap.get((Integer) jComboBox6.getSelectedIndex()))[1];
            }
            if (!"ALL".equalsIgnoreCase(nymID)) {
                MarketTicker marketTicker = Market.getTicker((String) jTable13.getModel().getValueAt(jTable13.getSelectedRow(), 1), serverID, nymID);
                if (marketTicker != null) {
                    jLabel3.setText("Last:" + marketTicker.getLastPrice() + "          Bid:" + marketTicker.getHighestBid() + "          Ask:" + marketTicker.getLowestAsk());
                }
                // FT: I just added this here.

                refreshMarketOfferList(serverID, nymID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {
        // Cancel Order
        String serverID = "ALL";
        String nymID = "ALL";
        try {
            if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
                serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
            }

            if (nymRegisteredMap != null && nymRegisteredMap.size() > 0 && jComboBox6.getSelectedIndex() > -1) {
                nymID = ((String[]) nymRegisteredMap.get((Integer) jComboBox6.getSelectedIndex()))[1];
            }

            if (jTable14.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Please select order to cancel", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int userSelection = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel order", "Order Cancellation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            System.out.println("userSelection:" + userSelection);

            if (userSelection == 0) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                boolean status = Market.cancelOrder(serverID, nymID, (String) jTable14.getModel().getValueAt(jTable14.getSelectedRow(), 2), (String) jTable14.getModel().getValueAt(jTable14.getSelectedRow(), 0));
                setCursor(Cursor.getDefaultCursor());
                if (status) {
                    JOptionPane.showMessageDialog(this, "Order cancelled successfully", "Order Cancellation", JOptionPane.INFORMATION_MESSAGE);
                    refreshMarketOfferList(serverID, nymID);
                } else {
                    JOptionPane.showMessageDialog(this, "Order cannot be cancelled", "Order Cancellation", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    public static void refreshMarketOfferList(String serverID, String nymID) {
        Map marketList = null;
        Map offerList = null;
        try {
            // DEBUGGING: this is where the next step happens
            marketList = Market.loadMarketList(serverID, nymID);
            offerList = Market.getNymOfferList(serverID, nymID);

            if (marketList != null) {
                ((MarketTableModel) jTable13.getModel()).setValue(marketList, jTable13);
            } else {
                System.out.println("refreshMarketOfferList:  Market.loadMarketList() returned null!");
            }
            // ------------------------------------
            if (offerList != null) {
                ((MarketOffersTableModel) jTable14.getModel()).setValue(offerList, jTable14);
            } else {
                System.out.println("refreshMarketOfferList:  Market.getNymOfferList() returned null!");
            }
            // ------------------------------------
            if (marketList != null && marketList.size() > 0) {
                jTable13.setRowSelectionInterval(0, 0);
            } else {
                System.out.println("refreshMarketOfferList:  marketList.size() was <= 0");
            }
            // ------------------------------------
            if (offerList != null && offerList.size() > 0) {
                jTable14.setRowSelectionInterval(0, 0);
            } else {
                System.out.println("refreshMarketOfferList:  offerList.size() was <= 0");
            }
            // ------------------------------------

        } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // Create Order and refresh order and market list
        String serverID = "ALL";
        String nymID = "ALL";
        try {
            if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
                serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
            }

            if (nymRegisteredMap != null && nymRegisteredMap.size() > 0 && jComboBox6.getSelectedIndex() > -1) {
                nymID = ((String[]) nymRegisteredMap.get((Integer) jComboBox6.getSelectedIndex()))[1];
            }
            if ("ALL".equalsIgnoreCase(nymID)) {
                JOptionPane.showMessageDialog(this, "NYM is required to create market offer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            new CreateMarketOrder(this, true, serverID, nymID).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

}//GEN-LAST:event_jButton27ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed

        if (jTable2.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select NYM to register", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new RegisterNYMDialog(this, true, (String) jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 1)).setVisible(true);

    }//GEN-LAST:event_jButton26ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

        JTabbedPane pane = (JTabbedPane) evt.getSource();
        int sel = pane.getSelectedIndex();
        System.out.println("Mainpage tab eventState changed:" + sel);
        if (sel == 4 && Utility.isLoadNymTrades()) {
            String serverID = "ALL";
            String nymID = "ALL";

            if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
                serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
            }
            if (nymRegisteredMap != null && nymRegisteredMap.size() > 0 && jComboBox6.getSelectedIndex() > -1) {
                nymID = ((String[]) nymRegisteredMap.get((Integer) jComboBox6.getSelectedIndex()))[1];
            }
            if (!"ALL".equalsIgnoreCase(nymID)) {
                Map nymTrades = Market.getNymTrades(serverID, nymID);
                if (nymTrades != null) {
                    ((MarketTradesTableModel) jTable16.getModel()).setValue(nymTrades, jTable16);
                }
                Utility.setLoadNymTrades(false);
            }
        }

        if (sel == 3 && !isBasketInit) {
            initBasketsTab();
            isBasketInit = true;
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed

        String currentServerID = "ALL";

        if (serverMap != null && serverMap.size() > 0 && jComboBox7.getSelectedIndex() > -1) {
            currentServerID = ((String[]) serverMap.get((Integer) jComboBox7.getSelectedIndex()))[1];
        }
        System.out.println("Server dropdown currentServerID:" + currentServerID);

        List baskets = Basket.getBasketList(currentServerID, null);
        System.out.println("baskets:" + baskets);
        System.out.println("baskets.size():" + baskets.size());


        if (baskets != null && baskets.size() > 0 && !"Popup Dialog".equalsIgnoreCase(((String[]) baskets.get(0))[0])) {
            ((BasketTableModel) jTable19.getModel()).setValue(baskets);
        } else if (baskets != null && baskets.size() != 0) {

            // Show dialog asking for register
            new RegisterNymOnServerDialog(this, true, currentServerID).setVisible(true);
            if (Utility.getNymID() == null) {
                return;
            }
            String nymID = Utility.getNymID();
            ((BasketTableModel) jTable19.getModel()).setValue(Basket.getBasketList(currentServerID, nymID));

        }

        jTextField9.setText("");
        jTextField11.setText("");
        jLabel62.setText("");

    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jTable19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable19MouseClicked

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        String currentServerID = "ALL";

        if (serverMap != null && serverMap.size() > 0 && jComboBox7.getSelectedIndex() > -1) {
            currentServerID = ((String[]) serverMap.get((Integer) jComboBox7.getSelectedIndex()))[1];
        }
        System.out.println("currentServerID:" + currentServerID);
        new AddBasketDialog(this, true, currentServerID).setVisible(true);
    }//GEN-LAST:event_jButton30ActionPerformed

    public static void setBasketAssetName(String updatedName) {
        jTextField9.setText(updatedName);

    }

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        if (jTable19.getSelectedRow() > -1) {
            new AssetContractEditDialog(this, true, (String) jTable19.getModel().getValueAt(jTable19.getSelectedRow(), 1)).setVisible(true);
            refreshAssetContractList();
            String currentServerID = "ALL";
            if (serverMap != null && serverMap.size() > 0 && jComboBox7.getSelectedIndex() > -1) {
                currentServerID = ((String[]) serverMap.get((Integer) jComboBox7.getSelectedIndex()))[1];
            }
            System.out.println("currentServerID:" + currentServerID);
            List baskets = Basket.getBasketList(currentServerID, null);

            if (baskets != null && baskets.size() > 0 && !"Popup Dialog".equalsIgnoreCase(((String[]) baskets.get(0))[0])) {
                ((BasketTableModel) jTable19.getModel()).setValue(baskets);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please select an Asset Contract to edit.", "Asset Contract Edit Error", JOptionPane.ERROR_MESSAGE);
        }    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        int existingValue = Integer.valueOf(jTextField1.getText());
        if (existingValue == 1) {
            JOptionPane.showMessageDialog(null, "Minimum value should be 1", "Min Bound Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        jTextField1.setText(String.valueOf(existingValue - 1));
        jLabel62.setText(Basket.getNewValue(existingValue - 1));
}//GEN-LAST:event_jButton31ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed

        int existingValue = Integer.valueOf(jTextField1.getText());
        if (existingValue == 100) {
            JOptionPane.showMessageDialog(null, "Maximum value is 100", "Max Bound Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        jTextField1.setText(String.valueOf(existingValue + 1));
        jLabel62.setText(Basket.getNewValue(existingValue + 1));
}//GEN-LAST:event_jButton33ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                try {
                    //UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
                    new MainPage().setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private static javax.swing.JComboBox jComboBox1;
    private static javax.swing.JComboBox jComboBox2;
    private static javax.swing.JComboBox jComboBox3;
    private static javax.swing.JComboBox jComboBox4;
    private static javax.swing.JComboBox jComboBox5;
    private static javax.swing.JComboBox jComboBox6;
    private static javax.swing.JComboBox jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private static javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private static javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private static javax.swing.JPanel jPanel27;
    private static javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private static javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable10;
    private static javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private static javax.swing.JTable jTable13;
    private static javax.swing.JTable jTable14;
    private static javax.swing.JTable jTable15;
    private static javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private static javax.swing.JTable jTable18;
    private static javax.swing.JTable jTable19;
    private static javax.swing.JTable jTable2;
    private static javax.swing.JTable jTable3;
    private static javax.swing.JTable jTable4;
    private static javax.swing.JTable jTable5;
    private static javax.swing.JTable jTable7;
    private static javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private static javax.swing.JTextField jTextField1;
    private static javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private static javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
    private static Map nymMap;
    private static Map nymRegisteredMap;
    private static Map assetMap;
    private static Map serverMap;

    public static void setNymOutbox(Map nymOutboxData) {
        nymOutBox = nymOutboxData;
    }

    private void initMainTab() {


        jPanel8.setLayout(new CardLayout());
        jPanel15.setLayout(new CardLayout());

        jTable5.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("valueChanged Action Listener :" + jTable5.getSelectedRow() + "e:" + e.getSource());
                if (jTable5.getSelectedRow() >= 0) {
                    try {
                        jPanel8.setVisible(true);
                        jPanel15.setVisible(true);

                        jSeparator2.setVisible(true);

                        CardLayout topLayout = (CardLayout) (jPanel8.getLayout());
                        CardLayout bottomlayout = (CardLayout) (jPanel15.getLayout());

                        jScrollPane13.setVisible(true);
                        String type = null;
                        String accountID = null;

                        type = (String) jTable5.getModel().getValueAt(jTable5.getSelectedRow(), 2);
                        accountID = (String) jTable5.getModel().getValueAt(jTable5.getSelectedRow(), 3);

                        System.out.println("Type:" + type);

                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                        for (int i = 0; i < Account.allAccounts.length; i++) {

                            if (Account.allAccounts[i].equalsIgnoreCase(type)) {
                                try {
                                    topLayout.show(jPanel8, type + "TopPanel");
                                    bottomlayout.show(jPanel15, type + "BottomPanel");
                                    Class obj = Class.forName("com.wrapper.core." + type);


                                    Account account = (Account) obj.newInstance();
                                    Object details = account.getAccountDetails(accountID);
                                    if (details == null) {
                                        JOptionPane.showMessageDialog(null, "Error loading details", "Details Error", JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                    if ("OpenTransactionAccount".equalsIgnoreCase(type)) {
                                        OTDetails otDetails = (OTDetails) details;
                                        Utility.populateOTDetails(otDetails);
                                        ((AccountTableModel) jTable5.getModel()).setValueAt(otDetails.getBalance(), jTable5.getSelectedRow(), 1);
                                    } else if ("CashPurseAccount".equalsIgnoreCase(type)) {
                                        CashPurseDetails cashDetails = (CashPurseDetails) details;
                                        populateCashPurseDetails(cashDetails, cashDetails.getBalance());
                                    }

                                    break;
                                } catch (InstantiationException ex) {
                                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IllegalAccessException ex) {
                                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                repaint();
                            }

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
        });

        nymMap = new NYM().loadNYM();

        jComboBox1.addItem(new ComboObject("ALL"));
        jComboBox2.addItem(new ComboObject("ALL"));
        jComboBox3.addItem(new ComboObject("ALL"));

        Utility.populateCombo(nymMap, jComboBox1);

        Contract contract = new Contract();

        serverMap = contract.loadServerContract();
        Utility.populateCombo(serverMap, jComboBox2);

        assetMap = contract.loadAssetContract();
        Utility.populateCombo(assetMap, jComboBox3);


        Account account = null;
        for (int i = 0; i < Account.allAccounts.length; i++) {
            try {

                if ("OpenTransactionAccount".equals(Account.allAccounts[i]) || "CashPurseAccount".equals(Account.allAccounts[i])) {

                    Class obj = Class.forName("com.wrapper.core." + Account.allAccounts[i]);
                    account = (Account) obj.newInstance();
                    try {
                        account.loadAccount("ALL", "ALL", "ALL");
                    } catch (Exception ex) {
                        Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Class obj1 = Class.forName("com.wrapper.ui.panels." + Account.allAccounts[i] + "TopPanel");
                    JPanel topPanel = (JPanel) obj1.newInstance();
                    jPanel8.add(topPanel, Account.allAccounts[i] + "TopPanel");

                    Class obj2 = Class.forName("com.wrapper.ui.panels." + Account.allAccounts[i] + "BottomPanel");
                    JPanel bottomPanel = (JPanel) obj2.newInstance();

                    jPanel15.add(bottomPanel, Account.allAccounts[i] + "BottomPanel");
                }

            } catch (InstantiationException ex) {
                //Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                ///Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void initOtherTab() {
        try {
            jPanel27.setLayout(new CardLayout());
            jPanel28.setLayout(new CardLayout());
            System.out.println("in initOtherTab");

            //load servers here

            loadOtherTabServers();

            jTable4.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting()) {
                        return;
                    }
                    System.out.println("selectedRow:" + jTable4.getSelectedRow());
                    if (jTable4.getSelectedRow() >= 0) {
                        String serverID = (String) jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 2);
                        String type = (String) jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 1);
                        System.out.println("selected serverID:" + serverID + " type:" + type);

                        if ("BitcoinAccount".equals(type)) {
                            Account account = null;
                            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            try {
                                try {
                                    account = (Account) (Class.forName("com.wrapper.core." + type)).newInstance();
                                    account.setServerID(serverID);
                                } catch (InstantiationException ex) {
                                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IllegalAccessException ex) {
                                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            /*if (type.contains("Bitcoin")) {
                            account = new BitcoinAccount(serverID);
                            }*/
                            try {
                                account.loadAccount("", serverID, "");
                                ((OtherTabAccountModel) jTable3.getModel()).setValue(account.getAccountList(), jTable3);

                            } catch (Exception ex) {
                                System.out.println("In BTC load exception:" + ex);
                                JOptionPane.showMessageDialog(null, "Cannot connect to bitcoin service - Connection refused", "Connection Error", JOptionPane.ERROR_MESSAGE);
                                //jTable4.setRowSelectionInterval(WIDTH, WIDTH);

                            } finally {
                                setCursor(Cursor.getDefaultCursor());
                            }
                        } else if ("RippleAccount".equalsIgnoreCase(type)) {
                            CardLayout topLayout = (CardLayout) (jPanel27.getLayout());
                            CardLayout bottomlayout = (CardLayout) (jPanel28.getLayout());
                            topLayout.show(jPanel27, "BlankTop");
                            bottomlayout.show(jPanel28, "BlankBottom");
                            ((OtherTabAccountModel) jTable3.getModel()).clearValue();
                            String[] details = new RippleAccount().loadServerDetails(serverID);
                            if (details != null) {
                                RippleAccountTopPanel.openBrowser(details[0], details[1], details[2], details[3], details[4]);
                            }
                        }
                    }
                }
            });
            //((OtherTabServerTableModel) jTable4.getModel()).setValue(new NYM().loadNYM(), jTable4);

            jTable3.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting()) {
                        return;
                    }
                    System.out.println("valueChanged Action Listener :" + jTable3.getSelectedRow() + "e:" + e.getSource());
                    if (jTable3.getSelectedRow() >= 0) {
                        try {

                            jPanel27.setVisible(true);
                            jPanel28.setVisible(true);

                            CardLayout topLayout = (CardLayout) (jPanel27.getLayout());
                            CardLayout bottomlayout = (CardLayout) (jPanel28.getLayout());

                            String type = null;
                            String accountID = null;

                            type = (String) jTable3.getModel().getValueAt(jTable3.getSelectedRow(), 2);
                            accountID = (String) jTable3.getModel().getValueAt(jTable3.getSelectedRow(), 3);

                            System.out.println("Type:" + type);

                            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));


                            for (int i = 0; i < Account.allAccounts.length; i++) {

                                if (Account.allAccounts[i].equalsIgnoreCase(type)) {
                                    try {
                                        topLayout.show(jPanel27, type + "TopPanel");
                                        bottomlayout.show(jPanel28, type + "BottomPanel");
                                        Class obj = Class.forName("com.wrapper.core." + type);

                                        Account account = (Account) obj.newInstance();
                                        account.setServerID((String) jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 2));
                                        Object details = account.getAccountDetails(accountID);
                                        if (details == null) {
                                            JOptionPane.showMessageDialog(null, "Error loading details", "Details Error", JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                        if ("BitcoinAccount".equalsIgnoreCase(type)) {
                                            BitcoinDetails btcDetails = (BitcoinDetails) details;
                                            populateBitcoinDetails(btcDetails);
                                        }
                                        //}


                                    } catch (InstantiationException ex) {
                                        Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    repaint();
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            setCursor(Cursor.getDefaultCursor());
                        }
                    }
                }
            });

            jPanel27.add(new BlankPanel(), "BlankTop");
            jPanel28.add(new BlankPanel(), "BlankBottom");
            for (int i = 0; i < Account.allAccounts.length; i++) {
                try {
                    System.out.println("in initOtherTabloop");
                    if (!"OpenTransactionAccount".equals(Account.allAccounts[i]) && !"CashPurseAccount".equals(Account.allAccounts[i])) {
                        System.out.println("initOtherTabloop --- Account.allAccounts[i]:" + Account.allAccounts[i]);

                        Class obj1 = Class.forName("com.wrapper.ui.panels." + Account.allAccounts[i] + "TopPanel");
                        JPanel topPanel = (JPanel) obj1.newInstance();
                        jPanel27.add(topPanel, Account.allAccounts[i] + "TopPanel");

                        Class obj2 = Class.forName("com.wrapper.ui.panels." + Account.allAccounts[i] + "BottomPanel");
                        JPanel bottomPanel = (JPanel) obj2.newInstance();
                        // Setting the account account to retrieve in bottom panel

                        jPanel28.add(bottomPanel, Account.allAccounts[i] + "BottomPanel");

                    }
                } catch (InstantiationException ex) {
                    //Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    ///Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // TODO might need to uncomment
            //loadOtherTabAccount("");
            OtherTabAccountModel.removeCols(jTable3);
            OtherTabServerTableModel.removeCols(jTable4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void populateCashPurseDetails(CashPurseDetails cashDetails, String balance) {
        if (cashDetails == null) {
            return;
        }

        CashPurseAccountTopPanel.populateCashPurseDetails(cashDetails, balance);
        CashPurseAccountBottomPanel.populateCashPurseDetails(cashDetails);

    }

    private void populateBitcoinDetails(BitcoinDetails details) {
        BitcoinAccountTopPanel.populateBitcoinDetails(details);
        BitcoinAccountBottomPanel.populateBitcoinDetails(details);
    }

    public static void reLoadAccount() {

        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";
        String selectedAccount = (String) jTable5.getModel().getValueAt(jTable5.getSelectedRow(), 3);
        if (nymMap != null && nymMap.size() > 0 && jComboBox1.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox1.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox3.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox3.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBox2.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox2.getSelectedIndex() - 1))[1];
        }

        System.out.print("reLoadAccount , nymID :" + nymID + " assetID:" + assetID + " serverID:" + serverID);

        loadAccount(assetID, serverID, nymID);
        boolean isAccountPresent = false;
        for (int i = 0; i < jTable5.getRowCount(); i++) {
            String accountID = (String) jTable5.getModel().getValueAt(i, 3);
            if (selectedAccount.equals(accountID)) {
                isAccountPresent = true;
                jTable5.setRowSelectionInterval(i, i);
            }
        }

        if (!isAccountPresent) {
            clearDetailPage();
        }
    }

    public static void loadAccount(String assetID, String serverID, String nymID) {
        Account account = null;

        for (int i = 0; i < Account.allAccounts.length; i++) {
            try {
                if ("OpenTransactionAccount".equals(Account.allAccounts[i]) || "CashPurseAccount".equals(Account.allAccounts[i])) {
                    Class obj = Class.forName("com.wrapper.core." + Account.allAccounts[i]);

                    account = (Account) obj.newInstance();

                    if (i == 0) {
                        account.clearOTAccountList();
                    }
                    account.loadAccount(assetID, serverID, nymID);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        ((AccountTableModel) jTable5.getModel()).setValue(account.getOTAccountList(), jTable5);

    }

    public static void loadAccount(String assetID, String serverID, String nymID, String selectedID) {
        Account account = null;

        for (int i = 0; i < Account.allAccounts.length; i++) {
            try {
                if ("OpenTransactionAccount".equals(Account.allAccounts[i]) || "CashPurseAccount".equals(Account.allAccounts[i])) {
                    Class obj = Class.forName("com.wrapper.core." + Account.allAccounts[i]);

                    account = (Account) obj.newInstance();

                    if (i == 0) {
                        account.clearOTAccountList();
                    }
                    account.loadAccount(assetID, serverID, nymID);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        ((AccountTableModel) jTable5.getModel()).setValue(account.getOTAccountList(), jTable5);


        for (int i = 0; i < jTable5.getModel().getRowCount(); i++) {
            System.out.println("selectedID:" + selectedID + "jTable5.getModel().getValueAt(i, 3" + (String) jTable5.getModel().getValueAt(i, 3));
            if (selectedID != null && jTable5.getModel().getValueAt(i, 3) != null && selectedID.equals(jTable5.getModel().getValueAt(i, 3))) {
                jTable5.setRowSelectionInterval(i, i);
                break;
            }

        }

    }

    public static void loadOtherTabAccount(String serverID) {
        Account account = null;
        for (int i = 0; i < Account.allAccounts.length; i++) {
            try {
                if (!"OpenTransactionAccount".equals(Account.allAccounts[i]) && !"CashPurseAccount".equals(Account.allAccounts[i])) {
                    Class obj = Class.forName("com.wrapper.core." + Account.allAccounts[i]);

                    account = (Account) obj.newInstance();
                    if (i == 0) {
                        account.clearAccountList();
                    }
                    account.loadAccount("", serverID, "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        ((OtherTabAccountModel) jTable3.getModel()).setValue(account.getAccountList(), jTable3);

    }

    private static void clearOtherTabDetailPage() {

        BitcoinAccountTopPanel.clearPanel();

        BitcoinAccountBottomPanel.clearPanel();
    }

    private static void clearDetailPage() {

        CashPurseAccountTopPanel.clearPanel();
        OpenTransactionAccountTopPanel.clearPanel();
        //BitcoinAccountTopPanel.clearPanel();

        CashPurseAccountBottomPanel.clearPanel();
        OpenTransactionAccountBottomPanel.clearPanel();
        //BitcoinAccountBottomPanel.clearPanel();
    }

    private void initNYMSTab() {

        jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("selectedRow:" + jTable2.getSelectedRow());
                if (jTable2.getSelectedRow() >= 0) {
                    String nymID = (String) jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 1);
                    System.out.println("selected nymID:" + nymID);
                    NYM nym = new NYM();
                    nymBox = nym.loadNymBox(nymID);
                    nymOutBox = nym.loadNymOutBox(nymID);
                    System.out.println("loadNymBox loadNymOutBox data loaded");
                    populateNYMDetails(nymID, nym.getRawFileData(nymID), nymBox, nymOutBox);
                }
            }
        });
        ((NYMTableModel) jTable2.getModel()).setValue(new NYM().loadNYM(), jTable2);
    }

    private void initContractsTab() {
        Contract contract = new Contract();
        jTable7.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("valueChanged:" + jTable7.getSelectedRow() + "e:" + e.getSource());
                if (jTable7.getSelectedRow() >= 0) {
                    String assetID = (String) jTable7.getModel().getValueAt(jTable7.getSelectedRow(), 1);
                    populateAssetContractDetails(assetID, new Contract().getRawAssetFileData(assetID));
                }
            }
        });
        jTable11.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("valueChanged:" + jTable11.getSelectedRow() + "e:" + e.getSource());
                if (jTable11.getSelectedRow() >= 0) {
                    String serverID = (String) jTable11.getModel().getValueAt(jTable11.getSelectedRow(), 1);
                    populateServerContractDetails(serverID, new Contract().getRawServerFileData(serverID));
                }
            }
        });
        ((AssetContractTableModel) jTable7.getModel()).setValue(contract.loadAssetContract(), jTable7);
        ((ServerContractTableModel) jTable11.getModel()).setValue(contract.loadServerContract(), jTable11);
    }

    private void populateNYMDetails(String id, String rawData, Map nymBox, Map nymOutBox) {
        System.out.println("populateNYMDetails nymID:" + id + " rawData:" + rawData);
        jTextField4.setText(id);
        jTextArea3.setText(rawData);
        jTextArea3.setCaretPosition(0);
        System.out.println("populateNYMDetails before setting grids,nymBox:" + nymBox.entrySet() + "----nymOutBox:" + nymOutBox.entrySet());
        ((NYMBoxTableModel) jTable8.getModel()).setValue(nymBox, jTable8);
        ((NYMOutboxTableModel) jTable10.getModel()).setValue(nymOutBox, jTable10);
        System.out.println("populateNYMDetails Ends");
    }

    private void populateAssetContractDetails(String id, String rawData) {
        jTextField7.setText(id);
        jTextArea6.setText(rawData);
        jTextArea6.setCaretPosition(0);
    }

    private void populateServerContractDetails(String id, String rawData) {
        jTextField8.setText(id);
        jTextArea7.setText(rawData);
        jTextArea7.setCaretPosition(0);
    }

    public static void refreshNYMSList() {

        System.out.println("IN refreshNYMSList");

        ((NYMTableModel) jTable2.getModel()).setValue(new NYM().loadNYM(), jTable2);
        nymMap = new NYM().loadNYM();

        String serverID = "ALL";

        if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
        }

        nymRegisteredMap = new NYM().loadRegisteredNYM(serverID);

        Utility.populateCombo(nymMap, jComboBox1);
        Utility.populateComboWithoutAll(nymRegisteredMap, jComboBox6);
    }

    private static void refreshAssetContractList() {

        Contract contract = new Contract();
        ((AssetContractTableModel) jTable7.getModel()).setValue(contract.loadAssetContract(), jTable7);
        assetMap.clear();
        assetMap = contract.loadAssetContract();
        Utility.populateCombo(assetMap, jComboBox3);

    }

    private void refreshServerContractList() {
        Contract contract = new Contract();
        ((ServerContractTableModel) jTable11.getModel()).setValue(contract.loadServerContract(), jTable11);
        serverMap.clear();
        serverMap = contract.loadServerContract();
        System.out.println("serverv" + serverMap.entrySet());
        Utility.populateCombo(serverMap, jComboBox2);
        Utility.populateComboWithoutAll(serverMap, jComboBox5);

    }

    public static JTable getAccountTable() {
        return jTable5;
    }

    public static void setAssets(String serverID) {

        List baskets = Basket.getBasketList(serverID, null);

        if (baskets != null && baskets.size() > 0 && !"Popup Dialog".equalsIgnoreCase(((String[]) baskets.get(0))[0])) {
            ((BasketTableModel) jTable19.getModel()).setValue(baskets);
        }
        refreshAssetContractList();

    }

    private void initSettingsTab() {
        jTextField5.setText(String.valueOf(Configuration.getWaitTime()));

    }

    private void initCreditsTab() {
        final String fileName = "LICENSE-AND-CREDITS.txt";
        /*System.out.println("Path:" + Utility.getDataFolder() + File.separator + fileName);
        jTextArea1.setText(Utility.fileToString(new File(Utility.getDataFolder() + File.separator + fileName)));
         */
        String creditsFileContents = Utility.getCreditsFile(fileName);
        jTextArea1.setText(creditsFileContents);
        jTextArea1.setCaretPosition(0);
    }

    public static void loadOtherTabServers() {

        ((OtherTabServerTableModel) jTable4.getModel()).setValue(StorageHelper.getOtherTabServerList(jComboBox4.getSelectedItem() == null ? "" : jComboBox4.getSelectedItem().toString()), jTable4);

    }

    public static void setOtherTabAccountLabel(String newLabel, int row) {
        jTable3.getModel().setValueAt(newLabel, row, 0);
        ((OtherTabAccountModel) jTable3.getModel()).fireTableDataChanged();

    }

    private void initMarketsTab() {
          
        jLabel25.setText("");
        jLabel46.setText("");
        jLabel30.setText("");
        jLabel27.setText("");
        jLabel39.setText("");
        jLabel32.setText("");
        jLabel40.setText("");
        jLabel41.setText("");
        jLabel59.setText("");
        jLabel60.setText("");
        jLabel61.setText("");

        MarketTableModel.removeCols(jTable13);
        MarketOffersTableModel.removeCols(jTable14);

        Utility.populateComboWithoutAll(serverMap, jComboBox5);

        String serverID = "ALL";
        String nymID = "ALL";

        if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
        }

        nymRegisteredMap = new NYM().loadRegisteredNYM(serverID);
        Utility.populateComboWithoutAll(nymRegisteredMap, jComboBox6);

        jTable14.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("valueChanged Market List Action Listener :" + jTable14.getSelectedRow() + "e:" + e.getSource());
                // Get nym offer details
                nymOfferClick();

            }
        });


        jTable13.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("jTable13 valueChanged Market List Action Listener :" + jTable13.getSelectedRow() + "e:" + e.getSource());
                // Get market details

                marketListClick();

            }
        });

        if (nymRegisteredMap != null && nymRegisteredMap.size() > 0 && jComboBox6.getSelectedIndex() > -1) {
            nymID = ((String[]) nymRegisteredMap.get((Integer) jComboBox6.getSelectedIndex()))[1];
        }
        if (!"ALL".equalsIgnoreCase(nymID)) {
            Map nymTrades = Market.getNymTrades(serverID, nymID);
            if (nymTrades != null) {
                ((MarketTradesTableModel) jTable16.getModel()).setValue(nymTrades, jTable16);
            }
        }
    }

    private void nymOfferClick() {
        try {

            String serverID = "ALL";
            String nymID = "ALL";
            if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
                serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
            }
            if (nymRegisteredMap != null && nymRegisteredMap.size() > 0 && jComboBox6.getSelectedIndex() > -1) {
                nymID = ((String[]) nymRegisteredMap.get((Integer) jComboBox6.getSelectedIndex()))[1];
            }
            if (!"ALL".equalsIgnoreCase(nymID)) {
                NymOfferDetails nymOfferDetails = Market.getNymOfferDetails(serverID, nymID, (String) jTable14.getModel().getValueAt(jTable14.getSelectedRow(), 0));
                jLabel47.setVisible(true);
                jLabel48.setVisible(true);
                jLabel49.setVisible(true);
                jLabel50.setVisible(true);

                if (null != nymOfferDetails) {
                    jLabel47.setText(nymOfferDetails.getPrice());
                    jLabel48.setText(nymOfferDetails.getMinIncrement());
                    jLabel49.setText(nymOfferDetails.getTotalAssetsOnOffer());
                    jLabel50.setText(nymOfferDetails.getAssetsStillOnOffer());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void marketListClick() {
                
        try {
            String serverID = "ALL";
            String nymID = "ALL";
            if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
                serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
            }
            if (nymRegisteredMap != null && nymRegisteredMap.size() > 0 && jComboBox6.getSelectedIndex() > -1) {
                nymID = ((String[]) nymRegisteredMap.get((Integer) jComboBox6.getSelectedIndex()))[1];
            }
            if (!"ALL".equalsIgnoreCase(nymID)) {
                MarketDetails marketDetails = Market.getMarketDetails((String) jTable13.getModel().getValueAt(jTable13.getSelectedRow(), 1), serverID, nymID);
                System.out.println("marketDetails:" + marketDetails);
                if (marketDetails != null) {
                    MarketTicker marketTicker = marketDetails.getMarketTicker();
                    jLabel3.setText("Last:" + marketTicker.getLastPrice() + "          Bid:" + marketTicker.getHighestBid() + "          Ask:" + marketTicker.getLowestAsk());
                    jLabel46.setVisible(true);
                    jLabel46.setText(marketDetails.getAssetTypeID() == null ? "" : marketDetails.getAssetTypeID());
                    jLabel46.setToolTipText(jLabel46.getText());
                    jLabel25.setVisible(true);
                    jLabel25.setText(marketDetails.getAssetTypeName() == null ? "" : marketDetails.getAssetTypeName());
                    jLabel30.setVisible(true);
                    jLabel25.setToolTipText(jLabel25.getText());
                    jLabel30.setText(marketDetails.getCurrencyName() == null ? "" : marketDetails.getCurrencyName());
                    jLabel30.setToolTipText(jLabel30.getText());
                    jLabel27.setVisible(true);
                    jLabel27.setText(marketDetails.getCurrencyID() == null ? "" : marketDetails.getCurrencyID());
                    jLabel27.setToolTipText(jLabel27.getText());
                    jLabel39.setVisible(true);
                    jLabel39.setText(marketDetails.getServerName() == null ? "" : marketDetails.getServerName());
                    jLabel39.setToolTipText(jLabel39.getText());
                    jLabel32.setVisible(true);
                    jLabel32.setText(marketDetails.getServerID() == null ? "" : marketDetails.getServerID());
                    jLabel32.setToolTipText(jLabel32.getText());
                    jLabel41.setVisible(true);
                    jLabel40.setVisible(true);
                    jLabel40.setText("Scale");
                    jLabel41.setText(marketDetails.getGranularity() == null ? "" : marketDetails.getGranularity());
                    jLabel41.setToolTipText(jLabel41.getText());

                    jLabel59.setText(marketDetails.getTotalAssets());
                    jLabel59.setToolTipText(jLabel59.getText());

                    jLabel60.setText(marketDetails.getNbrBids());
                    jLabel60.setToolTipText(jLabel60.getText());

                    jLabel61.setText(marketDetails.getNbrAsks());
                    jLabel61.setToolTipText(jLabel61.getText());

                    ((MarketBidTableModel) jTable15.getModel()).setValue(marketDetails.getMarketBid(), jTable15);
                    ((MarketAskTableModel) jTable17.getModel()).setValue(marketDetails.getMarketAsk(), jTable17);
                    ((MarketRecentTradesTableModel) jTable18.getModel()).setValue(marketDetails.getMarketRecentTrades(), jTable18);
                    ((MarketOffersTableModel) jTable14.getModel()).setValue(marketDetails.getNymOffers(), jTable14);
                    if (jTable14.getRowCount() > 0) {
                        jTable14.setRowSelectionInterval(0, 0);
                        nymOfferClick();
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initBasketsTab() {

        Utility.populateComboWithoutAll(serverMap, jComboBox7);
        BasketTableModel.removeCols(jTable19);
        jLabel62.setText("");

        String currentServerID = "ALL";

        if (serverMap != null && serverMap.size() > 0 && jComboBox7.getSelectedIndex() > -1) {
            currentServerID = ((String[]) serverMap.get((Integer) jComboBox7.getSelectedIndex()))[1];
        }

        List baskets = Basket.getBasketList(currentServerID, null);

        System.out.println("baskets:" + baskets);
        System.out.println("baskets.size():" + baskets.size());


        if (baskets != null && baskets.size() > 0 && !"Popup Dialog".equalsIgnoreCase(((String[]) baskets.get(0))[0])) {
            ((BasketTableModel) jTable19.getModel()).setValue(baskets);
        } else if (baskets != null && baskets.size() != 0) {

            // Show dialog asking for register
            new RegisterNymOnServerDialog(this, true, currentServerID).setVisible(true);
            if (Utility.getNymID() == null) {
                return;
            }
            String nymID = Utility.getNymID();
            ((BasketTableModel) jTable19.getModel()).setValue(Basket.getBasketList(currentServerID, nymID));

        }
        jTable19.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("jTable19 valueChanged Basket List Action Listener :" + jTable19.getSelectedRow() + "e:" + e.getSource());

                basketClick();
            }
        });


    }

    private void basketClick() {

        try {
            jButton33.setEnabled(true);
            jButton31.setEnabled(true);
            String serverID = "ALL";

            if (serverMap != null && serverMap.size() > 0 && jComboBox7.getSelectedIndex() > -1) {
                serverID = ((String[]) serverMap.get((Integer) jComboBox7.getSelectedIndex()))[1];
            }
            if (jTable19.getSelectedRow() > -1) {
                String assetTypeID = (String) ((BasketTableModel) jTable19.getModel()).getValueAt(jTable19.getSelectedRow(), 1);
                String assetTypeName = Basket.getAssetTypeName(assetTypeID, serverID);
                if ("Popup Dialog".equals(assetTypeName)) {
                    // Show dialog asking for register
                    new RegisterNymOnServerDialog(this, true, serverID).setVisible(true);
                    if (Utility.getNymID() == null) {
                        return;
                    }
                    String nymID = Utility.getNymID();
                    assetTypeName = Basket.getAssetTypeNameForRegNym(assetTypeID, serverID, nymID);
                }

                jTextField9.setText(assetTypeName);
                jTextField9.setToolTipText(assetTypeName);
                jTextField11.setText(assetTypeID);
                jTextField11.setToolTipText(assetTypeID);

                String basket = Basket.getBasketDetailsLabel(assetTypeID, assetTypeName);

                jLabel62.setText(basket);

                jLabel62.setToolTipText(basket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
