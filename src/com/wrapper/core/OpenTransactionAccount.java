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
import com.wrapper.core.util.OTAPI_Func;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



// *********************************************************


/**
 *
 * @author Vicky C
 * 
 * Rewrites: FT -- Lots of refactoring. I wrote the above class, and re-used it
 * as much as possible in all the code below, to reduce its size and complexity.
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

    private boolean createAssetAccount(String serverID, String nymID, String assetID) {

        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.CREATE_ASSET_ACCT, serverID, nymID, assetID);
        String      strResponse  = OTAPI_Func.SendRequest(theRequest, "CREATE_ASSET_ACCT");
        
        if (null == strResponse)
        {
            System.out.println("IN createAssetAccount: OTAPI_Func.SendRequest(() failed. (I give up.) ");
            return false;
        }
        // ----------------------------------------------------------
        
        String strNewAcctID = otapi.OT_API_Message_GetNewAcctID(strResponse);
        
        otapi.OT_API_SetAccountWallet_Name(strNewAcctID, nymID, label);
        
        // When you first create an asset account you need to get a copy of the outbox.
        // (Or create an empty one, which would save us sending the message here. Todo.)
        //
        Utility.getOutboxLowLevel(serverID, nymID, strNewAcctID);
        // ----------------------------------------------------------
 
        return true;
    }

    @Override
    public boolean createAccount() {
        boolean bSuccess = false;
        System.out.println("IN createAccount serverID:" + serverID + " nymID: " + nymID + " assetID:" + assetID);
        try {
            // If the Nym's not registered at the server, we do that first...
            //
            if (0 == otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID)) {
                
                OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.CREATE_USER_ACCT, serverID, nymID);
                String      strResponse  = OTAPI_Func.SendRequest(theRequest, "CREATE_USER_ACCT");

                if (null == strResponse)
                {
                    System.out.println("IN createAccount: OTAPI_Func.SendRequest(() failed. (I give up.) ");
                    return false;
                }
                // ---------------
                // Create_User_Acct should ALWAYS succeed (since the server succeeds no matter what,
                // to prevent sync issues.) Therefore I feel *somewhat* safe in going ahead here
                // and calling Utility.getRequestNumber(), since this is a brand new Nym and will
                // need to sync it for the first time, in order to do any other messages.
                if (false == Utility.getRequestNumber(serverID, nymID))
                {
                    System.out.println("In createAccount: Failure to call getRequestNumber() after supposedly creating user acct at server.");
                    //return false; // commenting this out, so for moa7 types with bad connections, even if this call fails, it'll go ahead and try the next one, and when THAT fails it does another getRequestNumber followed by a retry. So we have plenty of chances to succeed here...
                }
            }
            // -----------------------------------------------
            // Okay the Nym is definitely registered at the server, so let's 
            // create the asset account...
            //
            bSuccess = createAssetAccount(serverID, nymID, assetID);

            if (bSuccess)
                System.out.println("In createAccount: Success");
            else
                System.out.println("In createAccount: Failure");
        } catch (Exception e) {
//          e.printStackTrace();
            System.out.println("In Exception");
            return false;
        }
        return bSuccess;
    }

    @Override
    public boolean deleteAccount(String accountID) throws Exception {

        boolean deleteAccount = otapi.OT_API_Wallet_CanRemoveAccount(accountID) == 1 ? true : false;
        
        if (!deleteAccount) {
            System.out.println("deleteAccount: unable. There must still be open receipts, or a nonzero balance?");
            return false;
        }
        // ----------------------------------------
        
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.DELETE_ASSET_ACCT, serverID, nymID, accountID);
        String      strResponse  = OTAPI_Func.SendRequest(theRequest, "DELETE_ASSET_ACCT");
        
        if (null == strResponse)
        {
            System.out.println("IN deleteAccount: OTAPI_Func.SendRequest(() failed. (I give up.) ");
            return false;
        }
        // ----------------------------------------

        // BY THIS POINT, we successfully deleted the account from the server.
        // Now we just need to remove it from the wallet (todo).

        // TODO
        //deleteAccount = otapi.OT_API_Wallet_RemoveAccount(accountID) == 1 ? true : false;
        System.out.println("IN deleteAccount: THIS IS THE SPOT WHERE, THE **DELETE ACCOUNT** MESSAGE TO THE SERVER WAS SUCCESSFUL, THREFORE WE CAN GO AHEAD AND DELETE THE LOCAL ONE AS WELL. (TODO.)");
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
            System.out.println("In getAccountID:  nymID:"+nymID+" serverID:"+serverID);
            return null;
        }
        int j = 0;
        for (int i = 0; i < count; i++) {
            String accountID = otapi.OT_API_GetAccountWallet_ID(i);

            if (accountID == null) {
                continue;
            }
            if ((assetID != null) && assetID.equals(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID))
             && (serverID != null) && serverID.equals(otapi.OT_API_GetAccountWallet_ServerID(accountID))
             && (nymID != null) && nymID.equals(otapi.OT_API_GetAccountWallet_NymID(accountID))) {
                accounts.put((j), new String[]{otapi.OT_API_GetAccountWallet_Name(accountID), accountID});
                j++;
            }

        }

        return accounts;


    }

    public boolean exchangeBasket(String assetID, String serverID, String nymID, String basket, boolean inXchange, int memberCount, String accountID) {

        String IN_FUNCTION = new String("exchangeBasket");
        System.out.println("In " + IN_FUNCTION + " serverID:" + serverID + " accountID:" + accountID + " nymID:" + nymID + " assetID:" + assetID + " basket:" + basket + " inXchange is " + inXchange + " memberCount:" + memberCount);

        // ----------------------------------------
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.EXCHANGE_BASKET, serverID, nymID, assetID, basket, accountID, inXchange, (memberCount + 2));
        String      strResponse  = OTAPI_Func.SendTransaction(theRequest, "EXCHANGE_BASKET");
        
        if (null == strResponse)
        {
            System.out.println("IN exchangeBasket: OTAPI_Func.SendTransaction(() failed. (I give up.) ");
            return false;
        }
        // ----------------------------------------

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

        System.out.println("In getOTAccountDetails: ");

        OTDetails otDetails = new OTDetails();

        if (null == accountID)
        {
            System.out.println("Failure: accountID is null.");
            return otDetails;
        }
        // ---------------------------------------------
        
        otDetails.setAccountID(accountID);

        // ---------------------------------------------
        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        if (null == serverID)
        {
            System.out.println("Failure: serverID is null for accountID: " + accountID);
            return otDetails;
        }
        otDetails.setServerID(serverID);
        
        String serverName = otapi.OT_API_GetServer_Name(serverID);
        if (null == serverName)
        {
            System.out.println("Failure: serverName is null for serverID: " + serverID);
            return otDetails;
        }
        otDetails.setServerName(serverName);
        // ---------------------------------------------
        String assetID = otapi.OT_API_GetAccountWallet_AssetTypeID(accountID);
        
        if (null == assetID)
        {
            System.out.println("Failure: assetID is null for accountID: " + accountID);
            return otDetails;
        }
        otDetails.setAssetID(assetID);
        
        String assetName = otapi.OT_API_GetAssetType_Name(assetID);
        
        if (null == assetName)
        {
            System.out.println("Failure: assetName is null for assetID: " + assetID);
            return otDetails;
        }
        otDetails.setAssetName(assetName);
        // ---------------------------------------------
        String strAccountName = otapi.OT_API_GetAccountWallet_Name(accountID);
        
        if (null == strAccountName)
        {
            System.out.println("Failure: strAccountName is null for accountID: " + accountID);
            return otDetails;
        }
        otDetails.setAccountName(strAccountName);
        
        String strBalance = otapi.OT_API_GetAccountWallet_Balance(accountID);
        
        if (null == strBalance)
        {
            System.out.println("Failure: strBalance is null for accountID: " + accountID);
            return otDetails;
        }
        otDetails.setBalance(strBalance);
        // ---------------------------------------------
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);
        
        if (null == nymID)
        {
            System.out.println("Failure: nymID is null for accountID: " + accountID);
            return otDetails;
        }
        otDetails.setNymID(nymID);
        
        String strNymName = (null == nymID) ? new String("") : otapi.OT_API_GetNym_Name(nymID);
        
        if (null == strNymName)
        {
            System.out.println("Failure: strNymName is null for nymID: " + nymID);
            return otDetails;
        }
        otDetails.setNymName(strNymName);
        // ---------------------------------------------
        try {
            if (Utility.getIntermediaryFiles(serverID, nymID, accountID))
            {
                otDetails.setInboxData(getInboxData(accountID));
                otDetails.setOutboxData(getOutboxData(accountID));
            }
            else
                System.out.println("getOTAccountDetails: Failed in Utility.getIntermediaryFiles()");
        } catch (InterruptedException ex) {
            Logger.getLogger(OpenTransactionAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        // ---------------------------------------------        
        if (1 == otapi.OT_API_IsBasketCurrency(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID))) {
            otDetails.setBasketName(otapi.OT_API_GetAssetType_Name(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID)) == null ? "" : 
                    otapi.OT_API_GetAssetType_Name(otapi.OT_API_GetAccountWallet_AssetTypeID(accountID)));
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
            System.out.println("IN showBasket, OT_API_LoadAssetContract is null");

            // ----------------------------------------
            OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.GET_CONTRACT, serverID, nymID, assetID);
            String      strResponse  = OTAPI_Func.SendRequest(theRequest, "GET_CONTRACT");

            if (null == strResponse)
            {
                System.out.println("IN showBasket: OTAPI_Func.SendRequest(() failed. (I give up.) ");
                return null;
            }
        }
        // ----------------------------------------

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

        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);
        String ledger = otapi.OT_API_LoadInbox(serverID, nymID, accountID);

        // ------------------------------------------------------------------
        // Normally I could remove this code, since SendTransaction() (below)
        // does the work already, of grabbing new numbers when they're needed.
        // But in THIS case, I need those numbers BEFORE I send the transaction,
        // as I'm forming the message-ledger, and a number is reserved during
        // this time.
        // Specifically, inside the call to OT_API_Ledger_CreateResponse(), the
        // first time it is called for a specific processInbox() message, is when
        // the transaction # is grabbed from local storage for that message. If
        // it's not available, the call will fail.
        //
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) 
        {
            if ((false == Utility.getTransactionNumbers(serverID, nymID)) ||
                (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount())) 
            {
                System.out.println("IN processInbox , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
                return false;
            }
        }
        // ------------------------------------------------------------------
        // SET UP THE PROCESS INBOX MESSAGE.
        
        String responseLedger = otapi.OT_API_Ledger_CreateResponse(serverID, nymID, accountID, ledger);

        if (responseLedger == null) {
            System.out.println("OT_API_Ledger_CreateResponse returned responseLedger:" + responseLedger);
            return false;
        }
        // ------------------------
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
                String strTemp = new String(responseLedger);
                responseLedger = otapi.OT_API_Transaction_CreateResponse(serverID, nymID, accountID, strTemp, transaction, selected);
                isInboxEmpty = false;
            }
        }

        if (isInboxEmpty) {
            System.out.println("Return False - reason isInboxEmpty - " + isInboxEmpty);
            return false;
        }
        // ------------------------

        if (responseLedger == null) {
            System.out.println("Return False - reason responseLedger is null, from  OT_API_Transaction_CreateResponse.");
            return false;
        }

        String accountLedger = otapi.OT_API_Ledger_FinalizeResponse(serverID, nymID, accountID, responseLedger);

        if (accountLedger == null) {
            System.out.println("Return False - reason accountLedger from  OT_API_Ledger_FinalizeResponse - " + accountLedger);
            return false;
        }

        // -------------------------------------------------------------------
        // SEND THE PROCESS INBOX MESSAGE (to server.)
        // ----------------------------------------
        
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.PROCESS_INBOX, serverID, nymID, accountID, accountLedger);
        String      strResponse  = OTAPI_Func.SendTransaction(theRequest, "PROCESS_INBOX");
        
        if (null == strResponse)
        {
            System.out.println("IN processInbox: OTAPI_Func.SendTransaction(() failed. (I give up.) ");
            return false;
        }

        // -------------------------------------------------------------------

        // Since we just processed the inbox, let's grab the latest versions
        // of these files.
        // UPDATE: the caller already does this, so I think it's redundant.
        //
//      if (false == Utility.getIntermediaryFiles(serverID, nymID, accountID)) {
//          System.out.println("processInbox: Error: getIntermediaryFiles returned false.");
//          return false;
//      }
        // -----------------------------------
        
        return true;
    }

    
    
    
  
    
    

    
    
    // **********************************************************************
    
    
    public Map getInboxData(String accountID) throws InterruptedException {

        Map data = new HashMap();

        if (null == accountID)
            return data;
        
        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);

        // The caller now does this.
//      Utility.getInboxAccount(serverID, nymID, accountID);

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
        
        // The caller now does this.
//      Utility.getOutboxLowLevel(serverID, nymID, accountID);        
        
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

    public boolean depositCash(String serverID, String nymID, String accountID, String purse, boolean isToken) {

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
        // ----------------------------------------
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.DEPOSIT_CASH, serverID, nymID, accountID, purse);
        String      strResponse  = OTAPI_Func.SendTransaction(theRequest, "DEPOSIT_CASH"); // <========================
        
        if (null == strResponse)
        {
            System.out.println("IN depositCash: OTAPI_Func.SendTransaction(() failed. (I give up.) ");
            
            if (isToken) {
                System.out.println("IN depositCash, failed action for single token");
                String assetID = otapi.OT_API_GetAccountWallet_AssetTypeID(accountID);
                boolean importStatus = otapi.OT_API_Wallet_ImportPurse(serverID, assetID, nymID, purse) == 1 ? true : false;
                System.out.println("Since failure of depositCashPurse, OT_API_Wallet_ImportPurse called, status of import:" + importStatus);
                if (!importStatus) {
                    Utility.setOtDepositCash(purse);
                }
            }

            return false;
        }
        // ----------------------------------------
        return true;
    }

    public String withdrawVoucher(String serverID, String nymID, String accountID, String amount, String chequeMemo, String recepientNymID) {

        System.out.println("In withdrawVoucher serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);

        // ---------------------------------------------------
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.WITHDRAW_VOUCHER, serverID, nymID, accountID, recepientNymID, chequeMemo, amount);
        String      strResponse  = OTAPI_Func.SendTransaction(theRequest, "WITHDRAW_VOUCHER"); // <========================

        if (null == strResponse) {
            System.out.println("OTAPI_Func.SendTransaction() failed, in withdrawVoucher.");
            return null;
        }
        // ---------------------------------------------------------
        String ledger = otapi.OT_API_Message_GetLedger(strResponse);
        if (ledger == null) {
            System.out.println(" ledger is null, returned by OT_API_Message_GetLedger, serverResponseMessage passed as argument");
            return null;
        }
        String transaction = otapi.OT_API_Ledger_GetTransactionByIndex(serverID, nymID, accountID, ledger, 0);
        if (transaction == null) {
            System.out.println("withdrawVoucher: transaction is null, returned by OT_API_Ledger_GetTransactionByIndex, argument passed, index 0 and ledger :" + ledger);
            return null;
        }
        // ---------------------------------------------------------
        String output = otapi.OT_API_Transaction_GetVoucher(serverID, nymID, accountID, transaction);
        System.out.println("output returned by OT_API_Transaction_GetVoucher:" + output);

        return output;
    }

    public boolean withdrawCash(String serverID, String nymID, String accountID, String amount) {
        String serverResponseMessage = null;
        System.out.println("In withdrawCash serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);
        String assetID = otapi.OT_API_GetAccountWallet_AssetTypeID(accountID);
        
        // ----------------------------------------        
        String assetContract = otapi.OT_API_LoadAssetContract(assetID);        
        if (assetContract == null) {
            OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.GET_CONTRACT, serverID, nymID, assetID);
            String      strResponse  = OTAPI_Func.SendRequest(theRequest, "GET_CONTRACT");

            if (null == strResponse)
            {
                System.out.println("IN withdrawCash: OTAPI_Func.SendRequest(GET_CONTRACT) failed. (I give up.) (Unable to find asset contract.)");
                return false;
            }
            // ----------------------------------------
            assetContract = otapi.OT_API_LoadAssetContract(assetID);
            if (assetContract == null) {
                System.out.println("OT_API_LoadAssetContract returned null even after OT_API_getContract (Unable to find asset contract.)");
                return false;
            }
        }
        // ---------------------------------------------------------

        String mintFile = otapi.OT_API_LoadMint(serverID, assetID);
        if (mintFile == null) {
            // ----------------------------------------
            OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.GET_MINT, serverID, nymID, assetID);
            String      strResponse  = OTAPI_Func.SendRequest(theRequest, "GET_MINT");

            if (null == strResponse)
            {
                System.out.println("IN withdrawCash: OTAPI_Func.SendRequest(GET_MINT) failed. (I give up.) (Unable to find mint.)");
                return false;
            }
            // ----------------------------------------
            mintFile = otapi.OT_API_LoadMint(serverID, assetID);
            if (mintFile == null) {
                System.out.println("OT_API_LoadMint returned null even after OT_API_getContract (I give up.) (Unable to find mint.)");
                return false;
            }
        }        
        // ---------------------------------------------------
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.WITHDRAW_CASH, serverID, nymID, accountID, amount);
        String      strResponse  = OTAPI_Func.SendTransaction(theRequest, "WITHDRAW_CASH"); // <========================

        if (null == strResponse) {
            System.out.println("OTAPI_Func.SendTransaction() failed, in withdrawCash.");
            return false;
        }
        // --------------------------------------------------------------------------------------

        return true;
    }

    public boolean sendTransfer(String serverID, String nymID, String accountID, String amount, String note, String recepientAccountID) {

        boolean isSuccess = false;
        System.out.println("In sendTransfer serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);
       
        // ---------------------------------------------------
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.SEND_TRANSFER, serverID, nymID, accountID, recepientAccountID, amount, note);
        String      strResponse  = OTAPI_Func.SendTransaction(theRequest, "SEND_TRANSFER"); // <========================

        if (null == strResponse) {
            System.out.println("OTAPI_Func.SendTransaction() failed, in sendTransfer.");
            return false;
        }
        // -------------------------------------------------------------------------------------- 
        // No need to do anything like this, since the caller already re-downloads
        // the intermediary files after success.
//      if (false == Utility.getIntermediaryFiles(serverID, nymID, accountID))
//      {
//          System.out.println("sendTransfer: getIntermediaryFiles returned false. (I give up.)");
//          return null;
//      }

        return true;
    }

    public boolean depositCheque(String serverID, String nymID, String accountID, String cheque) {

        System.out.println("In depositCheque serverID:" + serverID + " nymID:" + nymID + " acount ID:" + accountID);
        
        // ---------------------------------------------------
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.DEPOSIT_CHEQUE, serverID, nymID, accountID, cheque);
        String      strResponse  = OTAPI_Func.SendTransaction(theRequest, "DEPOSIT_CHEQUE"); // <========================

        if (null == strResponse) {
            System.out.println("OTAPI_Func.SendTransaction() failed, in depositCheque.");
            return false;
        }
        // -------------------------------------------------------------------------------------- 

        return true;
    }

    public String writeCheque(String serverID, String nymID, String accountID, String validFrom, String validTo, String memo, String recepientNymID, String amount) {
        String cheque = "";
        System.out.println("In writeCheque serverID:" + serverID + " nymID:" + nymID);
        
        boolean bSure = true;	
        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
  	
            Utility.getTransactionNumbers(serverID, nymID);
            bSure = Utility.getTransactionNumbers(serverID, nymID);
        }
        if (!bSure || otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
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
