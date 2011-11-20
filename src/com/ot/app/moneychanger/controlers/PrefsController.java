/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.controlers;

import com.ot.app.moneychanger.dialogs.panels.PrefsPanel;
import com.ot.moneychanger.main.Concierge;
import com.ot.moneychanger.main.ConfigBean;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import net.sf.swinglib.SwingUtil;
import net.sf.swinglib.UIHelper;
import net.sf.swinglib.actions.DocumentCheckReturn;
import net.sf.swinglib.field.DocumentWatcher;

/**
 *
 * @author cameron
 */
public class PrefsController {

    private static Concierge _concierge;

    private ViewModel _viewModel;
    private Dialog _dialog;

    public PrefsController(Concierge concierge) {
        _concierge = concierge;
        _viewModel = new ViewModel(_concierge.getConfig());
        _dialog = new Dialog(_viewModel);
        _dialog.show();
    }

    public static class Dialog {

        private static JDialog _prefsDialog;
        private static PrefsPanel _prefsPanel;
        private static ViewModel _model;
        private static Set<DocumentListener> _documentListenerSet;
        private static Map<String, Boolean> _isReadyMap;

        public Dialog(ViewModel model) {
            this._model = model;
            buildPrefsPanel();
            buildFields();
            buildPrefsDialog();
        }

        private void buildFields() {
            _documentListenerSet = new HashSet<DocumentListener>();
            _isReadyMap = new HashMap<String, Boolean>();

            _prefsPanel.jTextField_TimeOut.setToolTipText(ConfigBean.timeOutInfo());
            _prefsPanel.jTextField_TimeOut.setText(ViewModel.getPrefs().getTimeOut());
            _documentListenerSet.add(textFieldListener(_prefsPanel.jTextField_TimeOut,
                    ConfigBean.timeOutRegex(), _isReadyMap));

            _prefsPanel.jTextField_Wallet.setToolTipText(ConfigBean.walletFilenameInfo());
            _prefsPanel.jTextField_Wallet.setText(ViewModel.getPrefs().getWalletFilename());
            _documentListenerSet.add(textFieldListener(_prefsPanel.jTextField_Wallet,
                    ConfigBean.walletFilenameRegex(), _isReadyMap));

            _prefsPanel.jTextField_UserData.setToolTipText(ConfigBean.userDataPathInfo());
            _prefsPanel.jTextField_UserData.setText(ViewModel.getPrefs().getUserDataPath());
            _documentListenerSet.add(textFieldListener(_prefsPanel.jTextField_UserData,
                    ConfigBean.userDataPathRegex(), _isReadyMap));

            _prefsPanel.jTextField_Libraries.setToolTipText(ConfigBean.OTPathInfo());
            _prefsPanel.jTextField_Libraries.setText(ViewModel.getPrefs().getOTPath());
            _documentListenerSet.add(textFieldListener(_prefsPanel.jTextField_Libraries,
                    ConfigBean.OTPathRegex(), _isReadyMap));

            _prefsPanel.jButton_OK.setAction(new Actions.OKAction());
            _prefsPanel.jButton_Cancel.setAction(new Actions.CancelAction());
            _prefsPanel.jButton_UserData.setAction(new Actions.BrowseAction(_prefsPanel.jTextField_UserData));
            _prefsPanel.jButton_Libraries.setAction(new Actions.EditLibrariesAction(_prefsPanel.jTextField_Libraries));
        }

        public void show() {
            if (_prefsDialog == null) {
                buildPrefsPanel();
                buildFields();
                buildPrefsDialog();
                Refresh();
            }

            Dialog._prefsDialog.pack();
            SwingUtil.centerAndShow(_prefsDialog, _concierge.getDialogOwner());
        }

        private void buildPrefsPanel() {
            PrefsPanel prefsPanel = new PrefsPanel();
            _prefsPanel = prefsPanel;
        }

        private void buildPrefsDialog() {
            Dialog._prefsDialog = UIHelper.newDialog(_concierge.getDialogOwner(), "Preferences", _prefsPanel);
        }
    }

    private static DocumentListener textFieldListener(final JTextField field, final String regex, final Map<String, Boolean> isReady) {
        DocumentCheckReturn testDocReturn = new DocumentCheckReturn() {

            @Override
            public void ReturnIsValid(boolean isValid) {
                if (!isValid) {
                    field.setBackground(Color.YELLOW);
                }
                if (isValid) {
                    field.setBackground(Color.WHITE);
                }
                isReady.put(field.getName(), isValid);
                Refresh();
            }
        };
        return new DocumentWatcher(field.getDocument(), testDocReturn, regex);
    }

    private static void Refresh() {
        boolean ready = true;
        for (boolean b : Dialog._isReadyMap.values()) {
            ready = b;
            if (!ready) {
                break;
            }
        }
        Dialog._prefsPanel.jButton_OK.setEnabled(ready);
    }

    private static class Actions {

        private static ViewModel _viewModel;

        public Actions(ViewModel viewModel) {
            this._viewModel = viewModel;
        }

        private static class EditLibrariesAction extends AbstractAction {

            public EditLibrariesAction(JTextField textField) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }

        private static class BrowseAction extends AbstractAction {

            private JFileChooser pathFolderChooser;
            private JTextField _textField;

            public BrowseAction(JTextField textField) {

                super("Browse");

                this._textField = textField;

                pathFolderChooser = new JFileChooser();
                pathFolderChooser.setCurrentDirectory(new java.io.File("."));
                pathFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                int returnVal = pathFolderChooser.showOpenDialog(this.pathFolderChooser);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    _textField.setText(pathFolderChooser.getSelectedFile().getPath());

                } else {
                    System.out.println("Cancelled");
                }
            }
        }

        private static class CancelAction extends AbstractAction {

            public CancelAction() {
                super("Cancel");
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                // note: this dialog will be rarely used; no reason to leave
                //       it around, consuming resources
                Dialog._prefsDialog.dispose();
            }
        }

        private static class OKAction extends AbstractAction {

            public OKAction() {
                super("OK");
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                // note: this dialog will be rarely used; no reason to leave
                //       it around, consuming resources
                ViewModel.getPrefs().setOTPath(Dialog._prefsPanel.jTextField_Libraries.getText());
                ViewModel.getPrefs().setTimeOut(Dialog._prefsPanel.jTextField_TimeOut.getText());
                ViewModel.getPrefs().setWalletFilename(Dialog._prefsPanel.jTextField_Wallet.getText());
                ViewModel.getPrefs().setUserDataPath(Dialog._prefsPanel.jTextField_UserData.getText());
                ViewModel.getPrefs().setOTPath(Dialog._prefsPanel.jTextField_Libraries.getText());
                Dialog._prefsDialog.dispose();
            }
        }
    }

    protected static class ViewModel {

        private static ConfigBean _configBean;

        public ViewModel(ConfigBean configBean) {
            this._configBean = configBean;
        }

        static protected ConfigBean getPrefs() {
            return _configBean;
        }
    }
}
