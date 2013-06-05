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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opentransactions.otjavalib;

import java.io.File;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opentransactions.otapi.OTAPI_Basic;
import org.opentransactions.otapi.OTCallback;
import org.opentransactions.otapi.OTCaller;
import org.opentransactions.otapi.Storable;
import org.opentransactions.otapi.StoredObjectType;
import org.opentransactions.otapi.StringMap;
import org.opentransactions.otapi.otapi;
import org.opentransactions.otapi.otapiJNI;
import org.opentransactions.otjavalib.Load.LoadingOpenTransactionsFailure.LoadErrorType;

/**
 *
 * @author Cameron Garnham
 */
public class Load {

    private static Load ptrThis = null;

    public synchronized static Load It() {
        if (null == ptrThis) {
            ptrThis = new Load();
        }
        return ptrThis;
    }
    private boolean isNativeLoaded = false;
    private boolean isInitialized = false;
    private boolean isPasswordImageSet = false;
    private boolean isPasswordCallbackSet = false;
    private boolean isWalletLoaded = false;

    public interface IJavaPath {
        Boolean GetIfUserCancelled();
        String  GetJavaPathFromUser(String message);
    }
    
    public interface IPasswordImage {
        Boolean GetIfUserCancelled();
        String GetPasswordImageFromUser(String message);
        boolean SetPasswordImage(String path);
    }
    

    // The code here will only ever happen once per instance
    private Load() {
    }

    public boolean GetIsLoaded() {
        return isWalletLoaded;
    }
    
    public boolean GetInitialized() {
        return isInitialized;
    }

