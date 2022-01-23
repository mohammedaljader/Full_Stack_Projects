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
import { useAlert } from 'react-alert';
import { useHistory } from 'react-router-dom';
import { useLocation } from "react-router-dom";
import MessageService from '../../Services/MessageService';

function SendMessage() {
    const history = useHistory();
    const alert = useAlert();
    const location = useLocation();
    const data = location.state;
    let subjectRef = useRef();
    let messageRef = useRef();

    console.log(data)


    const  handleSubmit  = (event) =>{
        event.preventDefault();
  
        const enteredSubject = subjectRef.current.value;
        const enteredMessage = messageRef.current.value;
  
        const message = {
            subject: enteredSubject,
            message: enteredMessage,
            senderUser: data.senderUser,
            receiverUser: data.receiverUser
        }
        console.log(message);
  
         MessageService.sendMessage(message).then(() => {
           alert.success("The message is sent successfully!!", {timeout: 2000})
           setTimeout(function(){
              history.replace('/');
           }, 2000);
        }).catch(err=>{console.log(err);
          alert.error("Oops, there is a problem.. Please try again!!")
        });  
    }

    return (
     <form onSubmit={handleSubmit} >
      <Card>
        <CardHeader
          title="Send a message" />
        <Divider />
        <CardContent>
          <Grid container spacing={3} >
            <Grid item md={20}  xs={20} >
              <TextField
                fullWidth
                helperText="Please specify the subject"
                label="Subject"
                name="subject"
                inputRef={subjectRef}
                required
                variant="outlined"
              />
            </Grid>
            <Grid item md={20}  xs={20} >
            <TextField
                fullWidth
                helperText="Please specify the message"
                label="Message"
                name="Message"
                inputRef={messageRef}
                required
                id="outlined-multiline-static"
                multiline
                rows={10}
              />
            </Grid>
          </Grid>
        </CardContent>
        <Divider />
        <Box  sx={{  display: 'flex', justifyContent: 'flex-end', p: 2 }} >
          <Button color="primary" variant="contained" type = "submit">
            Send
          </Button>
        </Box>
      </Card>
    </form>
    )
}

export default SendMessage
