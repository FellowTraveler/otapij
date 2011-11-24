/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.models.viewmodel;

import java.util.Map;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import net.sf.swing.document.DocStateChangedEvent;
import net.sf.swing.document.DocStateChangedEventListener;
import net.sf.swing.document.DocWatcher;
import net.sf.swinglib.actions.FieldValidationChange;

/**
 *
 * @author cameron
 */
public abstract class AbstractFields<F extends Enum<F>> implements IFields<F> {

    protected Map<F, Document> _fieldsMap;
    protected Map<F, DocWatcher> _documentWatcherMap;
    protected Map<F, Boolean> _fieldsStatus;

    public AbstractFields(Map<F, Document> fieldsMap, Map<F, DocWatcher> documentWatcherMap, Map<F, Boolean> fieldsStatus) {
        _documentWatcherMap = documentWatcherMap;
        _fieldsMap = fieldsMap;
        _fieldsStatus = fieldsStatus;
    }

    @Override
    public Boolean getFieldStatus(F fieldKey) {
        return _fieldsStatus.get(fieldKey);
    }

    @Override
    public DocWatcher getDocWatcher(F fieldKey) {
        return _documentWatcherMap.get(fieldKey);
    }

    @Override
    public void bindValidatedDoc(final F key, Document doc, FieldValidationChange _fieldValidationChange) {
        _fieldsMap.put(key, doc);
        _documentWatcherMap.put(key, new DocWatcher(doc, getFieldRegex(key), _fieldValidationChange));
        _documentWatcherMap.get(key).addMyEventListener(new DocStateChangedEventListener() {

            @Override
            public void stateChangedEventOccurred(DocStateChangedEvent evt) {
                _fieldsStatus.put(key, evt.getNewState());
                fieldStatusChanged();
            }
        });
        _documentWatcherMap.get(key).refreshChecks();
        updateFieldDoc(key);
    }

    @Override
    public void updateFieldDoc(F key, String newText) {
        try {
            _fieldsMap.get(key).remove(0, _fieldsMap.get(key).getLength());
            _fieldsMap.get(key).insertString(0, newText, null);
        } catch (BadLocationException e) {
        }
    }

    @Override
    public String getFieldDocText(F fieldKey) {
        try {
            return _fieldsMap.get(fieldKey).getText(0, _fieldsMap.get(fieldKey).getLength());
        } catch (BadLocationException e) {
            return null;
        }
    }

    @Override
    public void updateFieldDoc(F fieldKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getToolTipText(F fieldKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateConfig() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fieldStatusChanged() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getFieldRegex(F fieldKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void buildCheckedActions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}