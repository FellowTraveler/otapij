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
package com.moneychanger.core;

import org.opentransactions.jni.core.otapiJNI;
import com.moneychanger.core.util.Utility;
import com.moneychanger.core.util.OTAPI_Func;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vicky C
 */
public class NYM {

    HashMap mapNYMAccountList = new HashMap();

    public Map loadNYM() {

        mapNYMAccountList.clear();

        int count = otapiJNI.OTAPI_Basic_GetNymCount();
        for (int i = 0; i < count; i++) {
            String key = otapiJNI.OTAPI_Basic_GetNym_ID(i);
            String label = otapiJNI.OTAPI_Basic_GetNym_Name(key);
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
        for (int i = 0; i < otapiJNI.OTAPI_Basic_GetNymCount(); i++) {
            String key = otapiJNI.OTAPI_Basic_GetNym_ID(i);
            if (!Utility.VerifyStringVal(key)) {
                continue;
            }
            if (otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(key, serverID)) {
                String label = otapiJNI.OTAPI_Basic_GetNym_Name(key);
                mapNYMList.put(count, new String[]{label, key});
                count++;
            }

        }

        return mapNYMList;
    }

    public String registeredNYM(String serverID) {

        if(!Utility.VerifyStringVal(serverID))
            return null;

        String nymID = null;

        for (int i = 0; i < otapiJNI.OTAPI_Basic_GetNymCount(); i++) {
            String key = otapiJNI.OTAPI_Basic_GetNym_ID(i);
            if (!Utility.VerifyStringVal(key)) {
                continue;
            }
            if (otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(key, serverID)) {
                nymID = key;
                break;
            }

        }

        return nymID;
    }

    public String getRawFileData(String nymID) {
        return otapiJNI.OTAPI_Basic_GetNym_Stats(nymID);
    }

    public boolean deleteMail(String nymID, int index) {
        return otapiJNI.OTAPI_Basic_Nym_RemoveMailByIndex(nymID, index);

    }

    public boolean deleteOutboxMail(String nymID, int index) {
        return otapiJNI.OTAPI_Basic_Nym_RemoveOutmailByIndex(nymID, index);

    }

    public int registerNym(String serverID, String nymID) throws InterruptedException {

        System.out.println("IN registerNym, serverID:" + serverID + " nymID:" + nymID);

        int status = 2;

        if (otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, serverID)) {
            status = 1;
        } else {
            OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.CREATE_USER_ACCT, serverID, nymID);
            String      strResponse  = OTAPI_Func.SendRequest(theRequest, "CREATE_USER_ACCT");

            if (!Utility.VerifyStringVal(strResponse))
            {
                System.out.println("IN registerNym: OTAPI_Func.SendRequest() failed. (I give up.) ");
                return status;
            }
            // -----------------------------------------
            else // Since we just registered, let's sync the request number 
                 // (for the first time.)
            {
                if (Utility.getRequestNumber(serverID, nymID) < 1)
                {
                    System.out.println("IN registerNym: Utility.getRequestNumber() failed. (The first one ever for this Nym on this server.) ");
                    return status;
                }
            }

            status = 0;
            System.out.println("registerNYm otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, serverID):" + 
                    otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, serverID));

        }
        return status;
    }

    public Map downloadNymBox(String nymID) {
        System.out.println("downloadNymBox starts, nymID:" + nymID);

        int countServer = otapiJNI.OTAPI_Basic_GetServerCount();
        for (int i = 0; i < countServer; i++) {
            String serverID = otapiJNI.OTAPI_Basic_GetServer_ID(i);
            System.out.println("IN FOR,i=" + i + " server id:" + serverID);
            if (Utility.VerifyStringVal(serverID)) {
                if (otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, serverID)) 
                {
                    System.out.println("nym is registered");
                    
    // Returns:
    //   -1 ERROR.
    //    0 Nymbox was empty -- nothing done. (bWasMsgSent = false)
    //    0 Transaction status == server reply received (bWasMsgSent = true), but 
    //      the server reply has status == FAILED. (All harvesting was subsequently successful for processNymbox).
    //    1 If the ProcessNymbox Transaction status (from the server reply) is SUCCESS,
    //      then this function returns 1.
    //   >1 If the ProcessNymbox Transaction status (from the server reply) is >1, then this function returns the 
    //      REQUEST NUMBER from when it was originally sent. (Harvesting was NOT performed, which is why the request
    //      number is being returned, so the caller can choose what to do next.)
                    
                    Utility.OTBool bWasMsgSent = new Utility.OTBool (false);
                    Utility.getAndProcessNymbox(serverID, nymID, bWasMsgSent, true); // bForceDownload=true
                }
            }
        }
        System.out.println("Before wait");
        
        Utility.delay();

        System.out.println("After wait");
        return loadNymBox(nymID);
    }

    public Map loadNymBox(String nymID) {

        System.out.println("loadNymBox starts");
        Map nymBox = new HashMap();

        int count = otapiJNI.OTAPI_Basic_GetNym_MailCount(nymID);

        for (int i = 0; i < count; i++) {
            String message = otapiJNI.OTAPI_Basic_GetNym_MailContentsByIndex(nymID, i);
            String[] rowData = new String[8];

            String subject = "";
            String messageBody = "";
            if (Utility.VerifyStringVal(message) && (message.startsWith("Subject") || message.startsWith("subject")) && message.contains("\n")) {
                subject = message.substring(0, message.indexOf("\n"));
                messageBody = message.substring(subject.length());
                subject = subject.substring(8);
            } else {
                messageBody = message;
            }
            rowData[0] = subject;
            String senderNym = otapiJNI.OTAPI_Basic_GetNym_MailSenderIDByIndex(nymID, i);
            String nymName = null;
            if (Utility.VerifyStringVal(senderNym))
                nymName = otapiJNI.OTAPI_Basic_GetNym_Name(senderNym);
            rowData[1] = !Utility.VerifyStringVal(nymName) ? !Utility.VerifyStringVal(senderNym) ? "" : senderNym : nymName;
            String serverID = otapiJNI.OTAPI_Basic_GetNym_MailServerIDByIndex(nymID, i);
            String serverName = null;
            if (Utility.VerifyStringVal(serverID)) {
                serverName = otapiJNI.OTAPI_Basic_GetServer_Name(serverID);
            }
            rowData[2] = !Utility.VerifyStringVal(serverName) ? !Utility.VerifyStringVal(serverID) ? "" : serverID : serverName;
            String isVerified = otapiJNI.OTAPI_Basic_Nym_VerifyMailByIndex(nymID, i) ? "true" : "false";
            // This is the key
            rowData[3] = String.valueOf(i);
            rowData[6] = !Utility.VerifyStringVal(messageBody) ? "" : messageBody;
            rowData[7] = !Utility.VerifyStringVal(isVerified)  ? "" : isVerified;
            rowData[4] = senderNym;
            rowData[5] = serverID;
            nymBox.put(rowData[3], rowData);
        }
        System.out.println("loadNymBox ends");
        return nymBox;
    }

    public Map loadNymOutBox(String nymID) {


        Map nymOutBox = new HashMap();

        int count = otapiJNI.OTAPI_Basic_GetNym_OutmailCount(nymID);

        for (int i = 0; i < count; i++) {
            String message = otapiJNI.OTAPI_Basic_GetNym_OutmailContentsByIndex(nymID, i);

            String[] rowData = new String[8];

            String subject = "";
            String messageBody = "";
            if (Utility.VerifyStringVal(message) && (message.startsWith("Subject") || message.startsWith("subject")) && message.contains("\n")) {
                subject = message.substring(0, message.indexOf("\n"));
                messageBody = message.substring(subject.length());
                subject = subject.substring(8);
            } else {
                messageBody = message;
            }
            rowData[0] = subject;
            String recepientNymID = otapiJNI.OTAPI_Basic_GetNym_OutmailRecipientIDByIndex(nymID, i);
            String nymName = null;
            if (Utility.VerifyStringVal(recepientNymID)) {
                nymName = otapiJNI.OTAPI_Basic_GetNym_Name(recepientNymID);
            }
            rowData[1] = !Utility.VerifyStringVal(nymName) ? !Utility.VerifyStringVal(recepientNymID) ? "" : recepientNymID : nymName;
            String serverID = otapiJNI.OTAPI_Basic_GetNym_OutmailServerIDByIndex(nymID, i);
            String serverName = null;
            if (Utility.VerifyStringVal(serverID)) {
                serverName = otapiJNI.OTAPI_Basic_GetServer_Name(serverID);
            }
            rowData[2] = !Utility.VerifyStringVal(serverName) ? !Utility.VerifyStringVal(serverID) ? "" : serverID : serverName;
            String isVerified = otapiJNI.OTAPI_Basic_Nym_VerifyOutmailByIndex(nymID, i) ? "true" : "false";
            // This is the key
            rowData[3] = String.valueOf(i);
            rowData[6] = !Utility.VerifyStringVal(messageBody) ? "" : messageBody;
            rowData[7] = !Utility.VerifyStringVal(isVerified) ? "" : isVerified;
            rowData[4] = recepientNymID;
            rowData[5] = serverID;
            nymOutBox.put(rowData[3], rowData);
        }
        return nymOutBox;
    }

    public boolean editLabel(String nymID, String signNymID, String nymName) {
        return otapiJNI.OTAPI_Basic_SetNym_Name(nymID, signNymID, nymName);
    }

    public String createNym(String nymName) {
        String nymID = otapiJNI.OTAPI_Basic_CreateNym(1024); // TODO:  Dropdown:  1024, 2048, 4096, 8192
        if (Utility.VerifyStringVal(nymID)) {
            otapiJNI.OTAPI_Basic_SetNym_Name(nymID, nymID, nymName);
        }
        return nymID;
    }

    public boolean sendMessage(String serverID, String nymID, String recipientNymID, String message) throws InterruptedException {

        System.out.println("Send message starts recepientNymID:" + recipientNymID + " nymID:" + nymID + " serverID:" + serverID);

        // If the Nym's not registered at the server, we do that first...
        //
        if (!otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, serverID)) {

            OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.CREATE_USER_ACCT, serverID, nymID);
            String      strResponse  = OTAPI_Func.SendRequest(theRequest, "CREATE_USER_ACCT");

            if (!Utility.VerifyStringVal(strResponse))
            {
                System.out.println("IN sendMessage: OTAPI_Func.SendRequest() failed. (I give up.) ");
                return false;
            }
        }
        // -----------------------------------------------
        // Okay the Nym is definitely registered at the server        
        
        String recipientPubKey = otapiJNI.OTAPI_Basic_LoadPubkey(recipientNymID);
        System.out.println("recepientPubKey:" + recipientPubKey);
        // Download the recipient's pubkey since we don't already have it.
        if (!Utility.VerifyStringVal(recipientPubKey)) {
            
            // ----------------------------------------------------------
            OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.CHECK_USER, serverID, nymID, recipientNymID);
            String      strResponse  = OTAPI_Func.SendRequest(theRequest, "CHECK_USER");

            if (!Utility.VerifyStringVal(strResponse))
            {
                System.out.println("IN sendMessage: OTAPI_Func.SendRequest() failed. (I give up.) ");
                return false;
            }
            // ----------------------------------------------------------
            recipientPubKey = otapiJNI.OTAPI_Basic_LoadPubkey(recipientNymID);
        }
        if (!Utility.VerifyStringVal(recipientPubKey)) {
            System.out.println("recepientPubKey is null");
            return false;
        }
        System.out.println("Just before api call recepientNymID:" + recipientNymID + " nymID:" + nymID + " serverID:" + serverID);
        // --------------------------------------------------------
        
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.SEND_USER_MESSAGE, serverID, nymID, recipientNymID, recipientPubKey, message);
        String      strResponse  = OTAPI_Func.SendRequest(theRequest, "SEND_USER_MESSAGE");

        if (!Utility.VerifyStringVal(strResponse))
        {
            System.out.println("IN sendMessage: OTAPI_Func.SendRequest() failed. (I give up.) ");
            return false;
        }
        
        return true;
    }

    public boolean deleteWalletNym(String nymID) {
        System.out.println("deleteUnregisteredNym....nymID -- " + nymID);
        boolean deleteNym = otapiJNI.OTAPI_Basic_Wallet_CanRemoveNym(nymID);
        System.out.println("deleteNym:" + deleteNym);
        if (!deleteNym) {
            return deleteNym;
        }
        deleteNym = otapiJNI.OTAPI_Basic_Wallet_RemoveNym(nymID);
        System.out.println("Is Nym Deleted :" + deleteNym);
        return deleteNym;
    }

    public String importCert(String name, String key) {
        // OT now supports ImportNym **AND** ImportCert,
        // So we need to update Moneychanger to support both as well.
        // This is just a hack in the meantime.
        //
        String nymID = otapiJNI.OTAPI_Basic_Wallet_ImportCert(name, key);
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
                if (otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, serverID)) {
                    registeredServers.put((count), new String[]{otapiJNI.OTAPI_Basic_GetServer_Name(serverID), serverID});
                    count++;
                }
            }
        }


        return registeredServers;
    }

    public boolean deleteNym(String nymID, String serverID, boolean deleteWalletNym) {

        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.DELETE_USER_ACCT, serverID, nymID);
        String      strResponse  = OTAPI_Func.SendRequest(theRequest, "DELETE_USER_ACCT");

        if (!Utility.VerifyStringVal(strResponse))
        {
            System.out.println("IN deleteNym: OTAPI_Func.SendRequest() failed. (I give up.) ");
            return false;
        }
        
        if (deleteWalletNym) {
            return deleteWalletNym(nymID);
        }
        
        return true;
    }
}