    public boolean InitNative(IJavaPath javaPathCallback, String optionalPath) throws LoadingOpenTransactionsFailure {
        final Logger l = Logger.getLogger(Load.class.getName());

        String extra_path = optionalPath.isEmpty() ? "" : optionalPath;

        if (isNativeLoaded) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.NATIVE_ALREADY_LOADED, "Native Already Loaded!");
        }

        boolean bFirstAttempt = true;
        boolean bLoadSuccess = false;

        
        for (;;) {

            try {
                Collection<String> pathsSet = null;

                if (extra_path.isEmpty()) {
                    pathsSet = Tools.appendPathToRuntime(Tools.getDefaultLibPath(Tools.getOS()));
                } else {
                    pathsSet = Tools.appendPathToRuntime(extra_path);
                }

                l.log(Level.FINE, pathsSet.toString());

            } catch (IllegalAccessException ex) {
                l.log(Level.SEVERE, "Unable To Access Java Paths", ex);
                System.exit(-1); // bad excetion
            } catch (NoSuchFieldException ex) {
                l.log(Level.SEVERE, "Unable to Set Feild", ex);
                System.exit(-1); // bad excetion
            }

            try {
                Tools.loadNative(Tools.getOS());
            } catch (UnsatisfiedLinkError ex) {

                if (bFirstAttempt) {
                    l.log(Level.FINE, "This error can be safely ignored", ex);
                    bFirstAttempt = false;
                }
                else {
                    l.log(Level.SEVERE, "Failed to Load Second Attempt! (Bad)", ex);
                }
                    
                extra_path = javaPathCallback.GetJavaPathFromUser(Tools.getJavaPathUserMessage(Tools.getOS()));
                
                if (javaPathCallback.GetIfUserCancelled()) {
                    break;
                }
                
                continue;
            }
            bLoadSuccess = true;
            break; //success
        }
        if (bLoadSuccess)
        {
        isNativeLoaded = true;
        return true;
        }
        else
        {
            return false;
        }
    }

    public boolean Init() throws LoadingOpenTransactionsFailure {
        final Logger l = Logger.getLogger(Load.class.getName());

        if (!isNativeLoaded) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.NATIVE_NOT_LOADED, "Native Libs Not Loaded!");
        }
        if (isInitialized) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.OTAPI_ALREADY_INSTIGATED, "Is Already Initialized");
        }

        // --------------------------------------------
        // This has internal logic so that it only actually is called once.
        // Probably not necessary anymore since I moved the call here.
        // The problem was, this call was happening too early (it could
        // happen even if otapi init failed) and then it wouldn't allow
        // itself to trigger again.  This I think should fix that.
        //
        // --------------------------------------------

        boolean bSuccess = false;

        if (otapiJNI.OTAPI_Basic_AppStartup()) // Call once at startup. Sets up OpenSSL, signal handlers, etc.
        {
            bSuccess = otapiJNI.OTAPI_Basic_Init(); // Initialize OTAPI context. Loads config file, etc.
        }
        // -------------------------------------------------
        if (bSuccess) {
            System.out.println("Load.initOTAPI: SUCCESS invoking OTAPI_Basic_AppStartup and OTAPI_Basic_Init.");
        } else // Failed in OTAPI_Basic_AppStartup or OTAPI_Basic_Init.
        {
            String strErrorMsg = "Load.initOTAPI: Failed calling OTAPI_Basic_AppStartup or OTAPI_Basic_Init.";
            throw new LoadingOpenTransactionsFailure(LoadErrorType.OTAPI_FAILED_TO_INSTIGATE, strErrorMsg);
        }

        isInitialized = true;
        return true;
    }

    public boolean SetupPasswordImage(IPasswordImage passwordImage) throws LoadingOpenTransactionsFailure {

        if (!isInitialized) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.OTAPI_NOT_INSTIGATED, "Is Not Initialized");
        }
        if (isPasswordImageSet) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.PASSWORD_IMAGE_ALREADY_SET, "Password Image Already Set!");
        }

        String imagePath = "";
        boolean bHaveImage = false;

        // First Lets Check if we already have a password image. (we don't need annother one)
        if (otapi.Exists("moneychanger", "settings.dat")) {

            Storable storable = null;
            StringMap stringMap = null;

            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_STRING_MAP, "moneychanger", "settings.dat");

            if (null != storable) {

                stringMap = StringMap.ot_dynamic_cast(storable);
                imagePath = stringMap.GetValue("ImagePath");

                File f = new File(imagePath);
                if (f.exists()) {
                    // Good we have a password Image
                    bHaveImage = true;
                }
            }
        }

        // We don't have an image... lets get it from the user, then save it.
        if (!bHaveImage) {

            for (;;) {
                imagePath = passwordImage.GetPasswordImageFromUser("passwordImage");
                
                if (passwordImage.GetIfUserCancelled()) {
                    bHaveImage = false;
                    break;
                }

                File f = new File(imagePath);
                if (f.exists()) {
                    bHaveImage = true;
                    // Good we have a password Image
                    break;
                }
            }
            
            if (!bHaveImage)
            {
                return false;
            }

            StringMap stringMap = null;  // we are about to create this object
            Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_STRING_MAP);

            if (storable != null) {
                stringMap = StringMap.ot_dynamic_cast(storable);
                System.out.println("stringMap:" + stringMap);

                if (stringMap != null) {
                    //stringMap.SetValue("ImagePath", "~/.ot/default.gif");
                    stringMap.SetValue("ImagePath", imagePath);
                    bHaveImage = otapi.StoreObject(stringMap, "moneychanger", "settings.dat");
                }
            }
        }

        if (bHaveImage) {
            passwordImage.SetPasswordImage(imagePath);
        } else {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.PASSWORD_IMAGE_FAILED_TO_SET, "Password image not Set!");
        }
        isPasswordImageSet = true;
        return true;
    }

    public boolean SetupPasswordCallback(OTCaller passwordCaller, OTCallback passwordCallback) throws LoadingOpenTransactionsFailure {

        if (!isPasswordImageSet) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.PASSWORD_IMAGE_NOT_SET, "Must Set Password Image First!");
        }
        if (isPasswordCallbackSet) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.PASSWORD_CALLBACK_ALREADY_SET, "Already Have Set Password Callback!");
        }

        if (null == passwordCallback) {
            throw new IllegalArgumentException("passwordCallback is null");
        }

        try {
            passwordCaller.setCallback(passwordCallback);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new LoadingOpenTransactionsFailure(LoadErrorType.PASSWORD_CALLBACK_FAILED_TO_SET, "Unable to Set Password Callback");
        }
        
        Boolean bSuccess = otapi.OT_API_Set_PasswordCallback(passwordCaller);
        if (!bSuccess)
        {
            passwordCaller = null;
            passwordCallback = null;
            throw new LoadingOpenTransactionsFailure(LoadErrorType.PASSWORD_CALLBACK_FAILED_TO_SET, "Unable to Set Password Callback");
        }
        
        isPasswordCallbackSet = true;
        return true;
    }

    public boolean LoadWallet() throws LoadingOpenTransactionsFailure {

        if (!isPasswordCallbackSet) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.PASSWORD_CALLBACK_NOT_SET, "Must Set Password Callback First!");
        }
        if (isWalletLoaded) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.WALLET_ALREADY_LOADED, "Already Loaded!");
        }

        if (!OTAPI_Basic.LoadWallet()) {
            throw new LoadingOpenTransactionsFailure(LoadErrorType.WALLET_UNABLE_TO_LOAD, "Unable to Load Wallet");
        }

        isWalletLoaded = true;
        return true;
    }

    static public class LoadingOpenTransactionsFailure extends Exception {

        private final String _message;
        private final LoadErrorType _errorType;

        public LoadingOpenTransactionsFailure(final LoadErrorType errorType, final String message) {
            this._errorType = errorType;
            this._message = message;
        }

        public final LoadErrorType getErrorType() {
            return this._errorType;
        }
        
        public final String getError() {
            return this._message;
        }
        
        public enum LoadErrorType {
            NATIVE_ALREADY_LOADED,
            NATIVE_NOT_LOADED,
            OTAPI_ALREADY_INSTIGATED,
            OTAPI_NOT_INSTIGATED,
            OTAPI_FAILED_TO_INSTIGATE,
            PASSWORD_IMAGE_ALREADY_SET,
            PASSWORD_IMAGE_NOT_SET,
            PASSWORD_IMAGE_FAILED_TO_SET,
            PASSWORD_CALLBACK_ALREADY_SET,
            PASSWORD_CALLBACK_NOT_SET,
            PASSWORD_CALLBACK_FAILED_TO_SET,
            WALLET_ALREADY_LOADED,
            WALLET_UNABLE_TO_LOAD,
            OTHER
        }
    }
}
