package com.example.cpp;

import com.example.cpp.Factories.InkUsageFactory;
import com.example.cpp.Factories.MachineIDFactory;
import com.example.cpp.model.InkUsage;
import com.example.cpp.model.MachineID;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MachineIDServiceTest {

    private DatabaseUtilTests databaseUtil = new DatabaseUtilTests();

    @Test
    public void getMultipleInkUsageOverTime() {
        String url = databaseUtil.GetUrl();
        String user = databaseUtil.GetUser();
        String password = databaseUtil.GetPassword();

        MachineIDFactory factory = new MachineIDFactory();
        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            try (Statement stCheck = connection.createStatement()) {
                connection.setAutoCommit(false);


                String machineID;
                MachineID contractor = factory.CreateMachineID("44");
                assertEquals("44", contractor.getMachineID());

                try (ResultSet rs = stCheck.executeQuery("SELECT ink_usage_over_time.machine_id AS machine_id FROM ink_usage_over_time \n" +
                        "union \n" +
                        "SELECT media_category_over_time.machine_id AS machine_id FROM media_category_over_time")) {

                    assertTrue(rs.next());
                    machineID = rs.getString("machine_id");
                    assertEquals(machineID, rs.getString("machine_id"));

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
