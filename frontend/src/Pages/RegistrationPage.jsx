import {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {Input} from "antd";
import {MainLayout} from "../Components/Layouts/MainLayout";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";
import {login} from "../Services/AuthService";
import {SubmitButton} from "../Components/Buttons/SubmitButton";
import {createCustomer} from "../Services/CustomerService";

export function RegistrationPage() {
    document.title = "Registration | E-Shop Pet";
    const navigate = useNavigate();
    const [customerName, setCustomerName] = useState("");
    const [customerSurname, setCustomerSurname] = useState("");
    const [customerEmail, setCustomerEmail] = useState("");
    const [customerPhone, setCustomerPhone] = useState("");
    const [customerAddress, setCustomerAddress] = useState("");
    const [customerPassword, setCustomerPassword] = useState("");
    const [disabled, setDisabled] = useState(true);
    useEffect(() => {
        let loginUserId = localStorage.getItem("loginUserId");
        if (loginUserId) {
            navigate("/shop");
        }
    }, []);
    const loginAfterRegistration = async (e) => {
        e.preventDefault();
        setDisabled(true);
        login(customerEmail, customerPassword)
            .then(() => {
                navigate("/shop");
            })
            .catch(() => {
                setDisabled(false);
            });
    }
    const registerOnClick = async (e) => {
        setDisabled(true);
        createCustomer(customerName, customerSurname, customerEmail, customerPhone, customerAddress, customerPassword)
            .then(() => loginAfterRegistration(e))
            .catch(() => {
                setDisabled(false);
            });
    }
    const setupData = (value, setter) => {
        setDisabled(!customerName || !customerSurname || !customerEmail || !customerPhone || !customerAddress || !customerPassword);
        setter(value);
    }
    return (
        <MainLayout>
            <CenteredLayout>
                <form onSubmit={(e) => registerOnClick(e)} style={{textAlign: "center"}}>
                    <h1>Registration</h1>
                    <p>Already have an account? <Link to={"/login"}>Login</Link></p>
                    <Input type={"text"} placeholder={"Name"}
                           value={customerName}
                           onChange={(e) => setupData(e.target.value, setCustomerName)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"text"} placeholder={"Surname"}
                           value={customerSurname}
                           onChange={(e) => setupData(e.target.value, setCustomerSurname)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"email"} placeholder={"Email"}
                           value={customerEmail}
                           onChange={(e) => setupData(e.target.value, setCustomerEmail)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"phone"} placeholder={"Phone"}
                           value={customerPhone}
                           onChange={(e) => setupData(e.target.value, setCustomerPhone)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"text"} placeholder={"Address"}
                           value={customerAddress}
                           onChange={(e) => setupData(e.target.value, setCustomerAddress)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"password"} placeholder={"Password"}
                           value={customerPassword}
                           onChange={(e) => setupData(e.target.value, setCustomerPassword)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <SubmitButton disabled={disabled} value={"Register"}/>
                </form>
            </CenteredLayout>
        </MainLayout>
    )
}
