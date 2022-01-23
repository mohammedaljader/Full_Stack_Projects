import {React, useRef, useState, useEffect} from "react";
import "./popup.css";
import { useHistory } from "react-router-dom";
import { useAlert } from "react-alert";
import {
    Box,
    Button,
    Card,
    CardContent,
    CardHeader,
    Divider,
    Grid,
    TextField
  } from '@mui/material';
import CategoryService from "../../Services/CategoryService";
import NotFound from '../UI/NotFound'

 
function EditCategory(props) {
  const alert = useAlert();
  const history = useHistory();
  const [category, setCategory] = useState(null);
  const categoryNameRef = useRef();


  useEffect(() => {
      CategoryService.getCategoryById(props.categoryId).then((response) => {
        console.log(response.data);
        setCategory(response.data);
      });
  }, [props.categoryId])


  function submit(e){
    e.preventDefault();
    const enteredCategoryName = categoryNameRef.current.value;
    const data = {
        categoryId: props.categoryId,
        categoryName: enteredCategoryName
    }

    CategoryService.editCategory(data).then(() => {
        alert.success("Category updated successfully!!", {timeout: 2000})
        setTimeout(function(){
           history.replace('/categories');
           props.closeHandler();
           window.location.reload(1);
        }, 2000);
     }).catch(err=>{console.log(err);
       alert.error("Can't update this category, please try again!!")
     });  
  }


  if(!category) return <NotFound />
  return (
    <div className="popup-box">
        <div className="box">
            <span className="close-icon" onClick={props.closeHandler}>x</span>
            <form onSubmit={submit} >
                <Card>
                    <CardHeader title="Add new Category" />
                    <Divider />
                    <CardContent>
                    <Grid container spacing={3} >
                        <Grid item md={20} xs={20} >
                        <TextField
                            fullWidth
                            helperText="Please specify the category name"
                            label="Category Name"
                            name="categoryName"
                            defaultValue={category.categoryName}
                            inputRef={categoryNameRef}
                            required
                            variant="outlined"
                        />
                        </Grid>
                    </Grid>
                    </CardContent>
                    <Divider />
                    <Box  sx={{ display: 'flex', justifyContent: 'flex-end', p: 2 }} >
                    <Button color="primary" variant="contained" type = "submit" > Save </Button>
                    </Box>
                </Card>
            </form>
        </div>
    </div>
  );
};
 
export default EditCategory;