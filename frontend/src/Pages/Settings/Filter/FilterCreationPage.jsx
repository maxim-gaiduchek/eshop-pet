import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getFilterCategory} from "../../../Services/FilterCategoryService";
import {toast} from "react-toastify";
import {MainLayout} from "../../../Components/Layouts/MainLayout";
import Sider from "antd/lib/layout/Sider";
import {MenuButtons} from "../../../Components/Sider/MenuButtons";
import {CenteredLayout} from "../../../Components/Layouts/CenteredLayout";
import {Input} from "antd";
import {SubmitButton} from "../../../Components/Buttons/SubmitButton";
import {secondaryBackgroundColor} from "../../../colors";
import {createFilter} from "../../../Services/FilterService";


export function FilterCreationPage() {
    document.title = "New filter | Settings | E-Shop Pet";
    const {filterCategoryId} = useParams();
    const navigate = useNavigate();
    const [filterCategory, setFilterCategory] = useState({
        id: filterCategoryId,
        name: filterCategoryId
    });
    const [filterName, setFilterName] = useState("");
    const [disabled, setDisabled] = useState(false);
    useEffect(() => {
        getFilterCategory(filterCategoryId)
            .then(filterCategory => {
                document.title = `New filter | ${filterCategory.name} | Settings | E-Shop Pet`;
                setFilterCategory(filterCategory);
                setDisabled(false);
            })
    }, []);
    const setupData = (value, setter) => {
        setter(value);
        setDisabled(!filterName);
    }
    const createOnClick = async (e) => {
        e.preventDefault();
        setDisabled(true);
        createFilter(filterName, filterCategory.id)
            .then(() => {
                toast("New filter successfully created!");
                navigate("/settings/filter-categories/" + filterCategoryId);
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
                    <h1>New filter</h1>
                    <Input type={"text"} placeholder={"Filter name"}
                           value={filterName}
                           onChange={(e) => setupData(e.target.value, setFilterName)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"text"} placeholder={"Filter category name"}
                           value={"Filter category: " + filterCategory.name}
                           disabled={true}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <SubmitButton disabled={disabled} value={"Create"}/>
                </form>
            </CenteredLayout>
        </MainLayout>
    )
}
