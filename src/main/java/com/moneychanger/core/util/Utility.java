/**
 * **********************************************************
 * -----BEGIN PGP SIGNED MESSAGE----- Hash: SHA256
 *
 * M O N E Y C H A N G E R
 *
 * Open Transactions: Financial Cryptography and Digital Cash Library, Protocol,
 * API, Server, and GUI
 *
 * -- Anonymous Numbered Accounts. -- Untraceable Digital Cash. -- Triple-Signed
 * Receipts. -- Cheques, Vouchers, Transfers, Inboxes. -- Basket Currencies,
 * Markets, Payment Plans. -- Signed, XML, Ricardian-style Contracts.
 *
 * Copyright (C) 2010-2012 by "Fellow Traveler" (A pseudonym)
 *
 * EMAIL: FellowTraveler@rayservers.net
 *
 * FINGERPRINT: 9DD5 90EB 9292 4B48 0484 7910 0308 00ED F951 BB8E
 *
 * BITCOIN: 1NtTPVVjDsUfDWybS4BwvHpG2pdS9RnYyQ
 *
 * OFFICIAL PROJECT WIKI: https://github.com/FellowTraveler/Moneychanger
 * https://github.com/FellowTraveler/Open-Transactions/wiki
 *
 * WEBSITE: http://www.OpenTransactions.org/
 *
 * Components and licensing: -- Moneychanger..A Java client
 * GUI.....LICENSE:.....GPLv3 -- OTLib.........A class
 * library.......LICENSE:...LAGPLv3 -- OT-API........A client
 * API..........LICENSE:...LAGPLv3 -- testwallet....Command-line
 * client...LICENSE:...LAGPLv3 -- OT-Server.....Server
 * Application....LICENSE:....AGPLv3
 * Github.com/FellowTraveler/Open-Transactions/wiki/Components
 *
 * All of the above OT components were designed and written by Fellow Traveler,
 * with the exception of Moneychanger, which was contracted out to Vicky C
 * (livewire_3001@yahoo.com).
 *
 * -----------------------------------------------------
 *
 * LICENSE: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see: http://www.gnu.org/licenses/
 *
 * If you would like to use this software outside of the free software license,
 * please contact FellowTraveler. (Unfortunately many will run anonymously and
 * untraceably, so who could really stop them?)
 *
 * DISCLAIMER: This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * -----BEGIN PGP SIGNATURE----- Version: GnuPG v1.4.11 (Darwin)
 *
 * iQIcBAEBCAAGBQJOj7gfAAoJEAMIAO35UbuOs3sP/2rrjSdYu/AsXcgLK9/9CP4a
 * lIJfw3KLvybKZjZW5r5j+4xUlCYIqPZSI66PGDChGPMPFcZQN6M4Ddpn9kbctymS
 * sdTXvbdFhpbV6k2wSa1Fz97ygfXJc/7MDTmHYbZ53hVV8AoUBrCHWtVttkQD31o3
 * Pn/qGmy+jOgTvjEXhjEpV66pDkMWze1SiI1MArHUziCYoxItuM45x0EfzwQIqlo3
 * ku2R7rRTtqm47Dgea12psWrjbPS5XRL1Q8Hs38Z1J0JdFlfn6cJYe52Iiluzof6M
 * kCLhy6FH8QfIADfrKkFP48EIhnVquDlkV9AlJ1r217K3cpK2jEjlZUnGBECMAMEo
 * pSXXk1BLNgxsa4yaXCgHY92/MhgtcdCMLkcCq6MWUTGZsLGiWIiQGmO9mwBfNIlY
 * SawlIviuS5DiE/D16A290Byxhha/5e144cIiKm27fSQra8eogUXNfZdZeuv6n69v
 * t8QjeBjoLhe5/KnRNoGLpSXhPphsWLRSJBru77ZU2msHfmkNfcP2UoEUCfNTfTbE
 * XpyRfeyRVowVKeKunV9KUSHgdD5wa6RUeyodAbaHvWrFpIpNkaFIP9OwhRULpjx0
 * arwVNYucbX1qb2I8HBm2u+IRWQTONp74TFFjU0/CVAXu2DeJKY5mL4zDej35c5j9
 * AK+ZirdWhhoHeWR1tAkN =RcXP -----END PGP SIGNATURE-----
 *************************************************************
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.core.util;

import java.util.ArrayList;
import java.util.List;

import com.moneychanger.core.Account;
import com.moneychanger.core.OpenTransactionAccount;
import com.moneychanger.core.dataobjects.OTDetails;
import org.ot.wrapper.jni.AddressBook;
import org.ot.wrapper.jni.MarketList;
import org.ot.wrapper.jni.OTCallback;
import org.ot.wrapper.jni.OTCaller;
import org.ot.wrapper.jni.OfferListMarket;
import org.ot.wrapper.jni.OfferListNym;
import org.ot.wrapper.jni.Storable;
import org.ot.wrapper.jni.StoredObjectType;
import org.ot.wrapper.jni.StringMap;
import org.ot.wrapper.jni.TradeListMarket;
import org.ot.wrapper.jni.TradeListNym;
import org.ot.wrapper.jni.WalletData;
import org.ot.wrapper.jni.otapi;
import com.moneychanger.ui.Load;
import com.moneychanger.ui.LoadState;
import com.moneychanger.ui.MainPage;
import com.moneychanger.ui.model.AccountTableModel;
import com.moneychanger.ui.panels.OpenTransactionAccountBottomPanel;
import com.moneychanger.ui.panels.OpenTransactionAccountTopPanel;
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

    public static class OTBool {

        private boolean value = false;
        private boolean value2 = false;
        // ----------------------------

        public OTBool(boolean value) {
            this.value = value;
        }
        // ----------------------------

        public boolean getBooleanValue() {
            return value;
        }
        // ----------------------------

        public void setBooleanValue(boolean value) {
            this.value = value;
        }
        // ----------------------------

        public boolean getSecondValue() {
            return value2;
        }
        // ----------------------------

        public void setSecondValue(boolean value) {
            this.value2 = value;
        }
    }

// ------------------------------------
    public static class OTInteger {

        private int value = 0;
        // ----------------------------

        public OTInteger(int value) {
            this.value = value;
        }
        // ----------------------------

        public int getIntegerValue() {
            return value;
        }
        // ----------------------------

        public void setIntegerValue(int value) {
            this.value = value;
        }
    }
// ------------------------------------
    private static Object settingsObj;
    private static String m_nymID, lastReplyReceived;
    private static List basketExistingAssets = new ArrayList();

    public static List getBasketExistingAssets() {
        return basketExistingAssets;
    }

    public static String getLastReplyReceived() {
        return lastReplyReceived;
    }

    public static void setLastReplyReceived(String strReply) {
        lastReplyReceived = strReply;
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
        return m_nymID;
    }

    public static void setNymID(String nymID) {
        Utility.m_nymID = nymID;
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

    // ------------------------------------------------------
    public static int getNymboxLowLevel(String serverID, String nymID) {
        OTBool bWasSent = new OTBool(false);

        return Utility.getNymboxLowLevel(serverID, nymID, bWasSent);
    }

    // This returns -1 if error, or a positive request number if it was sent.
    // (It cannot return 0.)
    // Called by getAndProcessNymbox.   
    // DONE
    public static int getNymboxLowLevel(String serverID, String nymID, OTBool bWasSent) {
        otapi.OT_API_FlushMessageBuffer();
        bWasSent.setBooleanValue(false);
        // --------------------------------------------------------------------
        final int nRequestNum = otapi.OT_API_getNymbox(serverID, nymID); // <===== ATTEMPT TO SEND THE MESSAGE HERE...

        switch (nRequestNum) {
            case (-2):
                System.out.println("Utility.getNymboxLowLevel: ERROR, not supported. (-2 was returned.)");
                return (-1); // -2 is also possible at some future date. (If the request number won't fit in an int, this is returned and then you can retrieve the actual number via a separate call.)
            case (-1):      // if the requestNumber returned by the send-attempt is -1, that means it DIDN'T SEND (error)
                System.out.println("Utility.getNymboxLowLevel: Failed to send getNymbox message due to error.");
                return (-1);
            case (0):
                System.out.println("Utility.getNymboxLowLevel: Unexpectedly returned 0. Didn't send getNymbox message, but NO error occurred, either. (In this case, SHOULD NEVER HAPPEN. Treating as Error.)");
                return (-1); // Even though '0' MEANS "didn't send, but no error" by convention in many places, it is actually an impossible return value from getNymbox. Therefore I treat it as an error.
            default:
                if (nRequestNum < 0) {
                    System.out.println("Utility.getNymboxLowLevel: Unexpected request number: " + nRequestNum);
                    return (-1);
                }
                // -------------------------
                break; // SUCCESS!
        }
        bWasSent.setBooleanValue(true);
        // ***************************************************
        //
        final int nResult = Utility.receiveReplySuccessLowLevel(serverID, nymID, nRequestNum, "Utility.getNymboxLowLevel");
//        System.out.println("IN Utility.getNymboxLowLevel " + Utility.getLastReplyReceived());

        // BY this point, we definitely have the request number in nResult, which means
        // the message was actually SENT. (At least.) This also means we can use nResult
        // later to query for a copy of that sent message.
        // Let's go ahead, in this case, and remove that now:
        //
        // THE REMOVE SENT MESSAGE BELOW FAILS, LIKE IT'S ALREADY GONE.
        //
        // THIS MUST BE DUE TO THE PROCESS SERVER REPLY THAT OCCURS **IMMEDIATELY** after the message was originally sent!
        // (The reply came in and was sent to OT's "ProcessServerReply", INSIDE the call to getNymbox.)
        // Our subsequent "receive" (above) is nothing of the sort, but actually pops the incoming message buffer where
        // the server's reply was ALREADY SITTING, since it was put there in OT's "ProcessServerReply", WHICH REMOVED THE
        // SENT MESSAGE ALREADY (that's why the below call to RemoveSentMessage fails.)
        //
        // RETHINK any logic that doesn't take this into account,. 
        // Either we REMOVE this call wherever this happens, OR... we call Get first and make sure whether it's
        // there, THEN remove it. But we can't be lumping "Failure because it's gone" versus "Error state" by mixing
        // 0 and -1 here. We need to differentiate.
        //
        // Bottom line: if the reply WAS received, then the original sent message has ALREADY been removed
        // from the sent buffer. Whereas if the reply was NOT received, then the sent message is still there,
        // but in that case, we do NOT want to remove it -- we want it to STAY in the sent buffer, so that
        // when we get the Nymbox later and we DO have the reply from that, THEN we can remove the sent msg from
        // the sent buffer. Until then, we don't want OT to think it's already been processed (which it will, if
        // it's already been removed from the sent buffer. So we leave it there for now.)
        //
        // 
        //

//        final int nRemovedSentMsg = otapi.OT_API_RemoveSentMessage(Integer.toString(nRequestNum), serverID, nymID);
//
//        if (nRemovedSentMsg < 1)
//        {
//            System.out.println("Utility.getNymboxLowLevel: ERROR: OT_API_RemoveSentMessage returned: " + nRemovedSentMsg);
//        }
        // ----------------------------------------------

        if (1 == nResult) {
            return nRequestNum;
        }
        return nResult;
    }

    // GET NYMBOX
    //
    // Note: The caller needs to call OT_API_RemoveSentMessage after
    // calling this function.
    // Correction: I spoke too soon. GetNymboxLowLevel ALREADY does
    // these things:
    //    receiveReplySuccessLowLevel
    //    OT_API_RemoveSentMessage(Integer.toString(nRequestNum));
    //
    public static int getNymbox(String serverID, String nymID) {
        return Utility.getNymbox(serverID, nymID, false); // bForceDownload=false
    }

    public static int getNymbox(String serverID, String nymID, boolean bForceDownload) {
        //-------------------------------------------------                        
        final String strRecentHash = otapi.OT_API_GetNym_RecentHash(serverID, nymID);
        final boolean bRecentHash = Utility.isValid(strRecentHash);
        if (!bRecentHash) {
            System.out.println("Utility.getNymbox(): Warning: Unable to retrieve recent cached copy of server-side "
                    + "NymboxHash from client-side nym (perhaps he's never downloaded it before?)\n\n");
        }
        //-------------------------------------------------                
        String strLocalHash = otapi.OT_API_GetNym_NymboxHash(serverID, nymID);
        boolean bLocalHash = Utility.isValid(strLocalHash);
        if (!bLocalHash) {
            System.out.println("Utility.getNymbox(): Warning: Unable to retrieve client-side NymboxHash "
                    + "for:\n serverID: " + serverID + "\n nymID: " + nymID);
        }
        //-------------------------------------------------
        if (!bForceDownload) {
            if (bLocalHash
                    && bRecentHash
                    && strRecentHash.equals(strLocalHash)) // the hashes match -- no need to download anything.
            {
                System.out.println("Utility.getNymbox(): The hashes already match (skipping Nymbox download.)");
                return 1;
            }
        }

        //-------------------------------------------------
        // -- SECTION 1: "GET NYMBOX"
        //
        OTBool bWasMsgSent = new OTBool(false);

        int nGetNymbox = Utility.getNymboxLowLevel(serverID, nymID, bWasMsgSent); // bWasMsgSent is output from this call.        

        if (bWasMsgSent.getBooleanValue()) {
            System.out.println("Utility.getNymbox(): FYI: Utility.getNymboxLowLevel apparently SENT the request. nGetNymbox is: " + nGetNymbox);
        }
        // -----------------------------------------------------------
        if ((false == bWasMsgSent.getBooleanValue()) || // UPDATE: This might've been a bug:  Removing this for now, since I still want to give it one last chance with the getRequest call, just below.
                ((nGetNymbox <= 0) && ((-1) != nGetNymbox))) {
            System.out.println("Utility.getNymbox(): Failure: Utility.getNymboxLowLevel returned unexpected value: " + nGetNymbox);
            return (-1);
        }   // NOTE: for getNymbox, there is no '0' return value, which is why you don't see me handling it here.
        // -----------------------------------------------------------
        if ((-1) == nGetNymbox) // we'll try re-syncing the request number, then try again.
        {
            System.out.println("Utility.getNymbox(): FYI: Utility.getNymboxLowLevel returned -1. (Re-trying...)");

            final int nGetRequest = Utility.getRequestNumber(serverID, nymID);

            if (1 != nGetRequest) {
                System.out.println("Utility.getNymbox(): Failure: Utility.getNymboxLowLevel failed, then I tried to resync with getRequestNumber and then that failed too. (I give up.)");
                return (-1);
            }
            // ---------------------------------

            final String strLastReplyReceived = Utility.getLastReplyReceived();
            // I had to do this bit because getRequestNumber doesn't return the actual
            // reply itself. But in this case, I needed it.
            if (!Utility.isValid(strLastReplyReceived)) // THIS SHOULD NEVER HAPPEN.
            {
                System.out.println("Utility.getNymbox(): "
                        + "ERROR in Utility.getLastReplyReceived(): why was this string not set, when Utility.getRequestNumber was otherwise an apparent success?");
                return (-1); // (SHOULD NEVER HAPPEN. This string is set in the getRequestNumber function.)
            }
            //-------------------------------------------------

            // BY THIS POINT, we have received a server reply:  @getRequest
            // (Unless it is malformed.) It's definitely not null, nor empty.

            //-------------------------------------------------

            // Grab the NymboxHash on the @getRequest reply, and also the one I
            // already had on my client-side Nym... (So we can compare them.)
            //
            //      If the hashes do NOT match, then I DO need to download nymbox and box receipts.
            /*
             * ===> If the NymboxHash is changed from what I expected, then I
             * need to re-download the nymbox (and any box receipts I don't
             * already have.)
             *
             * Then I need to process the Nymbox. But first, see if my missing
             * server reply is in there. If it is, then I have the server reply!
             * (As if we had succeeded in the first place!!) Next, process the
             * Nymbox (which processes that reply) and then return strReply like
             * normal.
             *
             * (Clearly this is just going to be a normal part of the getRequest
             * syncronization.)
             *
             * By the time that much is done, I will KNOW the request number,
             * the nymbox, the box receipts, etc are ALL syncronized properly,
             * and that I THEN processed the Nymbox successfully.
             *
             *
             * NOTICE: In this example I do NOT want to pull out my sent message
             * from the outbuffer (using the request number) and try to harvest
             * all the transaction numbers. Why not? Because possibly the server
             * DID reply! And if I processed that reply properly, it would sync
             * my transaction numbers properly just from that! ===>
             *
             * ===> Therefore, I need to see FIRST if the old message has a
             * reply WAITING in the Nymbox. THEN I need to process the Nymbox.
             * ONLY if the reply wasn't there, can I THEN pull out the message
             * from my outbuffer and harvest it. (Which I am reticent to do,
             * until I am SURE the server really never saw that message in the
             * first place.)
             *
             * However, as long as my NymboxHash hasn't changed, then I'm safe!
             * But if it HAS changed, then I HAVE to A. download it B. SEE if
             * the reply is there for the request number, then C. process it.
             * ... If the reply wasn't there, THEN Harvest the transaction #s
             * (for transaction messages) and then re-try.
             */

            //-------------------------------------------------       
            // Grabbing again in case it's changed.
            //
            final String strServerHash = otapi.OT_API_Message_GetNymboxHash(strLastReplyReceived);
            final boolean bServerHash = Utility.isValid(strServerHash);
            if (!bServerHash) {
                System.out.println("Utility.getNymbox(): Warning: Unable to retrieve server-side "
                        + "NymboxHash from server @getRequest reply:\n\n"
                        + strLastReplyReceived);
            }
            //-------------------------------------------------                
            strLocalHash = otapi.OT_API_GetNym_NymboxHash(serverID, nymID);
            bLocalHash = Utility.isValid(strLocalHash);

            if (!bLocalHash) {
                System.out.println("Utility.getNymbox(2): Warning: Unable to retrieve client-side NymboxHash "
                        + "for:\n serverID: " + serverID + "\n nymID: " + nymID);
            }
            //-------------------------------------------------

            if (bForceDownload
                    || !bLocalHash
                    || !bServerHash
                    || (bServerHash && bLocalHash
                    && !strServerHash.equals(strLocalHash))) // the hashes don't match -- so let's definitely re-try to download the latest nymbox.
            {
                // the getRequest worked, and the server hashes don't match,
                // so let's try the call again...
                //
                nGetNymbox = Utility.getNymboxLowLevel(serverID, nymID, bWasMsgSent);

                if ((false == bWasMsgSent.getBooleanValue())
                        || ((nGetNymbox <= 0) && ((-1) != nGetNymbox))) {
                    System.out.println("Utility.getNymbox(2): Failure: Utility.getNymboxLowLevel returned unexpected value: " + nGetNymbox);
                    return (-1);
                } else if ((-1) == nGetNymbox) // we'll try re-syncing the request number, then try again.
                {
                    System.out.println("Utility.getNymbox(): Failure: Utility.getNymboxLowLevel returned -1, even after syncing the request number successfully. (Giving up.)");
                    return (-1);
                }
            }
        }

        // By this point, we DEFINITELY know that the Nymbox was retrieved successfully.
        // (With request number nGetNymbox.) This is because the getNymboxLowLevel() call
        // also tries to receive the reply, so we already know by now whether the reply
        // was successfully received.
        //
        return nGetNymbox;
    }

