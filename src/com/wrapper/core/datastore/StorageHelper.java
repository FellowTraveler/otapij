/************************************************************
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA256

 *                 M O N E Y C H A N G E R
 *
 *   http://wiki.github.com/FellowTraveler/Moneychanger/wiki
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
 *  Copyright (C) 2010-2011 by "Fellow Traveler" (A pseudonym)
 *
 *  EMAIL:
 *  FellowTraveler@rayservers.net --- SEE PGP KEY BELOW.
 *  F3llowTraveler@gmail.com --- (not preferred.)
 *
 *  FINGERPRINT:
 *  9DD5 90EB 9292 4B48 0484  7910 0308 00ED F951 BB8E
 *
 *  BITCOIN:  1NtTPVVjDsUfDWybS4BwvHpG2pdS9RnYyQ
 *
 *  OFFICIAL PROJECT WIKI:
 *  http://wiki.github.com/FellowTraveler/Open-Transactions/wiki
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
wsFVAwUBTbFZUwMIAO35UbuOAQjDRBAAmIUJBi5/WC1KpI4TNAWdQNh6g59qYS6w
SI6mTMbnP0DUVOrmJdNR7/n1sRlnWzyjKLcKkRtXwRWGC+jE16jijxek9Ome5Qid
bDqjHSuFvqnsD3+0tbENf+kVrbAReU3YvWk+xFvVc6I2NpS+lEIdjHIWm85jSmew
Ydx+4KpELkO59thkcKgSYsTSyTP3l9GOTtJlq45XiamoEvso4jFUC1y5KMQsz1KH
DTE32m5FPZqJqUw9loAmrni3dIMpXKC5yLhdqSMXHK0MAPEIexsuaZjrjKJQSjwV
eDjwJcMn2WZVvcIr9IEoKEU/2j9wHNZv5Xuj78A/78AkjqEUwrY1M9ht0r/QbusW
ZT7MlxNCq4DFstrjyKi03yZQGR+m8eJFHE7GvF8Vzg/ap0/CUJzeoXg5wACXGfJj
k6y8ZBriQO08JECki2sy6oTitDoi7FmzgAIxPGB1qA4HMur/LuzrxAj2V7XkZQlk
VfAda6Ff9bmStNut+zbsQ0pnIeL/URwWifI8Wq81c7DEIvA5SH/bU9Hws1FMO8PU
BcDmzadU+syJBTxoP/mHZcLfwHDhcZyBeHX7sHfpHweEunzWjcHjqVCutQMO4dii
yrsc64WTfAqd4s12SfKMgVFLeL/FUYH7MNqpfgjgwX5co817m9VvCntU6njIuYtV
6+G/TuSViH8=
=/jIC
-----END PGP SIGNATURE-----
 **************************************************************/
package com.wrapper.core.datastore;

import com.wrapper.core.jni.AddressBook;
import com.wrapper.core.jni.BitcoinAcct;
import com.wrapper.core.jni.BitcoinServer;
import com.wrapper.core.jni.Contact;
import com.wrapper.core.jni.ContactAcct;
import com.wrapper.core.jni.ContactNym;
import com.wrapper.core.jni.RippleServer;
import com.wrapper.core.jni.ServerInfo;
import com.wrapper.core.jni.Storable;
import com.wrapper.core.jni.StoredObjectType;
import com.wrapper.core.jni.WalletData;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Utility;
import java.util.HashMap;
import java.util.Map;

public class StorageHelper {

