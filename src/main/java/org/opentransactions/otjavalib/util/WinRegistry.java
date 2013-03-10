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


package org.opentransactions.otjavalib.util;


// Copy From http://stackoverflow.com/questions/62289/read-write-to-windows-registry-using-java


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

public class WinRegistry {
  public static final int HKEY_CURRENT_USER = 0x80000001;
  public static final int HKEY_LOCAL_MACHINE = 0x80000002;
  public static final int REG_SUCCESS = 0;
  public static final int REG_NOTFOUND = 2;
  public static final int REG_ACCESSDENIED = 5;

  private static final int KEY_ALL_ACCESS = 0xf003f;
  private static final int KEY_READ = 0x20019;
  private static Preferences userRoot = Preferences.userRoot();
  private static Preferences systemRoot = Preferences.systemRoot();
  private static Class<? extends Preferences> userClass = userRoot.getClass();
  private static Method regOpenKey = null;
  private static Method regCloseKey = null;
  private static Method regQueryValueEx = null;
  private static Method regEnumValue = null;
  private static Method regQueryInfoKey = null;
  private static Method regEnumKeyEx = null;
  private static Method regCreateKeyEx = null;
  private static Method regSetValueEx = null;
  private static Method regDeleteKey = null;
  private static Method regDeleteValue = null;

