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
 * OpenTransactionAccountBottomPanel.java
 *
 * Created on 18 Mar, 2011, 8:55:00 AM
 */
package com.moneychanger.ui.panels;

import com.moneychanger.core.OpenTransactionAccount;
import com.moneychanger.core.dataobjects.OTDetails;
import com.moneychanger.core.util.Helpers;
import com.moneychanger.ui.MainPage;
import com.moneychanger.ui.dialogs.OTNotes;
import com.moneychanger.ui.model.OTInboxTableModel;
import com.moneychanger.ui.model.OTOutboxTableModel;
import com.moneychanger.ui.model.OTReceiptTableModel;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import org.jdesktop.application.Action;
import org.opentransactions.otjavalib.util.Utility;

/**
 *
 * @author Vicky C
 */
public class OpenTransactionAccountBottomPanel extends javax.swing.JPanel {

    private static Map inbox;
    private static Map outbox;
    private static String accountID;

    /** Creates new form OpenTransactionAccountBottomPanel */
    public OpenTransactionAccountBottomPanel() {
        initComponents();
        setCustomProperties();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel_Inbox = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jScrollPane_Inbox = new javax.swing.JScrollPane();
        jTable_Inbox = new com.moneychanger.ui.custom.CustomTable();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jButton_ProcessInbox = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel_Outbox = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jScrollPane_Outbox = new javax.swing.JScrollPane();
        jTable_Outbox = new com.moneychanger.ui.custom.CustomTable();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel_Receipts = new javax.swing.JPanel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jScrollPane_Recepts = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jButton_OK = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));

        setName("Form"); // NOI18N
        setLayout(new java.awt.GridBagLayout());

        filler9.setName("filler9"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(filler9, gridBagConstraints);

        filler11.setName("filler11"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(filler11, gridBagConstraints);

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(200, 200));

        jPanel_Inbox.setName("jPanel_Inbox"); // NOI18N
        jPanel_Inbox.setLayout(new java.awt.GridBagLayout());

        filler1.setName("filler1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.weighty = 1.0;
        jPanel_Inbox.add(filler1, gridBagConstraints);

        jScrollPane_Inbox.setMinimumSize(new java.awt.Dimension(200, 100));
        jScrollPane_Inbox.setName("jScrollPane_Inbox"); // NOI18N

        jTable_Inbox.setModel(new com.moneychanger.ui.model.OTInboxTableModel());
        jTable_Inbox.setName("jTable_Inbox"); // NOI18N
        jTable_Inbox.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_Inbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_InboxMouseClicked(evt);
            }
        });
        jScrollPane_Inbox.setViewportView(jTable_Inbox);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Inbox.add(jScrollPane_Inbox, gridBagConstraints);

        filler2.setName("filler2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.weighty = 1.0;
        jPanel_Inbox.add(filler2, gridBagConstraints);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(OpenTransactionAccountBottomPanel.class, this);
        jButton_ProcessInbox.setAction(actionMap.get("ProcessInbox")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(OpenTransactionAccountBottomPanel.class);
        jButton_ProcessInbox.setText(resourceMap.getString("jButton_ProcessInbox.text")); // NOI18N
        jButton_ProcessInbox.setName("jButton_ProcessInbox"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Inbox.add(jButton_ProcessInbox, gridBagConstraints);

        filler4.setName("filler4"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.weighty = 1.0;
        jPanel_Inbox.add(filler4, gridBagConstraints);

        jTabbedPane1.addTab("INBOX", jPanel_Inbox);

        jPanel_Outbox.setName("jPanel_Outbox"); // NOI18N
        jPanel_Outbox.setLayout(new java.awt.GridBagLayout());

        filler3.setName("filler3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel_Outbox.add(filler3, gridBagConstraints);

        jScrollPane_Outbox.setName("jScrollPane_Outbox"); // NOI18N

        jTable_Outbox.setModel(new com.moneychanger.ui.model.OTOutboxTableModel());
        jTable_Outbox.setName("jTable_Outbox"); // NOI18N
        jTable_Outbox.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_Outbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_OutboxMouseClicked(evt);
            }
        });
        jScrollPane_Outbox.setViewportView(jTable_Outbox);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Outbox.add(jScrollPane_Outbox, gridBagConstraints);

        filler5.setName("filler5"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel_Outbox.add(filler5, gridBagConstraints);

        jTabbedPane1.addTab("OUTBOX", jPanel_Outbox);

        jPanel_Receipts.setName("jPanel_Receipts");
        jPanel_Receipts.setLayout(new java.awt.GridBagLayout());

        filler6.setName("filler6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel_Receipts.add(filler6, gridBagConstraints);

        jScrollPane_Recepts.setName("jScrollPane_Recepts"); // NOI18N

        jTable8.setModel(new com.moneychanger.ui.model.OTReceiptTableModel());
        jTable8.setName("jTable8");
        jTable8.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane_Recepts.setViewportView(jTable8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Receipts.add(jScrollPane_Recepts, gridBagConstraints);

        filler7.setName("filler7"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel_Receipts.add(filler7, gridBagConstraints);

        jButton_OK.setText(resourceMap.getString("jButton_OK.text")); // NOI18N
        jButton_OK.setName("jButton_OK"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_Receipts.add(jButton_OK, gridBagConstraints);

        filler8.setName("filler8"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel_Receipts.add(filler8, gridBagConstraints);

        jTabbedPane1.addTab("RECEIPTS", jPanel_Receipts);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 50.0;
        gridBagConstraints.weighty = 50.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jTabbedPane1, gridBagConstraints);

        filler12.setName("filler12"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(filler12, gridBagConstraints);

        filler10.setName("filler10"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(filler10, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_OutboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_OutboxMouseClicked
        System.out.println("Count:" + evt.getClickCount());
        if (evt.getClickCount() == 2) {
            String key = (String) jTable_Outbox.getModel().getValueAt(jTable_Outbox.getSelectedRow(), 7);
            System.out.println("In outbox double clcik, key:" + key);
            String[] row = (String[]) outbox.get(key);
            if (row != null && "pending".equalsIgnoreCase(row[3])) {
                System.out.println("In outbox double clcik, key:aaaaaaaaaa" + key);
                OTNotes otNotes = new OTNotes(null, true, row[10]);
                otNotes.setVisible(true);
            }
        }
}//GEN-LAST:event_jTable_OutboxMouseClicked

    private void jTable_InboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_InboxMouseClicked
        System.out.println("Count:" + evt.getClickCount());
        if (evt.getClickCount() == 2) {
            String key = (String) jTable_Inbox.getModel().getValueAt(jTable_Inbox.getSelectedRow(), 9);
            if (!Utility.VerifyStringVal(key)) key = new String("");
            System.out.println("In outbox double clcik, key:" + key);
            String[] row = (String[]) inbox.get(key);
            if (row != null && "pending".equalsIgnoreCase(row[3])) {
                System.out.println("In inbox double clcik, key:aaaaaaaaaa" + key);
                OTNotes otNotes = new OTNotes(null, true, row[10]);
                otNotes.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTable_InboxMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton_OK;
    private javax.swing.JButton jButton_ProcessInbox;
    private javax.swing.JPanel jPanel_Inbox;
    private javax.swing.JPanel jPanel_Outbox;
    private javax.swing.JPanel jPanel_Receipts;
    private javax.swing.JScrollPane jScrollPane_Inbox;
    private javax.swing.JScrollPane jScrollPane_Outbox;
    private javax.swing.JScrollPane jScrollPane_Recepts;
    private javax.swing.JTabbedPane jTabbedPane1;
    private static javax.swing.JTable jTable8;
    private static javax.swing.JTable jTable_Inbox;
    private static javax.swing.JTable jTable_Outbox;
    // End of variables declaration//GEN-END:variables

    private void setCustomProperties() {

        int width = 120;

        jTable_Inbox.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn col = jTable_Inbox.getColumnModel().getColumn(4);

        col.setPreferredWidth(width);

        col = jTable_Inbox.getColumnModel().getColumn(5);
        col.setPreferredWidth(width);

        col = jTable_Inbox.getColumnModel().getColumn(6);
        col.setPreferredWidth(180);

        jTable_Outbox.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        col = jTable_Outbox.getColumnModel().getColumn(4);
        col.setPreferredWidth(80);

        col = jTable_Outbox.getColumnModel().getColumn(4);
        col.setPreferredWidth(width);

        col = jTable_Outbox.getColumnModel().getColumn(5);
        col.setPreferredWidth(width);


        col = jTable_Outbox.getColumnModel().getColumn(6);
        col.setPreferredWidth(150);

        jTable8.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        col = jTable8.getColumnModel().getColumn(3);
        col.setPreferredWidth(width);

        col = jTable8.getColumnModel().getColumn(5);
        col.setPreferredWidth(width);

        // TODO Uncomment below when implementing RECEIPT
        jTabbedPane1.remove(jPanel_Receipts);
    }

    public static void clearPanel() {
        if (jTable_Inbox != null && jTable_Outbox != null && jTable8 != null) {
            ((OTInboxTableModel) jTable_Inbox.getModel()).clearValue();
            ((OTOutboxTableModel) jTable_Outbox.getModel()).clearValue();
            ((OTReceiptTableModel) jTable8.getModel()).clearValue();
        }
    }

    public static void setOTTables(Map inboxData, Map outboxData) {

        inbox = inboxData;
        outbox = outboxData;
        ((OTInboxTableModel) jTable_Inbox.getModel()).setValue(inbox, jTable_Inbox);
        ((OTOutboxTableModel) jTable_Outbox.getModel()).setValue(outbox, jTable_Outbox);
        //((OTReceiptTableModel) jTable8.getModel()).setValue(otDetails.getReceiptData(),jTable8);
    }

    public static void populateOTDetails(OTDetails otDetails) {

        inbox = otDetails.getInboxData();
        outbox = otDetails.getOutboxData();
        accountID = otDetails.getAccountID();
        ((OTInboxTableModel) jTable_Inbox.getModel()).setValue(inbox, jTable_Inbox);
        ((OTOutboxTableModel) jTable_Outbox.getModel()).setValue(outbox, jTable_Outbox);
        //((OTReceiptTableModel) jTable8.getModel()).setValue(otDetails.getReceiptData(),jTable8);
    }

    @Action
    public void ProcessInbox() {
                try {
            OpenTransactionAccount openTransaction = new OpenTransactionAccount();
            Map selectedIndices = new HashMap();
            List finalReceiptRefNo = new ArrayList();
            for (int i = 0; i < jTable_Inbox.getRowCount(); i++) {
                String key = (String) jTable_Inbox.getModel().getValueAt(i, 9);
                if (!Utility.VerifyStringVal(key)) key = new String("");
                if ((Boolean) jTable_Inbox.getModel().getValueAt(i, 7)) {
                    selectedIndices.put(key, true);
                }
                if ((Boolean) jTable_Inbox.getModel().getValueAt(i, 8)) {
                    selectedIndices.put(key, false);
                }
                if ("finalReceipt".equalsIgnoreCase((String) jTable_Inbox.getModel().getValueAt(i, 3)) && (Boolean) jTable_Inbox.getModel().getValueAt(i, 7)) {
                    finalReceiptRefNo.add((String) jTable_Inbox.getModel().getValueAt(i, 1));
                }
            }
            System.out.println("selectedIndices:" + selectedIndices.entrySet());
            if (jTable_Inbox.getRowCount() < 1 || selectedIndices.size() < 1) {
                JOptionPane.showMessageDialog(this, "Please check some transactions to process", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int j = 0; j < finalReceiptRefNo.size(); j++) {
                String referenceNo = (String) finalReceiptRefNo.get(j);
                for (int i = 0; i < jTable_Inbox.getRowCount(); i++) {
                    if (referenceNo.equals((String) jTable_Inbox.getModel().getValueAt(i, 1)) && !(Boolean) jTable_Inbox.getModel().getValueAt(i, 7)) {
                        JOptionPane.showMessageDialog(this, "To close a finalReceipt, you must also close the other receipts that have the same reference number", "Error", JOptionPane.ERROR_MESSAGE);
                        return;

                    }
                }
            }
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            boolean success = openTransaction.processInbox(accountID, selectedIndices);
            if (success) {
                JOptionPane.showMessageDialog(this, "Inbox processed successfully", "Processing Success", JOptionPane.INFORMATION_MESSAGE);
                Helpers.reloadOTDetails(accountID);
                MainPage.reLoadAccount();
                Helpers.setLoadNymTrades(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error in processing Inbox", "Server Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
}
