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
package com.wrapper.ui.model;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
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
public class CashPurseTableModel extends AbstractTableModel implements WrapperTableModel {

    private String[] columnNames = {"Denomination", "Valid From", "Valid To", "Token ID", "Mint Series", "Select", "Token"};
    private Object[][] data = null;
    JCheckBox checkBox = new JCheckBox();
    CheckBoxEditorCell editorCheckBox = new CheckBoxEditorCell(checkBox);

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
        if (data == null) {
            return null;
        }
        if (row < 0 || col < 0) {
            return null;
        }
        return data[row][col];
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        if (row < 0 || column < 0) {
            return;
        }
        data[row][column] = aValue;
        fireTableCellUpdated(row, column);
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
    public boolean isCellEditable(int row, int col) {
        if (col == 5) {
            return true;
        }
        return false;

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

    public void setValue(Map values, JTable cashPurseTable) {

        clearValue();
        System.out.println("In cash purse table mode---set values size:" + values.size());
        Set set = values.keySet();
        Iterator iterator = set.iterator();
        int i = 0;
        data = new Object[values.size()][columnNames.length];
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String[] row = (String[]) values.get(key);
            System.out.println("row len:" + row.length);
            Object[] tableData = new Object[columnNames.length];
            for (int j = 0; j < row.length; j++) {
                tableData[j] = row[j];
            }
            tableData[5] = Boolean.FALSE;
            tableData[6] = key;
            data[i] = tableData;
            i++;
        }


       /*RowSorter<TableModel> sorter =
             new TableRowSorter<TableModel>(this);
           cashPurseTable.setRowSorter(sorter);*/

        TableColumnModel tcm = cashPurseTable.getColumnModel();
        System.out.println("cash purse getColumn() count:" + tcm.getColumnCount());
        if (tcm.getColumnCount() == 7) {
            cashPurseTable.removeColumn(tcm.getColumn(6));
        }
        fireTableDataChanged();
    }
}

class CheckBoxEditorCell extends DefaultCellEditor {

    CheckBoxEditorCell(JCheckBox checkbox) {
        super(checkbox);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        System.out.println("isSelected:" + isSelected);
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}