  static {
    try {
      regOpenKey = userClass.getDeclaredMethod("WindowsRegOpenKey",
          new Class[] { int.class, byte[].class, int.class });
      regOpenKey.setAccessible(true);
      regCloseKey = userClass.getDeclaredMethod("WindowsRegCloseKey",
          new Class[] { int.class });
      regCloseKey.setAccessible(true);
      regQueryValueEx = userClass.getDeclaredMethod("WindowsRegQueryValueEx",
          new Class[] { int.class, byte[].class });
      regQueryValueEx.setAccessible(true);
      regEnumValue = userClass.getDeclaredMethod("WindowsRegEnumValue",
          new Class[] { int.class, int.class, int.class });
      regEnumValue.setAccessible(true);
      regQueryInfoKey = userClass.getDeclaredMethod("WindowsRegQueryInfoKey1",
          new Class[] { int.class });
      regQueryInfoKey.setAccessible(true);
      regEnumKeyEx = userClass.getDeclaredMethod(  
          "WindowsRegEnumKeyEx", new Class[] { int.class, int.class,  
              int.class });  
      regEnumKeyEx.setAccessible(true);
      regCreateKeyEx = userClass.getDeclaredMethod(  
          "WindowsRegCreateKeyEx", new Class[] { int.class,  
              byte[].class });  
      regCreateKeyEx.setAccessible(true);  
      regSetValueEx = userClass.getDeclaredMethod(  
          "WindowsRegSetValueEx", new Class[] { int.class,  
              byte[].class, byte[].class });  
      regSetValueEx.setAccessible(true); 
      regDeleteValue = userClass.getDeclaredMethod(  
          "WindowsRegDeleteValue", new Class[] { int.class,  
              byte[].class });  
      regDeleteValue.setAccessible(true); 
      regDeleteKey = userClass.getDeclaredMethod(  
          "WindowsRegDeleteKey", new Class[] { int.class,  
              byte[].class });  
      regDeleteKey.setAccessible(true); 
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private WinRegistry() {  }

  /**
   * Read a value from key and value name
   * @param hkey   HKEY_CURRENT_USER/HKEY_LOCAL_MACHINE
   * @param key
   * @param valueName
   * @return the value
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static String readString(int hkey, String key, String valueName) 
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    if (hkey == HKEY_LOCAL_MACHINE) {
      return readString(systemRoot, hkey, key, valueName);
    }
    else if (hkey == HKEY_CURRENT_USER) {
      return readString(userRoot, hkey, key, valueName);
    }
    else {
      throw new IllegalArgumentException("hkey=" + hkey);
    }
  }

  /**
   * Read value(s) and value name(s) form given key 
   * @param hkey  HKEY_CURRENT_USER/HKEY_LOCAL_MACHINE
   * @param key
   * @return the value name(s) plus the value(s)
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static Map<String, String> readStringValues(int hkey, String key) 
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    if (hkey == HKEY_LOCAL_MACHINE) {
      return readStringValues(systemRoot, hkey, key);
    }
    else if (hkey == HKEY_CURRENT_USER) {
      return readStringValues(userRoot, hkey, key);
    }
    else {
      throw new IllegalArgumentException("hkey=" + hkey);
    }
  }

  /**
   * Read the value name(s) from a given key
   * @param hkey  HKEY_CURRENT_USER/HKEY_LOCAL_MACHINE
   * @param key
   * @return the value name(s)
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static List<String> readStringSubKeys(int hkey, String key) 
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    if (hkey == HKEY_LOCAL_MACHINE) {
      return readStringSubKeys(systemRoot, hkey, key);
    }
    else if (hkey == HKEY_CURRENT_USER) {
      return readStringSubKeys(userRoot, hkey, key);
    }
    else {
      throw new IllegalArgumentException("hkey=" + hkey);
    }
  }

  /**
   * Create a key
   * @param hkey  HKEY_CURRENT_USER/HKEY_LOCAL_MACHINE
   * @param key
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static void createKey(int hkey, String key) 
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    int [] ret;
    if (hkey == HKEY_LOCAL_MACHINE) {
      ret = createKey(systemRoot, hkey, key);
      regCloseKey.invoke(systemRoot, new Object[] { new Integer(ret[0]) });
    }
    else if (hkey == HKEY_CURRENT_USER) {
      ret = createKey(userRoot, hkey, key);
      regCloseKey.invoke(userRoot, new Object[] { new Integer(ret[0]) });
    }
    else {
      throw new IllegalArgumentException("hkey=" + hkey);
    }
    if (ret[1] != REG_SUCCESS) {
      throw new IllegalArgumentException("rc=" + ret[1] + "  key=" + key);
    }
  }

  /**
   * Write a value in a given key/value name
   * @param hkey
   * @param key
   * @param valueName
   * @param value
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static void writeStringValue
    (int hkey, String key, String valueName, String value) 
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    if (hkey == HKEY_LOCAL_MACHINE) {
      writeStringValue(systemRoot, hkey, key, valueName, value);
    }
    else if (hkey == HKEY_CURRENT_USER) {
      writeStringValue(userRoot, hkey, key, valueName, value);
    }
    else {
      throw new IllegalArgumentException("hkey=" + hkey);
    }
  }

  /**
   * Delete a given key
   * @param hkey
   * @param key
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static void deleteKey(int hkey, String key) 
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    int rc = -1;
    if (hkey == HKEY_LOCAL_MACHINE) {
      rc = deleteKey(systemRoot, hkey, key);
    }
    else if (hkey == HKEY_CURRENT_USER) {
      rc = deleteKey(userRoot, hkey, key);
    }
    if (rc != REG_SUCCESS) {
      throw new IllegalArgumentException("rc=" + rc + "  key=" + key);
    }
  }

  /**
   * delete a value from a given key/value name
   * @param hkey
   * @param key
   * @param value
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  public static void deleteValue(int hkey, String key, String value) 
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    int rc = -1;
    if (hkey == HKEY_LOCAL_MACHINE) {
      rc = deleteValue(systemRoot, hkey, key, value);
    }
    else if (hkey == HKEY_CURRENT_USER) {
      rc = deleteValue(userRoot, hkey, key, value);
    }
    if (rc != REG_SUCCESS) {
      throw new IllegalArgumentException("rc=" + rc + "  key=" + key + "  value=" + value);
    }
  }

  // =====================

  private static int deleteValue
    (Preferences root, int hkey, String key, String value)
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    int[] handles = (int[]) regOpenKey.invoke(root, new Object[] {
        new Integer(hkey), toCstr(key), new Integer(KEY_ALL_ACCESS) });
    if (handles[1] != REG_SUCCESS) {
      return handles[1];  // can be REG_NOTFOUND, REG_ACCESSDENIED
    }
    int rc =((Integer) regDeleteValue.invoke(root,  
        new Object[] { 
          new Integer(handles[0]), toCstr(value) 
          })).intValue();
    regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
    return rc;
  }

  private static int deleteKey(Preferences root, int hkey, String key) 
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    int rc =((Integer) regDeleteKey.invoke(root,  
        new Object[] { new Integer(hkey), toCstr(key) })).intValue();
    return rc;  // can REG_NOTFOUND, REG_ACCESSDENIED, REG_SUCCESS
  }

  private static String readString(Preferences root, int hkey, String key, String value)
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    int[] handles = (int[]) regOpenKey.invoke(root, new Object[] {
        new Integer(hkey), toCstr(key), new Integer(KEY_READ) });
    if (handles[1] != REG_SUCCESS) {
      return null; 
    }
    byte[] valb = (byte[]) regQueryValueEx.invoke(root, new Object[] {
        new Integer(handles[0]), toCstr(value) });
    regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
    return (valb != null ? new String(valb).trim() : null);
  }

  private static Map<String,String> readStringValues
    (Preferences root, int hkey, String key)
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    HashMap<String, String> results = new HashMap<String,String>();
    int[] handles = (int[]) regOpenKey.invoke(root, new Object[] {
        new Integer(hkey), toCstr(key), new Integer(KEY_READ) });
    if (handles[1] != REG_SUCCESS) {
      return null;
    }
    int[] info = (int[]) regQueryInfoKey.invoke(root,
        new Object[] { new Integer(handles[0]) });

    int count = info[0]; // count  
    int maxlen = info[3]; // value length max
    for(int index=0; index<count; index++)  {
      byte[] name = (byte[]) regEnumValue.invoke(root, new Object[] {
          new Integer
            (handles[0]), new Integer(index), new Integer(maxlen + 1)});
      String value = readString(hkey, key, new String(name));
      results.put(new String(name).trim(), value);
    }
    regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
    return results;
  }

  private static List<String> readStringSubKeys
    (Preferences root, int hkey, String key)
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    List<String> results = new ArrayList<String>();
    int[] handles = (int[]) regOpenKey.invoke(root, new Object[] {
        new Integer(hkey), toCstr(key), new Integer(KEY_READ) 
        });
    if (handles[1] != REG_SUCCESS) {
      return null;
    }
    int[] info = (int[]) regQueryInfoKey.invoke(root,
        new Object[] { new Integer(handles[0]) });

    int count  = info[0]; // Fix: info[2] was being used here with wrong results. Suggested by davenpcj, confirmed by Petrucio
    int maxlen = info[3]; // value length max
    for(int index=0; index<count; index++)  {
      byte[] name = (byte[]) regEnumKeyEx.invoke(root, new Object[] {
          new Integer
            (handles[0]), new Integer(index), new Integer(maxlen + 1)
          });
      results.add(new String(name).trim());
    }
    regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
    return results;
  }

  private static int [] createKey(Preferences root, int hkey, String key)
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    return  (int[]) regCreateKeyEx.invoke(root,
        new Object[] { new Integer(hkey), toCstr(key) });
  }

  private static void writeStringValue 
    (Preferences root, int hkey, String key, String valueName, String value) 
    throws IllegalArgumentException, IllegalAccessException,
    InvocationTargetException 
  {
    int[] handles = (int[]) regOpenKey.invoke(root, new Object[] {
        new Integer(hkey), toCstr(key), new Integer(KEY_ALL_ACCESS) });

    regSetValueEx.invoke(root,  
        new Object[] { 
          new Integer(handles[0]), toCstr(valueName), toCstr(value) 
          }); 
    regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
  }

  // utility
  private static byte[] toCstr(String str) {
    byte[] result = new byte[str.length() + 1];

    for (int i = 0; i < str.length(); i++) {
      result[i] = (byte) str.charAt(i);
    }
    result[str.length()] = 0;
    return result;
  }
}