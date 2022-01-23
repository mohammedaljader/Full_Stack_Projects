import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import Tooltip from '@mui/material/Tooltip';
import ArrowCircleUpIcon from '@mui/icons-material/ArrowCircleUp';
import ArrowCircleDownIcon from '@mui/icons-material/ArrowCircleDown';
import UserService from '../../Services/UserService';
import { useAlert } from 'react-alert';





export default function Users(props) {
    const alert = useAlert();

    const columns = [
        { field: 'name', headerName: 'Name', flex:1 },
        { field: 'username', headerName: 'Username', flex:1 },
        { field: 'email', headerName: 'Email address', flex:1 },
        { field: 'roleName', headerName: 'User Role',flex:1 },
        {
          field: "Actions",
          flex:1,
          renderCell: (cellValues) => {
            return (
              <div>
                <Tooltip title="Update User role to Job Publisher">
                <IconButton aria-label="upgrade" onClick={()=>{handleClick(0,cellValues)}}>
                    <ArrowCircleUpIcon color = "primary"/>
                </IconButton>
                </Tooltip>
                <Tooltip title="Update User role to Job Seeker">
                <IconButton aria-label="RemoveUpgrade" onClick={()=>{handleClick(1,cellValues)}}>
                    <ArrowCircleDownIcon color = "primary"/>
                </IconButton>
                </Tooltip>
                <Tooltip title="Delete User Account">
                <IconButton aria-label="delete" onClick={()=>{handleClick(2,cellValues)}}>
                  <DeleteIcon style={{color : "red"}}/>
                </IconButton>
                </Tooltip>
              </div>
            );
          }
        },
        ];
      
        function handleClick(mode, selected){
          switch(mode){
            case 0:
              upgradeUserToJobPublisher(selected.row.username, selected.row.roleName)
              break;
            case 1:
              upgradeUserToJobSeeker(selected.row.username, selected.row.roleName)
              break;
            default:
              deleteUser(selected.row.username, selected.row.roleName)
              break;
          }
        }
      
        
      
        function upgradeUserToJobPublisher(username, role){
            if(checkIfAdmin(role) === false){
              if(role === "Job_seeker"){
                const data ={
                   username: username,
                   roleName: "Job_publisher"
                 }
                UserService.updateUserRole(data).then(() => {
                    alert.success('User role Successfully updated to Job Publisher!', {timeout: 3000})
                    setTimeout(function(){
                      window.location.reload(1);
                  }, 3000);
                  }).catch(err=>{console.log(err);
                      alert.error('error!', {timeout: 3000})
                    return;
                  });
              }
              else{
                  alert.info('This user is already job publisher!', {timeout: 3000})
                  return;
              }
            }    
        }    

        function upgradeUserToJobSeeker(username, role){
          if(checkIfAdmin(role) === false){
            if(role === "Job_publisher"){
              const data ={
                 username: username,
                 roleName: "Job_seeker"
               }
              UserService.updateUserRole(data).then(() => {
                  alert.success('User role Successfully updated to Job Seeker!', {timeout: 3000})
                  setTimeout(function(){
                    window.location.reload(1);
                }, 3000);
                }).catch(err=>{console.log(err);
                    alert.error('error!', {timeout: 3000})
                  return;
                });
            }
            else{
                alert.info('This user is already job seeker!', {timeout: 3000})
                return;
            }
          }  
      }    

      function checkIfAdmin(role){
        if(role === "Admin"){
          alert.info("Can't change Admin role !!", {timeout: 3000})
          return true;
        }
        return false;
      }

      function deleteUser(username , role){
        if(role === "Admin"){
          alert.info('Can not delete the Admin User!!', {timeout: 3000})
        }
        else{
          UserService.deleteUser(username).then(() => {
            alert.success('User Successfully deleted!', {timeout: 3000})
            setTimeout(function(){
              window.location.reload(1);
          }, 3000);
          }).catch(err=>{console.log(err);
              alert.error('Can not delete this user, please try again!!', {timeout: 3000})
          });
        }
      }


  return (
    <div style={{ height: 600, width: 'flex' }}>
      <DataGrid
        density="comfortable"
        getRowId={row => row.userId}
        rows={props.users}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[5]}
        disableColumnSelector
        disableMultipleSelection={true}
        disableSelectionOnClick={true}
      />
    </div>
  );
}