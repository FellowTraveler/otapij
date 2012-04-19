/*
 *To change this template, choose Tools | Templates
 *and open the template in the editor.
 */
package com.wrapper.ui.panels;

/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;


import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationParameters;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Christopher Deckers
 */
public class WebBrowserPanel extends JPanel {

    public WebBrowserPanel(String url, String usernameID, String passwordID, String username, String password) {
        super(new BorderLayout());
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        //webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
        final JWebBrowser webBrowser = new JWebBrowser();
        WebBrowserNavigationParameters parameters = new WebBrowserNavigationParameters();
        Map<String, String> postDataMap = new HashMap<String, String>();
        
        postDataMap.put(usernameID, username);
        postDataMap.put(passwordID, password);
        parameters.setPostData(postDataMap);
        webBrowser.navigate(url, parameters);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        add(webBrowserPanel, BorderLayout.CENTER);
        // Create an additional bar allowing to show/hide the menu bar of the web browser.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
        JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
        menuBarCheckBox.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        buttonPanel.add(menuBarCheckBox);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}
