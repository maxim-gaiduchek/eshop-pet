import {MainLayout} from "../Components/Layouts/MainLayout";
import {useNavigate} from "react-router-dom";
import {Input} from "antd";
import {SubmitButton} from "../Components/Buttons/SubmitButton";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";
import {useState} from "react";
import {createSeller} from "../Services/SellerService";
import {toast} from "react-toastify";
import {AdministratorSider} from "../Components/Administrator/AdministratorSider";

export function SellerCreationPage() {
    document.title = "New seller | Administrator | E-Shop Pet";
    const navigate = useNavigate();
    const [sellerName, setSellerName] = useState("");
    const [sellerSurname, setSellerSurname] = useState("");
    const [sellerEmail, setSellerEmail] = useState("");
    const [sellerPhone, setSellerPhone] = useState("");
    const [sellerAddress, setSellerAddress] = useState("");
    const [sellerPassword, setSellerPassword] = useState("");
    const [disabled, setDisabled] = useState(true);
    const setupData = (value, setter) => {
        setDisabled(!sellerName || !sellerSurname || !sellerEmail || !sellerPhone || !sellerAddress || !sellerPassword);
        setter(value);
    }
    const createOnClick = async (e) => {
        e.preventDefault();
        setDisabled(true);
        createSeller(sellerName, sellerSurname, sellerEmail, sellerPhone, sellerAddress, sellerPassword)
            .then(() => {
                toast("New seller successfully created!");
                navigate("/sellers");
            })
            .catch(() => {
                setDisabled(false);
            });
    }
    return (
        <MainLayout>
            <AdministratorSider/>
            <CenteredLayout>
                <form onSubmit={(e) => createOnClick(e)} style={{textAlign: "center"}}>
                    <h1>New seller</h1>
                    <Input type={"text"} placeholder={"Name"}
                           value={sellerName}
                           onChange={(e) => setupData(e.target.value, setSellerName)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"text"} placeholder={"Surname"}
                           value={sellerSurname}
                           onChange={(e) => setupData(e.target.value, setSellerSurname)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"email"} placeholder={"Email"}
                           value={sellerEmail}
                           onChange={(e) => setupData(e.target.value, setSellerEmail)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"phone"} placeholder={"Phone"}
                           value={sellerPhone}
                           onChange={(e) => setupData(e.target.value, setSellerPhone)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"text"} placeholder={"Address"}
                           value={sellerAddress}
                           onChange={(e) => setupData(e.target.value, setSellerAddress)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"password"} placeholder={"Password"}
                           value={sellerPassword}
                           onChange={(e) => setupData(e.target.value, setSellerPassword)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <SubmitButton disabled={disabled} value={"Create"}/>
                </form>
            </CenteredLayout>
        </MainLayout>
    )
}
