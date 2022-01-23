package org.project.joboffers.ServiceInterfaces;

import org.project.joboffers.DTO.FileDTO;

public interface IFileService {
    FileDTO getImageFileByJobId(String jobId);
    FileDTO getCVFileByJobApplicationId(String jobApplicationId);
}
