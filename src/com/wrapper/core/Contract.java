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

import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Contract {

    HashMap mapServContractAccountList = new HashMap();
    HashMap mapAssetContractAccountList = new HashMap();

    public Map loadServerContract() {

        mapServContractAccountList.clear();

        /* if(true){
        mapServContractAccountList.put("serverID1", "serverlabel1");
        mapServContractAccountList.put("serverID2", "serverlabel2");
        mapServContractAccountList.put("serverID3", "serverlabel3");
        return mapServContractAccountList;
        }*/

        int count = otapi.OT_API_GetServerCount();
        for (int i = 0; i < count; i++) {
            String key = otapi.OT_API_GetServer_ID(i);
            String label = otapi.OT_API_GetServer_Name(key);
            //mapServContractAccountList.put(key, label);
            mapServContractAccountList.put((i), new String[]{label, key});
        }

        return mapServContractAccountList;
    }

    public Map loadAssetContract() {

        mapAssetContractAccountList.clear();

        /*if(true){
        mapAssetContractAccountList.put("assetID1", "assetlabel1");
        mapAssetContractAccountList.put("assetID2", "assetlabel2");
        mapAssetContractAccountList.put("assetID3", "assetlabel3");
        return mapAssetContractAccountList;
        }*/

        int count = otapi.OT_API_GetAssetTypeCount();
        for (int i = 0; i < count; i++) {
            String key = otapi.OT_API_GetAssetType_ID(i);
            String label = otapi.OT_API_GetAssetType_Name(key);
            //mapAssetContractAccountList.put(key, label);
            mapAssetContractAccountList.put((i), new String[]{label, key});
        }

        return mapAssetContractAccountList;
    }

    public void createServerContract(String fileText) {
        otapi.OT_API_AddServerContract(fileText);
    }

    public void createAssetContract(String fileText) {
        otapi.OT_API_AddAssetContract(fileText);
    }

    public String getRawServerFileData(String serverID) {
        return otapi.OT_API_LoadServerContract(serverID);
    }

    public String getRawAssetFileData(String assetID) {
        return otapi.OT_API_LoadAssetContract(assetID);
    }

    public boolean editLabel(String contractID, String contractName, String newLabel) {
        if ("Asset".equalsIgnoreCase(contractName)) {
            return otapi.OT_API_SetAssetType_Name(contractID, newLabel) == 1 ? true : false;
        } else if ("Server".equalsIgnoreCase(contractName)) {
            return otapi.OT_API_SetServer_Name(contractID, newLabel) == 1 ? true : false;
        }
        return false;
    }

    public boolean deleteServerContract(String serverID) {
        boolean deleteServerContract = otapi.OT_API_Wallet_CanRemoveServer(serverID) == 1 ? true : false;
        if (!deleteServerContract) {
            return deleteServerContract;
        }
        deleteServerContract = otapi.OT_API_Wallet_RemoveServer(serverID) == 1 ? true : false;
        return deleteServerContract;
    }

    public boolean deleteAssetContract(String assetID) {
        boolean deleteAssetContract = otapi.OT_API_Wallet_CanRemoveAssetType(assetID) == 1 ? true : false;
        if (!deleteAssetContract) {
            return deleteAssetContract;
        }
        deleteAssetContract = otapi.OT_API_Wallet_RemoveAssetType(assetID) == 1 ? true : false;
        return deleteAssetContract;
    }

    public boolean issueAssetType(String serverID, String nymID, String contract) throws InterruptedException {

        if (otapi.OT_API_IsNym_RegisteredAtServer(nymID, serverID) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_createUserAccount(serverID, nymID);
            Thread.sleep(Configuration.getWaitTime());
            String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN issueAssetType,OT_API_IsNym_RegisteredAtServer serverResponseMessage " + serverResponseMessage);
            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            } else if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return false;
            } else if (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1) {

                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getRequest(serverID, nymID);
                Thread.sleep(Configuration.getWaitTime());
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    System.out.println("IN issueAssetType, during nym registration, getrequestNumber failed . serverResponseMessage - " + serverResponseMessage);
                    return false;
                }
            }
        }


        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_issueAssetType(serverID, nymID, contract);

        Thread.sleep(Configuration.getWaitTime());

        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN issueAssetType " + serverResponseMessage);
        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Thread.sleep(Configuration.getWaitTime());
            otapi.OT_API_issueAssetType(serverID, nymID, contract);
            Thread.sleep(Configuration.getWaitTime());
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            if ((serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0)) {
                return false;
            }
        }

        return (otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 1 ? true : false);

    }
}
