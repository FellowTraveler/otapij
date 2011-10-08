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

import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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

        int count = otapi.OT_API_GetNymCount();
        for (int i = 0; i < count; i++) {
            String key = otapi.OT_API_GetNym_ID(i);
            String label = otapi.OT_API_GetNym_Name(key);
            mapNYMAccountList.put((i), new String[]{label, key});
        }

        return mapNYMAccountList;
    }

    public Map loadRegisteredNYM(String serverID) {

        Map mapNYMList = new HashMap();

        if ("ALL".equalsIgnoreCase(serverID)) {
            return mapNYMList;
        }

        int count = 0;
        for (int i = 0; i < otapi.OT_API_GetNymCount(); i++) {
            String key = otapi.OT_API_GetNym_ID(i);
            if (key == null) {
                continue;
            }
            if (otapi.OT_API_IsNym_RegisteredAtServer(key, serverID) == 1) {
                String label = otapi.OT_API_GetNym_Name(key);
                mapNYMList.put(count, new String[]{label, key});
                count++;
            }

        }

        return mapNYMList;
    }

    public String getRawFileData(String nymID) {
        return otapi.OT_API_GetNym_Stats(nymID);
    }

    public boolean deleteMail(String nymID, int index) {
        return otapi.OT_API_Nym_RemoveMailByIndex(nymID, index) == 1 ? true : false;

    }

    public boolean deleteOutboxMail(String nymID, int index) {
        return otapi.OT_API_Nym_RemoveOutmailByIndex(nymID, index) == 1 ? true : false;

    }

    public int registerNym(String serverID, String nymID) throws InterruptedException {

        System.out.println("IN registerNYm,serverID:" + serverID + " nymID:" + nymID);

        int status = 2;

        if (otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID) == 1) {
            status = 1;
        } else {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_createUserAccount(serverID, nymID);
            Utility.delay();
            String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN registerNYm " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getRequest(serverID, nymID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN getRequestNumber " + serverResponseMessage);

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    return status;
                } else {
                    otapi.OT_API_createUserAccount(serverID, nymID);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                }

            }

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("registerNYm OT_API_Message_GetSuccess returned false:");
                return status;
            }
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return status;
            }

            status = 0;
            System.out.println("registerNYm otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID):" + otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID));

        }
        return status;
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
            Utility.delay();
        } catch (InterruptedException ex) {
            Logger.getLogger(NYM.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("After wait");
        return loadNymBox(nymID);
    }

    public Map loadNymBox(String nymID) {

        System.out.println("loadNymBox starts");
        Map nymBox = new HashMap();

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

    public boolean sendMessage(String serverID, String nymID, String recepientNymID, String message) throws InterruptedException {

        System.out.println("Send message starts recepientNymID:" + recepientNymID + " nymID:" + nymID + " serverID:" + serverID);

        if (otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_createUserAccount(serverID, nymID);
            Utility.delay();
            String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN send message,OT_API_IsNym_RegisteredAtServer serverResponseMessage " + serverResponseMessage);

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
                Utility.delay();
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
                    Utility.delay();
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

    public boolean deleteWalletNym(String nymID) {
        System.out.println("deleteUnregisteredNym....nymID -- " + nymID);
        boolean deleteNym = otapi.OT_API_Wallet_CanRemoveNym(nymID) == 1 ? true : false;
        System.out.println("deleteNym:" + deleteNym);
        if (!deleteNym) {
            return deleteNym;
        }
        deleteNym = otapi.OT_API_Wallet_RemoveNym(nymID) == 1 ? true : false;
        System.out.println("Is Nym Deleted :" + deleteNym);
        return deleteNym;
    }

    public String importNYM(String name, String key) {
        String nymID = null;
        otapi.OT_API_Wallet_ImportNym(name, key);
        return nymID;
    }

    public Map registeredServers(String nymID) {
        Map registeredServers = new HashMap();
        Map serverMap = new Contract().loadServerContract();
        int count = 0;
        Set set = serverMap.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            if (serverMap.get(key) != null) {
                String serverID = ((String[]) serverMap.get(key))[1];
                if (otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID) == 1) {
                    registeredServers.put((count), new String[]{otapi.OT_API_GetServer_Name(serverID), serverID});
                    count++;
                }
            }
        }


        return registeredServers;
    }

    public boolean deleteNym(String nymID, String serverID, boolean deleteWalletNym) throws InterruptedException {

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_deleteUserAccount(serverID, nymID);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN deleteNym " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN deleteNym getRequestNumber " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            } else {
                otapi.OT_API_deleteUserAccount(serverID, nymID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            }

        }

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("deleteNym OT_API_Message_GetSuccess returned false:");
            return false;
        }
        if (deleteWalletNym) {
            return deleteWalletNym(nymID);
        } else {
            return true;
        }
    }
}
