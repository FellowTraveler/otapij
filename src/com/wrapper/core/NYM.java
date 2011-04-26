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

import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vicky C
 */
public class NYM {

    HashMap mapNYMAccountList = new HashMap();

    public Map loadNYM() {

        mapNYMAccountList.clear();
        /*if(true){
        mapNYMAccountList.put("nymID1", "NYMlabel1");
        mapNYMAccountList.put("nymID2", "NYMlabel2");
        mapNYMAccountList.put("nymID3", "NYMlabel3");
        return mapNYMAccountList;
        }*/
        int count = otapi.OT_API_GetNymCount();
        for (int i = 0; i < count; i++) {
            String key = otapi.OT_API_GetNym_ID(i);
            String label = otapi.OT_API_GetNym_Name(key);
            //mapNYMAccountList.put(key, label);
            mapNYMAccountList.put((i), new String[]{label, key});
        }

        return mapNYMAccountList;
    }

    public String getRawFileData(String nymID) {
        //return "RRRRRRaqq";
        return otapi.OT_API_GetNym_Stats(nymID);
    }

    public boolean deleteMail(String nymID, int index) {
        return otapi.OT_API_Nym_RemoveMailByIndex(nymID, index) == 1 ? true : false;

    }

    public boolean deleteOutboxMail(String nymID, int index) {
        return otapi.OT_API_Nym_RemoveOutmailByIndex(nymID, index) == 1 ? true : false;

    }

