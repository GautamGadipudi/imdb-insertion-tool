/**
 * TransactionTest.java
 *
 * @author Gautam Gadipudi
 *
 * @id gg7148
 *
 * @description This is the question 5 of assignment 1.*/
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionTest {
    public static void main(String[] args) {
        try (Connection conn = DB.Connection.connect()) {
            conn.setAutoCommit(false);
            var st1 = conn.prepareStatement("INSERT INTO imdb.titles(" +
                    "id, " +
                    "primaryTitleName, " +
                    "originalTitleName) " +
                    "VALUES(?,?,?)");
            st1.setInt(1, 91724082);
            st1.setString(2, "Barca dreams");
            st1.setString(3, "Barca dreams");

            var st2 = conn.prepareStatement("INSERT INTO imdb.titles(" +
                    "primaryTitleName, " +
                    "originalTitleName) " +
                    "VALUES(?,?)");
            st2.setString(1, "Madrid dreams");
            st2.setString(2, "Madrid dreams");

            var st3 = conn.prepareStatement("INSERT INTO imdb.titles(" +
                    "id, " +
                    "primaryTitleName, " +
                    "originalTitleName) " +
                    "VALUES(?,?,?)");
            st3.setInt(1, 91724084);
            st3.setString(2, "Betis dreams");
            st3.setString(3, "Betis dreams");

            try {
                st1.executeUpdate();
                st2.executeUpdate();
                st3.executeUpdate();
                conn.commit();
            }
            catch (SQLException e) {
                conn.rollback();
                System.out.println("SQL Exception occured. Rolling back transaction.");
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
} //TransactionTest
