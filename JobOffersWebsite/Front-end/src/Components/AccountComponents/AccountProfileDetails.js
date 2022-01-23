import {React, useRef } from 'react';
import {
  Box,
  Button,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Grid,
  TextField
} from '@mui/material';
import UserService from '../../Services/UserService';
import { useAlert } from 'react-alert';


export default function AccountProfileDetails(props){

   const alert = useAlert()
   let nameRef = useRef();
   let emailRef = useRef();
   let addressRef = useRef();
   let passwordRef = useRef();

  
    const  handleSubmit  = (event) =>{
      event.preventDefault();

      const enteredName = nameRef.current.value;
      const enteredEmail = emailRef.current.value;
      const enteredPassword = passwordRef.current.value;
      const enteredAddress = addressRef.current.value;

      const user = {
          userId: props.user.userId,
          name: enteredName,
          email: enteredEmail,
          address: enteredAddress,
          password: enteredPassword,
      }
      console.log(user);

       UserService.editProfile(user).then(() => {
         alert.success("Your Profile updated successfully!!", {timeout: 2000})
         setTimeout(function(){
            window.location.reload(1);
         }, 2000);
      }).catch(err=>{console.log(err);
        alert.error("Error: Can't update the profile, Please try again! ", {timeout: 2000})
      });  
  }

  return (
    <form
     onSubmit={handleSubmit}
    >
      <Card>
        <CardHeader
          subheader="The information can be edited"
          title="Profile"
        />
        <Divider />
        <CardContent>
          <Grid container spacing={3}  >
            <Grid item md={20} xs={20} >
              <TextField
                fullWidth
                helperText="Please specify the first name"
                label="Name"
                name="Name"
                inputRef={nameRef}
                required
                defaultValue={props.user.name}
                variant="outlined"
              />
            </Grid>
            <Grid item md={20} xs={20} >
              <TextField
                fullWidth
                helperText="Please specify the email address"
                label="Email Address"
                name="email"
                inputRef={emailRef}
                required
                defaultValue={props.user.email}
                variant="outlined"
              />
            </Grid>
            <Grid item md={6} xs={12} >
              <TextField
                fullWidth
                helperText="Please specify the password"
                label="Password"
                name="Password"
                type="password"
                inputRef={passwordRef}
                required
                variant="outlined"
              />
            </Grid>
            <Grid item md={6} xs={12} >
              <TextField
                fullWidth
                helperText="Please specify the address"
                label="Address"
                name="address"
                inputRef={addressRef}
                required
                defaultValue={props.user.address}
                variant="outlined"
              />
            </Grid>
          </Grid>
        </CardContent>
        <Divider />
        <Box sx={{ display: 'flex', justifyContent: 'flex-end',  p: 2 }} >
          <Button
            style={{background:"#032892"}}
            variant="contained"
            type = "submit"
          >
            Save details
          </Button>
        </Box>
      </Card>
    </form>
  );
};