import {React, useState, useEffect} from "react";
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import 'react-datepicker/dist/react-datepicker.css'
import axios from "axios";
import { useLocation } from "react-router-dom";
import {InkUsageBar, InkUsageLine} from "../Components/Charts/InkUsage";
import {MediaUsageBar, MediaUsageLine} from "../Components/Charts/MediaUsageChartjs";
import {SqmPerPrintModeChartJs} from "../Components/Charts/SqmPerPrintModeChartjs";
import {TopTenChartJs} from "../Components/Charts/TopTenChartJs";
import {UsedMediaTypePerMachine} from "../Components/Charts/UsedMediaTypePerMachine";
import moment from "moment";
import { SqmPerPrintModeLine } from "../Components/Charts/SqmPerPrintModeChartjs";


function Home() {  
     const [InkUsage, setInkUsage] = useState(null);
     const [mediaUsage, setMediaUsage] = useState(null);
     const [printMode, setPrintMode] = useState(null);
     const [topMachines, setTopMachines] = useState(null);
     //const [UsedMedia, setUsedMedia] = useState(null);
     const location = useLocation();
     const myparam = location.state;
  
     useEffect(() => {
       if(myparam === null || myparam === undefined){
          getAllData();
       }
       else{
         console.log(myparam)
         if(myparam.inkusage !== null){
          setInkUsage(myparam.inkusage)
         }
         if(myparam.Mediausage !== null){
          setMediaUsage(myparam.Mediausage)
         }
         if(myparam.SqmPerPrintMode !== null){
          setPrintMode(myparam.SqmPerPrintMode)
         }
       }  
       getTopTenMachines()
     },[myparam]) // eslint-disable-line


    var now = moment();
    const endDate = now.format('YYYY-MM-DD')
    //const startDate = moment().date(1).month(10).year(now.year()).format('YYYY-MM-DD')
    const startDate = moment().date(20).month(10).year(2021).format('YYYY-MM-DD')
    const startDateTime = startDate + " 00:00:00"
    const endDateTime = endDate + " 11:59:59"

    function getAllData(){
      getInkUsageData();
      getMediaUsageData();
      getSqmPerPrintMode();
      getTopTenMachines();
      //getUsedMediaTypesPerMachine();
    }
    
    async function getInkUsageData(){
      await axios.post("http://localhost:8080/engineControl/test2", 
      {'startDate':startDateTime, 'endDate':endDateTime}).then((response) => {
        setInkUsage(response.data);
      });  
    }

    async function getMediaUsageData(){
      await axios.post("http://localhost:8080/engineControl/media/testMedia1", 
      {'startDate':startDateTime, 'endDate':endDateTime}).then((response) => {
        setMediaUsage(response.data);
      }); 
    }

    async function getSqmPerPrintMode(){
      await axios.post("http://localhost:8080/engineControl/sqm/test1",
       {'startDate':startDateTime, 'endDate':endDateTime}).then((response) => {
        setPrintMode(response.data);
      }); 
    }

    async function getTopTenMachines(){
      await axios.get("http://localhost:8080/engineControl/top10").then((response) => {
        setTopMachines(response.data);
      }); 
    }

    
   
    return (
         <Container sx={{ py: 1 }} maxWidth="xl">
         <Grid container spacing={3}>
         <Grid item xs={12} sm={6} md={6}>
                 <Card
                   sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}> 
                    <InkUsageBar Data={InkUsage}/>
                 </Card>
            </Grid> 
           <Grid item xs={12} sm={6} md={6}>
                 <Card
                   sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}> 
                    <InkUsageLine Data={InkUsage}/>
                 </Card>
            </Grid> 
            <Grid item xs={12} sm={6} md={6}>
                 <Card
                   sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}> 
                    {/* <Inkusage Data={chart}/> */}
                    <MediaUsageBar Data={mediaUsage} />
                 </Card>
            </Grid> 
            <Grid item xs={12} sm={6} md={6}>
                 <Card
                   sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}> 
                   <MediaUsageLine Data={mediaUsage} />
                 </Card>
            </Grid> 
            <Grid item xs={12} sm={6} md={6}>
                 <Card
                   sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}> 
                   <SqmPerPrintModeChartJs Data={printMode} />
                 </Card>
            </Grid> 
            <Grid item xs={12} sm={6} md={6}>
                 <Card
                   sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}> 
                   <SqmPerPrintModeLine Data={printMode} />
                 </Card>
            </Grid> 
            <Grid item xs={12} sm={6} md={6}>
                 <Card
                   sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}> 
                   <UsedMediaTypePerMachine />
                 </Card>
            </Grid> 
            <Grid item xs={12} sm={6} md={6}>
                 <Card
                   sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}> 
                   <TopTenChartJs Data={topMachines} />
                 </Card>
            </Grid> 
        </Grid>
       </Container>
    )
}

export default Home