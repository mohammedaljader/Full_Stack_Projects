import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import Tooltip from '@mui/material/Tooltip';
import EditIcon from '@mui/icons-material/Edit';
import Button from '@mui/material/Button';
import { useAlert } from 'react-alert';
import {
    Card,
    CardContent,
    CardHeader,
    Divider,
  } from '@mui/material';
import AddCategory from './AddCategory';
import EditCategory from './EditCategory';
import CategoryService from '../../Services/CategoryService';





export default function CategoriesList(props) {
    const alert = useAlert();
    const [isOpenAdd, setIsOpenAdd] = React.useState(false);
    const [isOpenEdit, setIsOpenEdit] = React.useState(false);
    const [categoryId, setCategoryId] = React.useState(null);
 
    const togglePopupAddCategory = () => {
        setIsOpenAdd(!isOpenAdd);
    }
    const togglePopupEditCategory = () => {
        setIsOpenEdit(!isOpenEdit);
    }

    const closeHandler = () => {
        setIsOpenAdd(false);
        setIsOpenEdit(false);
    }

    const columns = [
        { field: 'categoryName', headerName: 'Category Name', flex:1 },
        {
          field: "Actions",
          flex:1,
          renderCell: (cellValues) => {
            return (
              <div>
                <Tooltip title="Edit category">
                    <IconButton aria-label="edit" onClick={() =>{handleClick(0, cellValues)}} >
                        <EditIcon color = "primary"/>
                    </IconButton>
                </Tooltip>
                <Tooltip title="Delete category">
                    <IconButton aria-label="delete" onClick={()=>{handleClick(1, cellValues)}}>
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
                editCategory(selected)
                break;
              case 1:
                deleteCategory(selected)
                break;
             default:
                console.log("Select a valid id")
              break;
            }
          }
        function deleteCategory(selected){
          CategoryService.deleteCategory(selected.row.categoryId).then(() => {
            alert.success("Category deleted successfully!!", {timeout: 2000})
            setTimeout(function(){
               window.location.reload(1);
            }, 2000);
         }).catch(err=>{console.log(err);
           alert.error("Can't delete this category, please try again!!")
         });     
        }

        function editCategory(selected){
            setCategoryId(selected.row.categoryId)
            togglePopupEditCategory()
        }

  return (
    <Card>
        <CardHeader title="Categories List" />
        <Button variant="contained" size="small" 
            style={{background:"#032892" , marginLeft: "20px" , marginBottom: "20px"}} 
            onClick={togglePopupAddCategory}>
            Add Category
        </Button>
        <Divider />
        <CardContent>
        <div style={{ height: 400, width: 'flex' }}>
        <DataGrid
            getRowId={row => row.categoryId}
            rows={props.Categories}
            columns={columns}
            pageSize={5}
            rowsPerPageOptions={[5]}
            disableColumnSelector
            disableMultipleSelection={true}
            disableSelectionOnClick={true}
        />
        </div>
        </CardContent>
        {isOpenAdd && <AddCategory
            closeHandler={closeHandler}
            />}
        {isOpenEdit && <EditCategory
                    closeHandler={closeHandler}
                    categoryId={categoryId}
                />}
    </Card>
  );
}