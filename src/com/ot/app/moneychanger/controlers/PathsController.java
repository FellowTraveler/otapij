/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.controlers;

import com.ot.app.moneychanger.dialogs.panels.PathsPanel;
import com.ot.app.moneychanger.models.Load.JavaPaths;
import com.ot.moneychanger.main.Concierge;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import net.sf.swinglib.SwingUtil;
import net.sf.swinglib.UIHelper;
import net.sf.swinglib.actions.ReturnAction;

/**
 *
 * @author cameron
 */
public class PathsController {

    private static Concierge _concierge;

    public PathsController(Concierge concierge) {
        _concierge = concierge;
    }

    public static class Dialog {
        private static Actions _actions;
        private static JDialog _pathsDialog;
        private static PathsPanel _pathsPanel;
        private static ViewModel _model;

        public Dialog(ViewModel model) {
            this._model = model;
            buildPanel();
            buildFields();
            buildDialog();
        }

        private void buildFields() {
            
            _actions = new Actions(_model);
            _pathsPanel.jButton_Close.setAction(new Actions.CancelAction());
            _pathsPanel.jButton_Add.setAction(new Actions.AddAction(Dialog.getDialog()));
            _pathsPanel.jButton_Remove.setAction(new Actions.RemoveAction(_pathsPanel.jList_PathList));
            _pathsPanel.jList_PathList.setModel(_model.getModel());
        }

        private void buildPanel() {
            _pathsPanel = new PathsPanel();

        }

        private void buildDialog() {
            _pathsDialog = UIHelper.newDialog(_concierge.getDialogOwner(), "Preferences", _pathsPanel);
        }

        private static void dispose() {
            _pathsDialog.dispose();
        }
        
        public static void show() {
            _pathsDialog.pack();
            SwingUtil.centerAndShow(_pathsDialog, _concierge.getDialogOwner());
        }
        
        public static void hide()
        {
            _pathsDialog.setVisible(false);
        }

        private static JDialog getDialog() {
            return _pathsDialog;
        }

        private static Object getSelectedPath() {
            return _pathsPanel.jList_PathList.getSelectedValue();
        }
    }

    private static class Actions {

        private static ViewModel _viewModel;

        public Actions(ViewModel model) {
            this._viewModel = model;
        }

        public static class CancelAction extends AbstractAction {

            public CancelAction() {
                super("Cancel");
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                // note: this dialog will be rarely used; no reason to leave
                //       it around, consuming resources
                Dialog.hide();
            }
        }

        public static class AddAction extends AbstractAction {

            private static FolderBrowser _folderBrowser;

            public AddAction(JDialog dialog) {
                super("Add Path");
                _folderBrowser = new FolderBrowser(dialog);
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                // note: this dialog will be rarely used; no reason to leave
                //       it around, consuming resources
                _viewModel.addPath(_folderBrowser.GetPath());
            }
        }

        public static class RemoveAction extends AbstractAction {

            private JList _jList;

            public RemoveAction(JList jList) {
                super("Remove");
                _jList = jList;
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                // note: this dialog will be rarely used; no reason to leave
                //       it around, consuming resources
                _viewModel.removePath(_jList.getSelectedValue());
                _jList.setSelectedIndex(_jList.getLastVisibleIndex());
                this.setEnabled(!_viewModel.getModel().isEmpty());
            }
        }
    }

    private static class FolderBrowser {

        private JFileChooser _pathFolderChooser;
        private int _returnVal;

        public FolderBrowser(JDialog dialog) {
                            _pathFolderChooser = new JFileChooser();
                _pathFolderChooser.setCurrentDirectory(new java.io.File("."));
                _pathFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }

        public String GetPath() {
            _returnVal = _pathFolderChooser.showOpenDialog(this._pathFolderChooser);
            if (_returnVal == JFileChooser.APPROVE_OPTION) {
                return _pathFolderChooser.getSelectedFile().getPath();
            } else {
                return null;
            }

        }
    }

    protected static class ViewModel {

        private static JavaPaths _pathsModel;
        private static ReturnAction _returnAction;

        public ViewModel(JavaPaths pathsModel, ReturnAction returnAction) {
            ViewModel._pathsModel = pathsModel;
            ViewModel._returnAction = returnAction;
        }

        public void addPathsString(String pathsString) {
            List<String> paths = Arrays.asList(pathsString.split(";"));
            
            for (String path : paths) {
                if (!path.equals(""))
                _pathsModel.addPath(path);
            }
            _returnAction.ReturnAction();
            
        }

        public String getPathsString() {
            return _pathsModel.toString();
        }

        protected void setModel(JList list) {
            list.setModel(_pathsModel);
            _returnAction.ReturnAction();
        }

        protected JavaPaths getModel() {
            return _pathsModel;
        }

        protected void addPath(String path) {
            _pathsModel.addPath(path);
            _returnAction.ReturnAction();
        }

        protected void removePath(Object path) {
            _pathsModel.remove(path);
            _returnAction.ReturnAction();
        }
    }
}
