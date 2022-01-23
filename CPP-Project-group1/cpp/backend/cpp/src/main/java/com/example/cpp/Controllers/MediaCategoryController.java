package com.example.cpp.Controllers;

import com.example.cpp.ServiceInterfaces.IMediaCategoryService;
import com.example.cpp.model.MediaCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/engineControl/media")
public class MediaCategoryController {

    private final IMediaCategoryService mediaCategoryService;

    @Autowired
    public MediaCategoryController(IMediaCategoryService mediaCategoryService)
    {
        this.mediaCategoryService = mediaCategoryService;

    }

    @GetMapping("/testMedia")
    public List<MediaCategory> getMediaCategory() throws SQLException {
        return mediaCategoryService.getMediaCategories();
    }
    @PostMapping("/testMedia1")
    public List<MediaCategory> getMediaCategoriesOnGivenTime(@RequestBody MediaCategoriesDTO mediaCategoriesDTO) throws SQLException {
        return mediaCategoryService.getMediaCategoryByTimeframe(mediaCategoriesDTO.getStartDate(),mediaCategoriesDTO.getEndDate());
    }
    @PostMapping("/mediaCategoryByid")
    public List<MediaCategory> getMediaCategoryById(@RequestBody MediaCategoriesDTO mediaCategoriesDTO) throws SQLException {
        return mediaCategoryService.FilterMediaCatergoriesBasedOnMachineID(mediaCategoriesDTO.getMachines(), mediaCategoriesDTO.getStartDate(),mediaCategoriesDTO.getEndDate());
    }
}

class MediaCategoriesDTO {
    private List<String> machines;
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<String> getMachines() {
        return machines;
    }
}
