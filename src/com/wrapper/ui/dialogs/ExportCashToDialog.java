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

/*
 * ExportCashToDialog.java
 *
 * Created on 22 Apr, 2011, 9:09:06 PM
 */

package com.wrapper.ui.dialogs;

import com.wrapper.core.CashPurseAccount;
import com.wrapper.core.NYM;
import com.wrapper.core.dataobjects.CashPurseDetails;
import com.wrapper.core.util.Utility;
import com.wrapper.ui.MainPage;
import com.wrapper.ui.panels.CashPurseAccountBottomPanel;
import com.wrapper.ui.panels.CashPurseAccountTopPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Vicky C
 */
public class ExportCashToDialog extends javax.swing.JDialog {
    private final Map nymMap;
    private static CashPurseDetails details;
    private static ArrayList selectedIndices;


    /** Creates new form ExportCashToDialog */
    public ExportCashToDialog(java.awt.Frame parent, boolean modal,CashPurseDetails cashDetails,ArrayList selectedIndices1) {
        super(parent, modal);
        initComponents();
        details = cashDetails;
        selectedIndices = selectedIndices1;
        nymMap = new NYM().loadNYM();
        setLocation(Utility.getLocation(this.getSize()));
        Utility.populateCombo(nymMap, jComboBox2);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new com.wrapper.ui.custom.SteppedComboBox();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(app.ApplicationLauncher.class).getContext().getResourceMap(ExportCashToDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setAlwaysOnTop(true);
        setName("Form"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select or Paste ID" }));
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        jTextField1.setBackground(Color.WHITE);
        if (jComboBox2.getSelectedIndex() == 0) {
            jTextField1.setText("");
            jTextField1.setEditable(true);
        } else {
            jTextField1.setEditable(false);
            jTextField1.setText(((String[]) nymMap.get((Integer) jComboBox2.getSelectedIndex() - 1))[1]);
        }
}//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CashPurseAccount cashPurseAccount = new CashPurseAccount();
        boolean isPasted = true;
        if(jComboBox2.getSelectedIndex()>0)
            isPasted = false;
        try{
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            String purse = cashPurseAccount.exportCashPurse(details.getServerID(), details.getAssetID(), details.getNymID(), details.getPurse(), selectedIndices,jTextField1.getText(),isPasted);
            if(purse!=null){
                CashPurseDetails cashDetails = new CashPurseAccount().getCashPurseDetails(details.getServerID()+":"+details.getAssetID()+":"+details.getNymID());
                CashPurseAccountBottomPanel.populateCashPurseDetails(cashDetails);
                CashPurseAccountTopPanel.populateCashPurseDetails(cashDetails, cashDetails.getBalance());
                MainPage.reLoadAccount();
                
                //((CashPurseTableModel) jTable5.getModel()).setValue(cashPurseAccount.refreshGridData(details.getServerID(), details.getAssetID(), details.getNymID()), jTable5);
            }
            if(Utility.getObj()!=null){
                new CashPurseExportDetails(null, true,purse,false).setVisible(true);
                dispose();
            }else{
            new CashPurseExportDetails(null, true,purse,true).setVisible(true);
            dispose();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            setCursor(Cursor.getDefaultCursor());
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Transferable clipboardContent = getToolkit().getSystemClipboard().getContents(this);

        if ((clipboardContent != null)
                && (clipboardContent.isDataFlavorSupported(DataFlavor.stringFlavor))) {
            try {
                String tempString;
                tempString = (String) clipboardContent.getTransferData(DataFlavor.stringFlavor);
                jTextField1.setText(tempString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}//GEN-LAST:event_jButton2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExportCashToDialog dialog = new ExportCashToDialog(new javax.swing.JFrame(), true,null,null);
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
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
