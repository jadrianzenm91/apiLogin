/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisadri.apilogin.helpers;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author pc
 */
public class AES {

    private final byte[] password = "s1s4dr1C0br4nz4".getBytes();
    private final byte[] vector = "$s1st3m4s&4dr14nz3n$".getBytes();

    IvParameterSpec iv;
    SecretKeySpec keySpec;
    Cipher cipher;

    public AES() {
        try {
            iv = new IvParameterSpec(vector);
            keySpec = new SecretKeySpec(password, "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String encrypt(String value) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

        byte[] encrypted = cipher.doFinal(value.getBytes());

        return Base64.encodeBase64String(encrypted);
    }

    public String decrypt(String value) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
        
        byte[] original = cipher.doFinal(Base64.decodeBase64(value));
        
        return new String(original);
    }
    
}

