package model;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;

/**
 * Created by gur on 12/23/2015.
 */
public class User {
    private static SecureRandom RANDOM = new SecureRandom();

    private String username;
    String passwordHash;
    String salt;
    public String cookie;

    public User(String username, String password) {
        this.setUsername(username);
        this.salt = Integer.toString(RANDOM.nextInt());
        this.passwordHash = getPasswordHash(password);
    }

    public String getPasswordHash(String password) {
        return DigestUtils.sha1Hex(password + salt);
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (getUsername() == null) return false;
        if (!getClass().equals(other.getClass())) return false;
        return getUsername().equals(((User) other).getUsername());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}