/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.models.viewmodel;

import com.ot.app.moneychanger.actions.IActions;
import com.ot.app.moneychanger.main.ConfigBean;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.Action;
import javax.swing.text.Document;
import net.sf.swinglib.actions.FieldValidationChange;

/**
 *
 * @author cameron
 */
public abstract class AbstractViewModel<F extends Enum<F>, A extends Enum<A>> implements IViewModel<F, A> {

    protected IFields<F> _fields;
    protected IActions<A> _actions;

    public AbstractViewModel(IFields<F> fields, IActions<A> actions) {
        _fields = fields;
        _actions = actions;
    }

    @Override
    public void bindValidatedDoc(F fieldKey, Document doc, FieldValidationChange fieldValidationChange) {
        //throw new UnsupportedOperationException("Not supported yet.");
        _fields.bindValidatedDoc(fieldKey, doc, fieldValidationChange);
    }

    @Override
    public String getFieldInfo(F fieldKey) {
        return _fields.getToolTipText(fieldKey);
    }

    @Override
    public Action getButtonAction(A buttonKey) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return _actions.getAction(buttonKey);
    }

    @Override
    public String getButtonInfo(A buttonKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
