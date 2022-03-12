import "./chart.css";
import React from "react";
import {ResponsiveContainer} from "recharts";
import { Line, Bar } from 'react-chartjs-2' // eslint-disable-line
import Chart from 'chart.js/auto' // eslint-disable-line
import PrinterList from "../Filters/PrinterList";
import axios from "axios"; // eslint-disable-line





export function UsedMediaTypePerMachine() {
  const [UsedMedia, setUsedMedia] = React.useState([]);
   
  
  React.useEffect(() => {
    getUsedMediaTypesPerMachine();
 },[])

  async function getUsedMediaTypesPerMachine(){
    await axios.get("http://localhost:8080/engineControl/usedMediaTypes/test2").then((response) => {
      setUsedMedia(response.data);
    }); 
  }
     

    if(!UsedMedia ||  Object.keys(UsedMedia).length === 0  ) 
    return <><div className="chart">
    <h3 className="chartTitle">Used Media Types per Machine</h3>
     <p>No Data available..</p>
    </div>
    </>;

  return (
    <div className="chart">
    <h3 className="chartTitle">Used Media Types per Machine</h3>
    <PrinterList UsedMediaSet={setUsedMedia}/>
    <ResponsiveContainer width="100%" aspect={2 / 1}>
          <div>
              <div>
                  <Bar
                      data={{
                        labels: UsedMedia.map(x => x.name),
                        datasets: [
                          {
                            label: 'Area',
                            data: UsedMedia.map(x => x.area),
                            backgroundColor: 'blue'
                          },  
                        ],
                      }}
                      height={400}
                      width={600}
                      options={{
                        maintainAspectRatio: false,
                        scales: {
                          y: 
                            {
                              ticks: {
                                beginAtZero: true,
                              },
                              stacked: true,
                            },
                          x:{
                            stacked: true,
                          }
                        },
                        legend: {
                          labels: {
                            fontSize: 25,
                          },
                        },
                      }}
                    />
            </div>
        </div>
      </ResponsiveContainer>
    </div>
  );
}

