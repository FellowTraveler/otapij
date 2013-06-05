/************************************************************
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA1

 *                 M O N E Y C H A N G E R
 *
 *  Open Transactions:
 *       Financial Cryptography and Digital Cash
 *       Library, Protocol, API, Server, CLI, GUI 
 *    
 *       -- Anonymous Numbered Accounts.
 *       -- Untraceable Digital Cash.
 *       -- Triple-Signed Receipts.
 *       -- Cheques, Vouchers, Transfers, Inboxes.
 *       -- Basket Currencies, Markets, Payment Plans.
 *       -- Signed, XML, Ricardian-style Contracts.
 *       -- Scripted smart contracts.
 *    
 *  Copyright (C) 2010-2013 by "Fellow Traveler" (A pseudonym)
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
 *  https://github.com/FellowTraveler/Moneychanger
 *  https://github.com/FellowTraveler/Open-Transactions/wiki
 *
 *  WEBSITE:
 *  http://www.OpenTransactions.org/
 *    
 *  Components and licensing:
 *   -- Moneychanger..A Java client GUI.....LICENSE:.....GPLv3
 *   -- otlib.........A class library.......LICENSE:...LAGPLv3
 *   -- otapi.........A client API..........LICENSE:...LAGPLv3
 *   -- opentxs/ot....Command-line client...LICENSE:...LAGPLv3
 *   -- otserver......Server Application....LICENSE:....AGPLv3
 *  Github.com/FellowTraveler/Open-Transactions/wiki/Components
 *
 *  All of the above OT components were designed and written by
 *  Fellow Traveler, with the exception of Moneychanger, which
 *  was contracted out to Vicky C (bitcointrader4@gmail.com).
 *  The open-source community has since actively contributed.
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
Version: GnuPG v1.4.9 (Darwin)

iQIcBAEBAgAGBQJRSsfMAAoJEAMIAO35UbuOCTQQAJWUR6l+IbUGYPfudumDBZep
XWu5aUVXPt+HTetWobTT3VrSEoQTV+t3Qk10EHzIIQNIvDNkA3cfQod5xMk3CZgm
MuA1PTDZdmrcm1lf92rVULaiB2tHYuPKYryFfkmNcidoWaJAI0ny/AE4gSdopKuU
MCLhB+fWpPv+nK9Qjb8duvRyFjYeLxrnwjfy05zNIY5Fj4HsCzmf6G6xqbUOZzBA
Zc4RjfKeg9MVJl4ObIKhDfpicCTVZkgFPVGeok/KtmiPRgV1HtaHqib4RiN9VMkr
YKbOUOb931pukRJQv+z5fT1EQkSVBDO5Th2q7Nls5idMgtR2BPXxVhs+e8OM4IJK
W+1V0WHWHuE+6SRKQrPU4hAmXrtmGRtu474TTmPlW6dCqFgvLWBuxeTRCPf4l29T
ImEOxdjFwlMVoxsazE3KE/YMbX7IiZqgLx/C4OTPlz7BVLhphzPRXA7KhhRFi1Df
jie5oRhJ4zbTFQI8SKsjbx0H/4VpB+Vtjx6fOxnLUpjZAE7G6ZL2zOEK8rtiGeiH
0AWK2rKWP8oOMnQwBMP838WRtxFmaOIhvVqAngjynSmVouf1RKKU7y7/YQ0iVDcN
WqAhaZhvszQ6UCDAEi11rfdC2qt29Jds9lS41YGqvYaan+b50lr5u59Uknz2LC94
HS4/gWtdVEVnXDda0wk9
=CbRB
-----END PGP SIGNATURE-----
 **************************************************************/

package com.moneychanger.core;

import com.moneychanger.core.util.OTAPI_Func;
import com.moneychanger.core.util.Helpers;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.opentransactions.otapi.otapi;
import org.opentransactions.otapi.otapiJNI;
import org.opentransactions.otjavalib.util.Utility;

/**
 *
 * @author Vicky C
 */
public class Payments {

    public static boolean checkNym(String nymID, String serverID){
        boolean result = false;
        if (otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, serverID)) {
            result = true;
        }

