package model;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;

/**
 * Created by gur on 12/23/2015.
 */
public class User {
    private static SecureRandom RANDOM = new SecureRandom();

    String username;
    String passwordHash;
    String salt;

    public User(String username, String password){
        this.username = username;
        this.salt = Integer.toString(RANDOM.nextInt());
        this.passwordHash = getPasswordHash(password);
    }

    public String getPasswordHash(String password){
        return DigestUtils.sha1Hex(password + salt);
    }
}
