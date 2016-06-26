package db;

import model.Categories;
import model.Story;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shai on 25/06/2016.
 */
public class StoryDbHandler {
    private static final int ID = 1;
    private static final int TITLE = 2;
    private static final int ROOT_ID = 3;
    private static final int CATEGORY = 4;


    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Set<Story> queryStory(String key, String value) {
        PreparedStatement stmt=null;
        StringBuilder query = new StringBuilder();
        query.append("select * from STORY ");
        query.append("where ");
        query.append(key + " = ? ");


        ResultSet rs = null;
        Set<Story> storySet = new HashSet<>();

        try {
            stmt = conn.prepareStatement(query.toString());
            stmt.setString(1, value);
            rs = stmt.executeQuery();
            while(rs.next()){

                String id = rs.getString(ID);
                String title = rs.getString(TITLE);
                String root = rs.getString(ROOT_ID);
                Categories category = Categories.getCategoryByString(rs.getString(CATEGORY));

                Story story = new Story().
                        setId(id).
                        setTitle(title).
                        setRoot(root).
                        setCategory(category);

                storySet.add(story);
            }
            return storySet;
        } catch (SQLException e) {
            e.printStackTrace();
            return storySet;
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

    public boolean InsertStory(Story story) {
        PreparedStatement stmt = null;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO `taleitdb`.`story`(`ID`,`TITLE`,`ROOT_ID`,`CATEGORY`)");
        query.append("VALUES(?,?,?,?)");

        try {
            int columnIndex = 1;

            stmt = conn.prepareStatement(query.toString());

            stmt.setString(columnIndex++, story.getId());
            stmt.setString(columnIndex++, story.getTitle());
            stmt.setString(columnIndex++, story.getRoot().getId());
            stmt.setString(columnIndex, story.getCategory().getValue());

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
}
