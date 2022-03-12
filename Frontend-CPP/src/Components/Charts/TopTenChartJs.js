import "./chart.css";
import React from "react";
import {ResponsiveContainer} from "recharts";
import { Bar } from 'react-chartjs-2'
import Chart from 'chart.js/auto' // eslint-disable-line


export function TopTenChartJs(props) {

    console.log(props)

    if(!props.Data ||  Object.keys(props.Data).length === 0   ) 
    return <><div className="chart">
    <h3 className="chartTitle">Top 10 Machines</h3>
     <p>No Data available..</p>
    </div>
    </>;

  return (
    <div className="chart">
    <h3 className="chartTitle">Top 10 Machines</h3>
    <ResponsiveContainer width="100%" aspect={2 / 1}>
          <div>
              <div>
                  <Bar
                      data={{
                        labels: props.Data.map(x => x.machineID),
                        datasets: [
                          {
                            label: 'Value',
                            data: props.Data.map(x => x.sum),
                            backgroundColor: '#C0E8D5'
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
