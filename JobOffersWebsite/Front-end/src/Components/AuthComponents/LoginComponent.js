import React from 'react'
import { useHistory } from 'react-router';
import { Link } from 'react-router-dom';
import AuthService from '../../Services/AuthService';
import './index.css';
import { useAlert } from 'react-alert';


function LoginComponent() {

        const alert = useAlert()
        const History = useHistory()
        const usernameRef = React.useRef();
        const passwordRef = React.useRef();
        const [msg, msgSet] = React.useState(null);
        async function handleSubmit(e){
            e.preventDefault();
            const data = {
                username: usernameRef.current.value,
                password: passwordRef.current.value
            };
            console.log(data);
           await AuthService.login(data.username, data.password).then((response) => response.json())
           .then((responseData) => {
               console.log(JSON.stringify(responseData));
               localStorage.setItem("user", JSON.stringify(responseData));
               alert.success("You are logged in successfully!!", {timeout: 2000})
                setTimeout(function(){
                    History.push("/");
                    window.location.reload(1);
                }, 2000);
           },(error) => {
             console.log(error)
             msgSet("Username/Password are incorrect, Please try again!!")
             alert.error("Username/Password are incorrect, Please try again!!");
           })
           
        //    .catch(err=>{console.log(err);
        //      msgSet("Username/Password are incorrect, Please try again!!")
        //      alert.error("Username/Password are incorrect, Please try again!!");
        //     });
             
        };
  

    return (
        <div className="outer">
          <div className="inner">
          <form onSubmit={handleSubmit}>

            <h3>Log in</h3>

            <div className="form-group">
                <label>Username</label>
                <input type="text" className="form-control" placeholder="Enter username" ref={usernameRef} />
            </div>

            <div className="form-group">
                <label>Password</label>
                <input type="password" className="form-control" placeholder="Enter password"  ref={passwordRef} />
            </div>

            {/* <div className="form-group" style={{marginTop: "10px"}}>
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="customCheck1" />
                    <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
                </div>
            </div> */}
             <br/>
            <button type="submit" className="btn btn-dark btn-block custom">Sign in</button>
            <p className="forgot-password text-right">
                Forgot <Link to="/forgetPassword">password?</Link>
            </p>
            <h4 style={{color:"red"}}>{msg}</h4>
            </form>
          </div>
        </div>
    );
}

export default LoginComponent