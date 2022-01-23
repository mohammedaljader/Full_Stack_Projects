import {React,useEffect, useState} from 'react'
import Users from '../../Components/UserComponents/Users';
import UserService from '../../Services/UserService';

function UsersList() {
    const [users,setUsers] = useState([]);

    useEffect(() => {
        getAllUsers()
    }, [])

    const getAllUsers = () => {
      UserService.getAllUsers().then((response) => {
        setUsers(response.data);
        });
    } 
   
    return (
        <div>
            <h1>All Users</h1>
            <Users users={users} />
        </div>
    )
}

export default UsersList
