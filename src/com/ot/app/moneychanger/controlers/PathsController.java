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
            _pathsPanel.jButton_Close.setAction(new Actions.CancelAction());
            _pathsPanel.jButton_Add.setAction(new Actions.AddAction(Dialog.getDialog()));
            _pathsPanel.jButton_Remove.setAction(new Actions.RemoveAction(getSelectedPath()));
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

        private static ViewModel _model;

        public Actions(ViewModel model) {
            this._model = model;
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

            private static FolderBrowser f;

            public AddAction(JDialog dialog) {
                super("Add Path");
                f = new FolderBrowser(dialog);
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                // note: this dialog will be rarely used; no reason to leave
                //       it around, consuming resources
                _model.addPath(f.GetPath());
            }
        }

        public static class RemoveAction extends AbstractAction {

            private Object _rem;

            public RemoveAction(Object rem) {
                super("Remove Path");
                _rem = rem;
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                // note: this dialog will be rarely used; no reason to leave
                //       it around, consuming resources
                _model.removePath(_rem);
            }
        }
    }

    private static class FolderBrowser {

        private JFileChooser _pathFolderChooser;
        private int _returnVal;
        private JDialog _dialog;

        public FolderBrowser(JDialog dialog) {
            _pathFolderChooser = new JFileChooser();
            this._dialog = dialog;
        }

        public String GetPath() {
            _returnVal = _pathFolderChooser.showOpenDialog(_dialog);
            if (_returnVal == JFileChooser.APPROVE_OPTION) {
                return _pathFolderChooser.getSelectedFile().getPath();
            } else {
                return null;
            }

        }
    }

    protected static class ViewModel {

        private static JavaPaths _pathsModel;

        public ViewModel(JavaPaths pathsModel) {
            this._pathsModel = pathsModel;
        }

        public ViewModel(String pathsString) {
            List<String> paths = Arrays.asList(pathsString.split(";"));
            for (String path : paths) {
                _pathsModel.addPath(path);
            }
        }

        public String getPathsString() {

            return _pathsModel.toString();
        }

        protected void setModel(JList list) {
            list.setModel(_pathsModel);
        }

        protected JavaPaths getModel() {
            return _pathsModel;
        }

        protected void addPath(String path) {
            _pathsModel.addPath(path);
        }

        protected void removePath(Object path) {
            _pathsModel.remove(path);
        }
    }
}
