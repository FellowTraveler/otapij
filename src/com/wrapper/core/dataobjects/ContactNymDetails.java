/*
*To change this template, choose Tools | Templates
*and open the template in the editor.
 */

package com.wrapper.core.dataobjects;

import java.util.List;

public class ContactNymDetails {

private String label;
private String nymType;
private List serverList;
private String nymID;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getNymID() {
        return nymID;
    }

    public void setNymID(String nymID) {
        this.nymID = nymID;
    }

    public String getNymType() {
        return nymType;
    }

    public void setNymType(String nymType) {
        this.nymType = nymType;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public List getServerList() {
        return serverList;
    }

    public void setServerList(List serverList) {
        this.serverList = serverList;
    }
private String memo;
private String publicKey;
}
