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

package com.moneychanger.ui.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Vicky C
 */
public class NYMBoxTableModel extends AbstractTableModel implements WrapperTableModel {

        private String[] columnNames = {"Subject" , "From", "Server Name","Unique ID","NYM ID","Server ID"};
        private Object[][] data = null;

        /**
         *
	    {"TRN11","TRN23","Withdraw Cash Receipt","Test address","11/11/2010 12:30:12"},
	    {"TRN19","TRN21","Deposit Cash Receipt","Test address","11/11/2010 12:30:12"}
         * @return
         */
       
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
             if(null == data)
                return 0;
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }
        
public Object getValueAt(int row, int col) {
             if(null == data)
                return null;
            if(col>=columnNames.length || col<0 || row<0)
                return null;
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @Override
        public Class getColumnClass(int column) {
               Class returnValue;
               if ((column >= 0) && (column < getColumnCount())) {
                 if(getValueAt(0, column)==null)
                     return String.class;
                 returnValue = getValueAt(0, column).getClass();
               } else {
                 returnValue = Object.class;
               }
               return returnValue;
             }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {

                return false;

        }

    public void setValue(List values, JTable nymBox) {
        data = new Object[values.size()][];
        for(int i=0;i<values.size();i++){
            String[] row = (String[]) values.get(i);
            data [i] = row;
        }
      TableColumnModel tcm = nymBox.getColumnModel();
      System.out.println("nymbox.getColumncount"+tcm.getColumnCount());
      if(tcm.getColumnCount()==6){
        nymBox.removeColumn(tcm.getColumn(5));
      }
      if(tcm.getColumnCount()==5){
         nymBox.removeColumn(tcm.getColumn(4));
      }

      if(tcm.getColumnCount()==4){
         nymBox.removeColumn(tcm.getColumn(3));
      }

      fireTableDataChanged();
       
    }

    public void clearValue() {
        data = null;
        fireTableDataChanged();
    }
    public void setValue(Map values, JTable nymBox) {

       clearValue();
       Set set = values.keySet();
       Iterator iterator = set.iterator();
       int i=0;
       data = new Object[values.size()][];
       while(iterator.hasNext()){
           String key = (String)iterator.next();
           String[] row = (String[])values.get(key);
           String[] tableData = new String[6];
           for(int j=0;j<tableData.length;j++)
               tableData[j] = row[j];
           data[i] = tableData;
           i++;
       }

       /*RowSorter<TableModel> sorter =
             new TableRowSorter<TableModel>(this);
           nymBox.setRowSorter(sorter);*/
        TableColumnModel tcm = nymBox.getColumnModel();
      System.out.println("col count:"+tcm.getColumnCount());
      if(tcm.getColumnCount()==6){
        nymBox.removeColumn(tcm.getColumn(5));
      }
      if(tcm.getColumnCount()==5){
         nymBox.removeColumn(tcm.getColumn(4));
      }

      if(tcm.getColumnCount()==4){
         nymBox.removeColumn(tcm.getColumn(3));
      }
        fireTableDataChanged();
     
    }



}


