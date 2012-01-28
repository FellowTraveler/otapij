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

package com.wrapper.core.datastore;

import com.wrapper.core.dataobjects.ContactAcctDetails;
import com.wrapper.core.dataobjects.ContactDetails;
import com.wrapper.core.dataobjects.ContactNymDetails;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookHelper {

    public static String createContact(String name, String email, String publicKey, String memo) {

        String contactID = "error";
        System.out.println("in createContact");
        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("createContact - addressBook returns null");
            return contactID;
        }
        System.out.println("in createContact,addressBook:"+addressBook);
        Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_CONTACT);
        System.out.println("in createContact, storable:"+storable);
        if (storable != null) {
            Contact contact = Contact.ot_dynamic_cast(storable);
            System.out.println("contact:"+contact);
            if (contact != null) {
                contact.setContact_id(Utility.generateID());
                contact.setEmail(email);
                contact.setGui_label(name);
                contact.setMemo(memo);
                contact.setPublic_key(publicKey);
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
                                System.out.println("serverType[j]:"+serverType[j]);
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
        System.out.println("createContactAccount contactID:"+contactID);
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
                System.out.println("createContactAccount - contactID matches, index="+index);
                ContactAcct contactAcct = null;
                if (index == -1) {
                    System.out.println("createContactAccount new obj");
                    Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_CONTACT_ACCT);
                    if (storable != null) {
                        contactAcct = ContactAcct.ot_dynamic_cast(storable);
                    }
                } else {
                    contactAcct = contact.GetContactAcct(index);
                    System.out.println("createContactAccount old obj, contactAcct :"+contactAcct);
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
            System.out.println("contact.getGui_label():"+contact.getGui_label());
            row[1] = contact.getContact_id()==null?"":contact.getContact_id();
            contactList.add(row);
        }

        return contactList;
    }

    public static ContactDetails getContactDetails(String contactID,int mode){
        ContactDetails contactDetails = null;
        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("getContactDetails - addressBook returns null");
            return null;
        }
        for(int i=0;i<addressBook.GetContactCount();i++){
            Contact contact = addressBook.GetContact(i);
            if(contact==null)
                continue;
            if(contactID.equals(contact.getContact_id())){
                contactDetails = new ContactDetails();
                contactDetails.setEmail(contact.getEmail());
                contactDetails.setLabel(contact.getGui_label());
                contactDetails.setPublicKey(contact.getPublic_key());
                contactDetails.setMemo(contact.getMemo());
                contactDetails.setContact(contact);
                List data = new ArrayList();
                System.out.println("mode:"+mode+" contact.GetContactAcctCount():"+contact.GetContactAcctCount());
                if(mode==1){
                    for(int j=0;j<contact.GetContactAcctCount();j++){
                        if(contact.GetContactAcct(j)==null)
                            continue;
                        
                        data.add(new String[]{contact.GetContactAcct(j).getGui_label()});
                    }
                    contactDetails.setContactAccts(data);
                }else{
                    for(int j=0;j<contact.GetContactNymCount();j++){
                        if(contact.GetContactNym(j)==null)
                            continue;
                        data.add(new String[]{contact.GetContactNym(j).getGui_label()});
                    }
                    contactDetails.setContactNyms(data);
                }
                break;
            }
        }
        return contactDetails;
    }

    public static ContactNymDetails getContactNymDetails(Contact contact,int index){

            ContactNymDetails data = new ContactNymDetails();
            List serverList = new ArrayList();
            if(contact!=null && index>-1){
            ContactNym contactNYM = contact.GetContactNym(index);
            if(contactNYM==null){
                System.out.println("getContactNymDetails contact.GetContactNym(index) returned null");
                return null;
            }
            data.setLabel(contactNYM.getGui_label());
            data.setMemo(contactNYM.getMemo());
            data.setNymID(contactNYM.getNym_id());
            data.setNymType(contactNYM.getNym_type());
            data.setPublicKey(contactNYM.getPublic_key());

            for(int i=0;i<contactNYM.GetServerInfoCount();i++){
                String [] servers = new String[2];
                if(contactNYM.GetServerInfo(i)==null)
                    continue;
                servers [0] = contactNYM.GetServerInfo(i).getServer_id();
                servers [0] = contactNYM.GetServerInfo(i).getServer_type();
                serverList.add(servers);
            }
            data.setServerList(serverList);
        }
        
        return data;
    }

    public static ContactAcctDetails getContactAccDetails(Contact contact,int index){
        AddressBook addressBook = Utility.getAddressBook();
        if (addressBook == null) {
            System.out.println("getContactAccDetails - addressBook returns null");
            return null;
        }

        for(int i=0;i<addressBook.GetContactCount();i++){
            Contact newContact = addressBook.GetContact(i);
            if(contact==null)
                continue;
            if(newContact.getContact_id().equals(contact.getContact_id())){
                contact = newContact;
                break;
            }
        }
        ContactAcctDetails data = new ContactAcctDetails();
           if(contact!=null && index>-1){
            ContactAcct contactAcct = contact.GetContactAcct(index);
            if(contactAcct==null){
                System.out.println("getContactNymDetails contact.getContactAccDetails(index) returned null");
                return null;
            }
            data.setLabel(contactAcct.getGui_label());
            data.setMemo(contactAcct.getMemo());
            data.setNymID(contactAcct.getNym_id());
            data.setAcctID(contactAcct.getAcct_id());
            data.setAssetID(contactAcct.getAsset_type_id());
            data.setServerID(contactAcct.getServer_id());
            data.setServerType(contactAcct.getServer_type());
            data.setPublicKey(contactAcct.getPublic_key());

        }
        return data;
    }
}
