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
package com.wrapper.core.util;

import java.util.ArrayList;
import java.util.List;

import com.wrapper.core.Account;
import com.wrapper.core.OpenTransactionAccount;
import com.wrapper.core.dataobjects.OTDetails;
import com.wrapper.core.jni.AddressBook;
import com.wrapper.core.jni.MarketList;
import com.wrapper.core.jni.OTCallback;
import com.wrapper.core.jni.OTCaller;
import com.wrapper.core.jni.OfferListMarket;
import com.wrapper.core.jni.OfferListNym;
import com.wrapper.core.jni.Storable;
import com.wrapper.core.jni.StoredObjectType;
import com.wrapper.core.jni.StringMap;
import com.wrapper.core.jni.TradeListMarket;
import com.wrapper.core.jni.TradeListNym;
import com.wrapper.core.jni.WalletData;
import com.wrapper.core.jni.otapi;
import com.wrapper.ui.Load;
import com.wrapper.ui.MainPage;
import com.wrapper.ui.model.AccountTableModel;
import com.wrapper.ui.panels.OpenTransactionAccountBottomPanel;
import com.wrapper.ui.panels.OpenTransactionAccountTopPanel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.LookAndFeel;

/**
 *
 * @author Vicky C and Cameron
 */
public class Utility {

    private static Object settingsObj;
    private static String nymID;
    private static List basketExistingAssets = new ArrayList();

    public static List getBasketExistingAssets() {
        return basketExistingAssets;
    }

    public static void addBasketExistingAssets(String assetID) {
        Utility.basketExistingAssets.add(assetID);
    }

    public static void clearBasketExistingAssets() {
        Utility.basketExistingAssets.clear();
    }

    public static void setBasketExistingAssets(List basketExistingAssets) {
        Utility.basketExistingAssets = basketExistingAssets;
    }

    public static String getMinTransfer() {
        return minTransfer;
    }

    public static void setMinTransfer(String minTransfer) {
        Utility.minTransfer = minTransfer;
    }

    public static String getSubCurrency() {
        return subCurrency;
    }

    public static void setSubCurrency(String subCurrency) {
        Utility.subCurrency = subCurrency;
    }

    public static boolean getCancelBasket() {
        return cancelBasket;
    }

    public static void setCancelBasket(boolean cancelBasket) {
        Utility.cancelBasket = cancelBasket;
    }
    private static String minTransfer;
    private static String subCurrency;
    private static boolean cancelBasket = false;

    public static String getNymID() {
        return nymID;
    }

    public static void setNymID(String nymID) {
        Utility.nymID = nymID;
    }

    public static Object getSettingsObj() {
        return settingsObj;
    }

    public static void setSettingsObj(Object settingsObj) {
        Utility.settingsObj = settingsObj;
    }
    private static String dataFolder;

    public static String getDataFolder() {
        return Utility.dataFolder;
    }

    public static void setDataFolder(String dataFolder) {
        Utility.dataFolder = dataFolder;
    }

    // <editor-fold defaultstate="collapsed" desc="Add Directory To Runtime" >
    public static void addDirToRuntime(String javaPaths) throws IOException {

        try {
            Field field = ClassLoader.class.getDeclaredField("usr_paths");
            field.setAccessible(true);

            List<String> pathList = new ArrayList<String>();
            pathList.addAll(Arrays.asList((String[]) field.get(null)));
            pathList.addAll(Arrays.asList(javaPaths.split(File.pathSeparator)));

            Collection<String> pathSet = new HashSet<String>();
            Iterator<String> listIterator = pathList.iterator();
            String path;
            while (listIterator.hasNext()) {
                path = listIterator.next(); //.toLowerCase();
                if (!path.equalsIgnoreCase(".")) {
                    pathSet.add(path);
                }
            }

            StringBuilder pathsString = new StringBuilder();
            Iterator<String> setIterator = pathSet.iterator();
            while (setIterator.hasNext()) {
                pathsString.append(setIterator.next());
                if (setIterator.hasNext()) {
                    pathsString.append(File.pathSeparator);
                }
            }

            field.set(null, pathSet.toArray(new String[0]));
            System.setProperty("java.library.path", pathsString.toString());

            System.out.println("Utility.addDirToRuntime: Setting java.library.path: " + pathsString.toString());

        } catch (IllegalAccessException e) {
            throw new IOException("Failed to get permissions to set library path");
        } catch (NoSuchFieldException e) {
            throw new IOException("Failed to get field handle to set library path");
        }

    }
    // </editor-fold>
    public static Object obj;

    public static Object getObj() {
        return obj;
    }

    public static void setObj(Object obj) {
        Utility.obj = obj;
    }

    // OT Helpers
    public static void reloadOTDetails(String accountID) {
        Account account = new OpenTransactionAccount();
        Object details = account.getAccountDetails(accountID);
        OTDetails otDetails = (OTDetails) details;
        populateOTDetails(otDetails);

        JTable table = MainPage.getAccountTable();
        ((AccountTableModel) table.getModel()).setValueAt(otDetails.getBalance(), table.getSelectedRow(), 1);
    }

    public static void populateOTDetails(OTDetails otDetails) {
        if (otDetails == null) {
            return;
        }
        OpenTransactionAccountTopPanel.populateOTDetails(otDetails);
        OpenTransactionAccountBottomPanel.populateOTDetails(otDetails);

        System.out.println("IN populateOTDetails");

    }
    private static String basketXAcct;
    private static boolean basketXCancelled;

