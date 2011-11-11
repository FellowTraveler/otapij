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

package com.wrapper.core;

import com.wrapper.core.dataobjects.BitcoinDetails;
import com.wrapper.core.helper.bitcoin.BitcoinClient;
import com.wrapper.core.helper.bitcoin.TransactionInfo;
import com.wrapper.core.jni.BitcoinAcct;
import com.wrapper.core.jni.BitcoinServer;
import com.wrapper.core.jni.Storable;
import com.wrapper.core.jni.StoredObjectType;
import com.wrapper.core.jni.WalletData;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Utility;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.paradoxs.bitcoin.client.ValidatedAddressInfo;
import ru.paradoxs.bitcoin.client.exceptions.BitcoinClientException;

public class BitcoinAccount extends Account {

    BitcoinClient btcClient;
    HashMap btcClientMap = new HashMap();
    String serverID;

    /**
     * Warning: The call to setServerID(serverID) is mandatory if default constructor is used
     */
    public BitcoinAccount() {
        type = "BitcoinAccount";
    }

    public BitcoinAccount(String serverID) {
        type = "BitcoinAccount";
        setBTCClient(serverID);
    }

    private void setBTCClient(String serverID) {
        try {
            this.serverID = serverID;

            if (!btcClientMap.containsKey(serverID)) {
                // get the details from bitcoin object from datastore
                WalletData walletData = Utility.getWalletData();
                if (walletData == null) {
                    System.out.println("setBTCClient - walletData returns null");
                    return;
                }
                for (int i = 0; i < walletData.GetBitcoinServerCount(); i++) {
                    BitcoinServer btcServer = walletData.GetBitcoinServer(i);
                    if (btcServer == null) {
                        continue;
                    }
                    System.out.println("serverID:" + serverID + " btcServer.getServer_id():" + btcServer.getServer_id());
                    if (serverID.equals(btcServer.getServer_id())) {
                        int port = 8332;
                        try {
                            port = Integer.parseInt(btcServer.getServer_port());
                        } catch (NumberFormatException nfe) {
                        }
                        System.out.println("btcServer.getServer_host():" + btcServer.getServer_host() + " btcServer.getBitcoin_username():" + btcServer.getBitcoin_username() + " btcServer.getBitcoin_password():" + btcServer.getBitcoin_password() + " port:" + port);
                        //btcClient = new BitcoinClient(Configuration.getHostBitcoin(), Configuration.getUserBitcoin(), Configuration.getPwdBitcoin(), Configuration.getPortBitcoin());
                        btcClient = new BitcoinClient(btcServer.getServer_host(), btcServer.getBitcoin_username(), btcServer.getBitcoin_password(), port);
                        btcClientMap.put(serverID, btcClient);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public boolean createAccount() {
        return false;
    }

    @Override
    public boolean deleteAccount(String accountID) {
        return false;
    }

    @Override
    public void editAccount() {
    }

    public void getBitcoinAccountList() throws Exception {
        System.out.println("getBitcoinAccountList strts ");
        btcClient = (BitcoinClient) btcClientMap.get(serverID);

        List accountsList = btcClient.getAccountList();

        for (int j = 0; j < accountsList.size(); j++) {

            String data[] = (String[]) accountsList.get(j);
            WalletData walletData = Utility.getWalletData();
            if (walletData == null) {
                System.out.println("getBitcoinAccountList - walletData returns null");
                return;
            }
            int count = (int) walletData.GetBitcoinAcctCount();
            BitcoinAcct btcAcct = null;
            boolean acctExists = false;

            for (int i = 0; i < count; i++) {
                acctExists = false;
                btcAcct = walletData.GetBitcoinAcct(i);
                if (btcAcct != null && btcAcct.getAcct_id().equals(data[0])) {
                    acctExists = true;
                    break;
                }
            }
            if (!acctExists) {
                Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_BITCOIN_ACCT);
                if (storable != null) {
                    btcAcct = BitcoinAcct.ot_dynamic_cast(storable);
                    if (btcAcct != null) {
                        btcAcct.setGui_label(data[0]);
                        btcAcct.setBitcoin_acct_name(data[0]);
                        btcAcct.setAcct_id(data[0]);
                        btcAcct.setServer_id(serverID);

                        boolean status = walletData.AddBitcoinAcct(btcAcct);
                        System.out.println("status walletData.AddBitcoinAcct:" + status);

                        status = otapi.StoreObject(walletData, "moneychanger", "gui_wallet.dat");

                        System.out.println("status otapi.StoreObject:" + status);
                    }
                }
            }
            if (btcAcct != null) {
                key = btcAcct.getAcct_id();
                label = btcAcct.getGui_label();
                amount = data[1];
                accountList.put(key, new String[]{label, amount, type, key});
            }

        }
        System.out.println("getBitcoinAccountList ends ");

    }

    @Override
    public void loadAccount(String assetID, String serverID, String nymID) throws Exception {
        //accountList.put("Bitcoin1", new String[] {"bitcoinlabel1","2000","BitcoinAccount","Bitcoin1"});
        System.out.println("In Bit load");
        getBitcoinAccountList();
    }

    @Override
    public void setServerID(String serverID) {
        setBTCClient(serverID);
    }

    @Override
    public Object getAccountDetails(String accountID) {
        System.out.println(" getAccountDetails - Starts ");
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        BitcoinDetails bitcoinDetails = new BitcoinDetails();

        bitcoinDetails.setAccount(accountID);
        bitcoinDetails.setServerID(serverID);
        System.out.println(" getLabel - " + bitcoinDetails.getLabel());
        btcClient.getServerInfo();

        bitcoinDetails.setAddress(btcClient.getAccountAddress(accountID));

        bitcoinDetails.setName(btcClient.getLabel(bitcoinDetails.getAddress()));

        bitcoinDetails.setBalance(String.valueOf(btcClient.getBalance()));

        bitcoinDetails.setCurrentBlockNumber(btcClient.getBlockNumber());

        bitcoinDetails.setSentboxData(getSentBoxData(accountID));
        bitcoinDetails.setReceivedboxData(getReceivedBoxData(accountID));
        bitcoinDetails.setReceivedAddrboxData(getRecevingAddrBoxData(accountID));
        System.out.println("Block Count:" + btcClient.getBlockCount() + " btcClient.getBlockNumber():" + btcClient.getBlockNumber());
        return bitcoinDetails;
    }

    @Override
    public boolean editLabel(String accountID, String newLabel) {
        //Loop
        boolean status = false;
        WalletData walletData = Utility.getWalletData();
        if (walletData == null) {
            System.out.println("editLabel - walletData returns null");
            return false;
        }
        for (int i = 0; i < walletData.GetBitcoinAcctCount(); i++) {
            BitcoinAcct btcAcct = walletData.GetBitcoinAcct(i);
            if(btcAcct==null)
                continue;
            if (accountID.equals(btcAcct.getAcct_id())) {
                btcAcct.setGui_label(newLabel);
                status = otapi.StoreObject(walletData, "moneychanger", "gui_wallet.dat");
                System.out.println("Edit label status otapi.StoreObject:" + status);
                break;
            }
        }
        return status;
    }

    public double checkBalance(String account) {
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        System.out.println("bal:" + btcClient.getBalance());
        return btcClient.getBalance();
    }

    public String createNewAddress(String label, String account) {
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        String newAddress = btcClient.getNewAddress(label);
        System.out.println("newAddress:" + newAddress);
        if (newAddress != null) {
            btcClient.setAccountForAddress(newAddress, account);
        }
        return newAddress;

    }

    public void setLabelForAddress(String address, String newLabel) {
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        btcClient.setLabelForAddress(address, newLabel);
    }

    public String sendFunds(String toAddress, double amount, String comment, String commentTo) {
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        try {
            String result = btcClient.sendToAddress(toAddress, amount, comment, commentTo);
            System.out.println("result:" + result);

        } catch (BitcoinClientException btcException) {
            return btcException.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Success";
    }

    public boolean validateAddress(String address) {
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        ValidatedAddressInfo addressInfo = btcClient.validateAddress(address);
        return addressInfo.getIsValid();
    }

    public boolean isMineAddress(String address) {
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        ValidatedAddressInfo addressInfo = btcClient.validateAddress(address);
        return addressInfo.getIsMine();
    }

    public String getBlockNumber() {
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        return String.valueOf(btcClient.getBlockNumber());
    }

    public Map getRecevingAddrBoxData(String account) {

        HashMap data = new HashMap();
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        List listOfAddress = btcClient.getAddressesByAccount(account);

        for (int i = 0; i < listOfAddress.size(); i++) {
            String address = (String) listOfAddress.get(i);
            boolean addAddress = true;
            double amount = 0.0;
            List list = btcClient.listTransactions(account);
            for (int j = 0; j < list.size(); j++) {
                TransactionInfo info = (TransactionInfo) list.get(j);

                if (address != null && address.equals(info.getAddress()) && "send".equalsIgnoreCase(info.getCategory())) {
                    addAddress = false;
                    break;
                } else if (address != null && address.equals(info.getAddress()) && "receive".equalsIgnoreCase(info.getCategory())) {
                    amount += info.getAmount();
                }
            }
            if (addAddress) {

                String[] row = new String[3];

                row[0] = btcClient.getLabel(address);
                row[1] = address;
                row[2] = String.valueOf(Utility.roundTwoDecimals(amount));

                data.put(row[1], row);
            }

        }
        return data;
    }

    public Map getSentBoxData(String account) {
        HashMap data = new HashMap();
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        List listOfTransactions = btcClient.listTransactions(account);
        System.out.println(" listOfTransactions.size(): " + listOfTransactions.size());
        for (int i = 0; i < listOfTransactions.size(); i++) {
            TransactionInfo trnInfo = (TransactionInfo) listOfTransactions.get(i);
            /*System.out.println(" ------------------------------------ " + trnInfo);
            System.out.println("confirm:" + trnInfo.getConfirmations());
            System.out.println("amout:" + trnInfo.getAmount());
            System.out.println("Address:" + trnInfo.getAddress());
            System.out.println("mess:" + trnInfo.getMessage());
            System.out.println("cat:" + trnInfo.getCategory());
            System.out.println("other acct:" + trnInfo.getOtherAccount());
            System.out.println("fees:" + trnInfo.getFee());
            System.out.println("ID:" + trnInfo.getTxId());
            System.out.println("Date:" + trnInfo.getTimestamp());
            System.out.println("Formatted Date:" + new Date(trnInfo.getTimestamp() * 1000));*/

            if ("send".equalsIgnoreCase(trnInfo.getCategory())) {
                String[] row = new String[6];
                row[0] = String.valueOf(trnInfo.getConfirmations() + " Confirmations");
                row[1] = String.valueOf(new Date(trnInfo.getTimestamp() * 1000));
                row[2] = String.valueOf(trnInfo.getAmount() * -1);
                row[3] = String.valueOf(trnInfo.getAddress());
                row[4] = trnInfo.getMessage() == null ? "" : trnInfo.getMessage();
                row[5] = String.valueOf(trnInfo.getTxId());

                data.put(row[5], row);
            }
        }
        return data;
    }

    public Map getReceivedBoxData(String account) {
        HashMap data = new HashMap();
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        /*List accountsList = btcClient.listReceivedByAccount(0, true);
        for (int j = 0; j < accountsList.size(); j++) {
        String accounts = ((AccountInfo) accountsList.get(j)).getAccount();*/
        List listOfTransactions = btcClient.listTransactions(account);
        System.out.println(" listOfTransactions.size(): " + listOfTransactions.size());
        for (int i = 0; i < listOfTransactions.size(); i++) {
            TransactionInfo trnInfo = (TransactionInfo) listOfTransactions.get(i);
            /*System.out.println(" ------------------------------------ " + trnInfo);
            System.out.println("confirm:" + trnInfo.getConfirmations());
            System.out.println("amout:" + trnInfo.getAmount());
            System.out.println("Address:" + trnInfo.getAddress());
            System.out.println("mess:" + trnInfo.getMessage());
            System.out.println("cat:" + trnInfo.getCategory());
            System.out.println("other acct:" + trnInfo.getOtherAccount());
            System.out.println("fees:" + trnInfo.getFee());
            System.out.println("ID:" + trnInfo.getTxId());
            System.out.println("Date:" + trnInfo.getTimestamp());
            System.out.println("Formatted Date:" + new Date(trnInfo.getTimestamp() * 1000));*/

            if ("receive".equalsIgnoreCase(trnInfo.getCategory())) {
                String[] row = new String[6];
                row[0] = String.valueOf(trnInfo.getConfirmations() + " Confirmations");
                row[1] = String.valueOf(new Date(trnInfo.getTimestamp() * 1000));
                row[2] = String.valueOf(trnInfo.getAmount());
                row[3] = String.valueOf(trnInfo.getAddress());
                row[4] = trnInfo.getMessage() == null ? "" : trnInfo.getMessage();
                row[5] = String.valueOf(trnInfo.getTxId());

                data.put(row[5], row);
            }
        }
        //}
        return data;
    }

    public String getLabel(String address) {
        btcClient = (BitcoinClient) btcClientMap.get(serverID);
        return btcClient.getLabel(address);
    }
}