//  public static int getNymboxLowLevel(String serverID, String nymID)
//  public static int receiveNymboxLowLevel(String serverID, String nymID, final int nRequestNum)
//  public static int processNymboxLowLevel(String serverID, String nymID) {
    public static int getAndProcessNymbox(String serverID, String nymID, OTBool bWasMsgSent) {
        return Utility.getAndProcessNymbox(serverID, nymID, bWasMsgSent, false); // bForceDownload=false
    }

    public static int getAndProcessNymbox(String serverID, String nymID, OTBool bWasMsgSent, boolean bForceDownload) {
        final int nRequestNumber = 0;

        OTBool bFoundNymboxItem = new OTBool(false); // bFoundNymboxItem is output bool, telling caller whether it was found.

        return Utility.getAndProcessNymbox(serverID, nymID, bWasMsgSent, bForceDownload, nRequestNumber, bFoundNymboxItem,
                false, false, false, false, false);
    }

    // NOTE, TODO: if the Nymbox simply WON'T process, OR on a regular basis,
    // need to iterate through the sent messages and make sure to harvest whichever
    // ones are not already in the Nymbox (no need to harvest those--they're already
    // in the system.)
    //
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
    public static int getAndProcessNymbox(String serverID, String nymID, OTBool bWasMsgSent, boolean bForceDownload,
            final int nRequestNumber, // nRequestNumber refers to a PREVIOUS msg (like a cash withdrawal) that had an error and then called this while trying to resync. (The caller will want to know whether it was found in the Nymbox.)
            OTBool bFoundNymboxItem, // bFoundNymboxItem is output bool, telling caller whether it was found.
            boolean bHarvestingForRetry, // bHarvestingForRetry is INPUT, in the case nRequestNumber needs to be harvested before a flush occurs.
            boolean bMsgReplySuccess, // bMsgReplySuccess is INPUT, and is in case nRequestNumber needs to be HARVESTED before a FLUSH happens.
            boolean bMsgReplyFailure, // bMsgReplyFailure is INPUT, and is in case nRequestNumber needs to be HARVESTED before a FLUSH happens.
            boolean bMsgTransSuccess, // bMsgTransSuccess is INPUT, and is in case nRequestNumber needs to be HARVESTED before a FLUSH happens.
            boolean bMsgTransFailure) // bMsgTransFailure is INPUT, and is in case nRequestNumber needs to be HARVESTED before a FLUSH happens.
    {
        if (1 == nRequestNumber) {
            System.out.println("getAndProcessNymbox: WARNING: Request Num of '1' was just passed in here.");
        }

        // This should NEVER happen (need an assert here.)
        //
        if ((null == bWasMsgSent) || (null == bFoundNymboxItem)) {
            System.out.println("getAndProcessNymbox: SHOULD NEVER HAPPEN!!! ASSERT!! ERROR!! FAILURE!!! PROBLEM!!!!!");
            return -1;
        }

        bWasMsgSent.setBooleanValue(false);

        // what is nRequestNumber?
        //
        // Let's say a message, say for a cash withdrawal with request number 5, has FAILED.
        // Since the message failed, perhaps the request number was out of sync, or Nymbox hash
        // was old? So, let's say that it then sent a getRequest message, in order to resync,
        // and discovered that the Nymbox hash has changed. Therefore the Nymbox is now being
        // re-downloaded and processed, so that the cash withdrawal can be attempted again.
        //
        // HOWEVER, before we PROCESS the Nymbox, we need to see if the withdrawal reply is already
        // sitting in it. Why, you ask, if the withdrawal failed, would I expect a reply to be in 
        // the Nymbox? In case 1, the message was dropped, so I don't know if the reply is there
        // until I check the Nymbox. In case 2, the message may have failed OR SUCCEEDED, with the
        // successful message containing a FAILED TRANSACTION. 
        // Thus, we just want to check the Nymbox for nRequestNumber, and make sure whether it's
        // there or not, before we PROCESS the nymbox, because once we do THAT, it will be empty.
        // 
        // We will return a bool parameter to the caller, so that he can know whether the reply
        // was already in the Nymbox. We can also harvest the transaction numbers from the reply
        // message, if it's a transaction, so that everything is set for the re-try. (Possibly pass
        // a bool parameter dictating whether the harvesting is being done for a re-try or not.)
        //


        // -- SECTION 1: "GET NYMBOX"
        //
        // This call is sufficiently high-level enough that it already has re-tries
        // built into it. That's why you don't see me re-trying the getNymbox if it
        // fails.
        //
        int nGetNymbox = Utility.getNymbox(serverID, nymID, bForceDownload);

        if (nGetNymbox < 1) {
            System.out.println("Utility.getAndProcessNymbox(): Failure: Utility.getNymbox returned: " + nGetNymbox);
            return (-1);
        }

        // By this point, we DEFINITELY know that the Nymbox was retrieved successfully.
        // (With request number nGetNymbox.) This is because the getNymboxLowLevel() call
        // also tries to receive the reply, so we already know by now whether the reply
        // was successfully received.
        //
        /*
         * FYI: nRequestNumber is the request number, if >0, for whatever
         * command is causing this getAndProcessNymbox to occur (like a cash
         * withdrawal, or a cheque deposit, etc.) We pass it in here so we can
         * verify whether it's on the Nymbox, before we process it out (so the
         * caller knows whether to clawback.)
         *
         * FYI: nGetNymbox is the request number from getNymboxLowLevel()
         * (above.) We know for a FACT, by this point, this number is >0.
         *
         * FYI: nProcess (just below) is the request number for the PROCESS
         * NYMBOX message. Below the switch block just down there, we know for a
         * fact this number is >0.
         */
        // ******************************************************************************

        // -- SECTION 2: "SEND PROCESS NYMBOX"
        //

        // Next, we have to make sure we have all the BOX RECEIPTS downloaded
        // for this Nymbox.

        //-------------------------------------------------------------------------------

        if (Utility.insureHaveAllBoxReceipts(serverID, nymID, nymID, 0, // nBoxType = 0 aka nymbox
                nRequestNumber, bFoundNymboxItem)) // If the caller wanted to know whether a certain reply (by request number) was in the Nymbox, then bFoundNymboxItem
        {                                           // will be set true in this call, if it was there. That way he can Harvest his own msg if he needs to. (Just like I 
            // harvest my own processNymbox call below, if necessary.)

            // If the caller was on about a specific request number...
            //
            if (nRequestNumber > 0) {
                // And if we DID NOT find that number in the Nymbox, 
                // then harvest it!! 
                // (If we HAD found it, then we'd know it didn't NEED harvesting, 
                // since the server clearly REPLIED to it already.)
                //
                if (bFoundNymboxItem.getBooleanValue()) // FOUND it in the nymbox! Therefore we can remove without harvesting. (Server definitely processed it, so nothing to harvest.)
                {
                    // Notice, if the above call to insureHaveAllBoxReceipts had any network hiccups, then
                    // it may have had to get and processNymbox, meaning the below Remove would fail, since
                    // the sent message was already removed. Therefore, might want to update this to call GetSent
                    // FIRST, before trying to remove.
                    // (Might want different messages in either case.)
                    //
                    final int nRemovedMsg = otapi.OT_API_RemoveSentMessage(Integer.toString(nRequestNumber), serverID, nymID);
                    System.out.println("Utility.getAndProcessNymbox(): OT_API_RemoveSentMessage: " + nRemovedMsg);
                } else // Didn't find it in the nymbox, so we can harvest it:
                {

                    // NOTE: This may always fail, 

                    System.out.println("Utility.getAndProcessNymbox(): FYI: Calling OT_API_GetSentMessage...");

                    final String strSentMsg = otapi.OT_API_GetSentMessage(Integer.toString(nRequestNumber), serverID, nymID);

                    if (!Utility.isValid(strSentMsg)) {
                        System.out.println("Utility.getAndProcessNymbox(): ERROR: (SHOULD NEVER HAPPEN 1) Expected OT_API_GetSentMessage to return the sent message (for clawback) but couldn't find it. (Expected it--I JUST supposedly sent it!) Request number: " + nRequestNumber);
                    } else // OT_API_GetSentMessage success.
                    {
                        System.out.println("Utility.getAndProcessNymbox(): FYI: Harvesting transaction numbers from failed Msg attempt...");
                        // ------------------------------------
                        final int nHarvested = otapi.OT_API_Msg_HarvestTransactionNumbers(strSentMsg, nymID,
                                bHarvestingForRetry ? 1 : 0, // bHarvestingForRetry.
                                bMsgReplySuccess ? 1 : 0, // bReplyWasSuccess,       // RECEIVED server reply: explicit success.
                                bMsgReplyFailure ? 1 : 0, // bReplyWasFailure,       // RECEIVED server reply: explicit failure.
                                bMsgTransSuccess ? 1 : 0, // bTransactionWasSuccess, // MESSAGE success, Transaction success. (Explicit.)
                                bMsgTransFailure ? 1 : 0);   // bTransactionWasFailure  // MESSAGE success, Transaction failure. (Explicit.)
                        System.out.println("Utility.getAndProcessNymbox(): OT_API_Msg_HarvestTransactionNumbers: " + nHarvested);

                        final int nRemovedMsg = otapi.OT_API_RemoveSentMessage(Integer.toString(nRequestNumber), serverID, nymID);
                        System.out.println("Utility.getAndProcessNymbox(): OT_API_RemoveSentMessage: " + nRemovedMsg);

                    } // strSentMsg NOT null!
                }
            }
            // ------------------------------------------------------------------

            // (flush): LOOP THROUGH ALL "SENT" messages, and see if ANY of them has a reply
            // sitting in my Nymbox. If so, REMOVE IT from "Sent" queue, (since clearly the server
            // DID respond already.) And if it's NOT in my nymbox, that means I DEFINITELY need to
            // harvest it back since the server definitely rejected it or never received it.
            //
            // The Nym actually SAVES the sent messages PER SERVER, so that this
            // will continue to work in every possible case!!
            // NOTE: Also now storing, on the client nym, a copy of the server's latest nymbox hash
            // for that nym, in addition to the nym's copy (which only updates when he gets his Nymbox.)
            // That way the Nym, even before syncing the nymboxes, and even before sending a new message
            // to find out if they are out of sync, ALREADY KNOWS if they're in sync or not. (That's why
            // all those other server messages send a copy of that hash back, not just the getNymbox msg.)
            // 
            // 
//            void OT_API_FlushSentMessages(const int bHarvestingForRetry, // bHarvestingForRetry is actually OT_BOOL
//                              const char * SERVER_ID, 
//                              const char * USER_ID,
//                              const char * THE_NYMBOX); 
            // NoVerify means don't load up all the box receipts.
            // Especially in this case--we only care about whether a box receipt is THERE, not
            // what it contains. FlushSentMessages will work just fine WITHOUT loading those
            // box receipts (because the Nymbox contains enough of an abbreviated record already
            // for each one, that we will have the info we need already.)
            //
            String strNymbox = otapi.OT_API_LoadNymboxNoVerify(serverID, nymID);      // FLUSH SENT MESSAGES!!!!  (AND HARVEST.)

            // *******************************************************
            if (Utility.isValid(strNymbox)) // ---------------------------
            {
                otapi.OT_API_FlushSentMessages(0, //harvesting for retry == OT_FALSE. None of the things are being re-tried by the time they are being flushed.  They were already old news.
                        serverID,
                        nymID,
                        strNymbox);
            } // Flushing removes all the messages from the "sent messages" queue,
            // and harvests any transaction numbers to be had. How do I know for sure
            // that I can get away with this? How do I know whether the server has
            // processed those messages or not? How can I harvest them as though they
            // were dropped on the network somewhere? The answer is because I JUST
            // called GetNymbox and downloaded the latest one. I can SEE which replies
            // are in there -- and which ones aren't. I pass that Nymbox into the flush
            // call, so that flush can be careful to remove all sent messages that have
            // nymbox replies, and only harvest the others.
            // *******************************************************
            else {
                System.out.println("Utility.getAndProcessNymbox(): Error while trying to flush sent messages: Failed loading Nymbox for nym: " + nymID);
            }
            // -------------------------------------------------
            OTInteger nMsgSentRequestNumOut = new OTInteger(-1);
            OTInteger nReplySuccessOut = new OTInteger(-1);
            OTInteger nBalanceSuccessOut = new OTInteger(-1);
            OTInteger nTransSuccessOut = new OTInteger(-1);
            // -------------------------------------------------

            // PROCESS NYMBOX
            //
            // Returns:
            // -1 Error.
            //  0 Nymbox was empty -- nothing done. (bWasMsgSent = false)
            //  0 Transaction status == server reply received (bWasMsgSent = true),
            //    but the server reply says status==failed.
            // >0 If the Transaction status (from the server reply) is SUCCESS, then this function
            //    returns the REQUEST NUMBER from when it was originally sent. 

            int nProcess = Utility.processNymbox(serverID, nymID,
                    bWasMsgSent,
                    // ---------------
                    nMsgSentRequestNumOut,
                    nReplySuccessOut,
                    nBalanceSuccessOut,
                    nTransSuccessOut);
            switch (nProcess) {
                case (-1):
                    // Todo: might want to remove the sent message here, IF bMsgWasSent is true.
                    // (Just like case 0.)
                    //
                    System.out.println("Utility.getAndProcessNymbox(): Failure: Utility.insureHaveAllBoxReceipts: error (-1), when calling sendProcessNymboxLowLevel. (It couldn't send. I give up.)");
                    return (-1); // (It didn't even send.)
                case (0):   // Nymbox was empty. (So we didn't send any process message because there was nothing to process.)
                    if (false == bWasMsgSent.getBooleanValue()) {
                        return 0; // success. done. (box was empty already.)
                    }                    // else: the message WAS sent, (the Nymbox was NOT empty)
                //       and then the server replied "success==FALSE" 
                //       in its REPLY to that message! Thus we continue...
                default:
                    if (nProcess < 0) {
                        System.out.println("Utility.getAndProcessNymbox(): Failure: Utility.insureHaveAllBoxReceipts: unexpected: " + nProcess + ", when calling sendProcessNymboxLowLevel. (I give up.)");
                        return (-1);
                    }
                    // (else break)
                    break; // Success! 

            } // switch

            // bWasMsgSent.setBooleanValue(true);  // unnecessary -- set already by processNymbox call above.
            // ------------------------------------------  
            // By this point, we definitely have a >0 request number from the sendProcessNymbox()
            // call, stored in nProcess (meaning the message WAS sent.) (Except in case of 0, see next line which fixes this:)
            //

            nProcess = nMsgSentRequestNumOut.getIntegerValue(); // Sometimes this could be 0 still, so we fix it here.
            final int nReplySuccess = nReplySuccessOut.getIntegerValue();
            final int nTransSuccess = nTransSuccessOut.getIntegerValue();
            final int nBalanceSuccess = nBalanceSuccessOut.getIntegerValue();

            // ------------------------------------------  
            /*
             * const char * OT_API_GetSentMessage(const char * REQUEST_NUMBER)
             * OT_BOOL OT_API_RemoveSentMessage(const char * REQUEST_NUMBER)
             *
             */
            // All of these booleans (except "error") represent RECEIVED ANSWERS from the server.
            // In other words, "false" does not mean "failed to find message."
            // Rather, it means "DEFINITELY got a reply, and that reply says success==false."

            // ---------------------------------
            // SHOULD NEVER HAPPEN (processNymbox call just above was successful,
            // therefore the sent message SHOULD be here in my cache.)
            //
            final String strReplyProcess = Utility.getLastReplyReceived();
            // I had to do this bit because getRequestNumber doesn't return the actual
            // reply itself. But in this case, I needed it.
            if (!Utility.isValid(strReplyProcess)) // THIS SHOULD NEVER HAPPEN.
            {
                System.out.println("Utility.getAndProcessNymbox(): "
                        + "ERROR in Utility.getLastReplyReceived(): why was this string not set, when Utility.getRequestNumber was otherwise an apparent success?");
                return (-1); // (SHOULD NEVER HAPPEN. This string is set in the getRequestNumber function.)
            }
            //-------------------------------------------------

            final boolean bProcessNymboxReplyError = (!Utility.isValid(strReplyProcess) || (nReplySuccess < 0));
            final boolean bProcessNymboxBalanceError = (!Utility.isValid(strReplyProcess) || (nBalanceSuccess < 0));
            final boolean bProcessNymboxTransError = (!Utility.isValid(strReplyProcess) || (nTransSuccess < 0));
            // -----------------------------------------------------------------------------------------------------
            final boolean bProcessNymboxReplySuccess = (!bProcessNymboxReplyError && (nReplySuccess > 0));
            final boolean bProcessNymboxReplyFailure = (!bProcessNymboxReplyError && (nReplySuccess == 0));
            // -----------------------------------------------------------------------------------------------------
            final boolean bProcessNymboxBalanceSuccess = (!bProcessNymboxReplyError && !bProcessNymboxBalanceError && (nBalanceSuccess > 0));
            final boolean bProcessNymboxBalanceFailure = (!bProcessNymboxReplyError && !bProcessNymboxBalanceError && (nBalanceSuccess == 0));
            // -----------------------------------------------------------------------------------------------------
            final boolean bProcessNymboxTransSuccess = (!bProcessNymboxReplyError && !bProcessNymboxBalanceError && !bProcessNymboxTransError && (nTransSuccess > 0));
            final boolean bProcessNymboxTransFailure = (!bProcessNymboxReplyError && !bProcessNymboxBalanceError && !bProcessNymboxTransError && (nTransSuccess == 0));
            // -----------------------------------------------------------------------------------------------------
            final boolean bProcessAnyError = (bProcessNymboxReplyError || bProcessNymboxBalanceError || bProcessNymboxTransError);
            final boolean bProcessAnyFailure = (bProcessNymboxReplyFailure || bProcessNymboxBalanceFailure || bProcessNymboxTransFailure);
            final boolean bProcessAllSuccess = (bProcessNymboxReplySuccess && bProcessNymboxBalanceSuccess && bProcessNymboxTransSuccess);
            // -----------------------------------------------------------------------------------------------------


            // Note: we LEAVE the sent message in the "sent queue" until we are certain that it processed.
            // If we are NOT certain that it processed, then we try to download the Nymbox and see if there's
            // a reply there (for the sent message.) If we are able to confirm THAT, AFTER SUCCESSFULLY downloading
            // the Nymbox, then then we don't have to do anything because we know for sure it was processed.
            // Similarly, if we DEFINITELY download the Nymbox and do NOT find the reply, then we know the server
            // DEFINITELY did not receive (or at least process) that message, which is what allows us to HARVEST
            // the transaction numbers back from the sent message, and remove the sent message from the sent queue.
            //
            // However, if we are NOT able to verify any of these things, NOR are we able to download the Nymbox to
            // see, then we DO leave the message in the sent queue. This is deliberate, since it gives us the opportunity
            // in the future to clear those sent messages NEXT time we successfully DO download the Nymbox, and in the
            // meantime, it allows us to store a record of EXACTLY which messages were MISSED.
            //

            int nHarvested = -1;

            if (bProcessAllSuccess) {
                // the processNymbox was a complete success, including the message
                // AND the transaction AND the transaction statement.
                // Therefore, there's DEFINITELY nothing to clawback.
                //
                // (Thus I RemoveSentMessage for the processNymbox message, since 
                // I'm totally done with it now.)
                //
//                final int nRemoved = otapi.OT_API_RemoveSentMessage(Integer.toString(nProcess), serverID, nymID);
                // NOTE: The above call is unnecessary, since a successful process means
                // we already received the successful server reply, and OT's "ProcessServerReply"
                // already removed the sent message from the sent buffer (so no need to do that here.)
                //
            } else if (bProcessAnyError || bProcessAnyFailure) // let's resync, and clawback whatever transaction numbers we might have used on the processNymbox request...
            {
                nGetNymbox = Utility.getNymbox(serverID, nymID, true); // bForceDownload=true - NOTE: could maybe change this to false and have it still work.

                if (nGetNymbox < 1) {
                    System.out.println("Utility.getAndProcessNymbox(): Failure: Utility.getNymbox returned: " + nGetNymbox);
                    return (-1);
                }

                OTBool bWasFound = new OTBool(false);

                if (Utility.insureHaveAllBoxReceipts(serverID, nymID, nymID, 0, // nBoxType = 0 aka nymbox
                        nProcess, bWasFound)) // This will tell us whether the processNymbox reply was found in the Nymbox
                {
                    // we FOUND the processNymbox reply in the Nymbox!
                    //
                    if (bWasFound.getBooleanValue()) {
                        // Thus, no need to clawback any transaction numbers,
                        // since the server clearly already processed this processNymbox
                        // transaction, since I have a reply to it already sitting in my Nymbox.
                        //
//                        final int nRemoved = otapi.OT_API_RemoveSentMessage(Integer.toString(nProcess), serverID, nymID);
                        //
                        // NOTE: The above call is unnecessary, since a successful process means
                        // we already received the successful server reply, and OT's "ProcessServerReply"
                        // already removed the sent message from the sent buffer (so no need to do that here.)

                        System.out.println("Utility.getAndProcessNymbox(): FYI: I *did* find the @processNymbox reply in my Nymbox, so NO NEED to clawback any transaction numbers.");
                    } else // was NOT found... we need to clawback.
                    {
                        // This means the server's reply was definitely NOT found in the
                        // Nymbox, even after successfully DOWNLOADING that Nymbox. Which
                        // means the server never got it in the first place, or rejected it
                        // at the message level before the transaction portion had a chance
                        // to run. Either way, we need to claw back any relevant transaction
                        // numbers...

                        // HARVEST the processNymbox message from outgoing messages.

                        System.out.println("Utility.getAndProcessNymbox 2: FYI: Calling OT_API_GetSentMessage...");

                        final String strSentProcessNymboxMsg = otapi.OT_API_GetSentMessage(Integer.toString(nProcess), serverID, nymID);

                        if (!Utility.isValid(strSentProcessNymboxMsg)) {
                            System.out.println("Utility.getAndProcessNymbox(): ERROR: (SHOULD NEVER HAPPEN 2) Expected OT_API_GetSentMessage to return the sent processNymbox message (for clawback) but couldn't find it. (Expected it--I JUST sent it!)");
                        } else // strSentProcessNymboxMsg NOT null!
                        {
                            System.out.println("Utility.getAndProcessNymbox(): FYI: Harvesting transaction numbers from failed processNymbox attempt...");
                            // ------------------------------------
                            nHarvested = otapi.OT_API_Msg_HarvestTransactionNumbers(strSentProcessNymboxMsg, nymID,
                                    0, // bHarvestingForRetry == false
                                    bProcessNymboxReplySuccess ? 1 : 0, // bReplyWasSuccess,       // RECEIVED server reply: explicit success.
                                    bProcessNymboxReplyFailure ? 1 : 0, // bReplyWasFailure,       // RECEIVED server reply: explicit failure.
                                    bProcessNymboxTransSuccess ? 1 : 0, // bTransactionWasSuccess, // MESSAGE success, Transaction success. (Explicit.)
                                    bProcessNymboxTransFailure ? 1 : 0);  // bTransactionWasFailure  // MESSAGE success, Transaction failure. (Explicit.)

                            System.out.println("Utility.getAndProcessNymbox(): OT_API_Msg_HarvestTransactionNumbers: " + nHarvested);

                            final int nRemovedProcessNymboxMsg = otapi.OT_API_RemoveSentMessage(Integer.toString(nProcess), serverID, nymID);

                            System.out.println("Utility.getAndProcessNymbox(): OT_API_RemoveSentMessage: " + nRemovedProcessNymboxMsg);

                        } // strSentProcessNymboxMsg NOT null!
                    } // a specific receipt was not found in the nymbox (need to clawback the transaction numbers on that receipt.)
                    // ----------------------------------------------------------------

                    strNymbox = otapi.OT_API_LoadNymboxNoVerify(serverID, nymID);      // FLUSH SENT MESSAGES!!!!  (AND HARVEST.)

                    // *******************************************************
                    if (Utility.isValid(strNymbox)) {
                        otapi.OT_API_FlushSentMessages(0, //harvesting for retry == OT_FALSE
                                serverID,
                                nymID,
                                strNymbox);
                    } // Flushing removes all the messages from the "sent messages" queue,
                    // and harvests any transaction numbers to be had. How do I know for sure
                    // that I can get away with this? How do I know whether the server has
                    // processed those messages or not? How can I harvest them as though they
                    // were dropped on the network somewhere? The answer is because I JUST
                    // called GetNymbox and downloaded the latest one. I can SEE which replies
                    // are in there -- and which ones aren't. I pass that Nymbox into the flush
                    // call, so that flush can be careful to remove all sent messages that have
                    // nymbox replies, and only harvest the others.
                    // *******************************************************
                    else {
                        System.out.println("Utility.getAndProcessNymbox(): Error while trying to flush sent messages: Failed loading Nymbox for nym: " + nymID);
                    }
                    // -------------------------------------------------
                } // if insureHaveAllBoxReceipts()
                else // we do NOT have all the box receipts.
                {
                    System.out.println("Utility.getAndProcessNymbox(): Error: Utility.insureHaveAllBoxReceipts failed. (I give up.)");
                    return (-1);
                }
            } // else if (bProcessAnyError || bProcessAnyFailure)
            // --------------------------------------------------------
            // Return the request number, if potentially needed by caller.
            // If explicit success, the request number is returned as a positive
            // number (though already removed from sent queue.) Whereas if explicit
            // failure (reply status=failed) then we harvest the numbers
            //
            if (bProcessAllSuccess) //              return Utility.getNymbox(serverID, nymID, true); // bForceDownload=true. Since we DID process it successfully, then we grab it again.
            {
                return 1;  // We don't need the sent message after this, and we've already removed it from sent queue.
            }
            if (bProcessAnyFailure || bProcessAnyError) {
                if (nHarvested < 1) // If the message failed, and the harvesting failed, then we return the request 
                {
                    return nProcess; // number for the process nymbox, so the caller has a choice of what to do next.
                }
                if (bProcessAnyFailure) {
                    return 0;        // by this point, we've definitely harvested, AND removed sent message from sent queue. So we just return 0 so the caller can see the server said FAILED.
                }
            }

            return (-1); // must've been an error.
        } // if Utility.insureAllBoxReceipts()
        else {
            System.out.println("Utility.getAndProcessNymbox(): Utility.insureHaveAllBoxReceipts failed. (I give up.)");
        }

        return (-1);
    }

    // ---------------------------------------------------------
    // PROCESS NYMBOX
    //
    // Returns:
    // -1 Error.
    //  0 Nymbox was empty -- nothing done. (bWasMsgSent = false)
    //  0 server reply received, but it says success==false on that msg. (bWasMsgSent = true)
    // >0 If the Transaction status (from the server reply) is SUCCESS, then this function
    //    returns the REQUEST NUMBER from when it was originally sent. 
    //
    public static int processNymbox(String serverID, String nymID,
            OTBool bWasMsgSent,
            // --------------------------------
            OTInteger nMsgSentRequestNumOut,
            OTInteger nReplySuccessOut,
            OTInteger nBalanceSuccessOut,
            OTInteger nTransSuccessOut) {
        bWasMsgSent.setBooleanValue(false);

        // ----------------------------------

        if ((null == bWasMsgSent) || (null == nReplySuccessOut) || (null == nBalanceSuccessOut) || (null == nTransSuccessOut)) {
            System.out.println("SHOULD NEVER HAPPEN: processNymbox has null values passed in...");
            return -1;
        }

        nMsgSentRequestNumOut.setIntegerValue(-1);
        nReplySuccessOut.setIntegerValue(-1);
        nBalanceSuccessOut.setIntegerValue(-1);
        nTransSuccessOut.setIntegerValue(-1);

        // -- SECTION 2: "SEND PROCESS NYMBOX"
        //

        // Next, we have to make sure we have all the BOX RECEIPTS downloaded
        // for this Nymbox.        
        //-------------------------------------------------------------------------------

        //
        final int nProcess = Utility.sendProcessNymboxLowLevel(serverID, nymID); // <===================== SEND PROCESS NYMBOX!!

        switch (nProcess) {
            case (-1):
                System.out.println("Utility.processNymbox(): Failure: Utility.insureHaveAllBoxReceipts: error (-1), when calling sendProcessNymboxLowLevel. (It couldn't send. I give up.)");
                return (-1); // (It didn't even send.)
            case (0):   // Nymbox was empty. (So we didn't send any process message because there was nothing to process.)
                return 0; // success. done.
            default:
                if (nProcess < 0) {
                    System.out.println("Utility.processNymbox(): Failure: Utility.insureHaveAllBoxReceipts: unexpected: " + nProcess + ", when calling sendProcessNymboxLowLevel. (I give up.)");
                    return (-1);
                }
                // (else break)
                break; // Success! 

        } // switch

        bWasMsgSent.setBooleanValue(true);
        nMsgSentRequestNumOut.setIntegerValue(nProcess);
        // ------------------------------------------  
        // By this point, we definitely have a >0 request number from the sendProcessNymbox()
        // call, stored in  ** nProcess ** (meaning the message WAS sent.)
        //
        // But was it received?
        //
        final String strReplyProcess =
                Utility.ReceiveReplyLowLevel(serverID, nymID, nProcess,
                "Utility.processNymbox / sendProcessNymboxLowLevel / ReceiveReplyLowLevel"); // <=============== Here we RECEIVE the REPLY...

        // -----------------------------------------------        
        // Utility.getLastReplyReceived() will also contain the same as strReplyProcess.
        // So if the CALLER of this function (that we're in, receiveNymboxLowLevel)
        // wants to see the contents, he can.
        // ------------------------------------------
        // ReceiveReplyLowLevel returns null unless there was a string returned.
        // So we can directly check it for success...

        final int nReplySuccess = Utility.getMessageSuccess(strReplyProcess); // sendProcessNymboxLowLevel
        final int nTransSuccess, nBalanceSuccess;

        if (nReplySuccess > 0) // If message was success, then let's see if the transaction was, too.
        {
            nBalanceSuccess = otapi.OT_API_Message_GetBalanceAgreementSuccess(serverID, nymID, nymID, strReplyProcess); // the processNymbox transaction.

            if (nBalanceSuccess > 0) {
                nTransSuccess = otapi.OT_API_Message_GetTransactionSuccess(serverID, nymID, nymID, strReplyProcess); // the processNymbox transaction.
            } else {
                nTransSuccess = (-1);
            }
        } else {
            nBalanceSuccess = -1;
            nTransSuccess = -1;
        }

        nReplySuccessOut.setIntegerValue(nReplySuccess);
        nBalanceSuccessOut.setIntegerValue(nBalanceSuccess);
        nTransSuccessOut.setIntegerValue(nTransSuccess);

        // ------------------------------------------  
        // NOTE: The caller MUST have a call to OT_API_RemoveSentMessage
        // to correspond to THIS function's call of sendProcessNymboxLowLevel().
        //
        if (nTransSuccess > 0) {
            return nProcess;  // <=========================
        } else {
            return nTransSuccess;
        }
    }

    // No need to deal with getRequest here when failure, since the calling
    // function already goes through that crap before we get here.
    // Returns: the request number for the process Nymbox request.
    // OR returns 0 if the Nymbox was empty (and no message was sent.)
    // OR returns -1 if there was an error.
    //
    // DONE
    public static int sendProcessNymboxLowLevel(String serverID, String nymID) // bWasSent is an output param allowing to return whether the request was even sent.
    {
        // ------------------------------------------
        // Send message..
        otapi.OT_API_FlushMessageBuffer();

        int nRequestNum = otapi.OT_API_processNymbox(serverID, nymID);

        // ------------------------------------------
        if ((-1) == nRequestNum) {
            otapi.OT_API_Output(0, " Utility.processNymbox: Failure sending. OT_API_processNymbox() returned -1. \n");
            return (-1); // no need to check for any reply.
        } // ------------------------------------------
        else if (nRequestNum < 0) {
            otapi.OT_API_Output(0, " Utility.processNymbox: Failure: OT_API_processNymbox() returned unexpected value: " + nRequestNum);
            otapi.OT_API_Output(0, "\n");
            return (-1); // no need to check for any reply.
        } // ------------------------------------------
        else if (0 == nRequestNum) {
            otapi.OT_API_Output(0, " Utility.processNymbox: Nymbox was empty; no need to process it. \n");
            return 0;	// Nymbox is empty, thus no need to process it.
        }

        // Note: I do NOT call RemoveSentMessage for processNymbox, at least, not here.
        // Instead, the place that CALLS this function, will actually use that because 
        // it has to be able to harvest the transaction numbers in certain failure cases.

        return nRequestNum;
    }

    // ------------------------------------------
    // after calling Utility.sendProcessNymboxLowLevel, then
    // call this:
    // int receiveReplySuccessLowLevel(String IN_FUNC)
    /*
     * What might happen "in reply" when you send a message to the OT server,
     * successfully receive the request number from the send call, and then you
     * pop the message buffer to check for the actual server reply?
     *
     * 1. Returns null. 2. Returns an empty string. (Just being redundant,
     * really.) 3. Returns the proper message, failed. 4. Returns the proper
     * message, succeeded. TRANSACTIONS ONLY: 5. Returns the proper message,
     * succeeded: BUT Balance Agreement failed! 6. Returns the proper message,
     * succeeded: AND Balance Agreement success, but TRANSACTION failed. 7.
     * Returns the proper message, succeeded: AND Balance Agreement success, AND
     * TRANSACTION succeeded.
     */
    public static boolean isValid(String strInput) {
        if (strInput == null || strInput.length() < 1) {
            return false;
        }
        return true;
    }

    // Returns:
    //    -1 if error, such as failure to load the message
    // and 0 if the message is failure
    // and 1 if the message is a sucess
    //
    public static int getMessageSuccess(String strInput) {
        if (!Utility.isValid(strInput)) {
            System.out.println("Utility.getMessageSuccess(): null or empty input string. (Returning error condition of -1.)");
            return (-1);
        }
        // ---------------------------------------------------  

        int nSuccess = otapi.OT_API_Message_GetSuccess(strInput); // <==================

        // ---------------------------------------------------

        switch (nSuccess) {
            case (-1):
                System.out.println("Utility.getMessageSuccess(): Error calling OT_API_Message_GetSuccess, for message:\n\n" + strInput);
                break;
            case (0):
                System.out.println("Utility.getMessageSuccess(): Reply received: success == FALSE. Reply message:\n\n" + strInput);
                break;
            case (1):
                System.out.println("Utility.getMessageSuccess(): Reply received: success == TRUE.");
                break;
            default:
                System.out.println("Utility.getMessageSuccess(): Error. (This should never happen!) Input: " + strInput);
                nSuccess = (-1);
                break;
        }

        return nSuccess;
    }
    // ------------------------------------------------------

    // returns:
    // -1 for error, 
    //  0 for server reply of failure, 
    //  1 for server reply of success
    //
    public static int receiveReplySuccessLowLevel(String serverID, String nymID, int nRequestNumber, String IN_FUNC) {
        final String strReply = Utility.ReceiveReplyLowLevel(serverID, nymID, nRequestNumber,
                "Utility.receiveReplySuccessLowLevel: " + IN_FUNC); // <=============== Here we RECEIVE the REPLY...

        // -----------------------------------------------        
        // Utility.getLastReplyReceived() will also contain the same as strReply.
        // So if the CALLER of this function (that we're in, receiveNymboxLowLevel)
        // wants to see the contents, he can.
        // ------------------------------------------
        // ReceiveReplyLowLevel returns null unless there was a string returned.
        // So we can directly check it for success...

        return Utility.getMessageSuccess(strReply);
    }
    // ------------------------------------------------------

    // Tries to receive a server reply
    // (for a message that you presumably just sent.)
    // If successful, returns the server reply. Otherwise returns null.
    // (Successful meaning, a valid-formed message was received. Whether that is a
    // "success=true" or "success=false" message, the caller will have to figure
    // that out for himself.)
    //
    public static String ReceiveReplyLowLevel(String serverID, String nymID, int nRequestNumber, String IN_FUNCTION) {
        Utility.delay();
        Utility.setLastReplyReceived(null);
        // --------------------------------------------------------------------
        final String strResponseMessage = otapi.OT_API_PopMessageBuffer(Integer.toString(nRequestNumber),
                serverID, nymID);

        if (!Utility.isValid(strResponseMessage)) {
            System.out.println("Utility.ReceiveReplyLowLevel (" + IN_FUNCTION + "): null server reply!");
            return null;
        }
        Utility.setLastReplyReceived(strResponseMessage);
        // --------------------------------------------------------------------
        return strResponseMessage;
    }

    // ---------------------------------------------------
    public static int getRequestNumber(String serverID, String nymID) {
        OTBool bWasSent = new OTBool(false);

        return Utility.getRequestNumber(serverID, nymID, bWasSent);
    }

    // -1 == error (couldn't send, or couldn't receive)
    //  0 == success false (received reply from server)
    //  1 == success true  (received reply from server)
    //
    // To distinguish between error where message wasn't sent,
    // and error where message WAS sent, but reply never received,
    // bWasSent will be set to TRUE once this function is sure that
    // it was sent out. (which you only care about if (-1) was the
    // return value, since otherwise you already KNOW you had a
    // server reply, AND its status.
    // DONE
    public static int getRequestNumber(String serverID, String nymID, OTBool bWasSent) // bWasSent is an output param allowing to return whether the request was even sent.
    {
        otapi.OT_API_FlushMessageBuffer();

        final int nResult = otapi.OT_API_getRequest(serverID, nymID);

        if (nResult == (-1)) // if error (-1), that means it DIDN'T SEND (error)
        {
            System.out.println("Utility.getRequestNumber: Failed to send getRequest message due to error.");
            return (-1);
        } else if (nResult == 0) // if 0 is returned, that also means it DIDN'T SEND (but there was NO error...)
        {                       // I don't know if this case can actually even HAPPEN... but if it does, I'll log it.
            System.out.println("Utility.getRequestNumber: Didn't send this getRequest message, but NO error occurred, either. (Should never happen.)");
            return (-1); // Since the 0 case should never happen, I'm returning it as an ERROR (-1).
            // Note: I could never return 0 here, because that would imply that we RECEIEVED a SERVER REPLY,
            // and that the server's reply said "success == 0". But that's not what happened here. Here,
            // we couldn't even SEND our request, which is an error
        }
        //
        // else it's >0  ==  successfully sent! (I BELIEVE this is 1, in this case, every time, since you don't NEED a request number to CALL getRequestNum since you are only calling it in the first place because it must have gotten out of sync.)
        //
        bWasSent.setBooleanValue(true);
        // ***************************************************
        //
        final int nReturn = Utility.receiveReplySuccessLowLevel(serverID, nymID, nResult, "Utility.getRequestNumber");
//        System.out.println("IN Utility.getRequestNumber " + Utility.getLastReplyReceived());

        // BY this point, we definitely have the request number in nResult, which means
        // the message was actually SENT. (At least.) This also means we can use nResult
        // later to query for a copy of that sent message.
        // Let's go ahead, in this case, and remove that now:
        //
        //final int nRemovedGetRequest = otapi.OT_API_RemoveSentMessage(Integer.toString(nResult), serverID, nymID);

        // NOTE: The above call is unnecessary, since a successful reply means
        // we already received the successful server reply, and OT's "ProcessServerReply"
        // already removed the sent message from the sent buffer (so no need to do that here.)

//        if (nRemovedGetRequest < 1)
//        {
//            System.out.println("Utility.getRequestNumber: ERROR: OT_API_RemoveSentMessage returned: " + nRemovedGetRequest);
//        }
        // ----------------------------------------------

        return nReturn;
    }
    // --------------------------------------------------------------

    /*
     * // This returns -1 if error, or a positive request number if it was sent.
     * // (It cannot return 0.) // Called by getAndProcessNymbox. public static
     * int getNymboxLowLevel(String serverID, String nymID) {
     * otapi.OT_API_FlushMessageBuffer(); //
     * --------------------------------------------------------------------
     * final int nRequestNum = otapi.OT_API_getNymbox(serverID, nymID); //
     * <===== ATTEMPT TO SEND THE MESSAGE HERE...
     *
     * switch(nRequestNum) { case (-2):
     * System.out.println("Utility.getNymboxLowLevel: ERROR, not supported. (-2
     * was returned.)"); return (-1); // -2 is also possible at some future
     * date. (If the request number won't fit in an int, this is returned and
     * then you can retrieve the actual number via a separate call.) case (-1):
     * // if the requestNumber returned by the send-attempt is -1, that means it
     * DIDN'T SEND (error) System.out.println("Utility.getNymboxLowLevel: Failed
     * to send getNymbox message due to error."); return (-1); case (0):
     * System.out.println("Utility.getNymboxLowLevel: Didn't send getNymbox
     * message, but NO error occurred, either. (In this case, SHOULD NEVER
     * HAPPEN. Treating as Error.)"); return (-1); // Even though '0' MEANS
     * "didn't send, but no error" by convention in many places, it is actually
     * an impossible return value from getNymbox. Therefore I treat it as an
     * error. default: if (nRequestNum < 0) {
     * System.out.println("Utility.getNymboxLowLevel: Unexpected request number:
     * " + nRequestNum); return (-1); } // ------------------------- break; //
     * SUCCESS! } // -------------------------- // // BY this point, we
     * definitely have the request number, which means the // message was
     * actually SENT. (At least.) This also means we can use nRequestNum //
     * later to query for a copy of that sent message. // //
     * ----------------------------------------------
     *
     * return nRequestNum; }
     */
    // called by getBoxReceiptWithErrorCorrection   DONE
    public static boolean getBoxReceiptLowLevel(String serverID, String nymID, String accountID, int nBoxType, String strTransactionNum, OTBool bWasSent) {
        bWasSent.setBooleanValue(false);

        otapi.OT_API_FlushMessageBuffer();
        // --------------------------------------------------------------------
        final int nRequestNum = otapi.OT_API_getBoxReceipt(serverID, nymID, accountID, nBoxType, strTransactionNum); // <===== ATTEMPT TO SEND THE MESSAGE HERE...

        switch (nRequestNum) {
            case (-2):
                System.out.println("Utility.getBoxReceiptLowLevel: ERROR, not supported. (-2 was returned.)");
                return false; // -2 is also possible at some future date. (If the request number won't fit in an int, this is returned and then you can retrieve the actual number via a separate call.)
            case (-1):      // if the requestNumber returned by the send-attempt is -1, that means it DIDN'T SEND (error)
                System.out.println("Utility.getBoxReceiptLowLevel: Failed to send getNymbox message due to error.");
                return false;
            case (0):
                System.out.println("Utility.getBoxReceiptLowLevel: Didn't send getNymbox message, but NO error occurred, either. (In this case, SHOULD NEVER HAPPEN. Treating as Error.)");
                return false; // Even though '0' MEANS "didn't send, but no error" by convention in many places, it is actually an impossible return value from getNymbox. Therefore I treat it as an error.
            default:
                if (nRequestNum < 0) {
                    System.out.println("Utility.getBoxReceiptLowLevel: Unexpected request number: " + nRequestNum);
                    return false;
                }
                // -------------------------
                break; // SUCCESS!
        }
        // --------------------------
        //
        bWasSent.setBooleanValue(true);
        //
        // BY this point, we definitely have the request number, which means the
        // message was actually SENT. (At least.) This also means we can use nRequestNum
        // later to query for a copy of that sent message.
        //
        // ***************************************************
        //
        int nReturn = Utility.receiveReplySuccessLowLevel(serverID, nymID, nRequestNum, "Utility.getBoxReceiptLowLevel");

        System.out.println("IN Utility.getBoxReceiptLowLevel: nRequestNum: " + nRequestNum + " /  nReturn: " + nReturn);

        // ---------------------------------------------

//        final int nRemovedGetBoxReceipt = otapi.OT_API_RemoveSentMessage(Integer.toString(nRequestNum), serverID, nymID);
//
//        // NOTE: The above call is unnecessary, since a successful reply means
//        // we already received the successful server reply, and OT's "ProcessServerReply"
//        // already removed the sent message from the sent buffer (so no need to do that here.)
//        
//        if (nRemovedGetBoxReceipt < 1)
//        {
//            System.out.println("Utility.getBoxReceiptLowLevel: ERROR: OT_API_RemoveSentMessage returned: " + nRemovedGetBoxReceipt);
//        }
        // ---------------------

        if (nReturn > 0) {
            return true;
        }

        System.out.println("Failure in Utility.getBoxReceiptLowLevel : Response from server: " + Utility.getLastReplyReceived());

        return false;
    }

    // called by insureHaveAllBoxReceipts     DONE
    public static boolean getBoxReceiptWithErrorCorrection(String serverID, String nymID, String accountID, int nBoxType, String strTransactionNum) {
        OTBool bWasSent = new OTBool(false), bWasRequestSent = new OTBool(false);
        // ------------------------------------------
        if (Utility.getBoxReceiptLowLevel(serverID, nymID, accountID, nBoxType, strTransactionNum, bWasSent)) {
            return true;
        } else if (bWasSent.getBooleanValue() && (1 == Utility.getRequestNumber(serverID, nymID, bWasRequestSent))) // this might be out of sync, if it failed... we'll re-sync, and re-try.
        {
            if (bWasRequestSent.getBooleanValue()
                    && Utility.getBoxReceiptLowLevel(serverID, nymID, accountID, nBoxType, strTransactionNum, bWasSent)) {
                return true;
            } else {
                System.out.println("Utility.getBoxReceiptWithErrorCorrection(): Utility.getBoxReceiptLowLevel failed, then Utility.getRequestNumber succeeded, then Utility.getBoxReceiptLowLevel failed again. (I give up.)");
            }
        } else {
            System.out.println("Utility.getBoxReceiptWithErrorCorrection(): Utility.getBoxReceiptLowLevel failed, then Utility.getRequestNumber failed. (I give up.) Was getRequest message sent: " + bWasRequestSent.getBooleanValue());
        }
        return false;
    }

    // This function assumes you just downloaded the latest version of the box (inbox, outbox, or nymbox)
    // and its job is to make sure all the related box receipts are downloaded as well and available, though
    // not necessarily loaded into memory. (Yet.)
    // DONE
    public static boolean insureHaveAllBoxReceipts(String serverID, String nymID, String accountID, int nBoxType) {
        OTBool bFoundIt = new OTBool(false);

        final int nRequestSeeking = 0;

        return insureHaveAllBoxReceipts(serverID, nymID, accountID, nBoxType, nRequestSeeking, bFoundIt);
    }

    // ---------------------------------------------------------------
    public static boolean insureHaveAllBoxReceipts(String serverID, String nymID, String accountID, int nBoxType, int nRequestSeeking, OTBool bFoundIt) {
        // -------------------

        String ledger = null;

        // -------------------
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
        if (!Utility.isValid(ledger) || (0 == otapi.OT_API_VerifySignature(nymID, ledger))) {
            System.out.println("Utility.insureHaveAllBoxReceipts(): Unable to load or verify signature on ledger. (Failure.) Contents: " + ledger);
            return false;
        }
        // ----------------------------------------------
        // At this point, the box is definitely loaded.
        // Next we'll iterate the receipts
        // within, and for each, verify that the Box Receipt already exists. If not,
        // then we'll download it using getBoxReceiptLowLevel(). If any download fails,
        // then we break out of the loop (without continuing on to try the rest.)
        //
        boolean bReturnValue = true; // Assuming an empty box, we return success by default.

        final int nReceiptCount = otapi.OT_API_Ledger_GetCount(serverID, nymID, accountID, ledger);

        if (nReceiptCount > 0) {
            for (int i = 0; i < nReceiptCount; i++) {
                final String strTransactionNum = otapi.OT_API_Ledger_GetTransactionIDByIndex(serverID, nymID, accountID, ledger, i);

                if (Utility.isValid(strTransactionNum) && !strTransactionNum.equals("-1")) {
                    final Long lTransactionNum = Long.valueOf(strTransactionNum);

                    if (lTransactionNum > 0) {
                        final boolean bHaveBoxReceipt =
                                (1 == otapi.OT_API_DoesBoxReceiptExist(serverID, nymID, accountID, nBoxType, strTransactionNum))
                                ? true : false;

                        if (!bHaveBoxReceipt) {
                            System.out.println("Utility.insureHaveAllBoxReceipts(): Downloading box receipt to add to my collection...");

                            final boolean bDownloaded = Utility.getBoxReceiptWithErrorCorrection(serverID, nymID, accountID, nBoxType, strTransactionNum);

                            if (!bDownloaded) {
                                System.out.println("Utility.insureHaveAllBoxReceipts(): Failed downloading box receipt. (Skipping any others.) Transaction number: " + strTransactionNum);
                                bReturnValue = false;
                                break;
                                // No point continuing to loop and fail 500 times, when getBoxReceiptWithErrorCorrection() already failed
                                // even doing the getRequest() trick and everything, and whatever retries are inside OT, before it finally
                                // gave up.
                            }
                            // else (Download success.)
                        } // if (!bHaveBoxReceipt)
                        // else we already have the box receipt, no need to download again.
                    } // if (lTransactionNum > 0)
                    else {
                        System.out.println("Utility.insureHaveAllBoxReceipts(): Error: TransactionNum less-than-or-equal-to 0.");
                    }
                } else {
                    System.out.println("Utility.insureHaveAllBoxReceipts(): Error: TransactionNum was null, when trying to read it based on the index (within bounds, too.)");
                }
            } // for
        } // if (nReceiptCount > 0)
        // ---------------------------------------------
        // 
        // if nRequestSeeking is >0, that means the caller wants to know if there is a receipt present for that request number.
        // (which is only a valid option if nBoxType == 0 for Nymbox.)
        // IF the receipt is found, then bFoundIt is set to true.
        //
        if ((nRequestSeeking > 0) && (0 == nBoxType)) {
            // NOTE: the below call to OT_API_Nymbox_GetReplyNotice will succeed even if
            // only the abbreviated receipt is available, because the caller mainly just
            // wants to know if it is there.
            // Technically the full receipt SHOULD always be there, with the above loop,
            // but since the above loop can break in case of error, it's still possible that
            // box receipts haven't been downloaded by the time you reach this code.
            // Nevertheless, we will see if the reply is there for the appropriate request
            // number, whether abbreviated or not.
            //
            final String strReplyNotice = otapi.OT_API_Nymbox_GetReplyNotice(serverID, nymID, Integer.toString(nRequestSeeking));

            if (Utility.isValid(strReplyNotice)) {
                bFoundIt.setBooleanValue(true);
            }
        }

        // ---------------------------------------------

        return bReturnValue;
    }

    /*
     * static void getBoxReceipt( const std::string	SERVER_ID, const std::string
     * USER_ID, const std::string	ACCT_ID,	// If for Nymbox (vs inbox/outbox)
     * then pass USER_ID in this field also. const int	nBoxType,	// 0/nymbox,
     * 1/inbox, 2/outbox const std::string	TRANSACTION_NUMBER);
     *
     * static bool DoesBoxReceiptExist(const std::string	SERVER_ID, const
     * std::string	USER_ID, const std::string	ACCT_ID,	// If for Nymbox (vs
     * inbox/outbox) then pass USER_ID in this field also. const int	nBoxType,
     * // 0/nymbox, 1/inbox, 2/outbox const std::string	TRANSACTION_NUMBER);
     */
    // If the transaction number requests fail, this function will try to resync
    // the request number. But you still have to call getRequest() yourself if
    // you have a failure in your own function, since you might already have
    // enough transaction numbers, and thus this function will never get called,
    // even if your request number IS out of sync. Sorry :-)
    //
    public static int getTransactionNumLowLevel(String serverID, String nymID, OTBool bWasSent) {
        otapi.OT_API_FlushMessageBuffer();
        bWasSent.setBooleanValue(false);
        // --------------------------------------------------------------------
        final int nRequestNum = otapi.OT_API_getTransactionNumber(serverID, nymID); // <===== ATTEMPT TO SEND THE MESSAGE HERE...

        switch (nRequestNum) {
            case (-2):
                System.out.println("Utility.getTransactionNum: ERROR, not supported. (-2 was returned.)");
                return (-1); // -2 is also possible at some future date. (If the request number won't fit in an int, this is returned and then you can retrieve the actual number via a separate call.)
            case (-1):      // if the requestNumber returned by the send-attempt is -1, that means it DIDN'T SEND (error)
                System.out.println("Utility.getTransactionNum: Failed to send getNymbox message due to error.");
                return (-1);
            case (0):
                System.out.println("Utility.getTransactionNum: Unexpectedly returned 0. Didn't send getTransactionNum message, but NO error occurred, either. (In this case, SHOULD NEVER HAPPEN. Treating as Error.)");
                return (-1); // Even though '0' MEANS "didn't send, but no error" by convention in many places, it is actually an impossible return value from getTransactionNum. Therefore I treat it as an error.
            default:
                if (nRequestNum < 0) {
                    System.out.println("Utility.getTransactionNum: Unexpected request number: " + nRequestNum);
                    return (-1);
                }
                // -------------------------
                break; // SUCCESS!
        }
        bWasSent.setBooleanValue(true);
        // ***************************************************
        //
        final int nReturn = Utility.receiveReplySuccessLowLevel(serverID, nymID, nRequestNum, "Utility.getTransactionNum");
//        System.out.println("IN Utility.getTransactionNum " + Utility.getLastReplyReceived());

        // BY this point, we definitely have the request number in nResult, which means
        // the message was actually SENT. (At least.) This also means we can use nResult
        // later to query for a copy of that sent message.
        // Let's go ahead, in this case, and remove that now:
        //

        // THE REMOVE SENT MESSAGE BELOW FAILS, LIKE IT'S ALREADY GONE.
        //
        // THIS MUST BE DUE TO THE PROCESS SERVER REPLY THAT OCCURS **IMMEDIATELY** after the message was originally sent!
        // (The reply came in and was sent to OT's "ProcessServerReply", INSIDE the call to OT_API_getTransactionNumber.)
        // Our subsequent "receive" (above) is nothing of the sort, but actually pops the incoming message buffer where
        // the server's reply was ALREADY SITTING, since it was put there in OT's "ProcessServerReply", WHICH REMOVED THE
        // SENT MESSAGE ALREADY (that's why the below call to RemoveSentMessage fails.)
        //
        // RETHINK any logic that doesn't take this into account,. 
        // Either we REMOVE this call wherever this happens, OR... we call Get first and make sure whether it's
        // there, THEN remove it. But we can't be lumping "Failure because it's gone" versus "Error state" by mixing
        // 0 and -1 here. We need to differentiate.
        //
        // Bottom line: if the reply WAS received, then the original sent message has ALREADY been removed
        // from the sent buffer. Whereas if the reply was NOT received, then the sent message is still there,
        // but in that case, we do NOT want to remove it -- we want it to STAY in the sent buffer, so that
        // when we get the Nymbox later and we DO have the reply from that, THEN we can remove the sent msg from
        // the sent buffer. Until then, we don't want OT to think it's already been processed (which it will, if
        // it's already been removed from the sent buffer. So we leave it there for now.)
        //
        // 
        //
//        final int nRemovedSentMsg = otapi.OT_API_RemoveSentMessage(Integer.toString(nRequestNum), serverID, nymID);
//
//        if (nRemovedSentMsg < 1)
//        {
//            System.out.println("Utility.getTransactionNum: ERROR: OT_API_RemoveSentMessage returned: " + nRemovedSentMsg);
//        }
        // ----------------------------------------------

        if (1 == nReturn) {
            return nRequestNum;
        }
        return nReturn;
    }

    // DONE
    public static boolean getTransactionNumbers(String serverID, String nymID) {
        return Utility.getTransactionNumbers(serverID, nymID, true); // bForceFirstCall == true (by default) but in special cases you can override it and set it to false.
    }

    public static boolean getTransactionNumbers(String serverID, String nymID, boolean bForceFirstCall) // defaults to true.
    {
// System.out.println("DEBUGGING -- 1.");
        //
        OTBool bWasSent = new OTBool(false);
        int nGetNumbers = -1;

        if (bForceFirstCall) {
            nGetNumbers = Utility.getTransactionNumLowLevel(serverID, nymID, bWasSent);   // <============ FIRST TRY
        } else {
            nGetNumbers = -1;
        }

// System.out.println("DEBUGGING -- 2.");

        if (!bForceFirstCall || // if the first call didn't happen, due to bForceFirstCall being false, that means the caller wants the rest of this to happen as though it did.  
                (bWasSent.getBooleanValue() && (nGetNumbers >= 1))
                || (!bWasSent.getBooleanValue() && (nGetNumbers == 0))) {
            // System.out.println("DEBUGGING -- 3.");

            // Because it was successful, we have to now SIGN FOR those numbers we requested.
            //
            int nProcess = Utility.getAndProcessNymbox(serverID, nymID, bWasSent, true); // bForceDownload=true

            // System.out.println("DEBUGGING -- 4.");

            if ((bWasSent.getBooleanValue() && (1 == nProcess))
                    || (!bWasSent.getBooleanValue() && (0 == nProcess))) {
                // System.out.println("DEBUGGING -- 5.");
                return true;
            }
        } else if ((nGetNumbers < (-1)) || // If value is LESS THAN (-1) (which is an unexpected value)
                !bWasSent.getBooleanValue()) // or if the getTransactionNum message WASN'T EVEN SENT, then return.
        {
            // System.out.println("DEBUGGING -- 6.");

            System.out.println("Utility.getTransactionNumbers: Failure: Utility.getTransactionNumLowLevel returned unexpected value: " + nGetNumbers);
            return false;
        } // -----------------------------------------------------------------
        // Below this point, the message WAS sent.  -1 is error, 0 is failure, >0 is success.
        // Now it's just about whether a reply was successful, or was even received.
        //
        else if (((-1) == nGetNumbers) || // Message sent, but then error receiving or loading the reply.
                ((0) == nGetNumbers)) // Received a reply, but status == failure on that reply.
        {
// System.out.println("DEBUGGING -- 7.");

            if ((-1) == nGetNumbers) {
                System.out.println("Utility.getTransactionNumbers: FYI: Utility.getTransactionNumLowLevel did send, but returned error (-1). (Re-trying...)");
            } else if ((0) == nGetNumbers) {
                System.out.println("Utility.getTransactionNumbers: FYI: Utility.getTransactionNumLowLevel did send, but returned failure (0). (Re-trying...)");
            }

            // ---------------------------------
            final int nGetRequest = Utility.getRequestNumber(serverID, nymID);

// System.out.println("DEBUGGING -- 8.");

            if (1 != nGetRequest) {
                System.out.println("Utility.getTransactionNumbers: Failure: Utility.getTransactionNumLowLevel failed, then "
                        + "I tried to resync with getRequestNumber and then that failed too. (I give up.)");
                return false;
            }
            // ---------------------------------

// System.out.println("DEBUGGING -- 9.");

            OTBool bWasProcessSent = new OTBool(false);
            OTBool bFoundNymboxItem = new OTBool(false);

            final int nProcessNymbox = Utility.getAndProcessNymbox(serverID, nymID, bWasProcessSent, true);   //boolean bForceDownload=true

// System.out.println("DEBUGGING -- 10.");

            if ((!bWasProcessSent.getBooleanValue() && ((nProcessNymbox < 0) || (nProcessNymbox > 1)))
                    || (bWasProcessSent.getBooleanValue() && (nProcessNymbox != 1))) // -1 error, 0 failed (harvesting success), 1 success, >1 failed (harvesting NOT done) RequestNum is returned.
            {
                // System.out.println("DEBUGGING -- 11.");

                // todo: if request num is returned probably don't have to do anything with it.
                // Why not?  Because future processNymbox will iterate Nymbox and search for all found
                // items in the sent message buffer, and REMOVE them from it (as clearly they will be
                // processed already.)
                // The ones left over in the sent buffer, after this? Must be harvested!
                // Hmm, solution: Use the "Flush Sent Messages" function, which is already
                // there. Have it be smart enough to harvest all sent messages before flushing,
                // 
                //
                if (bWasProcessSent.getBooleanValue() && nProcessNymbox > 1) {
                    // System.out.println("DEBUGGING -- 12.");

                    String strNymbox = otapi.OT_API_LoadNymboxNoVerify(serverID, nymID);      // FLUSH SENT MESSAGES!!!!  (AND HARVEST.)

                    // System.out.println("DEBUGGING -- 13.");

                    // *******************************************************
                    if (Utility.isValid(strNymbox)) {
                        otapi.OT_API_FlushSentMessages(0, //harvesting for retry == OT_FALSE
                                serverID,
                                nymID,
                                strNymbox);
                    }
                    // System.out.println("DEBUGGING -- 14.");
                }

                System.out.println("Utility.getTransactionNumbers: Failure: Utility.getAndProcessNymbox. Returned value: " + nProcessNymbox);
                return false;
            }

// System.out.println("DEBUGGING -- 15.");

            // -----------------------------------------------------------------

            nGetNumbers = Utility.getTransactionNumLowLevel(serverID, nymID, bWasSent);  // <================= SECOND TRY

// System.out.println("DEBUGGING -- 16.");

            // -----------------------------------------------------------------

            if ((bWasSent.getBooleanValue() && (nGetNumbers >= 1)) || // if message was sent, and was a success.
                    (!bWasSent.getBooleanValue() && (nGetNumbers == 0))) // Or if message wasn't sent due to "you already signed out too many numbers--you need to process your Nymbox..."
            {
// System.out.println("DEBUGGING -- 17.");

                int nProcess = Utility.getAndProcessNymbox(serverID, nymID, bWasSent, true); // bForceDownload=true

// System.out.println("DEBUGGING -- 18.");

                if ((bWasSent.getBooleanValue() && (1 == nProcess))
                        || (!bWasSent.getBooleanValue() && (0 == nProcess))) {
// System.out.println("DEBUGGING -- 19.");

                    return true;
                }
            } else if ((nGetNumbers < (-1))
                    || (!bWasSent.getBooleanValue() && nGetNumbers != 0)) {
// System.out.println("DEBUGGING -- 20.");

                System.out.println("Utility.getTransactionNumbers: Failure: Utility.getTransactionNumLowLevel returned unexpected value: " + nGetNumbers);
                return false;
            } else if (((-1) == nGetNumbers)
                    || ((0) == nGetNumbers)) {
                // System.out.println("DEBUGGING -- 21.");

                if ((-1) == nGetNumbers) {
                    System.out.println("Utility.getTransactionNumbers: Failure: Utility.getTransactionNumLowLevel did send, but returned error (-1), "
                            + "even after syncing the request number successfully. (Giving up.)");
                } else if ((0) == nGetNumbers) {
                    System.out.println("Utility.getTransactionNumbers: Failure: Utility.getTransactionNumLowLevel did send, but returned failure (0), "
                            + "even after syncing the request number successfully. (Giving up.)");
                }


                // System.out.println("DEBUGGING -- 22.");

                int nLast = Utility.getAndProcessNymbox(serverID, nymID, bWasProcessSent, true);   //boolean bForceDownload=true
                if (((false == bWasProcessSent.getBooleanValue()) && ((nLast < 0) || (nLast > 1)))
                        || ((true == bWasProcessSent.getBooleanValue()) && (nLast != 1))) // -1 error, 0 failed (harvesting success), 1 success, >1 failed (harvesting NOT done) RequestNum is returned.
                {

                    // System.out.println("DEBUGGING -- 23.");

                    if (bWasProcessSent.getBooleanValue() && (nLast > 1)) {

                        // System.out.println("DEBUGGING -- 24.");

                        String strNymbox = otapi.OT_API_LoadNymboxNoVerify(serverID, nymID);      // FLUSH SENT MESSAGES!!!!  (AND HARVEST.)

                        // System.out.println("DEBUGGING -- 25.");

                        // *******************************************************
                        if (Utility.isValid(strNymbox)) {
                            otapi.OT_API_FlushSentMessages(0, //harvesting for retry == OT_FALSE
                                    serverID,
                                    nymID,
                                    strNymbox);
                        }
                    }

                    System.out.println("Utility.getTransactionNumbers: Failure: Utility.getAndProcessNymbox. Returned value: " + nLast);
                    return false;
                }

                // System.out.println("DEBUGGING -- 27.");

                nGetNumbers = Utility.getTransactionNumLowLevel(serverID, nymID, bWasSent);   // <============ FIRST TRY      

                // System.out.println("DEBUGGING -- 28.");

                if ((bWasSent.getBooleanValue() && (nGetNumbers >= 1))
                        || ((!bWasSent.getBooleanValue() && (nGetNumbers == 0)))) {
                    // System.out.println("DEBUGGING -- 29.");


                    int nProcess = Utility.getAndProcessNymbox(serverID, nymID, bWasSent, true); // bForceDownload=true


                    // System.out.println("DEBUGGING -- 30.");


                    if ((bWasSent.getBooleanValue() && (1 == nProcess))
                            || (!bWasSent.getBooleanValue() && (0 == nProcess))) {
                        // System.out.println("DEBUGGING -- 31.");

                        return true;
                    }
                }
                if ((nGetNumbers < (-1)) || // If value is LESS THAN (-1) (which is an unexpected value)
                        !bWasSent.getBooleanValue()) // or if the getTransactionNum message WASN'T EVEN SENT, then return.
                {
                    // System.out.println("DEBUGGING -- 32.");

                    System.out.println("Utility.getTransactionNumbers: Failure: Utility.getTransactionNumLowLevel returned unexpected value: " + nGetNumbers);
                    return false;
                }
            }
        }

        // System.out.println("DEBUGGING -- 33.");

        // BY THIS POINT, we have SUCCESSFULLY sent the getTransactionNumLowLevel message,
        // and nGetNumbers contains its request number.
        // -----

        // No need to read the result, as getTransactionNumLowLevel() already read it,
        // and and it's available anytime via Utility.getLastReplyReceived()
        // -------------------------------------------------------------------------

        final String strLastReplyReceived = Utility.getLastReplyReceived();

        if (!Utility.isValid(strLastReplyReceived)) {
            System.out.println("Utility.getTransactionNumbers: "
                    + "ERROR in Utility.getLastReplyReceived(): why was this string not set, when Utility.getRequestNumber was otherwise an apparent success?");
            return false; // (SHOULD NEVER HAPPEN. This string is set in the getRequestNumber function.)
        }
        //-------------------------------------------------

        // BY THIS POINT, we have received a server reply:  @getTransactionNum
        // (Unless it is malformed.) It's definitely not null, nor empty.

        //-------------------------------------------------

        // Grab the NymboxHash on the @getTransactionNum reply, and also the one I
        // already had on my client-side Nym... (So we can compare them.)
        //
        final String strServerHash = otapi.OT_API_Message_GetNymboxHash(strLastReplyReceived);
        final boolean bServerhash = Utility.isValid(strServerHash);
        if (!bServerhash) {
            System.out.println("Utility.getTransactionNumbers: Warning: Unable to retrieve server-side "
                    + "NymboxHash from OT, from server @getTransactionNum reply:\n\n"
                    + strLastReplyReceived);
//            return false; 
        }
        //-------------------------------------------------                
        final String strLocalHash = otapi.OT_API_GetNym_NymboxHash(serverID, nymID);
        final boolean bLocalhash = Utility.isValid(strLocalHash);
        if (!bLocalhash) {
            System.out.println("Utility.getTransactionNumbers: Warning: Unable to retrieve client-side NymboxHash from OT, "
                    + "for:\n serverID: " + serverID + "\n nymID: " + nymID);
//            return false; 
        }
        //-------------------------------------------------

        if (!bServerhash || !bLocalhash
                || (bServerhash && bLocalhash
                && !strServerHash.equals(strLocalHash))) // the hashes don't match -- so let's definitely re-try to download the latest nymbox.
        {
            // the getRequest worked, and the server hashes don't match,
            // so let's get and process the Nymbox...
            //

            // System.out.println("DEBUGGING -- 34.");


            OTBool bWasProcessSent = new OTBool(false);
            OTBool bFoundNymboxItem = new OTBool(false);
            final int nGetNymbox = Utility.getAndProcessNymbox(serverID, nymID, bWasProcessSent, true);   //boolean bForceDownload=true

            if (((false == bWasProcessSent.getBooleanValue()) && ((nGetNymbox < 0) || (nGetNymbox > 1)))
                    || ((true == bWasProcessSent.getBooleanValue()) && (nGetNymbox != 1))) // -1 error, 0 failed (harvesting success), 1 success, >1 failed (harvesting NOT done) RequestNum is returned.
            {
                if (nGetNymbox > 1) {
                    String strNymbox = otapi.OT_API_LoadNymboxNoVerify(serverID, nymID);      // FLUSH SENT MESSAGES!!!!  (AND HARVEST.)

                    // *******************************************************
                    if (Utility.isValid(strNymbox)) {
                        otapi.OT_API_FlushSentMessages(0, //harvesting for retry == OT_FALSE
                                serverID,
                                nymID,
                                strNymbox);
                    }
                }

                System.out.println("Utility.getTransactionNumbers: Failure: Utility.getAndProcessNymbox returned unexpected value: " + nGetNymbox);
                return false;
            } else if ((-1) == nGetNymbox) // we'll try re-syncing the request number, then try again.
            {
                System.out.println("Utility.getTransactionNumbers: Failure: Utility.getAndProcessNymbox returned -1, even after syncing the request number successfully. (Giving up.)");
                return false;
            }
        }

        // System.out.println("DEBUGGING -- 35.");

        return true;
    }
    // -------------------------------

    public static String getCreditsFile(String fileName) {
        return otapi.QueryPlainString(fileName);
    }

