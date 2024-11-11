import {MainLayout} from "../Components/Layouts/MainLayout";
import {Button, Flex, Table} from "antd";
import {Link} from "react-router-dom";
import {LinkOutlined, MailOutlined} from "@ant-design/icons";
import {useEffect, useState} from "react";
import {getSellers} from "../Services/SellerService";
import {mockSellers} from "../mock";
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../colors";
import {MenuButtons} from "../Components/Sider/MenuButtons";

const columns = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
        render: (id) => (
            <Link to={"/sellers/" + id}>
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
        title: 'Surname',
        dataIndex: 'surname',
        key: 'surname',
    },
    {
        title: 'Phone',
        dataIndex: 'phone',
        key: 'phone',
    },
    {
        title: 'E-Mail',
        dataIndex: 'email',
        key: 'email',
        render: (email) => (
            <Link to={"mailto:" + email}>
                {email}
                <MailOutlined/>
            </Link>
        ),
    },
    {
        title: 'Companies',
        dataIndex: 'companies',
        key: 'companies',
        render: (_, {companies}) => (companies && companies.length !== 0 ?
                <>
                    {
                        companies.map(company => (
                            <Link to={"/companies/" + company.id}>
                                {company.name}
                                <LinkOutlined/>
                                <br/>
                            </Link>
                        ))
                    }
                </> :
                <>
                    <i style={{color: "grey"}}>No companies</i>
                </>
        )
    },
    {
        title: 'Address',
        dataIndex: 'address',
        key: 'address',
    },
];

export function SellersPage() {
    document.title = "Sellers | E-Shop Pet"
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const pageParam = urlParams.get("page");
    const pageSizeParam = urlParams.get("pageSize");
    // const [sellers, setSellers] = useState(mockSellers);
    const [sellers, setSellers] = useState([]);
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(pageParam ? +pageParam : 1);
    const [pageSize, setPageSize] = useState(pageSizeParam ? +pageSizeParam : 10);
    const [total, setTotal] = useState(sellers.length);
    const fetchSellers = () => {
        setLoading(true);
        getSellers(page, pageSize)
            .then(sellerPage => {
                setLoading(false);
                setSellers(sellerPage.sellers);
                setPage(sellerPage.currentPage);
                setTotal(sellerPage.totalMatches);
            })
            /*.then(() => {
                setSellers(mockSellers);
                setPage(1);
                setPageSize(10);
                setTotal(mockSellers.length);
            })*/
            .catch(() => {
                setLoading(false);
                setSellers(mockSellers);
                setPage(1);
                setPageSize(10);
                setTotal(mockSellers.length);
            })
    }
    const onTablePaginationChange = (page, pageSize) => {
        setPage(page);
        setPageSize(pageSize);
    };
    useEffect(() => {
        fetchSellers();
    }, [page, pageSize]);
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
                    <h1>Sellers</h1>
                    <Link to={"/sellers/new"}>
                        <Button>Add seller</Button>
                    </Link>
                </Flex>
                <Table
                    dataSource={sellers}
                    columns={columns}
                    pagination={{
                        pageSize: pageSize,
                        total: total,
                        showSizeChanger: true,
                        onChange: onTablePaginationChange,
                        showTotal: (total) => "Sellers found: " + total,
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
