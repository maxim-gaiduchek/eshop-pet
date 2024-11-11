import {MainLayout} from "../Components/Layouts/MainLayout";
import {useEffect, useState} from "react";
import {getProducts} from "../Services/ProductService";
import {ProductItem} from "../Components/Product/ProductItem";
import {Flex, Pagination} from "antd"
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../colors";
import {mockProducts} from "../mock";
import {MenuButtons} from "../Components/Sider/MenuButtons";

export function ShopPage() {
    document.title = "Products | E-Shop Pet";
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const pageParam = urlParams.get("page");
    const pageSizeParam = urlParams.get("pageSize");
    const [products, setProducts] = useState(mockProducts);
    const [page, setPage] = useState(pageParam ? +pageParam : 1);
    const [pageSize, setPageSize] = useState(pageSizeParam ? +pageSizeParam : 10);
    const [total, setTotal] = useState(products.length);
    const onTablePaginationChange = (page, pageSize) => {
        setPage(page);
        setPageSize(pageSize);
    };
    useEffect(() => {
        getProducts(page, pageSize)
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
                setProducts(mockProducts);
                setPage(1);
            })
    }, [page, pageSize]);
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
                <h1 style={{width: "100%"}}>Search</h1>
                {
                    products.map((product) => (
                        <ProductItem product={product}/>
                    ))
                }
                <Pagination pageSize={pageSize} total={total} showSizeChanger={true} onChange={onTablePaginationChange}
                            align={"end"} style={{width: "100%"}}/>
            </Flex>
        </MainLayout>
    )
}
