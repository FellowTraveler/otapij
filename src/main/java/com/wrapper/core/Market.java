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
import com.wrapper.core.util.OTAPI_Func;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market {

    public static MarketTicker getTicker(String marketID, String serverID, String nymID) {

        // ----------------------------------------------------------
        OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.GET_MARKET_LIST, serverID, nymID);
        String strResponse = OTAPI_Func.SendRequest(theRequest, "GET_MARKET_LIST");

        if (null == strResponse) {
            System.out.println("IN getTicker: OTAPI_Func.SendRequest(() failed. (I give up.) ");
            return null;
        }
        // ----------------------------------------------------------

        if (otapi.OT_API_Message_GetDepth(strResponse) == 0) {
            System.out.println("getTicker: OT_API_Message_GetDepth returned zero");
            return null;
        }

        MarketList marketList = Utility.getMarketList(serverID);

        if (marketList == null) {
            System.out.println("getTicker - marketList returns null");
            return null;
        }

        int nMarketDataCount = (int) marketList.GetMarketDataCount();

        for (int i = 0; i < nMarketDataCount; i++) {

            MarketData marketData = marketList.GetMarketData(i);
            if (marketData == null) {
                continue;
            }

            if ((null == marketID)
                    || (null != marketID) && marketID.equals(marketData.getMarket_id())) {

                MarketTicker marketTicker = new MarketTicker();
                marketTicker.setLastPrice(marketData.getLast_sale_price());
                marketTicker.setHighestBid(marketData.getCurrent_bid());
                marketTicker.setLowestAsk(marketData.getCurrent_ask());
                marketTicker.setHighPrice(marketData.getRecent_highest_bid());
                marketTicker.setLowPrice(marketData.getRecent_lowest_ask());
                return marketTicker;
            }
        }
        return null;
    }

    public static Map loadMarketList(String serverID, String nymID) throws InterruptedException {
//DEBUGGING 3rd step of debugging.
        Map marketListMap = new HashMap();

        // ----------------------------------------------------------
        OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.GET_MARKET_LIST, serverID, nymID);
        String strResponse = OTAPI_Func.SendRequest(theRequest, "GET_MARKET_LIST");

        if (null == strResponse) {
            System.out.println("IN loadMarketList: OTAPI_Func.SendRequest(() failed. (I give up.) ");
            return null;
        }
        // ----------------------------------------------------------

        if (otapi.OT_API_Message_GetDepth(strResponse) == 0) {
            System.out.println("loadMarketList - marketList returns with a OT_API_Message_GetDepth() of 0 elements.");
            return marketListMap;
        }

        MarketList marketList = Utility.getMarketList(serverID);

        if (marketList == null) {
            System.out.println("loadMarketList - marketList returns null");
            return null;
        }
        int count = (int) marketList.GetMarketDataCount();

        if (0 >= count) {
            System.out.println("loadMarketList - marketList returns with a size of 0 elements.");
            return null;
        }

        for (int i = 0; i < count; i++) {

            MarketData marketData = marketList.GetMarketData(i);
            if (marketData == null) {
                continue;
            }
            if ("ALL".equalsIgnoreCase(serverID) || serverID.equals(marketData.getServer_id())) {
                String[] data = new String[2];
                if (marketData.getAsset_type_id() != null && marketData.getCurrency_type_id() != null) {
                    String assetName = otapi.OT_API_GetAssetType_Name(marketData.getAsset_type_id());
                    String currencyName = otapi.OT_API_GetAssetType_Name(marketData.getCurrency_type_id());
                    if (assetName != null && currencyName != null) {
                        data[0] = assetName + "-" + currencyName;
                }

                } else {
                    data[0] = marketData.getMarket_id() == null ? "" : marketData.getMarket_id();
                }
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

    public static boolean createOrder(String serverID, String nymID, String assetTypeID, String assetAcctID, String currencyTypeID, String currencyAcctID,
            String scale, String minIncrement, String quantity, String price, int selling) {

        // ----------------------------------------
        OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.CREATE_MARKET_OFFER, serverID, nymID, assetTypeID, assetAcctID, currencyTypeID, currencyAcctID,
                scale, minIncrement, quantity, price, selling == 1 ? true : false);
        String strResponse = OTAPI_Func.SendTransaction(theRequest, "CREATE_MARKET_OFFER");

        if (null == strResponse) {
            System.out.println("IN createOrder: OTAPI_Func.SendTransaction(() failed. (I give up.) ");
            return false;
        }
        // ----------------------------------------

        return true;
    }

    public static boolean cancelOrder(String serverID, String nymID, String assetAccountID, String transactionID) {

        // ----------------------------------------
        System.out.println(serverID+" .. "+nymID+" .. "+assetAccountID+" .. "+transactionID);
        OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.CANCEL_MARKET_OFFER, serverID, nymID, assetAccountID, transactionID);
        String strResponse = OTAPI_Func.SendTransaction(theRequest, "CANCEL_MARKET_OFFER");

        if (null == strResponse) {
            System.out.println("IN cancelOrder: OTAPI_Func.SendTransaction(() failed. (I give up.) ");
            return false;
        }
        // ----------------------------------------

        return true;
    }

    public static Map getNymOfferList(String serverID, String nymID) throws InterruptedException {

        Map nymOffersData = new HashMap();

        // ----------------------------------------------------------
        OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.GET_NYM_MARKET_OFFERS, serverID, nymID);
        String strResponse = OTAPI_Func.SendRequest(theRequest, "GET_NYM_MARKET_OFFERS");

        if (null == strResponse) {
            System.out.println("IN getNymOfferList: OTAPI_Func.SendRequest(() failed. (I give up.) ");
            return null;
        }
        // ----------------------------------------------------------

        if (otapi.OT_API_Message_GetDepth(strResponse) == 0) {
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
        int nMarketDataCount = (int) marketList.GetMarketDataCount();

        for (int i = 0; i < nMarketDataCount; i++) {
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

                // ----------------------------------------------------------
                OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.GET_MARKET_OFFERS, serverID, nymID, marketID, Configuration.getMarketMaxDepth());
                String strResponse = OTAPI_Func.SendRequest(theRequest, "GET_MARKET_OFFERS");

                if (null == strResponse) {
                    System.out.println("IN getMarketDetails: OTAPI_Func.SendRequest(() failed. (I give up.) ");
                    return null;
                }
                // ----------------------------------------------------------

                if (otapi.OT_API_Message_GetDepth(strResponse) > 0) {

                    OfferListMarket offerListMarket = Utility.getMarketOffer(serverID, marketID);

                    if (offerListMarket == null) {
                        System.out.println("getMarketDetails - offerListMarket returns null");
                        return null;
                    }
                    // ----------------------------------------------------
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
                            Long lScale = Long.valueOf(marketDetails.getGranularity());
                            Long lPrice = Long.valueOf(askRow[0]);    // this price is "per scale"
                            Long lQuantity = Long.valueOf(askRow[1]);    // Total overall quantity available
                            Long lScaleUnits = Long.valueOf(lQuantity / lScale);   // Number of scale units available in total quanity. (120 total at scale of 10, is 12 units.)
                            Long lTotalCost = Long.valueOf(lPrice * lScaleUnits); // // Total value of available units is price times scale units.
                            askRow[2] = String.valueOf(lTotalCost);    // At $5 per scale, at 12 units, is $60 total for 120 total assets. (The number 60 goes here, plus the currency symbol todo.)
//                          askRow[2] = String.valueOf(Double.parseDouble(askRow[0]) * Double.parseDouble(askRow[1]));

                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                            System.out.println("getMarketDetails: Invalid number returned");
                            askRow[2] = "";
                        }

                        askGridData.put(askData.getTransaction_id(), askRow);
                    }
                    // ----------------------------------------------------
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
                            Long lScale = Long.valueOf(marketDetails.getGranularity());
                            Long lPrice = Long.valueOf(bidRow[0]);    // this price is "per scale"
                            Long lQuantity = Long.valueOf(bidRow[1]);    // Total overall quantity available
                            Long lScaleUnits = Long.valueOf(lQuantity / lScale);   // Number of scale units available in total quanity. (120 total at scale of 10, is 12 units.)
                            Long lTotalCost = Long.valueOf(lPrice * lScaleUnits); // // Total value of available units is price times scale units.
                            bidRow[2] = String.valueOf(lTotalCost);    // At $5 per scale, at 12 units, is $60 total for 120 total assets. (The number 60 goes here, plus the currency symbol todo.)
