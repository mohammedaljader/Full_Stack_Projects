package com.example.cpp.Dal;

import com.example.cpp.DalInterfaces.IInkUsageDal;
import com.example.cpp.DatabaseUtilProd;
import com.example.cpp.Factories.InkUsageFactory;
import com.example.cpp.model.InkUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InkUsageDal implements IInkUsageDal {

    private final InkUsageFactory factory;
    private PreparedStatement statement;
    private DatabaseUtilProd databaseUtilProd;


    @Autowired
    public InkUsageDal()
    {
        this.factory = new InkUsageFactory();
        this.databaseUtilProd = new DatabaseUtilProd();
    }

    @Override
    public List<InkUsage> ListOfInkUsage() throws SQLException {
        List<InkUsage> temp = new ArrayList<>();

        String url = databaseUtilProd.GetUrl();
        String user = databaseUtilProd.GetUser();
        String password = databaseUtilProd.GetPassword();

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM `ink_usage_over_time`");
            while (rs.next() ) {
                temp.add(factory.createInkUsage(rs.getString("machine_id"),rs.getDate("date"),rs.getFloat("color_black"),rs.getFloat("color_cyan"),rs.getFloat("color_magenta"),rs.getFloat("color_yellow")));
            }

            return temp;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        finally {
            conn.close();
        }
        return null;
    }

    @Override
    public List<InkUsage> ListInkUsageByTimeFrame(String startDate, String endDate) throws SQLException {
        List<InkUsage> temp = new ArrayList<>();

        Connection conn = null;


        Timestamp startTimestamp = Timestamp.valueOf(startDate);
        Timestamp endTimestamp = Timestamp.valueOf(endDate);

        String url = databaseUtilProd.GetUrl();
        String user = databaseUtilProd.GetUser();
        String password = databaseUtilProd.GetPassword();
        try {

            conn = DriverManager.getConnection(url,user,password);

            statement = conn.prepareStatement(" SELECT *" +
                    "                    FROM (" +
                    "                            SELECT @row := @row +1 AS rownum, ink_usage_over_time.*\n" +
                    "            FROM (" +
                    "                    SELECT @row :=0) r, ink_usage_over_time" +
                    "    ) ranked" +
                    "           WHERE date BETWEEN '"+startTimestamp+"' AND '"+endTimestamp+"'\n" +
                    "       GROUP by date ");
            ResultSet rs = statement.executeQuery();


            while (rs.next() ) {

                temp.add(factory.createInkUsage(rs.getString("machine_id"),rs.getDate("date"),rs.getFloat("color_black"),rs.getFloat("color_cyan"),rs.getFloat("color_magenta"),rs.getFloat("color_yellow")));

            }
            return temp;

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        finally {
            conn.close();

        }
        return null;
    }

    @Override
    public List<InkUsage> FilterInkUsageBasedOnMachineIDs() throws SQLException {
    List<InkUsage> temp = new ArrayList<>();
    Connection conn = null;

    String url = databaseUtilProd.GetUrl();
    String user = databaseUtilProd.GetUser();
    String password = databaseUtilProd.GetPassword();


        try {

        conn = DriverManager.getConnection(url,user,password);
            statement = conn.prepareStatement("SELECT * FROM ink_usage_over_time GROUP BY date");
            ResultSet rs = statement.executeQuery();


        while (rs.next() ) {
            temp.add(factory.createInkUsage(rs.getString("machine_id"),rs.getDate("date"),rs.getFloat("color_black"),rs.getFloat("color_cyan"),rs.getFloat("color_magenta"),rs.getFloat("color_yellow")));


        }
        return temp;

    } catch (Exception e) {
        System.err.println("Got an exception!");
        System.err.println(e.getMessage());
    }

        finally {
        conn.close();

    }
        return null;
}


}