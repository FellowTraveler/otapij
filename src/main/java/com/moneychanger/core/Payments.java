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

import org.opentransactions.jni.core.otapi;
import com.moneychanger.core.util.OTAPI_Func;
import com.moneychanger.core.util.Utility;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vicky C
 */
public class Payments {

    public static Map getServerList(String nymID) {

        Map serverMap = new HashMap();

        if (nymID == null) {
            return serverMap;
        }

        int count = otapi.OT_API_GetServerCount();
        int indexKey = 0;
        for (int i = 0; i < count; i++) {
            String key = otapi.OT_API_GetServer_ID(i);
            String label = otapi.OT_API_GetServer_Name(key);
            if (key == null) {
                continue;
            }

            if (otapi.OT_API_IsNym_RegisteredAtServer(nymID, key) == 1) {
                serverMap.put((indexKey), new String[]{label, key});
                indexKey++;
            }
        }


        return serverMap;
    }

    private static Map getServerForRegNym(String nymID) {

        Map serverList = new HashMap();

        int count = 0;
        for (int i = 0; i < otapi.OT_API_GetServerCount(); i++) {
            String key = otapi.OT_API_GetServer_ID(i);
            if (key == null) {
                continue;
            }

            if (otapi.OT_API_IsNym_RegisteredAtServer(nymID, key) == 1) {
                String label = otapi.OT_API_GetServer_Name(key);
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

        String ledger = otapi.OT_API_LoadPaymentInbox(serverID, nymID);
        if (ledger == null) {
            return data;
        }
        int count = otapi.OT_API_Ledger_GetCount(serverID, nymID, nymID, ledger);

        for (int i = 0; i < count; i++) {

            String transactionID = otapi.OT_API_Ledger_GetTransactionIDByIndex(serverID, nymID, nymID, ledger, i);
            String transaction = otapi.OT_API_Ledger_GetTransactionByIndex(serverID, nymID, nymID, ledger, i);

            if (transaction == null) {
                System.out.println("getPaymentRecordbox Skip this record, since OT_API_Ledger_GetTransactionByIndex has returned null");
                continue;
            }
            String txnType = "";
            System.out.println("In getPaymentRecordbox, type of txn is :" + txnType);
            String amount = otapi.OT_API_Transaction_GetAmount(serverID, nymID, nymID, transaction);
            String referenceNumber = otapi.OT_API_Transaction_GetDisplayReferenceToNum(serverID, nymID, nymID, transaction);
            String timestamp = otapi.OT_API_Transaction_GetDateSigned(serverID, nymID, nymID, transaction);
            if (timestamp == null) {
                timestamp = "";
            } else {
                try {
                    timestamp = String.valueOf(new Date(Long.parseLong(timestamp) * 1000));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.out.println("getPaymentRecordbox Invalid number returned by timestmp:" + timestamp);
                }
            }
            String userTo = otapi.OT_API_Transaction_GetRecipientUserID(serverID, nymID, nymID, transaction);
            String nymUserID = userTo;
            String accountTo = otapi.OT_API_Transaction_GetRecipientAcctID(serverID, nymID, nymID, transaction);

            String accountOutboxID = accountTo;
            if (userTo == null) {
                userTo = "";
            } else {
                userTo = otapi.OT_API_GetNym_Name(nymUserID) == null ? nymUserID : otapi.OT_API_GetNym_Name(nymUserID);
            }
            if (accountTo == null) {
                accountTo = "";
            } else {
                accountTo = otapi.OT_API_GetAccountWallet_Name(accountOutboxID) == null ? accountOutboxID : otapi.OT_API_GetAccountWallet_Name(accountOutboxID);
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
        
        if (Utility.getAndProcessNymbox(unusedServerID, nymID, bWasMsgSent) == (-1)) {
            return data;

        }
        int count = otapi.OT_API_GetNym_OutpaymentsCount(nymID);

        for (int i = 0; i < count; i++) {
            String message = otapi.OT_API_GetNym_OutpaymentsContentsByIndex(nymID, i);
            String type = otapi.OT_API_GetNym_OutpaymentsContentsByIndex(nymID, i);
            if (type != null) {
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
            String messageBody = otapi.OT_API_GetNym_OutpaymentsContentsByIndex(nymID, i);
            ;

            rowData[0] = subject;
            String recepientNymID = otapi.OT_API_GetNym_OutpaymentsRecipientIDByIndex(nymID, i);
            String nymName = null;
            if (recepientNymID != null) {
                nymName = otapi.OT_API_GetNym_Name(recepientNymID);
            }
            rowData[1] = nymName == null ? recepientNymID == null ? "" : recepientNymID : nymName;
            String serverID = otapi.OT_API_GetNym_OutpaymentsServerIDByIndex(nymID, i);
            
            System.out.println("getPaymentOutboxRecords serverID: " + serverID);
            
            String serverName = null;
            if (serverID != null) {
                serverName = otapi.OT_API_GetServer_Name(serverID);
            }

            rowData[2] = serverName == null ? serverID == null ? "" : serverID : serverName;
            String isVerified = otapi.OT_API_Nym_VerifyOutpaymentsByIndex(nymID, i) == 1 ? "true" : "false";
            // This is the key
            rowData[3] = type;
            rowData[4] = String.valueOf(i);
            rowData[7] = messageBody == null ? "" : messageBody;
            rowData[8] = isVerified == null ? "" : isVerified;
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

        if (Utility.getAndProcessNymbox(serverID, nymID, bWasMsgSent) == (-1)) {
            return data;

        }

        if (!otapi.Exists("paymentInbox", serverID, nymID)) {
            System.out.println("getPaymentInboxData otapi.Exists returned false");
            return data;
        }

        String ledger = otapi.OT_API_LoadPaymentInbox(serverID, nymID);
        if (ledger == null) {
            return data;
        }
        int count = otapi.OT_API_Ledger_GetCount(serverID, nymID, nymID, ledger);

        for (int i = 0; i < count; i++) {

            String transactionID = otapi.OT_API_Ledger_GetTransactionIDByIndex(serverID, nymID, nymID, ledger, i);
            String transaction = otapi.OT_API_Ledger_GetTransactionByIndex(serverID, nymID, nymID, ledger, i);

            if (transaction == null) {
                System.out.println("getPaymentInboxData Skip this record, since OT_API_Ledger_GetTransactionByIndex has returned null");
                continue;
            }

            String txnType = otapi.OT_API_Transaction_GetType(serverID, nymID, nymID, transaction);
            System.out.println("In getPaymentInboxData, type of txn is :" + txnType);
            if (txnType != null) {
                if (txnType.equalsIgnoreCase("instrumentRejection")) {
                    txnType = "Rejected";
                }
                if (txnType.equalsIgnoreCase("instrumentNotice")) {
                    txnType = otapi.OT_API_Ledger_GetInstrument(serverID, nymID, nymID, ledger, i);
                    System.out.println("In getPaymentInboxData, instrument is :" + txnType);
                    if (txnType != null) {
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
            String amount = otapi.OT_API_Transaction_GetAmount(serverID, nymID, nymID, transaction);
            String referenceNumber = otapi.OT_API_Transaction_GetDisplayReferenceToNum(serverID, nymID, nymID, transaction);
            String timestamp = otapi.OT_API_Transaction_GetDateSigned(serverID, nymID, nymID, transaction);
            if (timestamp == null) {
                timestamp = "";
            } else {
                try {
                    timestamp = String.valueOf(new Date(Long.parseLong(timestamp) * 1000));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.out.println("getPaymentInboxData Invalid number returned by timestmp:" + timestamp);
                }
            }
            String userFrom = otapi.OT_API_Transaction_GetSenderUserID(serverID, nymID, nymID, transaction);
            String nymUserID = userFrom;
            String accountFrom = otapi.OT_API_Transaction_GetSenderAcctID(serverID, nymID, nymID, transaction);

            String accountInboxID = accountFrom;
            if (userFrom == null) {
                userFrom = "";
            } else {
                userFrom = otapi.OT_API_GetNym_Name(nymUserID) == null ? nymUserID : otapi.OT_API_GetNym_Name(nymUserID);
            }
            if (accountFrom == null) {
                accountFrom = "";
            } else {
                accountFrom = otapi.OT_API_GetAccountWallet_Name(accountInboxID) == null ? accountInboxID : otapi.OT_API_GetAccountWallet_Name(accountInboxID);
            }
            if (amount != null && amount.contains("-")) {
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

        if (null == nymID) {
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

        String plan = otapi.OT_API_ProposePaymentPlan(serverID, validFrom, validToDate, senderAcctID, senderNymID, planConsideration, recepientAcctID, recepientNymID, initPayAmt, initPayDelay, payPlanAmt, payPlanDelay, payPlanPeriod, payPlanLen, payPlanMax);

        if (plan == null) {
            System.out.println("proposePaymentPlan - OT_API_ProposePaymentPlan returned null");
            return false;
        }

        String pubKey = otapi.OT_API_LoadUserPubkey(recepientNymID);

        if (pubKey == null) {
            System.out.println("proposePaymentPlan - OT_API_LoadUserPubkey returned null for recipient nym:" + recepientNymID);
            return false;
        }

        OTAPI_Func theRequest = new OTAPI_Func(OTAPI_Func.FT.SEND_USER_INSTRUMENT, serverID, senderNymID, recepientNymID, pubKey, plan);
        System.out.println(" theRequest :" + theRequest);

        String strResponse = theRequest.SendRequest(theRequest, "SEND_USER_INSTRUMENT");
        System.out.println(" strResponse:" + strResponse);

        if (strResponse != null) {
            status = true;
        }

        return status;
    }

    public static Map getAccountList(String serverID, String nymID) {

        Map acctMap = new HashMap();

        if (serverID == null || nymID == null) {
            return acctMap;
        }

        int count = otapi.OT_API_GetAccountCount();
        int indexKey = 0;

        for (int i = 0; i < count; i++) {
            String accountID = otapi.OT_API_GetAccountWallet_ID(i);
            if (accountID == null) {
                continue;
            }
            String walletServerID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
            String walletNymID = otapi.OT_API_GetAccountWallet_NymID(accountID);

            if (walletServerID == null || walletNymID == null) {
                continue;
            }
            if (serverID.equals(walletServerID) && nymID.equals(walletNymID)) {
                acctMap.put((indexKey), new String[]{otapi.OT_API_GetAccountWallet_Name(accountID), accountID});
                indexKey++;
            }
        }


        return acctMap;
    }
}
