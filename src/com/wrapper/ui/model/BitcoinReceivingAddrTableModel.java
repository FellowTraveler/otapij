/*
*To change this template, choose Tools | Templates
*and open the template in the editor.
 */

package com.wrapper.ui.model;

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
 * @author waqqas
 */
public class BitcoinReceivingAddrTableModel  extends AbstractTableModel implements WrapperTableModel {
        private String[] columnNames = {"Label","Address","Amount"};
        private Object[][] data = null;

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if(data==null)
                return 0;
            return data.length;
        }

        @Override
    public void setValueAt(Object aValue, int row, int column) {
        if(row<0 || column<0)
            return;
        data[row][column] = aValue;
        fireTableCellUpdated(row, column);
    }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            if(data==null)
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

    public void setValue(List values) {
        data = new Object[values.size()][];
        for(int i=0;i<values.size();i++){
            String[] row = (String[]) values.get(i);
            data [i] = row;
        }

        fireTableDataChanged();
    }

    public void clearValue() {
        data = null;
        fireTableDataChanged();
    }

    public void setValue(Map values, JTable receiveAddrTable) {

       clearValue();
       System.out.println("values.size():"+values.size());
       Set set = values.keySet();
       Iterator iterator = set.iterator();
       int i=0;
       data = new Object[values.size()][];
       while(iterator.hasNext()){
           String key = (String)iterator.next();
           String[] row = (String[])values.get(key);
           data[i] = row;
           i++;
       }

       RowSorter<TableModel> sorter =
             new TableRowSorter<TableModel>(this);
           receiveAddrTable.setRowSorter(sorter);

      /*TableColumnModel tcm = receiveAddrTable.getColumnModel();
      System.out.println("receivedTable.getColumn()"+tcm.getColumnCount());
      if(tcm.getColumnCount()==6){
        receiveAddrTable.removeColumn(tcm.getColumn(5));
      }*/

      fireTableDataChanged();
    }



}

