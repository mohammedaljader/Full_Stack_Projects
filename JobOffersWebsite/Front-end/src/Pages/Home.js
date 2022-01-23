import React, {useState, useEffect} from 'react';
import NotFound from '../Components/UI/NotFound'; // eslint-disable-line
import JobService from '../Services/JobService';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import EuroIcon from '@mui/icons-material/Euro';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import IconButton from '@mui/material/IconButton';
import FavoriteIcon from '@mui/icons-material/Favorite';
import Tooltip from '@mui/material/Tooltip';
import { Link } from 'react-router-dom';
import FavoriteJobService from '../Services/FavoriteJobService';
import { useAlert } from 'react-alert'
import FilterJob from '../Components/JobComponents/FilterJob';
import './style/home.css'


function Home() {
    const [jobs, setJobs] = useState([]);
    const [jobCount, setJobCount] = useState(3);
    //const [showButton, setShowButton] = useState(true);
    const alert = useAlert()

  useEffect(() => {
    getJobs();
  }, []);
  
  
  const loadMoreJobs = () => {
    setJobCount(jobCount + 3)
    // if(jobCount >= jobs.length){
    //   setShowButton(false)
    // }
  };

  async function getJobs(){
    await JobService.getJobs().then((response) => {
      console.log(response.data);
      setJobs(response.data);
    });
  }
 
  let isLoggedIn= false;
  let user = localStorage.getItem('user');
  if(user !== null){
    isLoggedIn = true; 
  }
  else{
    isLoggedIn = false;
  }

  let activeUser = JSON.parse(localStorage.getItem('user'));

  const addJobToList = (username, jobId) => {
    var data ={
      username: username,
      jobId: jobId
    }
    FavoriteJobService.addJobToFavorites(data).then(() => {
      alert.success("Job added successfully to your favorite list!" , {timeout: 2000})
    }).catch(err=>{console.log(err);
        alert.error('Can not add this job to the list!', {timeout: 2000})
      });
      console.log(data)  
  }

  const filterJobByCatogory = (filterTerm) => {
      if(filterTerm.categoryId === "1"){
        getJobs();
        setJobCount(3);
      }
      else{
        getJobsByCategory(filterTerm.categoryId)
        setJobCount(3);
      }
  };

  const filterJobByNameOrCity = (filterTerm) => {
    if(filterTerm === ""){
      getJobs();
      setJobCount(3);
    }
    getJobsByNameOrCity(filterTerm);
    setJobCount(3);
  };

  const clearFilter = () => {
    getJobs();
    setJobCount(3);
  };

  
  const getJobsByNameOrCity = async(filterSearch) =>{
    await JobService.filterJobs(filterSearch).then((response) => {
      setJobs(response.data);
    });
  }

  const getJobsByCategory = async(categoryId) =>{
    await JobService.getJobsByCategory(categoryId).then((response) => {
      setJobs(response.data);
    });
  }

  

    return (
      <>
        <Container sx={{ py: 1 }} maxWidth="md" id="jobs">
         <FilterJob FilterJob={filterJobByCatogory} FilterJobByName={filterJobByNameOrCity} clear={clearFilter}/>
        {
          Object.keys(jobs).length === 0 
          ? 
          <h1 id="nojobs">No Jobs available right now!</h1>
          :
          <Grid container spacing={3}>
            {jobs.slice(0, jobCount).map((job) => (
              <Grid item key={job.id} xs={10} sm={3} md={4}>
              <Tooltip title={`Description: ${job.jobDescription}`} placement="top-start">
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
                      <LocationOnIcon/> {job.jobAddress} 
                    </Typography>
                  </CardContent>
                  <CardActions style={{marginLeft: "28px"}}>
                      
                  { isLoggedIn === false ?
                    <>
                    <Tooltip title="View job">
                      <Button variant="contained" size="small" style={{background:"#032892"}}>
                        <Link  to={`/login`} style={{textDecoration: "none", color: "white"}} >View</Link>
                      </Button>
                    </Tooltip> 
                      <Tooltip title="Apply for job">
                        <Button variant="contained" size="small" style={{background:"#032892"}}>
                            <Link  to={`/login`} style={{textDecoration: "none", color: "white"}} >Apply</Link>
                        </Button>
                      </Tooltip>
                      <Tooltip title="Add job to Favorite List">
                      <Link  to={`/login`} style={{textDecoration: "none", color: "white"}} >
                        <IconButton color="primary" size="small" >
                          <FavoriteIcon  style={{color:"#032892"}}/>
                        </IconButton>
                      </Link>
                    </Tooltip>
                    </>
                      : 
                      <>
                      <Tooltip title="View job">
                        <Button variant="contained" size="small" style={{background:"#032892"}}>
                        <Link  to={`/viewJob/${job.id}`} style={{textDecoration: "none", color: "white"}} >View </Link>
                        </Button>
                        </Tooltip> 
                      <Tooltip title="Apply for job">
                      <Button variant="contained" size="small" style={{background:"#032892"}}>
                          <Link  to={`/applyForJob/${job.id}`} style={{textDecoration: "none", color: "white"}} >Apply</Link>
                      </Button>
                      </Tooltip>
                      <Tooltip title="Add job to Favorite List">
                          <IconButton color="primary" size="small" onClick={() => addJobToList(activeUser.username, job.id)}  >
                            <FavoriteIcon  style={{color:"#032892"}}/>
                          </IconButton>
                      </Tooltip>
                      </>
                    }   
                  </CardActions>
                </Card>
              </Tooltip>
              </Grid>
            ))}
          </Grid>
        }   
      </Container>
        {
            jobCount <= jobs.length
            ? 
              <Tooltip title="Load more jobs" placement="top-start">
                <Button 
                variant="contained"
                 size="large"
                 className='loadBtn'
                  style={{background:"#cc2062", marginLeft: "350px", marginTop: "20px"}} onClick={loadMoreJobs}>
                  Load more
                </Button>
            </Tooltip>
        :
        <></>
        }
    </>
    )
}

export default Home