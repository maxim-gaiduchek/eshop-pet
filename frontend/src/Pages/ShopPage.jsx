import {MainLayout} from "../Components/Layouts/MainLayout";
import {useEffect, useState} from "react";
import {getProducts} from "../Services/ProductService";
import {ProductItem} from "../Components/Product/ProductItem";
import {Flex, Pagination} from "antd"
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../colors";
import {MenuButtons} from "../Components/Sider/MenuButtons";
import Search from "antd/lib/input/Search";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";

export function ShopPage() {
    document.title = "Products | E-Shop Pet";
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const pageParam = urlParams.get("page");
    const pageSizeParam = urlParams.get("pageSize");
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(pageParam ? +pageParam : 1);
    const [pageSize, setPageSize] = useState(pageSizeParam ? +pageSizeParam : 10);
    const [total, setTotal] = useState(products.length);
    const [name, setName] = useState("");
    const onTablePaginationChange = (page, pageSize) => {
        setPage(page);
        setPageSize(pageSize);
    };
    useEffect(() => {
        getProducts(page, pageSize, {
            name: name,
            deleted: [false],
        })
            .then(productPage => {
                setProducts(productPage.products);
                setPage(productPage.currentPage);
                setTotal(productPage.totalMatches);
            })
            /*.then(() => {
                setProducts(mockProducts);
                setPage(1);
                setPageSize(10);
                setTotal(mockProducts.length);
            })*/
            .catch(() => {
                setProducts([]);
                setPage(1);
            })
    }, [name, page, pageSize]);
    return (
        <MainLayout>
            <Sider style={{
                height: "100%",
                backgroundColor: secondaryBackgroundColor,
                overflowY: "hidden",
            }}>
                <MenuButtons/>
                <h3>Filters: </h3>
            </Sider>
            <Flex style={{
                height: "100%",
                width: "90%",
                maxWidth: 1200,
                margin: "0 auto",
                justifyContent: "flex-start",
                flexWrap: "wrap",
                padding: "0 auto",
                overflowY: "auto",
            }}>
                <Flex style={{
                    width: "100%",
                    alignItems: "center",
                    justifyContent: "space-between",
                    flexWrap: "nowrap",
                }}>
                    <h1 style={{width: "100%"}}>Search</h1>
                    <Search placeholder={"Search name..."} allowClear={true}
                            onSearch={value => setName(value)}
                            style={{width: "20%", margin: "10px 10px"}}/>
                </Flex>
                {
                    products && products.length !== 0 ?
                        products.map((product) => (
                            <ProductItem product={product}/>
                        )) :
                        <CenteredLayout>
                            <p style={{margin: "0 auto", fontSize: "16pt"}}>No items found</p>
                        </CenteredLayout>
                }
                <Pagination pageSize={pageSize} total={total} showSizeChanger={true}
                            showTotal={(total) => "Items found: " + total}
                            onChange={onTablePaginationChange}
                            align={"end"} style={{
                    width: "100%",
                    margin: "10px 10px",
                }}/>
            </Flex>
        </MainLayout>
    )
}
