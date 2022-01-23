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
import SendIcon from '@mui/icons-material/Send';
import Tooltip from '@mui/material/Tooltip';
import JobApplicationService from '../../Services/JobApplicationService';
import NotFound from '../UI/NotFound';
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


export default function Candidates() {
    const [applications, setApplications] = useState(null);
    let param = window.location.pathname;  //to find the path of id /viewjob/1
    let jobId = param.split('/').pop();
    const history = useHistory();

    let activeUser = JSON.parse(localStorage.getItem('user'));


  useEffect(() => {
      JobApplicationService.getAllCandidatesForJob(jobId).then((response) => {
      console.log(response.data);
      setApplications(response.data);
    });
  }, [jobId]);// Runs ONCE after initial rendering
  

  function sendMessage(receiverUser){
    const data={
      receiverUser: receiverUser,
      senderUser: activeUser.username
    }
    history.push('/sendMessage', data)
  }



  if(!applications) return <NotFound/>

  if(Object.keys(applications).length === 0) return <h1>No candidates for this job!</h1>
  return (
    <div>
      <h1>All Candidates for this job</h1>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
              <TableRow>
                <StyledTableCell>Candidate name</StyledTableCell>
                <StyledTableCell>Candidate email</StyledTableCell>
                <StyledTableCell>Candidate username</StyledTableCell>
                <StyledTableCell>CV</StyledTableCell>
                <StyledTableCell></StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
            {applications.map((job) => (
                <StyledTableRow key={job.jobApplyId}>
                  <StyledTableCell component="th" scope="row">
                    {job.name}
                  </StyledTableCell>
                  <StyledTableCell>{job.email}</StyledTableCell>
                  <StyledTableCell>{job.username}</StyledTableCell>
                  <StyledTableCell><a href ={job.cv}>Download CV</a></StyledTableCell>
                  <StyledTableCell>
                    <Tooltip title="Send message" onClick={() => sendMessage(job.username)}>
                      <IconButton color="primary" size="small">
                          <SendIcon />
                      </IconButton>
                    </Tooltip>
                  </StyledTableCell>
                </StyledTableRow>
                ))}
            </TableBody>
          </Table>
        </TableContainer>
    </div>
  );
}