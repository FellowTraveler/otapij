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


package com.moneychanger.ui;

import com.moneychanger.core.util.Helpers;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ProgressBar
        extends JFrame implements Runnable {

    private static JProgressBar progress;
    private JLabel label1;
    private JPanel topPanel;
    public static boolean stop = false;
    private static int MAX = 100;
    private static int SLEEP = 1000;
    private static int counter = 1;

    public ProgressBar() {
        /*try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        UIManager.setLookAndFeel("javax.swing.MultiUIDefaults");
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
        Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
        Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
        Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
//        ((JFrame) Utility.getSettingsObj()).dispose();


        setResizable(false);

        setAlwaysOnTop(true);

        setTitle("Loading in progress");
        setSize(340, 80);

        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(340, 80));
        getContentPane().add(topPanel);

        // Create a label and progress bar
        label1 = new JLabel("Moneychanger is loading wallet. Please wait...");
        label1.setPreferredSize(new Dimension(300, 24));
        topPanel.add(label1);

        progress = new JProgressBar();
        progress.setPreferredSize(new Dimension(300, 20));
        progress.setMinimum(0);
        progress.setMaximum(MAX);

        progress.setBounds(20, 35, 260, 20);
        topPanel.add(progress);

        progress.setValue(0);
        final Rectangle progressRect = progress.getBounds();
        progressRect.x = 0;
        progressRect.y = 0;
        progress.setIndeterminate(true);


        repaint();

        this.setLocationRelativeTo(null);


        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    //progress.paintImmediately(progressRect);
                    repaint();
                    MainPage mainPage = new MainPage();
                    stop = true;
                    completeAndClose();
                    mainPage.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(-1);
                }
            }
        });


    }

    public void completeAndClose() {
        progress.setValue(MAX);
        Rectangle progressRect = progress.getBounds();
        progressRect.x = 0;
        progressRect.y = 0;
        progress.paintImmediately(progressRect);
        progress.setIndeterminate(false);

    }

    public static void main(String args[]) {
        // Create an instance of the test application
        ProgressBar mainFrame = new ProgressBar();
        //new Thread(mainFrame).start();

        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    @Override
    public void run() {

        System.out.println("In run");

        try {
            do {
                progress.setValue(counter);
                Rectangle progressRect = progress.getBounds();
                progressRect.x = 0;
                progressRect.y = 0;
                progress.paintImmediately(progressRect);
                //counter++;
                /*{
                Thread.sleep(SLEEP);
                } catch (Exception ex) {
                Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                }*/


            } while (!stop);
        } catch (Exception ex) {
            Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        /* java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        (new BackgroundTask()).execute();
        }
        } );*/


    }

    class BackgroundTask extends SwingWorker<String, Object> {

        @Override
        public String doInBackground() {
            someTimeConsumingMethod();
            return null;
        }

        @Override
        protected void done() {
            System.out.println("Done");
        }

        private void someTimeConsumingMethod() {
        }
    }
}
