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

/*
 * ExchangeBasketDialog.java
 *
 * Created on Aug 31, 2011, 3:19:48 PM
 */
package com.moneychanger.ui.dialogs;


import com.moneychanger.core.OpenTransactionAccount;
import com.moneychanger.core.util.Helpers;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.opentransactions.otapi.otapiJNI;

/**
 *
 * @author administrator
 */
public class ExchangeBasketDialog extends javax.swing.JDialog {

    private final String basket;
    private final boolean inXchange;
    private final String serverID;
    private final String assetID;
    private final String nymID;
    private final String accountID;

    /** Creates new form ExchangeBasketDialog */
    public ExchangeBasketDialog(java.awt.Frame parent, boolean modal, String basket, boolean inXchange, String serverID, String nymID, String accountID, String assetID) {
        super(parent, modal);
        initComponents();
        this.basket = basket;
        this.inXchange = inXchange;
        this.serverID = serverID;
        this.assetID = assetID;
        this.nymID = nymID;
        this.accountID = accountID;
        jLabel1.setText(basket);
        jLabel1.setToolTipText(basket);
        this.setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.moneychanger.app.Moneychanger.class).getContext().getResourceMap(ExchangeBasketDialog.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setDisabledTextColor(resourceMap.getColor("jTextField1.disabledTextColor")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1)
                    .add(jButton2))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(32, 32, 32)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 324, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jButton3)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jButton3)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int existingValue = Integer.valueOf(jTextField1.getText());
        jTextField1.setText(String.valueOf(existingValue + 1));
        setNewValue(existingValue + 1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int existingValue = Integer.valueOf(jTextField1.getText());
        if (existingValue == 1) {
            JOptionPane.showMessageDialog(null, "Minimum value should be 1", "Min Bound Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        jTextField1.setText(String.valueOf(existingValue - 1));
        setNewValue(existingValue - 1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            doExchangeBasket();
        } catch (InterruptedException ex) {
            Logger.getLogger(ExchangeBasketDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ExchangeBasketDialog dialog = new ExchangeBasketDialog(new javax.swing.JFrame(), true, "", true, "", "", "", "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private static javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private void setNewValue(int newValue) {
        String newLabel = "";
        String[] basketSplitted = basket.split("==");
        String[] basketBase = basketSplitted[0].split(" ");
        int basketMultiple = Integer.parseInt(basketBase[0]);
        newLabel = String.valueOf(basketMultiple * (newValue)) + " " + basketBase[1];
        newLabel += " == ";
        String basketMemberSplitted[] = basketSplitted[1].split(",");
        for (int i = 0; i < basketMemberSplitted.length; i++) {
            String[] eachMember = basketMemberSplitted[i].split(" ");
            newLabel += String.valueOf(Integer.parseInt(eachMember[1]) * (newValue)) + " " + eachMember[2];
            if (i != basketMemberSplitted.length - 1) {
                newLabel += ", ";
            }
        }
        jLabel1.setText(newLabel);
        jLabel1.setToolTipText(newLabel);
    }

    private void doExchangeBasket() throws InterruptedException {

        int memberCount = otapiJNI.OTAPI_Basic_Basket_GetMemberCount(assetID);
        boolean bSure = true;
         if (otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(serverID, nymID) < (2+memberCount)) {
            bSure = Helpers.getTransactionNumbers(serverID, nymID);
        }

        if (!bSure || otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(serverID, nymID) < (2+memberCount)) {
            System.out.println("IN doExchangeBasket , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(serverID, nymID));
            return;
        }

        OpenTransactionAccount otAccount = new OpenTransactionAccount();
        String exchangeRequest = otAccount.getExchangeBasketRequest(serverID, nymID, assetID, accountID, Integer.parseInt(jTextField1.getText()));

        
        for (int i = 0; i < memberCount; i++) {
            String memberAssetID = otapiJNI.OTAPI_Basic_Basket_GetMemberType(assetID, i);

            System.out.println("In doExchangeBasket,memberAssetID:" + memberAssetID + " assetID:" + assetID + " nymID:" + nymID + " serverID:" + serverID);

            new ExchangeBasketAcctDialog(null, true, memberAssetID, serverID, nymID).setVisible(true);
            if (Helpers.isBasketXCancelled()) {
                Helpers.setBasketXCancelled(false);
                JOptionPane.showMessageDialog(null, "The basket exchange has been cancelled", "Basket Exchange Canellation", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String memberAccountID = Helpers.getBasketXAcct();
            exchangeRequest = otapiJNI.OTAPI_Basic_AddBasketExchangeItem(serverID, nymID, exchangeRequest, memberAssetID, memberAccountID);

        }
        String xchangeType = inXchange == true ? "IN" : "OUT";
        if (!otAccount.exchangeBasket(assetID, serverID, nymID, exchangeRequest, inXchange, memberCount, accountID)) {
            JOptionPane.showMessageDialog(null, "Exchange basket failed", "Server Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, xchangeType + " Exchange of basket currency done successfully", "Basket Exchange Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
