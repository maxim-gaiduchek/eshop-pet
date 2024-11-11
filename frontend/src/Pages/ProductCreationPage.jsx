import {MainLayout} from "../Components/Layouts/MainLayout";
import {useNavigate} from "react-router-dom";
import {Input, InputNumber, Select} from "antd";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";
import {useEffect, useState} from "react";
import {toast} from "react-toastify";
import {createProducts} from "../Services/ProductService";
import TextArea from "antd/lib/input/TextArea";
import {SubmitButton} from "../Components/Buttons/SubmitButton";
import {getCompanies} from "../Services/CompanyService";
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../colors";
import {MenuButtons} from "../Components/Sider/MenuButtons";

export function ProductCreationPage() {
    document.title = "New product | Seller | E-Shop Pet";
    const navigate = useNavigate();
    const [productName, setProductName] = useState("");
    const [productDescription, setProductDescription] = useState("");
    const [productCompanyId, setProductCompanyId] = useState();
    const [productCost, setProductCost] = useState();
    const [productCount, setProductCount] = useState();
    const [disabled, setDisabled] = useState(true);
    const [companies, setCompanies] = useState([]);
    const setupData = (value, setter) => {
        setDisabled(!productName || !productDescription || !productCost || !productCount || !productCompanyId);
        setter(value);
    }
    const createOnClick = async (e) => {
        e.preventDefault();
        setDisabled(true);
        createProducts(productName, productDescription, productCost, productCount, productCompanyId)
            .then(createdProduct => {
                toast("New product successfully created!");
                navigate("/companies/" + createdProduct.company.id);
            })
            .catch(() => {
                setDisabled(false);
            });
    }
    useEffect(() => {
        getCompanies(1, 0, {
            sellerIds: [localStorage.getItem("loginUserId")]
        })
            .then(companyPage => {
                setCompanies(companyPage.companies);
            })
            .catch(() => {
                setCompanies([]);
            });
    }, []);
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
                    <h1>New product</h1>
                    <Input type={"text"} placeholder={"Product name"}
                           value={productName}
                           onChange={(e) => setupData(e.target.value, setProductName)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <TextArea placeholder={"Product description"} rows={4}
                              value={productDescription}
                              onChange={(e) => setupData(e.target.value, setProductDescription)}
                              style={{width: "100%", margin: "10px 10px"}}/>
                    <InputNumber placeholder={"Product cost"} addonAfter={"€"}
                                 stringMode step={"0.01"} min={"0.01"} value={productCost}
                                 onChange={(value) => setupData(value, setProductCost)}
                                 style={{width: "100%", margin: "10px 10px"}}/>
                    <InputNumber placeholder={"Product count"}
                                 min={0} value={productCount}
                                 onChange={(value) => setupData(value, setProductCount)}
                                 style={{width: "100%", margin: "10px 10px"}}/>
                    <Select placeholder={"Select a company"}
                            optionFilterProp={"label"} showSearch={true}
                            onChange={(value) => setupData(value, setProductCompanyId)}
                            options={companies.map(company => {
                                return {
                                    value: company.id,
                                    label: company.name,
                                }
                            })}
                            style={{width: "100%", margin: "10px 10px"}}/>
                    <SubmitButton disabled={disabled} value={"Create"}/>
                </form>
            </CenteredLayout>
        </MainLayout>
    )
}
