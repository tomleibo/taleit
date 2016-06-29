package taleit.db;

import taleit.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDbHandler {
    private static final int USERNAME = 1;
    private static final int PASSWORDHASH = 2;
    private static final int SALT = 3;
    private static final int COOKIE = 4;
    private static final int FACEBOOK_ID = 5;
    private static final int FACEBOOK_ACCESS_TOKEN = 6;
    private static final int NAME = 7;

    private Connection conn;

    /**
     *
     * @return list of users
     */
    public User queryUser(String key, String value) {
        PreparedStatement stmt=null;
        StringBuilder query = new StringBuilder();
        query.append("select * from AUTHOR ");
        query.append("where ");
        query.append(key + " = ? ");


        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(query.toString());
            stmt.setString(1, value);
            rs = stmt.executeQuery();
            while(rs.next()){

                String username = rs.getString(USERNAME);
                String pass = rs.getString(PASSWORDHASH);
                String salt = rs.getString(SALT);
                String cookie = rs.getString(COOKIE);
                String fbid = rs.getString(FACEBOOK_ID);
                String fbacctkn = rs.getString(FACEBOOK_ACCESS_TOKEN);
                String name = rs.getString(NAME);

                User user = new User().
                        setUsername(username).
                        setPasswordHash(pass).
                        setSalt(salt).
                        setCookie(cookie).
                        setFacebookId(fbid).
                        setFacebookAccessToken(fbacctkn).
                        setName(name);

                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean InsertUser(User user) {
        PreparedStatement stmt = null;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO `taleitdb`.`author`(`USERNAME`,`PASSWORDHASH`,`SALT`,`COOKIE`,`FACEBOOK_ID`,`FACEBOOK_ACCESS_TOKEN`,`NAME`)");
        query.append("VALUES(?,?,?,?,?,?,?)");

        try {
            int columnIndex = 1;

            stmt = conn.prepareStatement(query.toString());

            stmt.setString(columnIndex++, user.getUsername());
            stmt.setString(columnIndex++, user.passwordHash);
            stmt.setString(columnIndex++, user.getSalt());
            stmt.setString(columnIndex++, null);
            stmt.setString(columnIndex++, user.getFacebookId());
            stmt.setString(columnIndex++, user.getFacebookAccessToken());
            stmt.setString(columnIndex, user.getName());

            return stmt.executeUpdate() > 0;



        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateUser(User user) {
        PreparedStatement stmt = null;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE `taleitdb`.`author` SET `USERNAME` = ?,`PASSWORDHASH` = ?,`SALT` = ?,`COOKIE` = ?,`FACEBOOK_ID` = ?,`FACEBOOK_ACCESS_TOKEN` = ?,`NAME` = ?");
        query.append(" WHERE `USERNAME` = ?");

        try {
            int columnIndex = 1;

            stmt = conn.prepareStatement(query.toString());

            stmt.setString(columnIndex++, user.getUsername());
            stmt.setString(columnIndex++, user.passwordHash);
            stmt.setString(columnIndex++, user.getSalt());
            stmt.setString(columnIndex++, user.getCookie());
            stmt.setString(columnIndex++, user.getFacebookId());
            stmt.setString(columnIndex++, user.getFacebookAccessToken());
            stmt.setString(columnIndex++, user.getName());
            stmt.setString(columnIndex, user.getUsername());

            return stmt.executeUpdate() > 0;



        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }


    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