    public static boolean isBasketXCancelled() {
        return basketXCancelled;
    }

    public static void setBasketXCancelled(boolean basketXCancelled) {
        Utility.basketXCancelled = basketXCancelled;
    }

    public static String getBasketXAcct() {
        return basketXAcct;
    }

    public static void setBasketXAcct(String basketXAcct) {
        Utility.basketXAcct = basketXAcct;
    }
    private static boolean loadNymTrades = false;

    public static boolean isLoadNymTrades() {
        return loadNymTrades;
    }

    public static void setLoadNymTrades(boolean loadNymTrades) {
        Utility.loadNymTrades = loadNymTrades;
    }
    private static OTCallback g_theCallback = null;

    public static OTCallback getG_theCallback() {
        return g_theCallback;
    }

    public static void setG_theCallback(OTCallback g_theCallback) {
        Utility.g_theCallback = g_theCallback;
    }
    private static OTCaller g_theCaller;

    public static OTCaller getG_theCaller() {
        return g_theCaller;
    }

    public static void setG_theCaller(OTCaller g_theCaller) {
        Utility.g_theCaller = g_theCaller;
    }
    public static Object otDepositCash;

    public static Object getOtDepositCash() {
        return otDepositCash;
    }

    public static void setOtDepositCash(Object otDepositCash) {
        Utility.otDepositCash = otDepositCash;
    }

