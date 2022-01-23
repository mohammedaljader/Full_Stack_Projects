package com.example.cpp;

import com.example.cpp.Factories.InkUsageFactory;
import com.example.cpp.model.InkUsage;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InkUsageServiceTest {

    private DatabaseUtilTests databaseUtil = new DatabaseUtilTests();


    @Test
    public void getInkUsageOverTime() {
        String url = databaseUtil.GetUrl();
        String user = databaseUtil.GetUser();
        String password = databaseUtil.GetPassword();

        InkUsageFactory factory = new InkUsageFactory();
        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            try (Statement stCheck = connection.createStatement()) {
                connection.setAutoCommit(false);


                Date date = new Date(2020,11,11);

                Timestamp startTimestamp = Timestamp.valueOf("2020-11-11 00:00:00");
                Timestamp endTimestamp = Timestamp.valueOf("2020-11-11 00:00:00");

                float color_black = 260.031f;
                float color_cyan = 248.114f;

                InkUsage contractor = factory.createInkUsage("1", date, color_black, color_cyan, 23f, 23f);
                assertEquals(color_black, contractor.getColor_black());
                assertEquals(color_cyan, contractor.getColor_cyan());

                Long machineID;


                // Check the Person table contains one row with the expected values:
                try (ResultSet rs = stCheck.executeQuery("SELECT *" +
                        "                    FROM (" +
                        "                            SELECT @row := @row +1 AS rownum, ink_usage_over_time.*\n" +
                        "            FROM (" +
                        "                    SELECT @row :=0) r, ink_usage_over_time" +
                        "    ) ranked" +
                        "           WHERE date BETWEEN '" + startTimestamp + "' AND '" + endTimestamp + "'\n" +
                        "       GROUP by date ")) {
                    assertTrue(rs.next());
                    machineID = rs.getLong("machine_id");
                    assertEquals(color_black, rs.getFloat("color_black"));
                    assertEquals(color_cyan, rs.getFloat("color_cyan"));

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

        InkUsageFactory factory = new InkUsageFactory();
        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            try (Statement stCheck = connection.createStatement()) {
                connection.setAutoCommit(false);

                List<InkUsage> inkUsagesToTest = new ArrayList<>();


                Timestamp startTimestamp = Timestamp.valueOf("2020-11-11 00:00:00");
                Timestamp endTimestamp = Timestamp.valueOf("2020-11-13 00:00:00");


                // Check the Person table contains one row with the expected values:
                try (ResultSet rs = stCheck.executeQuery("SELECT *" +
                        "                    FROM (" +
                        "                            SELECT @row := @row +1 AS rownum, ink_usage_over_time.*\n" +
                        "            FROM (" +
                        "                    SELECT @row :=0) r, ink_usage_over_time" +
                        "    ) ranked" +
                        "           WHERE date BETWEEN '" + startTimestamp + "' AND '" + endTimestamp + "'\n" +
                        "       GROUP by date ")) {

                    while (rs.next() ) {

                        inkUsagesToTest.add(factory.createInkUsage(rs.getString("machine_id"),rs.getDate("date"),rs.getFloat("color_black"),rs.getFloat("color_cyan"),rs.getFloat("color_magenta"),rs.getFloat("color_yellow")));

                    }
                    assertEquals(3,inkUsagesToTest.size());

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
