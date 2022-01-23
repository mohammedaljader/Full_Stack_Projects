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

export default function TopTenMachines(props) {
  
  return (    
    <div className="chart">
    <h3 className="chartTitle">Top 10 Machines</h3>
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
        <XAxis dataKey="machineID" name="machineID" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Bar dataKey="sum" stackId="a" name="machineID" fill="#C0E8D5" />
        </BarChart>
        </ResponsiveContainer>
    </div>
  );
}

