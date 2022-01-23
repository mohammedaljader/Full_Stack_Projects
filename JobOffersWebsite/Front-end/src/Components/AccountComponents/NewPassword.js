import { useRef } from 'react';
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
import { useHistory } from 'react-router-dom';


export default function NewPassword(){

   const history = useHistory();
   const alert = useAlert()
   let usernameRef = useRef();
   let passwordRef = useRef();

   

    const  handleSubmit  = (event) =>{
      event.preventDefault();

      const enteredUsername = usernameRef.current.value;
      const enteredPassword = passwordRef.current.value;

      const user = {
          password: enteredPassword,
          username: enteredUsername
      }
      console.log(user);

       UserService.editPassword(user).then(() => {
         alert.success("The password is updated successfully!!", {timeout: 2000})
         setTimeout(function(){
            history.replace('/login');
         }, 2000);
      }).catch(err=>{console.log(err);
        alert.error("Your username is incorrect!")
      });  
  }

  return (
    <form
     onSubmit={handleSubmit}
    >
      <Card>
        <CardHeader
          title="New Password"
        />
        <Divider />
        <CardContent>
          <Grid
            container
            spacing={3}
          >
            <Grid
              item
              md={20}
              xs={20}
            >
              <TextField
                fullWidth
                helperText="Please specify the first name"
                label="Username"
                name="username"
                inputRef={usernameRef}
                required
                variant="outlined"
              />
            </Grid>
            <Grid
              item
              md={20}
              xs={20}
            >
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
          </Grid>
        </CardContent>
        <Divider />
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'flex-end',
            p: 2
          }}
        >
          <Button
            color="primary"
            variant="contained"
            type = "submit"
          >
            Save
          </Button>
        </Box>
      </Card>
    </form>
  );
};