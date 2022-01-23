import React from "react";
import './index.css';
import { Link } from "react-router-dom";
import { useHistory } from 'react-router';
import UserService from "../../Services/UserService";
import { useAlert } from 'react-alert';


export default function RegisterForm() {
  const alert = useAlert()
  const History = useHistory();
  const nameRef = React.useRef();
  const emailRef = React.useRef();
  const usernameRef = React.useRef();
  const passwordRef = React.useRef();
  const addressRef = React.useRef();
  const [msg, msgSet] = React.useState(null);

  
  const handleSubmit = e => {
      e.preventDefault();
      const data = {
          name : nameRef.current.value,
          username: usernameRef.current.value,
          address: addressRef.current.value,
          roleName: "Job_seeker",
          password: passwordRef.current.value,
          email: emailRef.current.value
      };
     console.log(data);
     UserService.registerUser(data).then(() => {
         alert.success("Your account is succesfully registed!!", {timeout: 2000})
        setTimeout(function(){
            History.push("/login");
            window.location.reload(1);
         }, 2000);
        
     }).catch(err=>{console.log(err);
       msgSet("Username/Email exists already!!!")
       alert.error("Username/Email exists already!!!");
    });
  };


  return (
    <div className="outer">
       <div className="inner">
          <form onSubmit={handleSubmit}>
                <h3>Register</h3>

                <div className="form-group">
                    <label>Name</label>
                    <input type="text" className="form-control" placeholder="Name" ref={nameRef} required />
                </div>

                <div className="form-group">
                    <label>Username</label>
                    <input type="text" className="form-control" placeholder="Enter username"  ref={usernameRef} required />
                </div>


                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter password" ref={passwordRef} required />
                </div>


                <div className="form-group">
                    <label>Email</label>
                    <input type="email" className="form-control" placeholder="Enter email" ref={emailRef} required/>
                </div>

                <div className="form-group">
                    <label>Address</label>
                    <input type="text" className="form-control" placeholder="Enter address" ref={addressRef} required />
                </div>

                <br/>
                <button type="submit" className="btn btn-dark btn-block custom">Sign up</button>
                <p className="forgot-password text-right">
                    Already registered <Link to="/login">log in?</Link>
                </p>
                <h4 style={{color:"red"}}>{msg}</h4>
            </form>
          </div>
        </div>
  );
}