    public Map downloadNymBox(String nymID) {
        System.out.println("downloadNymBox starts, nymID:" + nymID);

        int countServer = otapi.OT_API_GetServerCount();
        for (int i = 0; i < countServer; i++) {
            String serverID = otapi.OT_API_GetServer_ID(i);
            System.out.println("IN FOR,i=" + i + " server id:" + serverID);
            if (serverID != null) {
                if (otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID) == 1) {
                    System.out.println("nym is registered");
                    otapi.OT_API_getNymbox(serverID, nymID);
                }
            }
        }
        System.out.println("Before wait");
        try {
            Thread.sleep(Configuration.getWaitTime());
        } catch (InterruptedException ex) {
            Logger.getLogger(NYM.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("After wait");
        return loadNymBox(nymID);
    }

    public Map loadNymBox(String nymID) {

        System.out.println("loadNymBox starts");
        Map nymBox = new HashMap();


        /*nymBox.put("0", new String[]{"Subject iii","Nym","Server","0","BIDYYYYYYYYYYY","true","dsffdgdfg","fffffffff"});
        nymBox.put("1", new String[]{"Subject iii","Nym","Server","1","BIDYYYYYYYYYYY","true","dfghf","fgfghgf"});
        nymBox.put("2", new String[]{"Subject iii","Nym","Server","2","BIDYYYYYYYYYYY","true","nnnn","fffffffff"});

        if(true)
        return nymBox;*/
        int count = otapi.OT_API_GetNym_MailCount(nymID);

        for (int i = 0; i < count; i++) {
            String message = otapi.OT_API_GetNym_MailContentsByIndex(nymID, i);
            String[] rowData = new String[8];

            String subject = "";
            String messageBody = "";
            if (message != null && (message.startsWith("Subject") || message.startsWith("subject")) && message.contains("\n")) {
                subject = message.substring(0, message.indexOf("\n"));
                messageBody = message.substring(subject.length());
                subject = subject.substring(8);
            } else {
                messageBody = message;
            }
            rowData[0] = subject;
            String senderNym = otapi.OT_API_GetNym_MailSenderIDByIndex(nymID, i);
            String nymName = null;
            if (senderNym != null);
            nymName = otapi.OT_API_GetNym_Name(senderNym);
            rowData[1] = nymName == null ? senderNym == null ? "" : senderNym : nymName;
            String serverID = otapi.OT_API_GetNym_MailServerIDByIndex(nymID, i);
            String serverName = null;
            if (serverID != null) {
                serverName = otapi.OT_API_GetServer_Name(serverID);
            }
            rowData[2] = serverName == null ? serverID == null ? "" : serverID : serverName;
            String isVerified = otapi.OT_API_Nym_VerifyMailByIndex(nymID, i) == 1 ? "true" : "false";
            // This is the key
            rowData[3] = String.valueOf(i);
            rowData[6] = messageBody == null ? "" : messageBody;
            rowData[7] = isVerified == null ? "" : isVerified;
            rowData[4] = senderNym;
            rowData[5] = serverID;
            nymBox.put(rowData[3], rowData);
        }
        System.out.println("loadNymBox ends");
        return nymBox;
    }

    public Map loadNymOutBox(String nymID) {


        Map nymOutBox = new HashMap();

        /*nymOutBox.put("0", new String[]{"Subject iii","Nym","Server","0","BIDYYYYYYYYYYY","true","dsffdgdfg","fffffffff"});
        nymOutBox.put("1", new String[]{"Subject iii","Nym","Server","1","BIDYYYYYYYYYYY","true","dfghf","fgfghgf"});
        nymOutBox.put("2", new String[]{"Subject iii","Nym","Server","2","BIDYYYYYYYYYYY","true","nnnn","fffffffff"});
        if(true)
        return nymOutBox;*/
        int count = otapi.OT_API_GetNym_OutmailCount(nymID);

        for (int i = 0; i < count; i++) {
            String message = otapi.OT_API_GetNym_OutmailContentsByIndex(nymID, i);

            String[] rowData = new String[8];

            String subject = "";
            String messageBody = "";
            if (message != null && (message.startsWith("Subject") || message.startsWith("subject")) && message.contains("\n")) {
                subject = message.substring(0, message.indexOf("\n"));
                messageBody = message.substring(subject.length());
                subject = subject.substring(8);
            } else {
                messageBody = message;
            }
            rowData[0] = subject;
            String recepientNymID = otapi.OT_API_GetNym_OutmailRecipientIDByIndex(nymID, i);
            String nymName = null;
            if (recepientNymID != null) {
                nymName = otapi.OT_API_GetNym_Name(recepientNymID);
            }
            rowData[1] = nymName == null ? recepientNymID == null ? "" : recepientNymID : nymName;
            String serverID = otapi.OT_API_GetNym_OutmailServerIDByIndex(nymID, i);
            String serverName = null;
            if (serverID != null) {
                serverName = otapi.OT_API_GetServer_Name(serverID);
            }
            rowData[2] = serverName == null ? serverID == null ? "" : serverID : serverName;
            String isVerified = otapi.OT_API_Nym_VerifyOutmailByIndex(nymID, i) == 1 ? "true" : "false";
            // This is the key
            rowData[3] = String.valueOf(i);
            rowData[6] = messageBody == null ? "" : messageBody;
            rowData[7] = isVerified == null ? "" : isVerified;
            rowData[4] = recepientNymID;
            rowData[5] = serverID;
            nymOutBox.put(rowData[3], rowData);
        }
        return nymOutBox;
    }

    public boolean editLabel(String nymID, String signNymID, String nymName) {
        return otapi.OT_API_SetNym_Name(nymID, signNymID, nymName) == 1 ? true : false;
    }

    public String createNym(String nymName) {
        String nymID = otapi.OT_API_CreateNym();
        if (nymID != null) {
            otapi.OT_API_SetNym_Name(nymID, nymID, nymName);
        }
        return nymID;
    }

    public void registerNym(String serverID, String nymID) {
        otapi.OT_API_createUserAccount(serverID, nymID);
    }

    public boolean sendMessage(String serverID, String nymID, String recepientNymID, String message) throws InterruptedException {

        System.out.println("Send message starts recepientNymID:" + recepientNymID + " nymID:" + nymID + " serverID:" + serverID);

        if (otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID) == 0) {

                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_createUserAccount(serverID, nymID);
                Thread.sleep(Configuration.getWaitTime());
                String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN send message,OT_API_IsNym_RegisteredAtServer serverResponseMessage " + serverResponseMessage);

                if (serverResponseMessage == null) {
                    return false;
               } else if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    return false;
                } else if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1) {
                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_getRequest(serverID, nymID);
                    Thread.sleep(Configuration.getWaitTime());
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    if (serverResponseMessage == null) {
                        return false;
                    }
                }
            }

        String recepientPubKey = otapi.OT_API_LoadPubkey(recepientNymID);
        System.out.println("recepientPubKey:" + recepientPubKey);
        if (recepientPubKey == null) {
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_checkUser(serverID, nymID, recepientNymID);
            try {
                Thread.sleep(Configuration.getWaitTime());
            } catch (InterruptedException ex) {
                Logger.getLogger(NYM.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(NYM.class.getName()).log(Level.SEVERE, null, ex);
                }
                serverResponse = otapi.OT_API_PopMessageBuffer();
                if (serverResponse == null) {
                    return false;
                } else {
                    recepientPubKey = otapi.OT_API_LoadPubkey(recepientNymID);
                }
            }
        }
        if (recepientPubKey == null) {
            System.out.println("recepientPubKey is null");
            return false;
        }
        System.out.println("Just before api call recepientNymID:" + recepientNymID + " nymID:" + nymID + " serverID:" + serverID);
        otapi.OT_API_sendUserMessage(serverID, nymID, recepientNymID, recepientPubKey, message);
        return true;
    }

    public boolean deleteNym(String nymID) {
        System.out.println("deleteNym....nymID -- " + nymID);
        boolean deleteNym = otapi.OT_API_Wallet_CanRemoveNym(nymID) == 1 ? true : false;
        System.out.println("deleteNym:" + deleteNym);
        if (!deleteNym) {
            return deleteNym;
        }
        deleteNym = otapi.OT_API_Wallet_RemoveNym(nymID) == 1 ? true : false;
        System.out.println("Is Nym Deleted :" + deleteNym);
        return deleteNym;
    }
}
