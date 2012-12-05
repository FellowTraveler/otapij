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

package com.moneychanger.core;

import org.opentransactions.jni.core.otapiJNI;
import com.moneychanger.core.util.OTAPI_Func;
import com.moneychanger.core.util.Utility;
import java.util.HashMap;
import java.util.Map;

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

        int count = otapiJNI.OTAPI_Basic_GetServerCount();
        for (int i = 0; i < count; i++) {
            String key = otapiJNI.OTAPI_Basic_GetServer_ID(i);
            String label = otapiJNI.OTAPI_Basic_GetServer_Name(key);
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

        int count = otapiJNI.OTAPI_Basic_GetAssetTypeCount();
        for (int i = 0; i < count; i++) {
            String key = otapiJNI.OTAPI_Basic_GetAssetType_ID(i);
            String label = otapiJNI.OTAPI_Basic_GetAssetType_Name(key);
            //mapAssetContractAccountList.put(key, label);
            mapAssetContractAccountList.put((i), new String[]{label, key});
        }

        return mapAssetContractAccountList;
    }

    public void createServerContract(String fileText) {
        otapiJNI.OTAPI_Basic_AddServerContract(fileText);
    }

    public void createAssetContract(String fileText) {
        otapiJNI.OTAPI_Basic_AddAssetContract(fileText);
    }

    public String getRawServerFileData(String serverID) {
        return otapiJNI.OTAPI_Basic_LoadServerContract(serverID);
    }

    public String getRawAssetFileData(String assetID) {
        return otapiJNI.OTAPI_Basic_LoadAssetContract(assetID);
    }

    public boolean editLabel(String contractID, String contractName, String newLabel) {
        if ("Asset".equalsIgnoreCase(contractName)) {
            return otapiJNI.OTAPI_Basic_SetAssetType_Name(contractID, newLabel);
        } else if ("Server".equalsIgnoreCase(contractName)) {
            return otapiJNI.OTAPI_Basic_SetServer_Name(contractID, newLabel);
        }
        return false;
    }

    public boolean deleteServerContract(String serverID) {
        boolean deleteServerContract = otapiJNI.OTAPI_Basic_Wallet_CanRemoveServer(serverID);
        if (!deleteServerContract) {
            return deleteServerContract;
        }
        deleteServerContract = otapiJNI.OTAPI_Basic_Wallet_RemoveServer(serverID);
        return deleteServerContract;
    }

    public boolean deleteAssetContract(String assetID) {
        boolean deleteAssetContract = otapiJNI.OTAPI_Basic_Wallet_CanRemoveAssetType(assetID);
        if (!deleteAssetContract) {
            return deleteAssetContract;
        }
        deleteAssetContract = otapiJNI.OTAPI_Basic_Wallet_RemoveAssetType(assetID);
        return deleteAssetContract;
    }

    public boolean issueAssetType(String serverID, String nymID, String contract) throws InterruptedException {

        if (!otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, serverID)) {

            OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.CREATE_USER_ACCT, serverID, nymID);
            String      strResponse  = OTAPI_Func.SendRequest(theRequest, "CREATE_USER_ACCT");

            if (!Utility.VerifyStringVal(strResponse))
            {
                System.out.println("IN issueAssetType: OTAPI_Func.SendRequest(() failed. (I give up.) ");
                return false;
            }
        }
        // -----------------------------------------------
 
        OTAPI_Func  theRequest   = new OTAPI_Func(OTAPI_Func.FT.ISSUE_ASSET_TYPE, serverID, nymID, contract);
        String      strResponse  = OTAPI_Func.SendRequest(theRequest, "ISSUE_ASSET_TYPE");

        if (!Utility.VerifyStringVal(strResponse))
        {
            System.out.println("IN issueAssetType: OTAPI_Func.SendRequest(() failed. (I give up.) ");
            return false;
        }
        
        System.out.println("IN issueAssetType: Success. ");
        return true;
    }
}



