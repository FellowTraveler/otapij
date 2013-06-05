/************************************************************
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA1

 *                 M O N E Y C H A N G E R
 *
 *  Open Transactions:
 *       Financial Cryptography and Digital Cash
 *       Library, Protocol, API, Server, CLI, GUI 
 *    
 *       -- Anonymous Numbered Accounts.
 *       -- Untraceable Digital Cash.
 *       -- Triple-Signed Receipts.
 *       -- Cheques, Vouchers, Transfers, Inboxes.
 *       -- Basket Currencies, Markets, Payment Plans.
 *       -- Signed, XML, Ricardian-style Contracts.
 *       -- Scripted smart contracts.
 *    
 *  Copyright (C) 2010-2013 by "Fellow Traveler" (A pseudonym)
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
 *  https://github.com/FellowTraveler/Moneychanger
 *  https://github.com/FellowTraveler/Open-Transactions/wiki
 *
 *  WEBSITE:
 *  http://www.OpenTransactions.org/
 *    
 *  Components and licensing:
 *   -- Moneychanger..A Java client GUI.....LICENSE:.....GPLv3
 *   -- otlib.........A class library.......LICENSE:...LAGPLv3
 *   -- otapi.........A client API..........LICENSE:...LAGPLv3
 *   -- opentxs/ot....Command-line client...LICENSE:...LAGPLv3
 *   -- otserver......Server Application....LICENSE:....AGPLv3
 *  Github.com/FellowTraveler/Open-Transactions/wiki/Components
 *
 *  All of the above OT components were designed and written by
 *  Fellow Traveler, with the exception of Moneychanger, which
 *  was contracted out to Vicky C (bitcointrader4@gmail.com).
 *  The open-source community has since actively contributed.
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
Version: GnuPG v1.4.9 (Darwin)

iQIcBAEBAgAGBQJRSsfMAAoJEAMIAO35UbuOCTQQAJWUR6l+IbUGYPfudumDBZep
XWu5aUVXPt+HTetWobTT3VrSEoQTV+t3Qk10EHzIIQNIvDNkA3cfQod5xMk3CZgm
MuA1PTDZdmrcm1lf92rVULaiB2tHYuPKYryFfkmNcidoWaJAI0ny/AE4gSdopKuU
MCLhB+fWpPv+nK9Qjb8duvRyFjYeLxrnwjfy05zNIY5Fj4HsCzmf6G6xqbUOZzBA
Zc4RjfKeg9MVJl4ObIKhDfpicCTVZkgFPVGeok/KtmiPRgV1HtaHqib4RiN9VMkr
YKbOUOb931pukRJQv+z5fT1EQkSVBDO5Th2q7Nls5idMgtR2BPXxVhs+e8OM4IJK
W+1V0WHWHuE+6SRKQrPU4hAmXrtmGRtu474TTmPlW6dCqFgvLWBuxeTRCPf4l29T
ImEOxdjFwlMVoxsazE3KE/YMbX7IiZqgLx/C4OTPlz7BVLhphzPRXA7KhhRFi1Df
jie5oRhJ4zbTFQI8SKsjbx0H/4VpB+Vtjx6fOxnLUpjZAE7G6ZL2zOEK8rtiGeiH
0AWK2rKWP8oOMnQwBMP838WRtxFmaOIhvVqAngjynSmVouf1RKKU7y7/YQ0iVDcN
WqAhaZhvszQ6UCDAEi11rfdC2qt29Jds9lS41YGqvYaan+b50lr5u59Uknz2LC94
HS4/gWtdVEVnXDda0wk9
=CbRB
-----END PGP SIGNATURE-----
 **************************************************************/


package com.moneychanger.core;

import com.moneychanger.core.util.Configuration;
import com.moneychanger.core.util.Helpers;
import org.opentransactions.otapi.RippleServer;
import org.opentransactions.otapi.WalletData;

/**
 *
 * @author waqqas
 */
public class RippleAccount extends Account {

    @Override
    public boolean createAccount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAccount(String accountID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void editAccount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void loadAccount(String assetID, String serverID, String nymID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getAccountDetails(String accountID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean editLabel(String accountID, String newLabel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] loadServerDetails(String serverID){

        String[] details = null;
        WalletData walletData = Helpers.getWalletData();
        if (walletData == null) {
            System.out.println("loadServerDetails - walletData returns null");
            return null;
        }
        for (int i = 0; i < walletData.GetRippleServerCount(); i++) {
            RippleServer rippleServer = walletData.GetRippleServer(i);
            if (rippleServer == null) {
                continue;
            }
            System.out.println("serverID:" + serverID + " rippleServer.getServer_id():" + rippleServer.getServer_id());
            if (serverID.equals(rippleServer.getServer_id())) {
                details = new String[5];
                details[0] = rippleServer.getServer_host()==null?"":rippleServer.getServer_host();
                details[1] = rippleServer.getNamefield_id()==null || rippleServer.getNamefield_id().trim().length()<1 ?Configuration.getRippleUsernameID():rippleServer.getNamefield_id();
                details[2] = rippleServer.getPassfield_id()==null || rippleServer.getPassfield_id().trim().length()<1 ?Configuration.getRipplePasswordID():rippleServer.getPassfield_id();
                details[3] = rippleServer.getRipple_username()==null?"":rippleServer.getRipple_username();
                details[4] = rippleServer.getRipple_password()==null?"":rippleServer.getRipple_password();
                break;
            }
        }

        return details;
    }
}
