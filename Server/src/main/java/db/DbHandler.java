package db;


import model.Paragraph;
import model.User;

import java.sql.*;
import java.util.Set;


public final class DbHandler {
    private static DbHandler instance = null;
    private DbHandler() {
        userDbHandler = new UserDbHandler();
        paragraphDbHandler = new ParagraphDbHandler();
    }

    public static synchronized DbHandler getInstance() {
        if (instance == null) {
            instance = new DbHandler();
            instance.connect();
            instance.userDbHandler.setConn(instance.conn);
            instance.paragraphDbHandler.setConn(instance.conn);
        }

        return instance;
    }




    private static final String DB_URL = "jdbc:mysql://localhost:3306/taleitdb";
    private static final String USER = "root";
    private static final String PASS = "root";

    private Connection conn;
    private UserDbHandler userDbHandler;
    private ParagraphDbHandler paragraphDbHandler;


    /**
     *
     * @return list of users
     */
    public User queryUser(String key, String value) {
        return userDbHandler.queryUser(key, value);
    }

    public boolean InsertUser(User user) {
        return userDbHandler.InsertUser(user);
    }

    public boolean updateUser(User user) {
        return userDbHandler.updateUser(user);
    }

    public Set<Paragraph> queryParagraph(String key, String value) {
        return paragraphDbHandler.queryParagraph(key, value);
    }

    public boolean InsertParagraph(Paragraph paragraph) {
        return paragraphDbHandler.InsertParagraph(paragraph);
    }

        /**
         *
         * @return if the query changed anything
         */
    public boolean truncateTables() {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("TRUNCATE `taleitdb`.`author`;");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("TRUNCATE `taleitdb`.`paragraph`;");
            stmt.executeUpdate();

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
