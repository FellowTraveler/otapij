/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.ui.model;

import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Vicky C
 */
public class PaymentOutboxTableModel extends AbstractTableModel implements WrapperTableModel {

    private String[] columnNames = {"Subject", "To", "Server Name", "Type", "Unique ID", "NYM ID", "Server ID"};
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
        if (data == null) {
            return null;
        }
        if (col >= columnNames.length || col < 0 || row < 0) {
            return null;
        }
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
            if (getValueAt(0, column) == null) {
                return String.class;
            }
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

    public void setValue(Map values, JTable nymOutBox) {

        clearValue();

        fireTableDataChanged();

    }

    public static void removeCols(JTable paymentTable) {

        TableColumnModel tcm = paymentTable.getColumnModel();
        System.out.println("getColumnCount:" + tcm.getColumnCount());

        if (tcm.getColumnCount() == 7) {
            paymentTable.removeColumn(tcm.getColumn(6));
        }
        if (tcm.getColumnCount() == 6) {
            paymentTable.removeColumn(tcm.getColumn(5));
        }

        if (tcm.getColumnCount() == 5) {
            paymentTable.removeColumn(tcm.getColumn(4));
        }
    }
}
