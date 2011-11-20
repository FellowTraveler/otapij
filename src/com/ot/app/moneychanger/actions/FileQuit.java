/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.actions;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.AbstractAction;

/**
 *
 * @author cameron
 */
/**
 *  Shuts down the application, after first checking to ensure that everything
 *  has been saved. In addition to being invoked from the menu, this action is
 *  attached to the main frame's close button.
 */
public class FileQuit extends AbstractAction implements WindowListener
{
    private static final long serialVersionUID = 1L;

//----------------------------------------------------------------------------
//  Instance Variables and Constructor
//----------------------------------------------------------------------------

    public FileQuit()
    {
        super("Quit");
    }


//----------------------------------------------------------------------------
//  ActionListener
//----------------------------------------------------------------------------

    public void actionPerformed(ActionEvent e)
    {
 //       _logger.info("invoked");
        System.exit(0);
    }


//----------------------------------------------------------------------------
//  WindowListener
//----------------------------------------------------------------------------

    public void windowActivated(WindowEvent e)
    {
        // ignored
    }

    public void windowClosed(WindowEvent e)
    {
        // ignored
    }

    public void windowClosing(WindowEvent e)
    {
        actionPerformed(null);
    }

    public void windowDeactivated(WindowEvent e)
    {
        // ignored
    }

    public void windowDeiconified(WindowEvent e)
    {
        // ignored
    }

    public void windowIconified(WindowEvent e)
    {
        // ignored
    }

    public void windowOpened(WindowEvent e)
    {
        // ignored
    }
}
