import React from 'react';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import Contactuscom from './Contactuscom';

// "https://source.unsplash.com/random"
//Developer name
//Front-End Developer
//https://www.facebook.com
//https://nl.linkedin.com
//https://github.com

//image , name , job , facebook , linkedin and github

function ContactUs() {
    
    return (
        <>
        <h1 style={{marginLeft: "600px"}}> Contact us </h1>
        <Container sx={{ py: 1 }} maxWidth="md">
          <Grid container spacing={3}>
           <Contactuscom 
           image="https://source.unsplash.com/random" 
           name= "Developer name" 
           job= "Front-End Developer"
           facebook="https://www.facebook.com"
           linkedin="https://nl.linkedin.com"
           github="https://github.com"
           />   

          <Contactuscom 
           image="https://source.unsplash.com/random" 
           name= "Developer name" 
           job= "Back-End Developer"
           facebook="https://www.facebook.com"
           linkedin="https://nl.linkedin.com"
           github="https://github.com"
           />   

         <Contactuscom 
           image="https://source.unsplash.com/random" 
           name= "Developer name" 
           job= "Full-stack Developer"
           facebook="https://www.facebook.com"
           linkedin="https://nl.linkedin.com"
           github="https://github.com"
           />   
                   
          </Grid>      
      </Container>
      </>
    )
}

export default ContactUs