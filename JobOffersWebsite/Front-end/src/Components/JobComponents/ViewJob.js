import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import EuroIcon from '@mui/icons-material/Euro';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import JobService from '../../Services/JobService';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import classes from './ViewJob.module.css';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import { Link } from 'react-router-dom';
import CardActions from '@mui/material/CardActions';
import NotFound from '../UI/NotFound';

export default function ViewJob() {
    const [job, setJob] = React.useState([]);
    const [load, setLoad] = React.useState(false);
    let param = window.location.pathname;  //to find the path of id /viewjob/1
    let id = param.split('/').pop();

    React.useEffect(() => {
      JobService.getJobById(id).then((response) => {
        console.log(response.data);
        setJob(response.data);
        setLoad(true)
      });
      setLoad(true)
    }, [id]);



  if(Object.keys(job).length === 0 && load === true) return <NotFound/>

  return (
    <Container sx={{ py: 1 }} maxWidth="md" className={classes.containerViewJob} >
    <Grid container spacing={3}>
        <Grid item key={job.id} xs={30} sm={10} md={14}>
            <Card sx={{ maxWidth: 320 }}  >
            <CardMedia
                component="img"
                height="140"
                image={job.jobImage}
                alt="green iguana"
            />
            <CardContent sx={{ flexGrow: 1 }}>
                <Typography gutterBottom variant="h5" component="div">
                {job.jobName}
                </Typography>
                <Typography variant="body2" >
                {job.jobDescription}
                </Typography>
                <Typography variant="body3">
                        <EuroIcon/>{job.jobSalary} per hour
                </Typography>
                <Typography variant="body3">
                        <LocationOnIcon/> {job.jobAddress} 
                </Typography> 
            </CardContent>
            <CardActions>
                <Tooltip title="Apply for job">
                    <Button variant="contained" size="small" style={{background:"#032892"}}>
                        <Link  to={`/applyForJob/${job.id}`} style={{textDecoration: "none", color: "white"}} >Apply</Link>
                    </Button>
                </Tooltip>
                </CardActions>
            </Card>
            </Grid>
        </Grid>
      </Container>
  );
}
