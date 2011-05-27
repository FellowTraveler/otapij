/*
 *To change this template, choose Tools | Templates
 *and open the template in the editor.
 */
package com.wrapper.core.dataobjects;

import java.util.Map;

/**
 *
 * @author waqqas
 */
public class BitcoinDetails {

    private String name;
    String serverID;
    String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public Map getReceivedboxData() {
        return receivedboxData;
    }

    public void setReceivedboxData(Map receivedboxData) {
        this.receivedboxData = receivedboxData;
    }

    public Map getSentboxData() {
        return sentboxData;
    }

    public void setSentboxData(Map sentboxData) {
        this.sentboxData = sentboxData;
    }
    private int currentBlockNumber;
    private Map sentboxData;
    private Map receivedboxData;
    private Map receivedAddrboxData;

    public Map getReceivedAddrboxData() {
        return receivedAddrboxData;
    }

    public void setReceivedAddrboxData(Map receivedAddrboxData) {
        this.receivedAddrboxData = receivedAddrboxData;
    }

    public int getCurrentBlockNumber() {
        return currentBlockNumber;
    }

    public void setCurrentBlockNumber(int currentBlockNumber) {
        this.currentBlockNumber = currentBlockNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String address;
    private String balance;
    private String account;
}
