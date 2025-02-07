import {MainLayout} from "../../Components/Layouts/MainLayout";
import {useNavigate} from "react-router-dom";
import {Button, Input, InputNumber, Select, Upload} from "antd";
import {CenteredLayout} from "../../Components/Layouts/CenteredLayout";
import {useEffect, useState} from "react";
import {toast} from "react-toastify";
import {createProduct} from "../../Services/ProductService";
import TextArea from "antd/lib/input/TextArea";
import {SubmitButton} from "../../Components/Buttons/SubmitButton";
import {getCompanies} from "../../Services/CompanyService";
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../../colors";
import {MenuButtons} from "../../Components/Sider/MenuButtons";
import {UploadOutlined} from "@ant-design/icons";

export function ProductCreationPage() {
    document.title = "New product | Seller | E-Shop Pet";
    const navigate = useNavigate();
    const [productName, setProductName] = useState("");
    const [productDescription, setProductDescription] = useState("");
    const [productCompanyId, setProductCompanyId] = useState();
    const [productCost, setProductCost] = useState();
    const [productCount, setProductCount] = useState();
    const [productImageUploaded, setProductImageUploaded] = useState(false);
    const [productImageFile, setProductImageFile] = useState();
    const [disabled, setDisabled] = useState(false);
    const [companies, setCompanies] = useState([]);
    const createOnClick = async (e) => {
        e.preventDefault();
        setDisabled(true);
        createProduct(productName, productDescription, productCost, productCount, productCompanyId, productImageFile)
            .then(createdProduct => {
                toast("New product successfully created!");
                navigate("/products/" + createdProduct.id);
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
    const uploadImage = (file) => {
        setProductImageFile(file);
        setProductImageUploaded(true);
        return false;
    };
    const removeImage = () => {
        setProductImageUploaded(false);
    };
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
                <form onSubmit={createOnClick} style={{textAlign: "center"}}>
                    <h1>New product</h1>
                    <Input type={"text"} placeholder={"Product name"}
                           value={productName}
                           onChange={(e) => setProductName(e.target.value)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <TextArea placeholder={"Product description"} rows={4}
                              value={productDescription}
                              onChange={(e) => setProductDescription(e.target.value)}
                              style={{width: "100%", margin: "10px 10px"}}/>
                    <InputNumber placeholder={"Product cost"} addonAfter={"€"}
                                 stringMode step={"0.01"} min={"0.01"} value={productCost}
                                 onChange={(value) => setProductCost(value)}
                                 style={{width: "100%", margin: "10px 10px"}}/>
                    <InputNumber placeholder={"Product count"}
                                 min={0} value={productCount}
                                 onChange={(value) => setProductCount(value)}
                                 style={{width: "100%", margin: "10px 10px"}}/>
                    <Select placeholder={"Select a company"}
                            optionFilterProp={"label"} showSearch={true}
                            onChange={(value) => setProductCompanyId(value)}
                            options={companies.map(company => {
                                return {
                                    value: company.id,
                                    label: company.name,
                                }
                            })}
                            style={{width: "100%", margin: "10px 10px"}}/>
                    <Upload name={"image"} multiple={false} beforeUpload={uploadImage} onRemove={removeImage}>
                        <Button icon={<UploadOutlined/>} style={{width: "100%", margin: "10px 10px"}}>
                            Upload product's image
                        </Button>
                    </Upload>
                    <SubmitButton disabled={disabled || !productName || !productDescription || !productCost ||
                        !productCount || !productCompanyId || !productImageUploaded} value={"Create"}/>
                </form>
            </CenteredLayout>
        </MainLayout>
    )
}
