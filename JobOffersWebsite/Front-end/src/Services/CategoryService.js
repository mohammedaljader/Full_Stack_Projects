import axios from 'axios';
import authHeader from './authHeader'

const Categories_API_Base_URL = "http://localhost:8080/api/categories";
const Category_API_Base_URL = "http://localhost:8080/api/category";

class CategoryService{

    getAllCategories(){
        return axios.get(Categories_API_Base_URL, { headers: authHeader() });
    }

    addCategory(data){
        return axios.post(Category_API_Base_URL , data, { headers: authHeader() })
    }

    deleteCategory(categoryId){
        return axios.delete(Category_API_Base_URL+"/"+categoryId, { headers: authHeader() })
    }

    editCategory(data){
        return axios.put(Category_API_Base_URL , data, { headers: authHeader() })
    }
     
    getCategoryById(categoryId){
        return axios.get(Category_API_Base_URL +"/" + categoryId, { headers: authHeader() })
    }
}

export default new CategoryService()
