package com.example.timesheet.security;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AESUtil {

    @Value("${aes.key}") private String secretKeyHex;
    @Value("${aes.iv}")  private String initVectorHex;

    private byte[] secretKey, initVector;

    @PostConstruct
    public void init() {
        secretKey = secretKeyHex.getBytes(StandardCharsets.UTF_8);  // <-- Use plain string
        initVector = initVectorHex.getBytes(StandardCharsets.UTF_8);

        if (secretKey.length != 16) {
            throw new IllegalArgumentException("Key must be 16 bytes");
        }
        if (initVector.length != 16) {
            throw new IllegalArgumentException("IV must be 16 bytes");
        }
    }

    public String encrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey, "AES"), new IvParameterSpec(initVector));
        return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes(StandardCharsets.UTF_8)));
    }

    public String decrypt(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey, "AES"), new IvParameterSpec(initVector));
        byte[] decoded = Base64.getDecoder().decode(encrypted);
        return new String(cipher.doFinal(decoded), StandardCharsets.UTF_8);
    }

    private byte[] hexToBytes(String s) {
        int len = s.length();
        if (len % 2 != 0) throw new IllegalArgumentException("Invalid hex string");
        byte[] data = new byte[len/2];
        for (int i = 0; i < len; i += 2)
            data[i/2] = (byte)((Character.digit(s.charAt(i),16)<<4)
                             + Character.digit(s.charAt(i+1),16));
        return data;
    }
}
