import {React, useState, useEffect} from "react";
import "./Filter.css";
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import 'react-datepicker/dist/react-datepicker.css'
import moment from "moment";
import axios from "axios";
import DateRangePicker from '@wojtekmaj/react-daterange-picker';
import { useHistory } from "react-router-dom";
import { useAlert } from "react-alert";
import { Box, Container, Grid } from '@mui/material';
import {
  Card,
  CardHeader,
  Divider,
} from '@mui/material';
import FormControl from '@mui/material/FormControl';
import { useTheme } from '@mui/material/styles';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import Chip from '@mui/material/Chip';
import TextField from '@mui/material/TextField';



const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};


function getStyles(name, Machines, theme) {
  return {
    fontWeight:
    Machines.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

 
function Filter(props) {
  const alert = useAlert();
  const history = useHistory();
  const [value, onChange] = useState([new Date(), new Date()]);
  const theme = useTheme();
  const [Machines, setMashines] = useState([]);
  const [selectedMachine, setSelectedMashines] = useState([]);

  useEffect(() => {
     axios.get("http://localhost:8080/engineControl/machines").then((response) => {
       console.log(response.data)
       setMashines(response.data)
      })
  },[])

    

  const handleChange = (event) => {
    const {
      target: { value },
    } = event;
    for (const element of Machines) {
      if(element.machineID === value){
        setSelectedMashines(typeof value === 'string' ? value.split(',') : value);
      }
      else if(typeof value !== "string"){
        setSelectedMashines(typeof value === 'string' ? value.split(',') : value);
      }
    }
  };
  

  function submit(e){
    e.preventDefault();
    const startDate = moment(value[0]).format("YYYY-MM-DD").toString()
    const endDate = moment(value[1]).format("YYYY-MM-DD").toString()

    const startDateTime = startDate + " 00:00:00"
    const endDateTime = endDate + " 11:59:59"
    
      axios.post("http://localhost:8080/engineControl/test2", {'startDate':startDateTime, 'endDate':endDateTime}).then((response) => {
        const inkusage = response.data;
          axios.post("http://localhost:8080/engineControl/media/testMedia1", {'startDate':startDateTime, 'endDate':endDateTime}).then((response) => {
            const mediausage = response.data;
                axios.post("http://localhost:8080/engineControl/sqm/test1", {'startDate':startDateTime, 'endDate':endDateTime}).then((response) => {
                  const SqmPerPrintMode = response.data;
                  const data ={
                    inkusage: inkusage,
                    Mediausage: mediausage,
                    SqmPerPrintMode: SqmPerPrintMode
                  }
                  console.log(data)
                  history.push('/', data)
                });
         }); 
      });  
    
    alert.success("The result of your search", {timeout: 2000});
    props.closeHandler(false);
  }


  function submitMachines(e){
    e.preventDefault();
    var now = moment();
    const endDate = now.format('YYYY-MM-DD')
    const startDate = moment().date(20).month(10).year(2021).format('YYYY-MM-DD')
    const startDateTime = startDate + " 00:00:00"
    const endDateTime = endDate + " 11:59:59"
    
     const Data = {
       machines: selectedMachine,
       startDate: startDateTime,
       endDate: endDateTime 
     }

    axios.post("http://localhost:8080/engineControl/test3", Data).then((response) => {
      const inkusage = response.data;
        axios.post("http://localhost:8080/engineControl/media/mediaCategoryByid", Data).then((response) => {
          const mediausage = response.data;
          const data ={
            inkusage: inkusage,
            Mediausage: mediausage,
            SqmPerPrintMode: null
          }
          console.log(data)
          history.push('/', data)
       }); 
    });  

    alert.success("The result of your search", {timeout: 2000});
    props.closeHandler(false);
  }


  return (
      <div className="popup-box">
      <div className="box">
        <span className="close-icon" onClick={props.handleClose}>x</span>
          <b style={{marginLeft: "150px", fontSize: "50px"}}>Filter</b>
          <Box component="main" sx={{ flexGrow: 1, py: 2 }} style={{marginLeft: "130px"}} >
              <Container maxWidth="lg">
                  <Grid container spacing={3} >

                    {/* Time Frame */}
                    <Grid  item lg={5} md={8} xs={12} >
                    <Card>
                      <CardHeader
                            title="Select Available Printers"
                      />
                      <Divider />
                      <FormControl sx={{ m: 2, minWidth: 270, maxWidth: 400 }}>
                            <form onSubmit={submitMachines}>
                                      <div>
                                        <TextField  sx={{ m: 1, minWidth: 300, maxWidth: 400 }} label="Search" id="outlined-size-small" size="small" onChange={handleChange} />
                                        <FormControl sx={{ m: 1, width: 300 }}>
                                          <InputLabel id="demo-multiple-chip-label">Printers</InputLabel>
                                          <Select
                                            labelId="demo-multiple-chip-label"
                                            id="demo-multiple-chip"
                                            multiple
                                            value={selectedMachine}
                                            onChange={handleChange}
                                            input={<OutlinedInput id="select-multiple-chip" label="Printers" />}
                                            renderValue={(selected) => (
                                              <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                                                {selected.map((value) => (
                                                  <Chip key={value} label={value} />
                                                ))}
                                              </Box>
                                            )}
                                            MenuProps={MenuProps}
                                          >
                                            {Machines.map((machine) => (
                                              <MenuItem
                                                key={machine.machineID}
                                                value={machine.machineID}
                                                style={getStyles(machine.machineID, selectedMachine, theme)}
                                              >
                                                {machine.machineID}
                                              </MenuItem>
                                            ))}
                                          </Select>
                                          <br/>
                                          <Stack direction="row" spacing={9} style={{ marginLeft: "100px"}}>
                                            <Button variant="contained" type="submit">Apply</Button>
                                          </Stack>
                                        </FormControl>
                                      </div> 
                                    </form>
                              </FormControl>
                          <Divider />
                        </Card>
                    </Grid>

                   {/* Time Frame */}
                   <FormControl sx={{ m: 3, minWidth: 270, maxWidth: 400, height:142 }}>
                          <form onSubmit={submit}>
                                <h1>Select Time Frame</h1>
                                <DateRangePicker onChange={onChange} value={value}/>
                                <Stack direction="row" spacing={9} style={{marginTop: "66px", marginLeft: "100px"}}>
                                    <Button variant="contained" type="submit">Apply</Button>
                                </Stack>
                          </form>
                   </FormControl>     
                </Grid>
              </Container>
        </Box>
    </div>
  </div>
  );
};
 
export default Filter;