    //Other Tab
    public static String addBitcoinServer(String label, String host, String user, String pwd, String port) {
        String serverID = Utility.generateID();
        WalletData walletData = Utility.getWalletData();

        if (walletData == null) {
            System.out.println("addBitcoinServer - walletData returns null");
            return null;
        }
        BitcoinServer btcServer = null;
        Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_BITCOIN_SERVER);
        if (storable != null) {
            btcServer = BitcoinServer.ot_dynamic_cast(storable);
            if (btcServer != null) {
                btcServer.setServer_host(host);
                btcServer.setBitcoin_password(pwd);
                btcServer.setBitcoin_username(user);
                btcServer.setServer_port(port);
                btcServer.setGui_label(label);
                btcServer.setServer_id(serverID);
                btcServer.setServer_type("Bitcoin");
                boolean status = walletData.AddBitcoinServer(btcServer);
                System.out.println("status walletData.AddBitcoinServer:" + status);
                status = otapi.StoreObject(walletData, "moneychanger", "gui_wallet.dat");
                System.out.println("status otapi.StoreObject:" + status);
                System.out.println("addBitcoinServer - serverID:" + serverID);
            }
        }
        return serverID;
    }
    //Ripple,Loom

    public static String addRippleServer(String label, String url, String user, String pwd, String userTextID, String pwdTextID) {
        String serverID = Utility.generateID();
        WalletData walletData = Utility.getWalletData();

        if (walletData == null) {
            System.out.println("addRippleServer - walletData returns null");
            return null;
        }
        RippleServer rippleServer = null;
        Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_RIPPLE_SERVER);
        if (storable != null) {
            rippleServer = RippleServer.ot_dynamic_cast(storable);
            if (rippleServer != null) {
                rippleServer.setServer_host(url);
                rippleServer.setRipple_password(pwd);
                rippleServer.setRipple_username(user);
                rippleServer.setNamefield_id(userTextID);
                rippleServer.setPassfield_id(pwdTextID);
                rippleServer.setGui_label(label);
                rippleServer.setServer_id(serverID);
                rippleServer.setServer_type("Ripple");
                boolean status = walletData.AddRippleServer(rippleServer);
                System.out.println("status walletData.AddRippleServer:" + status);
                status = otapi.StoreObject(walletData, "moneychanger", "gui_wallet.dat");
                System.out.println("status otapi.StoreObject:" + status);
                System.out.println("addRippleServer - serverID:" + serverID);
            }
        }
        return serverID;
    }

    public static Map getOtherTabServerList(String type) {

        WalletData walletData = Utility.getWalletData();
        HashMap dataMap = new HashMap();

        if (walletData == null) {
            System.out.println("getOtherTabServerList walletData returns null");
            return dataMap;
        }

        if ("Bitcoin".equalsIgnoreCase(type)) {
            getBitcoinServerList(walletData, dataMap);
        }

        if ("Ripple".equalsIgnoreCase(type)) {
            getRippleServerList(walletData, dataMap);
        }

        if ("ALL".equalsIgnoreCase(type)) {
            getBitcoinServerList(walletData, dataMap);
            getRippleServerList(walletData, dataMap);
        }

        return dataMap;
    }

    private static void getRippleServerList(WalletData walletData, HashMap dataMap) {

        long count = walletData.GetRippleServerCount();

        for (int i = 0; i < count; i++) {
            RippleServer rippleServer = walletData.GetRippleServer(i);
            if (rippleServer == null) {
                continue;
            }
            String[] data = new String[3];
            data[0] = rippleServer.getGui_label();
            data[1] = "RippleAccount";
            data[2] = rippleServer.getServer_id();
            dataMap.put(data[2], data);
        }
    }

    private static void getBitcoinServerList(WalletData walletData, HashMap dataMap) {

        long count = walletData.GetBitcoinServerCount();

        for (int i = 0; i < count; i++) {
            BitcoinServer btcServer = walletData.GetBitcoinServer(i);
            if (btcServer == null) {
                continue;
            }
            String[] data = new String[3];
            data[0] = btcServer.getGui_label();
            data[1] = "BitcoinAccount";
            data[2] = btcServer.getServer_id();
            dataMap.put(data[2], data);
        }
    }

    public static boolean editOtherTabServerLabel(String serverID, String newLabel, String type) {

        boolean status = false;
        WalletData walletData = Utility.getWalletData();
        if (walletData == null) {
            System.out.println("editOtherTabServerLabel - walletData returns null");
            return false;
        }

        System.out.println("TYPE:" + type);

        if ("BitcoinAccount".equalsIgnoreCase(type)) {
            status = editBitcoinServerLabel(walletData, serverID, newLabel);
        }
        if ("RippleAccount".equalsIgnoreCase(type)) {
            status = editRippleServerLabel(walletData, serverID, newLabel);
        }
        return status;

    }

    private static boolean editBitcoinServerLabel(WalletData walletData, String serverID, String newLabel) {
        boolean status = false;
        for (int i = 0; i < walletData.GetBitcoinServerCount(); i++) {
            BitcoinServer btcServer = walletData.GetBitcoinServer(i);
            if (btcServer == null) {
                continue;
            }
            if (serverID.equals(btcServer.getServer_id())) {
                btcServer.setGui_label(newLabel);
                status = otapi.StoreObject(walletData, "moneychanger", "gui_wallet.dat");
                System.out.println("editOtherTabServerLabel status otapi.StoreObject:" + status);
                break;
            }
        }
        return status;

    }

    public static boolean removeOtherTabServer(String serverID, String type) {

        boolean status = false;
        WalletData walletData = Utility.getWalletData();
        if (walletData == null) {
            System.out.println("removeOtherTabServer - walletData returns null");
            return false;
        }
        System.out.println("TYPE:" + type);
        if ("BitcoinAccount".equalsIgnoreCase(type)) {
            status = removeBitcoinServer(walletData, serverID);
        }

        if ("RippleAccount".equalsIgnoreCase(type)) {
            status = removeRippleServer(walletData, serverID);
        }

        return status;

    }

    private static boolean removeBitcoinServer(WalletData walletData, String serverID) {

        boolean status = false;
        for (int i = 0; i < walletData.GetBitcoinServerCount(); i++) {
            BitcoinServer btcServer = walletData.GetBitcoinServer(i);
            if (btcServer == null) {
                continue;
            }
            if (serverID.equals(btcServer.getServer_id())) {
                // Put logic for checking if accounts exist
                for (int j = 0; j < walletData.GetBitcoinAcctCount(); j++) {
                    BitcoinAcct btcAcct = walletData.GetBitcoinAcct(j);
                    if (btcAcct == null) {
                        continue;
                    }
                    if (serverID.equals(btcAcct.getServer_id())) {
                        walletData.RemoveBitcoinAcct(j);
                        if (otapi.StoreObject(walletData, "moneychanger", "gui_wallet.dat")) {
                            walletData = Utility.getWalletData();
                            if (walletData == null) {
                                System.out.println("removeOtherTabServer After RemoveBitcoinAcct - walletData returns null");
                                return false;
                            }
                        }

                    }
                }
                if (walletData == null) {
                    System.out.println("removeOtherTabServer Before RemoveBitcoinServer - walletData returns null");
                    return false;
                }
                walletData.RemoveBitcoinServer(i);
                status = otapi.StoreObject(walletData, "moneychanger", "gui_wallet.dat");
                System.out.println("removeOtherTabServer status otapi.StoreObject:" + status);
                break;
            }
        }
        return status;

    }

    private static boolean removeRippleServer(WalletData walletData, String serverID) {

        boolean status = false;
        for (int i = 0; i < walletData.GetRippleServerCount(); i++) {
            RippleServer rippleServer = walletData.GetRippleServer(i);
            if (rippleServer == null) {
                continue;
            }
            if (serverID.equals(rippleServer.getServer_id())) {
                walletData.RemoveRippleServer(i);
                status = otapi.StoreObject(walletData, "moneychanger", "gui_wallet.dat");
                System.out.println("removeOtherTabServer status otapi.StoreObject:" + status);
                break;
            }
        }
        return status;

    }

    private static boolean editRippleServerLabel(WalletData walletData, String serverID, String newLabel) {
        boolean status = false;
        for (int i = 0; i < walletData.GetRippleServerCount(); i++) {
            RippleServer rippleServer = walletData.GetRippleServer(i);
            if (rippleServer == null) {
                continue;
            }
            if (serverID.equals(rippleServer.getServer_id())) {
                rippleServer.setGui_label(newLabel);
                status = otapi.StoreObject(walletData, "moneychanger", "gui_wallet.dat");
                System.out.println("editOtherTabServerLabel status otapi.StoreObject:" + status);
                break;
            }
        }
        return status;

    }
}
