/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.controlers;

import com.ot.app.moneychanger.dialogs.panels.PrefsPanel;
import net.sf.swing.dialog.viewmodel.AbstractViewModel;
import com.ot.app.moneychanger.main.Concierge;
import com.ot.app.moneychanger.main.ConfigBean;
import net.sf.swinglib.actions.AbstractActions;
import net.sf.swing.dialog.AbstractDialog;
import net.sf.swinglib.field.AbstractFields;
import net.sf.swinglib.actions.IActions;
import com.ot.app.moneychanger.controlers.PrefsController.ValidationGroups.CheckedFields;
import com.ot.app.moneychanger.main.helpers.AppdataDirectory;
import com.ot.app.moneychanger.main.helpers.OSType;
import net.sf.swing.dialog.IDialog;
import net.sf.swinglib.field.IFields;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import net.sf.swing.document.DocWatcher;
import net.sf.swinglib.SwingUtil;
import net.sf.swinglib.UIHelper;
import net.sf.swinglib.actions.ReturnAction;
import net.sf.swinglib.validation.AbstractValidationGroup;
import net.sf.swinglib.validation.ValidationChangedEvent;
import net.sf.swinglib.validation.ValidationChangedEventListener;

/**
 *
 * @author cameron
 */
public class PrefsController {

    private static Concierge _concierge;
    private static IDialog _dialog;
    private static PrefsModel _prefsModel;
    private static Fields _fields;
    private Actions _actions;
    private PrefsViewModel _prefsViewModel;
    private static CheckedFields _chekedAcceptAction;

    // <editor-fold defaultstate="collapsed" desc="PrefsController Methods">
    public PrefsController(Concierge concierge) {
        _concierge = concierge;
        buildDialog();
    }

    private void buildDialog() {
        _prefsModel = new PrefsModel(_concierge.getConfig());
        _chekedAcceptAction = new ValidationGroups().new CheckedFields();
        _fields = new Fields(_prefsModel);
        _actions = new Actions(_fields);
        _prefsViewModel = new PrefsViewModel(_fields, _actions);
        _dialog = new Dialog(_prefsViewModel);
    }

    public void show() {
        _dialog.show();
    }

    public void close() {
        _dialog.close();
    }

    public enum ActionKeys {

        OK, CANCEL, BROWSE, LIBRARIES
    }

    public enum FieldKeys {

        OTLIB, TIMEOUT, USERDATA, WALLET
    };
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Dialog Class">

    private static class Dialog extends AbstractDialog {

        
                private JDialog _jDialog;
        private JPanel _jPanel;

        public Dialog(PrefsViewModel prefsViewModel) {
            _jPanel = new PrefsPanel(prefsViewModel);
            buildDialog();
        }

        @Override
        public void show() {
            _jDialog.pack();
            SwingUtil.centerAndShow(_jDialog, _concierge.getDialogOwner());
        }

        @Override
        public void close() {
            _jDialog.dispose();
        }

        private void buildDialog() {
            _jDialog = UIHelper.newDialog(_concierge.getDialogOwner(), "Preferences", _jPanel);
        }
        
        
        
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Fields Class">

    private static class Fields extends AbstractFields<FieldKeys> {

        private PrefsModel _prefsModel;

        public Fields(PrefsModel prefsModel) {
            super(new EnumMap<FieldKeys, Document>(FieldKeys.class),
                    new EnumMap<FieldKeys, DocWatcher>(FieldKeys.class),
                    new EnumMap<FieldKeys, Boolean>(FieldKeys.class));
            _prefsModel = prefsModel;
        }

        @Override
        public void buildCheckedActions() {
            buildAcceptAction();
        }

        private void buildAcceptAction() {
            _chekedAcceptAction.addItemToCheck(FieldKeys.TIMEOUT);
            _chekedAcceptAction.addItemToCheck(FieldKeys.WALLET);
            _chekedAcceptAction.addItemToCheck(FieldKeys.USERDATA);
//            _chekedAcceptAction.addItemToCheck(FieldKeys.OTLIB);
        }

