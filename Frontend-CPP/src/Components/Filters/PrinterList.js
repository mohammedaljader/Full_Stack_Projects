import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import axios from 'axios';
import Chip from '@mui/material/Chip';




export default function PrinterList(props) {
  const [Machines, setMashines] = React.useState([]);
  const [Printer, setPrinter] = React.useState("");
  const [PrinterTest, setPrinterTest] = React.useState(false);

  React.useEffect(() => {
     axios.get("http://localhost:8080/engineControl/machines").then((response) => {
       console.log(response.data)
       setMashines(response.data)
       setPrinterTest(false)
      })
  },[])

    

  const handleChange = (event) => {
    setPrinter(event.target.value)
    setPrinterTest(true)
    const arr = [event.target.value]
    axios.post("http://localhost:8080/engineControl/usedMediaTypes/usedMediaByMachineId", arr).then((response) => {
      props.UsedMediaSet(response.data)
    });
  };


  return (
    <div>
      <Box sx={{ minWidth: 120 }}>
      <FormControl sx={{width: 300 }}>
        <InputLabel id="demo-simple-select-label">Printer</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={Machines}
          label="Printer"
          onChange={handleChange}
          renderValue={() => (
            <div>
                {PrinterTest &&  <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                    <Chip key={Printer} label={Printer} />
                </Box>
                }
            </div>
          )}
        >
          {Machines.map((machine) => (
                      <MenuItem
                        key={machine.machineID}
                        value={machine.machineID}
                      >
                    {machine.machineID}
                   </MenuItem>
          ))}
        </Select>
      </FormControl>
    </Box>
    </div> 
  );
}