import {
  Card,
  CardHeader,
  Divider,
} from '@mui/material';
import TextField from '@mui/material/TextField'; // eslint-disable-line
import FormControl from '@mui/material/FormControl';
import PrinterList from "./PrinterList";



export const PrintPage = () => {
//   const [personName, setPersonName] = useState([]);
//   const handleChangeMultiple = (event) => {
//     const { options } = event.target;
//     const value = [];
//     for (let i = 0, l = options.length; i < l; i += 1) {
//       if (options[i].selected) {
//         value.push(options[i].value);
//       }
//     }
//     setPersonName(value);
//   };


  return (
      <Card>
        <CardHeader
          title="Select Available Printers"
        />
        <Divider />
          <FormControl sx={{ m: 2, minWidth: 270, maxWidth: 400 }}>
                <PrinterList />
            </FormControl>
        <Divider />
      </Card>
  );
};