import { useState, useRef } from 'react';
import Card from '../UI/Card'
import classes from './NewJobForm.module.css';

function EditJobForm(props) {

    const nameInputRef = useRef();
    const salaryInputRef = useRef();
    const cityInputRef = useRef();
    const descriptionInputRef = useRef();

    const [category, setCategory] = useState("");
    
  const handleChange = e =>
  {
      let CategoryObj = JSON.parse(e.target.value); //object
      console.log(CategoryObj);
      setCategory(CategoryObj);
  }
  let user = JSON.parse(localStorage.getItem('user'));
  if(user == null){
    return;
  }

  function submitHandler(event)
  {
    event.preventDefault();
    const enteredName = nameInputRef.current.value;
    const enteredSalary = salaryInputRef.current.value;
    const enteredCity = cityInputRef.current.value;
    const enteredDescription = descriptionInputRef.current.value;

    const Updatedjob ={
        id : props.job.id,
        jobName: enteredName,
        jobDescription: enteredDescription,
        jobSalary: enteredSalary,
        jobAddress: enteredCity,
        categoryId : category,
        username : user.username

    };
    console.log(Updatedjob);
    props.onUpdatejob(Updatedjob);
  }


    return (
        <Card>
      <form className={classes.form} onSubmit={submitHandler}>
          <div className={classes.control}>
            <label htmlFor='jobName'>Title</label>
            <input type='text' required id='jobName' defaultValue ={props.job.jobName}  ref={nameInputRef} />
          </div>

          <div className={classes.control}>
            <label htmlFor='jobAddress'>Address</label>
            <input type='text' required id='jobAddress' defaultValue ={props.job.jobAddress} ref={cityInputRef} />
          </div>

          <div className={classes.control}>
            <label htmlFor='jobSalary'>Salary</label>
            <input type='number' required id='jobSalary'  defaultValue ={props.job.jobSalary} ref={salaryInputRef} />
          </div>
          
          <div className={classes.control}>
            <label htmlFor='jobDescription'>Description</label>
            <textarea
              id='jobDescription'
              required
              rows='5'
              defaultValue ={props.job.jobDescription}
              ref={descriptionInputRef}
            ></textarea>
          </div>
         {/*Dropdown code */}
          <select onChange={handleChange} required> 
            <option value=""> -- Select a category -- </option>
            {props.Categories.map((option, index) => 
            <option key={index} 
                value={JSON.stringify(option.categoryName)}>{option.categoryName} </option>
            )}
          </select>
          <div className={classes.actions}>
            <button>Edit job</button>
          </div>
      </form>
    </Card>
    )
}

export default EditJobForm