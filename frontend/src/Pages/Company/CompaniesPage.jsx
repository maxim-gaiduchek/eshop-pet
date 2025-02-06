import {MainLayout} from "../../Components/Layouts/MainLayout";
import {Button, Flex, Table} from "antd";
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {getCompanies} from "../../Services/CompanyService";
import {mockCompanies} from "../../mock";
import {companyColumns} from "../../table_columns";
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../../colors";
import {MenuButtons} from "../../Components/Sider/MenuButtons";

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
    const userId = localStorage.getItem("loginUserId");
    const role = localStorage.getItem("loginUserRole");
    const sellerIds = role && role === "ROLE_SELLER" ? [userId] : []
    const fetchCompanies = () => {
        setLoading(true);
        getCompanies(page, pageSize, {
            sellerIds: sellerIds,
        })
            .then(companyPage => {
                setLoading(false);
                setCompanies(companyPage.companies);
                setPage(companyPage.currentPage);
                setTotal(companyPage.totalMatches);
            })
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
                    columns={companyColumns}
                    pagination={{
                        pageSize: pageSize,
                        total: total,
                        showSizeChanger: true,
                        onChange: onTablePaginationChange,
                        showTotal: (total) => "Companies found: " + total,
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