        @Override
        public void fieldStatusChanged() {
            _chekedAcceptAction.refreshValidationGroup();
        }

        @Override
        public String getToolTipText(FieldKeys key) {
            return _prefsModel.getInfoVieModel(key);
        }

        @Override
        public void updateFieldDoc(FieldKeys fieldKey) {
            updateFieldDoc(fieldKey, _prefsModel.getConfigViewModel(fieldKey));
        }

        @Override
        public void updateConfig() {
            for (FieldKeys key : _fieldsMap.keySet()) {
                try {
                    _prefsModel.setConfigOption(key, _fieldsMap.get(key).getText(0, _fieldsMap.get(key).getLength()));
                } catch (BadLocationException e) {
                }
            }
        }

        @Override
        public String getFieldRegex(FieldKeys fieldKey) {
            return _prefsModel.getRegexViewModel(fieldKey);
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Validation Groups">

    public static class ValidationGroups {

        public class CheckedFields extends AbstractValidationGroup<FieldKeys> {

            public CheckedFields() {
                super(EnumSet.noneOf(FieldKeys.class), new EnumMap<FieldKeys, Boolean>(FieldKeys.class));
            }

            @Override
            public void getFreshValues() {
                for (FieldKeys key : validationItems) {
                    validationItemsStates.put(key, _fields.getFieldStatus(key));
                }
            }
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Actions Class">

    private static class Actions extends AbstractActions<FieldKeys, ActionKeys> {

        private ReturnAction _pathReturnAction;

        public Actions(IFields<FieldKeys> fields) {
            super(fields, new EnumMap<ActionKeys, Action>(ActionKeys.class));
            _pathReturnAction = new PathReturnAction();
            buildActions();
        }

        @Override
        public IDialog getDialog() {
            return _dialog;
        }

        private class PathReturnAction implements ReturnAction {

            @Override
            public String getAction() {
                //return null;
                return _fields.getFieldDocText(FieldKeys.OTLIB);
            }

            @Override
            public void returnAction(String returnValue) {
                _fields.updateFieldDoc(FieldKeys.OTLIB, returnValue);

            }
        }

        private void buildActions() {
            _actionsMap.put(ActionKeys.OK, new OKAction());

            _fields.buildCheckedActions();
            _chekedAcceptAction.addValidationChangedEventListener(new ValidationChangedEventListener() {

                @Override
                public void validationChangedEventOccurred(ValidationChangedEvent evt) {
                    _actionsMap.get(ActionKeys.OK).setEnabled(evt.getNewState());
                }
            });
            _actionsMap.put(ActionKeys.CANCEL, new CancelAction());
            _actionsMap.put(ActionKeys.BROWSE, new BrowseAction());
            _actionsMap.put(ActionKeys.LIBRARIES, new EditLibrariesAction());
        }

        @Override
        public void setConfig() {
            _prefsModel.setConfig();
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        public class EditLibrariesAction extends AbstractAction {

            private PathsController _pathsController;

            public EditLibrariesAction() {
                super("Edit/Add");
                _pathsController = new PathsController(_concierge, _pathReturnAction);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                _pathsController.show();
            }
        }

        public class BrowseAction extends AbstractAction {

            private JFileChooser pathFolderChooser;

            public BrowseAction() {
                super("Browse");
                pathFolderChooser = new JFileChooser();
                pathFolderChooser.setCurrentDirectory(new java.io.File("."));
                pathFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                int returnVal = pathFolderChooser.showOpenDialog(pathFolderChooser);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    _fields.updateFieldDoc(FieldKeys.USERDATA, pathFolderChooser.getSelectedFile().getPath());

                } else {
                    System.out.println("Cancelled");
                }
            }
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Prefs View Model">

    private static class PrefsViewModel extends AbstractViewModel<FieldKeys, ActionKeys> {

        public PrefsViewModel(IFields<FieldKeys> fields, IActions<ActionKeys> actions) {
            super(fields, actions);
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Prefs Config Model">

    private static final class PrefsModel {

        private static ConfigBean _configBean;
        private static Map<FieldKeys, String> _localConfigViewModel;
        private static Map<FieldKeys, String> _localInfoViewModel;
        private static Map<FieldKeys, String> _localRegexViewModel;

        public PrefsModel(ConfigBean configBean) {
            _configBean = configBean;
            setDefaltValues(_configBean);


            _localConfigViewModel = new EnumMap<FieldKeys, String>(FieldKeys.class);
            _localInfoViewModel = new EnumMap<FieldKeys, String>(FieldKeys.class);
            _localRegexViewModel = new EnumMap<FieldKeys, String>(FieldKeys.class);
            buildConfigViewModel();
            buildInfoViewModel();
            buildRegexViewModel();
        }

        public void setDefaltValues(ConfigBean configBean) {
            if (configBean.getUserDataPath().isEmpty()
                    || configBean.getUserDataPath() == null) {
                configBean.setUserDataPath(getDefaultUserDataPath());
            }
            if (configBean.getWalletFilename().isEmpty()
                    || configBean.getWalletFilename() == null) {
                configBean.setWalletFilename("wallet.xml");
            }
            if (configBean.getTimeOut().isEmpty()
                    || configBean.getTimeOut() == null) {
                configBean.setTimeOut("1");
            }

        }

        public String getDefaultUserDataPath() {
            StringBuilder sb;
            sb = AppdataDirectory.AppdataDirectory(OSType.getOS());
            sb.append(File.separator);
            sb.append(".ot");
            sb.append(File.separator);
            sb.append("client_data");
            return sb.toString();
        }

        public void setConfigOption(FieldKeys key, String value) {
            _localConfigViewModel.put(key, value);
        }

        private void buildConfigViewModel() {
            _localConfigViewModel.put(FieldKeys.OTLIB, _configBean.getOTPath());
            _localConfigViewModel.put(FieldKeys.TIMEOUT, _configBean.getTimeOut());
            _localConfigViewModel.put(FieldKeys.USERDATA, _configBean.getUserDataPath());
            _localConfigViewModel.put(FieldKeys.WALLET, _configBean.getWalletFilename());
        }

        private void buildInfoViewModel() {
            _localInfoViewModel.put(FieldKeys.OTLIB, _configBean.OTPathInfo());
            _localInfoViewModel.put(FieldKeys.TIMEOUT, _configBean.timeOutInfo());
            _localInfoViewModel.put(FieldKeys.USERDATA, _configBean.timeOutInfo());
            _localInfoViewModel.put(FieldKeys.WALLET, _configBean.walletFilenameInfo());
        }

        private void buildRegexViewModel() {
            _localRegexViewModel.put(FieldKeys.OTLIB, _configBean.OTPathRegex());
            _localRegexViewModel.put(FieldKeys.TIMEOUT, _configBean.timeOutRegex());
            _localRegexViewModel.put(FieldKeys.USERDATA, _configBean.userDataPathRegex());
            _localRegexViewModel.put(FieldKeys.WALLET, _configBean.walletFilenameRegex());
        }

        public void setConfig() {
            _configBean.setOTPath(_localConfigViewModel.get(FieldKeys.OTLIB));
            _configBean.setTimeOut(_localConfigViewModel.get(FieldKeys.TIMEOUT));
            _configBean.setUserDataPath(_localConfigViewModel.get(FieldKeys.USERDATA));
            _configBean.setWalletFilename(_localConfigViewModel.get(FieldKeys.WALLET));
            _configBean.setConfigUpdated(Boolean.TRUE);
        }

        public String getConfigViewModel(FieldKeys key) {
            return _localConfigViewModel.get(key);
        }

        public String getInfoVieModel(FieldKeys key) {
            return _localInfoViewModel.get(key);
        }

        public String getRegexViewModel(FieldKeys key) {
            return _localRegexViewModel.get(key);
        }

    }
    // </editor-fold>
}
