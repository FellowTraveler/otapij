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
package com.wrapper.core;

import com.wrapper.core.jni.Storable;
import com.wrapper.core.jni.StoredObjectType;
import com.wrapper.core.jni.StringMap;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.OTAPI_Func;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vicky C
 */
public class Basket {

    public static String getBaseName() {
        return baseName;
    }

    public static void setBaseName(String baseName) {
        Basket.baseName = baseName;
    }

    public static String getBaseValue() {
        return baseValue;
    }

    public static void setBaseValue(String baseValue) {
        Basket.baseValue = baseValue;
    }

    public static List getSubCurrency() {
        return subCurrency;
    }

    public static void resetSubCurrency() {
        subCurrency.clear();
    }

    public static void addSubCurrency(String[] subCurrencyValue) {
        Basket.subCurrency.add(subCurrencyValue);
    }
    private static String baseValue = null;
    private static String baseName = null;
    private static List subCurrency = new ArrayList();

    public static List getBasketList(String serverID, String nymID) {

        List basketList = new ArrayList();

        /* TODO: This loop will be replaced by server call 
        which returns only those assets registered at the server */
        if (nymID == null) {
            nymID = new NYM().registeredNYM(serverID);
        }

        System.out.println("--nymID:" + nymID);


        if (nymID == null) {
            basketList.add(new String[]{"Popup Dialog"});
            return basketList;
        }

        List registeredAssets = getRegisteredAssets(serverID, nymID);

        if (registeredAssets != null) {

            for (int i = 0; i < registeredAssets.size(); i++) {
                String key = (String) registeredAssets.get(i);
                System.out.println("key:" + key);
                if (key != null && otapi.OT_API_IsBasketCurrency(key) == 1) {
                    String label = otapi.OT_API_GetAssetType_Name(key);
                    basketList.add(new String[]{label, key});
                }
            }
        }


        /* int count = otapi.OT_API_GetAssetTypeCount();

        for (int i = 0; i < count; i++) {
        String key = otapi.OT_API_GetAssetType_ID(i);
        System.out.println("key:" + key);
        if (key != null && otapi.OT_API_IsBasketCurrency(key) == 1) {
        String label = otapi.OT_API_GetAssetType_Name(key);
        basketList.add(new String[]{label, key});
        }
        }*/


        return basketList;
    }

    public static List getRegisteredAssets(String serverID, String nymID) {

        System.out.println("getRegisteredAssets - serverID:" + serverID + " nymID:" + nymID);

        List registeredAssetsList = null;

        String strEncodedObj = null; // output will go here.
        StringMap stringMap = null;	// we are about to create this object

        Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_STRING_MAP);

        if (storable != null) {
            stringMap = StringMap.ot_dynamic_cast(storable);
            if (stringMap != null) {
                // ADD ALL THE ASSET IDs HERE (To the string map, so you
                // can ask the server about them...)
                //
                int count = otapi.OT_API_GetAssetTypeCount();
                System.out.println(" count:" + count);
                for (int i = 0; i < count; i++) {
                    String key = otapi.OT_API_GetAssetType_ID(i);
                    System.out.println("key:" + key);
                    stringMap.SetValue(key, "exists");

                }
                System.out.println(" BEFORE ENCODE,stringMap:" + stringMap);

                strEncodedObj = otapi.EncodeObject(stringMap);
            }
        }
        System.out.println(" fter ENCODE,strEncodedObj:" + strEncodedObj);

        if (null == strEncodedObj) {
            System.out.println("strEncodedObj is null");
            return null;
            //Error;
        }

        // Then send the server message

        OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.QUERY_ASSET_TYPES, serverID, nymID, strEncodedObj);
        System.out.println(" theRequest :" + theRequest);

        String strResponse = theRequest.SendRequest(theRequest, "QUERY_ASSET_TYPES");
        System.out.println(" strResponse:" + strResponse);

        String strReplyMap = null;

        // When the server reply comes back, get the payload from it:

        if (strResponse != null) {
            strReplyMap = otapi.OT_API_Message_GetPayload(strResponse);
        }
        System.out.println("strResponse is " + strResponse);

        //	Pass the payload (the StringMap from the server's reply) to otapi.DecodeObject:

        if (strReplyMap != null) {
            StringMap stringMapOutput = null;
            storable = otapi.DecodeObject(StoredObjectType.STORED_OBJ_STRING_MAP, strReplyMap);
            if (storable != null) {
                stringMapOutput = StringMap.ot_dynamic_cast(storable);
                if (stringMapOutput != null) {
                    // Loop through string map. For each asset ID key, the value will
                    // say either "true" or "false".
                    int count = otapi.OT_API_GetAssetTypeCount();

                    for (int i = 0; i < count; i++) {
                        String key = otapi.OT_API_GetAssetType_ID(i);
                        System.out.println("key in output:" + key);
                        String isRegistered = stringMapOutput.GetValue(key);
                        System.out.println("isRegistered in output:" + isRegistered);
                        if ("true".equalsIgnoreCase(isRegistered)) {
                            if (registeredAssetsList == null) {
                                registeredAssetsList = new ArrayList();
                            }
                            registeredAssetsList.add(key);
                        }
                    }
                }
            }
        }
        System.out.println("registeredAssetsList is " + registeredAssetsList);


        return registeredAssetsList;
    }

    public static Map getAssetList(String serverID, String nymID, List existingAssets) {

        Map basketList = new HashMap();

        /* TODO: This loop will be replaced by server call
        which returns only those assets registered at the server */

        List registeredAssets = getRegisteredAssets(serverID, nymID);
        if (registeredAssets != null) {
            int count = registeredAssets.size();
            int i = 0, j = 0;
            System.out.println("count:" + count);
            while (j < count) {
                String key = (String) registeredAssets.get(j);
                if (key == null) {
                    continue;
                }
                boolean addToList = true;
                if (existingAssets != null && !existingAssets.isEmpty()) {
                    for (int k = 0; k < existingAssets.size(); k++) {
                        if (key.equals(existingAssets.get(k))) {
                            addToList = false;
                            break;
                        }
                    }

                }
                if (addToList) {
                    String label = otapi.OT_API_GetAssetType_Name(key);
                    basketList.put(i, new String[]{label, key});
                    i++;
                }
                j++;
            }
        }
        /*int count = otapi.OT_API_GetAssetTypeCount();
        int i = 0, j = 0;
        System.out.println("count:" + count);
        while (j < count) {
        String key = otapi.OT_API_GetAssetType_ID(j);
        System.out.println("existingAssets.contains(key):" + existingAssets.contains(key) + "existingAssets size:" + existingAssets.size());
        boolean addToList = true;
        if (existingAssets != null && !existingAssets.isEmpty()) {
        for (int k = 0; k < existingAssets.size(); k++) {
        if (key.equals(existingAssets.get(k))) {
        addToList = false;
        break;
        }
        }

        }
        if (addToList) {
        String label = otapi.OT_API_GetAssetType_Name(key);
        System.out.println("label:" + label + " i:" + i);
        basketList.put(i, new String[]{label, key});
        i++;
        }
        j++;
        }*/


        return basketList;
    }

    public static String getBasketDetailsLabel(String assetID, String assetName) {

        StringBuilder basket = new StringBuilder();

        String minTransferAmt = otapi.OT_API_Basket_GetMinimumTransferAmount(assetID);

        System.out.println("getBasketDetailsLabel --minTransferAmt:" + minTransferAmt + " assetName:" + assetName);

        basket.append(minTransferAmt);
        basket.append(" ");
        basket.append(assetName);
        basket.append(" == ");

        baseName = assetName;
        baseValue = minTransferAmt;


        int basketMemberCount = otapi.OT_API_Basket_GetMemberCount(assetID);

        System.out.println("getBasketDetailsLabel --basketMemberCount:" + basketMemberCount);
        for (int i = 0; i < basketMemberCount; i++) {
            String memberAssetID = otapi.OT_API_Basket_GetMemberType(assetID, i);
            System.out.println("getBasketDetailsLabel memberAssetID:" + memberAssetID);
            if (memberAssetID != null) {
                String minTransferAmtMember = otapi.OT_API_Basket_GetMemberMinimumTransferAmount(assetID, i);
                System.out.println("getBasketDetailsLabel minTransferAmtMember:" + minTransferAmtMember);
                basket.append(minTransferAmtMember);
                basket.append(" ");
                basket.append(otapi.OT_API_GetAssetType_Name(memberAssetID));
                addSubCurrency(new String[]{minTransferAmtMember, otapi.OT_API_GetAssetType_Name(memberAssetID)});
                if (i != basketMemberCount - 1) {
                    basket.append(", ");
                }
            }
        }
        System.out.println("getBasketDetailsLabel -- basket.toString():" + basket.toString());
        return basket.toString();

    }

    /*public static String getNewValueOld(int newValue, String basket) {

    String newLabel = "";
    String[] basketSplitted = basket.split("==");
    String[] basketBase = basketSplitted[0].split(" ");
    int basketMultiple = Integer.parseInt(basketBase[0]);
    newLabel = String.valueOf(basketMultiple * (newValue)) + " " + basketBase[1];
    newLabel += " == ";
    String basketMemberSplitted[] = basketSplitted[1].split(",");
    for (int i = 0; i < basketMemberSplitted.length; i++) {
    String[] eachMember = basketMemberSplitted[i].split(" ");
    newLabel += String.valueOf(Integer.parseInt(eachMember[1]) * (newValue)) + " " + eachMember[2];
    if (i != basketMemberSplitted.length - 1) {
    newLabel += ", ";
    }
    }

    return newLabel;
    }*/
    public static String getNewValue(int newValue) {

        String newLabel = "";
        newLabel = String.valueOf(Integer.parseInt(baseValue) * (newValue)) + " " + baseName;
        newLabel += " == ";
        for (int i = 0; i < subCurrency.size(); i++) {
            String[] eachMember = (String[]) subCurrency.get(i);
            newLabel += String.valueOf(Integer.parseInt(eachMember[0]) * (newValue)) + " " + eachMember[1];
            if (i != subCurrency.size() - 1) {
                newLabel += ", ";
            }
        }

        return newLabel;
    }

    public static String getAssetTypeName(String assetTypeID, String serverID) {

        String name = otapi.OT_API_GetAssetType_Name(assetTypeID);

        if (name != null) {
            return name;
        }

        if (otapi.OT_API_LoadAssetContract(assetTypeID) == null) {
            System.out.println("IN getAssetTypeName, OT_API_LoadAssetContract is null");

            // ----------------------------------------

            String nymID = new NYM().registeredNYM(serverID);

            if (nymID == null) {
                return "Popup Dialog";
            }

            OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.GET_CONTRACT, serverID, nymID, assetTypeID);
            String strResponse = OTAPI_Func.SendRequest(theRequest, "GET_CONTRACT");

            if (null == strResponse) {
                System.out.println("IN getAssetTypeName: OTAPI_Func.SendRequest(() failed. (I give up.) ");
                return null;
            }


        }

        return otapi.OT_API_GetAssetType_Name(assetTypeID) == null ? "" : otapi.OT_API_GetAssetType_Name(assetTypeID);


    }

    public static void loadAsset(String assetTypeID, String nymID, String serverID) {


        if (otapi.OT_API_LoadAssetContract(assetTypeID) == null) {
            System.out.println("IN getAssetTypeName, OT_API_LoadAssetContract is null");

            // ----------------------------------------

            OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.GET_CONTRACT, serverID, nymID, assetTypeID);
            String strResponse = OTAPI_Func.SendRequest(theRequest, "GET_CONTRACT");

            if (null == strResponse) {
                System.out.println("IN getAssetTypeName: OTAPI_Func.SendRequest(() failed. (I give up.) ");
            }


        }

    }

    public static String getAssetTypeNameForRegNym(String assetTypeID, String serverID, String nymID) {


        OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.GET_CONTRACT, serverID, nymID, assetTypeID);
        String strResponse = OTAPI_Func.SendRequest(theRequest, "GET_CONTRACT");

        if (null == strResponse) {
            System.out.println("IN getAssetTypeNameForRegNym: OTAPI_Func.SendRequest(() failed. (I give up.) ");
            return null;
        }

        return otapi.OT_API_GetAssetType_Name(assetTypeID) == null ? "" : otapi.OT_API_GetAssetType_Name(assetTypeID);


    }
}
