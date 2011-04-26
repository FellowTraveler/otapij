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


package com.wrapper.core;

import com.wrapper.core.dataobjects.CashPurseDetails;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CashPurseAccount extends Account {

    public CashPurseAccount() {
        type = "CashPurseAccount";
    }

    @Override
    public boolean createAccount() {
        return false;
    }

    @Override
    public boolean deleteAccount(String accountID) {
        return false;
    }

    @Override
    public void editAccount() {
    }

    public CashPurseDetails getCashPurseDetails(String cashPurseID) {

        Map gridData = new HashMap();

        String[] key = cashPurseID.split(":");

        String serverID = key[0];
        String assetID = key[1];
        String nymID = key[2];

        String cashPurse = otapi.OT_API_LoadPurse(serverID, assetID, nymID);

        if (cashPurse == null) {
            System.out.println("Purse is null");
            return null;
        }
        gridData = getGridData(serverID, assetID, nymID, cashPurse);
        CashPurseDetails cashDetails = new CashPurseDetails();
        cashDetails.setPurseGrid(gridData);
        cashDetails.setBalance(otapi.OT_API_Purse_GetTotalValue(serverID, assetID, cashPurse));
        cashDetails.setServerID(serverID);
        cashDetails.setAssetID(assetID);
        cashDetails.setNymID(nymID);
        cashDetails.setPurse(cashPurse);
        cashDetails.setServeName(otapi.OT_API_GetServer_Name(serverID));
        cashDetails.setAssetType(otapi.OT_API_GetAssetType_Name(assetID));
        if (nymID == null) {
            cashDetails.setNymName("");
        } else {
            cashDetails.setNymName(otapi.OT_API_GetNym_Name(nymID));
        }

        return cashDetails;
    }

    public void getCashPurseList(String assetID, String serverID, String nymID) {

        /*accountList.put(serverID + ":" + assetID + ":" + nymID, new String[]{"cashp", "100", type, serverID + ":" + assetID + ":" + nymID});
        if (true) {
        return;
        }*/

        int countServer = otapi.OT_API_GetServerCount();
        int countAsset = otapi.OT_API_GetAssetTypeCount();
        int countNyms = otapi.OT_API_GetNymCount();

        final String selectedServerID = serverID;
        final String selectedAssetID = assetID;
        final String selectedNymID = nymID;

        System.out.println("assetID:" + assetID);
        System.out.println("serverID:" + serverID + " countServer " + otapi.OT_API_GetServerCount());
        System.out.println("nymID:" + nymID);

        for (int j = 0; j < countServer; j++) {
            if ("ALL".equalsIgnoreCase(selectedServerID)) {
                serverID = otapi.OT_API_GetServer_ID(j);
                System.out.println("INNN LOOP serverID:" + serverID);
            } else {
                j = countServer + 1;
            }
            for (int i = 0; i < countAsset; i++) {
                if ("ALL".equalsIgnoreCase(selectedAssetID)) {
                    assetID = otapi.OT_API_GetAssetType_ID(i);
                } else {
                    i = countAsset + 1;
                }
                for (int k = 0; k < countNyms; k++) {
                    if ("ALL".equalsIgnoreCase(selectedNymID)) {
                        nymID = otapi.OT_API_GetNym_ID(k);
                    } else {
                        k = countNyms + 1;
                    }
                    //String cashPurse = otapi.OT_API_LoadPurse(serverID,assetID);
                    System.out.println("qqassetID:" + assetID);
                    System.out.println("aaserverID:" + serverID);
                    System.out.println("ccnymID:" + nymID);

                    if (assetID != null && serverID != null && nymID != null) {
                        String cashPurse = otapi.OT_API_LoadPurse(serverID, assetID, nymID);
                        System.out.println("cashPurse:" + cashPurse);
                        if (cashPurse != null) {
                            try {
                                key = serverID + ":" + assetID + ":" + nymID;
                                label = otapi.OT_API_GetAssetType_Name(assetID);
                                amount = otapi.OT_API_Purse_GetTotalValue(serverID, assetID, cashPurse);
                                accountList.put(key, new String[]{label, amount, type, key});

                            } catch (Exception nfe) {
                                nfe.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void loadAccount(String assetID, String serverID, String nymID) {
        System.out.println("In CASH load");
        getCashPurseList(assetID, serverID, nymID);
    }

    @Override
    public CashPurseDetails getAccountDetails(String accountID) {
        return getCashPurseDetails(accountID);
    }

    @Override
    public boolean editLabel(String accountID, String newLabel) {
        return false;
    }

    public boolean importCashPurse(String serverID, String nymID, String assetID, String userInput, boolean isPurse) {


        boolean isSuccess = true;
        System.out.println("importCashPurse, serverID:" + serverID + " nymID:" + nymID + " assetID:" + assetID);
        System.out.println("importCashPurse, userInput purse:" + userInput);
        if (!isPurse) {
            System.out.println("importCashPurse, isPurse:" + isPurse);
            String purse = otapi.OT_API_CreatePurse(serverID, assetID, nymID);
            if (purse == null) {
                System.out.println("OT_API_CreatePurse returned null");
                return false;
            }
            System.out.println("importCashPurse, OT_API_CreatePurse returne :" + purse);

            String newPurse = otapi.OT_API_Purse_Push(serverID, assetID, nymID, purse, userInput);
            if (newPurse == null) {
                System.out.println("OT_API_Purse_Push returned null");
                return false;
            }
            System.out.println("importCashPurse, OT_API_Purse_Push returne :" + newPurse);
            userInput = newPurse;
        }
        System.out.println("importCashPurse, Before calling OT_API_Wallet_ImportPurse, final purse:" + userInput);
        System.out.println("importCashPurse just before api , serverID:" + serverID + " nymID:" + nymID + " assetID:" + assetID);
        isSuccess = otapi.OT_API_Wallet_ImportPurse(serverID, assetID, nymID, userInput) == 1 ? true : false;
        return isSuccess;

    }

    private String processCashPurse(String serverID, String assetID, String nymID, String oldPurse, ArrayList selectedTokens, String recepientNymID) {

        String newPurse = null;
        //oldPurse = otapi.OT_API_LoadPurse(serverID, assetID, nymID);
        if (selectedTokens == null || selectedTokens.size() < 1) {
            System.out.println("IN Entire Cash Purse");
            newPurse = otapi.OT_API_CreatePurse(serverID, assetID, nymID);
            if (newPurse == null) {
                System.out.println("IN processCashPurse, OT_API_CreatePurse returned null");
                return null;
            }
            int count = otapi.OT_API_Purse_Count(serverID, assetID, oldPurse);
            String tempPurse = oldPurse;
            for (int i = 0; i < count; i++) {
                String token = otapi.OT_API_Purse_Peek(serverID, assetID, nymID, tempPurse);
                if (token == null) {
                    System.out.println("IN processCashPurse,OT_API_Purse_Peek returned null... Skipping this record");
                    continue;
                }

                String str = otapi.OT_API_Purse_Push(serverID, assetID, recepientNymID, newPurse, token);
                if (str == null) {
                    System.out.println("IN processCashPurse,OT_API_Purse_Push returned null... Skipping this record");
                    continue;
                }

                newPurse = str;
                String str1 = otapi.OT_API_Purse_Pop(serverID, assetID, nymID, tempPurse);

                if (str1 == null) {
                    System.out.println("IN processCashPurse,OT_API_Purse_Push returned null... Skipping this record");
                    continue;
                }
                tempPurse = str1;

            }
            otapi.OT_API_SavePurse(serverID, assetID, nymID, tempPurse);


        } else {
            System.out.println("Tokens in Cash Purse being processed");
            String newPurseSelectedTokens = otapi.OT_API_CreatePurse(serverID, assetID, nymID);
            if (newPurseSelectedTokens == null) {
                System.out.println("IN processCashPurse,1st OT_API_CreatePurse returned null");
                return null;
            }
            String newPurseUnSelectedTokens = otapi.OT_API_CreatePurse(serverID, assetID, nymID);
            if (newPurseUnSelectedTokens == null) {
                System.out.println("IN processCashPurse,2nd OT_API_CreatePurse returned null");
                return null;
            }

            int count = otapi.OT_API_Purse_Count(serverID, assetID, oldPurse);
            String tempPurse = oldPurse;
            for (int i = 0; i < count; i++) {
                String token = otapi.OT_API_Purse_Peek(serverID, assetID, nymID, tempPurse);

                if (token == null) {
                    System.out.println("IN processCashPurse,OT_API_Purse_Peek returned null... Skipping this record");
                    continue;
                }
                String tokenID = otapi.OT_API_Token_GetID(serverID, assetID, token);
                if (selectedTokens.contains(tokenID)) {

                    String str = otapi.OT_API_Purse_Push(serverID, assetID, nymID, newPurseSelectedTokens, token);
                    if (str == null) {
                        System.out.println("IN processCashPurse,OT_API_Purse_Push newPurseSelectedTokens returned null... Skipping this record");
                        continue;
                    }

                    newPurseSelectedTokens = str;
                } else {
                    String str = otapi.OT_API_Purse_Push(serverID, assetID, recepientNymID, newPurseUnSelectedTokens, token);
                    if (str == null) {
                        System.out.println("IN processCashPurse,OT_API_Purse_Push newPurseUnSelectedTokens returned null... Skipping this record");
                        continue;
                    }

                    newPurseUnSelectedTokens = str;
                }
                String str1 = otapi.OT_API_Purse_Pop(serverID, assetID, nymID, tempPurse);

                if (str1 == null) {
                    System.out.println("IN processCashPurse,OT_API_Purse_Pop returned null... Skipping this record");
                    continue;
                }
                tempPurse = str1;

            }

            if (otapi.OT_API_SavePurse(serverID, assetID, nymID, newPurseUnSelectedTokens) == 0) {
                return null;
            }
            newPurse = newPurseSelectedTokens;

        }

        return newPurse;
    }

    public String exportCashPurse(String serverID, String assetID, String nymID, String oldPurse, ArrayList selectedTokens, String recepientNymID, boolean isPasted) {
        System.out.println("exportCashPurse starts, selectedTokens:" + selectedTokens);
        if (isPasted) {
            String recepientPubKey = otapi.OT_API_LoadPubkey(recepientNymID);
            System.out.println("recepientPubKey:" + recepientPubKey);
            if (recepientPubKey == null) {
                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_checkUser(serverID, nymID, recepientNymID);
                try {
                    Thread.sleep(Configuration.getWaitTime());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                String serverResponse = otapi.OT_API_PopMessageBuffer();
                if (serverResponse != null) {
                    recepientPubKey = otapi.OT_API_LoadPubkey(recepientNymID);
                } else {
                    System.out.println("Error : Response fromserver " + serverResponse);

                    otapi.OT_API_getRequest(serverID, nymID);
                    try {
                        Thread.sleep(Configuration.getWaitTime());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    serverResponse = otapi.OT_API_PopMessageBuffer();
                    if (serverResponse == null) {
                        return null;
                    } else {
                        recepientPubKey = otapi.OT_API_LoadPubkey(recepientNymID);
                    }
                }
            }

        if (recepientPubKey == null) {
            System.out.println("recepientPubKey is null");
            return null;
        }
        }
        String newPurse = processCashPurse(serverID, assetID, nymID, oldPurse, selectedTokens, recepientNymID);
        return newPurse;
    }

    public boolean depositCashPurse(String serverID, String assetID, String nymID, String oldPurse, ArrayList selectedTokens, String accountID) throws InterruptedException {

        boolean isSuccess = false;
        Utility.setObj(null);
        System.out.println("depositCashPurse starts, selectedTokens:" + selectedTokens);
        String recepientNymID = otapi.OT_API_GetAccountWallet_NymID(accountID);
        String newPurse = processCashPurse(serverID, assetID, nymID, oldPurse, selectedTokens, recepientNymID);

        if (newPurse == null) {
            System.out.println("Before server OT_API_exchangePurse call, new Purse is emtpty.. returning false ");
            return false;
        }

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_notarizeDeposit(serverID, nymID, accountID, newPurse);

        Thread.sleep(Configuration.getWaitTime());

        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN depositCashPurse, server response:" + serverResponseMessage);
        if (serverResponseMessage == null) {
            getRequestNumber(serverID, nymID);
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null) {
                return false;
            } else {
                otapi.OT_API_notarizeDeposit(serverID, nymID, accountID, newPurse);
                Thread.sleep(Configuration.getWaitTime());
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                if (serverResponseMessage == null) {
                    return false;
                }
            }
        }
        isSuccess = (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false);
        System.out.println("depositCashPurse ends, status:" + isSuccess);
        if (!isSuccess) {
            boolean importStatus = otapi.OT_API_Wallet_ImportPurse(serverID, assetID, nymID, newPurse) == 1 ? true : false;
            System.out.println("Since failure of depositCashPurse, OT_API_Wallet_ImportPurse called, status of import:" + importStatus);
            if (!importStatus) {
                Utility.setObj(newPurse);
            }
        }
        return isSuccess;
    }

    public boolean exchangeCashPurse(String serverID, String assetID, String nymID, String oldPurse, ArrayList selectedTokens) throws InterruptedException {

        boolean isSuccess = false;
        Utility.setObj(null);
        System.out.println(" Cash Purse exchange starts, selectedTokens:" + selectedTokens);
        String newPurse = processCashPurse(serverID, assetID, nymID, oldPurse, selectedTokens, nymID);

        if (newPurse == null) {
            System.out.println("Before server OT_API_exchangePurse call, new Purse is emtpty.. returning false ");
            return false;
        }

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_exchangePurse(serverID, assetID, nymID, newPurse);

        Thread.sleep(Configuration.getWaitTime());

        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN Exchange cash, cashpurse, server response:" + serverResponseMessage);
        if (serverResponseMessage == null) {
            getRequestNumber(serverID, nymID);
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if (serverResponseMessage == null) {
                return false;
            } else {
                otapi.OT_API_exchangePurse(serverID, assetID, nymID, newPurse);
                Thread.sleep(Configuration.getWaitTime());
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                if (serverResponseMessage == null) {
                    return false;
                }
            }
        }
        isSuccess = (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false);
        System.out.println(" Cash Purse exchange ends, status:" + isSuccess);
        int oldPurseStatus = 0;
        if (!isSuccess) {
            oldPurseStatus = otapi.OT_API_SavePurse(serverID, assetID, nymID, oldPurse);
        }
        // Display new purse on screen...
        if (oldPurseStatus == 0) {
            Utility.setObj(newPurse);
        }
        return isSuccess;
    }

    public void getRequestNumber(String serverID, String nymID) throws InterruptedException {

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getRequest(serverID, nymID);
        Thread.sleep(Configuration.getWaitTime());
        System.out.println("Type:CashPurseAccount, IN getRequestNumber " + otapi.OT_API_PopMessageBuffer());

    }

    private Map getGridData(String serverID, String assetID, String nymID, String purse) {

        Map gridMap = new HashMap();

        /*gridMap.put("tokenk1", new String[]{"denomi","validfrom","validto","caidd","series"});
        gridMap.put("tokenk2", new String[]{"denomi1","avalidfrom2","validto3","bidd4","series5"});
        gridMap.put("tokenk3", new String[]{"denomi2","validfrom2","bvalidto3","cidd4","series5"});
        if(true)
        return gridMap;*/

        int count = otapi.OT_API_Purse_Count(serverID, assetID, purse);
        Date d = null;
        long seconds = 0;
        for (int i = 0; i < count; i++) {
            String row[] = new String[6];
            String token = otapi.OT_API_Purse_Peek(serverID, assetID, nymID, purse);
            if (token != null) {
                row[3] = otapi.OT_API_Token_GetID(serverID, assetID, token);
                row[0] = otapi.OT_API_Token_GetDenomination(serverID, assetID, token);
                row[4] = String.valueOf(otapi.OT_API_Token_GetSeries(serverID, assetID, token));
                // TODO: convert beow to date
                seconds = Long.parseLong(otapi.OT_API_Token_GetValidFrom(serverID, assetID, token));
                d = new Date(seconds * 1000);
                row[1] = String.valueOf(d);
                seconds = Long.parseLong(otapi.OT_API_Token_GetValidTo(serverID, assetID, token));
                d = new Date(seconds * 1000);
                row[2] = String.valueOf(d);
                d = null;
                gridMap.put(token, row);
            }
            purse = otapi.OT_API_Purse_Pop(serverID, assetID, nymID, purse);
        }
        return gridMap;
    }

    public static void main(String a[]) {
        String ll = "1300706232000";
        long milliseconds = Long.parseLong(ll);
        Date d = new Date(milliseconds);
        System.out.println(String.valueOf(d));

        Date d1 = new Date(System.currentTimeMillis());
        System.out.println(String.valueOf(d1) + " System.currentTimeMillis():" + System.currentTimeMillis());

    }

    public Map refreshGridData(String serverID, String assetID, String nymID) {
        String purse = otapi.OT_API_LoadPurse(serverID, assetID, nymID);
        if (purse == null) {
            return null;
        }
        return getGridData(serverID, assetID, nymID, purse);

    }
}