        return result;
    }
    
    public static Map getServerList(String nymID) {

        Map serverMap = new HashMap();

        if (!Utility.VerifyStringVal(nymID)) {
            return serverMap;
        }

        int count = otapiJNI.OTAPI_Basic_GetServerCount();
        int indexKey = 0;
        for (int i = 0; i < count; i++) {
            String key = otapiJNI.OTAPI_Basic_GetServer_ID(i);
            String label = otapiJNI.OTAPI_Basic_GetServer_Name(key);

            if (!Utility.VerifyStringVal(key)) {
                continue;
            }

            if (otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, key)) {
                serverMap.put((indexKey), new String[]{label, key});
                indexKey++;
            }
        }


        return serverMap;
    }

    private static Map getServerForRegNym(String nymID) {

        Map serverList = new HashMap();

        int count = 0;
        for (int i = 0; i < otapiJNI.OTAPI_Basic_GetServerCount(); i++) {
            String key = otapiJNI.OTAPI_Basic_GetServer_ID(i);
            if (!Utility.VerifyStringVal(key)) {
                continue;
            }

            if (otapiJNI.OTAPI_Basic_IsNym_RegisteredAtServer(nymID, key)) {
                String label = otapiJNI.OTAPI_Basic_GetServer_Name(key);
                serverList.put(count, new String[]{label, key});
                count++;
            }
        }

        if (count == 0) {
            return null;
        }

        return serverList;
    }

