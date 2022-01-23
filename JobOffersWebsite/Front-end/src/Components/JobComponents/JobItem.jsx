import React from 'react';
import Card from '../UI/Card';
import classes from './JobItem.module.css';
import { Link } from 'react-router-dom';



function JobItem(props) {
    return (
        <li className={classes.item} key={props.job.id}>
          <Card>
            <div className={classes.content}>
                <h3>{props.job.jobName}</h3>
                <address>{props.job.jobAddress}</address>
                <p>{props.job.jobDescription}</p>
            </div>
            <div className={classes.actions}>
                <button onClick={() => props.onDelete(props.job.id)}> Delete</button>
                <Link to={`/editjob/${props.job.id}`}><button className={classes.edit}>Edit </button></Link>
                {props.viewbutton ? <Link to={`/viewJob/${props.job.id}`} ><button className={classes.view}>View</button></Link> : null}
            </div>
          </Card>
        </li>
    )
}

export default JobItem
