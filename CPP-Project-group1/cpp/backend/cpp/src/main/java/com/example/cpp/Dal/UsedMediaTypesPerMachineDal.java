package com.example.cpp.Dal;

import com.example.cpp.DalInterfaces.IUsedMediaTypesPerMachineDal;
import com.example.cpp.DatabaseUtilProd;
import com.example.cpp.Factories.UsedMediaTypesPerMachineFactory;
import com.example.cpp.model.UsedMediaTypesPerMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsedMediaTypesPerMachineDal implements IUsedMediaTypesPerMachineDal {

    private final DatabaseUtilProd databaseUtilProd;
    private final UsedMediaTypesPerMachineFactory factory;

    @Autowired
    public UsedMediaTypesPerMachineDal() {
        this.factory = new UsedMediaTypesPerMachineFactory();
        this.databaseUtilProd = new DatabaseUtilProd();
    }

    @Override
    public List<UsedMediaTypesPerMachine> ListUsedMediaTypesPerMachine() throws SQLException {
        List<UsedMediaTypesPerMachine> temp = new ArrayList<>();

        String url = databaseUtilProd.GetUrl();
        String user = databaseUtilProd.GetUser();
        String password = databaseUtilProd.GetPassword();

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM used_media_type ");
            while (rs.next()) {
                temp.add(factory.createUsedMediaTypes(rs.getString("machine_id"), rs.getString("name"), rs.getInt("area")));
            }

            return temp;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public List<UsedMediaTypesPerMachine> ListMediaTypesByMachineID() throws SQLException {
        List<UsedMediaTypesPerMachine> temp = new ArrayList<>();

        String url = databaseUtilProd.GetUrl();
        String user = databaseUtilProd.GetUser();
        String password = databaseUtilProd.GetPassword();

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM used_media_type");
            while (rs.next()) {
                temp.add(factory.createUsedMediaTypes(rs.getString("machine_id"), rs.getString("name"), rs.getInt("area")));
            }

            return temp;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }
}

