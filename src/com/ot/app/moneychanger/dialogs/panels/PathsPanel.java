/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PathsPanel.java
 *
 * Created on 20/11/2011, 9:21:11 PM
 */
package com.ot.app.moneychanger.dialogs.panels;

import com.ot.app.moneychanger.controlers.PathsController.ActionKeys;
import com.ot.app.moneychanger.controlers.PathsController.FieldKeys;
import com.ot.app.moneychanger.controlers.PathsController.PathsModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.swinglib.dialog.viewmodel.IViewModel;

/**
 *
 * @author cameron
 */
public class PathsPanel extends javax.swing.JPanel {

    private IViewModel<FieldKeys, ActionKeys> _viewModel;
    private PathsModel _pathsModel;

    /** Creates new form PathsPanel */
    public PathsPanel(IViewModel<FieldKeys, ActionKeys> viewModel, PathsModel pathsModel) {
        _viewModel = viewModel;
        _pathsModel = pathsModel;
        initComponents();
        bindComponents();
        setToolTips();
    }

    private void bindComponents() {
        jButton_Close.setAction(_viewModel.getButtonAction(ActionKeys.CLOSE));
        jButton_Add.setAction(_viewModel.getButtonAction(ActionKeys.ADD));
        jButton_Remove.setAction(_viewModel.getButtonAction(ActionKeys.REMOVE));
        jList_PathList.setModel(_pathsModel.GetAbstractListModel());
        jList_PathList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                _pathsModel.setSelectedElement(e.getFirstIndex());
            }
        });
    }

    private void setToolTips() {
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

        setMinimumSize(new java.awt.Dimension(350, 250));
        setPreferredSize(new java.awt.Dimension(400, 320));
        setLayout(new java.awt.GridBagLayout());

        jLabel_Title.setText("Add/Remove Java Paths");
        jLabel_Title.setName("jLabel_Title"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jLabel_Title, gridBagConstraints);

        jScrollPane_PathList.setName("jScrollPane_PathList"); // NOI18N

        jList_PathList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList_PathList.setName("jList_PathList"); // NOI18N
        jList_PathList.setPreferredSize(new java.awt.Dimension(200, 100));
        jScrollPane_PathList.setViewportView(jList_PathList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jScrollPane_PathList, gridBagConstraints);

        jButton_Add.setText("Add");
        jButton_Add.setName("jButton_Add"); // NOI18N
        jButton_Add.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jButton_Add, gridBagConstraints);

        jButton_Remove.setText("Remove");
        jButton_Remove.setName("jButton_Remove"); // NOI18N
        jButton_Remove.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jButton_Remove, gridBagConstraints);

        jButton_Close.setText("Close");
        jButton_Close.setName("jButton_Close"); // NOI18N
        jButton_Close.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jButton_Close, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JButton jButton_Add = new javax.swing.JButton();
    private final javax.swing.JButton jButton_Close = new javax.swing.JButton();
    private final javax.swing.JButton jButton_Remove = new javax.swing.JButton();
    private final javax.swing.JLabel jLabel_Title = new javax.swing.JLabel();
    public final javax.swing.JList jList_PathList = new javax.swing.JList();
    private final javax.swing.JScrollPane jScrollPane_PathList = new javax.swing.JScrollPane();
    // End of variables declaration//GEN-END:variables
}
