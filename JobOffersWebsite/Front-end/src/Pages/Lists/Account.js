import { useState, useEffect } from 'react';
import { Box, Container, Grid, Typography } from '@mui/material';
import {AccountProfile} from '../../Components/AccountComponents/AccountProfile';
import  AccountProfileDetails  from '../../Components/AccountComponents/AccountProfileDetails';
import UserService from '../../Services/UserService';

function Account(){
    const [user, setUser] = useState([]);

    let currentUser = JSON.parse(localStorage.getItem('user'));
    
    useEffect(() => {
        UserService.getCurrentUser(currentUser.username).then((response) => {
            console.log(response.data);
            setUser(response.data);
          });
      }, [currentUser.username]);


    return (
        <Box component="main"  sx={{ flexGrow: 1,  py: 1 }}>
        <Container maxWidth="lg">
          <Typography sx={{ mb: 3 }} variant="h4">
            Account
          </Typography>
          <Grid container spacing={3} >
            <Grid item lg={4} md={6} xs={12} >
                <AccountProfile user={user} />
            </Grid>
            <Grid item lg={8} md={6} xs={12} >
                <AccountProfileDetails user={user}/>
            </Grid>
          </Grid>
        </Container>
      </Box>
    );
}

export default Account;