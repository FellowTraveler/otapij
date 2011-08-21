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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Market {

    public static Map loadMarketList(String serverID, String nymID) throws InterruptedException {

        System.out.println("In loadMarketList, serverID:" + serverID + " nymID:" + nymID+" otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID):"+otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));
        System.out.println(" Load market:"+Configuration.getNbrTransactionCount());

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }

        System.out.println("otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID):"+otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID));

        Map marketListMap = new HashMap();

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getMarketList(serverID, nymID);
        Thread.sleep(Configuration.getWaitTime());
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN loadMarketList " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Thread.sleep(Configuration.getWaitTime());
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return null;
            } else {
                otapi.OT_API_getMarketList(serverID, nymID);
                Thread.sleep(Configuration.getWaitTime());
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

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_issueMarketOffer(serverID, nymID, assetTypeID, assetAcctID, currencyTypeID, currencyAcctID, scale, minIncrement, quantity, price, selling);
        Thread.sleep(Configuration.getWaitTime());
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN createOrder,OT_API_issueMarketOffer " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Thread.sleep(Configuration.getWaitTime());
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber,OT_API_issueMarketOffer " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("OT_API_Message_GetSuccess returned false");
                return false;
            } else {
                otapi.OT_API_issueMarketOffer(serverID, nymID, assetTypeID, assetAcctID, currencyTypeID, currencyAcctID, scale, minIncrement, quantity, price, selling);
                Thread.sleep(Configuration.getWaitTime());
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println(" after getting request number,serverResponseMessage:" + serverResponseMessage);
            }

        }

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("OT_API_Message_GetSuccess OT_API_issueMarketOffer returned false1");
            return false;
        }

        return true;
    }

    public static boolean cancelOrder(String serverID, String nymID, String marketID, String transactionID) throws InterruptedException {

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_cancelNymMarketOffer(serverID, nymID, marketID, transactionID);
        Thread.sleep(Configuration.getWaitTime());
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN cancelOrder,OT_API_cancelNymMarketOffer " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Thread.sleep(Configuration.getWaitTime());
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber,OT_API_cancelNymMarketOffer " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("OT_API_Message_GetSuccess returned false");
                return false;
            } else {
                otapi.OT_API_cancelNymMarketOffer(serverID, nymID, marketID, transactionID);
                Thread.sleep(Configuration.getWaitTime());
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            }

        }

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
            System.out.println("OT_API_Message_GetSuccess OT_API_cancelNymMarketOffer returned false1");
            return false;
        }


        return true;
    }

    public static Map getNymOfferList(String serverID, String nymID) throws InterruptedException {

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }

        Map nymOffersData = new HashMap();

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getNym_MarketOffers(serverID, nymID);
        Thread.sleep(Configuration.getWaitTime());
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN getNymOfferList,OT_API_getNym_MarketOffers " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Thread.sleep(Configuration.getWaitTime());
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber,OT_API_getNym_MarketOffers " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                System.out.println("OT_API_Message_GetSuccess returned false");
                return null;
            } else {
                otapi.OT_API_getNym_MarketOffers(serverID, nymID);
                Thread.sleep(Configuration.getWaitTime());
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

            String[] nymDataRow = new String[2];
            //offerDataNym.getSelling()
            nymDataRow[0] = offerDataNym.getTransaction_id();
            nymDataRow[1] = offerDataNym.getSelling() == true ? "Ask" : "Bid";

            nymOffersData.put(offerDataNym.getTransaction_id(), nymDataRow);

        }

        return nymOffersData;
    }

    public static MarketDetails getMarketDetails(String marketID, String serverID, String nymID) throws InterruptedException {

        if (marketID == null || serverID == null || nymID == null) {
            System.out.println("getMarketDetails - returns null , marketID:" + marketID + " serverID:" + serverID + " nymID:" + nymID);
            return null;
        }

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }


        Map askGridData = new HashMap();
        Map bidGridData = new HashMap();
        Map nymGridData = new HashMap();
        Map tradeNymData = new HashMap();
        Map tradeMarketData = new HashMap();

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


                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getMarketOffers(serverID, nymID, marketID, Configuration.getMarketMaxDepth());
                Thread.sleep(Configuration.getWaitTime());
                String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN getMarketDetails " + serverResponseMessage);

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_getRequest(serverID, nymID);
                    Thread.sleep(Configuration.getWaitTime());
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    System.out.println("IN getRequestNumber " + serverResponseMessage);

                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        System.out.println("OT_API_Message_GetSuccess returned false");
                        return null;
                    } else {
                        otapi.OT_API_getMarketOffers(serverID, nymID, marketID, Configuration.getMarketMaxDepth());
                        Thread.sleep(Configuration.getWaitTime());
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

                        String[] askRow = new String[3];

                        askRow[0] = askData.getPrice_per_scale();
                        askRow[1] = askData.getAvailable_assets();
                        askRow[2] = askData.getMinimum_increment();

                        askGridData.put(askData.getTransaction_id(), askRow);


                    }



                    for (int j = 0; j < offerListMarket.GetBidDataCount(); j++) {
                        BidData bidData = offerListMarket.GetBidData(j);
                        if (bidData == null) {
                            continue;
                        }

                        String[] bidRow = new String[3];

                        bidRow[0] = bidData.getPrice_per_scale();
                        bidRow[1] = bidData.getAvailable_assets();
                        bidRow[2] = bidData.getMinimum_increment();

                        bidGridData.put(bidData.getTransaction_id(), bidRow);

                    }
                }
                if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
                    Utility.getTransactionNumbers(serverID, nymID);
                }
                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getMarketRecentTrades(serverID, nymID, marketID);
                Thread.sleep(Configuration.getWaitTime());
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN getMarketDetails,OT_API_getMarketRecentTrades " + serverResponseMessage);

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_getRequest(serverID, nymID);
                    Thread.sleep(Configuration.getWaitTime());
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    System.out.println("IN getRequestNumber,OT_API_getMarketRecentTrades " + serverResponseMessage);

                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        System.out.println("OT_API_Message_GetSuccess returned false");
                        return null;
                    } else {
                        otapi.OT_API_getMarketRecentTrades(serverID, nymID, marketID);
                        Thread.sleep(Configuration.getWaitTime());
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

                        String[] tradeDataRow = new String[4];

                        tradeDataRow[2] = tradeDataMarket.getAmount_sold()==null?"":tradeDataMarket.getAmount_sold();
                        tradeDataRow[3] = tradeDataMarket.getDate() == null ? "" : tradeDataMarket.getDate();
                        if (!tradeDataRow[3].equals("")) {

                            try {
                                tradeDataRow[3] = String.valueOf(new Date(Long.parseLong(tradeDataRow[3]) * 1000));
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                                System.out.println("Invalid number returned by timestmp:" + tradeDataRow[3]);
                                tradeDataRow[3] = "";
                            }
                        }
                        tradeDataRow[1] = tradeDataMarket.getPrice()==null?"":tradeDataMarket.getPrice();
                        tradeDataRow[0] = tradeDataMarket.getTransaction_id()==null?"":tradeDataMarket.getTransaction_id();

                        tradeMarketData.put(tradeDataMarket.getTransaction_id(), tradeDataRow);

                    }
                }

                otapi.OT_API_FlushMessageBuffer();
                otapi.OT_API_getNym_MarketOffers(serverID, nymID);
                Thread.sleep(Configuration.getWaitTime());
                serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                System.out.println("IN getMarketDetails,OT_API_getNym_MarketOffers " + serverResponseMessage);

                if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

                    otapi.OT_API_FlushMessageBuffer();
                    otapi.OT_API_getRequest(serverID, nymID);
                    Thread.sleep(Configuration.getWaitTime());
                    serverResponseMessage = otapi.OT_API_PopMessageBuffer();
                    System.out.println("IN getRequestNumber,OT_API_getNym_MarketOffers " + serverResponseMessage);

                    if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                        System.out.println("OT_API_Message_GetSuccess returned false");
                        return null;
                    } else {
                        otapi.OT_API_getNym_MarketOffers(serverID, nymID);
                        Thread.sleep(Configuration.getWaitTime());
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

                        String[] nymDataRow = new String[2];
                        //offerDataNym.getSelling()
                        nymDataRow[0] = offerDataNym.getTransaction_id();
                        nymDataRow[1] = offerDataNym.getSelling() == true ? "Ask" : "Bid";

                        nymGridData.put(offerDataNym.getTransaction_id(), nymDataRow);

                    }

                }

                TradeListNym tradeListNym = Utility.getNYMTrades(serverID, nymID);

                if (tradeListNym == null) {
                    System.out.println("getMarketDetails - tradeListNym returns null");
                    return null;
                }

                for (int j = 0; j < tradeListNym.GetTradeDataNymCount(); j++) {

                    TradeDataNym tradeDataNym = tradeListNym.GetTradeDataNym(j);
                    if (tradeDataNym == null) {
                        continue;
                    }

                    String[] tradeDataRow = new String[5];

                    System.out.println("tradeDataNym.getTransaction_id():"+tradeDataNym.getTransaction_id());
                    System.out.println("tradeDataNym.getPrice():"+tradeDataNym.getPrice());
                    System.out.println("tradeDataNym.getAmount_sold():"+tradeDataNym.getAmount_sold());
                    System.out.println("tradeDataNym.getCompleted_count():"+tradeDataNym.getCompleted_count());
                    System.out.println("tradeDataNym.getDate():"+tradeDataNym.getDate());



                    tradeDataRow[0] = tradeDataNym.getTransaction_id()==null?"":tradeDataNym.getTransaction_id();
                    tradeDataRow[1] = tradeDataNym.getPrice()==null?"":tradeDataNym.getPrice();
                    tradeDataRow[2] = tradeDataNym.getAmount_sold()==null?"":tradeDataNym.getAmount_sold();
                    tradeDataRow[3] = tradeDataNym.getCompleted_count()==null?"":tradeDataNym.getCompleted_count();
                    tradeDataRow[4] = tradeDataNym.getDate()==null?"":tradeDataNym.getDate();

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


                marketDetails.setMarketAsk(askGridData);
                marketDetails.setMarketBid(bidGridData);
                marketDetails.setMarketRecentTrades(tradeMarketData);
                marketDetails.setNymOffers(nymGridData);
                marketDetails.setNymTrades(tradeNymData);

                break;

            }


        }

        return marketDetails;
    }

    public static MarketTicker getTicker(String marketID, String serverID, String nymID) throws InterruptedException {

        if (otapi.OT_API_GetNym_TransactionNumCount(serverID, nymID) < Configuration.getNbrTransactionCount()) {
            Utility.getTransactionNumbers(serverID, nymID);
        }

        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getMarketList(serverID, nymID);
        Thread.sleep(Configuration.getWaitTime());
        String serverResponseMessage = otapi.OT_API_PopMessageBuffer();
        System.out.println("IN getTicker " + serverResponseMessage);

        if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {

            otapi.OT_API_FlushMessageBuffer();
            otapi.OT_API_getRequest(serverID, nymID);
            Thread.sleep(Configuration.getWaitTime());
            serverResponseMessage = otapi.OT_API_PopMessageBuffer();
            System.out.println("IN getRequestNumber " + serverResponseMessage);

            if (serverResponseMessage == null || otapi.OT_API_Message_GetSuccess(serverResponseMessage) == 0) {
                return null;
            } else {
                otapi.OT_API_getMarketList(serverID, nymID);
                Thread.sleep(Configuration.getWaitTime());
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
