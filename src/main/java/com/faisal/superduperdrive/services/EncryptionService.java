package com.faisal.superduperdrive.services;


import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class EncryptionService {

    public String encryptValue(String data, String key) {
        byte[] encryptedValue = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedValue = cipher.doFinal(data.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e.getMessage());
        }

        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public String decryptValue(String data, String key) {
        byte[] decryptedValue = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedValue = cipher.doFinal(Base64.getDecoder().decode(data));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e.getMessage());
        }

        return new String(decryptedValue);
    }

    public String getHashedValue(String data, String salt) {
        byte[] hashedValue = null;

        int iterCount = 12288;
        int derivedKeyLength = 256;
        KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), iterCount, derivedKeyLength * 8);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            hashedValue = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return Base64.getEncoder().encodeToString(hashedValue);
    }

    public String generateSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(20);
        return new String(Base64.getEncoder().encodeToString(salt));
    }
}
