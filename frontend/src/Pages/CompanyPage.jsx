import {Link, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getCompany} from "../Services/CompanyService";
import {MainLayout} from "../Components/Layouts/MainLayout";
import {Button, Flex, Table} from "antd";
import {deleteProduct, getProducts} from "../Services/ProductService";
import {DeleteOutlined, LinkOutlined} from "@ant-design/icons";
import {mockCompany, mockProducts} from "../mock";
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../colors";
import {MenuButtons} from "../Components/Sider/MenuButtons";

const productColumns = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
        render: (id) => (
            <Link to={"/products/" + id}>
                {id}
                <LinkOutlined/>
            </Link>
        ),
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Description',
        dataIndex: 'description',
        key: 'description',
    },
    {
        title: 'Company',
        dataIndex: 'company',
        key: 'company',
        render: (company) => (
            <Link to={"/companies/" + company.id}>
                {company.name}
                <LinkOutlined/>
            </Link>
        ),
    },
    {
        title: 'Cost',
        dataIndex: 'cost',
        key: 'cost',
        render: (cost) => (<>&euro; {cost}</>)
    },
    {
        title: 'Count',
        dataIndex: 'count',
        key: 'count',
    },
    {
        title: 'Deleted',
        dataIndex: 'deleted',
        key: 'deleted',
        render: (deleted) => deleted ? "true" : "false",
    },
];

export function CompanyPage() {
    document.title = "Company | Seller | E-Shop Pet";
    const {id} = useParams();
    // const [company, setCompany] = useState(mockCompany);
    const [company, setCompany] = useState([]);
    useEffect(() => {
        getCompany(id)
            .then(company => {
                setCompany(company);
                document.title = `${company.name} | Seller | E-Shop Pet`;
            })
            .catch(() => setCompany(mockCompany))
    }, []);
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const pageParam = urlParams.get("page");
    const pageSizeParam = urlParams.get("pageSize");
    const [products, setProducts] = useState([]);
    // const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(pageParam ? +pageParam : 1);
    const [pageSize, setPageSize] = useState(pageSizeParam ? +pageSizeParam : 10);
    const [total, setTotal] = useState(products.length);
    const fetchProducts = () => {
        setLoading(true);
        getProducts(page, pageSize, {
            companyIds: [id],
        })
            .then(productPage => {
                setLoading(false);
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
                setLoading(false);
                setProducts(mockProducts);
                setPage(1);
                setPageSize(10);
                setTotal(mockProducts.length);
            })
    }
    const onTablePaginationChange = (page, pageSize) => {
        setPage(page);
        setPageSize(pageSize);
    };
    useEffect(() => {
        fetchProducts();
    }, [company, page, pageSize]);
    const deleteCompanyProduct = (id) => {
        deleteProduct(id)
            .then(() => {
                const newProducts = products.map(product => {
                    if (product.id === id) {
                        product.deleted = true;
                    }
                    return product;
                })
                setProducts(newProducts);
            });
    }
    const columns = productColumns.concat({
        render: (_, {id}) => (
            <Button color="danger" variant="solid" onClick={() => deleteCompanyProduct(id)}>
                <DeleteOutlined/>
            </Button>
        ),
    });
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
            <Flex style={{
                height: "100%",
                width: "90%",
                maxWidth: 1200,
                margin: "0 auto",
                justifyContent: "flex-start",
                padding: "0 auto",
                overflowY: "auto",
                alignItems: "center",
                flexDirection: "column",
            }}>
                <Flex style={{
                    width: "100%",
                    alignItems: "center",
                    justifyContent: "space-between",
                }}>
                    <h1>{company.name} | Products</h1>
                    <Link to={"/products/new"}>
                        <Button>Add product</Button>
                    </Link>
                </Flex>
                <Table
                    dataSource={products}
                    columns={columns}
                    pagination={{
                        pageSize: pageSize,
                        total: total,
                        showSizeChanger: true,
                        onChange: onTablePaginationChange,
                        showTotal: (total) => "Items found: " + total,
                    }}
                    loading={loading}
                    style={{
                        width: "100%",
                        overflowX: "auto",
                        overflowY: "auto",
                    }}
                />
            </Flex>
        </MainLayout>
    )
}
