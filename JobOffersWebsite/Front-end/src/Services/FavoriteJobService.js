import axios from 'axios';
// import authHeader from './authHeader';

const favorites_API_Base_URL = "http://localhost:8080/api/favorite/";

class FavoriteJobService  {

    addJobToFavorites(data){
        return axios.post(favorites_API_Base_URL+"addJobToList", data)
    }

    getAllFavoriteJobsByUser(username){
        return axios.get(favorites_API_Base_URL+"favoriteJobs/"+ username)
    }
    deleteJobFromList(favoriteJobId){
        return axios.delete(favorites_API_Base_URL+"deleteJobFromList/"+ favoriteJobId)
    }
   
}

export default new FavoriteJobService()
