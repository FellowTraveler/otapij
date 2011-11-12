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

    public OpenTransactionAccount(String serverID, String nymID) {
        type = "OpenTransactionAccount";
        this.serverID = serverID;
        this.nymID = nymID;
    }

    public static void getRequestNumber(String serverID, String nymID, long waitTime) throws InterruptedException {

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
    public boolean deleteAccount(String accountID) throws Exception {

        boolean deleteAccount = otapi.OT_API_Wallet_CanRemoveAccount(accountID) == 1 ? true : false;
        if (!deleteAccount) {
            return deleteAccount;
        }
        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_deleteAssetAccount(serverID, nymID, accountID);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN deleteAccount " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN deleteAccount getRequestNumber " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            } else {
                otapi.OT_API_deleteAssetAccount(serverID, nymID, accountID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            }

        }

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("deleteAccount OT_API_Message_GetSuccess returned false:");
            return false;
        }

        //deleteAccount = otapi.OT_API_Wallet_RemoveAccount(accountID) == 1 ? true : false;
        return true;
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

    public Map getAccounts(String assetID, String nymID, String serverID) {

        System.out.println("In getAccounts,assetID:"+assetID+" nymID:"+nymID+" serverID:"+serverID);

        Map account = new HashMap();
        int count = otapi.OT_API_GetAccountCount();
        int j = 0;
        for (int i = 0; i < count; i++) {
            String accountID = otapi.OT_API_GetAccountWallet_ID(i);
            if (assetID.equals(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID))
                    && serverID.equals(otapi.OT_API_GetAccountWallet_ServerID(accountID))
                    && nymID.equals(otapi.OT_API_GetAccountWallet_NymID(accountID))) {
                account.put((j), new String[]{otapi.OT_API_GetAccountWallet_Name(accountID), accountID});
                j++;
            }
        }

        return account;
    }

    public Map getAccountID(String serverID, String nymID, String assetID) {

        int count = otapi.OT_API_GetAccountCount();

        Map accounts = new HashMap();

        if (assetID == null && nymID == null && serverID == null) {
            return null;
        }
        int j = 0;
        for (int i = 0; i < count; i++) {
            String accountID = otapi.OT_API_GetAccountWallet_ID(i);

            if (accountID == null) {
                continue;
            }
            if (assetID.equals(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID))
                    && serverID.equals(otapi.OT_API_GetAccountWallet_ServerID(accountID))
                    && nymID.equals(otapi.OT_API_GetAccountWallet_NymID(accountID))) {
                accounts.put((j), new String[]{otapi.OT_API_GetAccountWallet_Name(accountID), accountID});
                j++;
            }

        }

        return accounts;


    }

    public boolean exchangeBasket(String assetID, String serverID, String nymID, String basket, boolean inXchange, int memberCount, String accountID) throws InterruptedException {

        System.out.println("In exchangeBasket serverID:" + serverID + " accountID:" + accountID + " nymID:" + nymID + " assetID:" + assetID + " basket:" + basket + " inXchange is " + inXchange + " memberCount:" + memberCount);

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < (memberCount + 2)) {
            int configTxnCount = Configuration.getNbrTransactionCount();
            Utility.getTransactionNumbers(serverID, nymID);
            Configuration.setNbrTransactionCount(configTxnCount);
        }

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < (memberCount + 2)) {
            System.out.println("IN exchangeBasket , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return false;
        }

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_exchangeBasket(serverID, nymID, assetID, basket, inXchange == true ? 1 : 0);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN exchangeBasket --- serverResponseMessage:" + serverResponseMessage);
        boolean isSuccess = false;
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());
            otapi.OT_API_exchangeBasket(serverID, nymID, assetID, basket, inXchange == true ? 1 : 0);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("Return False - OT_API_exchangeBasket call failed");
                return false;
            } else {
                System.out.println("exchangeBasket Ends Retry - status " + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
                isSuccess = (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false);
            }
        }else{
            isSuccess = true;
        }

        if (!isSuccess) {
            System.out.println("exchangeBasket OT_API_Message_GetSuccess returns false");
            return false;
        }
        System.out.println("exchangeBasket Ends - server message status " + isSuccess);

        if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
        {
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getNymbox(serverID, nymID); // The failure might have been due to a finalReceipt waiting in my Nymbox.
            Utility.delay();    // So let's update the Nymbox and then try again.
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("exchangeBasket, OT_API_getNymbox returned false");
                return false;
            }
            // <====== TRYING AGAIN (THIRD TIME)
            otapi.OT_API_FlushMessageBuffer();
            System.out.println("exchangeBasket, serverID:"+serverID+" nymID:"+nymID+" assetID:"+assetID+" inXchange:"+inXchange+" basket:"+basket);
            otapi.OT_API_exchangeBasket(serverID, nymID, assetID, basket, inXchange == true ? 1 : 0);

            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            // Balance agreement STILL FAILURE <=========
            //
            if (serverResponseMessage == null) {
                System.out.println("exchangeBasket serverResponseMessage is null after retry after balance agreement failure. ");
                return false;
            } else if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
            {
                System.out.println("exchangeBasket serverResponseMessage is still FAILURE after retry after balance agreement failure. ");
                return false;
            }
            System.out.println("exchangeBasket after balance agreement retry, ");
        }

        if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure.
        {
            // Maybe we have an old Inbox.
            //
            if (!getInboxAccount(serverID, nymID, accountID)) {
                System.out.println("exchangeBasket getInboxAccount returned false");
                return false;
            }
            Utility.delay();

            otapi.OT_API_exchangeBasket(serverID, nymID, assetID, basket, inXchange == true ? 1 : 0);
            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            if (serverResponseMessage == null) {
                System.out.println("exchangeBasket serverResponseMessage is null after retry after transaction failure and retry. ");
                return false;
            }
            if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Transaction failed.
            {
                System.out.println("exchangeBasket serverResponseMessage is still a failure after retry after transaction failure. ");
                return false;
            }
            System.out.println("exchangeBasket after transaction retry, ");
        }

        return true;

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
        try {
            otDetails.setInboxData(getInboxData(accountID));
        } catch (InterruptedException ex) {
            Logger.getLogger(OpenTransactionAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            otDetails.setOutboxData(getOutboxData(accountID));

        } catch (InterruptedException ex) {
            Logger.getLogger(OpenTransactionAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (otapi.OT_API_IsBasketCurrency(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID)) == 1) {
            otDetails.setBasketName(otapi.OT_API_GetAssetType_Name(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID)) == null ? "" : otapi.OT_API_GetAssetType_Name(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID)));
        }

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

    public String getExchangeBasketRequest(String serverID, String nymID, String assetID, String accountID, int multiple) {

        System.out.println("exchangeBasketRequest, serverID:" + serverID + " nymID:" + nymID + " assetID:" + assetID + " accountID:" + accountID + " multiple:" + multiple);

        String basketExchangeRequest = otapi.OT_API_GenerateBasketExchange(serverID, nymID, assetID, accountID, multiple);

        if (basketExchangeRequest == null) {
            System.out.println(" exchangeBasketRequest - OT_API_GenerateBasketExchange returned null");
            return null;
        }

        return basketExchangeRequest;
    }

    public String showBasket(String serverID, String nymID, String assetID) throws InterruptedException {

        if (otapi.OT_API_LoadAssetContract(assetID) == null) {
            System.out.println("IN showBasket,OT_API_LoadAssetContract is null");
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getContract(serverID, nymID, assetID);
            Utility.delay();
            String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN showBasket,OT_API_getContract response:" + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getRequest(serverID, nymID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN getRequestNumber,showBasket " + serverResponseMessage);

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    System.out.println("OT_API_getRequest OT_API_Message_GetSuccess returned false");
                    return null;
                } else {
                    otapi.OT_API_getContract(serverID, nymID, assetID);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        System.out.println("OT_API_getContract after getRequest, OT_API_Message_GetSuccess returned false");
                        return null;
                    }
                }

            }

        }


        StringBuilder basket = new StringBuilder();

        String minTransferAmt = otapi.OT_API_Basket_GetMinimumTransferAmount(assetID);
        String assetName = otapi.OT_API_GetAssetType_Name(assetID);



        System.out.println("showBasket --minTransferAmt:" + minTransferAmt + " assetName:" + assetName);

        basket.append(minTransferAmt);
        basket.append(" ");
        basket.append(assetName);
        basket.append(" == ");

        int basketMemberCount = otapi.OT_API_Basket_GetMemberCount(assetID);

        System.out.println("showBasket --basketMemberCount:" + basketMemberCount);
        for (int i = 0; i < basketMemberCount; i++) {
            String memberAssetID = otapi.OT_API_Basket_GetMemberType(assetID, i);
            System.out.println("showBasket memberAssetID:" + memberAssetID);
            if (memberAssetID != null) {
                String minTransferAmtMember = otapi.OT_API_Basket_GetMemberMinimumTransferAmount(assetID, i);
                System.out.println("showBasket minTransferAmtMember:" + minTransferAmtMember);
                basket.append(minTransferAmtMember);
                basket.append(" ");
                basket.append(otapi.OT_API_GetAssetType_Name(memberAssetID));
                if (i != basketMemberCount - 1) {
                    basket.append(", ");
                }
            }
        }
        System.out.println("showBasket -- basket.toString():" + basket.toString());
        return basket.toString();
    }

    @Override
    public void loadAccount(String assetID, String serverID, String nymID) {

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

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            System.out.println("IN processInbox , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return false;
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
                    System.out.println("transaction is null, skipping this record");
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
        } else {
            isSuccess = true;
        }
        if (!isSuccess) {
            System.out.println("Process Inbox - OT_API_Message_GetSuccess returned false");
            return false;
        }
        System.out.println("Process Inbox Ends - server status " + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));

        if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
        {
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getNymbox(serverID, nymID); // The failure might have been due to a finalReceipt waiting in my Nymbox.
            Utility.delay();    // So let's update the Nymbox and then try again.
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("Process Inbox, OT_API_getNymbox returned false");
                return false;
            }
            // <====== TRYING AGAIN (THIRD TIME)
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_processInbox(serverID, nymID, accountID, accountLedger);

            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            // Balance agreement STILL FAILURE <=========
            //
            if (serverResponseMessage == null) {
                System.out.println("Process Inbox serverResponseMessage is null after retry after balance agreement failure. ");
                return false;
            } else if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
            {
                System.out.println("Process Inbox serverResponseMessage is still FAILURE after retry after balance agreement failure. ");
                return false;
            }
            System.out.println("Process Inbox after balance agreement retry, ");
        }

        if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure.
        {
            // Maybe we have an old Inbox.
            //
            if (!getInboxAccount(serverID, nymID, accountID)) {
                System.out.println("Process Inbox getInboxAccount returned false");
                return false;
            }

            Utility.delay();

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_processInbox(serverID, nymID, accountID, accountLedger);
            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            if (serverResponseMessage == null) {
                System.out.println("Process Inbox serverResponseMessage is null after retry after transaction failure and retry. ");
                return false;
            }
            if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Transaction failed.
            {
                System.out.println("Process Inbox serverResponseMessage is still a failure after retry after transaction failure. ");
                return false;
            }
            System.out.println("Process Inbox after transaction retry, ");
        }

        return true;

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

    public static boolean getInboxAccount(String serverID, String nymID, String accountID) throws InterruptedException {

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
            return false;
        }
        serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("Failed loading OT_API_getInbox/OT_API_getAccount  2nd response");
            return false;
        }
        return true;
    }

    public Map getInboxData(String accountID) throws InterruptedException {

        Map data = new HashMap();

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
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            System.out.println("IN depositCash , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return false;
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
                ///
                boolean isSuccess = false;

                if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
                {
                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_getNymbox(serverID, nymID); // The failure might have been due to a finalReceipt waiting in my Nymbox.
                    Utility.delay();    // So let's update the Nymbox and then try again.
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        System.out.println("depositCash, OT_API_getNymbox returned false");
                        return false;
                    }
                    // <====== TRYING AGAIN (THIRD TIME)
                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_notarizeDeposit(serverID, nymID, accountID, purse);

                    Utility.longDelay();


                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();

                    // Balance agreement STILL FAILURE <=========
                    //
                    if (serverResponseMessage == null) {
                        System.out.println("depositCash serverResponseMessage is null after retry after balance agreement failure. ");
                        return false;
                    } else if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
                    {
                        System.out.println("depositCash serverResponseMessage is still FAILURE after retry after balance agreement failure. ");
                        return false;
                    }
                    System.out.println("depositCash after balance agreement retry, ");
                }

                if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure.
                {
                    // Maybe we have an old Inbox.
                    //
                    if (!getInboxAccount(serverID, nymID, accountID)) {
                        System.out.println("depositCash getInboxAccount returned false");
                        return false;
                    }
                    Utility.delay();

                    otapi.OT_API_notarizeDeposit(serverID, nymID, accountID, purse);
                    Utility.longDelay();

                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();

                    if (serverResponseMessage == null) {
                        System.out.println("depositCash serverResponseMessage is null after retry after transaction failure and retry. ");
                        isSuccess = false;
                    }
                    if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Transaction failed.
                    {
                        System.out.println("depositCash serverResponseMessage is still a failure after retry after transaction failure. ");
                        isSuccess = false;
                    }
                    System.out.println("depositCash after transaction retry, ");
                } else {
                    isSuccess = true;
                }
                ///
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
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            System.out.println("IN withdrawVoucher , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return null;
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
        if (!isSuccess) {
            System.out.println("OT_API_Message_GetSuccess returns false");
            return null;
        }
        isSuccess = otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 1 ? true : false;
        if (!isSuccess) {
            System.out.println("OT_API_Message_GetTransactionSuccess for OT_API_withdrawVoucher returned :" + isSuccess);

            if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
            {
                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getNymbox(serverID, nymID); // The failure might have been due to a finalReceipt waiting in my Nymbox.
                Utility.delay();    // So let's update the Nymbox and then try again.
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    System.out.println("withdrawVoucher, OT_API_getNymbox returned false");
                    return null;
                }
                // <====== TRYING AGAIN (THIRD TIME)
                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_withdrawVoucher(serverID, nymID, accountID, recepientNymID, chequeMemo, amount);

                Utility.longDelay();


                serverResponseMessage = otapi.OT_API_PopMessageBuffer();

                // Balance agreement STILL FAILURE <=========
                //
                if (serverResponseMessage == null) {
                    System.out.println("withdrawVoucher serverResponseMessage is null after retry after balance agreement failure. ");
                    return null;
                } else if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
                {
                    System.out.println("withdrawVoucher serverResponseMessage is still FAILURE after retry after balance agreement failure. ");
                    return null;
                }
                System.out.println("withdrawVoucher after balance agreement retry, ");
            }

            /*System.out.println("Going to getInbox/Account");
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
            }*/
            ////

            System.out.println("exchangeBasket Ends - server message status " + isSuccess);



            if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure.
            {
                // Maybe we have an old Inbox.
                //
                if (!getInboxAccount(serverID, nymID, accountID)) {
                    System.out.println("withdrawVoucher getInboxAccount returned false");
                    return null;
                }
                Utility.delay();

                otapi.OT_API_withdrawVoucher(serverID, nymID, accountID, recepientNymID, chequeMemo, amount);
                Utility.longDelay();

                serverResponseMessage = otapi.OT_API_PopMessageBuffer();

                if (serverResponseMessage == null) {
                    System.out.println("withdrawVoucher serverResponseMessage is null after retry after transaction failure and retry. ");
                    return null;
                }
                if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Transaction failed.
                {
                    System.out.println("withdrawVoucher serverResponseMessage is still a failure after retry after transaction failure. ");
                    return null;
                }
                System.out.println("withdrawVoucher after transaction retry, ");
            }

            ////
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
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            System.out.println("IN withdrawCash , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return false;
        }

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_notarizeWithdrawal(serverID, nymID, accountID, amount); // <=== SEND MESSAGE FOR THE FIRST TIME

        Utility.delay(); // QUESTION:  DOES THIS USE THE SETTINGS PAGE?

        serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN withdrawCash --- " + serverResponseMessage);

        // ----------------------------------------------------------
        // MESSAGE FAILURE.
        //
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) // Message failure
        {
            getRequestNumber(serverID, nymID, Configuration.getWaitTime());     // <=== GET REQUEST NUMBER
            Utility.delay();

            otapi.OT_API_notarizeWithdrawal(serverID, nymID, accountID, amount); // <=== HERE WE TRY AGAIN (SECOND TIME)
            Utility.delay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer(); // withdrawal response (supposedly)

            // Message failure
            if (serverResponseMessage == null) {
                System.out.println("serverResponseMessage is null after retry. ");
                return false;
            } else if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("serverResponseMessage is still FAILURE after retry. ");
                return false;
            }

            // ----------------------------------------
            System.out.println("after retry, ");

//        System.out.println("after retry,OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));
        }

        // Below this point, I know for a fact that serverResponseMessage is not null,
        // that the message is SUCCESS, and that the Request Number is in-sync.
        //
        // But I DON'T KNOW yet, if the BALANCE AGREEMENT or TRANSACTION were a success!!
        // -------------------------------------------------------------------------------

        // Balance Agreement FAILURE        <======
        //
        if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
        {
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getNymbox(serverID, nymID); // The failure might have been due to a finalReceipt waiting in my Nymbox.
            Utility.delay();    // So let's update the Nymbox and then try again.
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("withdrawCash, OT_API_getNymbox returned false");
                return false;
            }
            // <====== TRYING AGAIN (THIRD TIME)
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_notarizeWithdrawal(serverID, nymID, accountID, amount); // <====== TRYING AGAIN (THIRD TIME)

            Utility.longDelay();


            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            // Balance agreement STILL FAILURE <=========
            //
            if (serverResponseMessage == null) {
                System.out.println("serverResponseMessage is null after retry after balance agreement failure. ");
                return false;
            } else if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
            {
                System.out.println("serverResponseMessage is still FAILURE after retry after balance agreement failure. ");
                return false;
            }
            System.out.println("after balance agreement retry, ");
        }

        // Below this point, I know for a fact that serverResponseMessage has a SUCCESSFUL BALANCE AGREEMENT.
        //
        // --------------------------------------------------------------------------------------

        // Next we'll check to see if the transaction itself was a failure.
        //
        if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure.
        {
            // Maybe we have an old Inbox.
            //
            if (!getInboxAccount(serverID, nymID, accountID)) {
                System.out.println("withdrawCash getInboxAccount returned false");
                return false;
            }
            Utility.delay();

            otapi.OT_API_notarizeWithdrawal(serverID, nymID, accountID, amount); // <======== TRYING AGAIN (FOURTH TIME)
            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            if (serverResponseMessage == null) {
                System.out.println("serverResponseMessage is null after retry after transaction failure and retry. ");
                return false;
            }
            if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Transaction failed.
            {
                System.out.println("serverResponseMessage is still a failure after retry after transaction failure. ");
                return false;
            }
            System.out.println("after transaction retry, ");
        }

        // At this point, I know the Transaction was an unqualified success.
        //
        return true;

        /*otapi.OT_API_FlushMessageBuffer();
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
         */



    }

    public boolean sendTransfer(String serverID, String nymID, String accountID, String amount, String note, String recepientAccountID) throws InterruptedException {

        boolean isSuccess = false;
        System.out.println("In sendTransfer serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            System.out.println("IN sendTransfer , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return false;
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
            }
        }
        System.out.println("OT_API_Message_GetSuccess:" + (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false));

        if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
        {
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getNymbox(serverID, nymID); // The failure might have been due to a finalReceipt waiting in my Nymbox.
            Utility.delay();    // So let's update the Nymbox and then try again.
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("sendTransfer, OT_API_getNymbox returned false");
                return false;
            }
            // <====== TRYING AGAIN (THIRD TIME)
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_notarizeTransfer(serverID, nymID, accountID, recepientAccountID, amount, note);

            Utility.longDelay();


            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            // Balance agreement STILL FAILURE <=========
            //
            if (serverResponseMessage == null) {
                System.out.println("serverResponseMessage is null after retry after balance agreement failure. ");
                return false;
            } else if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
            {
                System.out.println("serverResponseMessage is still FAILURE after retry after balance agreement failure. ");
                return false;
            }
            System.out.println("after balance agreement retry, ");
        }

        // Below this point, I know for a fact that serverResponseMessage has a SUCCESSFUL BALANCE AGREEMENT.
        //
        // --------------------------------------------------------------------------------------

        // Next we'll check to see if the transaction itself was a failure.
        //
        if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure.
        {
            // Maybe we have an old Inbox.
            //
            if (!getInboxAccount(serverID, nymID, accountID)) {
                System.out.println("sendTransfer getInboxAccount returned false");
                return false;
            }
            Utility.delay();

            otapi.OT_API_notarizeTransfer(serverID, nymID, accountID, recepientAccountID, amount, note);
            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            if (serverResponseMessage == null) {
                System.out.println("sendTransfer serverResponseMessage is null after retry after transaction failure and retry. ");
                return false;
            }
            if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Transaction failed.
            {
                System.out.println("sendTransfer serverResponseMessage is still a failure after retry after transaction failure. ");
                return false;
            }
            System.out.println("sendTransfer after transaction retry, ");
        }


        otapi.OT_API_getOutbox(serverID, nymID, accountID);
        try {
            Utility.delay();
        } catch (InterruptedException ex) {
            Logger.getLogger(OpenTransactionAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }

    public boolean depositCheque(String serverID, String nymID, String accountID, String cheque) throws InterruptedException {

        System.out.println("In depositCheque serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            System.out.println("IN depositCheque , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return false;
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
            }
        }


        if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
        {
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getNymbox(serverID, nymID); // The failure might have been due to a finalReceipt waiting in my Nymbox.
            Utility.delay();    // So let's update the Nymbox and then try again.
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("depositCheque, OT_API_getNymbox returned false");
                return false;
            }
            // <====== TRYING AGAIN (THIRD TIME)
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_depositCheque(serverID, nymID, accountID, cheque);
            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            // Balance agreement STILL FAILURE <=========
            //
            if (serverResponseMessage == null) {
                System.out.println("serverResponseMessage is null after retry after balance agreement failure. ");
                return false;
            } else if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure
            {
                System.out.println("serverResponseMessage is still FAILURE after retry after balance agreement failure. ");
                return false;
            }
            System.out.println("after balance agreement retry, ");
        }

        // Below this point, I know for a fact that serverResponseMessage has a SUCCESSFUL BALANCE AGREEMENT.
        //
        // --------------------------------------------------------------------------------------

        // Next we'll check to see if the transaction itself was a failure.
        //
        if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Failure.
        {
            // Maybe we have an old Inbox.
            //
            if (!getInboxAccount(serverID, nymID, accountID)) {
                System.out.println("depositCheque getInboxAccount returned false");
                return false;
            }
            Utility.delay();

            otapi.OT_API_depositCheque(serverID, nymID, accountID, cheque);
            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            if (serverResponseMessage == null) {
                System.out.println("serverResponseMessage is null after retry after transaction failure and retry. ");
                return false;
            }
            if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, accountID, serverResponseMessage) == 0) // Transaction failed.
            {
                System.out.println("serverResponseMessage is still a failure after retry after transaction failure. ");
                return false;
            }
            System.out.println("after transaction retry, ");
        }

        return true;
    }

    public String writeCheque(String serverID, String nymID, String accountID, String validFrom, String validTo, String memo, String recepientNymID, String amount) {
        String cheque = "";
        System.out.println("In writeCheque serverID:" + serverID + " nymID:" + nymID);
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);

        }
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            System.out.println("IN writeCheque , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return null;
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

    public int verifyFiles(String serverID, String nymID, String accountID) {
        String filenameAcct = accountID + ".success";
        String filenameNym = nymID + ".success";
        if (!otapi.Exists("receipts", serverID, filenameAcct)
                && !otapi.Exists("receipts", serverID, filenameNym)) {
            return 2;
        }

        boolean status = otapi.OT_API_VerifyAccountReceipt(serverID, nymID, accountID) == 1 ? true : false;
        if (status) {
            return 0;
        } else {
            return 1;
        }
    }

    
}
