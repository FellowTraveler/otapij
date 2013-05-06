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
package com.moneychanger.app;

import com.moneychanger.core.util.ConfigBean;
import com.moneychanger.core.util.JavaCallback;
import com.moneychanger.ui.ProgressBar;
import com.moneychanger.ui.dialogs.GetJavaPath;
import com.moneychanger.ui.dialogs.GetPasswordImageDialog;
import java.awt.Dialog;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.SingleFrameApplication;
import org.opentransactions.otapi.OTCallback;
import org.opentransactions.otapi.OTCaller;
import org.opentransactions.otapi.otapiJNI;
import org.opentransactions.otjavalib.Load;
import org.opentransactions.otjavalib.Load.IJavaPath;
import org.opentransactions.otjavalib.Load.IPasswordImage;
import org.opentransactions.otjavalib.Load.LoadingOpenTransactionsFailure;

/**
 *
 * @author Cameron Garnham
 */
public class Moneychanger extends SingleFrameApplication {

    static ConfigBean configBean = new ConfigBean();  // Our Configuration!
    static private IJavaPath javaPath = null;
    static private IPasswordImage passwordImageMgmt = null;
    static private OTCaller javaPasswordCaller = null;
    static private OTCallback javaPasswordCallback = null;
    

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        try {



            final Runnable doMoneychanger = new Runnable() {
                @Override
                public void run() {
                    new ProgressBar().setVisible(true);
                }
            };

            Thread appThread = new Thread() {
                @Override
                public void run() {
                    try {
                        SwingUtilities.invokeAndWait(doMoneychanger);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Finished on " + Thread.currentThread());
                }
            };

            appThread.setDaemon(true);
            appThread.start();


        } catch (Exception ex) {
            Logger.getLogger(Moneychanger.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     *
     * @return the instance of ApplicationLauncher
     */
    public static Moneychanger getApplication() {
        return Moneychanger.getInstance(Moneychanger.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        
        final Logger l = Logger.getLogger(Load.class.getName());

        Thread cleanupThread = new Thread(
    			new Runnable() {
                            @Override
    				public void run() {
    					System.out.println( "Shutdown hook ran." );

                                        otapiJNI.OTAPI_Basic_AppShutdown();
    				}	
    			}
    		);

        Runtime.getRuntime().addShutdownHook(cleanupThread);
        
        int retVal = Loader.Load();
        
        if (0 < retVal) // success (run Moneychanger)
        {
            launch(Moneychanger.class, args);  
        }
        
        if (0 == retVal) // cancel (just quit, no need to cleanup)
        {
        l.log(Level.WARNING, "Failed to Load. Will not atempt to run Moneychanger!");
            Runtime.getRuntime().removeShutdownHook(cleanupThread);
            System.exit(0);
        }
        
        if (0 > retVal) // error (quit and cleanup)
        {
            l.log(Level.SEVERE, "Failed to Load. Will not atempt to run Moneychanger!");
            System.exit(0);
        }
    }

    public static ConfigBean GetConfigBean()
    {
        return configBean;
    }
    
    static class Loader {

        static int Load() {
            try {
                javaPath = new JavaPathMgmt(configBean);
                if (!Load.It().InitNative(javaPath, "")) {
                    return 0; // user cancelled
                }

                if (Load.It().Init()) {

                    passwordImageMgmt = new PasswordImageMgmt();
                    if (Load.It().SetupPasswordImage(passwordImageMgmt)) {

                        javaPasswordCaller = new OTCaller();
                        javaPasswordCallback = new JavaCallback();
                        if (Load.It().SetupPasswordCallback(javaPasswordCaller, javaPasswordCallback)) {
                            if (Load.It().LoadWallet()) {
                                return 1; // all good
                            }
                        }
                    }
                }

                if (Load.It().GetInitialized()) {
                    return -1;  // we need to do some cleanup!
                }
                return 0;

            } catch (LoadingOpenTransactionsFailure ex) {
                Logger.getLogger(Moneychanger.class.getName()).log(Level.SEVERE, null, ex);
                
                if (Load.It().GetInitialized()) {
                    return -2;
                }
                else {
                    return 0;
                }
            }
        }
    }

    static class PasswordImageMgmt implements IPasswordImage {

        @Override
        public String GetPasswordImageFromUser(String string) {
            JDialog j = new GetPasswordImageDialog();
            j.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            j.setAlwaysOnTop(true);
            j.setVisible(true);
            return GetPasswordImageDialog.GetPasswordImagePath();
        }

        @Override
        public boolean SetPasswordImage(String string) {
            ConfigBean.Static.setKey(ConfigBean.Static.Keys.PasswordImagePath, string);
            return true;
        }

        @Override
        public Boolean GetIfUserCancelled() {
            return GetPasswordImageDialog.GetIfUserHasCancelled();
        }
    }

    static class JavaPathMgmt implements IJavaPath {

        private ConfigBean configBean = null;

        public JavaPathMgmt(ConfigBean configBean) {
            this.configBean = configBean;
        }
        static private boolean bHaveTriedConfigPath = false;

        @Override
        public String GetJavaPathFromUser(String message) {

            if (!bHaveTriedConfigPath) {
                bHaveTriedConfigPath = true;

                String pathFromConfig = configBean.getConfig(ConfigBean.Keys.JavaPath);
                if (!pathFromConfig.isEmpty()) {
                    return pathFromConfig;
                }
            }

            JDialog j = new GetJavaPath();
            
            GetJavaPath.SetDisplayMessage(message);
            
            j.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            j.setAlwaysOnTop(true);
            j.setVisible(true);
            
            String pathFromUser = GetJavaPath.GetJavaPath();
            configBean.setConfig(ConfigBean.Keys.JavaPath, pathFromUser);
            return pathFromUser;
        }

        @Override
        public Boolean GetIfUserCancelled() {
            return GetJavaPath.GetIfUserHasCancelled();
        }
    }
}
