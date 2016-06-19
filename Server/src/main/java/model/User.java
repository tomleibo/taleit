package model;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;

/**
 * Created by gur on 12/23/2015.
 */
public class User {
    private static SecureRandom RANDOM = new SecureRandom();

    private String username;
    public String passwordHash;
    String salt;
    public String cookie;
    private String facebookId;
    private String facebookAccessToken;
    private String name;

    public User(String username, String password) {
        this.setUsername(username);
        this.salt = Integer.toString(RANDOM.nextInt());
        this.passwordHash = getPasswordHash(password);
        this.facebookId = null;
        this.facebookAccessToken = null;
        this.name = null;
    }

    public User() {

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

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setFacebookId(String facebookId) {
        this.facebookId = facebookId;
        return this;
    }

    public String getFacebookId(){
        return this.facebookId;
    }

    public User setFacebookAccessToken(String facebookAccessToken) {
        this.facebookAccessToken = facebookAccessToken;
        return this;
    }

    public String getFacebookAccessToken(){
        return this.facebookAccessToken;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }


    public String getName() {
        return this.name;
    }

    public String getSalt() {
        return this.salt;
    }

    public User setSalt(String salt){
        this.salt = salt;
        return this;
    }

    public User setPasswordHash(String passHash){
        this.passwordHash = passHash;
        return this;
    }

    public User setCookie(String cookie){
        this.cookie = cookie;
        return this;
    }

    public String getCookie() {
        return cookie;
    }
}