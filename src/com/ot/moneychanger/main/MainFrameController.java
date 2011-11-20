/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.moneychanger.main;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import net.sf.swinglib.SwingUtil;
import net.sf.swinglib.components.MainFrame;

/**
 *
 * @author cameron
 */
/**
 *  Responsible for building out the application main frame. This class may
 *  be constructed anywhere, but {@link #buildAndShow} must be called from
 *  the event thread.
 */
public class MainFrameController {
    
    private final static String BASE_TITLE = "Money Changer";

    private Concierge _concierge;
    private ActionRegistry _actionRegistry;

    private JFrame _mainFrame;
    
        public MainFrameController(Concierge concierge)
    {
        _concierge = concierge;
        _actionRegistry = new ActionRegistry(_concierge);
    }
        
//----------------------------------------------------------------------------
//  Initialization
//----------------------------------------------------------------------------

    public void buildAndShow()
    {
        _mainFrame = new MainFrame(BASE_TITLE, _actionRegistry.fileQuit);
        _mainFrame.setJMenuBar(createMainMenu());
//        _mainFrame.setContentPane(createContentPane());
        _mainFrame.pack();
        _concierge.setMainFrame(this, _mainFrame);

//        PopupListener.attach(_table, createPopupMenu());
//        resetBucket();

        SwingUtil.centerAndShow(_mainFrame);
    }
    
    
    private JMenuBar createMainMenu()
    {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
//        fileMenu.add(_actionRegistry.fileRefresh);
//        fileMenu.add(new JSeparator(JSeparator.HORIZONTAL));
//        fileMenu.add(_actionRegistry.fileUpload);
//        fileMenu.add(_actionRegistry.fileDownload);
//        fileMenu.add(_actionRegistry.fileDelete);
//        fileMenu.add(new JSeparator(JSeparator.HORIZONTAL));
//        fileMenu.add(_actionRegistry.fileSetPrefs);
//        fileMenu.add(new JSeparator(JSeparator.HORIZONTAL));
        fileMenu.add(_actionRegistry.fileQuit);
//
//        JMenu editMenu = new JMenu("Edit");
//        fileMenu.setMnemonic(KeyEvent.VK_E);
//        editMenu.add(_actionRegistry.editSelectAll);
//        editMenu.add(_actionRegistry.editSelectNone);
//        editMenu.add(new JSeparator(JSeparator.HORIZONTAL));
//        editMenu.add(_actionRegistry.editCopyUrl);
//
//        JMenu bucketMenu = new JMenu("Bucket");
//        fileMenu.setMnemonic(KeyEvent.VK_B);
//        bucketMenu.add(_actionRegistry.bucketSelect);
//        bucketMenu.add(new JSeparator(JSeparator.HORIZONTAL));
//        bucketMenu.add(_actionRegistry.bucketDelete);
//
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
//        menuBar.add(editMenu);
//        menuBar.add(bucketMenu);
        return menuBar;
    }
        
        
    
}
