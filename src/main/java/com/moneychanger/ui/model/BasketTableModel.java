/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.moneychanger.ui.model;

import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Vicky C
 */
public class BasketTableModel  extends DefaultTableModel implements WrapperTableModel {

    private String[] columnNames = {"Baskets","ID"};
    private Object[][] data;

      public void setValue(List values) {
        clearValue();
        data = new Object[values.size()][];
        for (int i = 0; i < values.size(); i++) {
            String[] row = (String[]) values.get(i);
            data[i] = row;
        }

        fireTableDataChanged();
    }

      public void setValue(Map values,JTable basketTable){

      clearValue();
      fireTableDataChanged();
        }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        if(row<0 || column<0)
            return;
        data[row][column] = aValue;
        fireTableCellUpdated(row, column);
    }
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if(data!=null)
            return data.length;
            else
                return 0;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {

            if(row>-1 && col>-1 && data!=null)
                return data[row][col];
            else
                return null;
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

    public void clearValue() {
        data = null;
        fireTableDataChanged();
    }


    public static void removeCols(JTable basketTable){

        TableColumnModel tcm = basketTable.getColumnModel();
        System.out.println("getColumnCount:" + tcm.getColumnCount());
        if (tcm.getColumnCount() == 2) {
            basketTable.removeColumn(tcm.getColumn(1));
        }

    }
}



