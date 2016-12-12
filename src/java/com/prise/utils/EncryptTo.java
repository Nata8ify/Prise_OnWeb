/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author QuartierLatin
 */
public class EncryptTo {
    
    private static final String SALT = "KIND";
    
    public static String md5(String pwd){
        
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] pwdBytes = pwd.getBytes();
            md.update(SALT.getBytes());
            return strBytesBuilder(md.digest(pwdBytes));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncryptTo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public static String sha1(String pwd){
        
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] pwdBytes = pwd.getBytes();
            md.update(SALT.getBytes());
            return strBytesBuilder(md.digest(pwdBytes));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncryptTo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     private static String strBytesBuilder(byte[] pwdBytes){
            StringBuilder pwdBuilder = new StringBuilder();
            for(byte b : pwdBytes){
                pwdBuilder.append(String.format("%02x", b & 0xff));
            }
            return  pwdBuilder.toString();
        }
}
