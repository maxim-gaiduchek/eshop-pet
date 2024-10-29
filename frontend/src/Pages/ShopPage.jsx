import {MainLayout} from "../Components/Layouts/MainLayout";
import {useEffect, useState} from "react";
import {getProducts} from "../Services/ProductService";
import {ProductItem} from "../Components/Product/ProductItem";
import {Flex} from "antd"
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../colors";

const defaultProductPage = {
    products: [
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
        {
            id: 1,
            name: "Test",
            description: "Test description",
            price: 300
        },
    ],
    //products: [],
    currentPage: 1,
    totalMatches: 0,
    totalPages: 0
};

export function ShopPage() {
    document.title = "Products | E-Shop Pet";
    const [productPage, setProductPage] = useState(defaultProductPage);
    useEffect(() => {
        getProducts()
            .then(data => data.json())
            .then(json => setProductPage(json))
            .then(() => setProductPage(defaultProductPage))
            .catch(() => setProductPage(defaultProductPage))
    }, []);
    return (
        <MainLayout>
            <Sider style={{
                height: "100%",
                backgroundColor: secondaryBackgroundColor,
                overflowY: "auto",
            }}>
                <p>Filters: </p>
            </Sider>
            <Flex style={{
                height: "100%",
                width: "100%",
                justifyContent: "flex-start",
                flexWrap: "wrap",
                padding: "0 auto",
                overflowY: "auto",
            }}>
                {
                    productPage.products.map((product) => {
                        return (
                            <ProductItem product={product}/>
                        )
                    })
                }
            </Flex>
        </MainLayout>
    )
}
