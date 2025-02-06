import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {createFilterCategory} from "../../../Services/FilterCategoryService";
import {toast} from "react-toastify";
import {MainLayout} from "../../../Components/Layouts/MainLayout";
import Sider from "antd/lib/layout/Sider";
import {MenuButtons} from "../../../Components/Sider/MenuButtons";
import {CenteredLayout} from "../../../Components/Layouts/CenteredLayout";
import {Input} from "antd";
import {SubmitButton} from "../../../Components/Buttons/SubmitButton";
import {secondaryBackgroundColor} from "../../../colors";


export function FilterCategoryCreationPage() {
    document.title = "New filter category | Settings | E-Shop Pet";
    const navigate = useNavigate();
    const [categoryName, setCategoryName] = useState("");
    const [disabled, setDisabled] = useState(true);
    const setupData = (value, setter) => {
        setter(value);
        setDisabled(!categoryName);
    }
    const createOnClick = async (e) => {
        e.preventDefault();
        setDisabled(true);
        createFilterCategory(categoryName)
            .then(() => {
                toast("New filter category successfully created!");
                navigate("/settings");
            })
            .catch(() => {
                setDisabled(false);
            });
    }
    return (
        <MainLayout>
            <Sider style={{
                height: "100%",
                backgroundColor: secondaryBackgroundColor,
                overflowY: "hidden",
                alignItems: "center",
            }}>
                <MenuButtons/>
            </Sider>
            <CenteredLayout>
                <form onSubmit={(e) => createOnClick(e)} style={{textAlign: "center"}}>
                    <h1>New filter category</h1>
                    <Input type={"text"} placeholder={"Category name"}
                           value={categoryName}
                           onChange={(e) => setupData(e.target.value, setCategoryName)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <SubmitButton disabled={disabled} value={"Create"}/>
                </form>
            </CenteredLayout>
        </MainLayout>
    )
}
