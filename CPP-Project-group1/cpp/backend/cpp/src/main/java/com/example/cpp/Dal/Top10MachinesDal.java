package com.example.cpp.Dal;

import com.example.cpp.DalInterfaces.ITop10MachinesDal;
import com.example.cpp.DatabaseUtilProd;
import com.example.cpp.Factories.Top10MachinesFactory;
import com.example.cpp.model.Top10Machines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Top10MachinesDal implements ITop10MachinesDal {

    private final DatabaseUtilProd databaseUtilProd;
    private final Top10MachinesFactory factory;

    @Autowired
    public Top10MachinesDal() {
        this.factory = new Top10MachinesFactory();
        this.databaseUtilProd = new DatabaseUtilProd();

    }

    @Override
    public List<Top10Machines> ListTop10Machines() throws SQLException {
            List<Top10Machines> temp = new ArrayList<>();

            String url = databaseUtilProd.GetUrl();
            String user = databaseUtilProd.GetUser();
            String password = databaseUtilProd.GetPassword();
            Connection conn = null;

            try {

                conn = DriverManager.getConnection(url, user, password);

                PreparedStatement statement = conn.prepareStatement("SELECT * FROM `top_10_machines_print_volume`");
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {

                    temp.add(factory.createTop10Machines(rs.getString("machine_id"),rs.getInt("SUM")));

                }
                return temp;

            } catch (Exception e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            } finally {
                conn.close();

            }
            return null;
        }
    }