    // Get Helpers:
    public static String getKey(Map map, String value) {

        if ("All".equalsIgnoreCase(value)) {
            return "ALL";
        }

        if (value == null) {
            return null;
        }

        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            if (map.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }

    // No need to deal with getRequest here when failure, since the calling
    // function already goes through that crap before we get here.
    public static int processNymbox(String serverID, String nymID) {
        // ------------------------------------------
        // Send message..
        otapi.OT_API_FlushMessageBuffer();

        int nReceiptCount = otapi.OT_API_processNymbox(serverID, nymID);
        // ------------------------------------------
        if ((-1) == nReceiptCount) {
            otapi.OT_API_Output(0, " Utility.processNymbox: Failure. OT_API_processNymbox() returned -1. \n");
            return (-1);
        } // ------------------------------------------
        else if (0 == nReceiptCount) {
            return 0;	// Nymbox is empty, thus no need to process it.
        }
        // ------------------------------------------
        Utility.delay();
        // ------------------------------------------
        // Pop the reply buffer and check for success. If so, send the next
        // message (processNymbox).
        String serverResponse = otapi.OT_API_PopMessageBuffer();

        if ((serverResponse == null)
                || (serverResponse.length() < 10)
                || (0 == otapi.OT_API_Message_GetSuccess(serverResponse))) {
            nReceiptCount = (-1);
            System.out.println("Failure in processNymbox : Response from server " + serverResponse);
        }
        return nReceiptCount;
    }

    // Called by getAndProcessNymbox.
    public static boolean getNymboxLowLevel(String serverID, String nymID) {
        // ------------------------------------------
        // Send message..
        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getNymbox(serverID, nymID);
        // ------------------------------------------
        Utility.delay();
        // ------------------------------------------
        // Pop the reply buffer and check for success. If so, send the next
        // message (processNymbox).
        String serverResponse = otapi.OT_API_PopMessageBuffer();
        if ((serverResponse != null)
                && (serverResponse.length() > 10)
                && (1 == otapi.OT_API_Message_GetSuccess(serverResponse))) {
            return true;
        } else {
            System.out.println("Failure in getNymboxLowLevel : Response from server " + serverResponse);
        }
        return false;
    }

    public static int getAndProcessNymbox(String serverID, String nymID) {
        // ------------------------------------------  
        if (Utility.getNymboxLowLevel(serverID, nymID)) {
            if (Utility.insureHaveAllBoxReceipts(serverID, nymID, nymID, 0)) // nBoxType = 0 aka nymbox
            {
                return Utility.processNymbox(serverID, nymID);
            } else {
                System.out.println("Utility.getAndProcessNymbox(): Utility.insureHaveAllBoxReceipts failed. (I give up.)");
            }
        } else if (Utility.getRequestNumber(serverID, nymID)) // this might be out of sync, if it failed... we'll re-sync, and re-try.
        {
            if (Utility.getNymboxLowLevel(serverID, nymID)) {
                if (Utility.insureHaveAllBoxReceipts(serverID, nymID, nymID, 0)) // nBoxType = 0 aka nymbox
                {
                    return Utility.processNymbox(serverID, nymID);
                } else {
                    System.out.println("Utility.getAndProcessNymbox(): Utility.getNymboxLowLevel failed, then getRequestNumber() succeeded, then getNymboxLowLevel succeeded, then insureHaveAllBoxReceipts failed. (I give up.)");
                }
            } else {
                System.out.println("Utility.getAndProcessNymbox(): Utility.getNymboxLowLevel failed, then Utility.getRequestNumber succeeded, then Utility.getNymboxLowLevel failed again. (I give up.)");
            }
        } else {
            System.out.println("Utility.getAndProcessNymbox(): Utility.getNymboxLowLevel failed, then Utility.getRequestNumber failed. (I give up.)");
        }
        return (-1);
    }

    public static boolean getRequestNumber(String serverID, String nymID) {
        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getRequest(serverID, nymID);
        Utility.delay();
        String strReply = otapi.OT_API_PopMessageBuffer();

        System.out.println("IN getRequestNumber " + strReply);

        if (strReply == null || strReply.length() < 10) {
            System.out.println("Utility.getRequestNumber(): null server reply. Perhaps the receive_fail_ms in client.cfg needs to be set to a higher value?");
            return false;
        }

        if (1 == otapi.OT_API_Message_GetSuccess(strReply)) {
            return true;
        }

        // Hmm -- we got the reply, but it was a failure.
        System.out.println("FAILURE in Utility.getRequestNumber(). Perhaps the receive_fail_ms in client.cfg needs to be set to a higher value?");

        return false;
    }

    // --------------------------------------------------------------
    // called by getBoxReceiptWithErrorCorrection
    public static boolean getBoxReceiptLowLevel(String serverID, String nymID, String accountID, int nBoxType, String strTransactionNum) {
        // ------------------------------------------
        // Send message..
        otapi.OT_API_FlushMessageBuffer();
        otapi.OT_API_getBoxReceipt(serverID, nymID, accountID, nBoxType, strTransactionNum);
        // ------------------------------------------
        Utility.delay();
        // ------------------------------------------
        // Pop the reply buffer and check for success. If so, send the next
        // message (processNymbox).
        String serverResponse = otapi.OT_API_PopMessageBuffer();
        if ((serverResponse != null)
                && (serverResponse.length() > 10)
                && (1 == otapi.OT_API_Message_GetSuccess(serverResponse))) {
            return true;
        } else {
            System.out.println("Failure in getBoxReceiptLowLevel : Response from server " + serverResponse);
        }
        return false;
    }

    // called by insureHaveAllBoxReceipts
    public static boolean getBoxReceiptWithErrorCorrection(String serverID, String nymID, String accountID, int nBoxType, String strTransactionNum) {
        // ------------------------------------------
        if (Utility.getBoxReceiptLowLevel(serverID, nymID, accountID, nBoxType, strTransactionNum)) {
            return true;
        } else if (Utility.getRequestNumber(serverID, nymID)) // this might be out of sync, if it failed... we'll re-sync, and re-try.
        {
            if (Utility.getBoxReceiptLowLevel(serverID, nymID, accountID, nBoxType, strTransactionNum)) {
                return true;
            } else {
                System.out.println("Utility.getBoxReceiptWithErrorCorrection(): Utility.getBoxReceiptLowLevel failed, then Utility.getRequestNumber succeeded, then Utility.getBoxReceiptLowLevel failed again. (I give up.)");
            }
        } else {
            System.out.println("Utility.getBoxReceiptWithErrorCorrection(): Utility.getBoxReceiptLowLevel failed, then Utility.getRequestNumber failed. (I give up.)");
        }
        return false;
    }

    // This function assumes you just downloaded the latest version of the box (inbox, outbox, or nymbox)
    // and its job is to make sure all the related box receipts are downloaded as well and available, though
    // not necessarily loaded into memory. (Yet.)
    //
    public static boolean insureHaveAllBoxReceipts(String serverID, String nymID, String accountID, int nBoxType) {

        String ledger = null;
        switch (nBoxType) {
            // The "Verify" versions of these load functions actually tries to 
            // load all the box receipts. Therefore I use the "NoVerify" version,
            // which stops at loading the abbreviations. That way I can iterate
            // through them and download the box receipt for each, as necessary.
            //
            case 0:
                ledger = otapi.OT_API_LoadNymboxNoVerify(serverID, nymID);
                break;
            case 1:
                ledger = otapi.OT_API_LoadInboxNoVerify(serverID, nymID, accountID);
                break;
            case 2:
                ledger = otapi.OT_API_LoadOutboxNoVerify(serverID, nymID, accountID);
                break;
            default:
                System.out.println("Utility.insureHaveAllBoxReceipts(): Error. Expected nBoxType of 0,1,2 (nymbox, inbox, or outbox.)");
                return false;
        }

        // ----------------------------------------------
        // Unable to load or verify inbox/outbox/nymbox
        // Notice I don't call VerifyAccount() here (not that the API even exposes
        // that method) but why not? Because that method tries to load up all the
        // box receipts, in addition to verifying the signature. Therefore I call
        // "Load XXXX NoVerify()", avoiding all that, then I verify the Signature
        // itself. That's because this function's whole point is to find out what
        // the box receipts are, and download them from the server. No point trying
        // to load them before that time, when I know it will fail.
        // 
        if ((null == ledger) || (ledger.length() < 10) || (0 == otapi.OT_API_VerifySignature(nymID, ledger))) {
            System.out.println("Utility.insureHaveAllBoxReceipts(): Unable to load or verify signature on ledger. (Failure.)");
            return false;
        }

        // ----------------------------------------------
        // At this point, the box is definitely loaded. Next we'll iterate the receipts
        // within, and for each, verify that the Box Receipt already exists. If not,
        // then we'll download it using getBoxReceiptLowLevel(). If any download fails,
        // then we break out of the loop (without continuing on to try the rest.)
        //
        boolean bReturnValue = true; // Assuming an empty box, we return success by default.

        int nReceiptCount = otapi.OT_API_Ledger_GetCount(serverID, nymID, accountID, ledger);

        if (nReceiptCount > 0) {
            for (int i = 0; i < nReceiptCount; i++) {
                String strTransactionNum = otapi.OT_API_Ledger_GetTransactionIDByIndex(serverID, nymID, accountID, ledger, i);
                if ((null != strTransactionNum) && !strTransactionNum.equals("-1")) {
                    Long lTransactionNum = Long.valueOf(strTransactionNum);
                    if (lTransactionNum > 0) {
                        boolean bHaveBoxReceipt = (1 == otapi.OT_API_DoesBoxReceiptExist(serverID, nymID, accountID, nBoxType, strTransactionNum)) ? true : false;
                        if (!bHaveBoxReceipt) {
                            System.out.println("Utility.insureHaveAllBoxReceipts(): Downloading box receipt to add to my collection...");

                            boolean bDownloaded = Utility.getBoxReceiptWithErrorCorrection(serverID, nymID, accountID, nBoxType, strTransactionNum);

                            if (!bDownloaded) {
                                System.out.println("Utility.insureHaveAllBoxReceipts(): Failed downloading box receipt. (Skipping any others.) Transaction number: " + strTransactionNum);
                                bReturnValue = false;
                                break;
                                // No point continuing to loop and fail 500 times, when getBoxReceiptWithErrorCorrection() already failed
                                // even doing the getRequest() trick and everything, and whatever retries are inside OT, before it finally
                                // gave up.
                            }
                            // else (Download success.)
                        }
                        // else we already have the box receipt, no need to download again.
                    } else {
                        System.out.println("Utility.insureHaveAllBoxReceipts(): Error: TransactionNum less-than-or-equal-to 0.");
                    }
                } else {
                    System.out.println("Utility.insureHaveAllBoxReceipts(): Error: TransactionNum was null, when trying to read it based on the index (within bounds, too.)");
                }
            } // for
        }        // ---------------------------------------------

        return bReturnValue;
    }

    /*
    static void getBoxReceipt(  const std::string	SERVER_ID,
    const std::string	USER_ID,
    const std::string	ACCT_ID,	// If for Nymbox (vs inbox/outbox) then pass USER_ID in this field also.
    const int		nBoxType,	// 0/nymbox, 1/inbox, 2/outbox
    const std::string	TRANSACTION_NUMBER);

    static bool DoesBoxReceiptExist(const std::string	SERVER_ID,
    const std::string	USER_ID,
    const std::string	ACCT_ID,	// If for Nymbox (vs inbox/outbox) then pass USER_ID in this field also.
    const int		nBoxType,	// 0/nymbox, 1/inbox, 2/outbox
    const std::string	TRANSACTION_NUMBER);
     */
    // If the transaction number requests fail, this function will try to resync
    // the request number. But you still have to call getRequest() yourself if
    // you have a failure in your own function, since you might already have
    // enough transaction numbers, and thus this function will never get called,
    // even if your request number IS out of sync. Sorry :-)
    //
    public static boolean getTransactionNumbers(String serverID, String nymID) {

        boolean bSuccess = true;
        otapi.OT_API_FlushMessageBuffer();
        // -------------------------------------------
        int nFailures = 0;
        for (int i = 0; i < Configuration.getNbrTransactionCount(); i++) {
            otapi.OT_API_getTransactionNumber(serverID, nymID); // Request.
            Utility.delay();
            String serverResponse = otapi.OT_API_PopMessageBuffer();
            if ((serverResponse == null)
                    || (serverResponse.length() < 10)) {
                System.out.println("Utility.getTransactionNumbers(): null server reply. Perhaps the receive_fail_ms in client.cfg needs to be set to a higher value?");
                bSuccess = false;
            }
            // -----------------------------------------------------------
            // REPLY: FAILURE
            //
            if (0 == otapi.OT_API_Message_GetSuccess(serverResponse)) {
                System.out.println("Utility.getTransactionNumbers(): Server refused my request for a new transaction number! (Maybe reached the limit?): " + serverResponse);
                bSuccess = false;
            }

            // We got the reply, but it failed.
            // Or, it was null.
            //
            if (false == bSuccess) {
                ++nFailures; // Next time, won't be the first time anymore.

                // If it failed, the first time we will call getRequest() and then
                // try again (as normal.)
                // But then if it failed AGAIN after that? We cannot recover, so
                // in that case, we'll return.
                //
                if (1 == nFailures) // First failure
                {
                    // Resync success!
                    if (true == Utility.getRequestNumber(serverID, nymID)) {
                        --i;    // Give this guy an extra round.     
//                          --nFailures; // getRequest WORKED, so we set the failures counter back one.
                        // I commented this out because we only get past this spot if success. Therefore
                        // if the next call to OT_API_getTransactionNumber() (at the top of this loop)
                        // fails AGAIN, even after getRequest() success, then I need to TRY SOMETHING ELSE.
                        // No point succeeding, then failing, then succeeding, then failing, then succeeding,
                        // then failing, then succeeding, then failing, 10 times before returning... right?
                        bSuccess = true; // for now.
                        continue;
                        // Now that we got the request number, we'll go around the
                        // loop and try to get the next transaction number...
                    } else {
                        System.out.println("Utility.getTransactionNumbers(): OT_API_getTransactionNumber() failed, then my call to Utility.getRequestNumber() FAILED. (I don't know what else to do.)");
                        return false;
                    }
                } // At this point, I know that getRequestNumber() is SUCCEEDING,
                // (since we would have returned on iteration 1 if it had failed.)
                // yet that the getTransactionNumber() is STILL FAILING after that.
                // The likely culprit is that I've reached my limit of #s. Meaning
                // the server already put my limit into my nymbox already, and I
                // can never resync unless I get and process all those #s out of my 
                // Nymbox... And also I can't get any more numbers probably, until I
                // use some of those new ones up first, that are sitting in my Nymbox.)
                else if (2 == nFailures) // second failure
                {
                    if (Utility.getAndProcessNymbox(serverID, nymID) != (-1)) {
                        // getAndProcessNymbox() worked. But we could still be at our max transaction #s,
                        // so we might need to cut this loop short... I'll try to keep going... but if failures
                        // keep happening, we'll just return after this. The caller probably checks the overall
                        // count before and after calling this function, and even if I don't finish looping,
                        // I might still have plenty of numbers now, due to processing my nymbox. Therefore,
                        // if the third failure happens, it might STILL be a form of success. The caller might
                        // STILL determine that I now have enough numbers to keep going, and he then continues
                        // on to his next function call as if everything is a success.
                        --i;    // Give this guy an extra round.                         
                        bSuccess = true;
                        continue;
                    } else {
                        System.out.println("Utility.getTransactionNumbers(): While Utility.getRequestNumber() worked, OT_API_getTransactionNumber() is STILL failing, so I called Utility.getAndProcessNymbox(), but it didn't work. I don't know what else to do.");
                        return false;
                    }
                } else // third failure.
                {
                    // Here I cut things short and return. BUT we still might have enough numbers by this
                    // point, from the processNymbox call. The caller will just count them again before
                    // deciding whether to go on to his next call.
                    //
                    System.out.println("Utility.getTransactionNumbers(): OT_API_getTransactionNumber(): Returned success==false, meaning I probably have my limit of transaction numbers already signed out.");

                    // This time we don't set --i since we're not doing any more rounds.
                    // We also don't set bSuccess=true since we ALREADY processed Nymbox,
                    // and we're immediately returning anyway. We don't continue because
                    // we are now convinced that it will just keep failing. However,
                    // there MIGHT be enough numbers now, so the caller might still be
                    // happy!
                }

                // At this point, getTransactionNumber() failed, AND getRequestNumber() succeeded but then getTransactionNumber() failed again,
                // then getAndProcessNymbox() was called, and SUCCEEDED, but then getTransactionNumber() failed AGAIN! However, at this point
                // that could very well be because I have my max of numbers, so I can still RETURN TRUE here.
                //
                return true;

            } // if false==bSuccess
        } // for loop
        // ***************************************************************
        // By this point, it means we iterated the loop above with no failures.
        // If there were failures, we successfully recovered from them and finished
        // out the entire set of Transaction numbers. Thus, now it's time to retrieve
        // those numbers (sign-off on them), so we can actually use them for something.
        // (For whatever the caller was planning to use them for.)
        //
        if (bSuccess) {
            boolean b2 = (Utility.getAndProcessNymbox(serverID, nymID) != (-1)); // already logs inside here, if failure.

            if (false == b2) {
                System.out.println("Utility.getTransactionNumbers(): While all the calls to OT_API_getTransactionNumber() seemed to work, I was unable to get or process the nymbox at the end of it all.");
            }

            return b2;
            // ----------------------------------           
        } // (else already logs above.)

        return false;
        // -------------------------------
    }

    public static String getCreditsFile(String fileName) {
        return otapi.QueryPlainString(fileName);
    }

    public static Point getLocation(Dimension componentDimension) {

        Point center = new Point(0, 0);
        Dimension toolkitDimension = Toolkit.getDefaultToolkit().getScreenSize();
        center.x = center.x + (toolkitDimension.width - componentDimension.width) / 2;
        center.y = center.y + (toolkitDimension.height - componentDimension.height) / 2;

        return center;
    }

    public static void getKeyFromName(String name) {
    }

    public static WalletData getWalletData() {

        WalletData walletData = null;
        Storable storable = null;
        if (otapi.Exists("moneychanger", "gui_wallet.dat")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_WALLET_DATA, "moneychanger", "gui_wallet.dat");
            if (storable == null) {
                return null;
            }
            walletData = WalletData.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_WALLET_DATA);
            if (storable == null) {
                return null;
            }
            walletData = WalletData.ot_dynamic_cast(storable);
        }

