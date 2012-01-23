/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.main;

import com.ot.app.moneychanger.actions.LoadMenuAction;
import com.ot.app.moneychanger.actions.FileQuit;
import com.ot.app.moneychanger.actions.FileResetPrefs;
import com.ot.app.moneychanger.actions.FileSetPrefs;
import com.ot.app.moneychanger.actions.ShowObjectInfo;

/**
 *
 * @author cameron
 */
/**
 *  A central location for access to shared action instances. All
 *  instances are public members, set when the assocated GUI object
 *  is constructed.
 */
public class ActionRegistry {

    public ShowObjectInfo showObjectInfo;
    public FileSetPrefs fileSetPrefs;
    public FileQuit fileQuit;
    public FileResetPrefs fileResetPrefs;
    public LoadMenuAction loadMenuAction;

//----------------------------------------------------------------------------
//  Only one constructor, meant to be called within the package
//----------------------------------------------------------------------------
    protected ActionRegistry(Concierge concierge) {
        showObjectInfo = new ShowObjectInfo(concierge);
        loadMenuAction =  new LoadMenuAction(concierge);
        fileSetPrefs = new FileSetPrefs(concierge);
        fileResetPrefs = new FileResetPrefs(concierge);
        fileQuit = new FileQuit();

    }

//----------------------------------------------------------------------------
//  Public methods - used to enable/disable groups of actions
//----------------------------------------------------------------------------
    /**
     *  Called when the selection state changes (either some row is selected
     *  or no rows are selected).
     */
    public void updatePerSelection(int count) {
//        fileDownload.setEnabled(count > 0);
//        fileDelete.setEnabled(count > 0);
//        editSelectNone.setEnabled(count > 0);
//        editCopyUrl.setEnabled(count == 1);
    }

    /**
     *  Called to indicate whether there's currently an active bucket list
     *  being displayed.
     */
    public void updatePerActiveBucket(boolean isActive) {
//        bucketDelete.setEnabled(isActive);
    }
}
