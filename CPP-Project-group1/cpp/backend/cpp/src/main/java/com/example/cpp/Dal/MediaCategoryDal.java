package com.example.cpp.Dal;

import com.example.cpp.DalInterfaces.IMediaCategoryDal;
import com.example.cpp.DatabaseUtilProd;
import com.example.cpp.Factories.MediaCategoryFactory;
import com.example.cpp.model.MediaCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MediaCategoryDal implements IMediaCategoryDal {
    private final MediaCategoryFactory factory;
    private PreparedStatement statement;
    private DatabaseUtilProd databaseUtilProd;


    @Autowired
    public MediaCategoryDal() {

        this.factory = new MediaCategoryFactory();
        this.databaseUtilProd = new DatabaseUtilProd();
    }

    @Override
    public List<MediaCategory> ListMediaCategory() throws SQLException {
        List<MediaCategory> temp = new ArrayList<>();

        String url = databaseUtilProd.GetUrl();
        String user = databaseUtilProd.GetUser();
        String password = databaseUtilProd.GetPassword();

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM `media_category_over_time` GROUP BY date ");
            while (rs.next()) {
                temp.add(factory.createMediaCategory(rs.getString("machine_id"), rs.getDate("date"), rs.getFloat("Film"), rs.getFloat("LightPaper"), rs.getFloat("HeavyPaper"), rs.getFloat("LightBanner"),rs.getFloat("Textile"),rs.getFloat("MonomericVinyl"),rs.getFloat("Canvas"), rs.getFloat("PolymericCastVinyl"),rs.getFloat("HeavyBanner"),rs.getFloat("Paper"),rs.getFloat("ThickFilm")));
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
    public List<MediaCategory> ListMediaCategoryByDate(String startDate, String endDate) throws SQLException {
        List<MediaCategory> temp = new ArrayList<>();

        Connection conn = null;


        Timestamp startTimestamp = Timestamp.valueOf(startDate);
        Timestamp endTimestamp = Timestamp.valueOf(endDate);

        String url = databaseUtilProd.GetUrl();
        String user = databaseUtilProd.GetUser();
        String password = databaseUtilProd.GetPassword();
        try {

            conn = DriverManager.getConnection(url, user, password);

            statement = conn.prepareStatement(" SELECT *\n" +
                            "                    FROM (\n" +
                            "                            SELECT @row := @row +1 AS rownum, media_category_over_time.*\n" +
                            "            FROM (\n" +
                            "                    SELECT @row :=0) r, media_category_over_time\n" +
                            "    ) ranked\n" +
                            "            WHERE date BETWEEN '"+startTimestamp+"' AND '"+endTimestamp+"'\n"+
                            "GROUP BY date");
            ResultSet rs = statement.executeQuery();


            while (rs.next()) {
              temp.add(factory.createMediaCategory(rs.getString("machine_id"), rs.getDate("date"), rs.getFloat("Film"), rs.getFloat("LightPaper"), rs.getFloat("HeavyPaper"), rs.getFloat("LightBanner"), rs.getFloat("Textile"), rs.getFloat("MonomericVinyl"), rs.getFloat("Canvas"), rs.getFloat("PolymericCastVinyl"), rs.getFloat("HeavyBanner"), rs.getFloat("Paper"), rs.getFloat("ThickFilm")));
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

    @Override
    public List<MediaCategory> GetMediaCategoryByMachineId() throws SQLException {
        List<MediaCategory> temp = new ArrayList<>();

        String url = databaseUtilProd.GetUrl();
        String user = databaseUtilProd.GetUser();
        String password = databaseUtilProd.GetPassword();

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM `media_category_over_time` GROUP BY date ");
            while (rs.next()) {
                temp.add(factory.createMediaCategory(rs.getString("machine_id"), rs.getDate("date"), rs.getFloat("Film"), rs.getFloat("LightPaper"), rs.getFloat("HeavyPaper"), rs.getFloat("LightBanner"),rs.getFloat("Textile"),rs.getFloat("MonomericVinyl"),rs.getFloat("Canvas"), rs.getFloat("PolymericCastVinyl"),rs.getFloat("HeavyBanner"),rs.getFloat("Paper"),rs.getFloat("ThickFilm")));
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

