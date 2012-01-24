/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.controlers;

import com.ot.app.moneychanger.dialogs.OTPasswordDialog;
import com.ot.app.moneychanger.main.Concierge;
import java.awt.event.ActionEvent;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import net.sf.swinglib.actions.AbstractActions;
import net.sf.swinglib.actions.IActions;
import net.sf.swinglib.actions.ReturnAction;
import net.sf.swinglib.dialog.IDialog;
import net.sf.swinglib.dialog.viewmodel.AbstractViewModel;
import net.sf.swinglib.document.DocWatcher;
import net.sf.swinglib.field.AbstractFields;
import net.sf.swinglib.field.IFields;

/**
 *
 * @author Cameron Garnham
 */
public class PasswordController {

    private static OTPasswordDialog _dialog;
    private static Concierge _concierge;
    private static PasswordModel _passwordModel;
    private static Fields _fields;
    private static Actions _actions;
    private PasswordViewModel _passwordViewModel;
    private static ReturnAction _returnAction;

    // <editor-fold defaultstate="collapsed" desc="Controller Constructor">
    public PasswordController(Concierge concierge, ReturnAction returnAction) {
        _passwordModel = new PasswordModel();
        _returnAction = returnAction;
        _concierge = concierge;
        _fields = new Fields();
        _actions = new Actions(_fields);
        _passwordViewModel = new PasswordViewModel(_fields, _actions);
        _dialog = new OTPasswordDialog(_concierge.getDialogOwner(), true, _passwordViewModel);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public void show() {
        _dialog.setVisible(true);
    }

    public void close() {
        _dialog.dispose();
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Enum Keys">

    public enum ActionKeys {

        OK;
    }

    public enum FieldKeys {

        PASSWORD;
    };
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Password View Model">

    private static class PasswordViewModel extends AbstractViewModel<PasswordController.FieldKeys, PasswordController.ActionKeys> {

        public PasswordViewModel(IFields<PasswordController.FieldKeys> fields, IActions<PasswordController.ActionKeys> actions) {
            super(fields, actions);
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Password Model">

    private static final class PasswordModel {

        private static Map<FieldKeys, String> _localConfigViewModel;

        public PasswordModel() {
            _localConfigViewModel = new EnumMap<FieldKeys, String>(FieldKeys.class);
        }

        public void setConfigOption(FieldKeys key, String value) {
            _localConfigViewModel.put(key, value);
        }

        public String getConfigViewModel(FieldKeys key) {
            return _localConfigViewModel.get(key);
        }

        public String getPassword() {
            return _localConfigViewModel.get(FieldKeys.PASSWORD);
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Fields">

    private static class Fields extends AbstractFields<FieldKeys> {

        public Fields() {
            super(new EnumMap<FieldKeys, Document>(FieldKeys.class),
                    new EnumMap<FieldKeys, DocWatcher>(FieldKeys.class),
                    new EnumMap<FieldKeys, Boolean>(FieldKeys.class));
        }

        @Override
        public void updateFieldDoc(FieldKeys fieldKey) {
            updateFieldDoc(fieldKey, _passwordModel.getConfigViewModel(fieldKey));
        }

        @Override
        public void updateConfig() {
            for (FieldKeys key : _fieldsMap.keySet()) {
                try {
                    _passwordModel.setConfigOption(key, _fieldsMap.get(key).getText(0, _fieldsMap.get(key).getLength()));
                } catch (BadLocationException e) {
                }
            }
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Actions">

    private static class Actions extends AbstractActions<FieldKeys, ActionKeys> {

        public Actions(IFields<FieldKeys> fields) {
            super(fields, new EnumMap<ActionKeys, Action>(ActionKeys.class));
            _actionsMap.put(ActionKeys.OK, new AcceptAction());
        }

        @Override
        public void setConfig() {
            //do nothing, no config to set.
        }

        @Override
        public IDialog getDialog() {
            throw new UnsupportedOperationException("Not Needed.");
        }

        public class AcceptAction extends AbstractAction {

            public AcceptAction() {
                super("Accept");
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                _returnAction.returnAction(_passwordModel.getPassword());
                _dialog.dispose();
            }
        }
    }
}
// </editor-fold>
