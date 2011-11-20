/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.actions;

//import com.ot.app.moneychanger.controlers.PrefsDialogController;
import com.ot.moneychanger.main.Concierge;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author cameron
 */
/**
 *  Displays the Preferences dialog.
 */
public class FileSetPrefs extends AbstractAction
{
    private static final long serialVersionUID = 1L;


    private Concierge _concierge;


    public FileSetPrefs(Concierge concierge)
    {
        super("Preferences...");
        _concierge = concierge;
    }

//----------------------------------------------------------------------------
//  ActionListener
//----------------------------------------------------------------------------

    public void actionPerformed(ActionEvent e)
    {
//        new PrefsDialogController(_concierge).show();
    }
}
