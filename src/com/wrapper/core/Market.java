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

import com.wrapper.core.dataobjects.MarketDetails;
import com.wrapper.core.dataobjects.MarketTicker;
import com.wrapper.core.dataobjects.NymOfferDetails;
import com.wrapper.core.jni.AskData;
import com.wrapper.core.jni.BidData;
import com.wrapper.core.jni.MarketData;
import com.wrapper.core.jni.MarketList;
import com.wrapper.core.jni.OfferDataNym;
import com.wrapper.core.jni.OfferListMarket;
import com.wrapper.core.jni.OfferListNym;
import com.wrapper.core.jni.TradeDataMarket;
import com.wrapper.core.jni.TradeDataNym;
import com.wrapper.core.jni.TradeListMarket;
import com.wrapper.core.jni.TradeListNym;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market {

    public static Map loadMarketList(String serverID, String nymID) throws InterruptedException {

        Map marketListMap = new HashMap();

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getMarketList(serverID, nymID);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN loadMarketList " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return null;
            } else {
                otapi.OT_API_getMarketList(serverID, nymID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            }

        }

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("loadMarketList OT_API_Message_GetSuccess returned false:");
            return null;
        }

        if (otapi.OT_API_Message_GetDepth(serverResponseMessage) == 0) {
            return marketListMap;
        }

        MarketList marketList = Utility.getMarketList(serverID);

        if (marketList == null) {
            System.out.println("loadMarketList - marketList returns null");
            return null;
        }
        int count = (int) marketList.GetMarketDataCount();

        for (int i = 0; i < count; i++) {

            MarketData marketData = marketList.GetMarketData(i);
            if (marketData == null) {
                continue;
            }
            if ("ALL".equalsIgnoreCase(serverID) || serverID.equals(marketData.getServer_id())) {
                String[] data = new String[2];
                data[0] = marketData.getMarket_id() == null ? "" : marketData.getMarket_id();
                //data[0] = marketData.getGui_label() == null ? "" : marketData.getGui_label();
                data[1] = marketData.getMarket_id() == null ? "" : marketData.getMarket_id();
                marketListMap.put(data[1], data);
            }
        }

        return marketListMap;
    }

    public static NymOfferDetails getNymOfferDetails(String serverID, String nymID, String transactionID) {

        NymOfferDetails nymOfferDetails = new NymOfferDetails();

        OfferListNym offerListNym = Utility.getNYMOffer(serverID, nymID);

        if (offerListNym == null) {
            System.out.println("getNymOfferDetails - offerListNym returns null");
            return null;
        }

        for (int j = 0; j < offerListNym.GetOfferDataNymCount(); j++) {

            OfferDataNym offerDataNym = offerListNym.GetOfferDataNym(j);
            if (offerDataNym == null || transactionID == null) {
                continue;
            }
            if (transactionID.equals(offerDataNym.getTransaction_id())) {
                nymOfferDetails.setPrice(offerDataNym.getPrice_per_scale());
                nymOfferDetails.setMinIncrement(offerDataNym.getMinimum_increment());
                nymOfferDetails.setTotalAssetsOnOffer(offerDataNym.getTotal_assets());
                double assetCount = -1;
                try {
                    assetCount = Double.parseDouble(offerDataNym.getTotal_assets()) - Double.parseDouble(offerDataNym.getFinished_so_far());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                nymOfferDetails.setAssetsStillOnOffer(assetCount == -1 ? "" : String.valueOf(assetCount));

                break;
            }

        }
        return nymOfferDetails;
    }

    public static boolean createOrder(String serverID, String nymID, String assetTypeID, String assetAcctID, String currencyTypeID, String currencyAcctID, String scale, String minIncrement, String quantity, String price, int selling) throws InterruptedException {

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            System.out.println("IN createOrder , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return false;
        }

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_issueMarketOffer(serverID, nymID, assetTypeID, assetAcctID, currencyTypeID, currencyAcctID, scale, minIncrement, quantity, price, selling);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN createOrder,OT_API_issueMarketOffer " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber,OT_API_issueMarketOffer " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("OT_API_Message_GetSuccess returned false");
                return false;
            } else {
                otapi.OT_API_issueMarketOffer(serverID, nymID, assetTypeID, assetAcctID, currencyTypeID, currencyAcctID, scale, minIncrement, quantity, price, selling);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println(" after getting request number,serverResponseMessage:" + serverResponseMessage);
            }

        }

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("OT_API_Message_GetSuccess OT_API_issueMarketOffer returned false1");
            return false;
        }

        if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, assetAcctID, serverResponseMessage) == 0) // Failure
        {
            boolean b1 = Utility.getAndProcessNymbox(serverID, nymID);
            
//            otapi.OT_API_FlushMessageBuffer();
//            otapi.OT_API_getNymbox(serverID, nymID); // The failure might have been due to a finalReceipt waiting in my Nymbox.
//            Utility.delay();    // So let's update the Nymbox and then try again.
//            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
//            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            if (false == b1) {
                
                System.out.println("IN createOrder, Utility.getAndProcessNymbox returned false");
                return false;
            }
            // <====== TRYING AGAIN (THIRD TIME)
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_issueMarketOffer(serverID, nymID, assetTypeID, assetAcctID, currencyTypeID, currencyAcctID, scale, minIncrement, quantity, price, selling);

            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            // Balance agreement STILL FAILURE <=========
            //
            if (serverResponseMessage == null) {
                System.out.println("after calling OT_API_issueMarketOffer serverResponseMessage is null after retry after balance agreement failure. ");
                return false;
            } else if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, assetAcctID, serverResponseMessage) == 0) // Failure
            {
                System.out.println(" createOrder OT_API_issueMarketOffer serverResponseMessage is still FAILURE after retry after balance agreement failure. ");
                return false;
            }
            System.out.println("after balance agreement retry, ");
        }

        if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, assetAcctID, serverResponseMessage) == 0) // Failure.
        {
            // Maybe we have an old Inbox.
            //
            if(!OpenTransactionAccount.getInboxAccount(serverID, nymID, assetAcctID)){
                System.out.println("createOrder getInboxAccount returned false");
                return false;
            }
            Utility.delay();

            otapi.OT_API_issueMarketOffer(serverID, nymID, assetTypeID, assetAcctID, currencyTypeID, currencyAcctID, scale, minIncrement, quantity, price, selling);
            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            if (serverResponseMessage == null) {
                System.out.println("serverResponseMessage is null after retry after transaction failure and retry. ");
                return false;
            }
            if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, assetAcctID, serverResponseMessage) == 0) // Transaction failed.
            {
                System.out.println("serverResponseMessage is still a failure after retry after transaction failure. ");
                return false;
            }
            System.out.println("after transaction retry, ");
        }


        return true;
    }

    public static boolean cancelOrder(String serverID, String nymID, String assetAccountID, String transactionID) throws InterruptedException {

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            System.out.println("IN cancelOrder , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
            return false;
        }

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_cancelMarketOffer(serverID, nymID, assetAccountID, transactionID);

        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN cancelOrder,OT_API_cancelNymMarketOffer " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber,OT_API_cancelNymMarketOffer " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("OT_API_Message_GetSuccess returned false");
                return false;
            } else {
                otapi.OT_API_cancelMarketOffer(serverID, nymID, assetAccountID, transactionID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            }

        }

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("OT_API_Message_GetSuccess OT_API_cancelNymMarketOffer returned false1");
            return false;
        }

        if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, assetAccountID, serverResponseMessage) == 0) // Failure
        {
            boolean b1 = Utility.getAndProcessNymbox(serverID, nymID);

            if (false == b1) {
                System.out.println("IN cancelOrder, Utility.getAndProcessNymbox returned false");
                return false;
            }
            // <====== TRYING AGAIN (THIRD TIME)
            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_cancelMarketOffer(serverID, nymID, assetAccountID, transactionID);

            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            // Balance agreement STILL FAILURE <=========
            //
            if (serverResponseMessage == null) {
                System.out.println("cancelOrder OT_API_getNymbox serverResponseMessage is null after retry after balance agreement failure. ");
                return false;
            } else if (otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, assetAccountID, serverResponseMessage) == 0) // Failure
            {
                System.out.println("cancelOrder OT_API_getNymbox serverResponseMessage is still FAILURE after retry after balance agreement failure. ");
                return false;
            }
            System.out.println("cancelOrder after balance agreement retry, ");
        }

        if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, assetAccountID, serverResponseMessage) == 0) // Failure.
        {
            // Maybe we have an old Inbox.
            //
            if(!OpenTransactionAccount.getInboxAccount(serverID, nymID, assetAccountID)){
                System.out.println("cancelOrder getInboxAccount returned false");
                return false;
            }

            otapi.OT_API_cancelMarketOffer(serverID, nymID, assetAccountID, transactionID);
            Utility.longDelay();

            serverResponseMessage = otapi.OT_API_PopMessageBuffer();

            if (serverResponseMessage == null) {
                System.out.println("cancelOrder serverResponseMessage is null after retry after transaction failure and retry. ");
                return false;
            }
            if (otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, assetAccountID, serverResponseMessage) == 0) // Transaction failed.
            {
                System.out.println("cancelOrder serverResponseMessage is still a failure after retry after transaction failure. ");
                return false;
            }
            System.out.println("cancelOrder after transaction retry, ");
        }

        return true;
    }

    public static Map getNymOfferList(String serverID, String nymID) throws InterruptedException {

        Map nymOffersData = new HashMap();

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getNym_MarketOffers(serverID, nymID);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN getNymOfferList,OT_API_getNym_MarketOffers " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber,OT_API_getNym_MarketOffers " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("OT_API_Message_GetSuccess returned false");
                return null;
            } else {
                otapi.OT_API_getNym_MarketOffers(serverID, nymID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            }

        }

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("OT_API_Message_GetSuccess OT_API_getNym_MarketOffers returned false1");
            return null;
        }

        if (otapi.OT_API_Message_GetDepth(serverResponseMessage) == 0) {
            return nymOffersData;
        }

        OfferListNym offerListNym = Utility.getNYMOffer(serverID, nymID);

        if (offerListNym == null) {
            System.out.println("getNymOfferList - offerListNym returns null");
            return null;
        }

        for (int j = 0; j < offerListNym.GetOfferDataNymCount(); j++) {
            OfferDataNym offerDataNym = offerListNym.GetOfferDataNym(j);
            if (offerDataNym == null) {
                continue;
            }

            String[] nymDataRow = new String[3];
            //offerDataNym.getSelling()
            nymDataRow[0] = offerDataNym.getTransaction_id();
            nymDataRow[1] = offerDataNym.getSelling() == true ? "Ask" : "Bid";
            nymDataRow[2] = offerDataNym.getAsset_acct_id();

            nymOffersData.put(offerDataNym.getTransaction_id(), nymDataRow);

        }

        return nymOffersData;
    }

    public static MarketDetails getMarketDetails(String marketID, String serverID, String nymID) throws InterruptedException {

        if (marketID == null || serverID == null || nymID == null) {
            System.out.println("getMarketDetails - returns null , marketID:" + marketID + " serverID:" + serverID + " nymID:" + nymID);
            return null;
        }

        Map askGridData = new HashMap();
        Map bidGridData = new HashMap();
        Map nymGridData = new HashMap();

        //Map tradeMarketData = new HashMap();
        List tradeMarketData = new ArrayList();

        MarketList marketList = Utility.getMarketList(serverID);
        MarketDetails marketDetails = null;
        if (marketList == null) {
            System.out.println("getMarketDetails - marketList returns null");
            return null;
        }
        for (int i = 0; i < marketList.GetMarketDataCount(); i++) {
            MarketData marketData = marketList.GetMarketData(i);
            if (marketData == null) {
                continue;
            }


            if (marketID.equals(marketData.getMarket_id())) {
                marketDetails = new MarketDetails();
                MarketTicker marketTicker = new MarketTicker();
                marketTicker.setLastPrice(marketData.getLast_sale_price());
                marketTicker.setHighestBid(marketData.getCurrent_bid());
                marketTicker.setLowestAsk(marketData.getCurrent_ask());
                marketTicker.setHighPrice(marketData.getRecent_highest_bid());
                marketTicker.setLowPrice(marketData.getRecent_lowest_ask());

                marketDetails.setMarketTicker(marketTicker);

                marketDetails.setAssetTypeID(marketData.getAsset_type_id() == null ? "" : marketData.getAsset_type_id());
                marketDetails.setCurrencyID(marketData.getCurrency_type_id() == null ? "" : marketData.getCurrency_type_id());
                marketDetails.setServerID(marketData.getServer_id() == null ? "" : marketData.getServer_id());
                marketDetails.setGranularity(marketData.getScale() == null ? "" : marketData.getScale().toString());

                if (marketDetails.getAssetTypeID().equals("")) {
                    marketDetails.setAssetTypeName("");
                } else {
                    marketDetails.setAssetTypeName(otapi.OT_API_GetAssetType_Name(marketDetails.getAssetTypeID()));
                }

                if (marketDetails.getServerID().equals("")) {
                    marketDetails.setServerName("");
                } else {
                    marketDetails.setServerName(otapi.OT_API_GetServer_Name(marketDetails.getServerID()));
                }

                if (marketDetails.getCurrencyID().equals("")) {
                    marketDetails.setCurrencyName("");
                } else {
                    marketDetails.setCurrencyName(otapi.OT_API_GetAssetType_Name(marketDetails.getCurrencyID()));
                }

                marketDetails.setNbrAsks(marketData.getNumber_asks() == null ? "" : marketData.getNumber_asks());
                marketDetails.setNbrBids(marketData.getNumber_bids() == null ? "" : marketData.getNumber_bids());
                marketDetails.setTotalAssets(marketData.getTotal_assets() == null ? "" : marketData.getTotal_assets());

                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getMarketOffers(serverID, nymID, marketID, Configuration.getMarketMaxDepth());
                Utility.delay();
                String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN getMarketDetails " + serverResponseMessage);

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_getRequest(serverID, nymID);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    System.out.println("IN getRequestNumber " + serverResponseMessage);

                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        System.out.println("OT_API_Message_GetSuccess returned false");
                        return null;
                    } else {
                        otapi.OT_API_getMarketOffers(serverID, nymID, marketID, Configuration.getMarketMaxDepth());
                        Utility.delay();
                        serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    }

                }

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    System.out.println("OT_API_Message_GetSuccess returned false1");
                    return null;
                }

                if (otapi.OT_API_Message_GetDepth(serverResponseMessage) > 0) {


                    OfferListMarket offerListMarket = Utility.getMarketOffer(serverID, marketID);

                    if (offerListMarket == null) {
                        System.out.println("getMarketDetails - offerListMarket returns null");
                        return null;
                    }


                    for (int j = 0; j < offerListMarket.GetAskDataCount(); j++) {
                        AskData askData = offerListMarket.GetAskData(j);
                        if (askData == null) {
                            continue;
                        }

                        String[] askRow = new String[4];

                        askRow[0] = askData.getPrice_per_scale();
                        askRow[1] = askData.getAvailable_assets();
                        askRow[3] = askData.getMinimum_increment();

                        try {
                            Long lScale       = Long.valueOf(marketDetails.getGranularity()); 
                            Long lPrice       = Long.valueOf(askRow[0]);    // this price is "per scale"
                            Long lQuantity    = Long.valueOf(askRow[1]);    // Total overall quantity available
                            Long lScaleUnits  = Long.valueOf(lQuantity / lScale);   // Number of scale units available in total quanity. (120 total at scale of 10, is 12 units.)
                            Long lTotalCost   = Long.valueOf(lPrice * lScaleUnits); // // Total value of available units is price times scale units. 
                            askRow[2] = String.valueOf(lTotalCost);    // At $5 per scale, at 12 units, is $60 total for 120 total assets. (The number 60 goes here, plus the currency symbol todo.)
//                          askRow[2] = String.valueOf(Double.parseDouble(askRow[0]) * Double.parseDouble(askRow[1]));

                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                            System.out.println("Invalid number returned");
                            askRow[2] = "";
                        }

                        askGridData.put(askData.getTransaction_id(), askRow);


                    }



                    for (int j = 0; j < offerListMarket.GetBidDataCount(); j++) {
                        BidData bidData = offerListMarket.GetBidData(j);
                        if (bidData == null) {
                            continue;
                        }

                        String[] bidRow = new String[4];

                        bidRow[0] = bidData.getPrice_per_scale();
                        bidRow[1] = bidData.getAvailable_assets();
                        bidRow[3] = bidData.getMinimum_increment();

                        try {
                            Long lScale       = Long.valueOf(marketDetails.getGranularity()); 
                            Long lPrice       = Long.valueOf(bidRow[0]);    // this price is "per scale"
                            Long lQuantity    = Long.valueOf(bidRow[1]);    // Total overall quantity available
                            Long lScaleUnits  = Long.valueOf(lQuantity / lScale);   // Number of scale units available in total quanity. (120 total at scale of 10, is 12 units.)
                            Long lTotalCost   = Long.valueOf(lPrice * lScaleUnits); // // Total value of available units is price times scale units. 
                            bidRow[2] = String.valueOf(lTotalCost);    // At $5 per scale, at 12 units, is $60 total for 120 total assets. (The number 60 goes here, plus the currency symbol todo.)
//                          bidRow[2] = String.valueOf(Double.parseDouble(bidRow[0]) * Double.parseDouble(bidRow[1]));

                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                            System.out.println("Invalid number returned");
                            bidRow[2] = "";
                        }


                        bidGridData.put(bidData.getTransaction_id(), bidRow);

                    }
                }

                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getMarketRecentTrades(serverID, nymID, marketID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN getMarketDetails,OT_API_getMarketRecentTrades " + serverResponseMessage);

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_getRequest(serverID, nymID);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    System.out.println("IN getRequestNumber,OT_API_getMarketRecentTrades " + serverResponseMessage);

                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        System.out.println("OT_API_Message_GetSuccess returned false");
                        return null;
                    } else {
                        otapi.OT_API_getMarketRecentTrades(serverID, nymID, marketID);
                        Utility.delay();
                        serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    }

                }

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    System.out.println("OT_API_Message_GetSuccess OT_API_getMarketRecentTrades returned false1");
                    return null;
                }

                if (otapi.OT_API_Message_GetDepth(serverResponseMessage) > 0) {

                    TradeListMarket tradeListMarket = Utility.getMarketTradeList(serverID, marketID);

                    if (tradeListMarket == null) {
                        System.out.println("getMarketDetails - tradeListMarket returns null");
                        return null;
                    }

                    for (int j = 0; j < tradeListMarket.GetTradeDataMarketCount(); j++) {
                        TradeDataMarket tradeDataMarket = tradeListMarket.GetTradeDataMarket(j);
                        if (tradeDataMarket == null) {
                            continue;
                        }

                        String[] tradeDataRow = new String[5];

                        tradeDataRow[2] = tradeDataMarket.getAmount_sold() == null ? "" : tradeDataMarket.getAmount_sold();
                        tradeDataRow[4] = tradeDataMarket.getDate() == null ? "" : tradeDataMarket.getDate();

                        try {
                            tradeDataRow[4] = String.valueOf(new Date(Long.parseLong(tradeDataRow[4]) * 1000));

                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                            System.out.println("Invalid number returned by timestmp:" + tradeDataRow[4]);
                            tradeDataRow[4] = "";
                        }

                        tradeDataRow[1] = tradeDataMarket.getPrice() == null ? "" : tradeDataMarket.getPrice();
                        tradeDataRow[0] = tradeDataMarket.getTransaction_id() == null ? "" : tradeDataMarket.getTransaction_id();

                        try {
                            Long lScale       = Long.valueOf(marketDetails.getGranularity()); 
                            Long lPrice       = Long.valueOf(tradeDataRow[1]);    // this price is "per scale"
                            Long lQuantity    = Long.valueOf(tradeDataRow[2]);    // Total overall quantity available
                            Long lScaleUnits  = Long.valueOf(lQuantity / lScale);   // Number of scale units available in total quanity. (120 total at scale of 10, is 12 units.)
                            Long lTotalCost   = Long.valueOf(lPrice * lScaleUnits); // // Total value of available units is price times scale units. 
                            tradeDataRow[3] = String.valueOf(lTotalCost);    // At $5 per scale, at 12 units, is $60 total for 120 total assets. (The number 60 goes here, plus the currency symbol todo.)
//                          tradeDataRow[3] = String.valueOf(Double.parseDouble(tradeDataRow[2]) * Double.parseDouble(tradeDataRow[1]));

                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                            System.out.println("Invalid number returned by timestmp:" + tradeDataRow[3]);
                            tradeDataRow[3] = "";
                        }

                        tradeMarketData.add(tradeDataRow);
                    }
                }

                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getNym_MarketOffers(serverID, nymID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN getMarketDetails,OT_API_getNym_MarketOffers " + serverResponseMessage);

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_getRequest(serverID, nymID);
                    Utility.delay();
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    System.out.println("IN getRequestNumber,OT_API_getNym_MarketOffers " + serverResponseMessage);

                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        System.out.println("OT_API_Message_GetSuccess returned false");
                        return null;
                    } else {
                        otapi.OT_API_getNym_MarketOffers(serverID, nymID);
                        Utility.delay();
                        serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    }

                }

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                    System.out.println("OT_API_Message_GetSuccess OT_API_getNym_MarketOffers returned false1");
                    return null;
                }

                if (otapi.OT_API_Message_GetDepth(serverResponseMessage) > 0) {

                    OfferListNym offerListNym = Utility.getNYMOffer(serverID, nymID);

                    if (offerListNym == null) {
                        System.out.println("getMarketDetails - offerListNym returns null");
                        return null;
                    }


                    for (int j = 0; j < offerListNym.GetOfferDataNymCount(); j++) {
                        OfferDataNym offerDataNym = offerListNym.GetOfferDataNym(j);
                        if (offerDataNym == null) {
                            continue;
                        }

                        String[] nymDataRow = new String[3];
                        //offerDataNym.getSelling()
                        nymDataRow[0] = offerDataNym.getTransaction_id();
                        nymDataRow[1] = offerDataNym.getSelling() == true ? "Ask" : "Bid";
                        nymDataRow[2] = offerDataNym.getAsset_acct_id();

                        nymGridData.put(offerDataNym.getTransaction_id(), nymDataRow);

                    }

                }

                marketDetails.setMarketAsk(askGridData);
                marketDetails.setMarketBid(bidGridData);
                marketDetails.setMarketRecentTrades(tradeMarketData);
                marketDetails.setNymOffers(nymGridData);

                break;

            }


        }

        return marketDetails;
    }

    public static Map getNymTrades(String serverID, String nymID) {

        Map tradeNymData = new HashMap();

        TradeListNym tradeListNym = Utility.getNYMTrades(serverID, nymID);

        if (tradeListNym == null) {
            System.out.println("getNymTrades - tradeListNym returns null");
            return null;
        }

        for (int j = 0; j < tradeListNym.GetTradeDataNymCount(); j++) {

            TradeDataNym tradeDataNym = tradeListNym.GetTradeDataNym(j);
            if (tradeDataNym == null) {
                continue;
            }

            String[] tradeDataRow = new String[5];

            tradeDataRow[0] = tradeDataNym.getTransaction_id() == null ? "" : tradeDataNym.getTransaction_id();
            tradeDataRow[1] = tradeDataNym.getPrice() == null ? "" : tradeDataNym.getPrice();
            tradeDataRow[2] = tradeDataNym.getAmount_sold() == null ? "" : tradeDataNym.getAmount_sold();
            tradeDataRow[3] = tradeDataNym.getCompleted_count() == null ? "" : tradeDataNym.getCompleted_count();
            tradeDataRow[4] = tradeDataNym.getDate() == null ? "" : tradeDataNym.getDate();

            if (!tradeDataRow[4].equals("")) {

                try {
                    tradeDataRow[4] = String.valueOf(new Date(Long.parseLong(tradeDataRow[4]) * 1000));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.out.println("Invalid number returned by timestmp:" + tradeDataRow[4]);
                    tradeDataRow[4] = "";
                }
            }

            tradeNymData.put(tradeDataNym.getTransaction_id(), tradeDataRow);

        }

        return tradeNymData;
    }

    public static MarketTicker getTicker(String marketID, String serverID, String nymID) throws InterruptedException {


        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getMarketList(serverID, nymID);
        Utility.delay();
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN getTicker " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Utility.delay();
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return null;
            } else {
                otapi.OT_API_getMarketList(serverID, nymID);
                Utility.delay();
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            }

        }

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("OT_API_Message_GetSuccess returned false:");
            return null;
        }

        MarketTicker marketTicker = new MarketTicker();

        if (otapi.OT_API_Message_GetDepth(serverResponseMessage) == 0) {
            System.out.println("OT_API_Message_GetDepth returned zero");
            return null;
        }

        MarketList marketList = Utility.getMarketList(serverID);

        if (marketList == null) {
            System.out.println("getTicker - marketList returns null");
            return null;
        }
        for (int i = 0; i < marketList.GetMarketDataCount(); i++) {

            MarketData marketData = marketList.GetMarketData(i);
            if (marketData == null) {
                continue;
            }


            if (marketID.equals(marketData.getMarket_id())) {

                marketTicker.setLastPrice(marketData.getLast_sale_price());
                marketTicker.setHighestBid(marketData.getCurrent_bid());
                marketTicker.setLowestAsk(marketData.getCurrent_ask());
                marketTicker.setHighPrice(marketData.getRecent_highest_bid());
                marketTicker.setLowPrice(marketData.getRecent_lowest_ask());
                break;
            }
        }
        return marketTicker;
    }
}
