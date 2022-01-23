import React from 'react';
import {
    Card,
    Grid
  } from '@mui/material';

function MessageItem(props){

    let activeUser = JSON.parse(localStorage.getItem('user'));


    if(activeUser.username === props.msg.sender){
        return (    
            <Grid item xs={12}  align="right">
                 <Card  style={{width: "250px"}}>
                    <p style={{color: "blue"}}>{props.msg.content}</p>
                    <p>{props.msg.sender}</p>
                </Card>
            </Grid>
        )
    }
    else{
        return (    
            <Grid item xs={12} align="left">
            <Card  style={{width: "250px"}}>
               <p style={{color: "red"}}>{props.msg.content}</p>
               <p>{props.msg.sender}</p>
           </Card>
       </Grid>
        )
    }
    
}
export default MessageItem;