//    public static Point getLocation(Dimension componentDimension) {
//
//        Point center = new Point(0, 0);
//        Dimension toolkitDimension = Toolkit.getDefaultToolkit().getScreenSize();
//        center.x = center.x + (toolkitDimension.width - componentDimension.width) / 2;
//        center.y = center.y + (toolkitDimension.height - componentDimension.height) / 2;
//
//        return center;
//    }

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

        if (component instanceof com.moneychanger.ui.custom.SteppedComboBox) {

            Dimension d = component.getPreferredSize();
            component.setPreferredSize(new Dimension(100, d.height));
            ((com.moneychanger.ui.custom.SteppedComboBox) component).setPopupWidth(d.width);
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

        if (component instanceof com.moneychanger.ui.custom.SteppedComboBox) {

            Dimension d = component.getPreferredSize();
            component.setPreferredSize(new Dimension(100, d.height));
            ((com.moneychanger.ui.custom.SteppedComboBox) component).setPopupWidth(d.width);
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
        return Utility.getIntermediaryFiles(serverID, nymID, accountID, false); //bForceDownload=false
    }

    public static boolean getIntermediaryFiles(String serverID, String nymID, String accountID, boolean bForceDownload) // bForceDownload=false
    {

        if (null == serverID || serverID.length() < 10) {
            System.out.println("Utility.getIntermediaryFiles: invalid serverID: " + serverID);
            return false;
        }
        if (null == nymID || nymID.length() < 10) {
            System.out.println("Utility.getIntermediaryFiles: invalid nymID: " + nymID);
            return false;
        }
        if (null == accountID || accountID.length() < 10) {
            System.out.println("Utility.getIntermediaryFiles: invalid accountID: " + accountID);
            return false;
        }
        // -----------------------------------------------------
        OTBool bWasSentInbox = new OTBool(false),
                bWasSentAccount = new OTBool(false);
        // -----------------------------------------------------
        int nGetInboxAcct = Utility.getInboxAccount(serverID, nymID, accountID, bWasSentInbox, bWasSentAccount, bForceDownload);

        if ( // if we received an error state, and the "getAccount" message wasn't even sent,
                // then no point doing a bunch of retries -- it failed.
                //
                ((-1) == nGetInboxAcct) && !bWasSentAccount.getBooleanValue()) {
            System.out.println("Utility.getIntermediaryFiles: Utility.getInboxAccount failed, without even sending getAccount. (Returning false.)");
            return false;
        } else if (!bWasSentInbox.getBooleanValue() && // If it wasn't sent, and 0 was returned, that means
                (0 == nGetInboxAcct)) // no error: we already have the latest inbox. (Nothing done.)
        {
            // we don't return true here because getOutbox has to happen also.
        } else if (1 != nGetInboxAcct) {
            System.out.println("Utility.getIntermediaryFiles: getInboxAccount failed. (Trying one more time...)");

            // --------------------------------------
            final int nGetRequest = Utility.getRequestNumber(serverID, nymID);

            if (1 != nGetRequest) {
                System.out.println("Utility.getIntermediaryFiles(): Failure: Utility.getInboxAccount failed, then I tried to resync with getRequestNumber and then that failed too. (I give up.)");
                return false;
            }
            // --------------------------------------
            final int nSecondtry = Utility.getInboxAccount(serverID, nymID, accountID, bWasSentInbox, bWasSentAccount, bForceDownload);

            if (((-1) == nSecondtry) && !bWasSentAccount.getBooleanValue()) {
                // if we received an error state, and the "getAccount" message wasn't even sent,
                // then no point doing a bunch of retries -- it failed.
                //
                System.out.println("Utility.getIntermediaryFiles: Utility.getInboxAccount failed a second time, without even sending getAccount. (Returning false.)");
                return false;
            }
            if (!bWasSentInbox.getBooleanValue() && // If it wasn't sent, and 0 was returned, that means
                    (0 == nSecondtry)) // no error: we already have the latest inbox. (Nothing done.)
            {
                // we don't return true here because getOutbox has to happen also.
            } else if (1 != nSecondtry) {
                System.out.println("Utility.getIntermediaryFiles: getInboxAccount re-try failed. (That's twice now--Returning false.) Value: " + nSecondtry);
                return false;
            }
            System.out.println("Utility.getIntermediaryFiles: getInboxAccount second call succeeded. (Continuing...)");
        }
        // *****************************************************************************

        OTBool bWasSentOutbox = new OTBool(false);

        int nGetOutbox = Utility.getOutboxLowLevel(serverID, nymID, accountID, bWasSentOutbox, bForceDownload);

        if (((-1) == nGetOutbox) && !bWasSentOutbox.getBooleanValue()) {
            // if we received an error state, and the "getOutbox" message wasn't even sent,
            // then no point doing a bunch of retries -- it failed.
            //
            System.out.println("Utility.getIntermediaryFiles: Utility.getOutboxLowLevel failed, without even sending getOutbox. (Returning false.)");
            return false;
        } else if (!bWasSentOutbox.getBooleanValue() && // If it wasn't sent, and 0 was returned, that means the
                (0 == nGetOutbox)) // outbox we have is already the latest version.
        {
            return true;
        } else if (1 != nGetOutbox) {
            System.out.println("Utility.getIntermediaryFiles: getOutboxLowLevel failed. (Trying one more time...)");

            // --------------------------------------
            final int nGetRequest = Utility.getRequestNumber(serverID, nymID);

            if (1 != nGetRequest) {
                System.out.println("Utility.getIntermediaryFiles(): Failure: Utility.getOutboxLowLevel failed, then I tried to resync with getRequestNumber and then that failed too. (I give up.)");
                return false;
            }
            // --------------------------------------
            final int nSecondtry = Utility.getOutboxLowLevel(serverID, nymID, accountID, bWasSentOutbox, bForceDownload);

            if (((-1) == nSecondtry) && !bWasSentOutbox.getBooleanValue()) {
                // if we received an error state, and the "getOutbox" message wasn't even sent,
                // then no point doing a bunch of retries -- it failed.
                //
                System.out.println("Utility.getIntermediaryFiles: Utility.getOutboxLowLevel failed a second time, without even sending getOutbox. (Returning false.)");
                return false;
            }
            if (!bWasSentOutbox.getBooleanValue() && // If it wasn't sent, and 0 was returned, that means
                    (0 == nSecondtry)) // no error: we already have the latest outbox. (Nothing done.)
            {
                return true;
            } else if (1 != nSecondtry) {
                System.out.println("Utility.getIntermediaryFiles: getOutboxLowLevel re-try failed. (That's twice now--Returning false.) Value: " + nSecondtry);
                return false;
            }
            System.out.println("Utility.getIntermediaryFiles: getOutboxLowLevel second call succeeded. (Continuing...)");
        }

        return true;
    }

    // Same as the above function, except you only have to pass the accountID.
    // (instead of 3 IDs...)
    //
    public static boolean getInboxOutboxAccount(String accountID) {
        return Utility.getInboxOutboxAccount(accountID, false); //bForceDownload=false
    }

    public static boolean getInboxOutboxAccount(String accountID, boolean bForceDownload) //bForceDownload=false
    {

        if (null == accountID || accountID.length() < 10) {
            System.out.println("getInboxOutboxAccount: invalid accountID: " + accountID);
            return false;
        }
        // ------------------------------------------------------------------------
        String serverID = otapi.OT_API_GetAccountWallet_ServerID(accountID);
        String nymID = otapi.OT_API_GetAccountWallet_NymID(accountID);
        // ------------------------------------------------------------------------

        if (false == Utility.getIntermediaryFiles(serverID, nymID, accountID, bForceDownload)) {
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
    // DONE
    public static int getInboxAccount(String serverID, String nymID, String accountID, OTBool bWasSentInbox, OTBool bWasSentAccount) {
        return Utility.getInboxAccount(serverID, nymID, accountID, bWasSentInbox, bWasSentAccount, false); // bForceDownload = false
    }

    public static int getInboxAccount(String serverID, String nymID, String accountID, OTBool bWasSentInbox, OTBool bWasSentAccount, boolean bForceDownload) //bForceDownload=false
    {
        bWasSentAccount.setBooleanValue(false);
        bWasSentInbox.setBooleanValue(false);

        // ***************************************************
        //
        // (Success means both were downloaded, if necessary.)
        //
        // FIRST WE DO THE ACCOUNT...
        //
        // ***************************************************
        // GET ACCOUNT
        //
        otapi.OT_API_FlushMessageBuffer();

        final int nRequestNum = otapi.OT_API_getAccount(serverID, nymID, accountID); // <===== ATTEMPT TO SEND THE MESSAGE HERE...

        switch (nRequestNum) {
            case (-2):
                System.out.println("Utility.getInboxAccount: ERROR, not supported. (-2 was returned.)");
                return (-1); // -2 is also possible at some future date. (If the request number won't fit in an int, this is returned and then you can retrieve the actual number via a separate call.)
            case (-1):      // if the requestNumber returned by the send-attempt is -1, that means it DIDN'T SEND (error)
                System.out.println("Utility.getInboxAccount: Failed to send getAccount message due to error.");
                return (-1);
            case (0):
                System.out.println("Utility.getInboxAccount: Didn't send getAccount message, but NO error occurred, either. (In this case, SHOULD NEVER HAPPEN. Treating as Error.)");
                return (-1);
            default:
                if (nRequestNum < 0) {
                    System.out.println("Utility.getInboxAccount: Unexpected failure sending getAccount(). Request number: " + nRequestNum);
                    return (-1);
                }
                // -------------------------
                break; // SUCCESS!
        }
        bWasSentAccount.setBooleanValue(true);
        // ***************************************************
        // -1 for error
        //  0 for reply: failure
        //  1 for reply: success
        //
        final int nReturn = Utility.receiveReplySuccessLowLevel(serverID, nymID, nRequestNum, "Utility.getInboxAccount"); // <============ RETURN VALUE
//        System.out.println("IN Utility.getInboxAccount " + Utility.getLastReplyReceived());

        final boolean bAccount = ((1 == nReturn) ? true : false);

        // BY this point, we definitely have the request number, which means the
        // message was actually SENT. (At least.) This also means we can use nRequestNum
        // later to query for a copy of that sent message.
        // Let's go ahead, in this case, and remove that now:
        //
        // ----------------------------------------------
//        final int nRemovedSentMsg = otapi.OT_API_RemoveSentMessage(Integer.toString(nRequestNum), serverID, nymID);
//
//        // NOTE: The above call is unnecessary, since a successful process means
//        // we already received the successful server reply, and OT's "ProcessServerReply"
//        // already removed the sent message from the sent buffer (so no need to do that here.)
//        
//        if (nRemovedSentMsg < 1) // (not success.)
//        {
//            System.out.println("Utility.getInboxAccount: ERROR: OT_API_RemoveSentMessage returned: " + nRemovedSentMsg);
//        }

        if (nReturn < 0) // error
        {
            System.out.println("Utility.getInboxAccount: Error in getAccount: " + nReturn + ".  (I give up.)");
            return (-1);
        }
        // ***************************************************
        if (!bAccount) {
            System.out.println("Utility.getInboxAccount: getAccount failed, returning: " + nReturn);
            return nReturn;
        }
        // --------------------------

        final int nReturn2 = Utility.getInboxLowLevel(serverID, nymID, accountID, bWasSentInbox, bForceDownload);

        if (!bWasSentInbox.getBooleanValue() && // If it wasn't sent, and 0 was returned, that means
                (0 == nReturn2)) // no error: we already have the latest inbox. (Nothing done.)
        {
            return 0;
        }

        if (1 != nReturn2) {
            System.out.println("Utility.getInboxAccount: getInboxLowLevel failed. Returning: " + nReturn2);
        }

        return nReturn2;
    }

    // -1 error
    //  0 Request NOT sent: But NO error, since hash hasn't changed.  (bWasSent=false)
    //  0 Request WAS sent, reply WAS received: success == false.  (bWasSent=true)
    //  1 reply received: success == true.  
    //  bWasSent gets set to TRUE once the message is confirmed as sent.
    //
    public static int getInboxLowLevel(String serverID, String nymID, String accountID, OTBool bWasSent) {
        return Utility.getInboxLowLevel(serverID, nymID, accountID, bWasSent, false); // bForce defaults to FALSE 
    }

    public static int getInboxLowLevel(String serverID, String nymID, String accountID, OTBool bWasSent, boolean bForce) // bForce defaults to FALSE 
    {
        bWasSent.setBooleanValue(false);
        //-------------------------------------------------
        // 
        // Use OT_API_GetAccountWallet_InboxHash(ACCT_ID) to see the server's most recent inbox hash (on the OTAccount for that box)

        final String strRecentHash = otapi.OT_API_GetAccountWallet_InboxHash(accountID);
        final boolean bRecentHash = Utility.isValid(strRecentHash);
        if (!bRecentHash) {
            System.out.println("Utility.getInboxLowLevel: Warning: Unable to retrieve recent cached copy of server-side "
                    + "InboxHash from client-side nym (perhaps he's never downloaded it before?)\n\n");
        }
        //-------------------------------------------------
        // 
        // Use OT_API_GetNym_InboxHash(ACCT_ID, NYM_ID) to see the client's copy of the inbox hash,
        // from whenever the client last actually downloaded the inbox.

        String strLocalHash = otapi.OT_API_GetNym_InboxHash(accountID, nymID);
        boolean bLocalHash = Utility.isValid(strLocalHash);
        if (!bLocalHash) {
            System.out.println("Utility.getInboxLowLevel: Warning: Unable to retrieve client-side InboxHash "
                    + "for:\n accountID: " + accountID + "\n nymID: " + nymID);
        }
        //-------------------------------------------------
        if (!bForce) {
            if (bLocalHash
                    && bRecentHash
                    && strRecentHash.equals(strLocalHash)) // the hashes match -- no need to download anything.
            {
                System.out.println("Utility.getInboxLowLevel: The hashes already match (skipping Inbox download.)");
                return 0;
            }
        }
        // ************************************************************************************

        // Now that we dealt with the Inbox Hash, let's do the download!!
        //
        otapi.OT_API_FlushMessageBuffer();
        // --------------------------------------------------------------------
        final int nRequestNum = otapi.OT_API_getInbox(serverID, nymID, accountID); // <===== ATTEMPT TO SEND THE MESSAGE HERE...

        switch (nRequestNum) {
            case (-2):
                System.out.println("Utility.getInboxLowLevel: ERROR, not supported. (-2 was returned.)");
                return (-1); // -2 is also possible at some future date. (If the request number won't fit in an int, this is returned and then you can retrieve the actual number via a separate call.)
            case (-1):      // if the requestNumber returned by the send-attempt is -1, that means it DIDN'T SEND (error)
                System.out.println("Utility.getInboxLowLevel: Failed to send getInbox message due to error.");
                return (-1);
            case (0):
                System.out.println("Utility.getInboxLowLevel: Didn't send getInbox message, but NO error occurred, either. (In this case, SHOULD NEVER HAPPEN. Treating as Error.)");
                return (-1); // Even though '0' MEANS "didn't send, but no error" by convention in many places, it is actually an impossible return value from getInbox. Therefore I treat it as an error.
            default:
                if (nRequestNum < 0) {
                    System.out.println("Utility.getInboxLowLevel: Unexpected request number: " + nRequestNum);
                    return (-1);
                }
                // -------------------------
                break; // SUCCESS!
        }
        bWasSent.setBooleanValue(true);
        // ***************************************************
        //
        //
        final int nReturn = Utility.receiveReplySuccessLowLevel(serverID, nymID, nRequestNum, "Utility.getInboxLowLevel");
//        System.out.println("IN Utility.getInboxLowLevel " + Utility.getLastReplyReceived());

        final boolean bInbox = ((1 == nReturn) ? true : false);

        // BY this point, we definitely have the request number, which means the
        // message was actually SENT. (At least.) This also means we can use nRequestNum
        // later to query for a copy of that sent message.
        // Let's go ahead, in this case, and remove that now:
        //
        // ----------------------------------------------
//        final int nRemovedSentMsg = otapi.OT_API_RemoveSentMessage(Integer.toString(nRequestNum), serverID, nymID);
//
//        // NOTE: The above call is unnecessary, since a successful process means
//        // we already received the successful server reply, and OT's "ProcessServerReply"
//        // already removed the sent message from the sent buffer (so no need to do that here.)
//        
//        if (nRemovedSentMsg < 1)
//        {
//            System.out.println("Utility.getInboxLowLevel: ERROR: OT_API_RemoveSentMessage returned: " + nRemovedSentMsg);
//        }
        // ----------------------------------------------

        // ***************************************************
        // Now let's make sure we have all the box receipts for this outbox.
        // (They will be needed when it is used for something.)
        //
        if (bInbox && !Utility.insureHaveAllBoxReceipts(serverID, nymID, accountID, 1)) // <===== nBoxType = 1 aka INBOX
        {
            System.out.println("Utility.getInboxLowLevel: getInbox succeeded, but then insureHaveAllBoxReceipts failed. (I give up.)");
            return (-1);
        }
        // ***************************************************

        return nReturn;
    }

    // ************************************************************************
    // -1 error
    //  0 Request NOT sent: But NO error, since hash hasn't changed.  (bWasSent=false)
    //  0 Request WAS sent, reply WAS received: success == false.  (bWasSent=true)
    //  1 reply received: success == true.  
    //  bWasSent gets set to TRUE once the message is confirmed as sent.
    //
    public static int getOutboxLowLevel(String serverID, String nymID, String accountID, OTBool bWasSent) {
        return Utility.getOutboxLowLevel(serverID, nymID, accountID, bWasSent, false); // bForce defaults to FALSE 
    }

    public static int getOutboxLowLevel(String serverID, String nymID, String accountID, OTBool bWasSent, boolean bForce) // bForce defaults to FALSE 
    {
        bWasSent.setBooleanValue(false);

        //-------------------------------------------------
        // 
        // Use OT_API_GetAccountWallet_OutboxHash(ACCT_ID) to see the server's most recent outbox hash (on the OTAccount for that box)

        final String strRecentHash = otapi.OT_API_GetAccountWallet_OutboxHash(accountID);
        final boolean bRecentHash = Utility.isValid(strRecentHash);
        if (!bRecentHash) {
            System.out.println("Utility.getOutboxLowLevel: Warning: Unable to retrieve recent cached copy of server-side "
                    + "OutboxHash from client-side nym (perhaps he's never downloaded it before?)\n\n");
        }
        //-------------------------------------------------
        // 
        // Use OT_API_GetNym_OutboxHash(ACCT_ID, NYM_ID) to see the client's copy of the outbox hash,
        // from whenever the client last actually downloaded the outbox.

        String strLocalHash = otapi.OT_API_GetNym_OutboxHash(accountID, nymID);
        boolean bLocalHash = Utility.isValid(strLocalHash);
        if (!bLocalHash) {
            System.out.println("Utility.getOutboxLowLevel: Warning: Unable to retrieve client-side OutboxHash "
                    + "for:\n accountID: " + accountID + "\n nymID: " + nymID);
        }
        //-------------------------------------------------
        if (!bForce) {
            if (bLocalHash
                    && bRecentHash
                    && strRecentHash.equals(strLocalHash)) // the hashes match -- no need to download anything.
            {
                System.out.println("Utility.getOutboxLowLevel: The hashes already match (skipping Outbox download.)");
                return 0;
            }
        }
        // ************************************************************************************

        // Now that we dealt with the Outbox Hash, let's do the download!!
        //
        otapi.OT_API_FlushMessageBuffer();
        // --------------------------------------------------------------------
        final int nRequestNum = otapi.OT_API_getOutbox(serverID, nymID, accountID); // <===== ATTEMPT TO SEND THE MESSAGE HERE...

        switch (nRequestNum) {
            case (-2):
                System.out.println("Utility.getOutboxLowLevel: ERROR, not supported. (-2 was returned.)");
                return (-1); // -2 is also possible at some future date. (If the request number won't fit in an int, this is returned and then you can retrieve the actual number via a separate call.)
            case (-1):      // if the requestNumber returned by the send-attempt is -1, that means it DIDN'T SEND (error)
                System.out.println("Utility.getOutboxLowLevel: Failed to send getOutbox message due to error.");
                return (-1);
            case (0):
                System.out.println("Utility.getOutboxLowLevel: Didn't send getOutbox message, but NO error occurred, either. (In this case, SHOULD NEVER HAPPEN. Treating as Error.)");
                return (-1); // Even though '0' MEANS "didn't send, but no error" by convention in many places, it is actually an impossible return value from getOutbox. Therefore I treat it as an error.
            default:
                if (nRequestNum < 0) {
                    System.out.println("Utility.getOutboxLowLevel: Unexpected request number: " + nRequestNum);
                    return (-1);
                }
                // -------------------------
                break; // SUCCESS!
        }
        bWasSent.setBooleanValue(true);
        // ***************************************************
        //
        //
        final int nReturn = Utility.receiveReplySuccessLowLevel(serverID, nymID, nRequestNum, "Utility.getOutboxLowLevel");
//        System.out.println("IN Utility.getOutboxLowLevel " + Utility.getLastReplyReceived());

        final boolean bOutbox = ((1 == nReturn) ? true : false);

        // BY this point, we definitely have the request number, which means the
        // message was actually SENT. (At least.) This also means we can use nRequestNum
        // later to query for a copy of that sent message.
        // Let's go ahead, in this case, and remove that now:
        //
        // ----------------------------------------------
//        final int nRemovedSentMsg = otapi.OT_API_RemoveSentMessage(Integer.toString(nRequestNum), serverID, nymID);
//
//        // NOTE: The above call is unnecessary, since a successful process means
//        // we already received the successful server reply, and OT's "ProcessServerReply"
//        // already removed the sent message from the sent buffer (so no need to do that here.)
//        
//        if (nRemovedSentMsg < 1)
//        {
//            System.out.println("Utility.getOutboxLowLevel: ERROR: OT_API_RemoveSentMessage returned: " + nRemovedSentMsg);
//        }
        // ----------------------------------------------

        // ***************************************************
        // Now let's make sure we have all the box receipts for this outbox.
        // (They will be needed when it is used for something.)
        //
        if (bOutbox && !Utility.insureHaveAllBoxReceipts(serverID, nymID, accountID, 2)) // <===== nBoxType = 2 aka OUTBOX
        {
            System.out.println("Utility.getOutboxLowLevel: getOutbox succeeded, but then insureHaveAllBoxReceipts failed. (I give up.)");
            return (-1);
        }
        // ***************************************************

        return nReturn;
    }

    public static boolean saveImagePath(String imagePath) {

        boolean status = false;

        if (LoadState.isThisStageComplete(LoadState.Stages.InitOTAPI)) {
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
        } else {
            System.out.println("Utility.getImagePath():  Skipping. (OT not initialized yet.)");
        }
        return status;
    }

    public static String getImagePath() {

        String strDefault = null;
        String strImagePath = strDefault; // Todo: hardcoding

        if (LoadState.isThisStageComplete(LoadState.Stages.InitOTAPI)) {
            Storable storable = null;
            StringMap stringMap = null;

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
            } else {
                System.out.println("Utility.getImagePath():  File does not exist: (OT_MAIN_PATH)/moneychanger/settings.dat");
            }

            if ((null == strImagePath) || (strImagePath.length() < 1)) {
                strImagePath = strDefault;
            }
        } else {
            System.out.println("Utility.getImagePath():  Skipping. (OT not initialized yet.)");
        }
        return strImagePath;
    }

    public interface ReturnAction {

        public String getAction();

        public void returnAction(String returnValue);
    }
}
