import {useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {Button, Input} from "antd";
import {MainLayout} from "../Components/Layouts/MainLayout";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";
import createCustomer from "../Services/CustomerService";

export function RegistrationPage() {
    document.title = "Registration | E-Shop Pet";
    const [customerName, setCustomerName] = useState("");
    const [customerSurname, setCustomerSurname] = useState("");
    const [customerEmail, setCustomerEmail] = useState("");
    const [customerPhone, setCustomerPhone] = useState("");
    const [customerAddress, setCustomerAddress] = useState("");
    const [customerPassword, setCustomerPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const registerOnClick = async () => {
        setLoading(true);
        createCustomer(customerName, customerSurname, customerEmail, customerPhone, customerAddress, customerPassword)
            .then(user => {
                console.log(user);
                localStorage.setItem("loginUserId", user.id);
                navigate("/shop");
            })
            .catch(() => {
                setLoading(false);
            });
    }
    return (
        <MainLayout>
            <CenteredLayout>
                <h1>Registration</h1>
                <p>Already have an account? <Link to={"/login"}>Login</Link></p>
                <Input type={"text"} placeholder={"Name"}
                       value={customerName}
                       onChange={(e) => setCustomerName(e.target.value)}
                       style={{width: "100%"}}/>
                <Input type={"text"} placeholder={"Surname"}
                       value={customerSurname}
                       onChange={(e) => setCustomerSurname(e.target.value)}
                       style={{width: "100%"}}/>
                <Input type={"email"} placeholder={"Email"}
                       value={customerEmail}
                       onChange={(e) => setCustomerEmail(e.target.value)}
                       style={{width: "100%"}}/>
                <Input type={"phone"} placeholder={"Phone"}
                       value={customerPhone}
                       onChange={(e) => setCustomerPhone(e.target.value)}
                       style={{width: "100%"}}/>
                <Input type={"text"} placeholder={"Address"}
                       value={customerAddress}
                       onChange={(e) => setCustomerAddress(e.target.value)}
                       style={{width: "100%"}}/>
                <Input type={"password"} placeholder={"Password"}
                       value={customerPassword}
                       onChange={(e) => setCustomerPassword(e.target.value)}
                       style={{width: "100%"}}/>
                <Button onClick={registerOnClick} disabled={loading} loading={loading}>Register</Button>
            </CenteredLayout>
        </MainLayout>
    )
}
