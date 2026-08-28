package com.moneychanger.core.accounts;

import com.moneychanger.core.Account;
import org.opentransactions.otapi.otapiJNI;

public class CashPurseAccount extends Account {

    public CashPurseAccount() {
        type = "CashPurseAccount";
    }

    @Override
    public boolean createAccount() {
        return false;
    }

    @Override
    public boolean deleteAccount(String accountID) throws Exception {
        return false;
    }

    @Override
    public void editAccount() {
    }

    @Override
    public void loadAccount(String assetID, String serverID, String nymID) throws Exception {
    }

    @Override
    public Object getAccountDetails(String accountID) {
        return null;
    }

    @Override
    public boolean editLabel(String accountID, String newLabel) {
        return false;
    }

    public String getInboxOutboxAccount(String accountID) {
        if (accountID == null || accountID.isEmpty()) {
            System.out.println("getInboxOutboxAccount: invalid accountID: " + accountID);
            return "";
        }
        boolean exists = otapiJNI.Exists("inboxoutbox", "moneychanger", accountID, "");
        if (!exists) {
            return "";
        }
        return accountID;
    }
}
