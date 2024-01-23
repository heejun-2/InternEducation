package com.example.upgrade;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class Encryption {
    public static String encode(String password) {
        String sha = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            sha = Base64.getEncoder().encodeToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            log.info("error", e);
            throw new RuntimeException(e);
        }
        return sha;

    }
}