//                          bidRow[2] = String.valueOf(Double.parseDouble(bidRow[0]) * Double.parseDouble(bidRow[1]));

                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                            System.out.println("getMarketDetails: Invalid number returned");
                            bidRow[2] = "";
                        }

                        bidGridData.put(bidData.getTransaction_id(), bidRow);
                    }
                }
                // ----------------------------------------------------------
                OTAPI_Func theRequest2 = new OTAPI_Func(OTAPI_Func.FT.GET_MARKET_RECENT_TRADES, serverID, nymID, marketID);
                String strResponse2 = OTAPI_Func.SendRequest(theRequest2, "GET_MARKET_RECENT_TRADES");

                if (null == strResponse2) {
                    System.out.println("IN getMarketDetails: OTAPI_Func.SendRequest(() failed. (I give up.) ");
                    return null;
                }
                // ----------------------------------------------------------

                if (otapi.OT_API_Message_GetDepth(strResponse2) > 0) {

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
                        System.out.println("tradeDataMarket.getDate():"+tradeDataMarket.getDate());
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
                            Long lScale = Long.valueOf(marketDetails.getGranularity());
                            Long lPrice = Long.valueOf(tradeDataRow[1]);    // this price is "per scale"
                            Long lQuantity = Long.valueOf(tradeDataRow[2]);    // Total overall quantity available
                            Long lScaleUnits = Long.valueOf(lQuantity / lScale);   // Number of scale units available in total quanity. (120 total at scale of 10, is 12 units.)
                            Long lTotalCost = Long.valueOf(lPrice * lScaleUnits); // // Total value of available units is price times scale units.
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
                // ----------------------------------------------------------
                OTAPI_Func theRequest3 = new OTAPI_Func(OTAPI_Func.FT.GET_NYM_MARKET_OFFERS, serverID, nymID);
                String strResponse3 = OTAPI_Func.SendRequest(theRequest3, "GET_NYM_MARKET_OFFERS");

                if (null == strResponse3) {
                    System.out.println("IN getMarketDetails: OTAPI_Func.SendRequest(() failed. (I give up.) ");
                    return null;
                }
                // ----------------------------------------------------------

                if (otapi.OT_API_Message_GetDepth(strResponse3) > 0) {

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
                        if(marketData.getAsset_type_id()!=null
                          && marketData.getCurrency_type_id()!=null
                          && marketData.getScale()!=null
                          && offerDataNym.getAsset_type_id()!=null
                          && offerDataNym.getScale()!=null
                          && offerDataNym.getCurrency_type_id()!=null
                          && marketData.getAsset_type_id().equals(offerDataNym.getAsset_type_id())
                          && marketData.getCurrency_type_id().equals(offerDataNym.getCurrency_type_id())
                          && marketData.getScale().equals(offerDataNym.getScale())
                                ){
                        nymDataRow[0] = offerDataNym.getTransaction_id();
                        nymDataRow[1] = offerDataNym.getSelling() == true ? "Ask" : "Bid";
                        nymDataRow[2] = offerDataNym.getAsset_acct_id();

                        nymGridData.put(offerDataNym.getTransaction_id(), nymDataRow);
                        }
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
}
