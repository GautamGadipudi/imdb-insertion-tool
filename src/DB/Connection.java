package DB;

import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connection {
    private static final String url = "jdbc:postgresql://localhost:54321/csci_620";
    private static final String user = "imdb_super_admin";
    private static final String password = "imdb_super_admin";

    public static java.sql.Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
