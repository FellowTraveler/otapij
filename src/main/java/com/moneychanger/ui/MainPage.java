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
package com.moneychanger.ui;
//Recurring, Deed/Title, Escrow, Ripple, Settings, Credits
import com.moneychanger.core.Account;
import com.moneychanger.core.Basket;
import com.moneychanger.core.Contract;
import com.moneychanger.core.Market;
import com.moneychanger.core.NYM;
import com.moneychanger.core.OpenTransactionAccount;
import com.moneychanger.core.Payments;
import com.moneychanger.core.RippleAccount;
import com.moneychanger.core.dataobjects.BitcoinDetails;
import com.moneychanger.core.dataobjects.CashPurseDetails;
import com.moneychanger.core.dataobjects.MarketDetails;
import com.moneychanger.core.dataobjects.MarketTicker;
import com.moneychanger.core.dataobjects.NymOfferDetails;
import com.moneychanger.core.dataobjects.OTDetails;
import com.moneychanger.core.datastore.StorageHelper;
import com.moneychanger.core.util.ComboObject;
import com.moneychanger.core.util.Configuration;
import com.moneychanger.core.util.Utility;
import com.moneychanger.ui.custom.PaymentInboxRightClickListener;
import com.moneychanger.ui.dialogs.AccountAdditionDialog;
import com.moneychanger.ui.dialogs.AccountEditDialog;
import com.moneychanger.ui.dialogs.AddBasketDialog;
import com.moneychanger.ui.dialogs.AddressBookDialog;
import com.moneychanger.ui.dialogs.AssetContractEditDialog;
import com.moneychanger.ui.dialogs.ContractAdditionDialog;
import com.moneychanger.ui.dialogs.CreateMarketOrder;
import com.moneychanger.ui.dialogs.DeleteNYMDialog;
import com.moneychanger.ui.dialogs.ImportNYMDialog;
import com.moneychanger.ui.dialogs.IssueAssetContractDialog;
import com.moneychanger.ui.dialogs.NYMAdditionDialog;
import com.moneychanger.ui.dialogs.NYMEditDialog;
import com.moneychanger.ui.dialogs.NymBoxDetailsDialog;
import com.moneychanger.ui.dialogs.OtherTabAccountEditDialog;
import com.moneychanger.ui.dialogs.OtherTabServerAddDialog;
import com.moneychanger.ui.dialogs.OtherTabServerEditDialog;
import com.moneychanger.ui.dialogs.PaymentOutboxDetailsDialog;
import com.moneychanger.ui.dialogs.ProposePaymentDialog;
import com.moneychanger.ui.dialogs.RegisterNYMDialog;
import com.moneychanger.ui.dialogs.RegisterNymOnServerDialog;
import com.moneychanger.ui.dialogs.SendMessageDialog;
import com.moneychanger.ui.dialogs.ServerAcctDialog;
import com.moneychanger.ui.dialogs.ServerContractEditDialog;
import com.moneychanger.ui.dialogs.ServerSelectionDialog;
import com.moneychanger.ui.model.AccountTableModel;
import com.moneychanger.ui.model.AssetContractTableModel;
import com.moneychanger.ui.model.BasketTableModel;
import com.moneychanger.ui.model.MarketAskTableModel;
import com.moneychanger.ui.model.MarketBidTableModel;
import com.moneychanger.ui.model.MarketOffersTableModel;
import com.moneychanger.ui.model.MarketRecentTradesTableModel;
import com.moneychanger.ui.model.MarketTableModel;
import com.moneychanger.ui.model.MarketTradesTableModel;
import com.moneychanger.ui.model.NYMBoxTableModel;
import com.moneychanger.ui.model.NYMOutboxTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import com.moneychanger.ui.model.NYMTableModel;
import com.moneychanger.ui.model.OtherTabAccountModel;
import com.moneychanger.ui.model.OtherTabServerTableModel;
import com.moneychanger.ui.model.PaymentInboxTableModel;
import com.moneychanger.ui.model.PaymentOutboxTableModel;
import com.moneychanger.ui.model.PaymentRecordboxTableModel;
import com.moneychanger.ui.model.ServerContractTableModel;
import com.moneychanger.ui.panels.BitcoinAccountBottomPanel;
import com.moneychanger.ui.panels.BitcoinAccountTopPanel;
import com.moneychanger.ui.panels.BlankPanel;
import com.moneychanger.ui.panels.CashPurseAccountBottomPanel;
import com.moneychanger.ui.panels.CashPurseAccountTopPanel;
import com.moneychanger.ui.panels.OpenTransactionAccountBottomPanel;
import com.moneychanger.ui.panels.OpenTransactionAccountTopPanel;
import com.moneychanger.ui.panels.RippleAccountTopPanel;
import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
//import org.jvnet.substance.SubstanceLookAndFeel;
//import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;

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
    private static boolean isMarketInit = false;
    private static boolean isPaymentsInit = false;
    TrayIcon trayIcon;
    SystemTray tray;

    /** Creates new form MainPage */
    public MainPage() {
        // this.setExtendedState(MAXIMIZED_BOTH);
        super("Moneychanger");

        try {

//            SubstanceLookAndFeel laf = new SubstanceModerateLookAndFeel();
//            UIManager.setLookAndFeel(laf);

            if (false == System.getProperty("os.name").toLowerCase().contains("linux")) {
                setToSystray();
            }
            // ---------------------------------
            setTitle("Moneychanger");
            initComponents();
            initMainTab();
            initOtherTab();
            initNYMSTab();
            initContractsTab();
            //initMarketsTab();
            //initBasketsTab();
            initSettingsTab();
            initCreditsTab();
            setResizable(true);
            this.setLocationRelativeTo(null);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Utility.getSettingsObj() != null) {
                ((JFrame) Utility.getSettingsObj()).dispose();
            }
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private void setToSystray() {

        Image image = null;

        System.out.println("creating instance");

        if (SystemTray.isSupported()) {
            System.out.println("system tray supported");
            tray = SystemTray.getSystemTray();

            ImageIcon image1 = new javax.swing.ImageIcon(getClass().getResource("/com/moneychanger/ui/images/images.jpeg"));
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
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane_MainPage = new javax.swing.JTabbedPane();
        jPanel_Main = new javax.swing.JPanel();
        jPanel_Accounts = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel_FilterAccounts = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel_Nyms = new javax.swing.JLabel();
        jComboBox_Nyms = new com.moneychanger.ui.custom.SteppedComboBox();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel_ServerContracts = new javax.swing.JLabel();
        jComboBoxServerContracts = new com.moneychanger.ui.custom.SteppedComboBox();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel_AssetContracts = new javax.swing.JLabel();
        jComboBox_AssetContracts = new com.moneychanger.ui.custom.SteppedComboBox();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jScrollPane_Accounts = new javax.swing.JScrollPane();
        jTable_AccountTable = new com.moneychanger.ui.custom.CustomTable();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel_ListActions = new javax.swing.JPanel();
        jButton_AddAccount = new javax.swing.JButton();
        jButton_EditAccount = new javax.swing.JButton();
        jButton_RemoveAccount = new javax.swing.JButton();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(500, 0), new java.awt.Dimension(500, 0), new java.awt.Dimension(32767, 32767));
        jPanel_TopPanel = new javax.swing.JPanel();
        jPanel_BottomPanel = new javax.swing.JPanel();
        jPanel_Nyms = new javax.swing.JPanel();
        jPanel_NmysList = new javax.swing.JPanel();
        jScrollPane_NymsList = new javax.swing.JScrollPane();
        jTable_NymsList = new com.moneychanger.ui.custom.CustomTable();
        jPanel_ListActions1 = new javax.swing.JPanel();
        jButton_AddAccount1 = new javax.swing.JButton();
        jButton_EditAccount1 = new javax.swing.JButton();
        jButton_RemoveAccount1 = new javax.swing.JButton();
        jButton_ImportNym = new javax.swing.JButton();
        jButton_RegisterNym = new javax.swing.JButton();
        jPanel_NymInfo = new javax.swing.JPanel();
        jLabel_NymID = new javax.swing.JLabel();
        jTextField_NymID = new javax.swing.JTextField();
        jLabel_NymMisc = new javax.swing.JLabel();
        jScrollPane_NymMisc = new javax.swing.JScrollPane();
        jTextArea_NymMisc = new javax.swing.JTextArea();
        jPanel_Nymbox = new javax.swing.JPanel();
        jTabbedPane_Nymbox = new javax.swing.JTabbedPane();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable8 = new com.moneychanger.ui.custom.CustomTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable10 = new com.moneychanger.ui.custom.CustomTable();
        jButton_Compose = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jButton_DownloadMail = new javax.swing.JButton();
        jPanel_Contracts = new javax.swing.JPanel();
        jPanel_AssetList = new javax.swing.JPanel();
        jScrollPane_AssetList = new javax.swing.JScrollPane();
        jTable_AssetList = new com.moneychanger.ui.custom.CustomTable();
        jPanel_ListActions2 = new javax.swing.JPanel();
        jButton_AddAccount2 = new javax.swing.JButton();
        jButton_EditAccount2 = new javax.swing.JButton();
        jButton_RemoveAccount2 = new javax.swing.JButton();
        jPanel_AssetInfo = new javax.swing.JPanel();
        jLabel_AssetID = new javax.swing.JLabel();
        jTextField_AssetID = new javax.swing.JTextField();
        jButton_AssetIssue = new javax.swing.JButton();
        jLabel_AssetMisc = new javax.swing.JLabel();
        jScrollPane_AssetMisc = new javax.swing.JScrollPane();
        jTextArea_AssetMisc = new javax.swing.JTextArea();
        jPanel_ServerList = new javax.swing.JPanel();
        jScrollPane_ServerList = new javax.swing.JScrollPane();
        jTable_ServerList = new com.moneychanger.ui.custom.CustomTable();
        jPanel_ListActions3 = new javax.swing.JPanel();
        jButton_AddAccount3 = new javax.swing.JButton();
        jButton_EditAccount3 = new javax.swing.JButton();
        jButton_RemoveAccount3 = new javax.swing.JButton();
        jPanel_ServerInfo = new javax.swing.JPanel();
        jLabel_ServerID = new javax.swing.JLabel();
        jTextField_ServerID = new javax.swing.JTextField();
        jLabel_ServerMisc = new javax.swing.JLabel();
        jScrollPane_ServerMisc = new javax.swing.JScrollPane();
        jTextArea_ServerMisc = new javax.swing.JTextArea();
        jPanel_Baskets = new javax.swing.JPanel();
        jComboBox7 = new com.moneychanger.ui.custom.SteppedComboBox();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        jTable19 = new com.moneychanger.ui.custom.CustomTable();
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
        jPanel_Markets = new javax.swing.JPanel();
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
        jTable15 = new com.moneychanger.ui.custom.CustomTable();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTable17 = new com.moneychanger.ui.custom.CustomTable();
        jPanel35 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTable18 = new com.moneychanger.ui.custom.CustomTable();
        jLabel46 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable13 = new com.moneychanger.ui.custom.CustomTable();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable14 = new com.moneychanger.ui.custom.CustomTable();
        jComboBox5 = new com.moneychanger.ui.custom.SteppedComboBox();
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
        jTable16 = new com.moneychanger.ui.custom.CustomTable();
        jLabel3 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jComboBox6 = new com.moneychanger.ui.custom.SteppedComboBox();
        jLabel52 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jPanel_Payments = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        jTable6 = new com.moneychanger.ui.custom.CustomTable();
        jSeparator11 = new javax.swing.JSeparator();
        jPanel_PaymentTop = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane28 = new javax.swing.JScrollPane();
        jTable20 = new com.moneychanger.ui.custom.CustomTable();
        jButton34 = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane29 = new javax.swing.JScrollPane();
        jTable21 = new com.moneychanger.ui.custom.CustomTable();
        jPanel39 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        jTable22 = new javax.swing.JTable();
        jButton35 = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jPanel40 = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jPanel_Deed = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel_Agreements = new javax.swing.JPanel();
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
        jPanel_Other = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new com.moneychanger.ui.custom.CustomTable();
        jPanel25 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new com.moneychanger.ui.custom.CustomTable();
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
        jPanel_Settings = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jPanel_Credits = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel24 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 500));
        setName("MainPageForm"); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jTabbedPane_MainPage.setMinimumSize(new java.awt.Dimension(700, 500));
        jTabbedPane_MainPage.setPreferredSize(new java.awt.Dimension(700, 500));
        jTabbedPane_MainPage.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane_MainPageStateChanged(evt);
            }
        });

        jPanel_Main.setMinimumSize(new java.awt.Dimension(700, 0));
        jPanel_Main.setName("jPanel_Main"); // NOI18N
        jPanel_Main.setPreferredSize(new java.awt.Dimension(700, 0));
        jPanel_Main.setLayout(new java.awt.GridBagLayout());

        jPanel_Accounts.setMinimumSize(new java.awt.Dimension(200, 500));
        jPanel_Accounts.setPreferredSize(new java.awt.Dimension(200, 500));
        jPanel_Accounts.setLayout(new java.awt.GridBagLayout());

        filler1.setName("filler1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 10.0;
        jPanel_Accounts.add(filler1, gridBagConstraints);

        filler2.setName("filler2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler2, gridBagConstraints);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainPage.class);
        jLabel_FilterAccounts.setText(resourceMap.getString("jLabel_FilterAccounts.text")); // NOI18N
        jLabel_FilterAccounts.setPreferredSize(new java.awt.Dimension(100, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Accounts.add(jLabel_FilterAccounts, gridBagConstraints);

        filler3.setName("filler3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler3, gridBagConstraints);

        filler4.setName("filler4"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler4, gridBagConstraints);

        jLabel_Nyms.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Nyms.setText(resourceMap.getString("jLabel_Nyms.text")); // NOI18N
        jLabel_Nyms.setMaximumSize(new java.awt.Dimension(32767, 32767));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Accounts.add(jLabel_Nyms, gridBagConstraints);

        jComboBox_Nyms.setSelectedItem("ALL");
        jComboBox_Nyms.setMinimumSize(new java.awt.Dimension(0, 0));
        jComboBox_Nyms.setName(""); // NOI18N
        jComboBox_Nyms.setPreferredSize(null);
        jComboBox_Nyms.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_NymsItemStateChanged(evt);
            }
        });
        jComboBox_Nyms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_NymsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Accounts.add(jComboBox_Nyms, gridBagConstraints);

        filler5.setName("filler5"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler5, gridBagConstraints);

        filler6.setName("filler6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler6, gridBagConstraints);

        jLabel_ServerContracts.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_ServerContracts.setText(resourceMap.getString("jLabel_ServerContracts.text")); // NOI18N
        jLabel_ServerContracts.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jLabel_ServerContracts.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Accounts.add(jLabel_ServerContracts, gridBagConstraints);

        jComboBoxServerContracts.setSelectedItem("ALL");
        jComboBoxServerContracts.setMinimumSize(new java.awt.Dimension(0, 0));
        jComboBoxServerContracts.setName(""); // NOI18N
        jComboBoxServerContracts.setPreferredSize(null);
        jComboBoxServerContracts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxServerContractsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Accounts.add(jComboBoxServerContracts, gridBagConstraints);

        filler7.setName("filler7"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler7, gridBagConstraints);

        filler8.setName("filler8"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler8, gridBagConstraints);

        jLabel_AssetContracts.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_AssetContracts.setText(resourceMap.getString("jLabel_AssetContracts.text")); // NOI18N
        jLabel_AssetContracts.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jLabel_AssetContracts.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Accounts.add(jLabel_AssetContracts, gridBagConstraints);

        jComboBox_AssetContracts.setSelectedItem("ALL");
        jComboBox_AssetContracts.setMinimumSize(new java.awt.Dimension(0, 0));
        jComboBox_AssetContracts.setName(""); // NOI18N
        jComboBox_AssetContracts.setPreferredSize(null);
        jComboBox_AssetContracts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_AssetContractsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Accounts.add(jComboBox_AssetContracts, gridBagConstraints);

        filler9.setName("filler9"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler9, gridBagConstraints);

        filler10.setName("filler10"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 4.0;
        jPanel_Accounts.add(filler10, gridBagConstraints);

        filler11.setName("filler11"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler11, gridBagConstraints);

        jScrollPane_Accounts.setMinimumSize(new java.awt.Dimension(50, 50));
        jScrollPane_Accounts.setName("jScrollPane_Accounts"); // NOI18N
        jScrollPane_Accounts.setPreferredSize(new java.awt.Dimension(100, 100));

        jTable_AccountTable.setModel(new AccountTableModel());
        jTable_AccountTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_AccountTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_AccountTableMouseClicked(evt);
            }
        });
        jScrollPane_Accounts.setViewportView(jTable_AccountTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Accounts.add(jScrollPane_Accounts, gridBagConstraints);

        filler12.setName("filler12"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler12, gridBagConstraints);

        filler13.setName("filler13"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel_Accounts.add(filler13, gridBagConstraints);

        filler14.setName("filler14"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler14, gridBagConstraints);

        jPanel_ListActions.setName("jPanel_ListActions"); // NOI18N
        jPanel_ListActions.setLayout(new java.awt.GridBagLayout());

        jButton_AddAccount.setText(resourceMap.getString("jButton_AddAccount.text")); // NOI18N
        jButton_AddAccount.setToolTipText(resourceMap.getString("jButton_AddAccount.toolTipText")); // NOI18N
        jButton_AddAccount.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_AddAccount.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_AddAccount.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_AddAccount.setName(""); // NOI18N
        jButton_AddAccount.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton_AddAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddAccountActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel_ListActions.add(jButton_AddAccount, gridBagConstraints);

        jButton_EditAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/moneychanger/ui/images/pencil.jpg"))); // NOI18N
        jButton_EditAccount.setText(resourceMap.getString("jButton_EditAccount.text")); // NOI18N
        jButton_EditAccount.setToolTipText(resourceMap.getString("jButton_EditAccount.toolTipText")); // NOI18N
        jButton_EditAccount.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_EditAccount.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_EditAccount.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_EditAccount.setName(""); // NOI18N
        jButton_EditAccount.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton_EditAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditAccountActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel_ListActions.add(jButton_EditAccount, gridBagConstraints);

        jButton_RemoveAccount.setText(resourceMap.getString("jButton_RemoveAccount.text")); // NOI18N
        jButton_RemoveAccount.setToolTipText(resourceMap.getString("jButton_RemoveAccount.toolTipText")); // NOI18N
        jButton_RemoveAccount.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_RemoveAccount.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_RemoveAccount.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_RemoveAccount.setName(""); // NOI18N
        jButton_RemoveAccount.setPreferredSize(new java.awt.Dimension(50, 25));
        jButton_RemoveAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveAccountActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel_ListActions.add(jButton_RemoveAccount, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Accounts.add(jPanel_ListActions, gridBagConstraints);

        filler15.setName("filler15"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Accounts.add(filler15, gridBagConstraints);

        filler16.setName("filler16"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 10.0;
        jPanel_Accounts.add(filler16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel_Main.add(jPanel_Accounts, gridBagConstraints);

        filler17.setName("filler17"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel_Main.add(filler17, gridBagConstraints);

        jPanel_TopPanel.setMinimumSize(new java.awt.Dimension(500, 250));
        jPanel_TopPanel.setPreferredSize(new java.awt.Dimension(500, 250));
        jPanel_TopPanel.setVisible(false);

        javax.swing.GroupLayout jPanel_TopPanelLayout = new javax.swing.GroupLayout(jPanel_TopPanel);
        jPanel_TopPanel.setLayout(jPanel_TopPanelLayout);
        jPanel_TopPanelLayout.setHorizontalGroup(
            jPanel_TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );
        jPanel_TopPanelLayout.setVerticalGroup(
            jPanel_TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 1.0;
        jPanel_Main.add(jPanel_TopPanel, gridBagConstraints);

        jPanel_BottomPanel.setMinimumSize(new java.awt.Dimension(500, 250));
        jPanel_BottomPanel.setPreferredSize(new java.awt.Dimension(500, 250));
        jPanel_BottomPanel.setVisible(false);

        javax.swing.GroupLayout jPanel_BottomPanelLayout = new javax.swing.GroupLayout(jPanel_BottomPanel);
        jPanel_BottomPanel.setLayout(jPanel_BottomPanelLayout);
        jPanel_BottomPanelLayout.setHorizontalGroup(
            jPanel_BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );
        jPanel_BottomPanelLayout.setVerticalGroup(
            jPanel_BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 4.0;
        jPanel_Main.add(jPanel_BottomPanel, gridBagConstraints);

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Main.TabConstraints.tabTitle"), jPanel_Main); // NOI18N

        jPanel_Nyms.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel_Nyms.setName("jPanel_Nyms"); // NOI18N
        jPanel_Nyms.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel_Nyms.setLayout(new java.awt.GridBagLayout());

        jPanel_NmysList.setMinimumSize(new java.awt.Dimension(100, 400));
        jPanel_NmysList.setName("jPanel_NmysList"); // NOI18N
        jPanel_NmysList.setPreferredSize(new java.awt.Dimension(150, 500));
        jPanel_NmysList.setLayout(new java.awt.GridBagLayout());

        jScrollPane_NymsList.setMinimumSize(new java.awt.Dimension(150, 100));
        jScrollPane_NymsList.setName("jScrollPane_NymsList"); // NOI18N
        jScrollPane_NymsList.setPreferredSize(new java.awt.Dimension(200, 600));

        jTable_NymsList.setModel(new NYMTableModel());
        jTable_NymsList.setName("jTable_NymsList"); // NOI18N
        jTable_NymsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_NymsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_NymsListMouseClicked(evt);
            }
        });
        jScrollPane_NymsList.setViewportView(jTable_NymsList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel_NmysList.add(jScrollPane_NymsList, gridBagConstraints);

        jPanel_ListActions1.setMinimumSize(new java.awt.Dimension(110, 40));
        jPanel_ListActions1.setName("jPanel_ListActions1"); // NOI18N
        jPanel_ListActions1.setLayout(new java.awt.GridBagLayout());

        jButton_AddAccount1.setText(resourceMap.getString("jButton_AddAccount1.text")); // NOI18N
        jButton_AddAccount1.setToolTipText(resourceMap.getString("jButton_AddAccount1.toolTipText")); // NOI18N
        jButton_AddAccount1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_AddAccount1.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_AddAccount1.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_AddAccount1.setName("jButton_AddAccount1"); // NOI18N
        jButton_AddAccount1.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_AddAccount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddAccount1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel_ListActions1.add(jButton_AddAccount1, gridBagConstraints);

        jButton_EditAccount1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/moneychanger/ui/images/pencil.jpg"))); // NOI18N
        jButton_EditAccount1.setToolTipText(resourceMap.getString("jButton_EditAccount1.toolTipText")); // NOI18N
        jButton_EditAccount1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_EditAccount1.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_EditAccount1.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_EditAccount1.setName("jButton_EditAccount1"); // NOI18N
        jButton_EditAccount1.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_EditAccount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditAccount1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel_ListActions1.add(jButton_EditAccount1, gridBagConstraints);

        jButton_RemoveAccount1.setText(resourceMap.getString("jButton_RemoveAccount1.text")); // NOI18N
        jButton_RemoveAccount1.setToolTipText(resourceMap.getString("jButton_RemoveAccount1.toolTipText")); // NOI18N
        jButton_RemoveAccount1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_RemoveAccount1.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_RemoveAccount1.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_RemoveAccount1.setName("jButton_RemoveAccount1"); // NOI18N
        jButton_RemoveAccount1.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_RemoveAccount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveAccount1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel_ListActions1.add(jButton_RemoveAccount1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel_NmysList.add(jPanel_ListActions1, gridBagConstraints);

        jButton_ImportNym.setText(resourceMap.getString("jButton_ImportNym.text")); // NOI18N
        jButton_ImportNym.setName("jButton_ImportNym"); // NOI18N
        jButton_ImportNym.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ImportNymActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel_NmysList.add(jButton_ImportNym, gridBagConstraints);

        jButton_RegisterNym.setText(resourceMap.getString("jButton_RegisterNym.text")); // NOI18N
        jButton_RegisterNym.setName("jButton_RegisterNym"); // NOI18N
        jButton_RegisterNym.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegisterNymActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel_NmysList.add(jButton_RegisterNym, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_Nyms.add(jPanel_NmysList, gridBagConstraints);

        jPanel_NymInfo.setName("jPanel_NymInfo"); // NOI18N
        jPanel_NymInfo.setLayout(new java.awt.GridBagLayout());

        jLabel_NymID.setText(resourceMap.getString("jLabel_NymID.text")); // NOI18N
        jLabel_NymID.setName("jLabel_NymID"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_NymInfo.add(jLabel_NymID, gridBagConstraints);

        jTextField_NymID.setEditable(false);
        jTextField_NymID.setName("jTextField_NymID"); // NOI18N
        jTextField_NymID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NymIDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_NymInfo.add(jTextField_NymID, gridBagConstraints);

        jLabel_NymMisc.setText(resourceMap.getString("jLabel_NymMisc.text")); // NOI18N
        jLabel_NymMisc.setName("jLabel_NymMisc"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_NymInfo.add(jLabel_NymMisc, gridBagConstraints);

        jScrollPane_NymMisc.setName("jScrollPane_NymMisc"); // NOI18N

        jTextArea_NymMisc.setColumns(20);
        jTextArea_NymMisc.setEditable(false);
        jTextArea_NymMisc.setRows(5);
        jTextArea_NymMisc.setName("jTextArea_NymMisc"); // NOI18N
        jScrollPane_NymMisc.setViewportView(jTextArea_NymMisc);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_NymInfo.add(jScrollPane_NymMisc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_Nyms.add(jPanel_NymInfo, gridBagConstraints);

        jPanel_Nymbox.setName("jPanel_Nymbox"); // NOI18N
        jPanel_Nymbox.setLayout(new java.awt.GridBagLayout());

        jTabbedPane_Nymbox.setMinimumSize(new java.awt.Dimension(200, 100));
        jTabbedPane_Nymbox.setName("jTabbedPane_Nymbox"); // NOI18N
        jTabbedPane_Nymbox.setPreferredSize(new java.awt.Dimension(450, 250));
        jTabbedPane_Nymbox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane_NymboxStateChanged(evt);
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

        jTabbedPane_Nymbox.addTab(resourceMap.getString("jScrollPane15.TabConstraints.tabTitle"), jScrollPane15); // NOI18N

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

        jTabbedPane_Nymbox.addTab(resourceMap.getString("jScrollPane16.TabConstraints.tabTitle"), jScrollPane16); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_Nymbox.add(jTabbedPane_Nymbox, gridBagConstraints);

        jButton_Compose.setText(resourceMap.getString("jButton_Compose.text")); // NOI18N
        jButton_Compose.setName("jButton_Compose"); // NOI18N
        jButton_Compose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ComposeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_Nymbox.add(jButton_Compose, gridBagConstraints);

        jButton_Delete.setText(resourceMap.getString("jButton_Delete.text")); // NOI18N
        jButton_Delete.setName("jButton_Delete"); // NOI18N
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_Nymbox.add(jButton_Delete, gridBagConstraints);

        jButton_DownloadMail.setText(resourceMap.getString("jButton_DownloadMail.text")); // NOI18N
        jButton_DownloadMail.setName("jButton_DownloadMail"); // NOI18N
        jButton_DownloadMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DownloadMailActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_Nymbox.add(jButton_DownloadMail, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_Nyms.add(jPanel_Nymbox, gridBagConstraints);

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Nyms.TabConstraints.tabTitle"), jPanel_Nyms); // NOI18N

        jPanel_Contracts.setName("jPanel_Contracts"); // NOI18N
        jPanel_Contracts.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel_Contracts.setLayout(new java.awt.GridBagLayout());

        jPanel_AssetList.setMinimumSize(new java.awt.Dimension(200, 300));
        jPanel_AssetList.setName("jPanel_AssetList"); // NOI18N
        jPanel_AssetList.setPreferredSize(new java.awt.Dimension(200, 400));
        jPanel_AssetList.setLayout(new java.awt.GridBagLayout());

        jScrollPane_AssetList.setName("jScrollPane_AssetList"); // NOI18N
        jScrollPane_AssetList.setPreferredSize(new java.awt.Dimension(250, 250));

        jTable_AssetList.setModel(new AssetContractTableModel());
        jTable_AssetList.setName("jTable_AssetList"); // NOI18N
        jTable_AssetList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_AssetList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_AssetListMouseClicked(evt);
            }
        });
        jScrollPane_AssetList.setViewportView(jTable_AssetList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        jPanel_AssetList.add(jScrollPane_AssetList, gridBagConstraints);

        jPanel_ListActions2.setMinimumSize(new java.awt.Dimension(110, 30));
        jPanel_ListActions2.setName("jPanel_ListActions2"); // NOI18N

        jButton_AddAccount2.setText(resourceMap.getString("jButton_AddAccount2.text")); // NOI18N
        jButton_AddAccount2.setToolTipText(resourceMap.getString("jButton_AddAccount2.toolTipText")); // NOI18N
        jButton_AddAccount2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_AddAccount2.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_AddAccount2.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_AddAccount2.setName("jButton_AddAccount2"); // NOI18N
        jButton_AddAccount2.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_AddAccount2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddAccount2ActionPerformed(evt);
            }
        });
        jPanel_ListActions2.add(jButton_AddAccount2);

        jButton_EditAccount2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/moneychanger/ui/images/pencil.jpg"))); // NOI18N
        jButton_EditAccount2.setToolTipText(resourceMap.getString("jButton_EditAccount2.toolTipText")); // NOI18N
        jButton_EditAccount2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_EditAccount2.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_EditAccount2.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_EditAccount2.setName("jButton_EditAccount2"); // NOI18N
        jButton_EditAccount2.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_EditAccount2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditAccount2ActionPerformed(evt);
            }
        });
        jPanel_ListActions2.add(jButton_EditAccount2);

        jButton_RemoveAccount2.setText(resourceMap.getString("jButton_RemoveAccount2.text")); // NOI18N
        jButton_RemoveAccount2.setToolTipText(resourceMap.getString("jButton_RemoveAccount2.toolTipText")); // NOI18N
        jButton_RemoveAccount2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_RemoveAccount2.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_RemoveAccount2.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_RemoveAccount2.setName("jButton_RemoveAccount2"); // NOI18N
        jButton_RemoveAccount2.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_RemoveAccount2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveAccount2ActionPerformed(evt);
            }
        });
        jPanel_ListActions2.add(jButton_RemoveAccount2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_AssetList.add(jPanel_ListActions2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel_Contracts.add(jPanel_AssetList, gridBagConstraints);

        jPanel_AssetInfo.setMinimumSize(new java.awt.Dimension(400, 200));
        jPanel_AssetInfo.setName("jPanel_AssetInfo"); // NOI18N
        jPanel_AssetInfo.setPreferredSize(new java.awt.Dimension(550, 300));
        jPanel_AssetInfo.setLayout(new java.awt.GridBagLayout());

        jLabel_AssetID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_AssetID.setText(resourceMap.getString("jLabel_AssetID.text")); // NOI18N
        jLabel_AssetID.setName("jLabel_AssetID"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_AssetInfo.add(jLabel_AssetID, gridBagConstraints);

        jTextField_AssetID.setEditable(false);
        jTextField_AssetID.setName("jTextField_AssetID"); // NOI18N
        jTextField_AssetID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AssetIDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_AssetInfo.add(jTextField_AssetID, gridBagConstraints);

        jButton_AssetIssue.setText(resourceMap.getString("jButton_AssetIssue.text")); // NOI18N
        jButton_AssetIssue.setMaximumSize(new java.awt.Dimension(150, 23));
        jButton_AssetIssue.setMinimumSize(new java.awt.Dimension(100, 23));
        jButton_AssetIssue.setName("jButton_AssetIssue"); // NOI18N
        jButton_AssetIssue.setPreferredSize(new java.awt.Dimension(120, 23));
        jButton_AssetIssue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AssetIssueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_AssetInfo.add(jButton_AssetIssue, gridBagConstraints);

        jLabel_AssetMisc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_AssetMisc.setText(resourceMap.getString("jLabel_AssetMisc.text")); // NOI18N
        jLabel_AssetMisc.setName("jLabel_AssetMisc"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_AssetInfo.add(jLabel_AssetMisc, gridBagConstraints);

        jScrollPane_AssetMisc.setName("jScrollPane_AssetMisc"); // NOI18N
        jScrollPane_AssetMisc.setPreferredSize(new java.awt.Dimension(550, 250));

        jTextArea_AssetMisc.setColumns(20);
        jTextArea_AssetMisc.setEditable(false);
        jTextArea_AssetMisc.setRows(5);
        jTextArea_AssetMisc.setName("jTextArea_AssetMisc"); // NOI18N
        jScrollPane_AssetMisc.setViewportView(jTextArea_AssetMisc);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_AssetInfo.add(jScrollPane_AssetMisc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel_Contracts.add(jPanel_AssetInfo, gridBagConstraints);

        jPanel_ServerList.setMinimumSize(new java.awt.Dimension(200, 300));
        jPanel_ServerList.setName("jPanel_ServerList"); // NOI18N
        jPanel_ServerList.setPreferredSize(new java.awt.Dimension(200, 400));
        jPanel_ServerList.setLayout(new java.awt.GridBagLayout());

        jScrollPane_ServerList.setName("jScrollPane_ServerList"); // NOI18N
        jScrollPane_ServerList.setPreferredSize(new java.awt.Dimension(250, 250));

        jTable_ServerList.setModel(new ServerContractTableModel());
        jTable_ServerList.setName("jTable_ServerList"); // NOI18N
        jTable_ServerList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_ServerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ServerListMouseClicked(evt);
            }
        });
        jScrollPane_ServerList.setViewportView(jTable_ServerList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        jPanel_ServerList.add(jScrollPane_ServerList, gridBagConstraints);

        jPanel_ListActions3.setMinimumSize(new java.awt.Dimension(110, 30));
        jPanel_ListActions3.setName("jPanel_ListActions3"); // NOI18N

        jButton_AddAccount3.setText(resourceMap.getString("jButton_AddAccount3.text")); // NOI18N
        jButton_AddAccount3.setToolTipText(resourceMap.getString("jButton_AddAccount3.toolTipText")); // NOI18N
        jButton_AddAccount3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_AddAccount3.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_AddAccount3.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_AddAccount3.setName("jButton_AddAccount3"); // NOI18N
        jButton_AddAccount3.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_AddAccount3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddAccount3ActionPerformed(evt);
            }
        });
        jPanel_ListActions3.add(jButton_AddAccount3);

        jButton_EditAccount3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/moneychanger/ui/images/pencil.jpg"))); // NOI18N
        jButton_EditAccount3.setToolTipText(resourceMap.getString("jButton_EditAccount3.toolTipText")); // NOI18N
        jButton_EditAccount3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_EditAccount3.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_EditAccount3.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_EditAccount3.setName("jButton_EditAccount3"); // NOI18N
        jButton_EditAccount3.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_EditAccount3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditAccount3ActionPerformed(evt);
            }
        });
        jPanel_ListActions3.add(jButton_EditAccount3);

        jButton_RemoveAccount3.setText(resourceMap.getString("jButton_RemoveAccount3.text")); // NOI18N
        jButton_RemoveAccount3.setToolTipText(resourceMap.getString("jButton_RemoveAccount3.toolTipText")); // NOI18N
        jButton_RemoveAccount3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_RemoveAccount3.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_RemoveAccount3.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_RemoveAccount3.setName("jButton_RemoveAccount3"); // NOI18N
        jButton_RemoveAccount3.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton_RemoveAccount3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveAccount3ActionPerformed(evt);
            }
        });
        jPanel_ListActions3.add(jButton_RemoveAccount3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_ServerList.add(jPanel_ListActions3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel_Contracts.add(jPanel_ServerList, gridBagConstraints);

        jPanel_ServerInfo.setMinimumSize(new java.awt.Dimension(400, 200));
        jPanel_ServerInfo.setName("jPanel_ServerInfo"); // NOI18N
        jPanel_ServerInfo.setPreferredSize(new java.awt.Dimension(550, 300));
        jPanel_ServerInfo.setLayout(new java.awt.GridBagLayout());

        jLabel_ServerID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_ServerID.setText(resourceMap.getString("jLabel_ServerID.text")); // NOI18N
        jLabel_ServerID.setName("jLabel_ServerID"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_ServerInfo.add(jLabel_ServerID, gridBagConstraints);

        jTextField_ServerID.setEditable(false);
        jTextField_ServerID.setName("jTextField_ServerID"); // NOI18N
        jTextField_ServerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ServerIDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_ServerInfo.add(jTextField_ServerID, gridBagConstraints);

        jLabel_ServerMisc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_ServerMisc.setText(resourceMap.getString("jLabel_ServerMisc.text")); // NOI18N
        jLabel_ServerMisc.setName("jLabel_ServerMisc"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_ServerInfo.add(jLabel_ServerMisc, gridBagConstraints);

        jScrollPane_ServerMisc.setName("jScrollPane_ServerMisc"); // NOI18N
        jScrollPane_ServerMisc.setPreferredSize(new java.awt.Dimension(550, 250));

        jTextArea_ServerMisc.setColumns(20);
        jTextArea_ServerMisc.setEditable(false);
        jTextArea_ServerMisc.setRows(5);
        jTextArea_ServerMisc.setName("jTextArea_ServerMisc"); // NOI18N
        jScrollPane_ServerMisc.setViewportView(jTextArea_ServerMisc);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel_ServerInfo.add(jScrollPane_ServerMisc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel_Contracts.add(jPanel_ServerInfo, gridBagConstraints);

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Contracts.TabConstraints.tabTitle"), jPanel_Contracts); // NOI18N

        jPanel_Baskets.setName("jPanel_Baskets"); // NOI18N
        jPanel_Baskets.setPreferredSize(new java.awt.Dimension(800, 600));

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

        jTable19.setModel(new com.moneychanger.ui.model.BasketTableModel());
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

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/moneychanger/ui/images/pencil.jpg"))); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel_BasketsLayout = new javax.swing.GroupLayout(jPanel_Baskets);
        jPanel_Baskets.setLayout(jPanel_BasketsLayout);
        jPanel_BasketsLayout.setHorizontalGroup(
            jPanel_BasketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BasketsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_BasketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_BasketsLayout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jComboBox7, 0, 93, Short.MAX_VALUE))
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_BasketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_BasketsLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_BasketsLayout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel_BasketsLayout.setVerticalGroup(
            jPanel_BasketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BasketsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_BasketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(27, 27, 27)
                .addComponent(jLabel62)
                .addGap(31, 31, 31)
                .addGroup(jPanel_BasketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(553, Short.MAX_VALUE))
            .addGroup(jPanel_BasketsLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel_BasketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(258, 258, 258))
            .addComponent(jSeparator10, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Baskets.TabConstraints.tabTitle"), jPanel_Baskets); // NOI18N

        jPanel_Markets.setName("jPanel_Markets"); // NOI18N
        jPanel_Markets.setPreferredSize(new java.awt.Dimension(800, 600));

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

        jTable15.setModel(new com.moneychanger.ui.model.MarketBidTableModel());
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

        jTable17.setModel(new com.moneychanger.ui.model.MarketAskTableModel());
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

        jTable18.setModel(new com.moneychanger.ui.model.MarketRecentTradesTableModel(jTable18));
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
            .addGap(0, 404, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
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
            .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
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
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jScrollPane21.setName("jScrollPane21"); // NOI18N

        jTable13.setModel(new com.moneychanger.ui.model.MarketTableModel());
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

        jTable14.setModel(new com.moneychanger.ui.model.MarketOffersTableModel());
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
                .addContainerGap(598, Short.MAX_VALUE))
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
                .addContainerGap(140, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab(resourceMap.getString("jPanel32.TabConstraints.tabTitle"), jPanel32); // NOI18N

        jScrollPane22.setName("jScrollPane22"); // NOI18N

        jTable16.setModel(new com.moneychanger.ui.model.MarketTradesTableModel());
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
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel_MarketsLayout = new javax.swing.GroupLayout(jPanel_Markets);
        jPanel_Markets.setLayout(jPanel_MarketsLayout);
        jPanel_MarketsLayout.setHorizontalGroup(
            jPanel_MarketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MarketsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_MarketsLayout.setVerticalGroup(
            jPanel_MarketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MarketsLayout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Markets.TabConstraints.tabTitle"), jPanel_Markets); // NOI18N

        jPanel_Payments.setName("jPanel_Payments"); // NOI18N
        jPanel_Payments.setPreferredSize(new java.awt.Dimension(800, 600));

        jScrollPane27.setName("jScrollPane27"); // NOI18N

        jTable6.setModel(new NYMTableModel());
        jTable6.setName("jTable6"); // NOI18N
        jTable6.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane27.setViewportView(jTable6);

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator11.setName("jSeparator11"); // NOI18N

        jPanel_PaymentTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_PaymentTop.setName("jPanel_PaymentTop"); // NOI18N

        jTabbedPane5.setName("jTabbedPane5"); // NOI18N

        jPanel37.setName("jPanel37"); // NOI18N

        jScrollPane28.setName("jScrollPane28"); // NOI18N

        jTable20.setModel(new com.moneychanger.ui.model.PaymentInboxTableModel());
        jTable20.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable20.setName("jTable20"); // NOI18N
        jTable20.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable20MouseClicked(evt);
            }
        });
        jScrollPane28.setViewportView(jTable20);

        jButton34.setText(resourceMap.getString("jButton34.text")); // NOI18N
        jButton34.setName("jButton34"); // NOI18N
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane28, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(322, Short.MAX_VALUE)
                .addComponent(jButton34)
                .addGap(294, 294, 294))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jButton34))
        );

        jTabbedPane5.addTab(resourceMap.getString("jPanel37.TabConstraints.tabTitle"), jPanel37); // NOI18N

        jPanel38.setName("jPanel38"); // NOI18N

        jScrollPane29.setName("jScrollPane29"); // NOI18N

        jTable21.setModel(new com.moneychanger.ui.model.PaymentOutboxTableModel());
        jTable21.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable21.setName("jTable21"); // NOI18N
        jTable21.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable21MouseClicked(evt);
            }
        });
        jScrollPane29.setViewportView(jTable21);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab(resourceMap.getString("jPanel38.TabConstraints.tabTitle"), jPanel38); // NOI18N

        jPanel39.setName("jPanel39"); // NOI18N

        jScrollPane30.setName("jScrollPane30"); // NOI18N

        jTable22.setModel(new com.moneychanger.ui.model.PaymentRecordboxTableModel());
        jTable22.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable22.setName("jTable22"); // NOI18N
        jTable22.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane30.setViewportView(jTable22);

        jButton35.setName("jButton35"); // NOI18N

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(490, Short.MAX_VALUE))
            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel39Layout.createSequentialGroup()
                    .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel39Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE)))
        );

        jTabbedPane5.addTab(resourceMap.getString("jPanel39.TabConstraints.tabTitle"), jPanel39); // NOI18N

        javax.swing.GroupLayout jPanel_PaymentTopLayout = new javax.swing.GroupLayout(jPanel_PaymentTop);
        jPanel_PaymentTop.setLayout(jPanel_PaymentTopLayout);
        jPanel_PaymentTopLayout.setHorizontalGroup(
            jPanel_PaymentTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PaymentTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel_PaymentTopLayout.setVerticalGroup(
            jPanel_PaymentTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PaymentTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSeparator12.setName("jSeparator12"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Send Transfer", "Send Cheque", "Send Voucher", "Send Cash" }));
        jComboBox8.setSelectedItem(null);
        jComboBox8.setName("jComboBox8"); // NOI18N
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel40.border.title"))); // NOI18N
        jPanel40.setToolTipText(resourceMap.getString("jPanel40.toolTipText")); // NOI18N
        jPanel40.setName("jPanel40"); // NOI18N

        jButton36.setText(resourceMap.getString("jButton36.text")); // NOI18N
        jButton36.setName("jButton36"); // NOI18N
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setText(resourceMap.getString("jButton37.text")); // NOI18N
        jButton37.setName("jButton37"); // NOI18N
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButton37)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_PaymentsLayout = new javax.swing.GroupLayout(jPanel_Payments);
        jPanel_Payments.setLayout(jPanel_PaymentsLayout);
        jPanel_PaymentsLayout.setHorizontalGroup(
            jPanel_PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PaymentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addGroup(jPanel_PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_PaymentsLayout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(9, 9, 9)
                            .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_PaymentTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(342, 342, 342))
            .addGroup(jPanel_PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_PaymentsLayout.createSequentialGroup()
                    .addContainerGap(227, Short.MAX_VALUE)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel_PaymentsLayout.setVerticalGroup(
            jPanel_PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PaymentsLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
            .addGroup(jPanel_PaymentsLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel_PaymentTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
            .addGroup(jPanel_PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_PaymentsLayout.createSequentialGroup()
                    .addGap(344, 344, 344)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(252, Short.MAX_VALUE)))
        );

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Payments.TabConstraints.tabTitle"), jPanel_Payments); // NOI18N

        jPanel_Deed.setName("jPanel_Deed"); // NOI18N
        jPanel_Deed.setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        javax.swing.GroupLayout jPanel_DeedLayout = new javax.swing.GroupLayout(jPanel_Deed);
        jPanel_Deed.setLayout(jPanel_DeedLayout);
        jPanel_DeedLayout.setHorizontalGroup(
            jPanel_DeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DeedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_DeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_DeedLayout.setVerticalGroup(
            jPanel_DeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DeedLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap(496, Short.MAX_VALUE))
        );

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Deed.TabConstraints.tabTitle"), jPanel_Deed); // NOI18N

        jPanel_Agreements.setName("jPanel_Agreements"); // NOI18N
        jPanel_Agreements.setPreferredSize(new java.awt.Dimension(800, 600));

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

        javax.swing.GroupLayout jPanel_AgreementsLayout = new javax.swing.GroupLayout(jPanel_Agreements);
        jPanel_Agreements.setLayout(jPanel_AgreementsLayout);
        jPanel_AgreementsLayout.setHorizontalGroup(
            jPanel_AgreementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AgreementsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_AgreementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                    .addGroup(jPanel_AgreementsLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel_AgreementsLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel_AgreementsLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel_AgreementsLayout.createSequentialGroup()
                .addGroup(jPanel_AgreementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_AgreementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel_AgreementsLayout.setVerticalGroup(
            jPanel_AgreementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AgreementsLayout.createSequentialGroup()
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
                .addContainerGap(327, Short.MAX_VALUE))
        );

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Agreements.TabConstraints.tabTitle"), jPanel_Agreements); // NOI18N

        jPanel_Other.setName("jPanel_Other"); // NOI18N
        jPanel_Other.setPreferredSize(new java.awt.Dimension(800, 600));

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

        jTable3.setModel(new com.moneychanger.ui.model.OtherTabAccountModel());
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

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/moneychanger/ui/images/pencil.jpg"))); // NOI18N
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
                .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jTable4.setModel(new com.moneychanger.ui.model.OtherTabServerTableModel());
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

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/moneychanger/ui/images/pencil.jpg"))); // NOI18N
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
                .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel_OtherLayout = new javax.swing.GroupLayout(jPanel_Other);
        jPanel_Other.setLayout(jPanel_OtherLayout);
        jPanel_OtherLayout.setHorizontalGroup(
            jPanel_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OtherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_OtherLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox4, 0, 132, Short.MAX_VALUE))
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_OtherLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_OtherLayout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_OtherLayout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
            .addGroup(jPanel_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_OtherLayout.createSequentialGroup()
                    .addGap(248, 248, 248)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(771, Short.MAX_VALUE)))
        );
        jPanel_OtherLayout.setVerticalGroup(
            jPanel_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OtherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_OtherLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                    .addGroup(jPanel_OtherLayout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
        );

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Other.TabConstraints.tabTitle"), jPanel_Other); // NOI18N

        jPanel_Settings.setName("jPanel_Settings"); // NOI18N
        jPanel_Settings.setPreferredSize(new java.awt.Dimension(800, 600));

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout jPanel_SettingsLayout = new javax.swing.GroupLayout(jPanel_Settings);
        jPanel_Settings.setLayout(jPanel_SettingsLayout);
        jPanel_SettingsLayout.setHorizontalGroup(
            jPanel_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SettingsLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel_SettingsLayout.setVerticalGroup(
            jPanel_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SettingsLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SettingsLayout.createSequentialGroup()
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE))
                .addGap(1465, 1465, 1465))
        );

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Settings.TabConstraints.tabTitle"), jPanel_Settings); // NOI18N

        jPanel_Credits.setName("jPanel_Credits"); // NOI18N
        jPanel_Credits.setPreferredSize(new java.awt.Dimension(800, 600));

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

        javax.swing.GroupLayout jPanel_CreditsLayout = new javax.swing.GroupLayout(jPanel_Credits);
        jPanel_Credits.setLayout(jPanel_CreditsLayout);
        jPanel_CreditsLayout.setHorizontalGroup(
            jPanel_CreditsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CreditsLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel_CreditsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_CreditsLayout.setVerticalGroup(
            jPanel_CreditsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CreditsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane_MainPage.addTab(resourceMap.getString("jPanel_Credits.TabConstraints.tabTitle"), jPanel_Credits); // NOI18N

        getContentPane().add(jTabbedPane_MainPage);
        jTabbedPane_MainPage.getAccessibleContext().setAccessibleName(resourceMap.getString("jTabbedPane_MainPage.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_NymsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_NymsListMouseClicked
}//GEN-LAST:event_jTable_NymsListMouseClicked

    private void jTextField_NymIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NymIDActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField_NymIDActionPerformed

    private void jComboBox_AssetContractsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_AssetContractsActionPerformed
        System.out.println("In Action for Asset Combo main tab");
        /*String nymID = Utility.getKey(nymMap, (String) jComboBox1.getSelectedItem());
        String assetID = Utility.getKey(assetMap, (String) jComboBox3.getSelectedItem());
        String serverID = Utility.getKey(serverMap, (String) jComboBox2.getSelectedItem());*/
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox_Nyms.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox_Nyms.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox_AssetContracts.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox_AssetContracts.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBoxServerContracts.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBoxServerContracts.getSelectedIndex() - 1))[1];
        }

        System.out.print("nymiiidL:" + nymID);
        loadAccount(assetID, serverID, nymID);
        clearDetailPage();
}//GEN-LAST:event_jComboBox_AssetContractsActionPerformed

    private void jButton_RemoveAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveAccountActionPerformed
        if (jTable_AccountTable.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select account to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userSelection = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete account", "Delete Account", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        System.out.println("userSelection:" + userSelection);
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox_Nyms.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox_Nyms.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox_AssetContracts.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox_AssetContracts.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBoxServerContracts.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBoxServerContracts.getSelectedIndex() - 1))[1];
        }

        // pass serverID and nymID here
        OpenTransactionAccount openTransaction = new OpenTransactionAccount(serverID, nymID);
        try {
            if (userSelection == 0) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                boolean status = openTransaction.deleteAccount((String) jTable_AccountTable.getModel().getValueAt(jTable_AccountTable.getSelectedRow(), 3));
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
}//GEN-LAST:event_jButton_RemoveAccountActionPerformed

    private void jButton_AddAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddAccountActionPerformed

        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox_Nyms.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox_Nyms.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox_AssetContracts.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox_AssetContracts.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBoxServerContracts.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBoxServerContracts.getSelectedIndex() - 1))[1];
        }

        new AccountAdditionDialog(this, true, nymID, assetID, serverID, "OT", (String) jTable_AccountTable.getModel().getValueAt(jTable_AccountTable.getSelectedRow(), 3)).setVisible(true);
        System.out.print("assetID:" + assetID);
        System.out.print("serverID:" + serverID);
        System.out.print("nymiiidL:" + nymID);

}//GEN-LAST:event_jButton_AddAccountActionPerformed

    private void jButton_EditAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditAccountActionPerformed
        if (jTable_AccountTable.getSelectedRow() > -1) {
            new AccountEditDialog(this, true, (String) jTable_AccountTable.getModel().getValueAt(jTable_AccountTable.getSelectedRow(), 3), (String) jTable_AccountTable.getModel().getValueAt(jTable_AccountTable.getSelectedRow(), 2)).setVisible(true);
            System.out.println("ggggggggjTable5.getSelectedRow():" + jTable_AccountTable.getSelectedRow());
            String nymID = "ALL";
            String assetID = "ALL";
            String serverID = "ALL";

            if (nymMap != null && nymMap.size() > 0 && jComboBox_Nyms.getSelectedIndex() > 0) {
                nymID = ((String[]) nymMap.get((Integer) jComboBox_Nyms.getSelectedIndex() - 1))[1];
            }

            if (assetMap != null && assetMap.size() > 0 && jComboBox_AssetContracts.getSelectedIndex() > 0) {
                assetID = ((String[]) assetMap.get((Integer) jComboBox_AssetContracts.getSelectedIndex() - 1))[1];
            }

            if (serverMap != null && serverMap.size() > 0 && jComboBoxServerContracts.getSelectedIndex() > 0) {
                serverID = ((String[]) serverMap.get((Integer) jComboBoxServerContracts.getSelectedIndex() - 1))[1];
            }
            loadAccount(assetID, serverID, nymID);
            //jTable5.setValueAt("", jTable5.getSelectedRow(), 0);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an account to edit.", "NYM Edit Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_EditAccountActionPerformed

    private void jComboBoxServerContractsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxServerContractsActionPerformed
        System.out.println("In Action for Server Combo main tab");
        /*String nymID = Utility.getKey(nymMap, (String) jComboBox1.getSelectedItem());
        String assetID = Utility.getKey(assetMap, (String) jComboBox3.getSelectedItem());
        String serverID = Utility.getKey(serverMap, (String) jComboBox2.getSelectedItem());*/
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox_Nyms.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox_Nyms.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox_AssetContracts.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox_AssetContracts.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBoxServerContracts.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBoxServerContracts.getSelectedIndex() - 1))[1];
        }

        System.out.print("serverID----------------------------:" + serverID);
        loadAccount(assetID, serverID, nymID);
        clearDetailPage();
}//GEN-LAST:event_jComboBoxServerContractsActionPerformed

    private void jTable_AccountTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_AccountTableMouseClicked
}//GEN-LAST:event_jTable_AccountTableMouseClicked

    private void jComboBox_NymsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_NymsActionPerformed
        System.out.println("In Action for NYM Combo Main tab");
        /*String nymID = Utility.getKey(nymMap, (String) jComboBox1.getSelectedItem());
        String assetID = Utility.getKey(assetMap, (String) jComboBox3.getSelectedItem());
        String serverID = Utility.getKey(serverMap, (String) jComboBox2.getSelectedItem());*/
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox_Nyms.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox_Nyms.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox_AssetContracts.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox_AssetContracts.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBoxServerContracts.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBoxServerContracts.getSelectedIndex() - 1))[1];
        }

        System.out.print(":" + nymID);
        System.out.print("serverID:" + serverID);
        System.out.print("assetID:" + assetID);
        loadAccount(assetID, serverID, nymID);
        clearDetailPage();
}//GEN-LAST:event_jComboBox_NymsActionPerformed

    private void jTextField_AssetIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_AssetIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AssetIDActionPerformed

    private void jTable_AssetListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_AssetListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_AssetListMouseClicked

    private void jTextField_ServerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ServerIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ServerIDActionPerformed

    private void jTable_ServerListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ServerListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_ServerListMouseClicked

    private void jButton_AssetIssueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AssetIssueActionPerformed
        if ("".equalsIgnoreCase(jTextArea_AssetMisc.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Please select Asset Contract", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nymID = "ALL";
        String assetID = "ALL";
        String serverID = "ALL";

        if (nymMap != null && nymMap.size() > 0 && jComboBox_Nyms.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox_Nyms.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox_AssetContracts.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox_AssetContracts.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBoxServerContracts.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBoxServerContracts.getSelectedIndex() - 1))[1];
        }

        System.out.print(":" + nymID);
        System.out.print("serverID:" + serverID);
        System.out.print("assetID:" + assetID);
        new IssueAssetContractDialog(this, true, jTextArea_AssetMisc.getText(), assetID, serverID, nymID).setVisible(true);

    }//GEN-LAST:event_jButton_AssetIssueActionPerformed

    private void jButton_ComposeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ComposeActionPerformed
        if ("".equalsIgnoreCase(jTextField_NymID.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Please select NYM", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new SendMessageDialog(this, true, jTextField_NymID.getText(), jTable10).setVisible(true);
    }//GEN-LAST:event_jButton_ComposeActionPerformed

    private void jButton_DownloadMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DownloadMailActionPerformed
        if (jTextField_NymID.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select NYM", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        NYM nym = new NYM();
        nymBox = nym.downloadNymBox(jTextField_NymID.getText());
        ((NYMBoxTableModel) jTable8.getModel()).setValue(nymBox, jTable8);
    }//GEN-LAST:event_jButton_DownloadMailActionPerformed

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        if (jTable8.getSelectedRow() < 0 && jTable10.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select mail to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            NYM nym = new NYM();
            if (jTabbedPane_Nymbox.getSelectedIndex() == 0) {
                boolean success = nym.deleteMail(jTextField_NymID.getText(), Integer.parseInt((String) jTable8.getModel().getValueAt(jTable8.getSelectedRow(), 3)));
                int previousSelection = jTable8.getSelectedRow();
                if (success) {
                    //JOptionPane.showMessageDialog(this, "Mail deleted successfully", "Mail Deletion Success", JOptionPane.INFORMATION_MESSAGE);
                    ((NYMBoxTableModel) jTable8.getModel()).setValue(nym.loadNymBox(jTextField_NymID.getText()), jTable8);
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

                boolean success = nym.deleteOutboxMail(jTextField_NymID.getText(), Integer.parseInt((String) jTable10.getModel().getValueAt(jTable10.getSelectedRow(), 3)));
                int previousSelection = jTable10.getSelectedRow();
                if (success) {
                    //JOptionPane.showMessageDialog(this, "Mail deleted successfully", "Mail Deletion Success", JOptionPane.INFORMATION_MESSAGE);
                    ((NYMOutboxTableModel) jTable10.getModel()).setValue(nym.loadNymOutBox(jTextField_NymID.getText()), jTable10);
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
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jComboBox_NymsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_NymsItemStateChanged
        System.out.println("Item stt--");
    }//GEN-LAST:event_jComboBox_NymsItemStateChanged

    private void jTable8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseEntered
}//GEN-LAST:event_jTable8MouseEntered

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked

        System.out.println("Count:" + evt.getClickCount());

        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            System.out.println("Right Click");
            int r = jTable8.rowAtPoint(evt.getPoint());
            if (r >= 0 && r < jTable8.getRowCount()) {
                jTable8.setRowSelectionInterval(r, r);
            } else {
                jTable8.clearSelection();
            }

            if (jTable8.getSelectedRow() > -1) {
                JPopupMenu popupMenu = new JPopupMenu();
                PaymentInboxRightClickListener menuListener = new PaymentInboxRightClickListener();
                JMenuItem menuItem = new JMenuItem("Test");
                popupMenu.add(menuItem);
                menuItem.addActionListener(menuListener);

                menuItem = new JMenuItem("Test1");

                popupMenu.add(menuItem);

                menuItem.addActionListener(menuListener);

                popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());

            }
        }

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

    private void jTabbedPane_NymboxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane_NymboxStateChanged
        JTabbedPane pane = (JTabbedPane) evt.getSource();
        int sel = pane.getSelectedIndex();
        System.out.println("State changed:" + sel);
        if (sel == 0) {
            jButton_DownloadMail.setVisible(true);
            repaint();
        } else {
            jButton_DownloadMail.setVisible(false);
            repaint();
        }
    }//GEN-LAST:event_jTabbedPane_NymboxStateChanged

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

    private void jButton_ImportNymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ImportNymActionPerformed
        new ImportNYMDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jButton_ImportNymActionPerformed

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

    private void jButton_RegisterNymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegisterNymActionPerformed

        if (jTable_NymsList.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select NYM to register", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new RegisterNYMDialog(this, true, (String) jTable_NymsList.getModel().getValueAt(jTable_NymsList.getSelectedRow(), 1)).setVisible(true);

    }//GEN-LAST:event_jButton_RegisterNymActionPerformed

    private void jTabbedPane_MainPageStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane_MainPageStateChanged

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
        if (sel == 4 && !isMarketInit) {
            initMarketsTab();
            isMarketInit = true;
        }
        if (sel == 5 && !isPaymentsInit) {
            initPaymentsInitTab();
            isPaymentsInit = true;
        }


    }//GEN-LAST:event_jTabbedPane_MainPageStateChanged

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
            String assetID = (String) jTable19.getModel().getValueAt(jTable19.getSelectedRow(), 1);
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
                if (assetID != null) {
                    for (int i = 0; i < jTable19.getRowCount(); i++) {
                        String id = (String) jTable19.getModel().getValueAt(i, 1);
                        if (id == null) {
                            continue;
                        }
                        if (assetID.equals(id)) {
                            jTable19.setRowSelectionInterval(i, i);
                            break;
                        }
                    }
                }
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

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_jTable6MouseClicked

    private void jTable20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable20MouseClicked


        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            System.out.println("Right Click");
            int r = jTable20.rowAtPoint(evt.getPoint());
            if (r >= 0 && r < jTable20.getRowCount()) {
                jTable20.setRowSelectionInterval(r, r);
            } else {
                jTable20.clearSelection();
            }

            if (jTable20.getSelectedRow() > -1) {
                JPopupMenu popupMenu = new JPopupMenu();
                PaymentInboxRightClickListener menuListener = new PaymentInboxRightClickListener();
                JMenuItem menuItem = new JMenuItem("Deposit");
                popupMenu.add(menuItem);
                menuItem.addActionListener(menuListener);

                menuItem = new JMenuItem("Reject");

                popupMenu.add(menuItem);

                menuItem.addActionListener(menuListener);

                popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());

            }
        }


}//GEN-LAST:event_jTable20MouseClicked

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
}//GEN-LAST:event_jButton34ActionPerformed

    private void jTable21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable21MouseClicked
        System.out.println("Count:" + evt.getClickCount());
        if (evt.getClickCount() == 2) {
            String key = (String) jTable21.getModel().getValueAt(jTable21.getSelectedRow(), 4);
            String subject = (String) jTable21.getModel().getValueAt(jTable21.getSelectedRow(), 0);
            System.out.println("In paymentOutBox double clcik, key:" + key);
            String status = (String) jTable21.getModel().getValueAt(jTable21.getSelectedRow(), 8);
            String content = (String) jTable21.getModel().getValueAt(jTable21.getSelectedRow(), 7);

            PaymentOutboxDetailsDialog payOutboxDialog = new PaymentOutboxDetailsDialog(this, true, status == "true" ? "Verified" : "Not Verified", content, subject);
            payOutboxDialog.setVisible(true);


        }
}//GEN-LAST:event_jTable21MouseClicked

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        if (jTable6.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Please select nym", "Nym selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new ServerAcctDialog(this, true, (String) jTable6.getModel().getValueAt(jTable6.getSelectedRow(), 1), "Send Invoice").setVisible(true);

    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        if (jTable6.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Please select nym", "Nym selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new ProposePaymentDialog(this, true, (String) jTable6.getModel().getValueAt(jTable6.getSelectedRow(), 1)).setVisible(true);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        if (jTable6.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Please select nym", "Nym selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (jComboBox8.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please select Send Funds", "Nym selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new ServerAcctDialog(this, true, (String) jTable6.getModel().getValueAt(jTable6.getSelectedRow(), 1), (String) jComboBox8.getSelectedItem()).setVisible(true);
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jButton_AddAccount2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddAccount2ActionPerformed
        new ContractAdditionDialog(this, true, "Asset").setVisible(true);
        refreshAssetContractList();
    }//GEN-LAST:event_jButton_AddAccount2ActionPerformed

    private void jButton_EditAccount2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditAccount2ActionPerformed
        if (jTable_AssetList.getSelectedRow() > -1) {
            new AssetContractEditDialog(this, true, (String) jTable_AssetList.getModel().getValueAt(jTable_AssetList.getSelectedRow(), 1)).setVisible(true);
            refreshAssetContractList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an Asset Contract to edit.", "Asset Contract Edit Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_EditAccount2ActionPerformed

    private void jButton_RemoveAccount2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveAccount2ActionPerformed
        if (jTable_AssetList.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select Asset Contract to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userSelection = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Asset Contract", "Asset Contract Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        System.out.println("userSelection:" + userSelection);
        Contract contract = new Contract();
        if (userSelection == 0) {
            boolean status = contract.deleteAssetContract((String) jTable_AssetList.getModel().getValueAt(jTable_AssetList.getSelectedRow(), 1));
            if (status) {
                JOptionPane.showMessageDialog(this, "Asset Contract deleted successfully", "Asset Contract Deletion", JOptionPane.INFORMATION_MESSAGE);
                refreshAssetContractList();
            } else {
                JOptionPane.showMessageDialog(this, "Asset Contract cannot be deleted", "Asset Contract Deletion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_RemoveAccount2ActionPerformed

    private void jButton_AddAccount3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddAccount3ActionPerformed
        new ContractAdditionDialog(this, true, "Server").setVisible(true);
        refreshServerContractList();
    }//GEN-LAST:event_jButton_AddAccount3ActionPerformed

    private void jButton_EditAccount3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditAccount3ActionPerformed
        if (jTable_ServerList.getSelectedRow() > -1) {
            new ServerContractEditDialog(this, true, (String) jTable_ServerList.getModel().getValueAt(jTable_ServerList.getSelectedRow(), 1)).setVisible(true);
            refreshServerContractList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a Server Contract to edit.", "Server Contract Edit Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_EditAccount3ActionPerformed

    private void jButton_RemoveAccount3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveAccount3ActionPerformed
        if (jTable_ServerList.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select Server Contract to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userSelection = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Server Contract", "Server Contract Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        System.out.println("userSelection:" + userSelection);
        Contract contract = new Contract();
        if (userSelection == 0) {
            boolean status = contract.deleteServerContract((String) jTable_ServerList.getModel().getValueAt(jTable_ServerList.getSelectedRow(), 1));
            if (status) {
                JOptionPane.showMessageDialog(this, "Server Contract deleted successfully", "Server Contract Deletion", JOptionPane.INFORMATION_MESSAGE);
                refreshServerContractList();
            } else {
                JOptionPane.showMessageDialog(this, "Server Contract cannot be deleted", "Server Contract Deletion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_RemoveAccount3ActionPerformed

    private void jButton_AddAccount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddAccount1ActionPerformed
        new NYMAdditionDialog(this, true).setVisible(true);
        refreshNYMSList();
    }//GEN-LAST:event_jButton_AddAccount1ActionPerformed

    private void jButton_EditAccount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditAccount1ActionPerformed
        if (jTable_NymsList.getSelectedRow() > -1) {
            new NYMEditDialog(this, true, (String) jTable_NymsList.getModel().getValueAt(jTable_NymsList.getSelectedRow(), 1)).setVisible(true);
            refreshNYMSList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a NYM to edit.", "NYM Edit Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_EditAccount1ActionPerformed

    private void jButton_RemoveAccount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveAccount1ActionPerformed
        if (jTable_NymsList.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select NYM to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nymID = (String) jTable_NymsList.getModel().getValueAt(jTable_NymsList.getSelectedRow(), 1);

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
    }//GEN-LAST:event_jButton_RemoveAccount1ActionPerformed

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
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton_AddAccount;
    private javax.swing.JButton jButton_AddAccount1;
    private javax.swing.JButton jButton_AddAccount2;
    private javax.swing.JButton jButton_AddAccount3;
    private javax.swing.JButton jButton_AssetIssue;
    private javax.swing.JButton jButton_Compose;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_DownloadMail;
    private javax.swing.JButton jButton_EditAccount;
    private javax.swing.JButton jButton_EditAccount1;
    private javax.swing.JButton jButton_EditAccount2;
    private javax.swing.JButton jButton_EditAccount3;
    private javax.swing.JButton jButton_ImportNym;
    private javax.swing.JButton jButton_RegisterNym;
    private javax.swing.JButton jButton_RemoveAccount;
    private javax.swing.JButton jButton_RemoveAccount1;
    private javax.swing.JButton jButton_RemoveAccount2;
    private javax.swing.JButton jButton_RemoveAccount3;
    private static javax.swing.JComboBox jComboBox4;
    private static javax.swing.JComboBox jComboBox5;
    private static javax.swing.JComboBox jComboBox6;
    private static javax.swing.JComboBox jComboBox7;
    private static javax.swing.JComboBox jComboBox8;
    private static javax.swing.JComboBox jComboBoxServerContracts;
    private static javax.swing.JComboBox jComboBox_AssetContracts;
    private static javax.swing.JComboBox jComboBox_Nyms;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
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
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private static javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel_AssetContracts;
    private javax.swing.JLabel jLabel_AssetID;
    private javax.swing.JLabel jLabel_AssetMisc;
    private javax.swing.JLabel jLabel_FilterAccounts;
    private javax.swing.JLabel jLabel_NymID;
    private javax.swing.JLabel jLabel_NymMisc;
    private javax.swing.JLabel jLabel_Nyms;
    private javax.swing.JLabel jLabel_ServerContracts;
    private javax.swing.JLabel jLabel_ServerID;
    private javax.swing.JLabel jLabel_ServerMisc;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private static javax.swing.JPanel jPanel27;
    private static javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel_Accounts;
    private javax.swing.JPanel jPanel_Agreements;
    private javax.swing.JPanel jPanel_AssetInfo;
    private javax.swing.JPanel jPanel_AssetList;
    private javax.swing.JPanel jPanel_Baskets;
    private static javax.swing.JPanel jPanel_BottomPanel;
    private javax.swing.JPanel jPanel_Contracts;
    private javax.swing.JPanel jPanel_Credits;
    private javax.swing.JPanel jPanel_Deed;
    private javax.swing.JPanel jPanel_ListActions;
    private javax.swing.JPanel jPanel_ListActions1;
    private javax.swing.JPanel jPanel_ListActions2;
    private javax.swing.JPanel jPanel_ListActions3;
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JPanel jPanel_Markets;
    private javax.swing.JPanel jPanel_NmysList;
    private javax.swing.JPanel jPanel_NymInfo;
    private javax.swing.JPanel jPanel_Nymbox;
    private javax.swing.JPanel jPanel_Nyms;
    private javax.swing.JPanel jPanel_Other;
    private static javax.swing.JPanel jPanel_PaymentTop;
    private javax.swing.JPanel jPanel_Payments;
    private javax.swing.JPanel jPanel_ServerInfo;
    private javax.swing.JPanel jPanel_ServerList;
    private javax.swing.JPanel jPanel_Settings;
    private static javax.swing.JPanel jPanel_TopPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane_Accounts;
    private javax.swing.JScrollPane jScrollPane_AssetList;
    private javax.swing.JScrollPane jScrollPane_AssetMisc;
    private javax.swing.JScrollPane jScrollPane_NymMisc;
    private javax.swing.JScrollPane jScrollPane_NymsList;
    private javax.swing.JScrollPane jScrollPane_ServerList;
    private javax.swing.JScrollPane jScrollPane_ServerMisc;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane_MainPage;
    private javax.swing.JTabbedPane jTabbedPane_Nymbox;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable12;
    private static javax.swing.JTable jTable13;
    private static javax.swing.JTable jTable14;
    private static javax.swing.JTable jTable15;
    private static javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private static javax.swing.JTable jTable18;
    private static javax.swing.JTable jTable19;
    private static javax.swing.JTable jTable20;
    private static javax.swing.JTable jTable21;
    private static javax.swing.JTable jTable22;
    private static javax.swing.JTable jTable3;
    private static javax.swing.JTable jTable4;
    private static javax.swing.JTable jTable6;
    private static javax.swing.JTable jTable8;
    private static javax.swing.JTable jTable_AccountTable;
    private static javax.swing.JTable jTable_AssetList;
    private static javax.swing.JTable jTable_NymsList;
    private static javax.swing.JTable jTable_ServerList;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea_AssetMisc;
    private javax.swing.JTextArea jTextArea_NymMisc;
    private javax.swing.JTextArea jTextArea_ServerMisc;
    private static javax.swing.JTextField jTextField1;
    private static javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField5;
    private static javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextField_AssetID;
    private javax.swing.JTextField jTextField_NymID;
    private javax.swing.JTextField jTextField_ServerID;
    // End of variables declaration//GEN-END:variables
    private static Map nymMap;
    private static Map nymRegisteredMap;
    private static Map assetMap;
    private static Map serverMap;

    public static void setNymOutbox(Map nymOutboxData) {
        nymOutBox = nymOutboxData;
    }

    private void initMainTab() {


        jPanel_TopPanel.setLayout(new CardLayout());
        jPanel_BottomPanel.setLayout(new CardLayout());

        jTable_AccountTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("valueChanged Action Listener :" + jTable_AccountTable.getSelectedRow() + "e:" + e.getSource());
                if (jTable_AccountTable.getSelectedRow() >= 0) {
                    try {
                        jPanel_TopPanel.setVisible(true);
                        jPanel_BottomPanel.setVisible(true);

                        CardLayout topLayout = (CardLayout) (jPanel_TopPanel.getLayout());
                        CardLayout bottomlayout = (CardLayout) (jPanel_BottomPanel.getLayout());

                        String type = null;
                        String accountID = null;

                        type = (String) jTable_AccountTable.getModel().getValueAt(jTable_AccountTable.getSelectedRow(), 2);
                        accountID = (String) jTable_AccountTable.getModel().getValueAt(jTable_AccountTable.getSelectedRow(), 3);

                        System.out.println("Type:" + type);

                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                        for (int i = 0; i < Account.allAccounts.length; i++) {

                            if (Account.allAccounts[i].equalsIgnoreCase(type)) {
                                try {
                                    topLayout.show(jPanel_TopPanel, type + "TopPanel");
                                    bottomlayout.show(jPanel_BottomPanel, type + "BottomPanel");
                                    Class obj = Class.forName("com.moneychanger.core." + type);


                                    Account account = (Account) obj.newInstance();
                                    Object details = account.getAccountDetails(accountID);
                                    if (details == null) {
                                        JOptionPane.showMessageDialog(null, "Error loading details", "Details Error", JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                    if ("OpenTransactionAccount".equalsIgnoreCase(type)) {
                                        OTDetails otDetails = (OTDetails) details;
                                        Utility.populateOTDetails(otDetails);
                                        ((AccountTableModel) jTable_AccountTable.getModel()).setValueAt(otDetails.getBalance(), jTable_AccountTable.getSelectedRow(), 1);
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

        jComboBox_Nyms.addItem(new ComboObject("ALL"));
        jComboBoxServerContracts.addItem(new ComboObject("ALL"));
        jComboBox_AssetContracts.addItem(new ComboObject("ALL"));

        Utility.populateCombo(nymMap, jComboBox_Nyms);

        Contract contract = new Contract();

        serverMap = contract.loadServerContract();
        Utility.populateCombo(serverMap, jComboBoxServerContracts);

        assetMap = contract.loadAssetContract();
        Utility.populateCombo(assetMap, jComboBox_AssetContracts);


        Account account = null;
        for (int i = 0; i < Account.allAccounts.length; i++) {
            try {

                if ("OpenTransactionAccount".equals(Account.allAccounts[i]) || "CashPurseAccount".equals(Account.allAccounts[i])) {

                    Class obj = Class.forName("com.moneychanger.core." + Account.allAccounts[i]);
                    account = (Account) obj.newInstance();
                    try {
                        account.loadAccount("ALL", "ALL", "ALL");
                    } catch (Exception ex) {
                        Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Class obj1 = Class.forName("com.moneychanger.ui.panels." + Account.allAccounts[i] + "TopPanel");
                    JPanel topPanel = (JPanel) obj1.newInstance();
                    jPanel_TopPanel.add(topPanel, Account.allAccounts[i] + "TopPanel");

                    Class obj2 = Class.forName("com.moneychanger.ui.panels." + Account.allAccounts[i] + "BottomPanel");
                    JPanel bottomPanel = (JPanel) obj2.newInstance();

                    jPanel_BottomPanel.add(bottomPanel, Account.allAccounts[i] + "BottomPanel");
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
                                    account = (Account) (Class.forName("com.moneychanger.core." + type)).newInstance();
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
                                        Class obj = Class.forName("com.moneychanger.core." + type);

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

                        Class obj1 = Class.forName("com.moneychanger.ui.panels." + Account.allAccounts[i] + "TopPanel");
                        JPanel topPanel = (JPanel) obj1.newInstance();
                        jPanel27.add(topPanel, Account.allAccounts[i] + "TopPanel");

                        Class obj2 = Class.forName("com.moneychanger.ui.panels." + Account.allAccounts[i] + "BottomPanel");
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
        String selectedAccount = (String) jTable_AccountTable.getModel().getValueAt(jTable_AccountTable.getSelectedRow(), 3);
        if (nymMap != null && nymMap.size() > 0 && jComboBox_Nyms.getSelectedIndex() > 0) {
            nymID = ((String[]) nymMap.get((Integer) jComboBox_Nyms.getSelectedIndex() - 1))[1];
        }

        if (assetMap != null && assetMap.size() > 0 && jComboBox_AssetContracts.getSelectedIndex() > 0) {
            assetID = ((String[]) assetMap.get((Integer) jComboBox_AssetContracts.getSelectedIndex() - 1))[1];
        }

        if (serverMap != null && serverMap.size() > 0 && jComboBoxServerContracts.getSelectedIndex() > 0) {
            serverID = ((String[]) serverMap.get((Integer) jComboBoxServerContracts.getSelectedIndex() - 1))[1];
        }

        System.out.print("reLoadAccount , nymID :" + nymID + " assetID:" + assetID + " serverID:" + serverID);

        loadAccount(assetID, serverID, nymID);
        boolean isAccountPresent = false;
        for (int i = 0; i < jTable_AccountTable.getRowCount(); i++) {
            String accountID = (String) jTable_AccountTable.getModel().getValueAt(i, 3);
            if (selectedAccount != null && selectedAccount.equals(accountID)) {
                isAccountPresent = true;
                jTable_AccountTable.setRowSelectionInterval(i, i);
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
                    Class obj = Class.forName("com.moneychanger.core." + Account.allAccounts[i]);

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

        ((AccountTableModel) jTable_AccountTable.getModel()).setValue(account.getOTAccountList(), jTable_AccountTable);

    }

    public static void loadAccount(String assetID, String serverID, String nymID, String selectedID) {
        Account account = null;

        for (int i = 0; i < Account.allAccounts.length; i++) {
            try {
                if ("OpenTransactionAccount".equals(Account.allAccounts[i]) || "CashPurseAccount".equals(Account.allAccounts[i])) {
                    Class obj = Class.forName("com.moneychanger.core." + Account.allAccounts[i]);

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

        ((AccountTableModel) jTable_AccountTable.getModel()).setValue(account.getOTAccountList(), jTable_AccountTable);


        for (int i = 0; i < jTable_AccountTable.getModel().getRowCount(); i++) {
            System.out.println("selectedID:" + selectedID + "jTable5.getModel().getValueAt(i, 3" + (String) jTable_AccountTable.getModel().getValueAt(i, 3));
            if (selectedID != null && jTable_AccountTable.getModel().getValueAt(i, 3) != null && selectedID.equals(jTable_AccountTable.getModel().getValueAt(i, 3))) {
                jTable_AccountTable.setRowSelectionInterval(i, i);
                break;
            }

        }

    }

    public static void loadOtherTabAccount(String serverID) {
        Account account = null;
        for (int i = 0; i < Account.allAccounts.length; i++) {
            try {
                if (!"OpenTransactionAccount".equals(Account.allAccounts[i]) && !"CashPurseAccount".equals(Account.allAccounts[i])) {
                    Class obj = Class.forName("com.moneychanger.core." + Account.allAccounts[i]);

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

        jTable_NymsList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("selectedRow:" + jTable_NymsList.getSelectedRow());
                if (jTable_NymsList.getSelectedRow() >= 0) {
                    String nymID = (String) jTable_NymsList.getModel().getValueAt(jTable_NymsList.getSelectedRow(), 1);
                    System.out.println("selected nymID:" + nymID);
                    NYM nym = new NYM();
                    nymBox = nym.loadNymBox(nymID);
                    nymOutBox = nym.loadNymOutBox(nymID);
                    System.out.println("loadNymBox loadNymOutBox data loaded");
                    populateNYMDetails(nymID, nym.getRawFileData(nymID), nymBox, nymOutBox);
                }
            }
        });
        ((NYMTableModel) jTable_NymsList.getModel()).setValue(new NYM().loadNYM(), jTable_NymsList);
    }

    private void initContractsTab() {
        Contract contract = new Contract();
        jTable_AssetList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("valueChanged:" + jTable_AssetList.getSelectedRow() + "e:" + e.getSource());
                if (jTable_AssetList.getSelectedRow() >= 0) {
                    String assetID = (String) jTable_AssetList.getModel().getValueAt(jTable_AssetList.getSelectedRow(), 1);
                    populateAssetContractDetails(assetID, new Contract().getRawAssetFileData(assetID));
                }
            }
        });
        jTable_ServerList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("valueChanged:" + jTable_ServerList.getSelectedRow() + "e:" + e.getSource());
                if (jTable_ServerList.getSelectedRow() >= 0) {
                    String serverID = (String) jTable_ServerList.getModel().getValueAt(jTable_ServerList.getSelectedRow(), 1);
                    populateServerContractDetails(serverID, new Contract().getRawServerFileData(serverID));
                }
            }
        });
        ((AssetContractTableModel) jTable_AssetList.getModel()).setValue(contract.loadAssetContract(), jTable_AssetList);
        ((ServerContractTableModel) jTable_ServerList.getModel()).setValue(contract.loadServerContract(), jTable_ServerList);
    }

    private void populateNYMDetails(String id, String rawData, Map nymBox, Map nymOutBox) {
        System.out.println("populateNYMDetails nymID:" + id + " rawData:" + rawData);
        jTextField_NymID.setText(id);
        jTextArea_NymMisc.setText(rawData);
        jTextArea_NymMisc.setCaretPosition(0);
        System.out.println("populateNYMDetails before setting grids,nymBox:" + nymBox.entrySet() + "----nymOutBox:" + nymOutBox.entrySet());
        ((NYMBoxTableModel) jTable8.getModel()).setValue(nymBox, jTable8);
        ((NYMOutboxTableModel) jTable10.getModel()).setValue(nymOutBox, jTable10);
        System.out.println("populateNYMDetails Ends");
    }

    private void populateAssetContractDetails(String id, String rawData) {
        jTextField_AssetID.setText(id);
        jTextArea_AssetMisc.setText(rawData);
        jTextArea_AssetMisc.setCaretPosition(0);
    }

    private void populateServerContractDetails(String id, String rawData) {
        jTextField_ServerID.setText(id);
        jTextArea_ServerMisc.setText(rawData);
        jTextArea_ServerMisc.setCaretPosition(0);
    }

    public static void refreshNYMSList() {

        System.out.println("IN refreshNYMSList");

        ((NYMTableModel) jTable_NymsList.getModel()).setValue(new NYM().loadNYM(), jTable_NymsList);
        nymMap = new NYM().loadNYM();

        String serverID = "ALL";

        if (serverMap != null && serverMap.size() > 0 && jComboBox5.getSelectedIndex() > -1) {
            serverID = ((String[]) serverMap.get((Integer) jComboBox5.getSelectedIndex()))[1];
        }

        nymRegisteredMap = new NYM().loadRegisteredNYM(serverID);

        Utility.populateCombo(nymMap, jComboBox_Nyms);
        Utility.populateComboWithoutAll(nymRegisteredMap, jComboBox6);
    }

    private static void refreshAssetContractList() {

        Contract contract = new Contract();
        ((AssetContractTableModel) jTable_AssetList.getModel()).setValue(contract.loadAssetContract(), jTable_AssetList);
        assetMap.clear();
        assetMap = contract.loadAssetContract();
        Utility.populateCombo(assetMap, jComboBox_AssetContracts);

    }

    private void refreshServerContractList() {
        Contract contract = new Contract();
        ((ServerContractTableModel) jTable_ServerList.getModel()).setValue(contract.loadServerContract(), jTable_ServerList);
        serverMap.clear();
        serverMap = contract.loadServerContract();
        System.out.println("serverv" + serverMap.entrySet());
        Utility.populateCombo(serverMap, jComboBoxServerContracts);
        Utility.populateComboWithoutAll(serverMap, jComboBox5);

    }

    public static JTable getAccountTable() {
        return jTable_AccountTable;
    }

    public static void setAssets(String serverID, String assetID) {

        List baskets = Basket.getBasketList(serverID, null);

        if (baskets != null && baskets.size() > 0 && !"Popup Dialog".equalsIgnoreCase(((String[]) baskets.get(0))[0])) {
            ((BasketTableModel) jTable19.getModel()).setValue(baskets);
        }
        refreshAssetContractList();
        if (assetID != null) {
            for (int i = 0; i < jTable19.getRowCount(); i++) {
                String id = (String) jTable19.getModel().getValueAt(i, 1);
                if (id == null) {
                    continue;
                }
                if (assetID.equals(id)) {
                    jTable19.setRowSelectionInterval(i, i);
                    break;
                }
            }
        }

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
        } else if (baskets != null && !baskets.isEmpty()) {

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
            jTextField1.setText("1");
            Basket.resetSubCurrency();
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

    private void initPaymentsInitTab() {

        PaymentInboxTableModel.removeCols(jTable20);
        PaymentOutboxTableModel.removeCols(jTable21);

        PaymentRecordboxTableModel.removeCols(jTable22);

        setCustomWidthPayments();
        NYMTableModel.removeCols(jTable6);

        ((NYMTableModel) jTable6.getModel()).setValue(new NYM().loadNYM(), jTable6);

        jTable6.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting()) {
                    return;
                }
                System.out.println("jTable6 valueChanged Payment List Action Listener :" + jTable6.getSelectedRow() + "e:" + e.getSource());
                try {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    paymentClick();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    setCursor(Cursor.getDefaultCursor());

                }
            }
        });

    }

    private void paymentClick() throws InterruptedException {

        if (jTable6.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select NYM", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nymID = (String) jTable6.getModel().getValueAt(jTable6.getSelectedRow(), 1);

        Map inbox = Payments.getPaymentInboxData(nymID);
        Map outbox = null;
        if (inbox != null) {
            if (inbox.get("Popup") != null) {
                RegisterNYMDialog registerNYM = new RegisterNYMDialog(this, true, nymID);
                registerNYM.setVisible(true);
                String serverID = registerNYM.getPaymentServerID();
                System.out.println("PaymentClick - after reg nym, serverID:" + serverID);
                if (serverID != null) {
                    inbox = Payments.getPaymentInboxRecords(nymID, serverID);
                }
            } else if (inbox.get("serverList") != null) {
                ServerSelectionDialog selectServer = new ServerSelectionDialog(this, true, (Map) inbox.get("serverList"));
                selectServer.setVisible(true);
                String serverID = selectServer.getPaymentServerID();
                System.out.println("PaymentClick - after selecting server, serverID:" + serverID);
                if (serverID != null) {
                    inbox = Payments.getPaymentInboxRecords(nymID, serverID);
                    outbox = Payments.getPaymentOutboxRecords(nymID, serverID);

                    ((PaymentInboxTableModel) jTable20.getModel()).setValue(inbox, jTable20);
                    ((PaymentOutboxTableModel) jTable21.getModel()).setValue(outbox, jTable21);
                    ((PaymentRecordboxTableModel) jTable22.getModel()).setValue(inbox, jTable22);
                }
            }


        }

    }

    private void setCustomWidthPayments() {

        int width = 120;

        TableColumn col = jTable20.getColumnModel().getColumn(4);

        col.setPreferredWidth(width);

        col = jTable20.getColumnModel().getColumn(5);
        col.setPreferredWidth(width);

        col = jTable20.getColumnModel().getColumn(6);
        col.setPreferredWidth(180);


        col = jTable22.getColumnModel().getColumn(3);
        col.setPreferredWidth(width);

        col = jTable22.getColumnModel().getColumn(5);
        col.setPreferredWidth(width);

    }
}
