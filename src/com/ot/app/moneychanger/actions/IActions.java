/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.actions;

import com.ot.app.moneychanger.models.viewmodel.IDialog;
import javax.swing.Action;

/**
 *
 * @author cameron
 */
public interface IActions<K extends Enum<K>> {
    public Action getAction(K key);
    public IDialog getDialog();
    public void setConfig();
    public void setActionEnabled(K key, boolean enabled);
}