        return walletData;
    }

    public static MarketList getMarketList(String serverID) {

        MarketList marketList = null;
        Storable storable = null;
        if (otapi.Exists("markets", serverID, "market_data.bin")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_MARKET_LIST, "markets", serverID, "market_data.bin");
            if (storable == null) {
                return null;
            }
            marketList = MarketList.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_MARKET_LIST);
            if (storable == null) {
                return null;
            }
            marketList = MarketList.ot_dynamic_cast(storable);
        }

        return marketList;
    }

    public static OfferListMarket getMarketOffer(String serverID, String marketID) {

        OfferListMarket offerListMarket = null;
        Storable storable = null;
        if (otapi.Exists("markets", serverID, "offers", marketID + ".bin")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_OFFER_LIST_MARKET, "markets", serverID, "offers", marketID + ".bin");
            if (storable == null) {
                return null;
            }
            offerListMarket = OfferListMarket.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_OFFER_LIST_MARKET);
            if (storable == null) {
                return null;
            }
            offerListMarket = OfferListMarket.ot_dynamic_cast(storable);
        }

        return offerListMarket;
    }

    public static TradeListNym getNYMTrades(String serverID, String nymID) {

        TradeListNym tradeListNym = null;
        Storable storable = null;
        if (otapi.Exists("nyms", "trades", serverID, nymID)) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_TRADE_LIST_NYM, "nyms", "trades", serverID, nymID);
            if (storable == null) {
                return null;
            }
            tradeListNym = TradeListNym.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_TRADE_LIST_NYM);
            if (storable == null) {
                return null;
            }
            tradeListNym = TradeListNym.ot_dynamic_cast(storable);
        }

        return tradeListNym;
    }

    public static OfferListNym getNYMOffer(String serverID, String nymID) {

        OfferListNym offerListNym = null;
        Storable storable = null;
        if (otapi.Exists("nyms", serverID, "offers", nymID + ".bin")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_OFFER_LIST_NYM, "nyms", serverID, "offers", nymID + ".bin");
            if (storable == null) {
                return null;
            }
            offerListNym = OfferListNym.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_OFFER_LIST_NYM);
            if (storable == null) {
                return null;
            }
            offerListNym = OfferListNym.ot_dynamic_cast(storable);
        }

        return offerListNym;
    }

    public static TradeListMarket getMarketTradeList(String serverID, String marketID) {

        TradeListMarket tradeListMarket = null;
        Storable storable = null;
        if (otapi.Exists("markets", serverID, "recent", marketID + ".bin")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_TRADE_LIST_MARKET, "markets", serverID, "recent", marketID + ".bin");
            if (storable == null) {
                return null;
            }
            tradeListMarket = TradeListMarket.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_TRADE_LIST_MARKET);
            if (storable == null) {
                return null;
            }
            tradeListMarket = TradeListMarket.ot_dynamic_cast(storable);
        }

        return tradeListMarket;
    }

    public static AddressBook getAddressBook() {

        AddressBook addressBook = null;
        Storable storable = null;
        if (otapi.Exists("moneychanger", "gui_contacts.dat")) {
            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_ADDRESS_BOOK, "moneychanger", "gui_contacts.dat");
            if (storable == null) {
                return null;
            }
            addressBook = AddressBook.ot_dynamic_cast(storable);
        } else {
            storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_ADDRESS_BOOK);
            if (storable == null) {
                return null;
            }
            addressBook = AddressBook.ot_dynamic_cast(storable);
        }

        return addressBook;
    }
    private static LookAndFeel defautLAF;

    public static LookAndFeel getDefautLAF() {
        return defautLAF;
    }

    public static void setDefautLAF(LookAndFeel defautLAF) {
        Utility.defautLAF = defautLAF;
    }

    public static String fileToString(File file) {
        String fileText = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new java.io.BufferedInputStream(fis)));
            //read the stream
            int c = 0;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            fileText = sb.toString();

        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileText;
    }

    public static void replaceToLower(List<String> strings) {
        ListIterator<String> iterator = strings.listIterator();
        while (iterator.hasNext()) {
            iterator.set(iterator.next().toLowerCase());
        }
    }

    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public static void populateCombo(Map data, JComboBox component) {

        int count = 1;

        while (component.getItemCount() > 1) {
            component.removeItemAt(count);

        }

        Set set = data.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            component.addItem(new ComboObject(((String[]) data.get(key))[0]));
        }

        if (component instanceof com.wrapper.ui.custom.SteppedComboBox) {

            Dimension d = component.getPreferredSize();
            component.setPreferredSize(new Dimension(100, d.height));
            ((com.wrapper.ui.custom.SteppedComboBox) component).setPopupWidth(d.width);
        }
    }

    public static void populateComboWithoutAll(Map data, JComboBox component) {

        while (component.getItemCount() > 0) {
            component.removeItemAt(0);

        }

        Set set = data.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            component.addItem(new ComboObject(((String[]) data.get(key))[0]));
        }

        if (component instanceof com.wrapper.ui.custom.SteppedComboBox) {

            Dimension d = component.getPreferredSize();
            component.setPreferredSize(new Dimension(100, d.height));
            ((com.wrapper.ui.custom.SteppedComboBox) component).setPopupWidth(d.width);
        }
    }

    public static boolean isValidDouble(String text) {
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return false;
        }
        return true;
    }

    public static void delay() {
        try { // SLEEP
            if (Configuration.getWaitTime() > 0) {
                Thread.sleep(Configuration.getWaitTime());
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void longDelay() throws InterruptedException {
        try { // SLEEP
            if (Configuration.getWaitTime() > 0) {
                Thread.sleep(Configuration.getWaitTime() + 200);
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return;
    }

    public static String generateID() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public static void main(String a[]) {
        System.out.println(generateID());
    }

    public static boolean getIntermediaryFiles(String serverID, String nymID, String accountID) {

        if (null == serverID || serverID.length() < 10) {
            System.out.println("getIntermediaryFiles: invalid serverID: " + serverID);
            return false;
        }
        if (null == nymID || nymID.length() < 10) {
            System.out.println("getIntermediaryFiles: invalid nymID: " + nymID);
            return false;
        }
        if (null == accountID || accountID.length() < 10) {
            System.out.println("getIntermediaryFiles: invalid accountID: " + accountID);
            return false;
        }
        // -----------------------------------------------------
        if (false == Utility.getInboxAccount(serverID, nymID, accountID)) {
            System.out.println("getIntermediaryFiles: getInboxAccount failed. (Returning.)");
            return false;
        }
        // --------------------------------------

        if (false == Utility.getOutboxLowLevel(serverID, nymID, accountID)) {
            System.out.println("getIntermediaryFiles: getOutboxLowLevel failed. (Returning.)");
            return false;
        }

        return true;
    }

    // Same as the above function, except you only have to pass the accountID.
    // (instead of 3 IDs...)
    //
    public static boolean getInboxOutboxAccount(String accountID) {

        if (null == accountID || accountID.length() < 10) {
            System.out.println("getInboxOutboxAccount: invalid accountID: " + accountID);
            return false;
        }

        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);

        if (false == Utility.getIntermediaryFiles(serverID, nymID, accountID)) {
            System.out.println("getInboxOutboxAccount: getIntermediaryFiles failed. (Returning.)");
            return false;
        }
        return true;
    }

    // getInboxAccount()
    // Grabs the "Account", which is the intermediary file containing the current balance, verified against
    // last signed receipt. Server must have your signature on the last balance agreement plus, if applicable,
    // any inbox receipts (box receipts), also with your signature, in order to justify the current balance.
    // Any inbox receipts, further, are only valid if they each contain a transaction number that was previously 
    // already signed out to you.
    // (As you can see, the "account" is not a list of transactions, as per the classical understanding in 
    // double-entry accounting, but instead it's just a signed balance agreement, plus any as-yet-unclosed
    // transactions that have cleared since that balance was last signed, and are still waiting in the inbox
    // for the next balance agreement to be signed when they can be removed.)
    // ----------------
    // In addition to the "Account" there is also the Inbox itself, as well as all of its box receipts.
    // The box receipts are stored in abbreviated form in the Inbox itself, with the actual full
    // versions in separate files. These are retrieved individually from the server after the inbox itself
    // is, and then each is verified against a hash kept inside its abbreviated version.)
    //
    public static boolean getInboxAccount(String serverID, String nymID, String accountID) {
        boolean bInbox = false;
        boolean bAccount = false;

        // ***************************************************
        otapi.OT_API_FlushMessageBuffer();
        System.out.println("getInboxAccount: Before getInbox Server call");
        otapi.OT_API_getInbox(serverID, nymID, accountID);  // <==== FIRST ATTEMPT
        Utility.delay();
        String inboxResponseMessage = otapi.OT_API_PopMessageBuffer();

        // getInbox failed, for whatever reason.
        //
        if ((inboxResponseMessage == null)
                || (inboxResponseMessage.length() < 10)
                || (0 == otapi.OT_API_Message_GetSuccess(inboxResponseMessage))) {
            if (Utility.getRequestNumber(serverID, nymID)) // re-sync request number.
            {
                otapi.OT_API_getInbox(serverID, nymID, accountID); // <==== SECOND ATTEMPT
                Utility.delay();
                inboxResponseMessage = otapi.OT_API_PopMessageBuffer();

                if ((inboxResponseMessage == null)
                        || (inboxResponseMessage.length() < 10)
                        || (0 == otapi.OT_API_Message_GetSuccess(inboxResponseMessage))) {
                    System.out.println("getInboxAccount: Failed re-trying OT_API_getInbox() after Utility.getRequestNumber().");
                    return false;
                }
                // -----------------------
                bInbox = true; // success
            } else {
                System.out.println("getInboxAccount: Failed calling Utility.getRequestNumber().");
                return false;
            }
        } else {
            bInbox = true; // success
        }        // ***************************************************
        // Now let's make sure we have all the box receipts for this inbox.
        // (They will be needed when it is used for something.)
        //
        if (bInbox && !Utility.insureHaveAllBoxReceipts(serverID, nymID, // <===== 
                accountID, 1)) // nBoxType = 1 aka inbox
        {
            System.out.println("getInboxAccount: getInbox succeeded, but then insureHaveAllBoxReceipts failed. (I give up.)");
            return false;
        }
        // ***************************************************
        // Inbox done. Account next:
        // ***************************************************
        otapi.OT_API_FlushMessageBuffer();
        System.out.println("Before getAccount Server call");
        otapi.OT_API_getAccount(serverID, nymID, accountID);  // <==== FIRST ATTEMPT
        Utility.delay();
        String accountResponseMessage = otapi.OT_API_PopMessageBuffer();

        // getAccount failed, for whatever reason.
        //
        if ((accountResponseMessage == null)
                || (accountResponseMessage.length() < 10)
                || (0 == otapi.OT_API_Message_GetSuccess(accountResponseMessage))) {
            if (Utility.getRequestNumber(serverID, nymID)) // re-sync request number.
            {
                otapi.OT_API_getAccount(serverID, nymID, accountID); // <==== SECOND ATTEMPT
                Utility.delay();
                accountResponseMessage = otapi.OT_API_PopMessageBuffer();

                if ((accountResponseMessage == null)
                        || (accountResponseMessage.length() < 10)
                        || (0 == otapi.OT_API_Message_GetSuccess(accountResponseMessage))) {
                    System.out.println("getInboxAccount: Failed re-trying OT_API_getAccount() after Utility.getRequestNumber().");
                    return false;
                }
                // -----------------------
                bAccount = true; // success
            } else {
                System.out.println("getInboxAccount: Failed calling Utility.getRequestNumber().");
                return false;
            }
        } else {
            bAccount = true; // success
        }        // ***************************************************

        return (bInbox && bAccount);
    }

    // ---------------------------------
    public static boolean getOutboxLowLevel(String serverID, String nymID, String accountID) {
        boolean bOutbox = false;
        // ***************************************************
        // Okay, the first two are done. Now let's finish up
        // with the OUTBOX:
        //
        otapi.OT_API_FlushMessageBuffer();
        System.out.println("Before getOutbox Server call");
        otapi.OT_API_getOutbox(serverID, nymID, accountID);  // <==== FIRST ATTEMPT
        Utility.delay();
        String outboxResponseMessage = otapi.OT_API_PopMessageBuffer();

        // getOutbox failed, for whatever reason.
        //
        if ((outboxResponseMessage == null)
                || (outboxResponseMessage.length() < 10)
                || (0 == otapi.OT_API_Message_GetSuccess(outboxResponseMessage))) {
            if (Utility.getRequestNumber(serverID, nymID)) // re-sync request number.
            {
                // Successfully re-synced the request number.
                otapi.OT_API_getOutbox(serverID, nymID, accountID); // <==== SECOND ATTEMPT
                Utility.delay();
                outboxResponseMessage = otapi.OT_API_PopMessageBuffer();

                if ((outboxResponseMessage == null)
                        || (outboxResponseMessage.length() < 10)
                        || (0 == otapi.OT_API_Message_GetSuccess(outboxResponseMessage))) {
                    System.out.println("getOutboxLowLevel: Failed re-trying OT_API_getOutbox() after Utility.getRequestNumber().");
                    return false;
                }
                // -----------------------
                bOutbox = true; // success
            } else {
                System.out.println("getOutboxLowLevel: Failed calling Utility.getRequestNumber().");
                return false;
            }
        } else {
            bOutbox = true; // success
        }        // ***************************************************
        // Now let's make sure we have all the box receipts for this outbox.
        // (They will be needed when it is used for something.)
        //
        if (bOutbox && !Utility.insureHaveAllBoxReceipts(serverID, nymID, accountID, 2)) // <===== nBoxType = 2 aka OUTBOX
        {
            System.out.println("getOutboxLowLevel: getOutbox succeeded, but then insureHaveAllBoxReceipts failed. (I give up.)");
            return false;
        }
        // ***************************************************

        return bOutbox;
    }

    public static boolean saveImagePath(String imagePath) {

        boolean status = false;
        
        if (Load.IsOTInitialized())
        {
            StringMap stringMap = null;  // we are about to create this object
            Storable storable =
                    otapi.CreateObject(StoredObjectType.STORED_OBJ_STRING_MAP);
            System.out.println("storable:" + storable);
            if (storable != null) {
                stringMap = StringMap.ot_dynamic_cast(storable);
                System.out.println("stringMap:" + stringMap);

                if (stringMap != null) {
                    //stringMap.SetValue("ImagePath", "~/.ot/default.gif");
                    stringMap.SetValue("ImagePath", imagePath);
                    status = otapi.StoreObject(stringMap, "moneychanger",
                            "settings.dat");
                }
            }
        }
        else {
            System.out.println("Utility.getImagePath():  Skipping. (OT not initialized yet.)");    
        }        
        return status;
    }

    public static String getImagePath() {

        String strDefault   = null;
        String strImagePath = strDefault; // Todo: hardcoding

        if (Load.IsOTInitialized())
        {
            Storable    storable    = null;
            StringMap   stringMap   = null;

            System.out.println("getImagePath top...");

            if (otapi.Exists("moneychanger", "settings.dat")) {
                storable =
                        otapi.QueryObject(StoredObjectType.STORED_OBJ_STRING_MAP,
                        "moneychanger", "settings.dat");
                System.out.println("getImagePath, storable:" + storable);

                if (storable == null) {
                    System.out.println("Utility.getImagePath, failed querying storable from local storage.");                
                    return strDefault;
                }

                stringMap = StringMap.ot_dynamic_cast(storable);

                System.out.println("getImagePath, stringMap:" + stringMap);

                if (stringMap == null) {
                    System.out.println("Utility.getImagePath, failed casting stringmap from storable.");                
                    return strDefault;
                }

                strImagePath = stringMap.GetValue("ImagePath");
            } 
            else
            {
                System.out.println("Utility.getImagePath():  File does not exist: (OT_MAIN_PATH)/moneychanger/settings.dat");    
            }

            if ((null == strImagePath) || (strImagePath.length() < 1))
                strImagePath = strDefault; 
        }
        else {
            System.out.println("Utility.getImagePath():  Skipping. (OT not initialized yet.)");    
        }
        return strImagePath;
    }
}
