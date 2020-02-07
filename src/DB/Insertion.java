package DB;

import Entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public final class Insertion {
    public static void insertTitles(List<Title> titles) {
        String sql = "INSERT INTO imdb.titles(" +
                "id, " +
                "type, " +
                "primaryTitleName, " +
                "originalTitleName, " +
                "isAdult, " +
                "startYear, " +
                "endYear, " +
                "runtimeMinutes, " +
                "genres) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DB.Connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            int count = 0;
            for (var title : titles) {
                pstmt.setInt(1, title.getId());
                pstmt.setString(2, title.getType());
                pstmt.setString(3, title.getPrimaryTitleName());
                pstmt.setString(4, title.getOriginalTitleName());
                pstmt.setBoolean(5, title.isAdult());
                pstmt.setInt(6, title.getStartYear());
                pstmt.setInt(7, title.getEndYear());
                pstmt.setInt(8, title.getRuntimeMinutes());
                pstmt.setString(9, title.getGenres());

                pstmt.addBatch();
                count++;

                if (count % 100 == 0 || count == titles.size()) {
                    pstmt.executeBatch();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertNames(List<Name> names) {
        String sql = "INSERT INTO imdb.names(" +
                "id, " +
                "primaryName, " +
                "birthYear, " +
                "deathYear)" +
                "VALUES(?,?,?,?)";
        try (Connection conn = DB.Connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            int count = 0;
            for (var name : names) {
                pstmt.setInt(1, name.getId());
                pstmt.setString(2, name.getPrimaryName());
                pstmt.setInt(3, name.getBirthYear());
                pstmt.setInt(4, name.getDeathYear());

                pstmt.addBatch();
                count++;

                if (count % 100 == 0 || count == names.size()) {
                    pstmt.executeBatch();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertDirectors(List<Director> directors) {
        String sql = "INSERT INTO imdb.directors(" +
                "titleId, " +
                "nameId)" +
                "VALUES(?,?)";
        try (Connection conn = DB.Connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            int count = 0;
            for (var director : directors) {
                pstmt.setInt(1, director.getTitleId());
                pstmt.setInt(2, director.getNameId());

                pstmt.addBatch();
                count++;

                if (count % 100 == 0 || count == directors.size()) {
                    pstmt.executeBatch();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertWriters(List<Writer> directors) {
        String sql = "INSERT INTO imdb.writers(" +
                "titleId, " +
                "nameId)" +
                "VALUES(?,?)";
        try (Connection conn = DB.Connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            int count = 0;
            for (var director : directors) {
                pstmt.setInt(1, director.getTitleId());
                pstmt.setInt(2, director.getNameId());

                pstmt.addBatch();
                count++;

                if (count % 100 == 0 || count == directors.size()) {
                    pstmt.executeBatch();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertPrincipals(List<Principal> directors) {
        String sql = "INSERT INTO imdb.principals(" +
                "titleId, " +
                "nameId," +
                "orderNumber," +
                "category," +
                "job," +
                "characters)" +
                "VALUES(?,?,?,?,?,?)";
        try (Connection conn = DB.Connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            int count = 0;
            for (var director : directors) {
                pstmt.setInt(1, director.getTitleId());
                pstmt.setInt(2, director.getNameId());
                pstmt.setInt(3, director.getOrderNumber());
                pstmt.setString(4, director.getCategory());
                pstmt.setString(5, director.getJob());
                pstmt.setString(6, director.getCharacters());

                pstmt.addBatch();
                count++;

                if (count % 100 == 0 || count == directors.size()) {
                    pstmt.executeBatch();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertRatings(List<Rating> list) {
        String sql = "INSERT INTO imdb.ratings(" +
                "titleId, " +
                "averageRating," +
                "voteCount)" +
                "VALUES(?,?,?)";
        try (Connection conn = DB.Connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            int count = 0;
            for (var entity : list) {
                pstmt.setInt(1, entity.getTitleId());
                pstmt.setFloat(2, entity.getAverageRating());
                pstmt.setInt(3, entity.getVoteCount());

                pstmt.addBatch();
                count++;

                if (count % 100 == 0 || count == list.size()) {
                    pstmt.executeBatch();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertEpisodes(List<Episode> list) {
        String sql = "INSERT INTO imdb.episodes(" +
                "id, " +
                "titleId," +
                "seasonNumber," +
                "episodeNumber)" +
                "VALUES(?,?,?,?)";
        try (Connection conn = DB.Connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            int count = 0;
            for (var entity : list) {
                pstmt.setInt(1, entity.getId());
                pstmt.setInt(2, entity.getTitleId());
                pstmt.setInt(3, entity.getSeasonNumber());
                pstmt.setInt(4, entity.getEpisodeNumber());

                pstmt.addBatch();
                count++;

                if (count % 100 == 0 || count == list.size()) {
                    pstmt.executeBatch();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertAkas(List<Aka> list) {
        String sql = "INSERT INTO imdb.title_episodes(" +
                "titleId, " +
                "orderNumber," +
                "titleName," +
                "region," +
                "language," +
                "types," +
                "attributes," +
                "isOriginalTitle)" +
                "VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = DB.Connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            int count = 0;
            for (var entity : list) {
                pstmt.setInt(1, entity.getTitleId());
                pstmt.setInt(2, entity.getOrderNumber());
                pstmt.setString(3, entity.getTitleName());
                pstmt.setString(4, entity.getRegion());
                pstmt.setString(5, entity.getLanguage());
                pstmt.setString(6, entity.getTypes());
                pstmt.setString(7, entity.getAttributes());
                pstmt.setBoolean(8, entity.isOriginalTitle());

                pstmt.addBatch();
                count++;

                if (count % 100 == 0 || count == list.size()) {
                    pstmt.executeBatch();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
