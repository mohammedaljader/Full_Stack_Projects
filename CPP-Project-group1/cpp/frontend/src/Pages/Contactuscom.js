import React from 'react'
import Button from '@mui/material/Button'; // eslint-disable-line
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';  // eslint-disable-line
import IconButton from '@mui/material/IconButton';
import Tooltip from '@mui/material/Tooltip';
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import FacebookIcon from '@mui/icons-material/Facebook';
import GitHubIcon from '@mui/icons-material/GitHub';


function Contactuscom(props) {
    return (
           <Grid item key="1" xs={10} sm={3} md={4}>
            <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
                <CardMedia component="img" height="194" image={props.image}  alt="Developer profile" />
                <CardContent sx={{ flexGrow: 1 }}>
                <Typography gutterBottom variant="h5" component="h3" style={{textAlign: "center"}}>
                    {props.name}
                </Typography>
                <Typography variant="body1" style={{textAlign: "center"}}>
                    {props.job}
                </Typography>
                </CardContent>
                <CardActions style={{marginLeft: "20px"}}>
                    <Tooltip title="Facebook">
                    <a  href={props.facebook} style={{textDecoration: "none", color: "white"}} >
                        <IconButton color="primary" size="medium"  >
                                <FacebookIcon  style={{color:"#032892", fontSize: "45px"}}/>
                        </IconButton>
                    </a>
                </Tooltip> 

                <Tooltip title="Facebook">
                    <a  href={props.linkedin} style={{textDecoration: "none", color: "white"}} >
                        <IconButton color="primary" size="medium"  >
                                <LinkedInIcon  style={{color:"#032892", fontSize: "45px"}}/>
                        </IconButton>
                    </a>
                </Tooltip>   

                <Tooltip title="Facebook">
                    <a  href={props.github} style={{textDecoration: "none", color: "white"}} >
                        <IconButton color="primary" size="medium"  >
                                <GitHubIcon  style={{color:"#000", fontSize: "40px"}}/>
                        </IconButton>
                    </a>
                </Tooltip>           
                </CardActions>
            </Card>
         </Grid>
    )
}

export default Contactuscom
