/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import net.sf.swinglib.AsynchronousOperation;
import net.sf.swinglib.CursorManager;

/**
 *
 * @author cameron
 */
public class Concierge {
    private ConfigBean _config;

    private ExecutorService _threadPool;
    private CursorManager _cursorManager;
    private JFrame _dialogOwner;
    private MainFrameController _mainFrame;
    
    private LoadMoneyChanger _loadMoneyChanger;


    public Concierge(ConfigBean config, LoadMoneyChanger loadMoneyChanger)
    {
        _config = config;
        _loadMoneyChanger = loadMoneyChanger;
    }
    
    //----------------------------------------------------------------------------
//  Public Accessor Methods -- these may be called from anywhere, at any time
//----------------------------------------------------------------------------

    /**
     *  Adds an operation to the shared background thread pool. This pool is
     *  created at instantiation, and cannot be changed.
     */
    public void execute(AsynchronousOperation<?> op)
    {
        if (_threadPool == null)
            _threadPool = Executors.newFixedThreadPool(1);
        _threadPool.execute(op);
    }


    /**
     *  Returns the configuration bean.
     */
    public ConfigBean getConfig()
    {
        return _config;
    }
    
    public LoadMoneyChanger getLoadMoneyChanger()
    {
        return _loadMoneyChanger;
    }


    /**
     *  Returns the "main frame" controller, for update from actions.
     */
    public MainFrameController getMainFrame()
    {
        return _mainFrame;
    }


    /**
     *  Returns the "owner frame" for all dialogs created by the app (nominally
     *  the main frame).
     */
    public JFrame getDialogOwner()
    {
        return _dialogOwner;
    }


    /**
     *  Returns the cursor manager, allowing operations/actions to set a temporary
     *  cursor on any component in the application.
     */
    public CursorManager getCursorManager()
    {
        if (_cursorManager == null)
            _cursorManager = new CursorManager();
        return _cursorManager;
    }


    /**
     *  Returns a factory for S3 requests, using current configuration params.
     */
//    public S3Factory getS3Factory()
//    {
//        // we recreate each time because the config might have changed
//        // FIXME - do better management of the HttpClient?
//        return new S3Factory(
//               _config.getAmazonEndpoint(),
//               _config.getAmazonPublicKey(),
//               _config.getAmazonPrivateKey());
//    }


//----------------------------------------------------------------------------
//  Methods called during initialization; these are all protected, on the
//  assumption that all initialization takes place in the "main" package
//----------------------------------------------------------------------------

    protected void setMainFrame(MainFrameController controller, JFrame frame)
    {
        _mainFrame = controller;
        _dialogOwner = frame;
    }
}
