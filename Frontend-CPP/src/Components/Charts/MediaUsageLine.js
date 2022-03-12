import "./chart.css";
import React from "react";
import {
    ScatterChart,
    Scatter,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    ResponsiveContainer
  } from "recharts";

export default function MediaUsageLine(props) {
  return (
    <div className="chart">
    <h3 className="chartTitle"> Media Usage</h3>
    <ResponsiveContainer width="100%" aspect={2 / 1}>
    <ScatterChart
      width={400}
      height={400}
      data={props.Data}
      margin={{
        top: 20,
        right: 20,
        bottom: 20,
        left: 20
      }}
    >
        <CartesianGrid />
        <XAxis dataKey="date" name="Date"/>
        <YAxis />
        <Tooltip/>
        <Legend />
        <Scatter dataKey="film" stackId="a" name="film" fill="orange" />
        <Scatter dataKey="lightPaper" stackId="b" name="lightPaper" fill="red" />
        <Scatter dataKey="heavyPaper" stackId="c" name="heavyPaper" fill="blue" />
        <Scatter dataKey="lightBanner" stackId="d" name="lightBanner" fill="black" />
        <Scatter dataKey="textile" stackId="e" name="textile" fill="lightBlue" />
        <Scatter dataKey="monomericVinyl" stackId="f" name="monomericVinyl" fill="gray" />
        <Scatter dataKey="canvas" stackId="g" name="canvas" fill="#C0E8D5" />
        <Scatter dataKey="polymericCastVinyl" stackId="h" name="polymericCastVinyl" fill="#E52B50" />
        <Scatter dataKey="heavyBanner" stackId="i" name="heavyBanner" fill="#F19CBB" />
        <Scatter dataKey="paper" stackId="j" name="paper" fill="#9966CC" />
        <Scatter dataKey="thickFilm" stackId="k" name="thickFilm" fill="#665D1E" />
      </ScatterChart>
      </ResponsiveContainer>
    </div>
  );
}
