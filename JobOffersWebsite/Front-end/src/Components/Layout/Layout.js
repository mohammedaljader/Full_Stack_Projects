import MainNavigation from './MainNavigation';
import classes from './Layout.module.css';
// import Footer from './Footer';

function Layout(props) {
  return (
    <div>
      <MainNavigation />
      <main className={classes.main} style={{minHeight: "500px"}}>{props.children}</main>
      {/* <Footer/>  */}
    </div>
  );
}

export default Layout;