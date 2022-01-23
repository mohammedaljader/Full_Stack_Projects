import React from 'react';
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
import EditIcon from '@mui/icons-material/Edit';
import PeopleIcon from '@mui/icons-material/People';
import VisibilityIcon from '@mui/icons-material/Visibility';
import Tooltip from '@mui/material/Tooltip';
import { Link } from 'react-router-dom';

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


export default function JobListTable(props) {
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Job name</StyledTableCell>
            <StyledTableCell align="right">Description</StyledTableCell>
            <StyledTableCell align="right">Salary</StyledTableCell>
            <StyledTableCell align="right">Address</StyledTableCell>
            <StyledTableCell align="right"></StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
        {props.jobs.map((job) => (
            <StyledTableRow key={job.id}>
              <StyledTableCell component="th" scope="row">
                {job.jobName}
              </StyledTableCell>
              <StyledTableCell align="right">{job.jobDescription}</StyledTableCell>
              <StyledTableCell align="right">{job.jobSalary}</StyledTableCell>
              <StyledTableCell align="right">{job.jobAddress}</StyledTableCell>
              <StyledTableCell align="right">
                <Tooltip title="Delete Job">
                  <IconButton color="primary" size="small" onClick={() => props.onDelete(job.id)} >
                      <DeleteIcon style={{color:"#032892"}} />
                  </IconButton>
                </Tooltip>
                <Tooltip title="Edit Job">
                <IconButton color="primary" size="small" >
                    <Link to={`/editjob/${job.id}`} > <EditIcon style={{color:"#032892"}} /></Link>
                </IconButton>
                </Tooltip>
                <Tooltip title="View Job">
                <IconButton color="primary" size="small" >
                    <Link to={`/viewJob/${job.id}`} ><VisibilityIcon style={{color:"#032892"}} /></Link>
                </IconButton>
                </Tooltip>
                <Tooltip title="Job Candidates">
                <IconButton color="primary" size="small" >
                    <Link to={`/jobCandidates/${job.id}`} > <PeopleIcon style={{color:"#032892"}} /></Link>
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