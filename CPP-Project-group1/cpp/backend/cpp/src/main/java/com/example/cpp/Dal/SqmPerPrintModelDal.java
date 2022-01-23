package com.example.cpp.Dal;

import com.example.cpp.DalInterfaces.ISqmPerPrintModeDal;
import com.example.cpp.DatabaseUtilProd;
import com.example.cpp.Factories.SqmPerPrintModeFactory;
import com.example.cpp.model.SqmPerPrintMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SqmPerPrintModelDal implements ISqmPerPrintModeDal {

    private final SqmPerPrintModeFactory factory;
    private PreparedStatement statement;
    private DatabaseUtilProd databaseUtilProd;

    @Autowired
    public SqmPerPrintModelDal() {
        this.factory = new SqmPerPrintModeFactory();
        this.databaseUtilProd = new DatabaseUtilProd();

    }

    @Override
    public List<SqmPerPrintMode> ListAllSqmPerPrintModeByTimeFrame(String startDate, String endDate) throws SQLException {
        List<SqmPerPrintMode> temp = new ArrayList<>();

        Connection conn = null;


        Timestamp startTimestamp = Timestamp.valueOf(startDate);
        Timestamp endTimestamp = Timestamp.valueOf(endDate);

        String url = databaseUtilProd.GetUrl();
        String user = databaseUtilProd.GetUser();
        String password = databaseUtilProd.GetPassword();
        try {

            conn = DriverManager.getConnection(url, user, password);

            statement = conn.prepareStatement(" SELECT *\n" +
                    "FROM (\n" +
                    "SELECT @row := @row +1 AS rownum, `sqm_per_print_mode`.*\n" +
                    "FROM (\n" +
                    "SELECT @row :=0) r, `sqm_per_print_mode`)\n" +
                    "ranked\n" +
                    "WHERE date BETWEEN '"+startTimestamp+"' AND '"+endTimestamp+"'\n"+
                    "GROUP BY date");
            ResultSet rs = statement.executeQuery();


            while (rs.next()) {

                temp.add(factory.createSqmPerPrintMode(rs.getInt("machine_id"), rs.getDate("date"), rs.getFloat("1_pass"), rs.getFloat("1_pass_highDensity"), rs.getFloat("2_pass"), rs.getFloat("4_pass"), rs.getFloat("8_pass"), rs.getFloat("8_pass_highDensity"), rs.getFloat("16_pass"), rs.getFloat("other")));
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

