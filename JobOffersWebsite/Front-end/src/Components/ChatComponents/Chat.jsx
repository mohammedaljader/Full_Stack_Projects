import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { useAlert } from 'react-alert';

// Set the backend location
const ENDPOINT = "http://localhost:8080/chat";

function Chat() {

  const [stompClient, setStompClient] = useState(null);
  const [msgToSend, setSendMessage] = useState("Enter your message here!");
  const [message, setMessage] = useState([]);
  const alert = useAlert();


  useEffect(() => {
    // use SockJS as the websocket client
    const socket = SockJS(ENDPOINT);
    // Set stomp to use websockets
    const stompClient = Stomp.over(socket);
    // connect to the backend
    stompClient.connect({}, () => {
      // subscribe to the backend
      stompClient.subscribe('/topic/public', (data) => {
        console.log(data);
        onMessageReceived(data);
      });
    });
    // maintain the client for sending and receiving
    setStompClient(stompClient);
  }, []);

  let activeUser = JSON.parse(localStorage.getItem('user'));

  // send the data using Stomp
  function sendMessage() {
    stompClient.send("/app/chat.send", {}, JSON.stringify({'content': msgToSend, "sender" : activeUser.username }));

  };

  // display the received data
  function onMessageReceived(data)
  {
    const result=  JSON.parse(data.body);
    setMessage(result);
    // alert(result.content + "  " + result.sender)
  };

  //TODO, add a solution for disconnection

  return (
    <div className="App">
    <br></br>
    <input onChange={(event) => setSendMessage(event.target.value)}></input>
    <button  onClick={sendMessage}>Send Message</button>
    <div>
        <h1>{message.sender}</h1>
        <p>{message.content}</p>
    </div>
  </div>
  );

}

export default Chat;