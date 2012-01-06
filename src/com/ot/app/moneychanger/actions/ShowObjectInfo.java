/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.actions;

import com.ot.app.moneychanger.main.Concierge;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

/**
 *
 * @author cameron
 */
/**
 *  Refreshes the main window.
 */
public class ShowObjectInfo  extends AbstractAction
{
    private static final long serialVersionUID = 1L;

    private Concierge _concierge;


    public ShowObjectInfo(Concierge concierge)
    {
        super("ShowObjectInfo");
        putValue(MNEMONIC_KEY, Integer.valueOf('I'));
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

        _concierge = concierge;
    }


//----------------------------------------------------------------------------
//  ActionListener
//----------------------------------------------------------------------------

    public void actionPerformed(ActionEvent evt)
    {
  //      new ShowInfoOP(_concierge).start();
    }
}