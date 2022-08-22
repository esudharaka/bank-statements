package com.nagarrow.statements.util;


import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.exceptions.AppError;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtils {

    private HashingUtils() {

    }
    public static String generateHashCode(String input) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(input.getBytes());
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new AccountServiceException(AppError.INTERNAL_SERVER_ERROR, e);
        }


    }
}
