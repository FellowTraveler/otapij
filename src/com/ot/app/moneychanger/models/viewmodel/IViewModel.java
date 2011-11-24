/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.models.viewmodel;

import javax.swing.Action;
import javax.swing.text.Document;
import net.sf.swinglib.actions.FieldValidationChange;

/**
 *
 * @author cameron
 */
public interface IViewModel<F extends Enum<F>,A extends Enum<A>> {
    
    void bindValidatedDoc(F fieldKey, Document doc, FieldValidationChange fieldValidationChange);
    String getFieldInfo(F fieldKey);
    
    Action getButtonAction(A buttonKey);
    String getButtonInfo(A buttonKey);
}
