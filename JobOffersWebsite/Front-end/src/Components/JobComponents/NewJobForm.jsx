import { useRef, useState } from 'react';
import Card from '../UI/Card'
import classes from './NewJobForm.module.css';

function NewJobForm(props) {
  const [category, setCategory] = useState([]);
  const [file, setFile] = useState(null);


  const nameInputRef = useRef();
  const salaryInputRef = useRef();
  const cityInputRef = useRef();
  const descriptionInputRef = useRef();
  let user = JSON.parse(localStorage.getItem('user'));

  if(user == null){
    return;
  }

  
  //handle 
  const handleChange = e =>
  {
      let obj = JSON.parse(e.target.value); //object
      console.log(obj);
      setCategory(obj);
  }

  const onChangeHandler=event=>{
      setFile(event.target.files[0])
  }

  function submitHandler(event) {

    event.preventDefault();
    const enteredName = nameInputRef.current.value;
    const enteredSalary = salaryInputRef.current.value;
    const enteredCity = cityInputRef.current.value;
    const enteredDescription = descriptionInputRef.current.value;

    

    const job = {
      jobName: enteredName,
      jobDescription: enteredDescription,
      jobSalary: enteredSalary,
      jobAddress: enteredCity,
      categoryId : category,
      username: user.username,
    };
    console.log(job);
    var data = new FormData();
    data.append('file', file);
    data.append('jobName', enteredName);
    data.append('jobDescription', enteredDescription);
    data.append('jobSalary', enteredSalary);
    data.append('jobAddress', enteredCity);
    data.append('categoryId', category);
    data.append('username', user.username);
    console.log(data)
    props.onAddJob(data);
  }

  return (
    <Card>
      <form className={classes.form} onSubmit={submitHandler} >
          <div className={classes.control}>
            <label htmlFor='jobName'>Title</label>
            <input type='text' placeholder="Job name.." required id='jobName' ref={nameInputRef} />
          </div>

          <div className={classes.control}>
            <label htmlFor='jobAddress'>Address</label>
            <input type='text' placeholder="Job address.." required id='jobAddress' ref={cityInputRef} />
          </div>

          <div className={classes.control}>
            <label htmlFor='jobImage'>Image</label>
            <input type="file" name="file" accept="image/png, image/jpeg, image/jpg" onChange={onChangeHandler} required/>
          </div>

          <div className={classes.control}>
            <label htmlFor='jobSalary'>Salary</label>
            <input type='number' placeholder="Job salary.." required id='jobSalary' ref={salaryInputRef} />
          </div>
          
          <div className={classes.control}>
            <label htmlFor='jobDescription'>Description</label>
            <textarea
              id='jobDescription'
              required
              rows='5'
              placeholder="Job description.."
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
            <button>Add job</button>
          </div>
      </form>
    </Card>
  );
}

export default NewJobForm;

