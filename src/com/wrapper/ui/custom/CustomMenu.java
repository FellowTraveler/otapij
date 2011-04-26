/************************************************************
 -----BEGIN PGP SIGNED MESSAGE-----
 Hash: SHA256
 
 *                 M O N E Y C H A N G E R
 *
 *   http://wiki.github.com/FellowTraveler/Moneychanger/wiki
 *
 *  Open Transactions:
 *       Financial Cryptography and Digital Cash
 *       Library, Protocol, API, Server, and GUI 
 *    
 *    	 -- Anonymous Numbered Accounts.
 *    	 -- Untraceable Digital Cash.
 *    	 -- Triple-Signed Receipts.
 *    	 -- Cheques, Vouchers, Transfers, Inboxes.
 *    	 -- Basket Currencies, Markets, Payment Plans.
 *    	 -- Signed, XML, Ricardian-style Contracts.
 *    
 *  Copyright (C) 2010-2011 by "Fellow Traveler" (A pseudonym)
 *
 *  EMAIL:
 *  FellowTraveler@rayservers.net --- SEE PGP KEY BELOW.
 *  F3llowTraveler@gmail.com --- (not preferred.)
 *  
 *  FINGERPRINT:
 *  9DD5 90EB 9292 4B48 0484  7910 0308 00ED F951 BB8E
 *
 *  BITCOIN:  1NtTPVVjDsUfDWybS4BwvHpG2pdS9RnYyQ
 *
 *  OFFICIAL PROJECT WIKI:
 *  http://wiki.github.com/FellowTraveler/Open-Transactions/wiki 
 *
 *  WEBSITE:
 *  http://www.OpenTransactions.org/
 *    
 *  Components and licensing:
 *   -- Moneychanger..A Java client GUI.....LICENSE:.....GPLv3
 *   -- OTLib.........A class library.......LICENSE:...LAGPLv3 
 *   -- OT-API........A client API..........LICENSE:...LAGPLv3
 *   -- testwallet....Command-line client...LICENSE:...LAGPLv3
 *   -- OT-Server.....Server Application....LICENSE:....AGPLv3
 *  Github.com/FellowTraveler/Open-Transactions/wiki/Components
 *
 *  All of the above OT components were designed and written by
 *  Fellow Traveler, with the exception of Moneychanger, which
 *  was contracted out to Vicky C (livewire_3001@yahoo.com).
 *
 *  -----------------------------------------------------
 *
 *   LICENSE:
 *   This program is free software: you can redistribute it
 *   and/or modify it under the terms of the GNU General
 *   Public License as published by the Free Software
 *   Foundation, either version 3 of the License, or (at your
 *   option) any later version.
 *
 *   You should have received a copy of the GNU General
 *   Public License along with this program.  If not, see:
 *   http://www.gnu.org/licenses/
 *
 *   If you would like to use this software outside of the free
 *   software license, please contact FellowTraveler.
 *   (Unfortunately many will run anonymously and untraceably,
 *   so who could really stop them?)
 *   
 *   DISCLAIMER:
 *   This program is distributed in the hope that it will be
 *   useful, but WITHOUT ANY WARRANTY; without even the implied
 *   warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 *   PURPOSE.  See the GNU General Public License for more
 *   details.
 
 -----BEGIN PGP SIGNATURE-----
 wsFVAwUBTbFZUwMIAO35UbuOAQjDRBAAmIUJBi5/WC1KpI4TNAWdQNh6g59qYS6w
 SI6mTMbnP0DUVOrmJdNR7/n1sRlnWzyjKLcKkRtXwRWGC+jE16jijxek9Ome5Qid
 bDqjHSuFvqnsD3+0tbENf+kVrbAReU3YvWk+xFvVc6I2NpS+lEIdjHIWm85jSmew
 Ydx+4KpELkO59thkcKgSYsTSyTP3l9GOTtJlq45XiamoEvso4jFUC1y5KMQsz1KH
 DTE32m5FPZqJqUw9loAmrni3dIMpXKC5yLhdqSMXHK0MAPEIexsuaZjrjKJQSjwV
 eDjwJcMn2WZVvcIr9IEoKEU/2j9wHNZv5Xuj78A/78AkjqEUwrY1M9ht0r/QbusW
 ZT7MlxNCq4DFstrjyKi03yZQGR+m8eJFHE7GvF8Vzg/ap0/CUJzeoXg5wACXGfJj
 k6y8ZBriQO08JECki2sy6oTitDoi7FmzgAIxPGB1qA4HMur/LuzrxAj2V7XkZQlk
 VfAda6Ff9bmStNut+zbsQ0pnIeL/URwWifI8Wq81c7DEIvA5SH/bU9Hws1FMO8PU
 BcDmzadU+syJBTxoP/mHZcLfwHDhcZyBeHX7sHfpHweEunzWjcHjqVCutQMO4dii
 yrsc64WTfAqd4s12SfKMgVFLeL/FUYH7MNqpfgjgwX5co817m9VvCntU6njIuYtV
 6+G/TuSViH8=
 =/jIC
 -----END PGP SIGNATURE-----
 **************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrapper.ui.custom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Vicky C
 */
