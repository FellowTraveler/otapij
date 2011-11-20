/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger;

//import com.ot.app.moneychanger.controlers.PrefsDialogController;
import com.ot.app.moneychanger.controlers.PrefsController;
import com.ot.app.moneychanger.controlers.TestDialogController;
import com.ot.moneychanger.main.Concierge;
import com.ot.moneychanger.main.ConfigBean;
import com.ot.moneychanger.main.MainFrameController;
import com.sun.xml.internal.ws.util.StringUtils;
import javax.swing.SwingUtilities;

/**
 *
 * @author cameron
 */
public class Main {
    public static void main(String[] argv) throws Exception
    {
        final Concierge concierge = new Concierge(new ConfigBean());
             SwingUtilities.invokeAndWait(new Runnable()
        {
            public void run()
            {

                new MainFrameController(concierge).buildAndShow();
                
                if (!concierge.getConfig().isConfigComplete())
                    new PrefsController(concierge);
                

//                if (StringUtils.isBlank(concierge.getConfig().getAmazonBucketName()))
//                    new BucketDialogController(concierge).show();
//                else
//                    new S3InitialLoadOp(concierge).start();
            }
        });
        
    }
    
}
