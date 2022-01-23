package com.example.cpp;

import com.example.cpp.Factories.InkUsageFactory;
import com.example.cpp.Factories.MediaCategoryFactory;
import com.example.cpp.model.InkUsage;
import com.example.cpp.model.MediaCategory;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class MediaCategoryServiceTest {

    private DatabaseUtilTests databaseUtil = new DatabaseUtilTests();


    @Test
    public void getInkUsageOverTime() {
        String url = databaseUtil.GetUrl();
        String user = databaseUtil.GetUser();
        String password = databaseUtil.GetPassword();

        MediaCategoryFactory factory = new MediaCategoryFactory();
        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            try (Statement stCheck = connection.createStatement()) {
                connection.setAutoCommit(false);


                Date date = new Date(2020,11,11);

                Timestamp startTimestamp = Timestamp.valueOf("2020-11-18 00:00:00");
                Timestamp endTimestamp = Timestamp.valueOf("2020-11-18 00:00:00");

                float film = 1.88018f;
                float heavyPaper = 0f;

                MediaCategory contractor = factory.createMediaCategory("1", date, film,0f, heavyPaper, 23f,0f,0f,0f,0f,0f,0f,0f);
                assertEquals(film, contractor.getFilm());
                assertEquals(heavyPaper, contractor.getHeavyPaper());

                Long machineID;


                // Check the Person table contains one row with the expected values:
                try (ResultSet rs = stCheck.executeQuery("SELECT *" +
                        "                    FROM (" +
                        "                            SELECT @row := @row +1 AS rownum, media_category_over_time.*\n" +
                        "            FROM (" +
                        "                    SELECT @row :=0) r, media_category_over_time" +
                        "    ) ranked" +
                        "           WHERE date BETWEEN '" + startTimestamp + "' AND '" + endTimestamp + "'\n" +
                        "       GROUP by date ")) {
                    assertTrue(rs.next());
                    machineID = rs.getLong("machine_id");
                    assertEquals(film, rs.getFloat("Film"));
                    assertEquals(heavyPaper, rs.getFloat("heavyPaper"));

                    assertFalse(rs.next());
                } finally {
                    // Undo the testing operations:
                    connection.rollback();
                }
            } catch (SQLException e) {
                fail(e.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void getMultipleInkUsageOverTime() {
        String url = databaseUtil.GetUrl();
        String user = databaseUtil.GetUser();
        String password = databaseUtil.GetPassword();

        MediaCategoryFactory factory = new MediaCategoryFactory();
        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            try (Statement stCheck = connection.createStatement()) {
                connection.setAutoCommit(false);

                List<MediaCategory> mediaCategoriesTest = new ArrayList<>();


                Timestamp startTimestamp = Timestamp.valueOf("2020-11-11 00:00:00");
                Timestamp endTimestamp = Timestamp.valueOf("2020-11-13 00:00:00");


                // Check the Person table contains one row with the expected values:
                try (ResultSet rs = stCheck.executeQuery("SELECT *" +
                        "                    FROM (" +
                        "                            SELECT @row := @row +1 AS rownum, media_category_over_time.*\n" +
                        "            FROM (" +
                        "                    SELECT @row :=0) r, media_category_over_time" +
                        "    ) ranked" +
                        "           WHERE date BETWEEN '" + startTimestamp + "' AND '" + endTimestamp + "'\n" +
                        "       GROUP by date ")) {

                    while (rs.next() ) {

                        mediaCategoriesTest.add(factory.createMediaCategory(rs.getString("machine_id"),rs.getDate("date"),rs.getFloat("Paper"),rs.getFloat("LightPaper"),
                                rs.getFloat("HeavyPaper"),rs.getFloat("Film"),rs.getFloat("ThickFilm"),rs.getFloat("LightBanner"),rs.getFloat("HeavyBanner"),rs.getFloat("Textile"),
                                rs.getFloat("MonomericVinyl"),rs.getFloat("Canvas"),rs.getFloat("PolymericCastVinyl")));

                    }
                    assertEquals(3,mediaCategoriesTest.size());

                } finally {
                    // Undo the testing operations:
                    connection.rollback();
                }
            } catch (SQLException e) {
                fail(e.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
