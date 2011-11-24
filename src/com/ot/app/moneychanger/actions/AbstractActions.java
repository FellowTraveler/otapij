/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.actions;

import com.ot.app.moneychanger.models.viewmodel.IFields;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author cameron
 */
public abstract class AbstractActions<F extends Enum<F>, A extends Enum<A>> implements IActions<A> {

    protected IFields<F> _fields;
    protected Map<A, Action> _actionsMap;
    protected Set<AbstractValidatedAction> _validatedActionsList;

    public AbstractActions(IFields<F> fields, Map<A, Action> actionsMap) {
        _fields = fields;
        _actionsMap = actionsMap;
        _validatedActionsList = new HashSet<AbstractValidatedAction>();
    }
    
    @Override
    public Action getAction(A key) {
        return _actionsMap.get(key);
    }

    @Override
    public void setActionEnabled(A key, boolean enabled) {
        _actionsMap.get(key).setEnabled(enabled);
    }

    public class CancelAction extends AbstractValidatedAction {

        public CancelAction() {
            super("Cancel");
        }

        @Override
        public void actionPerformed(ActionEvent ignored) {
            // note: this dialog will be rarely used; no reason to leave
            //       it around, consuming resources
            getDialog().close();
        }
    }

    public class OKAction extends AbstractValidatedAction {

        public OKAction() {
            super("OK");
        }

        @Override
        public void actionPerformed(ActionEvent ignored) {
            // note: this dialog will be rarely used; no reason to leave
            //       it around, consuming resources
            _fields.updateConfig();
            setConfig();
            getDialog().close();
        }
    }
}
