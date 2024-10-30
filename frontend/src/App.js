import './App.css'
import React from "react";
import {RouterProvider} from "react-router-dom";
import {router} from "./Router/Router";
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function App() {
    return (
        <>
            <RouterProvider router={router}/>
            <ToastContainer
                position={"bottom-right"}
                autoClose={2000}
                hideProgressBar={true}
            />
        </>
    )
}
