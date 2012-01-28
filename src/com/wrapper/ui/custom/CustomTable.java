/************************************************************
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA256

 *                 M O N E Y C H A N G E R
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
 *  Copyright (C) 2010-2012 by "Fellow Traveler" (A pseudonym)
 *
 *  EMAIL:
 *  FellowTraveler@rayservers.net
 *  
 *  FINGERPRINT:
 *  9DD5 90EB 9292 4B48 0484  7910 0308 00ED F951 BB8E
 *
 *  BITCOIN:  1NtTPVVjDsUfDWybS4BwvHpG2pdS9RnYyQ
 *
 *  OFFICIAL PROJECT WIKI:
 *  https://github.com/FellowTraveler/Moneychanger
 *  https://github.com/FellowTraveler/Open-Transactions/wiki
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
Version: GnuPG v1.4.11 (Darwin)

iQIcBAEBCAAGBQJOj7gfAAoJEAMIAO35UbuOs3sP/2rrjSdYu/AsXcgLK9/9CP4a
lIJfw3KLvybKZjZW5r5j+4xUlCYIqPZSI66PGDChGPMPFcZQN6M4Ddpn9kbctymS
sdTXvbdFhpbV6k2wSa1Fz97ygfXJc/7MDTmHYbZ53hVV8AoUBrCHWtVttkQD31o3
Pn/qGmy+jOgTvjEXhjEpV66pDkMWze1SiI1MArHUziCYoxItuM45x0EfzwQIqlo3
ku2R7rRTtqm47Dgea12psWrjbPS5XRL1Q8Hs38Z1J0JdFlfn6cJYe52Iiluzof6M
kCLhy6FH8QfIADfrKkFP48EIhnVquDlkV9AlJ1r217K3cpK2jEjlZUnGBECMAMEo
pSXXk1BLNgxsa4yaXCgHY92/MhgtcdCMLkcCq6MWUTGZsLGiWIiQGmO9mwBfNIlY
SawlIviuS5DiE/D16A290Byxhha/5e144cIiKm27fSQra8eogUXNfZdZeuv6n69v
t8QjeBjoLhe5/KnRNoGLpSXhPphsWLRSJBru77ZU2msHfmkNfcP2UoEUCfNTfTbE
XpyRfeyRVowVKeKunV9KUSHgdD5wa6RUeyodAbaHvWrFpIpNkaFIP9OwhRULpjx0
arwVNYucbX1qb2I8HBm2u+IRWQTONp74TFFjU0/CVAXu2DeJKY5mL4zDej35c5j9
AK+ZirdWhhoHeWR1tAkN
=RcXP
-----END PGP SIGNATURE-----
 **************************************************************/


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrapper.ui.custom;

import com.wrapper.core.util.Utility;
import com.wrapper.ui.*;
import com.wrapper.ui.model.AccountTableModel;
import com.wrapper.ui.model.AssetContractTableModel;
import com.wrapper.ui.model.BasketTableModel;
import com.wrapper.ui.model.NYMBoxTableModel;
import com.wrapper.ui.model.NYMOutboxTableModel;
import com.wrapper.ui.model.NYMTableModel;
import com.wrapper.ui.model.OTInboxTableModel;
import com.wrapper.ui.model.OTOutboxTableModel;
import com.wrapper.ui.model.ServerContractTableModel;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Vicky C
 */
public class CustomTable extends JTable {

    public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        if (c instanceof JComponent) {
            JComponent jc = (JComponent) c;
            if(getValueAt(rowIndex, vColIndex) instanceof String){
                //Utility.getKeyFromName((String)getValueAt(rowIndex, vColIndex));
                String value = (String)getValueAt(rowIndex, vColIndex);
                if(this.getModel() instanceof AccountTableModel && vColIndex==0){
                    value = (String)this.getModel().getValueAt(rowIndex, 3);
                }
                if(this.getModel() instanceof NYMTableModel && vColIndex==0){
                    value = (String)this.getModel().getValueAt(rowIndex, 1);
                }
                if(this.getModel() instanceof BasketTableModel && vColIndex==0){
                    value = (String)this.getModel().getValueAt(rowIndex, 1);
                }
                if(this.getModel() instanceof AssetContractTableModel && vColIndex==0){
                    value = (String)this.getModel().getValueAt(rowIndex, 1);
                }
                if(this.getModel() instanceof ServerContractTableModel && vColIndex==0){
                    value = (String)this.getModel().getValueAt(rowIndex, 1);
                }


                if(this.getModel() instanceof OTOutboxTableModel){
                    if(vColIndex==4)
                    value = (String)this.getModel().getValueAt(rowIndex, 8);
                    if(vColIndex==5)
                    value = (String)this.getModel().getValueAt(rowIndex, 9);

                }
                if(this.getModel() instanceof OTInboxTableModel){
                    if(vColIndex==4)
                    value = (String)this.getModel().getValueAt(rowIndex, 10);
                    if(vColIndex==5)
                    value = (String)this.getModel().getValueAt(rowIndex, 11);

                }
                if(this.getModel() instanceof NYMOutboxTableModel){
                     if(vColIndex==1)
                    value = (String)this.getModel().getValueAt(rowIndex, 4);
                    if(vColIndex==2)
                    value = (String)this.getModel().getValueAt(rowIndex, 5);
                }
                if(this.getModel() instanceof NYMBoxTableModel){
                    System.out.println("IN NYMBoxTableModel:"+(String)this.getModel().getValueAt(rowIndex, 4));
                    System.out.println("IN NYMBoxTableModel:"+(String)this.getModel().getValueAt(rowIndex, 5));
                    if(vColIndex==1)
                    value = (String)this.getModel().getValueAt(rowIndex, 4);
                    if(vColIndex==2)
                    value = (String)this.getModel().getValueAt(rowIndex, 5);

                }
                jc.setToolTipText(value);
            }

        }
        return c;
    }
}
