// <editor-fold defaultstate="collapsed" desc="Hedders" > 
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
// </editor-fold>

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrapper.core.util;

// <editor-fold defaultstate="collapsed" desc="Imports" > 
import com.wrapper.core.Account;
import com.wrapper.core.OpenTransactionAccount;
import com.wrapper.core.dataobjects.OTDetails;
import com.wrapper.core.jni.AddressBook;
import com.wrapper.core.jni.MarketList;
import com.wrapper.core.jni.OTCallback;
import com.wrapper.core.jni.OTCaller;
import com.wrapper.core.jni.OfferListMarket;
import com.wrapper.core.jni.OfferListNym;
import com.wrapper.core.jni.Storable;
import com.wrapper.core.jni.StoredObjectType;
import com.wrapper.core.jni.TradeListMarket;
import com.wrapper.core.jni.TradeListNym;
import com.wrapper.core.jni.WalletData;
import com.wrapper.core.jni.otapi;
import com.wrapper.ui.Load;
import com.wrapper.ui.MainPage;
import com.wrapper.ui.model.AccountTableModel;
import com.wrapper.ui.panels.OpenTransactionAccountBottomPanel;
import com.wrapper.ui.panels.OpenTransactionAccountTopPanel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
    // </editor-fold>

/**
 *
 * @author Vicky C and Cameron
 */
public class Utility {
    