public class CustomMenu extends JMenu {

    MenuListener menuListener;
    static final Map substanceLook = new HashMap();

    public CustomMenu() {
        super();
        init();
    }

    public CustomMenu(String text) {
        super(text);
        init();
    }

    public static Object getClassName(String key) {

        if (key == null) {
            return null;
        }

        return substanceLook.get(key);
    }

    private void init() {
        JMenuItem jmenuItem;
        menuListener = new MenuListener();
        initMap();
        Set set = substanceLook.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String display = (String) iterator.next();
            jmenuItem = new JMenuItem(display);
            this.add(jmenuItem);
            jmenuItem.addActionListener(menuListener);
        }

    }

    private void initMap() {
        
        substanceLook.put("Sahara", "org.jvnet.substance.skin.SubstanceSaharaLookAndFeel");
        substanceLook.put("NebulaBrickWall", "org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel");
        substanceLook.put("Autumn", "org.jvnet.substance.skin.SubstanceAutumnLookAndFeel");
        substanceLook.put("BusinessBlackSteel", "org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
        substanceLook.put("BusinessBlueSteel", "org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel");
        substanceLook.put("ChallengerDeep", "org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel");
        substanceLook.put("Business", "org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");
        substanceLook.put("CremeCoffee", "org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel");
        substanceLook.put("Creme", "org.jvnet.substance.skin.SubstanceCremeLookAndFeel");
        substanceLook.put("DustCoffee", "org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel");
        substanceLook.put("Dust", "org.jvnet.substance.skin.SubstanceDustLookAndFeel");
        substanceLook.put("EmeraldDusk", "org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel");
        //substanceLook.put("Gemini", "org.jvnet.substance.skin.SubstanceGeminiLookAndFeel");
        //substanceLook.put("GraphiteAqua", "org.jvnet.substance.skin.SubstanceGraphiteAquaLookAndFeel");
        substanceLook.put("Magma", "org.jvnet.substance.skin.SubstanceMagmaLookAndFeel");
        substanceLook.put("MistSilver", "org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel");
        substanceLook.put("Moderate", "org.jvnet.substance.skin.SubstanceModerateLookAndFeel");
        substanceLook.put("Nebula", "org.jvnet.substance.skin.SubstanceNebulaLookAndFeel");
        substanceLook.put("OfficeBlue2007", "org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
        substanceLook.put("OfficeSilver2007", "org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
        substanceLook.put("RavenGraphiteGlass", "org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel");
        substanceLook.put("RavenGraphite", "org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel");
        substanceLook.put("Raven", "org.jvnet.substance.skin.SubstanceRavenLookAndFeel");
        substanceLook.put("Twilight", "org.jvnet.substance.skin.SubstanceTwilightLookAndFeel");

    }
}
