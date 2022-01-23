package com.example.cpp.Dal;

import com.example.cpp.DalInterfaces.IMachineIDal;
import com.example.cpp.DatabaseUtilProd;
import com.example.cpp.Factories.MachineIDFactory;
import com.example.cpp.model.MachineID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MachineIDal implements IMachineIDal {


    private final DatabaseUtilProd databaseUtilProd;
    private final MachineIDFactory factory;

    @Autowired
    public MachineIDal(){
        this.databaseUtilProd = new DatabaseUtilProd();
        this.factory = new MachineIDFactory();
    }
    @Override
    public List<MachineID> GetAllMachineID() throws SQLException {
        List<MachineID> temp = new ArrayList<>();

        String url = databaseUtilProd.GetUrl();
        String user = databaseUtilProd.GetUser();
        String password = databaseUtilProd.GetPassword();
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = conn.prepareStatement("SELECT ink_usage_over_time.machine_id AS machine_id FROM ink_usage_over_time \n" +
                    "union \n" +
                    "SELECT media_category_over_time.machine_id AS machine_id FROM media_category_over_time");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                temp.add(factory.CreateMachineID(rs.getString("machine_id")));

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

