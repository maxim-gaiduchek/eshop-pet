import {MainLayout} from "../Components/Layouts/MainLayout";
import {Button, Flex, Table} from "antd";
import {Link} from "react-router-dom";
import {LinkOutlined} from "@ant-design/icons";
import {useEffect, useState} from "react";
import {AdministratorSider} from "../Components/Administrator/AdministratorSider";
import {getCompanies} from "../Services/CompanyService";
import {mockCompanies} from "../mock";

const columns = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
        render: (id) => (
            <Link to={"/companies/" + id}>
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
        title: 'Seller',
        dataIndex: 'seller',
        key: 'seller',
        render: (seller) => (
            <Link to={"/sellers/" + seller.id}>
                {seller.name} {seller.surname}
                <LinkOutlined/>
            </Link>
        ),
    },
];

export function CompaniesPage() {
    document.title = "Companies | E-Shop Pet"
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const pageParam = urlParams.get("page");
    const pageSizeParam = urlParams.get("pageSize");
    // const [companies, setCompanies] = useState(mockCompanies);
    const [companies, setCompanies] = useState([]);
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(pageParam ? +pageParam : 1);
    const [pageSize, setPageSize] = useState(pageSizeParam ? +pageSizeParam : 10);
    const [total, setTotal] = useState(companies.length);
    const fetchCompanies = () => {
        setLoading(true);
        getCompanies(page, pageSize)
            .then(companyPage => {
                setLoading(false);
                setCompanies(companyPage.companies);
                setPage(companyPage.currentPage);
                setTotal(companyPage.totalMatches);
            })
            /*.then(() => {
                setCompanies(mockCompanies);
                setPage(1);
                setPageSize(10);
                setTotal(mockCompanies.length);
            })*/
            .catch(() => {
                setLoading(false);
                setCompanies(mockCompanies);
                setPage(1);
            })
    }
    const onTablePaginationChange = (page, pageSize) => {
        setPage(page);
        setPageSize(pageSize);
    };
    useEffect(() => {
        fetchCompanies();
    }, [page, pageSize]);
    return (
        <MainLayout>
            <AdministratorSider/>
            <Flex style={{
                height: "100%",
                width: "90%",
                maxWidth: 1200,
                margin: "0 auto",
                justifyContent: "flex-start",
                flexWrap: "wrap",
                padding: "0 auto",
                overflowY: "auto",
                alignItems: "center",
                flexDirection: "column"
            }}>
                <Flex style={{
                    width: "100%",
                    alignItems: "center",
                    justifyContent: "space-between",
                }}>
                    <h1>Companies</h1>
                    <Link to={"/companies/new"}>
                        <Button>Add company</Button>
                    </Link>
                </Flex>
                <Table
                    dataSource={companies}
                    columns={columns}
                    pagination={{
                        pageSize: pageSize,
                        total: total,
                        showSizeChanger: true,
                        onChange: onTablePaginationChange,
                    }}
                    loading={loading}
                    style={{
                        width: "100%",
                        overflowX: "auto",
                    }}
                />
            </Flex>
        </MainLayout>
    )
}
