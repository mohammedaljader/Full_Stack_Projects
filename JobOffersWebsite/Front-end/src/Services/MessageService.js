import axios from 'axios';
import authHeader from './authHeader';

const Message_API_Base_URL = "http://localhost:8080/api/msg/";

class MessageService {
    sendMessage(message){
        return axios.post(Message_API_Base_URL + "send", message, {headers: authHeader()});
    }

    getAllSentMessages(username){
        return axios.get(Message_API_Base_URL+"all/sent/" +username,{headers: authHeader()});
    }

    getAllReceivedMessages(username){
        return axios.get(Message_API_Base_URL+"all/received/" + username,{headers: authHeader()});
    }

    getMessageById(messageId){
        return axios.get(Message_API_Base_URL + messageId, {headers: authHeader()});
    }

    deleteMessage(messageId){
        return axios.delete(Message_API_Base_URL + messageId, {headers: authHeader()})
    }
}

export default new MessageService();