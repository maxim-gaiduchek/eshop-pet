import './App.css'
import React from "react";
import {RouterProvider} from "react-router-dom";
import {router} from "./Router/Router";

export default function App() {
    return (
        <RouterProvider router={router}/>
    )
}