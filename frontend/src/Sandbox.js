import './App.css'
import React, {useEffect, useState} from 'react'
import {createBrowserRouter} from 'react-router-dom'
import {MainLayout} from "./Components/Layouts/MainLayout";
import {apiUrl} from "./config";

const router = createBrowserRouter([
    {
        path: "/",
        element: <MainLayout penisSize={10}></MainLayout>
    }
]);

const people = [
    {
        name: "Maxim",
        lastName: "Gayduchek"
    },
    {
        name: "Kvoza",
        lastName: "xyieza =)"
    }
]

export default function Sandbox() {
    const [maxim, setMaxim] = useState("ne pidor")
    const [name, setName] = useState("")
    const [product, setProduct] = useState({name: "Unknown"})
    useEffect(() => {
        fetch(apiUrl + "api/products/" + 1)
            .then(data => data.json())
            .then(product => {
                setProduct(product)
            })
            .catch(reason => {
                console.log(reason)
            })
    }, []);
    return (
        <div>
            Product: {product.name}
            <br/>
            Maxim: {maxim}
            <br/>
            <input type={"text"} placeholder={"name"} value={name} onChange={(e) => {
                setName(e.target.value)
            }}/>
            <br/>
            Name: {name}
            <br/>
            {
                people.map((person) => (
                    <>
                        <span>Name: {person.name} Surname: {person.lastName}</span>
                        <br/>
                    </>
                ))
            }
            <MainLayout penisSize={10} setMaxim={setMaxim}></MainLayout>
            {/*<RouterProvider router={router}/>*/}
        </div>
    )
}
