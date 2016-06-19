package db;


import javafx.util.Pair;
import model.User;

import java.sql.*;
import java.util.Map;

public class DbHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "root";

    //queryUser
    private static final int USER_ID = 1;
    private static final int USER_NAME = 2;
    private static final int USER_PASS = 3;
    private static final int USER_SALT = 4;


    private Connection conn;

    /**
     *
     * @param values map from column name to pair<boolean,object>
     * true means object is numeric, false means string
     * @return list of users
     */
    public User queryUser(Map<String,Pair<Boolean,Object>> values) {
        boolean firstValue = true;
        PreparedStatement stmt=null;
        StringBuilder query = new StringBuilder();
        query.append("select * from AUTHOR ");
        for (Map.Entry<String,Pair<Boolean,Object>> entry : values.entrySet()) {
            if (firstValue) {
                query.append("where ");
                firstValue=false;
            }
            else {
                query.append("and ");
            }
            query.append(entry.getKey()+" = ? ");
        }

        ResultSet rs=null;

        try {
            stmt = conn.prepareStatement(query.toString());
            int columnIndex=1;
            for (Pair<Boolean,Object> entry : values.values()) {
                if (entry.getKey()) {
                    stmt.setInt(++columnIndex,(Integer)(entry.getValue()));
                }
                else {
                    stmt.setString(++columnIndex,(String)(entry.getValue()));
                }
            }
            rs = stmt.executeQuery();
            while(rs.next()){
                int id  = rs.getInt(USER_ID);
                String name = rs.getString(USER_NAME);
                String pass = rs.getString(USER_PASS);
                String salt = rs.getString(USER_SALT);
                return new User(name,pass);
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

    public void InsertUser(User user) {
        PreparedStatement stmt = null;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO `taleitdb`.`author`(`USERNAME`,`PASSWORDHASH`,`SALT`,`COOKIE`,`FACEBOOK_ID`,`FACEBOOK_ACCESS_TOKEN`,`NAME`)");
        query.append("VALUES(?,?,?,?,?,?,?)");

        try {
            int columnIndex = 1;

            stmt = conn.prepareStatement(query.toString());

            stmt.setString(++columnIndex, user.getUsername());
            stmt.setString(++columnIndex, user.passwordHash);
            stmt.setString(++columnIndex, user.getSalt());
            stmt.setString(++columnIndex, null);
            stmt.setString(++columnIndex, user.getFacebookId());
            stmt.setString(++columnIndex, user.getFacebookAccessToken());
            stmt.setString(++columnIndex, user.getName());

            stmt.executeQuery();



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
    }

        /**
         * creates a connection
         */
    public void connect(){
        conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
