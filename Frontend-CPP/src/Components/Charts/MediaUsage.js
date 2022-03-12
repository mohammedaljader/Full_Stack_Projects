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

export default function MediaUsage(props) {
  
  return (    
    <div className="chart">
    <h3 className="chartTitle"> Media Usage</h3>
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
          <Bar dataKey="film" stackId="a" fill="orange" />
          <Bar dataKey="lightPaper" stackId="a" fill="red" />
          <Bar dataKey="heavyPaper" stackId="a" fill="blue" />
          <Bar dataKey="lightBanner" stackId="a" fill="black" />
          <Bar dataKey="textile" stackId="a" fill="lightBlue" />
          <Bar dataKey="monomericVinyl" stackId="a" fill="gray" />
          <Bar dataKey="canvas" stackId="a" fill="#C0E8D5" />
          <Bar dataKey="polymericCastVinyl" stackId="a" fill="#E52B50" />
          <Bar dataKey="heavyBanner" stackId="a" fill="#F19CBB" />
          <Bar dataKey="paper" stackId="a" fill="#9966CC" />
          <Bar dataKey="thickFilm" stackId="a" fill="#665D1E" />
        </BarChart>
        </ResponsiveContainer>
    </div>
  );
}

