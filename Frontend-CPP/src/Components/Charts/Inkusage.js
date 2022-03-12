import "./chart.css";
import React from "react";
import {ResponsiveContainer} from "recharts";
import { Line,Bar } from 'react-chartjs-2'
import Chart from 'chart.js/auto' // eslint-disable-line



export function InkUsageLine(props) {

  if(!props.Data ||  Object.keys(props.Data).length === 0  ) return <><div className="chart">
  <h3 className="chartTitle">Ink usage Line</h3>
   <p>No Data available..</p>
  </div>
  </>;

  return (
    <div className="chart">
    <h3 className="chartTitle"> Ink usage (Line chart)</h3>
    <ResponsiveContainer width="100%" aspect={2 / 1}>
          <div>
              <div>
                  <Line
                      data={{
                        labels: props.Data.map(x => x.date),
                        datasets: [
                          {
                            label: 'Black',
                            data: props.Data.map(x => x.color_black),
                            backgroundColor: 'black',
                            borderColor: 'Black',
                            borderWidth: 1.10
                          },
                          {
                            label: 'Cyan',
                            data: props.Data.map(x => x.color_cyan),
                            backgroundColor: 'cyan',
                            borderColor: 'cyan',
                            borderWidth: 1.10
                          },
                          {
                            label: 'Yellow',
                            data: props.Data.map(x => x.color_yellow),
                            backgroundColor: 'yellow',
                            borderColor: '#ffdf00',
                            borderWidth: 1.10
                          },
                          {
                            label: 'Magenta',
                            data: props.Data.map(x => x.color_magenta),
                            backgroundColor: 'magenta',
                            borderColor: 'magenta',
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

export function InkUsageBar(props) {

  if(!props.Data ||  Object.keys(props.Data).length === 0   ) return <><div className="chart">
  <h3 className="chartTitle">Ink usage</h3>
   <p>No Data available..</p>
  </div>
  </>;

  return (
    <div className="chart">
    <h3 className="chartTitle"> Ink usage</h3>
    <ResponsiveContainer width="100%" aspect={2 / 1}>
          <div>
              <div>
                  <Bar
                      data={{
                        labels: props.Data.map(x => x.date),
                        datasets: [
                          {
                            label: 'Black',
                            data: props.Data.map(x => x.color_black),
                            backgroundColor: 'black'
                          },
                          {
                            label: 'Cyan',
                            data: props.Data.map(x => x.color_cyan),
                            backgroundColor: 'cyan'
                          },
                          {
                            label: 'Yellow',
                            data: props.Data.map(x => x.color_yellow),
                            backgroundColor: 'yellow'
                          },
                          {
                            label: 'Magenta',
                            data: props.Data.map(x => x.color_magenta),
                            backgroundColor: 'magenta'
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
