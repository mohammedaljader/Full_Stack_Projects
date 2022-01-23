package com.example.cpp.Factories;

import com.example.cpp.model.MediaCategory;

import java.sql.Date;

public class MediaCategoryFactory {

    public MediaCategoryFactory(){

    }
    public MediaCategory createMediaCategory(String machine_id, Date date, float film, float lightPaper, float heavyPaper, float lightBanner, float textile, float monomericVinyl, float canvas, float polymericCastVinyl, float heavyBanner, float paper, float thickFilm)
    {
        MediaCategory mediaCategory = new MediaCategory(machine_id,date,film,lightPaper,heavyPaper,lightBanner,textile,monomericVinyl,canvas,polymericCastVinyl,heavyBanner,paper,thickFilm);

        return  mediaCategory;
    }

}
