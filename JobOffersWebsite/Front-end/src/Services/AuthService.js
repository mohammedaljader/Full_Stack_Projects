const API_URL = "http://localhost:8080/login";

class AuthService {

  login(username, password) {
    var reqBody = "username="+username+"&password="+password+"&grant_type=password";
    return fetch(API_URL, {
        method: 'POST',
        headers: {
            //'Authorization': 'Bearer token',
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: reqBody
    })
  }

  logout() {
    localStorage.removeItem("user");
  }

  IsAuthenticated(){
    let user = localStorage.getItem('user');
    if(!user){
      return false;
    }
    return true;
  }

   IsAdmin(){
      let user = JSON.parse(localStorage.getItem('user'));
      if(user && user.roleName.includes("Admin")){
        return true;
      }
      return false;
   }

   IsJobPublisher(){
      let user = JSON.parse(localStorage.getItem('user'));
      if(user && user.roleName.includes("Job_publisher")){
        return true;
      }
      return false;
  }

  IsJobSeeker(){
    let user = JSON.parse(localStorage.getItem('user'));
    if(user && user.roleName.includes("Job_seeker")){
      return true;
    }
    return false;
  }
  

}

export default new AuthService();