//        private String[] columnNames = {"Txn #", "In Ref To", "From NYM", "To NYM", "Amount", "From Account Name", "To Account Name", "Type", "Date", "From NYM ID", "From Acct ID", "To Nym ID", "To Acct ID"};
    public static Map getPaymentRecordbox(String nymID, String serverID) throws InterruptedException {

        Map data = new HashMap();
        if (!otapi.Exists("paymentRecordbox", serverID, nymID)) {
            System.out.println("getPaymentRecordbox otapi.Exists returned false");
            return data;
        }

        String ledger = otapiJNI.OTAPI_Basic_LoadPaymentInbox(serverID, nymID);
        if (!Utility.VerifyStringVal(ledger)) {
            return data;
        }
        int count = otapiJNI.OTAPI_Basic_Ledger_GetCount(serverID, nymID, nymID, ledger);

        for (int i = 0; i < count; i++) {

            String transactionID = otapiJNI.OTAPI_Basic_Ledger_GetTransactionIDByIndex(serverID, nymID, nymID, ledger, i);
            String transaction = otapiJNI.OTAPI_Basic_Ledger_GetTransactionByIndex(serverID, nymID, nymID, ledger, i);

            if (!Utility.VerifyStringVal(transaction)) {
                System.out.println("getPaymentRecordbox Skip this record, since OT_API_Ledger_GetTransactionByIndex has returned null");
                continue;
            }
            String txnType = "";
            System.out.println("In getPaymentRecordbox, type of txn is :" + txnType);
            String amount = otapiJNI.OTAPI_Basic_Transaction_GetAmount(serverID, nymID, nymID, transaction);
            String referenceNumber = otapiJNI.OTAPI_Basic_Transaction_GetDisplayReferenceToNum(serverID, nymID, nymID, transaction);
            String timestamp = otapiJNI.OTAPI_Basic_Transaction_GetDateSigned(serverID, nymID, nymID, transaction);
            if (!Utility.VerifyStringVal(timestamp)) {
                timestamp = "";
            } else {
                try {
                    timestamp = String.valueOf(new Date(Long.parseLong(timestamp) * 1000));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.out.println("getPaymentRecordbox Invalid number returned by timestmp:" + timestamp);
                }
            }
            String userTo = otapiJNI.OTAPI_Basic_Transaction_GetRecipientUserID(serverID, nymID, nymID, transaction);
            String nymUserID = userTo;
            String accountTo = otapiJNI.OTAPI_Basic_Transaction_GetRecipientAcctID(serverID, nymID, nymID, transaction);

            String accountOutboxID = accountTo;
            if (!Utility.VerifyStringVal(userTo)) {
                userTo = "";
            } else {
                userTo = !Utility.VerifyStringVal(otapiJNI.OTAPI_Basic_GetNym_Name(nymUserID)) ? nymUserID : otapiJNI.OTAPI_Basic_GetNym_Name(nymUserID);
            }
            if (!Utility.VerifyStringVal(accountTo)) {
                accountTo = "";
            } else {
                accountTo = !Utility.VerifyStringVal(otapiJNI.OTAPI_Basic_GetAccountWallet_Name(accountOutboxID)) ? accountOutboxID : otapiJNI.OTAPI_Basic_GetAccountWallet_Name(accountOutboxID);
            }

            String[] row = new String[13];
            row[0] = transactionID;
            row[1] = referenceNumber;
            row[3] = amount;
            row[5] = txnType;
            row[2] = userTo;
            row[4] = accountTo;
            row[6] = timestamp;
            row[7] = String.valueOf(i);
            row[8] = nymUserID;
            row[9] = accountOutboxID;

            data.put(row[7], row);

        }
        return data;
    }

    public static Map getPaymentOutboxRecords(String nymID, String unusedServerID) throws InterruptedException {

        Map data = new HashMap();
        /*if (!otapi.Exists("paymentOutbox", unusedServerID, nymID)) {
        System.out.println("getPaymentOutboxRecords otapi.Exists returned false");
        return data;
        }*/

        System.out.println("getPaymentOutboxRecords nymID:" + nymID + " serverID:" + unusedServerID);

        Utility.OTBool bWasMsgSent = new Utility.OTBool (false);
        
        if (Helpers.getAndProcessNymbox(unusedServerID, nymID, bWasMsgSent) == (-1)) {
            return data;

        }
        int count = otapiJNI.OTAPI_Basic_GetNym_OutpaymentsCount(nymID);

        for (int i = 0; i < count; i++) {
            String message = otapiJNI.OTAPI_Basic_GetNym_OutpaymentsContentsByIndex(nymID, i);
            String type    = otapiJNI.OTAPI_Basic_GetNym_OutpaymentsContentsByIndex(nymID, i);
            if (Utility.VerifyStringVal(type)) {
                if (type.toUpperCase().contains("CHEQUE")) {
                    type = "CHEQUE";
                } else if (type.toUpperCase().contains("VOUCHER")) {
                    type = "VOUCHER";

                } else if (type.toUpperCase().contains("PAYMENT PLAN")) {
                    type = "PAYMENT PLAN";

                } else if (type.toUpperCase().contains("INVOICE")) {
                    type = "INVOICE";

                } else if (type.toUpperCase().contains("PURSE")) {
                    type = "PURSE";
                }
            } else {
                type = "";
            }

            String[] rowData = new String[9];

            String subject = "";
            String messageBody = otapiJNI.OTAPI_Basic_GetNym_OutpaymentsContentsByIndex(nymID, i);
            ;

            rowData[0] = subject;
            String recepientNymID = otapiJNI.OTAPI_Basic_GetNym_OutpaymentsRecipientIDByIndex(nymID, i);
            String nymName = null;
            if (Utility.VerifyStringVal(recepientNymID)) {
                nymName = otapiJNI.OTAPI_Basic_GetNym_Name(recepientNymID);
            }
            rowData[1] = !Utility.VerifyStringVal(nymName) ? !Utility.VerifyStringVal(recepientNymID) ? "" : recepientNymID : nymName;
            String serverID = otapiJNI.OTAPI_Basic_GetNym_OutpaymentsServerIDByIndex(nymID, i);
            
            System.out.println("getPaymentOutboxRecords serverID: " + serverID);
            
            String serverName = null;
            if (Utility.VerifyStringVal(serverID)) {
                serverName = otapiJNI.OTAPI_Basic_GetServer_Name(serverID);
            }

            rowData[2] = !Utility.VerifyStringVal(serverName) ? !Utility.VerifyStringVal(serverID) ? ""     : serverID : serverName;
            String isVerified = otapiJNI.OTAPI_Basic_Nym_VerifyOutpaymentsByIndex(nymID, i)        ? "true" : "false";
            // This is the key
            rowData[3] = type;
            rowData[4] = String.valueOf(i);
            rowData[7] = !Utility.VerifyStringVal(messageBody) ? "" : messageBody;
            rowData[8] = !Utility.VerifyStringVal(isVerified)  ? "" : isVerified;
            rowData[5] = recepientNymID;
            rowData[6] = serverID;
            data.put(rowData[4], rowData);
        }

        return data;
    }

    public static Map getPaymentInboxRecords(String nymID, String serverID) throws InterruptedException {

        Map data = new HashMap();

        System.out.println("getPaymentInboxData nymID:" + nymID + " serverID:" + serverID);

       Utility.OTBool bWasMsgSent = new Utility.OTBool (false);

        if (Helpers.getAndProcessNymbox(serverID, nymID, bWasMsgSent) == (-1)) {
            return data;

        }

        if (!otapi.Exists("paymentInbox", serverID, nymID)) {
            System.out.println("getPaymentInboxData otapi.Exists returned false");
            return data;
        }

        String ledger = otapiJNI.OTAPI_Basic_LoadPaymentInbox(serverID, nymID);
        if (!Utility.VerifyStringVal(ledger)) {
            return data;
        }
        int count = otapiJNI.OTAPI_Basic_Ledger_GetCount(serverID, nymID, nymID, ledger);

        for (int i = 0; i < count; i++) {

            String transactionID = otapiJNI.OTAPI_Basic_Ledger_GetTransactionIDByIndex(serverID, nymID, nymID, ledger, i);
            String transaction = otapiJNI.OTAPI_Basic_Ledger_GetTransactionByIndex(serverID, nymID, nymID, ledger, i);

            if (!Utility.VerifyStringVal(transaction)) {
                System.out.println("getPaymentInboxData Skip this record, since OT_API_Ledger_GetTransactionByIndex has returned null");
                continue;
            }

            String txnType = otapiJNI.OTAPI_Basic_Transaction_GetType(serverID, nymID, nymID, transaction);
            System.out.println("In getPaymentInboxData, type of txn is :" + txnType);
            if (Utility.VerifyStringVal(txnType)) {
                if (txnType.equalsIgnoreCase("instrumentRejection")) {
                    txnType = "Rejected";
                }
                if (txnType.equalsIgnoreCase("instrumentNotice")) {
                    txnType = otapiJNI.OTAPI_Basic_Ledger_GetInstrument(serverID, nymID, nymID, ledger, i);
                    System.out.println("In getPaymentInboxData, instrument is :" + txnType);
                    if (Utility.VerifyStringVal(txnType)) {
                        if (txnType.toUpperCase().contains("CHEQUE")) {
                            txnType = "CHEQUE";
                        } else if (txnType.toUpperCase().contains("VOUCHER")) {
                            txnType = "VOUCHER";

                        } else if (txnType.toUpperCase().contains("PAYMENT PLAN")) {
                            txnType = "PAYMENT PLAN";

                        } else if (txnType.toUpperCase().contains("INVOICE")) {
                            txnType = "INVOICE";

                        } else if (txnType.toUpperCase().contains("PURSE")) {
                            txnType = "PURSE";
                        }
                    } else {
                        txnType = "";
                    }
                }
            } else {
                txnType = "";
            }
            String amount = otapiJNI.OTAPI_Basic_Transaction_GetAmount(serverID, nymID, nymID, transaction);
            String referenceNumber = otapiJNI.OTAPI_Basic_Transaction_GetDisplayReferenceToNum(serverID, nymID, nymID, transaction);
            String timestamp = otapiJNI.OTAPI_Basic_Transaction_GetDateSigned(serverID, nymID, nymID, transaction);
            if (!Utility.VerifyStringVal(timestamp)) {
                timestamp = "";
            } else {
                try {
                    timestamp = String.valueOf(new Date(Long.parseLong(timestamp) * 1000));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.out.println("getPaymentInboxData Invalid number returned by timestmp:" + timestamp);
                }
            }
            String userFrom = otapiJNI.OTAPI_Basic_Transaction_GetSenderUserID(serverID, nymID, nymID, transaction);
            String nymUserID = userFrom;
            String accountFrom = otapiJNI.OTAPI_Basic_Transaction_GetSenderAcctID(serverID, nymID, nymID, transaction);

            String accountInboxID = accountFrom;
            if (!Utility.VerifyStringVal(userFrom)) {
                userFrom = "";
            } else {
                userFrom = otapiJNI.OTAPI_Basic_GetNym_Name(nymUserID) == null ? nymUserID : otapiJNI.OTAPI_Basic_GetNym_Name(nymUserID);
            }
            if (!Utility.VerifyStringVal(accountFrom)) {
                accountFrom = "";
            } else {
                accountFrom = otapiJNI.OTAPI_Basic_GetAccountWallet_Name(accountInboxID) == null ? accountInboxID : otapiJNI.OTAPI_Basic_GetAccountWallet_Name(accountInboxID);
            }
            if (Utility.VerifyStringVal(amount) && amount.contains("-")) {
                txnType = "INVOICE";
                amount = amount.substring(1, amount.length());
            }
            String[] row = new String[10];
            row[0] = transactionID;
            row[1] = referenceNumber;
            row[3] = amount;
            row[5] = txnType;
            row[2] = userFrom;
            row[4] = accountFrom;
            row[6] = timestamp;
            row[7] = String.valueOf(i);
            row[8] = nymUserID;
            row[9] = accountInboxID;

            data.put(row[7], row);

        }
        return data;
    }

    public static Map getPaymentInboxData(String nymID) throws InterruptedException {

        System.out.println("getPaymentInboxData, nymID:" + nymID);

        Map data = null;

        if (!Utility.VerifyStringVal(nymID)) {
            return data;
        }

        data = getServerForRegNym(nymID);

        if (data == null || data.isEmpty()) {
            data = new HashMap();
            data.put("Popup", "Popup Dialog");
            return data;
        }

        Map serverList = new HashMap();

        serverList.put("serverList", data);
        return serverList;




        // String serverID = ((String[]) data.get(0))[1];

        // The caller now does this.
//      Utility.getInboxAccount(serverID, nymID, accountID);

        //return getPaymentInboxRecords(nymID, serverID);
    }

    public static boolean proposePaymentPlan(String serverID, String validToDate, String senderAcctID, String senderNymID, String planConsideration, String recepientAcctID, String recepientNymID, String initPayAmt, String initPayDelay, String payPlanAmt, String payPlanDelay, String payPlanPeriod, String payPlanLen, String payPlanMax) {

        String validFrom = "0";
        boolean status = false;

        System.out.println("proposePaymentPlan - serverID:" + serverID+",validToDate:"+validToDate+",validFrom:"+validFrom+",senderAcctID:"+senderAcctID+",senderNymID:"+senderNymID+",planConsideration:"+planConsideration+",recepientAcctID:"+recepientAcctID+",recepientNymID:"+recepientNymID+",initPayAmt:"+initPayAmt);
        System.out.println("proposePaymentPlan - initPayDelay:"+initPayDelay+",payPlanAmt:"+payPlanAmt+",payPlanDelay:"+payPlanDelay+",payPlanPeriod:"+payPlanPeriod+",payPlanLen:"+payPlanLen+",payPlanMax:"+payPlanMax);

        String plan = otapiJNI.OTAPI_Basic_ProposePaymentPlan(serverID, validFrom, validToDate, senderAcctID, senderNymID, planConsideration, recepientAcctID, recepientNymID, initPayAmt, initPayDelay, payPlanAmt, payPlanDelay, payPlanPeriod, payPlanLen, Integer.valueOf(payPlanMax).intValue());

        if (!Utility.VerifyStringVal(plan)) {
            System.out.println("proposePaymentPlan - OT_API_ProposePaymentPlan returned null");
            return false;
        }

        String pubKey = otapiJNI.OTAPI_Basic_LoadUserPubkey_Encryption(recepientNymID);

        if (!Utility.VerifyStringVal(pubKey)) {
            System.out.println("proposePaymentPlan - OT_API_LoadUserPubkey returned null for recipient nym:" + recepientNymID);
            return false;
        }

        OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.SEND_USER_INSTRUMENT, serverID, senderNymID, recepientNymID, pubKey, plan);
        System.out.println(" theRequest :" + theRequest);

        String strResponse = theRequest.SendRequest(theRequest, "SEND_USER_INSTRUMENT");
        System.out.println(" strResponse:" + strResponse);

        if (Utility.VerifyStringVal(strResponse)) {
            status = true;
        }

        return status;
    }

    public static Map getAccountList(String serverID, String nymID) {

        Map acctMap = new HashMap();

        if (!Utility.VerifyStringVal(serverID) || !Utility.VerifyStringVal(nymID)) {
            return acctMap;
        }

        int count = otapiJNI.OTAPI_Basic_GetAccountCount();
        int indexKey = 0;

        for (int i = 0; i < count; i++) {
            String accountID = otapiJNI.OTAPI_Basic_GetAccountWallet_ID(i);
            if (!Utility.VerifyStringVal(accountID)) {
                continue;
            }
            String walletServerID = otapiJNI.OTAPI_Basic_GetAccountWallet_ServerID(accountID);
            String walletNymID = otapiJNI.OTAPI_Basic_GetAccountWallet_NymID(accountID);

            if (!Utility.VerifyStringVal(walletServerID) || !Utility.VerifyStringVal(walletNymID)) {
                continue;
            }
            if (serverID.equals(walletServerID) && nymID.equals(walletNymID)) {
                acctMap.put((indexKey), new String[]{otapiJNI.OTAPI_Basic_GetAccountWallet_Name(accountID), accountID});
                indexKey++;
            }
        }


        return acctMap;
    }
}
