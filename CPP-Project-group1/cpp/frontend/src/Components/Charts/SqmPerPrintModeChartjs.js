import "./chart.css";
import React from "react";
import {ResponsiveContainer} from "recharts";
import { Line, Bar } from 'react-chartjs-2' // eslint-disable-line
import Chart from 'chart.js/auto' // eslint-disable-line


export function SqmPerPrintModeChartJs(props) {

    if(!props.Data ||  Object.keys(props.Data).length === 0   ) 
    return <><div className="chart">
    <h3 className="chartTitle"> Sqm Per Print Mode</h3>
     <p>No Data available..</p>
    </div>
    </>;
  
    return (
      <div className="chart">
      <h3 className="chartTitle"> Sqm Per Print Mode</h3>
      <ResponsiveContainer width="100%" aspect={2 / 1}>
            <div>
                <div>
                    <Bar
                        data={{
                          labels: props.Data.map(x => x.date),
                          datasets: [
                            {
                              label: 'pass 1',
                              data: props.Data.map(x => x.pass_1),
                              backgroundColor: 'orange'
                            },
                            {
                              label: 'pass highDensity 1',
                              data: props.Data.map(x => x.pass_highDensity_1),
                              backgroundColor: 'red'
                            },
                            {
                              label: 'pass 2',
                              data: props.Data.map(x => x.pass_2),
                              backgroundColor: 'blue'
                            },
                            {
                              label: 'pass 4',
                              data: props.Data.map(x => x.pass_4),
                              backgroundColor: 'black'
                            },
                            {
                              label: 'pass 8',
                              data: props.Data.map(x => x.pass_8),
                              backgroundColor: '#008000'
                            },
                            {
                              label: 'pass highDensity 8',
                              data: props.Data.map(x => x.pass_highDensity_8),
                              backgroundColor: 'gray'
                            },
                            {
                              label: 'pass highDensity 16',
                              data: props.Data.map(x => x.pass_16),
                              backgroundColor: '#C0E8D5'
                            },
                            {
                              label: 'others',
                              data: props.Data.map(x => x.other),
                              backgroundColor: '#E52B50'
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



  export function SqmPerPrintModeLine(props) {

    if(!props.Data ||  Object.keys(props.Data).length === 0   ) 
    return <><div className="chart">
    <h3 className="chartTitle"> Sqm Per Print Mode Line</h3>
     <p>No Data available..</p>
    </div>
    </>;
  
    return (
      <div className="chart">
      <h3 className="chartTitle"> Sqm Per Print Mode (Line chart)</h3>
      <ResponsiveContainer width="100%" aspect={2 / 1}>
            <div>
                <div>
                    <Line
                        data={{
                          labels: props.Data.map(x => x.date),
                          datasets: [
                            {
                              label: 'pass 1',
                              data: props.Data.map(x => x.pass_1).map(x => {if(x === 0){return 'N/A'}else return x}),
                              backgroundColor: 'orange',
                              borderColor: 'orange',
                              borderWidth: 1.10
                            },
                            {
                              label: 'pass highDensity 1',
                              data: props.Data.map(x => x.pass_highDensity_1).map(x => {if(x === 0){return 'N/A'}else return x}),
                              backgroundColor: 'red',
                              borderColor: 'red',
                              borderWidth: 1.10
                            },
                            {
                              label: 'pass 2',
                              data: props.Data.map(x => x.pass_2).map(x => {if(x === 0){return 'N/A'}else return x}),
                              backgroundColor: 'blue',
                              borderColor: 'blue',
                              borderWidth: 1.10
                            },
                            {
                              label: 'pass 4',
                              data: props.Data.map(x => x.pass_4),
                              backgroundColor: 'black',
                              borderColor: 'black',
                              borderWidth: 1.10
                            },
                            {
                              label: 'pass 8',
                              data: props.Data.map(x => x.pass_8),
                              backgroundColor: '#008000',
                              borderColor: '#008000',
                              borderWidth: 1.10
                            },
                            {
                              label: 'pass highDensity 8',
                              data: props.Data.map(x => x.pass_highDensity_8).map(x => {if(x === 0){return 'N/A'}else return x}),
                              backgroundColor: 'gray',
                              borderColor: 'gray',
                              borderWidth: 1.10
                            },
                            {
                              label: 'pass highDensity 16',
                              data: props.Data.map(x => x.pass_16).map(x => {if(x === 0){return 'N/A'}else return x}),
                              backgroundColor: '#C0E8D5',
                              borderColor: '#C0E8D5',
                              borderWidth: 1.10
                            },
                            {
                              label: 'others',
                              data: props.Data.map(x => x.other),
                              backgroundColor: '#E52B50',
                              borderColor: '#E52B50',
                              borderWidth: 1.10
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
                                stacked: false,
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