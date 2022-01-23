import {React, useEffect, useState} from 'react'
import CategoriesList from '../../Components/CategoriesComponents/CategoriesList';
import CategoryService from '../../Services/CategoryService';

function Categories() {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        CategoryService.getAllCategories().then((response) => {
          console.log(response.data);
          setCategories(response.data);
        });
      }, []);


    if(Object.keys(categories).length === 0) return <h1>No Categories available right now!</h1>

    return (
        <div>
            <CategoriesList Categories={categories}/>
        </div>
    )
}

export default Categories
