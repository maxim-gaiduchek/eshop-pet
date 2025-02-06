import {useParams} from 'react-router-dom';
import {useEffect, useState} from "react";
import {getProduct, updateProduct} from "../../Services/ProductService";
import {Button, Col, Flex, Input, InputNumber, Rate, Row, Select, Tree} from "antd";
import {mockProduct} from "../../mock";
import {Comment} from "../../Components/Product/Comment";
import TextArea from "antd/lib/input/TextArea";
import {secondaryBackgroundColor, secondaryTextColor} from "../../colors";
import {getFilters} from "../../Services/FilterService";
import {SubmitButton} from "../../Components/Buttons/SubmitButton";
import {toast} from "react-toastify";

export function ProductPage() {
    document.title = "Product | E-Shop Pet";
    const {id} = useParams();
    const [product, setProduct] = useState(mockProduct);
    const [productDescription, setProductDescription] = useState("");
    const [productCost, setProductCost] = useState();
    const [productCount, setProductCount] = useState();
    const [productFilterIds, setProductFilterIds] = useState([]);
    const [disabled, setDisabled] = useState(false);
    const [filters, setFilters] = useState([]);
    const [filterData, setFilterData] = useState([]);
    useEffect(() => {
        getProduct(id)
            .then(product => {
                setProduct(product);
                document.title = `${product.name} | ${product.company.name} | E-Shop Pet`;
                setProductDescription(product.description);
                setProductCost(product.cost);
                setProductCount(product.count);
                setProductFilterIds(product.filters.map(filter => filter.id))
            })
            .catch(() => setProduct(mockProduct))
    }, []);
    useEffect(() => {
        getFilters()
            .then(filters => setFilters(filters))
    }, []);
    useEffect(() => {
        let newFilterData = filters.map(category => {
            return {
                title: category.name,
                key: category.id,
                children: category.filters.map(filter => {
                    return {
                        title: (
                            <p>{filter.name}</p>
                        ),
                        key: category.id + "-" + filter.id,
                    }
                })
            }
        });
        setFilterData(newFilterData);
    }, [filters]);
    const saveOnClick = async (e) => {
        e.preventDefault();
        setDisabled(true);
        updateProduct(product.id, productDescription, productCost, productCount, productFilterIds)
            .then(updatedProduct => {
                toast("Product successfully updated!");
                setProduct(updatedProduct)
                setDisabled(false);
            })
            .catch(() => {
                setDisabled(false);
            });
    }
    const onFilterCheck = (checkedKeys) => {
        const newProductFilterIds = checkedKeys
            .filter(key => typeof key === "string")
            .map(key => +key.split("-")[1]);
        setProductFilterIds(newProductFilterIds);
    };
    return (
        <div style={{width: "100%", maxWidth: 1200, margin: "0 auto", paddingTop: 20}}>
            <Row wrap={false} gutter={12}>
                <Col flex="500px">
                    <div className={'productImage'} style={{
                        backgroundImage: "url(https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg)"
                    }}></div>
                </Col>
                <Col flex="auto">
                    <h3 className={'productTitle'}>{product.name}</h3>
                    <div className={'productDetails'}>
                        <span>| by: {product.company.name}</span>
                    </div>
                    <h4 className={'productTitle'}>&euro;{product.cost}</h4>
                    <Rate allowHalf defaultValue={2.5} disabled style={{
                        margin: "10px 0"
                    }}/><br/><br/>
                    <div className={"productDescription"}>
                        {product.description}
                    </div>
                </Col>
            </Row>
            {localStorage.getItem("loginUserRole") === "ROLE_SELLER" ? (
                <Flex style={{
                    width: "100%",
                    border: "3px solid " + secondaryBackgroundColor,
                    borderRadius: "10px",
                    margin: "15px 0",
                    padding: "10px",
                    flexDirection: "column",
                }}>
                    <h1>Product's settings | Seller's zone</h1>
                    <i style={{color: secondaryTextColor}}>Only customers <b>can not</b> see this</i>
                    <form onSubmit={saveOnClick} style={{textAlign: "center"}}>
                        <Flex style={{
                            width: "100%",
                            flexDirection: "row",
                            justifyContent: "space-around",
                            textAlign: "center",
                        }}>
                            <Flex style={{
                                flexDirection: "column",
                                width: "40%",
                            }}>
                                <Input type={"text"} placeholder={"Product name"}
                                       value={product.name} disabled={true}
                                       style={{width: "80%", margin: "10px 10px"}}/>
                                <TextArea placeholder={"Product description"} rows={4}
                                          value={productDescription}
                                          onChange={(e) => setProductDescription(e.target.value)}
                                          style={{width: "80%", margin: "10px 10px"}}/>
                                <InputNumber placeholder={"Product cost"} addonAfter={"€"}
                                             stringMode step={"0.01"} min={"0.01"} value={productCost}
                                             onChange={(value) => setProductCost(value)}
                                             style={{width: "80%", margin: "10px 10px"}}/>
                                <InputNumber placeholder={"Product count"}
                                             min={0} value={productCount}
                                             onChange={(value) => setProductCount(value)}
                                             style={{width: "80%", margin: "10px 10px"}}/>
                                <Select placeholder={"Select a company"} value={product.company.name} disabled={true}
                                        optionFilterProp={"label"} showSearch={true}
                                        style={{width: "80%", margin: "10px 10px"}}/>
                                <SubmitButton
                                    disabled={disabled || !productDescription || !productCost || !productCount}
                                    value={"Save"} style={{width: "80%", margin: "10px 10px"}}/>
                            </Flex>
                            <Flex style={{
                                width: "40%",
                            }}>
                                {filterData.length ? (
                                    <Tree checkable defaultExpandedKeys={filters.map(category => category.id)}
                                          defaultCheckedKeys={filters
                                              .flatMap(category => category.filters)
                                              .filter(filter => productFilterIds.includes(filter.id))
                                              .map(filter => filter.filterCategoryId + "-" + filter.id)}
                                          onCheck={onFilterCheck}
                                          treeData={filterData}
                                          style={{backgroundColor: "#f5f5f5"}}
                                    />
                                ) : (
                                    <></>
                                )}
                            </Flex>
                        </Flex>
                    </form>
                </Flex>
            ) : (
                <></>
            )}
            <div className={'commentsContainer'}>
                <h4>Reviews</h4>
                <div className={"reviewContainer"}>
                    <span style={{fontSize: 16}}>Leave a review</span>
                    <br/><br/>
                    <TextArea autoSize={{minRows: 4, maxRows: 6}} placeholder={"Comment"}/>
                    <br/><br/>
                    <Rate/> <Button>Send</Button>
                </div>
                <Comment/>
                <Comment/>
                <Comment/>
                <Comment/>
                <Comment/>
            </div>
        </div>
    )
}
