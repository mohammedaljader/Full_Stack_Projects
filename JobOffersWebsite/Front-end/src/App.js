import { Route, Switch } from 'react-router-dom';
import Layout from "./Components/Layout/Layout";
import NotFound from './Components/UI/NotFound';
import LoginComponent from './Components/AuthComponents/LoginComponent';
import Register from './Components/AuthComponents/Register';
import Home from './Pages/Home';
import {Provider as AlertProvider } from 'react-alert'
import AlertTemplate from 'react-alert-template-basic';
import NewPassword from './Components/AccountComponents/NewPassword';
import FavoritesPage from './Pages/FavoritesPage';
import CandidatesPage from './Pages/CandidatesPage';
import JobApplicationsPage from './Pages/JobApplicationsPage';
import EditJobPage from './Pages/EditJobPage';
import AccountPage from './Pages/AccountPage';
import JobsPage from './Pages/JobsPage';
import ApplyForJobPage from './Pages/ApplyForJobPage';
import CategoriesPage from './Pages/CategoriesPage';
import AddJobPage from './Pages/AddJobPage';
import UsersPage from './Pages/UsersPage';
import ViewJobPage from './Pages/ViewJobPage';
import ChatPage from './Pages/ChatPage';
import SendMessagePage from './Pages/SendMessagePage';


function App() {
  return (
    <AlertProvider template={AlertTemplate}>
    <Layout>
      <Switch>
        <Route exact path='/'>
          <Home/>
        </Route>
        <Route path='/myjobs'>
          <JobsPage />
        </Route>
        <Route path='/applyForJob/:id'>
          <ApplyForJobPage />
        </Route>
        <Route path='/favoriteList'>
          <FavoritesPage/>
        </Route>
        <Route path='/categories'>
          <CategoriesPage />
        </Route>
        <Route path='/addjob'>
          <AddJobPage />
        </Route>
        <Route path='/users'>
           <UsersPage />
        </Route>
        <Route path='/forgetPassword'>
          <NewPassword /> 
        </Route>
        <Route path='/jobApplications'>
          <JobApplicationsPage />
        </Route>
        <Route path='/jobCandidates'>
          <CandidatesPage />
        </Route>
        <Route path='/editjob/:id'>
          <EditJobPage />
        </Route>
        <Route path='/profile' >
          <AccountPage />
        </Route>
        <Route path='/login' >
          <LoginComponent />
        </Route>
        <Route path='/viewJob/:id'>
          <ViewJobPage />
        </Route>
        <Route path='/register'>
          <Register/>
        </Route>
        <Route path='/chat'>
          <ChatPage />
        </Route>
        <Route path='/sendMessage'>
           <SendMessagePage />
        </Route>
        <Route path ="*" >
          <NotFound />
        </Route>
      </Switch>
    </Layout>
    </AlertProvider>
  );
}

export default App;
