package taleit.db;


import taleit.model.Paragraph;
import taleit.model.Story;
import taleit.model.User;

import java.sql.*;
import java.util.Set;


public final class DbHandler {
    private static DbHandler instance = null;

    private DbHandler() {
        userDbHandler = new UserDbHandler();
        paragraphDbHandler = new ParagraphDbHandler();
        storyDbHandler = new StoryDbHandler();
    }

    public static synchronized DbHandler getInstance() {
        try {
            if (instance == null || instance.conn == null || instance.conn.isClosed()) {
                instance = new DbHandler();
                instance.connect();
                instance.userDbHandler.setConn(instance.conn);
                instance.paragraphDbHandler.setConn(instance.conn);
                instance.storyDbHandler.setConn(instance.conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instance;
    }

    //private static final String DB_URL = "jdbc:mysql://localhost:3306/taleitdb";
//    private static final String USER = "root";
//    private static final String PASS = "root";

    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/taleitdb?" + "user=root&password=root";


    private Connection conn;
    private UserDbHandler userDbHandler;
    private ParagraphDbHandler paragraphDbHandler;
    private StoryDbHandler storyDbHandler;



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

    public boolean updateParagraph(Paragraph paragraph) {
        return paragraphDbHandler.updateParagraph(paragraph);
    }


    public Set<Story> queryStory(String key, String value) {
        return storyDbHandler.queryStory(key, value);
    }

    public boolean InsertStory(Story story) {
        return storyDbHandler.InsertStory(story);
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
            stmt = conn.prepareStatement("TRUNCATE `taleitdb`.`story`;");
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Connecting to database...");

        //conn = DriverManager.getConnection(DB_URL, USER, PASS);
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
