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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrapper.core;

import com.wrapper.core.dataobjects.OTDetails;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vicky C
 */
public class OpenTransactionAccount extends Account {

    private String serverID;
    private String nymID;
    private String assetID;
    private String label;

    public OpenTransactionAccount() {

        type = "OpenTransactionAccount";
    }

    public OpenTransactionAccount(String serverID, String nymID, String assetID, String label) {
        type = "OpenTransactionAccount";
        this.serverID = serverID;
        this.nymID = nymID;
        this.assetID = assetID;
        this.label = label;
    }

    public void getRequestNumber(String serverID, String nymID, long waitTime) throws InterruptedException {

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getRequest(serverID, nymID);
        Utility.delay();
        System.out.println("IN getRequestNumber " + otapi.OT_API_PopMessageBuffer());

    }

    private boolean createAssetAccount(String serverID, String nymID, String assetID, long waitTime) throws InterruptedException {

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_createAssetAccount(serverID, nymID, assetID);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN createAssetAccount " + serverResponseMessage);
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            } else {
                otapi.OT_API_createAssetAccount(serverID, nymID, assetID);
                Utility.delay();
            }

        }
        System.out.println(otapi.OT_API_Message_GetCommand(serverResponseMessage));

        //The command printed in the above line showld be '@createAccount'

        boolean success = (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false);

        if (success) {
            otapi.OT_API_SetAccountWallet_Name(otapi.OT_API_Message_GetNewAcctID(serverResponseMessage), nymID, label);
            otapi.OT_API_getOutbox(serverID, nymID, otapi.OT_API_Message_GetNewAcctID(serverResponseMessage));
            try {
                Utility.delay();
            } catch (InterruptedException ex) {
                Logger.getLogger(OpenTransactionAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }

    @Override
    public boolean createAccount() {

        long waitTime = Configuration.getWaitTime();

        System.out.println("IN createAccount serverID:" + serverID + " nymID: " + nymID + " assetID:" + assetID);

        try {

            if (otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID) == 0) {

                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_createUserAccount(serverID, nymID);
                Utility.delay();
                String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN createAccount,OT_API_IsNym_RegisteredAtServer serverResponseMessage " + serverResponseMessage);
                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    return false;
                } else if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    return false;
                } else if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1) {

                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_getRequest(serverID, nymID);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        System.out.println("IN createAccount, during nym registration, getrequestNumber failed . serverResponseMessage - " + serverResponseMessage);
                        return false;
                    }
                }
            }

            boolean success = createAssetAccount(serverID, nymID, assetID, waitTime);

            if (success) {
                System.out.println("In success");
            } else {
                System.out.println("In Failure");
            }

            return success;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("In Exception");
            return false;
        }

    }

    @Override
    public boolean deleteAccount(String accountID) {
        boolean deleteAccount = otapi.OT_API_Wallet_CanRemoveAccount(accountID) == 1 ? true : false;
        if (!deleteAccount) {
            return deleteAccount;
        }
        deleteAccount = otapi.OT_API_Wallet_RemoveAccount(accountID) == 1 ? true : false;
        return deleteAccount;
    }

    @Override
    public void editAccount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map getAccounts(String accountID, String serverID) {
        return getAccountsForAssetServer(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID), serverID);
    }

    public Map getAccountsForAssetServer(String assetID, String serverID) {

        Map account = new HashMap();
        int count = otapi.OT_API_GetAccountCount();
        int j = 0;
        for (int i = 0; i < count; i++) {
            String accountID = otapi.OT_API_GetAccountWallet_ID(i);
            if (assetID.equals(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID))
                    && serverID.equals(otapi.OT_API_GetAccountWallet_ServerID(accountID))) {
                account.put((j), new String[]{otapi.OT_API_GetAccountWallet_Name(accountID), accountID});
                j++;
            }
        }

        return account;
    }

    public Map getAccountID(String serverID, String nymID, String assetID) {

        int count = otapi.OT_API_GetAccountCount();

        Map accounts = new HashMap();

        if(assetID == null && nymID == null && serverID == null){
            return null;
        }
        int j = 0;
        for (int i = 0; i < count; i++) {
            String accountID = otapi.OT_API_GetAccountWallet_ID(i);

            if (accountID == null) {
                continue;
            }
            if(assetID.equals(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID))
                    && serverID.equals(otapi.OT_API_GetAccountWallet_ServerID(accountID))
                    && nymID.equals(otapi.OT_API_GetAccountWallet_NymID(accountID))) {
                accounts.put((j), new String[]{otapi.OT_API_GetAccountWallet_Name(accountID), accountID});
                j++;
            }

        }

        return accounts;


    }

    public void getOTAccountList(String assetID, String serverID, String nymID) {

        int count = otapi.OT_API_GetAccountCount();

        for (int i = 0; i < count; i++) {
            String accountID = otapi.OT_API_GetAccountWallet_ID(i);
            if (accountID == null) {
                continue;
            }
            if (!"ALL".equalsIgnoreCase(assetID)) {
                if (assetID == null || !assetID.equals(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID))) {
                    continue;
                }
            }

            if (!"ALL".equalsIgnoreCase(serverID)) {
                if (serverID == null || !serverID.equals(otapi.OT_API_GetAccountWallet_ServerID(accountID))) {
                    continue;
                }
            }
            if (!"ALL".equalsIgnoreCase(nymID)) {
                if (nymID != null && nymID.equals(otapi.OT_API_GetAccountWallet_NymID(accountID))) {
                    key = accountID;
                    label = otapi.OT_API_GetAccountWallet_Name(accountID);
                    amount = otapi.OT_API_GetAccountWallet_Balance(accountID);
                    accountListOT.put(key, new String[]{label, amount, type, key});
                }
            } else {
                key = accountID;
                label = otapi.OT_API_GetAccountWallet_Name(accountID);
                amount = otapi.OT_API_GetAccountWallet_Balance(accountID);
                accountListOT.put(key, new String[]{label, amount, type, key});
            }

        }
    }

    public boolean editLabel(String accountID, String newLabel) {
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);
        if (nymID == null) {
            return false;
        }
        return otapi.OT_API_SetAccountWallet_Name(accountID, nymID, newLabel) == 1 ? true : false;
    }

    public OTDetails getOTAccountDetails(String accountID) {

        System.out.println("In getOTAccountDetails:");

        OTDetails otDetails = new OTDetails();

        Map tableData = new HashMap();
        tableData.put("0", new String[]{"TRN11", "TRN23", "4000", "pending", "Test address", "Account to", "11/11/2010 12:30:12", "0", "NOOOOOOOOOOOOOOOOOOOs"});
        tableData.put("1", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("2", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("3", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("4", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("5", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("16", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("17", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("18", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("10", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("19", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("68", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        tableData.put("51", new String[]{"TRN19", "TRN21", "8000", "Deposit Cash Receipt", "Test address", "sdfd", "11/11/2010 12:30:12", "1", "1"});
        try {
            otDetails.setInboxData(getInboxData(accountID));
        } catch (InterruptedException ex) {
            Logger.getLogger(OpenTransactionAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //otDetails.setInboxData(tableData);
            //otDetails.setOutboxData(tableData);
            otDetails.setOutboxData(getOutboxData(accountID));
            //otDetails.setReceiptData(tableData);
        } catch (InterruptedException ex) {
            Logger.getLogger(OpenTransactionAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        //otDetails.setReceiptData(tableData);



        otDetails.setBalance(otapi.OT_API_GetAccountWallet_Balance(accountID));
        otDetails.setAccountID(accountID);
        otDetails.setAccountName(otapi.OT_API_GetAccountWallet_Name(accountID));
        String assetID = otapi.OT_API_GetAccountWallet_AssetTypeID(accountID);
        otDetails.setAssetID(assetID);
        otDetails.setAssetName(otapi.OT_API_GetAssetType_Name(assetID));
        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        otDetails.setServerID(serverID);
        otDetails.setServerName(otapi.OT_API_GetServer_Name(serverID));
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);
        otDetails.setNymID(nymID);
        if (nymID == null) {
            otDetails.setNymName("");
        } else {
            otDetails.setNymName(otapi.OT_API_GetNym_Name(nymID));
        }

        return otDetails;
    }

    @Override
    public void loadAccount(String assetID, String serverID, String nymID) {

        //accountListOT.put("OT1", new String[] {"otlabel1","3000","OpenTransactionAccount","OT1"});
        System.out.println("In OT load");
        getOTAccountList(assetID, serverID, nymID);
        System.out.println("-----------accountList:" + accountListOT.entrySet());

    }

    @Override
    public Object getAccountDetails(String accountID) {
        return getOTAccountDetails(accountID);

    }

    public boolean processInbox(String accountID, Map selectedIndices) throws InterruptedException {

        System.out.println("Process Inbox starts");

        boolean isSuccess = false;
        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);
        String ledger = otapi.OT_API_LoadInbox(serverID, nymID, accountID);

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }

        String responseLedger = otapi.OT_API_Ledger_CreateResponse(serverID, nymID, accountID, ledger);

        if (responseLedger == null) {
            System.out.println("OT_API_Ledger_CreateResponse returned responseLedger:" + responseLedger);
            return false;
        }

        int count = otapi.OT_API_Ledger_GetCount(serverID, nymID, accountID, ledger);

        boolean isInboxEmpty = true;
        for (int i = 0; i < count; i++) {
            if (selectedIndices.containsKey(String.valueOf(i))) {
                int selected = ((Boolean) selectedIndices.get(String.valueOf(i))) == true ? 1 : 0;
                String transaction = otapi.OT_API_Ledger_GetTransactionByIndex(serverID, nymID, accountID, ledger, i);
                if (transaction == null) {
                    continue;
                }
                responseLedger = otapi.OT_API_Transaction_CreateResponse(serverID, nymID, accountID, responseLedger, transaction, selected);
                isInboxEmpty = false;
            }
        }

        if (isInboxEmpty) {
            System.out.println("Return False - reason isInboxEmpty - " + isInboxEmpty);
            return false;
        }

        String accountLedger = otapi.OT_API_Ledger_FinalizeResponse(serverID, nymID, accountID, responseLedger);

        if (accountLedger == null) {
            System.out.println("Return False - reason accountLedger from  OT_API_Ledger_FinalizeResponse - " + accountLedger);
            return false;
        }

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_processInbox(serverID, nymID, accountID, accountLedger);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN processInbox --- " + serverResponseMessage);
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            otapi.OT_API_processInbox(serverID, nymID, accountID, accountLedger);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("Return False - OT_API_processInbox call failed");
                return false;
            } else {
                System.out.println("Process Inbox Ends Retry - status " + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
                isSuccess = (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false);
            }
        }
        System.out.println("Process Inbox Ends - server status " + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
        isSuccess = otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;

        System.out.println("Process Inbox Ends - transction status " + isSuccess);
        return isSuccess;

    }

    public boolean getInboxOutboxAccount(String accountID) throws InterruptedException {

        boolean isSuccess = true;

        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);

        otapi.OT_API_FlushMessageBuffer();
        System.out.println("Before getInbox Server call");
        otapi.OT_API_getInbox(serverID, nymID, accountID);
        otapi.OT_API_getAccount(serverID, nymID, accountID);
        otapi.OT_API_getOutbox(serverID, nymID, accountID);

        Utility.delay();

        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("After getrequest,  Failed loading OT_API_getInbox/OT_API_getAccount");
            } else {
                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getInbox(serverID, nymID, accountID);
                otapi.OT_API_getAccount(serverID, nymID, accountID);
                otapi.OT_API_getOutbox(serverID, nymID, accountID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            }
        }
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("Failed loading OT_API_getInbox/OT_API_getAccount/OT_API_getOutbox 1st response");
            isSuccess = false;
        }
        serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("Failed loading OT_API_getInbox/OT_API_getAccount /OT_API_getOutbox 2nd response");
            isSuccess = false;
        }
        serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("Failed loading OT_API_getInbox/OT_API_getAccount/OT_API_getOutbox 3rd response");
            isSuccess = false;
        }

        return isSuccess;
    }

    private void getInboxAccount(String serverID, String nymID, String accountID) throws InterruptedException {

        otapi.OT_API_FlushMessageBuffer();
        System.out.println("Before getInbox Server call");
        otapi.OT_API_getInbox(serverID, nymID, accountID);
        otapi.OT_API_getAccount(serverID, nymID, accountID);

        Utility.delay();

        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("After getrequest,  Failed loading OT_API_getInbox/OT_API_getAccount");
            } else {

                otapi.OT_API_getInbox(serverID, nymID, accountID);
                otapi.OT_API_getAccount(serverID, nymID, accountID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            }
        }
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("Failed loading OT_API_getInbox/OT_API_getAccount  1st response");
        }
        serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("Failed loading OT_API_getInbox/OT_API_getAccount  2nd response");
        }
    }

    public Map getInboxData(String accountID) throws InterruptedException {

        Map data = new HashMap();

        /*data.put("0", new String[]{"sss","ccc","ccccz","vvv","dd","sd","cdf","0","sss","sdffd","gggg"});

        if(true)
        return data;*/

        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);

        getInboxAccount(serverID, nymID, accountID);

        String ledger = otapi.OT_API_LoadInbox(serverID, nymID, accountID);
        if (ledger == null) {
            return data;
        }
        int count = otapi.OT_API_Ledger_GetCount(serverID, nymID, accountID, ledger);

        for (int i = 0; i < count; i++) {

            String transactionID = otapi.OT_API_Ledger_GetTransactionIDByIndex(serverID, nymID, accountID, ledger, i);
            String transaction = otapi.OT_API_Ledger_GetTransactionByIndex(serverID, nymID, accountID, ledger, i);
            if (transaction == null) {
                System.out.println("Skip this record, since OT_API_Ledger_GetTransactionByIndex has returned null");
                continue;
            }
            String txnType = otapi.OT_API_Transaction_GetType(serverID, nymID, accountID, transaction);
            System.out.println("In Inbox, type of txn is :" + txnType);
            String amount = otapi.OT_API_Transaction_GetAmount(serverID, nymID, accountID, transaction);
            String referenceNumber = otapi.OT_API_Transaction_GetDisplayReferenceToNum(serverID, nymID, accountID, transaction);
            String timestamp = otapi.OT_API_Transaction_GetDateSigned(serverID, nymID, accountID, transaction);
            if (timestamp == null) {
                timestamp = "";
            } else {
                try {
                    timestamp = String.valueOf(new Date(Long.parseLong(timestamp) * 1000));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.out.println("Invalid number returned by timestmp:" + timestamp);
                }
            }
            String userFromTo = getUser(txnType, serverID, nymID, accountID, transaction);
            String nymUserID = userFromTo;
            String accountFromTo = getAccount(txnType, serverID, nymID, accountID, transaction);
            String accountInboxID = accountFromTo;
            if (userFromTo == null) {
                userFromTo = "";
            } else {
                userFromTo = otapi.OT_API_GetNym_Name(userFromTo) == null ? userFromTo : otapi.OT_API_GetNym_Name(userFromTo);
            }
            if (accountFromTo == null) {
                accountFromTo = "";
            } else {
                accountFromTo = otapi.OT_API_GetAccountWallet_Name(accountFromTo) == null ? accountFromTo : otapi.OT_API_GetAccountWallet_Name(accountFromTo);
            }

            String note = "";

            if (txnType != null && "pending".equalsIgnoreCase(txnType)) {
                note = otapi.OT_API_Pending_GetNote(serverID, nymID, accountID, transaction);
            }

            String[] row = new String[11];
            row[0] = transactionID;
            row[1] = referenceNumber;
            row[2] = amount;
            row[3] = txnType;
            row[4] = userFromTo;
            row[5] = accountFromTo;
            row[6] = timestamp;
            row[7] = String.valueOf(i);
            row[10] = note;
            row[8] = nymUserID;
            row[9] = accountInboxID;


            data.put(row[7], row);

        }

        return data;
    }

    public Map getOutboxData(String accountID) throws InterruptedException {

        Map data = new HashMap();
        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);

        otapi.OT_API_getOutbox(serverID, nymID, accountID);

        try {
            Utility.delay();
        } catch (InterruptedException ex) {
            Logger.getLogger(OpenTransactionAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            otapi.OT_API_getOutbox(serverID, nymID, accountID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("Failed loading OT_API_getOutbox");
            }
        }
        String ledger = otapi.OT_API_LoadOutbox(serverID, nymID, accountID);

        if (ledger == null) {
            return data;
        }

        int count = otapi.OT_API_Ledger_GetCount(serverID, nymID, accountID, ledger);

        for (int i = 0; i < count; i++) {

            String transactionID = otapi.OT_API_Ledger_GetTransactionIDByIndex(serverID, nymID, accountID, ledger, i);
            String transaction = otapi.OT_API_Ledger_GetTransactionByIndex(serverID, nymID, accountID, ledger, i);
            if (transaction == null) {
                System.out.println("Skip this record, since OT_API_Ledger_GetTransactionByIndex has returned null");
                continue;
            }
            String txnType = otapi.OT_API_Transaction_GetType(serverID, nymID, accountID, transaction);
            System.out.println("In Outbox, type of txn is :" + txnType);
            String amount = "-" + otapi.OT_API_Transaction_GetAmount(serverID, nymID, accountID, transaction);
            String referenceNumber = otapi.OT_API_Transaction_GetDisplayReferenceToNum(serverID, nymID, accountID, transaction);
            String timestamp = otapi.OT_API_Transaction_GetDateSigned(serverID, nymID, accountID, transaction);
            System.out.println("timestamp:" + timestamp);
            if (timestamp == null) {
                timestamp = "";
            } else {
                try {
                    timestamp = String.valueOf(new Date(Long.parseLong(timestamp) * 1000));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.out.println("Invalid number returned by timestmp:" + timestamp);
                }
            }

            System.out.println("serverID:" + serverID + " nymID:" + nymID + " accountID:" + accountID);

            String userFromTo = otapi.OT_API_Transaction_GetRecipientUserID(serverID, nymID, accountID, transaction);
            String accountFromTo = otapi.OT_API_Transaction_GetRecipientAcctID(serverID, nymID, accountID, transaction);
            String nymUserID = userFromTo;
            String accountOutboxID = accountFromTo;
            System.out.println("recepient nym id returned by OT_API_Transaction_GetRecipientUserID:" + userFromTo);
            if (userFromTo == null) {
                userFromTo = "";
            } else {
                userFromTo = otapi.OT_API_GetNym_Name(userFromTo) == null ? userFromTo : otapi.OT_API_GetNym_Name(userFromTo);
            }


            if (accountFromTo == null) {
                accountFromTo = "";
            } else {
                accountFromTo = otapi.OT_API_GetAccountWallet_Name(accountFromTo) == null ? accountFromTo : otapi.OT_API_GetAccountWallet_Name(accountFromTo);
            }


            String note = "";

            if (txnType != null && "pending".equalsIgnoreCase(txnType)) {
                note = otapi.OT_API_Pending_GetNote(serverID, nymID, accountID, transaction);
            }
            System.out.println("final recepient nym id/name:" + userFromTo);
            String[] row = new String[11];
            row[0] = transactionID;
            row[1] = referenceNumber;
            row[2] = amount;
            row[3] = txnType;
            row[4] = userFromTo;
            row[5] = accountFromTo;
            row[6] = timestamp;
            row[7] = String.valueOf(i);
            row[10] = note;
            row[8] = nymUserID;
            row[9] = accountOutboxID;
            data.put(row[7], row);

        }

        return data;

    }

    private String getUser(String txnType, String serverID, String nymID, String accountID, String transaction) {

        String user = "";
        if (txnType != null && "pending".equalsIgnoreCase(txnType)) {
            user = otapi.OT_API_Transaction_GetSenderUserID(serverID, nymID, accountID, transaction);
        } else if ("chequeReceipt".equalsIgnoreCase(txnType) || "transferReceipt".equalsIgnoreCase(txnType)
                || "marketReceipt".equalsIgnoreCase(txnType) || "paymentReceipt   ".equalsIgnoreCase(txnType)) {

            user = otapi.OT_API_Transaction_GetRecipientUserID(serverID, nymID, accountID, transaction);

        }

        return user;

    }

    private String getAccount(String txnType, String serverID, String nymID, String accountID, String transaction) {

        String account = "";
        if (txnType != null && "pending".equalsIgnoreCase(txnType)) {
            account = otapi.OT_API_Transaction_GetSenderAcctID(serverID, nymID, accountID, transaction);
        } else if ("chequeReceipt".equalsIgnoreCase(txnType) || "transferReceipt".equalsIgnoreCase(txnType)
                || "marketReceipt".equalsIgnoreCase(txnType) || "paymentReceipt   ".equalsIgnoreCase(txnType)) {

            account = otapi.OT_API_Transaction_GetRecipientAcctID(serverID, nymID, accountID, transaction);

        }

        return account;

    }

    public String[] getPurseBalance(String serverID, String assetID, String nymID) {

        String purse = otapi.OT_API_LoadPurse(serverID, assetID, nymID);
        if (purse == null) {
            return null;
        }
        String balance = otapi.OT_API_Purse_GetTotalValue(serverID, assetID, purse);

        return new String[]{purse, balance};

    }

    public boolean depositCash(String serverID, String nymID, String accountID, String purse, boolean isToken) throws InterruptedException {

        System.out.println("In depositCash serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID + " isToken:" + isToken);
        Utility.setOtDepositCash(null);
        String oldPurse = purse;
        if (isToken) {
            purse = getNewPurse(purse, serverID, nymID, accountID);
            System.out.println("purse after getting from push purse:" + purse);
            if (purse == null) {
                Utility.setOtDepositCash(oldPurse);
                return false;
            }
        }

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }
        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_notarizeDeposit(serverID, nymID, accountID, purse);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN depositCash server response for notarize deposit --- " + serverResponseMessage);
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            otapi.OT_API_notarizeDeposit(serverID, nymID, accountID, purse);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN depositCash server response for notarize deposit after doing getrequestNumber--- " + serverResponseMessage);
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("IN depositCash, even after getting request number, failure, returning null");
                return false;
            } else {
                System.out.println("after retry, OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
                if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 && otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) {
                    getInboxAccount(serverID, nymID, accountID);
                    otapi.OT_API_notarizeDeposit(serverID, nymID, accountID, purse);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    System.out.println("IN depositCash After getInboxAccount and OT_API_notarizeDeposit call,serverResponseMessage:" + serverResponseMessage);
                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        return false;
                    }
                }
                boolean isSuccess = otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;
                System.out.println("depositCash OT ends, status:" + isSuccess);
                if (!isSuccess && isToken) {
                    System.out.println("IN depositCash, failed action for single token");
                    String assetID = otapi.OT_API_GetAccountWallet_AssetTypeID(accountID);
                    boolean importStatus = otapi.OT_API_Wallet_ImportPurse(serverID, assetID, nymID, purse) == 1 ? true : false;
                    System.out.println("Since failure of depositCashPurse, OT_API_Wallet_ImportPurse called, status of import:" + importStatus);
                    if (!importStatus) {
                        Utility.setOtDepositCash(purse);
                    }
                }
                return isSuccess;
            }
        }

        System.out.println("OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
        if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 && otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) {
            getInboxAccount(serverID, nymID, accountID);
            otapi.OT_API_notarizeDeposit(serverID, nymID, accountID, purse);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            }
        }
        return otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;

    }

    public String withdrawVoucher(String serverID, String nymID, String accountID, String amount, String chequeMemo, String recepientNymID) throws InterruptedException {

        System.out.println("In withdrawVoucher serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }
        boolean isSuccess = false;
        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_withdrawVoucher(serverID, nymID, accountID, recepientNymID, chequeMemo, amount);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN withdrawVoucher, serverResponseMessage -- " + serverResponseMessage);
        System.out.println("serverResponseMessage ends ");
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            otapi.OT_API_withdrawVoucher(serverID, nymID, accountID, recepientNymID, chequeMemo, amount);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return null;
            }
        }
        isSuccess = (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false);
        System.out.println("OT_API_Message_GetSuccess status :" + isSuccess);
        isSuccess = otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;
        if (!isSuccess) {
            System.out.println("OT_API_Message_GetTransactionSuccess for OT_API_withdrawVoucher returned :" + isSuccess);
            System.out.println("Going to getInbox/Account");
            if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1) {
                getInboxAccount(serverID, nymID, accountID);
                otapi.OT_API_withdrawVoucher(serverID, nymID, accountID, recepientNymID, chequeMemo, amount);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    return null;
                }
            }
            isSuccess = otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;
            if (!isSuccess) {
                System.out.println("Even after getInbox/Account,  OT_API_Message_GetTransactionSuccess for OT_API_withdrawVoucher returned :" + isSuccess);
                return null;
            }
        }
        String ledger = otapi.OT_API_Message_GetLedger(serverResponseMessage);
        if (ledger == null) {
            System.out.println(" ledger is null, returned by OT_API_Message_GetLedger,serverResponseMessage passed as argument");
            return null;
        }
        String transaction = otapi.OT_API_Ledger_GetTransactionByIndex(serverID, nymID, accountID, ledger, 0);
        if (transaction == null) {
            System.out.println("transaction is null, returned by OT_API_Ledger_GetTransactionByIndex, argument passed, index 0 and ledger :" + ledger);
            return null;
        }
        String output = otapi.OT_API_Transaction_GetVoucher(serverID, nymID, accountID, transaction);
        System.out.println("output returned by OT_API_Transaction_GetVoucher:" + output);

        return output;
    }

    public boolean withdrawCash(String serverID, String nymID, String accountID, String amount) throws InterruptedException {
        String serverResponseMessage = null;
        System.out.println("In withdrawCash serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);
        String assetID = otapi.OT_API_GetAccountWallet_AssetTypeID(accountID);
        String assetContract = otapi.OT_API_LoadAssetContract(assetID);
        if (assetContract == null) {
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getContract(serverID, nymID, assetID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                getRequestNumber(serverID, nymID, Configuration.getWaitTime());
                otapi.OT_API_getContract(serverID, nymID, assetID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    return false;
                }
            }
            assetContract = otapi.OT_API_LoadAssetContract(assetID);
        }

        if (assetContract == null) {
            System.out.println("OT_API_LoadAssetContract returned null even after OT_API_getContract ");
            return false;
        }
        String mintFile = otapi.OT_API_LoadMint(serverID, assetID);
        if (mintFile == null) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getMint(serverID, nymID, assetID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                getRequestNumber(serverID, nymID, Configuration.getWaitTime());
                otapi.OT_API_getMint(serverID, nymID, assetID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    return false;
                }
            }
            mintFile = otapi.OT_API_LoadMint(serverID, assetID);
        }

        if (mintFile == null) {
            System.out.println("OT_API_LoadMint returned null even after OT_API_getMint ");
            return false;
        }
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }
        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_notarizeWithdrawal(serverID, nymID, accountID, amount);
        Utility.delay();
        serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN withdrawCash --- " + serverResponseMessage);
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            otapi.OT_API_notarizeWithdrawal(serverID, nymID, accountID, amount);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            } else {
                System.out.println("after retry,OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
                if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 && otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) {
                    getInboxAccount(serverID, nymID, accountID);
                    otapi.OT_API_notarizeWithdrawal(serverID, nymID, accountID, amount);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        return false;
                    }
                }
                return otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;
            }
        }
        System.out.println("OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
        if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 && otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) {
            getInboxAccount(serverID, nymID, accountID);
            otapi.OT_API_notarizeWithdrawal(serverID, nymID, accountID, amount);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            }
        }
        return otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;


    }

    public boolean sendTransfer(String serverID, String nymID, String accountID, String amount, String note, String recepientAccountID) throws InterruptedException {

        boolean isSuccess = false;
        System.out.println("In sendTransfer serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }
        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_notarizeTransfer(serverID, nymID, accountID, recepientAccountID, amount, note);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN sendTransfer,serverResponseMessage --- " + serverResponseMessage);
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            otapi.OT_API_notarizeTransfer(serverID, nymID, accountID, recepientAccountID, amount, note);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            } else {

                System.out.println("after retry, OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
                if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 && otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) {
                    getInboxAccount(serverID, nymID, accountID);
                    otapi.OT_API_notarizeTransfer(serverID, nymID, accountID, recepientAccountID, amount, note);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        return false;
                    }
                }
                isSuccess = otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;

            }
        }
        System.out.println("OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
        if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 && otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) {
            getInboxAccount(serverID, nymID, accountID);
            otapi.OT_API_notarizeTransfer(serverID, nymID, accountID, recepientAccountID, amount, note);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            }
        }
        isSuccess = otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;

        if (isSuccess) {
            otapi.OT_API_getOutbox(serverID, nymID, accountID);
            try {
                Utility.delay();
            } catch (InterruptedException ex) {
                Logger.getLogger(OpenTransactionAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return isSuccess;

    }

    public boolean depositCheque(String serverID, String nymID, String accountID, String cheque) throws InterruptedException {

        System.out.println("In depositCheque serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }
        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_depositCheque(serverID, nymID, accountID, cheque);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN depositCheque --- " + serverResponseMessage);
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            otapi.OT_API_depositCheque(serverID, nymID, accountID, cheque);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            } else {

                System.out.println("ater retry, OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
                if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 && otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) {
                    getInboxAccount(serverID, nymID, accountID);
                    otapi.OT_API_depositCheque(serverID, nymID, accountID, cheque);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        return false;
                    }
                }
                return otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;

            }
        }

        System.out.println("OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
        if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 && otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) {
            getInboxAccount(serverID, nymID, accountID);
            otapi.OT_API_depositCheque(serverID, nymID, accountID, cheque);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            }
        }
        return otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;
    }

    public String writeCheque(String serverID, String nymID, String accountID, String validFrom, String validTo, String memo, String recepientNymID, String amount) {
        String cheque = "";
        System.out.println("In writeCheque serverID:" + serverID + " nymID:" + nymID);
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);

        }
        System.out.println("validTo:" + validTo);
        cheque = otapi.OT_API_WriteCheque(serverID, amount, validFrom, validTo, accountID, nymID, memo, recepientNymID);
        System.out.println("cheque:" + cheque);
        return cheque;
    }

    private String getNewPurse(String purse, String serverID, String nymID, String accountID) {
        String updatedPurse = purse;
        String assetID = otapi.OT_API_GetAccountWallet_AssetTypeID(accountID);
        String newPurse = otapi.OT_API_CreatePurse(serverID, assetID, nymID);
        if (newPurse == null) {
            System.out.println("getNewPurse , OT_API_CreatePurse returns null, hence passing back same purse");
            return updatedPurse;
        }
        updatedPurse = otapi.OT_API_Purse_Push(serverID, assetID, nymID, newPurse, purse);
        System.out.println("getNewPurse ,updatedPurse");
        return updatedPurse;

    }

    public boolean verifyFiles(String serverID, String nymID, String accountID) {

        boolean status = otapi.OT_API_VerifyAccountReceipt(serverID, nymID, accountID) == 1 ? true : false;
        return status;
    }
}
//OT_API_Message_GetTransactionSuccess()

