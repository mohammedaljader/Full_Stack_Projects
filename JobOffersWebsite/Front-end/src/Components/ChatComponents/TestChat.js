import React, { useState, useEffect, useRef } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import Divider from '@material-ui/core/Divider';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import Fab from '@material-ui/core/Fab';
import SendIcon from '@material-ui/icons/Send';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import MessageItem from './MessageItem';
import NotFound from '../UI/NotFound';
import { useAlert } from 'react-alert';
import Container from '@mui/material/Container';


const ENDPOINT = "http://localhost:8080/chat";

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
  chatSection: {
    width: '75%',
    height: '80vh',
    justifyContent: "center"
  },
  headBG: {
      backgroundColor: '#e0e0e0'
  },
  borderRight500: {
      borderRight: '1px solid #e0e0e0'
  },
  messageArea: {
    height: '70vh',
    overflowY: 'auto'
  }
});




const TestChat = () => {
  const classes = useStyles();
  const alert = useAlert();

  const [stompClient, setStompClient] = useState(null);
  const inputRef = useRef();
  const [messages, setMessages] = useState([]);
  let activeUser = JSON.parse(localStorage.getItem('user'));
  let user = localStorage.getItem('user')


  useEffect(() => {
    const socket = SockJS(ENDPOINT);
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      stompClient.subscribe('/topic/public', (data) => {
        
        onMessageReceived(data);
      });
      stompClient.send('/app/chat.register', {}, JSON.stringify({sender: activeUser.username, type: "JOIN"}))
    });
    setStompClient(stompClient);
    return()=>{
      if(stompClient !== null){
        stompClient.send('/app/chat.send',{}, JSON.stringify({sender: activeUser.username, type: "LEAVE"}))
        stompClient.disconnect(function (frame){
          console.log("Stomp is succesfully disconnected")
        })
      }
    }
  }, [activeUser.username]); // eslint-disable-line


  function sendMessage() {
    const enteredMsg = inputRef.current.value;
    if(enteredMsg !== ""){
        stompClient.send("/app/chat.send", {}, JSON.stringify({'content': enteredMsg, "sender" : activeUser.username, type: "CHAT" }));  
        inputRef.current.value= ''
    }
  };


  function onMessageReceived(data){
    const result=  JSON.parse(data.body);
    if(result.type === "JOIN"){
      result.content = result.sender + " joined"
      alert.info(result.content)
    }
    else if(result.type === "LEAVE"){
      result.content = result.sender + " left"
      alert.info(result.content)  
    }
    else{
      setMessages(prevItems => [...prevItems, {result}]);  
    }
  };

  if(user === null) return <NotFound />
  return (
      <Container sx={{ py: 0 }} maxWidth="md">
        <Grid container style={{marginLeft:"100px"}}>
            <Grid item xs={1} >
                <Typography variant="h5" className="header-message"><b>Chat</b></Typography>
            </Grid>
        </Grid>
        <Grid container component={Paper} className={classes.chatSection} style={{marginLeft:"100px"}}>
            <Grid item xs={9}>
                <List className={classes.messageArea} >
                {messages.map((msg) => (
                    <ListItem key={msg.result.content} >
                        <Grid container >
                            <MessageItem msg={msg.result}  />
                        </Grid>     
                    </ListItem> 
                ))}
                </List>
                
                <Divider />
                <Grid container >
                    <Grid item xs={11} >
                        <TextField id="outlined-basic-email"
                         label="Type Something"
                         inputRef={inputRef}
                         fullWidth />
                    </Grid>
                    <Grid xs={1} >
                        <Fab color="primary" aria-label="add" onClick={sendMessage} ><SendIcon /></Fab>
                    </Grid>
                </Grid>
            </Grid>
        </Grid>
      </Container>
  );
}

export default TestChat;