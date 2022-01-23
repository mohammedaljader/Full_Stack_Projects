import React, {useState, useEffect} from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import EuroIcon from '@mui/icons-material/Euro';
import ScheduleIcon from '@mui/icons-material/Schedule';
import Tooltip from '@mui/material/Tooltip';
import Stack from '@mui/material/Stack';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import VisibilityIcon from '@mui/icons-material/Visibility';
import { Link } from 'react-router-dom';
import { useAlert } from 'react-alert';
import FavoriteJobService from '../../Services/FavoriteJobService';


function FavoriteList() {
    const [jobs, setJobs] = useState([]);
    const [load, setLoad] = useState(false);
    const alert = useAlert()

   
    let activeUser = JSON.parse(localStorage.getItem('user'));

  

  useEffect(() => {
    FavoriteJobService.getAllFavoriteJobsByUser(activeUser.username).then((response) => {
      console.log(response.data);
      setJobs(response.data);
      setLoad(true);
    });
    // setLoad(true);
  }, [activeUser.username]);

  

  function DeleteJob(id){
    FavoriteJobService.deleteJobFromList(id).catch(err=>{console.log(err);
      alert.error("Error: Can't delete the job from the list, Please try again! ", {timeout: 2000})
    });
    alert.success("Job deleted successfully from the list!" , {timeout: 2000})
    const newJobs = [...jobs];
    const index = newJobs.indexOf(id);
    newJobs.splice(index, 1);
    setJobs(newJobs);
  }
  


  if(load === true && Object.keys(jobs).length === 0){
    return <h1>No Jobs in the list available right now!</h1>
  }

    return (
      <Container sx={{ py: 1 }} maxWidth="md">
      <Grid container spacing={3}>
        {jobs.map((job) => (
          <Grid item key={job.jobId} xs={10} sm={3} md={4}>
            <Card
              sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
              <CardMedia
              component="img"
              height="194"
              image={job.jobImage}
              alt="job image"
            />
                  <CardContent sx={{ flexGrow: 1 }}>
                  <Typography gutterBottom variant="h5" component="h3">
                      {job.jobName}
                  </Typography>
                  <Typography variant="body3">
                      <EuroIcon/>{job.jobSalary} per hour
                  </Typography>
                  <br/>
                  <Typography variant="body3">
                      <ScheduleIcon/> {job.savedAt.substring(0, 10)} 
                  </Typography>
                  </CardContent>
                <CardActions>
              </CardActions>
                <Stack direction="row" alignItems="center" spacing={0} >
                <Tooltip title={"deleting the job from the list!"}>
                  <IconButton aria-label="delete" size="large" style={{color:"#032892"}} onClick={() => DeleteJob(job.favoriteJobId)}>
                    <DeleteIcon fontSize="inherit" />
                  </IconButton>
                  </Tooltip>
                  <Tooltip title="View Job">
                  <IconButton color="primary" size="small" >
                      <Link to={`/viewJob/${job.jobId}`} ><VisibilityIcon style={{color:"#032892"}} /></Link>
                  </IconButton>
                   </Tooltip>
                </Stack>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  )
}

export default FavoriteList



// taskkill -F -IM node.exe