    // Common Helpers
    // <editor-fold defaultstate="collapsed" desc="Settings" > 
    private static Object settingsObj;
    public static Object getSettingsObj() {
        return settingsObj;
    }
    public static void setSettingsObj(Object settingsObj) {
        Utility.settingsObj = settingsObj;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Data Folder" > 
    private static String dataFolder;
    public static String getDataFolder() {
        return Utility.dataFolder;
    }
    public static void setDataFolder(String dataFolder) {
        Utility.dataFolder = dataFolder;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Add Directory To Runtime" > 
    public static void addDirToRuntime(Load.JavaPaths javaPaths) throws IOException {
        List<String> pathsSet = new ArrayList<String>();
        StringBuilder pathsString = new StringBuilder();

        try {
            Field field = ClassLoader.class.getDeclaredField("usr_paths");
            field.setAccessible(true);

            pathsSet.addAll(Arrays.asList((String[]) field.get(null)));
            pathsSet.addAll(javaPaths.getPaths());

            replaceToLower(pathsSet);

            Collection<String> paths = new HashSet<String>(pathsSet);

            field.set(null, paths.toArray(new String[0]));

            for (String path : paths) {
                pathsString.append(path);
                pathsString.append(File.pathSeparator);
            }

            System.setProperty("java.library.path", pathsString.toString());
        } catch (IllegalAccessException e) {
            throw new IOException("Failed to get permissions to set library path");
        } catch (NoSuchFieldException e) {
            throw new IOException("Failed to get field handle to set library path");
        }

    }
// <editor-fold defaultstate="collapsed" desc="//Old Code" > 
//    public static void addDirToRuntime(String s, boolean mutiple) throws IOException {
//        try {
//            String[] path = null;
//            if (s.contains(";")) {
//                path = s.split(";");
//            } else {
//                return;
//            }
//            for (int j = 0; j < path.length; j++) {
//                Field field = ClassLoader.class.getDeclaredField("usr_paths");
//                field.setAccessible(true);
//                String[] paths = (String[]) field.get(null);
//                for (int i = 0; i < paths.length; i++) {
//                    if (path[j].equals(paths[i])) {
//                        return;
//                    }
//                }
//                String[] tmp = new String[paths.length + 1];
//                System.arraycopy(paths, 0, tmp, 0, paths.length);
//                tmp[paths.length] = path[j];
//                field.set(null, tmp);
//                System.setProperty("java.library.path", path[j] + File.pathSeparator + System.getProperty("java.library.path"));
//            }
//        } catch (IllegalAccessException e) {
//            throw new IOException("Failed to get permissions to set library path");
//        } catch (NoSuchFieldException e) {
//            throw new IOException("Failed to get field handle to set library path");
//        }
//    }
//
//    public static void addDirToRuntime(String s) throws IOException {
//        try {
//
//            Field field = ClassLoader.class.getDeclaredField("usr_paths");
//            field.setAccessible(true);
//            String[] paths = (String[]) field.get(null);
//            for (int i = 0; i < paths.length; i++) {
//                if (s.equals(paths[i])) {
//                    return;
//                }
//            }
//            String[] tmp = new String[paths.length + 1];
//            System.arraycopy(paths, 0, tmp, 0, paths.length);
//            tmp[paths.length] = s;
//            field.set(null, tmp);
//            System.setProperty("java.library.path", s + File.pathSeparator + System.getProperty("java.library.path"));
//        } catch (IllegalAccessException e) {
//            throw new IOException("Failed to get permissions to set library path");
//        } catch (NoSuchFieldException e) {
//            throw new IOException("Failed to get field handle to set library path");
//        }
//    }
// </editor-fold>
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Oject Utility" >
    public static Object obj;
    public static Object getObj() {
        return obj;
    }
    public static void setObj(Object obj) {
        Utility.obj = obj;
    }
    // </editor-fold>
    
    // OT Helpers
    // <editor-fold defaultstate="collapsed" desc="OT Detials" > 
    public static void reloadOTDetails(String accountID) {
        Account account = new OpenTransactionAccount();
        Object details = account.getAccountDetails(accountID);
        OTDetails otDetails = (OTDetails) details;
        populateOTDetails(otDetails);

        JTable table = MainPage.getAccountTable();
        ((AccountTableModel) table.getModel()).setValueAt(otDetails.getBalance(), table.getSelectedRow(), 1);
    }
    public static void populateOTDetails(OTDetails otDetails) {
        if (otDetails == null) {
            return;
        }
        OpenTransactionAccountTopPanel.populateOTDetails(otDetails);
        OpenTransactionAccountBottomPanel.populateOTDetails(otDetails);

        System.out.println("IN populateOTDetails");

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="BasketX" >
    private static String basketXAcct;
    private static boolean basketXCancelled;
    public static boolean isBasketXCancelled() {
        return basketXCancelled;
    }
    public static void setBasketXCancelled(boolean basketXCancelled) {
        Utility.basketXCancelled = basketXCancelled;
    }
    public static String getBasketXAcct() {
        return basketXAcct;
    }
    public static void setBasketXAcct(String basketXAcct) {
        Utility.basketXAcct = basketXAcct;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="NymTrades" > 
    private static boolean loadNymTrades = false;
    public static boolean isLoadNymTrades() {
        return loadNymTrades;
    }
    public static void setLoadNymTrades(boolean loadNymTrades) {
        Utility.loadNymTrades = loadNymTrades;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="The Callback" > 
    private static OTCallback g_theCallback;
    public static OTCallback getG_theCallback() {
        return g_theCallback;
    }
    public static void setG_theCallback(OTCallback g_theCallback) {
        Utility.g_theCallback = g_theCallback;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="The Caller" > 
    private static OTCaller g_theCaller;
    public static OTCaller getG_theCaller() {
        return g_theCaller;
    }
    public static void setG_theCaller(OTCaller g_theCaller) {
        Utility.g_theCaller = g_theCaller;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Deposit Cash" > 
    public static Object otDepositCash;
    public static Object getOtDepositCash() {
        return otDepositCash;
    }
    public static void setOtDepositCash(Object otDepositCash) {
        Utility.otDepositCash = otDepositCash;
    }
    // </editor-fold>
    
    // Get Helpers:
    // <editor-fold defaultstate="collapsed" desc="Get Key" > 
    public static String getKey(Map map, String value) {

        if ("All".equalsIgnoreCase(value)) {
            return "ALL";
        }

        if (value == null) {
            return null;
        }

        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            if (map.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Transaction Numbers" > 
    public static void getTransactionNumbers(String serverID, String nymID) {

        for (int i = 0; i < Configuration.getNbrTransactionCount(); i++) {
            otapi.OT_API_getTransactionNumber(serverID, nymID);
        }

        otapi.OT_API_getNymbox(serverID, nymID);

        try {
            Thread.sleep(Configuration.getWaitTime());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Credits File" > 
    public static String getCreditsFile(String fileName) {
        return otapi.QueryPlainString(fileName);
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Location" > 
    public static Point getLocation(Dimension componentDimension) {

        Point center = new Point(0, 0);
        Dimension toolkitDimension = Toolkit.getDefaultToolkit().getScreenSize();
        center.x = center.x + (toolkitDimension.width - componentDimension.width) / 2;
        center.y = center.y + (toolkitDimension.height - componentDimension.height) / 2;

        return center;
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Key From Name" > 
    public static void getKeyFromName(String name) {
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Wallet Data" > 
    public static WalletData getWalletData() {

        WalletData walletData = null;
        Storable storable = null;
        if (otapi.Exists("moneychanger", "gui_wallet.dat")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_WALLET_DATA, "moneychanger", "gui_wallet.dat");
            if (storable == null) {
                return null;
            }
            walletData = WalletData.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_WALLET_DATA);
            if (storable == null) {
                return null;
            }
            walletData = WalletData.ot_dynamic_cast(storable);
        }

        return walletData;
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Market List" > 
    public static MarketList getMarketList(String serverID) {

        MarketList marketList = null;
        Storable storable = null;
        if (otapi.Exists("markets", serverID, "market_data.bin")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_MARKET_LIST, "markets", serverID, "market_data.bin");
            if (storable == null) {
                return null;
            }
            marketList = MarketList.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_MARKET_LIST);
            if (storable == null) {
                return null;
            }
            marketList = MarketList.ot_dynamic_cast(storable);
        }

        return marketList;
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Market Offer" > 
    public static OfferListMarket getMarketOffer(String serverID, String marketID) {

        OfferListMarket offerListMarket = null;
        Storable storable = null;
        if (otapi.Exists("markets", serverID, "offers", marketID + ".bin")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_OFFER_LIST_MARKET, "markets", serverID, "offers", marketID + ".bin");
            if (storable == null) {
                return null;
            }
            offerListMarket = OfferListMarket.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_OFFER_LIST_MARKET);
            if (storable == null) {
                return null;
            }
            offerListMarket = OfferListMarket.ot_dynamic_cast(storable);
        }

        return offerListMarket;
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get NYM Trades" >
    public static TradeListNym getNYMTrades(String serverID, String nymID) {

        TradeListNym tradeListNym = null;
        Storable storable = null;
        if (otapi.Exists("nyms", "trades", serverID, nymID)) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_TRADE_LIST_NYM, "nyms", "trades", serverID, nymID);
            if (storable == null) {
                return null;
            }
            tradeListNym = TradeListNym.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_TRADE_LIST_NYM);
            if (storable == null) {
                return null;
            }
            tradeListNym = TradeListNym.ot_dynamic_cast(storable);
        }

        return tradeListNym;
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get NYM Offer" > 
    public static OfferListNym getNYMOffer(String serverID, String nymID) {

        OfferListNym offerListNym = null;
        Storable storable = null;
        if (otapi.Exists("nyms", serverID, "offers", nymID + ".bin")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_OFFER_LIST_NYM, "nyms", serverID, "offers", nymID + ".bin");
            if (storable == null) {
                return null;
            }
            offerListNym = OfferListNym.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_OFFER_LIST_NYM);
            if (storable == null) {
                return null;
            }
            offerListNym = OfferListNym.ot_dynamic_cast(storable);
        }

        return offerListNym;
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Market Trade List" > 
    public static TradeListMarket getMarketTradeList(String serverID, String marketID) {

        TradeListMarket tradeListMarket = null;
        Storable storable = null;
        if (otapi.Exists("markets", serverID, "recent", marketID + ".bin")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_TRADE_LIST_MARKET, "markets", serverID, "recent", marketID + ".bin");
            if (storable == null) {
                return null;
            }
            tradeListMarket = TradeListMarket.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_TRADE_LIST_MARKET);
            if (storable == null) {
                return null;
            }
            tradeListMarket = TradeListMarket.ot_dynamic_cast(storable);
        }

        return tradeListMarket;
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Address Book" > 
    public static AddressBook getAddressBook() {

        AddressBook addressBook = null;
        Storable storable = null;
        if (otapi.Exists("moneychanger", "gui_contacts.dat")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_ADDRESS_BOOK, "moneychanger", "gui_contacts.dat");
            if (storable == null) {
                return null;
            }
            addressBook = AddressBook.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_ADDRESS_BOOK);
            if (storable == null) {
                return null;
            }
            addressBook = AddressBook.ot_dynamic_cast(storable);
        }

        return addressBook;
    }
        // </editor-fold>
    
    // Generic Helpers
    // <editor-fold defaultstate="collapsed" desc="Defaut Look and Feel" >
    private static LookAndFeel defautLAF;
    public static LookAndFeel getDefautLAF() {
        return defautLAF;
    }
    public static void setDefautLAF(LookAndFeel defautLAF) {
        Utility.defautLAF = defautLAF;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="File to String" > 
    public static String fileToString(File file) {
        String fileText = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new java.io.BufferedInputStream(fis)));
            //read the stream
            int c = 0;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            fileText = sb.toString();

        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileText;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Replace To Lower" > 
    public static void replaceToLower(List<String> strings) {
        ListIterator<String> iterator = strings.listIterator();
        while (iterator.hasNext()) {
            iterator.set(iterator.next().toLowerCase());
        }
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Round Two Decimals" > 
    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Populate Combo" > 
    public static void populateCombo(Map data, JComboBox component) {

        int count = 1;

        while (component.getItemCount() > 1) {
            component.removeItemAt(count);

        }

        Set set = data.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            component.addItem(new ComboObject(((String[]) data.get(key))[0]));
        }

        if (component instanceof com.wrapper.ui.custom.SteppedComboBox) {

            Dimension d = component.getPreferredSize();
            component.setPreferredSize(new Dimension(100, d.height));
            ((com.wrapper.ui.custom.SteppedComboBox) component).setPopupWidth(d.width);
        }
    }
    public static void populateComboWithoutAll(Map data, JComboBox component) {

        while (component.getItemCount() > 0) {
            component.removeItemAt(0);

        }

        Set set = data.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            component.addItem(new ComboObject(((String[]) data.get(key))[0]));
        }

        if (component instanceof com.wrapper.ui.custom.SteppedComboBox) {

            Dimension d = component.getPreferredSize();
            component.setPreferredSize(new Dimension(100, d.height));
            ((com.wrapper.ui.custom.SteppedComboBox) component).setPopupWidth(d.width);
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Is Valid Double" > 
    public static boolean isValidDouble(String text) {
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return false;
        }
        return true;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Delay" > 
    public static void delay() throws InterruptedException {
        if (Configuration.getWaitTime() > 0) {
            Thread.sleep(Configuration.getWaitTime());
        }
        return;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Long Delay" > 
    public static void longDelay() throws InterruptedException {
        Thread.sleep(Configuration.getWaitTime() + 200);
        return;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Generate ID" > 
    public static String generateID() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
    public static void main(String a[]) {
        System.out.println(generateID());
    }
    // </editor-fold>
}