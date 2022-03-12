import "./chart.css";
import React from "react";
import {ResponsiveContainer} from "recharts";
import { Line,Bar } from 'react-chartjs-2'
import Chart from 'chart.js/auto'  // eslint-disable-line


export function MediaUsageLine(props) {

  if(!props.Data ||  Object.keys(props.Data).length === 0   ) return <><div className="chart">
  <h3 className="chartTitle">Media Usage Line</h3>
   <p>No Data available..</p>
  </div>
  </>;
  // var Data = props.Data;
  // var mediaTypes = ['film','lightPaper','heavyPaper','lightBanner','textile','monomericVinyl','canvas','polymericCastVinyl','heavyBanner','paper','thickFilm'];
  // for (let i = 0; i < props.Data.length; i++) {
  //   // for (let index = 0; index < 10; index++) {
  //   //   //Data.map(x=> {if(x===0){return 'N/A'}else return x})
      
  //   // }
  // }

  //console.log(Data)
  // console.log(Data.map(x => x.heavyPaper))
  // console.log(Data.map(x => x.heavyPaper).map(x => {if(x === 0){return 'N/A'}else return x}))

  return (
    <div className="chart">
    <h3 className="chartTitle"> Media Category Usage (Line chart)</h3>
    <ResponsiveContainer width="100%" aspect={2 / 1}>
          <div>
              <div>
                  <Line
                      data={{
                        labels: props.Data.map(x => x.date),
                        datasets: [
                          {
                            label: 'Film',
                            data: props.Data.map(x => x.film).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: 'orange',
                            borderColor: 'orange',
                            borderWidth: 1.10
                          },
                          {
                            label: 'LightPaper',
                            data: props.Data.map(x => x.lightPaper).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: 'red',
                            borderColor: 'red',
                            borderWidth: 1.10
                          },
                          {
                            label: 'HeavyPaper',
                            data: props.Data.map(x => x.heavyPaper).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: 'blue',
                            borderColor: 'blue',
                            borderWidth: 1.10
                          },
                          {
                            label: 'LightBanner',
                            data: props.Data.map(x => x.lightBanner).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: 'gold',
                            borderColor: 'gold',
                            borderWidth: 1.10
                          },
                          {
                            label: 'Textile',
                            data: props.Data.map(x => x.textile).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: 'lightBlue',
                            borderColor: 'lightBlue',
                            borderWidth: 1.10
                          },
                          {
                            label: 'MonomericVinyl',
                            data: props.Data.map(x => x.monomericVinyl).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: 'forestgreen',
                            borderColor: 'forestgreen',
                            borderWidth: 1.10
                          },
                          {
                            label: 'Canvas',
                            data: props.Data.map(x => x.canvas).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: 'black',
                            borderColor: 'black',
                            borderWidth: 1.10
                          },
                          {
                            label: 'PolymericCastVinyl',
                            data: props.Data.map(x => x.polymericCastVinyl).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: '#E52B50',
                            borderColor: '#E52B50',
                            borderWidth: 1.10
                          },
                          {
                            label: 'HeavyBanner',
                            data: props.Data.map(x => x.heavyBanner).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: '#F19CBB',
                            borderColor: '#F19CBB',
                            borderWidth: 1.10
                          },
                          {
                            label: 'Paper',
                            data: props.Data.map(x => x.paper).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: '#9966CC',
                            borderColor: '#9966CC',
                            borderWidth: 1.10
                          },
                          {
                            label: 'ThickFilm',
                            data: props.Data.map(x => x.thickFilm).map(x => {if(x === 0){return 'N/A'}else return x}),
                            backgroundColor: '#665D1E',
                            borderColor: '#665D1E',
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
                              
                            },
                          x: {
                            
                          }
                        },
                        spanGaps: true,
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

export function MediaUsageBar(props) {

  console.log(props)

    if(!props.Data ||  Object.keys(props.Data).length === 0   ) return <><div className="chart">
    <h3 className="chartTitle">Media Usage</h3>
     <p>No Data available..</p>
    </div>
    </>;
  
    return (
      <div className="chart">
      <h3 className="chartTitle"> Media Category Usage</h3>
      <ResponsiveContainer width="100%" aspect={2 / 1}>
            <div>
                <div>
                    <Bar
                        data={{
                          labels: props.Data.map(x => x.date),
                          datasets: [
                            {
                              label: 'Film',
                              data: props.Data.map(x => x.film),
                              backgroundColor: 'orange'
                            },
                            {
                              label: 'LightPaper',
                              data: props.Data.map(x => x.lightPaper),
                              backgroundColor: 'red'
                            },
                            {
                              label: 'HeavyPaper',
                              data: props.Data.map(x => x.heavyPaper),
                              backgroundColor: 'blue'
                            },
                            {
                              label: 'LightBanner',
                              data: props.Data.map(x => x.lightBanner),
                              backgroundColor: 'gold'
                            },
                            {
                              label: 'Textile',
                              data: props.Data.map(x => x.textile),
                              backgroundColor: 'lightBlue'
                            },
                            {
                              label: 'MonomericVinyl',
                              data: props.Data.map(x => x.monomericVinyl),
                              backgroundColor: 'forestgreen'
                            },
                            {
                              label: 'Canvas',
                              data: props.Data.map(x => x.canvas),
                              backgroundColor: 'black'
                            },
                            {
                              label: 'PolymericCastVinyl',
                              data: props.Data.map(x => x.polymericCastVinyl),
                              backgroundColor: '#E52B50'
                            },
                            {
                              label: 'HeavyBanner',
                              data: props.Data.map(x => x.heavyBanner),
                              backgroundColor: '#F19CBB'
                            },
                            {
                              label: 'Paper',
                              data: props.Data.map(x => x.paper),
                              backgroundColor: '#9966CC'
                            },
                            {
                              label: 'ThickFilm',
                              data: props.Data.map(x => x.thickFilm),
                              backgroundColor: '#665D1E'
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