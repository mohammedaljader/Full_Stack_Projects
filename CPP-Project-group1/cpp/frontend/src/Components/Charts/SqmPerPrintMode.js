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

export default function SqmPerPrintMode(props) {
  
  return (    
    <div className="chart">
    <h3 className="chartTitle"> Sqm per print mode</h3>
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
        <Bar dataKey="pass_1" stackId="a" name="1_pass" fill="orange" />
          <Bar dataKey="pass_highDensity_1" name="1_pass_highDensity" stackId="a" fill="red" />
          <Bar dataKey="pass_2" stackId="a" name="2_pass" fill="blue" />
          <Bar dataKey="pass_4" stackId="a" name="4_pass" fill="black" />
          <Bar dataKey="pass_8" stackId="a" name="8_pass" fill="lightBlue" />
          <Bar dataKey="pass_highDensity_8" stackId="a" name="8_pass_highDensity" fill="gray" />
          <Bar dataKey="pass_16" stackId="a" name="16_pass_highDensity" fill="#C0E8D5" />
          <Bar dataKey="other" stackId="a" name="others" fill="#E52B50" />
        </BarChart>
        </ResponsiveContainer>
    </div>
  );
}

