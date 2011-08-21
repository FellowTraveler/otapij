/************************************************************
 -----BEGIN PGP SIGNED MESSAGE-----
 Hash: SHA256
 
 *                 M O N E Y C H A N G E R
 *
 *   http://wiki.github.com/FellowTraveler/Moneychanger/wiki
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
 *  Copyright (C) 2010-2011 by "Fellow Traveler" (A pseudonym)
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
 *  http://wiki.github.com/FellowTraveler/Open-Transactions/wiki 
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
 wsFVAwUBTbFZUwMIAO35UbuOAQjDRBAAmIUJBi5/WC1KpI4TNAWdQNh6g59qYS6w
 SI6mTMbnP0DUVOrmJdNR7/n1sRlnWzyjKLcKkRtXwRWGC+jE16jijxek9Ome5Qid
 bDqjHSuFvqnsD3+0tbENf+kVrbAReU3YvWk+xFvVc6I2NpS+lEIdjHIWm85jSmew
 Ydx+4KpELkO59thkcKgSYsTSyTP3l9GOTtJlq45XiamoEvso4jFUC1y5KMQsz1KH
 DTE32m5FPZqJqUw9loAmrni3dIMpXKC5yLhdqSMXHK0MAPEIexsuaZjrjKJQSjwV
 eDjwJcMn2WZVvcIr9IEoKEU/2j9wHNZv5Xuj78A/78AkjqEUwrY1M9ht0r/QbusW
 ZT7MlxNCq4DFstrjyKi03yZQGR+m8eJFHE7GvF8Vzg/ap0/CUJzeoXg5wACXGfJj
 k6y8ZBriQO08JECki2sy6oTitDoi7FmzgAIxPGB1qA4HMur/LuzrxAj2V7XkZQlk
 VfAda6Ff9bmStNut+zbsQ0pnIeL/URwWifI8Wq81c7DEIvA5SH/bU9Hws1FMO8PU
 BcDmzadU+syJBTxoP/mHZcLfwHDhcZyBeHX7sHfpHweEunzWjcHjqVCutQMO4dii
 yrsc64WTfAqd4s12SfKMgVFLeL/FUYH7MNqpfgjgwX5co817m9VvCntU6njIuYtV
 6+G/TuSViH8=
 =/jIC
 -----END PGP SIGNATURE-----
 **************************************************************/


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wrapper.ui.model;

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
             if(data==null)
                return 0;
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }
        
public Object getValueAt(int row, int col) {
             if(data==null)
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


