import { Route, Switch } from 'react-router-dom';
import Layout from './Layout/Layout';
import Home from './Pages/Home';
import {Provider as AlertProvider } from 'react-alert'
import AlertTemplate from 'react-alert-template-basic';
import ContactUs from './Pages/ContactUs';


function App() {
  return (   
    <AlertProvider template={AlertTemplate}>
    <Layout>
      <Switch>
        <Route exact path='/'>
         <Home/>
        </Route>
        <Route path='/contactus'>
         <ContactUs/>
        </Route>
      </Switch>
    </Layout>
    </AlertProvider>
  );
}

export default App;
