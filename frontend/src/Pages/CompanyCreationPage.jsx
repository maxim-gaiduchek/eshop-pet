import {MainLayout} from "../Components/Layouts/MainLayout";
import {useNavigate} from "react-router-dom";
import {Input} from "antd";
import {SubmitButton} from "../Components/Buttons/SubmitButton";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";
import {useState} from "react";
import {toast} from "react-toastify";
import {AdministratorSider} from "../Components/Administrator/AdministratorSider";
import {createCompany} from "../Services/CompanyService";

export function CompanyCreationPage() {
    document.title = "New company | E-Shop Pet";
    const navigate = useNavigate();
    const [companyName, setCompanyName] = useState("");
    const [disabled, setDisabled] = useState(true);
    const setupData = (value, setter) => {
        setDisabled(!companyName);
        setter(value);
    }
    const createOnClick = async (e) => {
        e.preventDefault();
        setDisabled(true);
        createCompany(companyName)
            .then(() => {
                toast("New company successfully created!");
                navigate("/companies");
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
                    <h1>New company</h1>
                    <Input type={"text"} placeholder={"Company name"}
                           value={companyName}
                           onChange={(e) => setupData(e.target.value, setCompanyName)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <SubmitButton disabled={disabled} value={"Create"}/>
                </form>
            </CenteredLayout>
        </MainLayout>
    )
}
