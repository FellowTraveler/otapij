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
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JButton;
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
        private static ViewModel _viewModel;
        protected static Map<ViewModel.keys, JTextField> _fields;
        protected static Map<Actions.keys, JButton> _buttons;
        private static TextFieldListner _textFieldListner;
        private static Actions _actions;

        public Dialog(ViewModel viewModel) {
            Dialog._viewModel = viewModel;
            buildPrefsPanel();
            buildFields();
            buildButtons();
            buildPrefsDialog();
        }

        private void buildFields() {
            _fields = new EnumMap<ViewModel.keys, JTextField>(ViewModel.keys.class);

            _fields.put(ViewModel.keys.OTLIB, _prefsPanel.jTextField_Libraries);
            _fields.put(ViewModel.keys.TIMEOUT, _prefsPanel.jTextField_TimeOut);
            _fields.put(ViewModel.keys.USERDATA, _prefsPanel.jTextField_UserData);
            _fields.put(ViewModel.keys.WALLET, _prefsPanel.jTextField_Wallet);

            _textFieldListner = new TextFieldListner(Dialog._viewModel, Dialog._fields);

            _fields.get(ViewModel.keys.OTLIB).setToolTipText(Dialog._viewModel.getInfoVieModel(ViewModel.keys.OTLIB));
            _textFieldListner.add(ViewModel.keys.OTLIB);

            _fields.get(ViewModel.keys.TIMEOUT).setToolTipText(Dialog._viewModel.getInfoVieModel(ViewModel.keys.TIMEOUT));
            _textFieldListner.add(ViewModel.keys.TIMEOUT);

            _fields.get(ViewModel.keys.USERDATA).setToolTipText(Dialog._viewModel.getInfoVieModel(ViewModel.keys.USERDATA));
            _textFieldListner.add(ViewModel.keys.USERDATA);

            _fields.get(ViewModel.keys.WALLET).setToolTipText(Dialog._viewModel.getInfoVieModel(ViewModel.keys.WALLET));
            _textFieldListner.add(ViewModel.keys.WALLET);
        }

        private void buildButtons() {
            _buttons = new EnumMap<Actions.keys, JButton>(Actions.keys.class);

            _buttons.put(Actions.keys.OK, _prefsPanel.jButton_OK);
            _buttons.put(Actions.keys.CANCEL, _prefsPanel.jButton_Cancel);
            _buttons.put(Actions.keys.BROWSE, _prefsPanel.jButton_UserData);
            _buttons.put(Actions.keys.LIBRARIES, _prefsPanel.jButton_Libraries);

            _actions = new Actions(Dialog._viewModel, _buttons);

            _actions.BindAction(Actions.keys.OK, _actions.new OKAction());
            _actions.BindAction(Actions.keys.CANCEL, _actions.new CancelAction());
            _actions.BindAction(Actions.keys.BROWSE, _actions.new BrowseAction(_fields.get(ViewModel.keys.USERDATA)));
            _actions.BindAction(Actions.keys.LIBRARIES, _actions.new EditLibrariesAction(_fields.get(ViewModel.keys.OTLIB)));
        }

        public void show() {
            if (_prefsDialog == null) {
                buildPrefsPanel();
                buildFields();
                buildPrefsDialog();
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

    private static class TextFieldListner {

        private static ViewModel _viewModel;
        private static Map<ViewModel.keys, JTextField> _fields;
        private static Map<ViewModel.keys, DocumentCheckReturn> _fieldsReturn;
        private static Map<ViewModel.keys, DocumentListener> _fieldListener;
        private static Map<ViewModel.keys, Boolean> _fieldReady;

        public TextFieldListner(ViewModel viewModel, Map<ViewModel.keys, JTextField> fields) {
            _viewModel = viewModel;
            _fields = fields;
            _fieldsReturn = new EnumMap<ViewModel.keys, DocumentCheckReturn>(ViewModel.keys.class);
            _fieldListener = new EnumMap<ViewModel.keys, DocumentListener>(ViewModel.keys.class);
            _fieldReady = new EnumMap<ViewModel.keys, Boolean>(ViewModel.keys.class);
        }

        public void add(final ViewModel.keys key) {
            _fieldsReturn.put(key, new DocumentCheckReturn() {

                @Override
                public void ReturnIsValid(boolean isValid) {
                    if (!isValid) {
                        _fields.get(key).setBackground(Color.YELLOW);
                    }
                    if (isValid) {
                        _fields.get(key).setBackground(Color.WHITE);
                        _viewModel.setViewModelValue(key, _fields.get(key).getText());
                    }
                    _fieldReady.put(key, isValid);
                }
            });
            _fieldListener.put(key,
                    new DocumentWatcher(_fields.get(key).getDocument(),
                    _fieldsReturn.get(key),
                    _viewModel.getRegexVieModel(key)));
        }

        public Boolean fieldReady(ViewModel.keys key) {
            return _fieldReady.get(key);
        }

        public Boolean fieldsReady() {
            boolean ready = false;
            for (boolean b : _fieldReady.values()) {
                ready = b;
                if (!ready) {
                    break;
                }
            }
            return ready;
        }
    }

    private static class Refresh {

        public Refresh() {
        }

        static class RefreshAction implements ActionListener {

            public RefreshAction() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }

    private static class Actions {

        public enum keys {

            OK, CANCEL, BROWSE, LIBRARIES
        }
        private static ViewModel _viewModel;
        private static Map<Actions.keys, ActionListener> _actions;
        private static Map<Actions.keys, JButton> _buttons;

        public Actions(ViewModel viewModel, Map<Actions.keys, JButton> buttons) {
            Actions._actions = new EnumMap<keys, ActionListener>(keys.class);

            Actions._viewModel = viewModel;
            Actions._buttons = buttons;
        }

        public void BindAction(keys key, AbstractAction action) {
            _buttons.get(key).setAction(action);
        }

        public class EditLibrariesAction extends AbstractAction {

            public EditLibrariesAction(JTextField textField) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }

        public class BrowseAction extends AbstractAction {

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

        public class CancelAction extends AbstractAction {

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

        public class OKAction extends AbstractAction {

            public OKAction() {
                super("OK");

            }

            public void okActionsetEnabled(boolean enabled) {
                super.setEnabled(enabled);
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                // note: this dialog will be rarely used; no reason to leave
                //       it around, consuming resources

                _viewModel.updateConfigBean();

                Dialog._prefsDialog.dispose();
            }
        }
    }

    protected static class ViewModel {

        public enum keys {

            OTLIB, TIMEOUT, WALLET, USERDATA
        }
        private static ConfigBean _configBean;
        private static Map<keys, String> _localConfigViewModel;
        private static Map<keys, String> _localInfoViewModel;
        private static Map<keys, String> _localRegexViewModel;

        public ViewModel(ConfigBean configBean) {
            ViewModel._configBean = configBean;
            _localConfigViewModel = new EnumMap<keys, String>(keys.class);
            _localInfoViewModel = new EnumMap<keys, String>(keys.class);
            _localRegexViewModel = new EnumMap<keys, String>(keys.class);
            buildConfigViewModel();
            buildInfoViewModel();
            buildRegexViewModel();
        }

        private void buildConfigViewModel() {
            _localConfigViewModel.put(keys.OTLIB, _configBean.getOTPath());
            _localConfigViewModel.put(keys.TIMEOUT, _configBean.getTimeOut());
            _localConfigViewModel.put(keys.USERDATA, _configBean.getUserDataPath());
            _localConfigViewModel.put(keys.WALLET, _configBean.getWalletFilename());
        }

        private void buildInfoViewModel() {
            _localInfoViewModel.put(keys.OTLIB, _configBean.OTPathInfo());
            _localInfoViewModel.put(keys.TIMEOUT, _configBean.timeOutInfo());
            _localInfoViewModel.put(keys.USERDATA, _configBean.timeOutInfo());
            _localInfoViewModel.put(keys.WALLET, _configBean.walletFilenameInfo());
        }

        private void buildRegexViewModel() {
            _localRegexViewModel.put(keys.OTLIB, _configBean.OTPathRegex());
            _localRegexViewModel.put(keys.TIMEOUT, _configBean.timeOutRegex());
            _localRegexViewModel.put(keys.USERDATA, _configBean.userDataPathRegex());
            _localRegexViewModel.put(keys.WALLET, _configBean.walletFilenameRegex());
        }

        public void updateConfigBean() {
            _configBean.setOTPath(_localConfigViewModel.get(keys.OTLIB));
            _configBean.setTimeOut(_localConfigViewModel.get(keys.TIMEOUT));
            _configBean.setUserDataPath(_localConfigViewModel.get(keys.USERDATA));
            _configBean.setWalletFilename(_localConfigViewModel.get(keys.WALLET));
        }

        public String getConfigVieModel(keys key) {
            return _localConfigViewModel.get(key);
        }

        public String getInfoVieModel(keys key) {
            return _localInfoViewModel.get(key);
        }

        public String getRegexVieModel(keys key) {
            return _localRegexViewModel.get(key);
        }

        public void setViewModelValue(keys key, String value) {
            _localConfigViewModel.put(key, value);
        }
    }
}
