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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Vicky C
 */
public class OTInboxTableModel extends DefaultTableModel implements WrapperTableModel {

    JCheckBox checkBoxAccept = new JCheckBox();
    JCheckBox checkBoxReject = new JCheckBox();
    CheckBoxEditor editorAccept = new CheckBoxEditor(checkBoxAccept);
    CheckBoxEditor editorReject = new CheckBoxEditor(checkBoxReject);
    private String[] columnNames = {"Txn #", "Reference #", "Amount", "Type", "User From/To", "Account From/To", "Timestamp", "Accept", "Reject", "ID","NYM ID","Account ID"};
    private Object[][] data = null;

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        if (data == null) {
            return 0;
        }
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
    /*public Class getColumnClass(int c) {
    return getValueAt(0, c).getClass();
    }*/
    @Override
        public Class getColumnClass(int column) {
               Class returnValue;
               if ((column >= 0) && (column < getColumnCount())) {
                 if(getValueAt(0, column)==null)
                     return Object.class;
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

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 7 || col == 8) {
            return true;
        }
        return false;

    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        if (row < 0 || column < 0) {
            return;
        }
        data[row][column] = aValue;
        fireTableCellUpdated(row, column);
    }

    public void setValue(List values) {

        data = new Object[values.size()][];
        for (int i = 0; i < values.size(); i++) {
            String[] row = (String[]) values.get(i);
            data[i] = row;
        }

        fireTableDataChanged();
    }

    public void clearValue() {
        data = null;
        fireTableDataChanged();
    }

    /* public void setValueAt(Object aValue, int row, int column) {
    System.out.println("setValueAt:"+aValue+"row:"+row+" col:"+column);
    
    }*/
    public void setValue(Map values, final JTable inbox) {

        clearValue();
        Set set = values.keySet();
        Iterator iterator = set.iterator();
        int i = 0;
        data = new Object[values.size()][];
        while (iterator.hasNext()) {
            try {
                String key = (String) iterator.next();
                String[] row = (String[]) values.get(key);
                Object[] tableData = new Object[12];
                for (int j = 0; j < tableData.length - 1; j++) {
                    tableData[j] = row[j];
                }
                tableData[7] = Boolean.FALSE;
                tableData[8] = Boolean.FALSE;
                tableData[9] = key;
                tableData[10] = row[8];
                tableData[11] = row[9];
                data[i] = tableData;
                i++;
            } catch (Exception e) {
                System.out.println("IIIIIII:" + e);
                e.printStackTrace();
            }
        }


       /*RowSorter<TableModel> sorter =
             new TableRowSorter<TableModel>(this);
           inbox.setRowSorter(sorter);*/

        TableColumnModel tcm = inbox.getColumnModel();
        System.out.println("inbox:" + tcm.getColumnCount());
        
        if(tcm.getColumnCount()==12)
        inbox.removeColumn(tcm.getColumn(11));
      if(tcm.getColumnCount()==11){
          inbox.removeColumn(tcm.getColumn(10));
      }
      if(tcm.getColumnCount()==10){
          inbox.removeColumn(tcm.getColumn(9));
      }

        tcm.getColumn(7).setCellEditor(editorAccept);
        // tcm.getColumn(7).setMaxWidth(20);
        checkBoxAccept.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("In acion");
                if (checkBoxAccept.isSelected()) {
                    setValueAt(false, inbox.getSelectedRow(), 8);
                }
                fireTableCellUpdated(inbox.getSelectedRow(), 8);
            }
        });


        tcm.getColumn(8).setCellEditor(editorReject);
        //tcm.getColumn(8).setMaxWidth(20);
        checkBoxReject.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("In acion -- reject");
                if (checkBoxReject.isSelected()) {
                    setValueAt(false, inbox.getSelectedRow(), 7);
                    fireTableCellUpdated(inbox.getSelectedRow(), 7);
                }
            }
        });

        fireTableDataChanged();
        // tcm.getColumn(8).setCellRenderer(renderer);
    }
}

class CheckBoxEditor extends DefaultCellEditor {

    CheckBoxEditor(JCheckBox checkbox) {
        super(checkbox);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        System.out.println("isSelected:" + isSelected);
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}
