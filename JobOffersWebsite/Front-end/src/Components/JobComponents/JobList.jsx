import React from 'react';
// import JobItem from './JobItem';
// import classes from './JobList.module.css'
import JobListTable from './JobListTable';


function JobList(props) {

  const handleDelete = (id) => {
    props.onDelete(id);
};

    return (
    //   <ul className={classes.list}>
    //   {props.jobs.map((job) => (
    //     // <JobItem key={job.id}  job = {job} onDelete={handleDelete} viewbutton={props.viewButton} /> 
    //   ))}
    // </ul>
    <>
    <JobListTable   jobs={props.jobs} onDelete={handleDelete} />
    </>
    )
}

export default JobList
