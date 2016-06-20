package db;

import model.Paragraph;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shai on 20/06/2016.
 */
public class ParagraphDbHandler {

    private static final int ID = 1;
    private static final int FATHER = 2;
    private static final int AUTHOR_ID = 3;
    private static final int TEXT = 4;
    private static final int TITLE = 5;

    private Connection conn;
    public ParagraphDbHandler() {
        this.conn = conn;
    }

    public Set<Paragraph> queryParagraph(String key, String value) {
        PreparedStatement stmt=null;
        StringBuilder query = new StringBuilder();
        query.append("select * from PARAGRAPH ");
        query.append("where ");
        query.append(key + " = ? ");


        ResultSet rs = null;
        Set<Paragraph> paragraphSet = new HashSet<>();

        try {
            stmt = conn.prepareStatement(query.toString());
            stmt.setString(1, value);
            rs = stmt.executeQuery();
            while(rs.next()){

                String id = rs.getString(ID);
                String father = rs.getString(FATHER);
                String authorId = rs.getString(AUTHOR_ID);
                String text = rs.getString(TEXT);
                String title = rs.getString(TITLE);

                Paragraph paragraph = new Paragraph().
                        setId(id).
                        setFather(father).
                        setUser(authorId).
                        setText(text).
                        setTitle(title);

                paragraphSet.add(paragraph);
            }
            return paragraphSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return paragraphSet;
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

    public boolean InsertParagraph(Paragraph paragraph) {
        PreparedStatement stmt = null;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO `taleitdb`.`paragraph`(`ID`,`FATHER`,`AUTHOR_ID`,`TEXT`,`TITLE`)");
        query.append("VALUES(?,?,?,?,?)");

        try {
            int columnIndex = 1;

            stmt = conn.prepareStatement(query.toString());

            stmt.setString(columnIndex++, paragraph.getId());
            if (paragraph.getFather() == null){
                stmt.setString(columnIndex++, null);
            }else{
                stmt.setString(columnIndex++, paragraph.getFather().getId());
            }
            if(paragraph.getUser() == null){
                stmt.setString(columnIndex++, null);
            }else {
                stmt.setString(columnIndex++, paragraph.getUser().getUsername());
            }
            stmt.setString(columnIndex++, paragraph.getText());
            stmt.setString(columnIndex, paragraph.getTitle());

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
