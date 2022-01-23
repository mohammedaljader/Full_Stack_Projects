import React, {useState, useEffect} from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import Tooltip from '@mui/material/Tooltip';
import JobApplicationService from '../../Services/JobApplicationService'
import MessageIcon from '@mui/icons-material/Message';
import { useHistory } from "react-router-dom";



const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));


export default function JobApplicationList() {
    const [applications, setApplications] = useState([]);
    const history = useHistory();

    let activeUser = JSON.parse(localStorage.getItem('user'));

  useEffect(() => {
      JobApplicationService.getAllJobApplicationByUser(activeUser.username).then((response) => {
      console.log(response.data);
      setApplications(response.data);
    });
  }, [activeUser.username]);// Runs ONCE after initial rendering


  function onDelete(id){
     JobApplicationService.deleteJobApplication(id).then(() => {
         window.location.reload(false);
      });
  }

  function sendMessage(receiverUser){
    const data={
      receiverUser: receiverUser,
      senderUser: activeUser.username
    }
    history.push('/sendMessage', data)
  }

  

  if(Object.keys(applications).length === 0) return <h1>You don't have any job application!</h1>
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Job name</StyledTableCell>
            <StyledTableCell>Job Address</StyledTableCell>
            <StyledTableCell>Job Salary</StyledTableCell>
            <StyledTableCell>CV</StyledTableCell>
            <StyledTableCell></StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
        {applications.map((job) => (
            <StyledTableRow id={job.jobName} key={job.jobApplyId}>
              <StyledTableCell component="th" scope="row">
                {job.jobName}
              </StyledTableCell>
              <StyledTableCell>{job.jobAddress}</StyledTableCell>
              <StyledTableCell>€{job.jobSalary} Per Hour</StyledTableCell>
              <StyledTableCell><a href ={job.cv}>Download CV</a></StyledTableCell>
              <StyledTableCell>
                <Tooltip title="Delete JobApplication">
                  <IconButton data-cy="delete" color="primary" size="small" onClick={() => onDelete(job.jobApplyId)} >
                      <DeleteIcon style={{color:"red"}} />
                  </IconButton>
                </Tooltip>
                <Tooltip title="Send a message">
                  <IconButton color="primary" size="small" onClick={() => sendMessage(job.jobOwnerUsername)}>
                      <MessageIcon style={{color:"#032892"}} />
                  </IconButton>
                </Tooltip>
              </StyledTableCell>
            </StyledTableRow>
            ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}