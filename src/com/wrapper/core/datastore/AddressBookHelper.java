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
package com.wrapper.core.datastore;

import com.wrapper.core.dataobjects.ContactDetails;
import com.wrapper.core.jni.AddressBook;
import com.wrapper.core.jni.Contact;
import com.wrapper.core.jni.ContactAcct;
import com.wrapper.core.jni.ContactNym;
import com.wrapper.core.jni.ServerInfo;
import com.wrapper.core.jni.Storable;
import com.wrapper.core.jni.StoredObjectType;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Utility;
import java.util.ArrayList;
import java.util.List;

public class AddressBookHelper {

    public static String createContact(String name, String email, String publicKey, String memo) {

        String contactID = "error";

        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("createContact - addressBook returns null");
            return contactID;
        }
        Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_CONTACT_ACCT);
        if (storable != null) {
            Contact contact = Contact.ot_dynamic_cast(storable);
            if (contact != null) {
                boolean status = addressBook.AddContact(contact);
                System.out.println("status addressBook.AddContact:" + status);
                if (!status) {
                    return contactID;
                }
                status = otapi.StoreObject(addressBook, "moneychanger", "gui_contacts.dat");
                System.out.println("status addressBook otapi.StoreObject:" + status);
                if (!status) {
                    return contactID;
                }
                contactID = Utility.generateID();
            }
        }
        return contactID;
    }

    public static boolean createContactNym(int index, String contactID, String label, String nymID, String nymType, String[] serverID, String[] serverType, String publickey, String memo) {

        boolean status = false;
        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("createContactNym - addressBook returns null");
            return false;
        }
        int count = (int) addressBook.GetContactCount();
        for (int i = 0; i < count; i++) {
            Contact contact = addressBook.GetContact(i);
            if (contact == null) {
                continue;
            }
            if (contactID.equals(contact.getContact_id())) {
                System.out.println("createContactNym - contactID matches");
                ContactNym contactNYM = null;
                if (index == -1) {
                    Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_CONTACT_NYM);
                    if (storable != null) {
                        contactNYM = ContactNym.ot_dynamic_cast(storable);
                    }
                } else {
                    contactNYM = contact.GetContactNym(index);
                }
                if (contactNYM != null) {
                    contactNYM.setGui_label(label);
                    contactNYM.setMemo(memo);
                    contactNYM.setNym_id(nymID);
                    contactNYM.setNym_type(nymType);
                    contactNYM.setPublic_key(publickey);
                    while (contactNYM.GetServerInfoCount() > 0) {
                        contactNYM.RemoveServerInfo(0);
                    }

                    for (int j = 0; j < serverID.length; j++) {
                        Storable storable1 = otapi.CreateObject(StoredObjectType.STORED_OBJ_SERVER_INFO);
                        if (storable1 != null) {
                            ServerInfo serverInfo = ServerInfo.ot_dynamic_cast(storable1);
                            if (serverInfo != null) {
                                serverInfo.setServer_id(serverID[j]);
                                serverInfo.setServer_type(serverType[j]);
                                contactNYM.AddServerInfo(serverInfo);

                            }
                        }
                    }

                    contact.AddContactNym(contactNYM);
                    status = otapi.StoreObject(addressBook, "moneychanger", "gui_contacts.dat");
                    System.out.println("createContactNym status addressBook otapi.StoreObject:" + status);
                    // Set other values here
                }

                break;
            }
        }
        return status;
    }

    public static boolean createContactAccount(int index, String contactID, String label, String nymID, String acctID, String assetID, String serverID, String serverType, String publickey, String memo) {

        boolean status = false;
        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("createContactAccount - addressBook returns null");
            return false;
        }
        int count = (int) addressBook.GetContactCount();
        for (int i = 0; i < count; i++) {
            Contact contact = addressBook.GetContact(i);
            if (contact == null) {
                continue;
            }
            if (contactID.equals(contact.getContact_id())) {
                System.out.println("createContactAccount - contactID matches");
                ContactAcct contactAcct = null;
                if (index == -1) {
                    Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_CONTACT_ACCT);
                    if (storable != null) {
                        contactAcct = ContactAcct.ot_dynamic_cast(storable);
                    }
                } else {
                    contactAcct = contact.GetContactAcct(index);
                }
                if (contactAcct != null) {
                    contactAcct.setGui_label(label);
                    contactAcct.setMemo(memo);
                    contactAcct.setNym_id(nymID);
                    contactAcct.setAcct_id(acctID);
                    contactAcct.setAsset_type_id(assetID);
                    contactAcct.setPublic_key(publickey);
                    contactAcct.setServer_id(serverID);
                    contactAcct.setServer_type(serverType);
                    contact.AddContactAcct(contactAcct);
                    status = otapi.StoreObject(addressBook, "moneychanger", "gui_contacts.dat");
                    System.out.println("createContactAccount status addressBook otapi.StoreObject:" + status);
                    // Set other values here
                }

                break;
            }
        }
        return status;
    }

    public static boolean removeContact(String contactID) {
        boolean status = false;
        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("removeContact - addressBook returns null");
            return false;
        }
        for (int i = 0; i < addressBook.GetContactCount(); i++) {
            Contact contact = addressBook.GetContact(i);
            if (contact == null) {
                continue;
            }
            if (contactID.equals(contact.getContact_id())) {
                status = addressBook.RemoveContact(i);
                System.out.println("removeContact status addressBook otapi.RemoveContact:" + status);
                if (status) {
                    status = otapi.StoreObject(addressBook, "moneychanger", "gui_contacts.dat");
                }
                System.out.println("removeContact status addressBook otapi.StoreObject:" + status);
                break;
            }
        }
        return status;
    }

    public static boolean removeContactNym(String contactID,int index){
        boolean status = false;
        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("removeContactNym - addressBook returns null");
            return false;
        }
        for(int i=0;i<addressBook.GetContactCount();i++){
            Contact contact = addressBook.GetContact(i);
            if(contact==null)
                continue;
            if(contactID.equals(contact.getContact_id())){
                status = contact.RemoveContactNym(index);
                System.out.println("removeContactNym status contact.RemoveContactNym:" + status);
                if(status)
                    status = otapi.StoreObject(addressBook, "moneychanger", "gui_contacts.dat");
                System.out.println("removeContactNym status addressBook otapi.StoreObject:" + status);
                
                break;
            }
        }
        return status;
    }

    public static boolean removeContactAcct(String contactID,int index){

        boolean status = false;
        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("removeContactAcct - addressBook returns null");
            return false;
        }
        for(int i=0;i<addressBook.GetContactCount();i++){
            Contact contact = addressBook.GetContact(i);
            if(contact==null)
                continue;
            if(contactID.equals(contact.getContact_id())){
                status = contact.RemoveContactAcct(index);
                System.out.println("removeContactAcct status contact.RemoveContactAcct:" + status);
                
                if(status)
                    status = otapi.StoreObject(addressBook, "moneychanger", "gui_contacts.dat");
                System.out.println("removeContactAcct status addressBook otapi.StoreObject:" + status);

                break;
            }
        }
        return status;
    }

    public static List getContactList(){

        List contactList = new ArrayList();
        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("getContactList - addressBook returns null");
            return null;
        }
        for(int i=0;i<addressBook.GetContactCount();i++){
            Contact contact = addressBook.GetContact(i);
            if(contact==null)
                continue;

            String [] row = new String[2];

            row[0] = contact.getGui_label()==null?"":contact.getGui_label();
            row[1] = contact.getContact_id()==null?"":contact.getContact_id();
            contactList.add(row);
        }

        return contactList;
    }

    public static ContactDetails getContactDetails(String contactID){
        ContactDetails contactDetails = new ContactDetails();
        
        return null;
    }
}
