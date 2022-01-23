import React, {useState, useEffect} from 'react';
// import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';
import NotFound from '../Components/UI/NotFound';
import JobService from '../Services/JobService';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';

// const ExpandMore = styled((props) => {
//   const { expand, ...other } = props;
//   return <IconButton {...other} />;
// })(({ theme, expand }) => ({
//   transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
//   marginLeft: 'auto',
//   transition: theme.transitions.create('transform', {
//     duration: theme.transitions.duration.shortest,
//   }),
// }));

export default function TestHome() {
    const [jobs, setJobs] = useState(null);

    //const [expanded, setExpanded] = useState(false);

    // const handleExpandClick = () => {
    //   setExpanded(!expanded);
    // };

    useEffect(() => {
      JobService.getJobs().then((response) => {
        console.log(response.data);
        setJobs(response.data);
      });
    }, []);// Runs ONCE after initial rendering
   
  
    if (!jobs) return <NotFound/>;

  return (
      <Container sx={{ py: 1 }} maxWidth="md">
        {/* End hero unit */}
        <Grid container spacing={3}>
          {jobs.map((job) => (
            <Grid item key={job.id} xs={10} sm={3} md={4}>
            <Card sx={{ maxWidth: 345 }}>
              <CardMedia
                component="img"
                height="194"
                image="https://source.unsplash.com/random"
                alt="Paella dish"
              />
              <CardContent>
                <Typography variant="body2" color="text.secondary" align= "center">
                  {job.jobName}
                </Typography>
                <Typography variant="body4" color="text.secondary" >
                  {job.jobAddress}
                </Typography>
              </CardContent>
              <CardActions disableSpacing>
                <IconButton aria-label="add to favorites">
                  <FavoriteIcon />
                </IconButton>
                <IconButton aria-label="share">
                  <ShareIcon />
                </IconButton>
              </CardActions>
            </Card>
          </Grid>
          ))}
        </Grid>
      </Container>
  );
}