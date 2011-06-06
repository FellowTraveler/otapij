/*
*To change this template, choose Tools | Templates
*and open the template in the editor.
 */

package com.wrapper.ui.model;

import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ContactDetailsTableModel extends DefaultTableModel implements WrapperTableModel {

    private String[] columnNames = {"  "};
    private Object[][] data;

    public void setColumnNames(String[] columnNames){
        System.out.println("In setColumnNames");
        this.columnNames = columnNames;
        fireTableStructureChanged();
    }

      public void setValue(List values) {
        clearValue();
        System.out.println(" setValue "+values.size());
        data = new Object[values.size()][];
        for (int i = 0; i < values.size(); i++) {
            String[] row = (String[]) values.get(i);
            data[i] = row;
        }

        fireTableDataChanged();
    }

      public void setValue(Map values,JTable contactTable){

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


    public static void removeCols(JTable table){

        TableColumnModel tcm = table.getColumnModel();
        System.out.println("getColumnCount:" + tcm.getColumnCount());
        if (tcm.getColumnCount() == 2) {
            table.removeColumn(tcm.getColumn(1));
        }

    }
}



