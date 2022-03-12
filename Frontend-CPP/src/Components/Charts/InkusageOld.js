import "./chart.css";
import React from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer
} from "recharts";

export default function Inkusage(props) {
  
  return (    
    <div className="chart">
    <h3 className="chartTitle"> Ink usage</h3>
    <ResponsiveContainer width="100%" aspect={2 / 1}>
        <BarChart
        width={500}
        height={300}
        data={props.Data}
        margin={{
            top: 20,
            right: 30,
            left: 20,
            bottom: 5
        }}
        >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="date" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Bar dataKey="color_black" stackId="a" fill="black" />
        <Bar dataKey="color_cyan" stackId="a" fill="cyan" />
        <Bar dataKey="color_yellow" stackId="a" fill="yellow" />
        <Bar dataKey="color_magenta" stackId="a" fill="magenta" />
        </BarChart>
        </ResponsiveContainer>
    </div>
  );
}

