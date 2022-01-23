import axios from 'axios';
import authHeader from './authHeader';

const API_URL = 'http://localhost:8080/api/';
const API_URL_user = 'http://localhost:8080/api/user/';

class UserService {

  getAllUsers() {
    return axios.get(API_URL + 'users', { headers: authHeader() });
  }

  getCurrentUser(username){
        return axios.get(API_URL_user + username, {headers: authHeader()})}  

  registerUser(user){
          return axios.post(API_URL_user + "register", user)}  

  editProfile(user){
          return axios.put(API_URL_user, user)
  }

  editPassword(user){
    return axios.put(API_URL_user +"forgetPassword", user)
  }

  updateUserRole(data){
    return axios.post(API_URL_user + "updateUserRole", data, {headers: authHeader()})
  }

  deleteUser(username){
    return axios.delete(API_URL_user + username, {headers: authHeader()})
  }
}

export default new UserService();