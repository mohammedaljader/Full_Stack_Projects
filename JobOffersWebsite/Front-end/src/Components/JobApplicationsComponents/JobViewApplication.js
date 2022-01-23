import React from 'react'
import {
    Avatar,
    Box,
    Typography
  } from '@mui/material';
import EuroIcon from '@mui/icons-material/Euro';
import LocationOnIcon from '@mui/icons-material/LocationOn';
  
  
export default function JobViewApplication(props){
    return(
        <Box sx={{ alignItems: 'center',  display: 'flex', flexDirection: 'column' }} >
          <Avatar
            src= {props.job.jobImage}
            sx={{
              height: 74,
              mb: 2,
              width: 74
            }}
          />
          <Typography
            color="textPrimary"
            gutterBottom
            variant="h6"
          >
            Job name: {props.job.jobName}
          </Typography>
          <Typography
            color="textSecondary"
            variant="body2"
            sx={{  mb: 1 }}
          >
            Salary: <EuroIcon/>{props.job.jobSalary} per hour
          </Typography>
          <Typography
            color="textSecondary"
            variant="body2"
            sx={{  mb: 2 }} >
           <LocationOnIcon/> City: {props.job.jobAddress}
          </Typography>
        </Box>
    );
}