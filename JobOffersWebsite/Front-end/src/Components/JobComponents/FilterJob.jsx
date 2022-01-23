import {React, useEffect, useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import FormControl from '@material-ui/core/FormControl';
import CategoryService from '../../Services/CategoryService';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';


const useStyles = makeStyles((theme) => ({
  formControl: {
    minWidth: 200,
    minHeight: 50,
    marginBottom: 20,
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
}));

export default function FilterJob(props) {
  const classes = useStyles();
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    CategoryService.getAllCategories().then((response) => {
      console.log(response.data);
      setCategories(response.data);
    });
  }, []);


  const option = {
      categoryId: '1',
      categoryName: 'All'
  }
 

  return (
    <div>

      <TextField  sx={{ m: 1, minWidth: 400, maxWidth: 400}} 
          label="Search for job"  
          id="outlined-size-small" 
          size="small" 
          style={{marginLeft: "15px"}}
          onChange={(event) => {
             props.FilterJobByName(event.target.value)     
          }}
      />

      <FormControl variant="outlined" className={classes.formControl}>
        <select 
          id= "select"
          style={{height: "40px", marginLeft: "20px"}}
          onChange={(event) => {
            props.FilterJob(JSON.parse(event.target.value))
          }}
        > 
        <option id="all" value={JSON.stringify(option)}> -- (Select a category) -- </option>
            {categories.map((option, index) => 
            <option 
                id={option.categoryName}
                key={index} 
                value={JSON.stringify(option)}>{option.categoryName} </option>
            )}
        </select>
      </FormControl>
      <Button 
          variant="contained"
          style={{height: "40px", background: "#cc2062", marginLeft: "20px"}}
          onClick={props.clear}
      >
           Clear
      </Button>
    </div>
  )
}
