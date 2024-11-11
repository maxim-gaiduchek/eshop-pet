import {Link, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {Flex, Table} from "antd";
import {MainLayout} from "../Components/Layouts/MainLayout";
import {mockSeller} from "../mock";
import {MailOutlined} from "@ant-design/icons";
import {companyColumns} from "../table_columns";
import {getUser} from "../Services/UserService";
import {MenuButtons} from "../Components/Sider/MenuButtons";
import {secondaryBackgroundColor} from "../colors";
import Sider from "antd/lib/layout/Sider";

export function CustomerPage() {
    document.title = "User Account | E-Shop Pet";
    const {id} = useParams();
    const [customer, setCustomer] = useState(mockSeller);
    const navigate = useNavigate();
    const [purchases, setPurchases] = useState([]);
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(1);
    const [pageSize, setPageSize] = useState(10);
    const [total, setTotal] = useState(purchases.length);
    useEffect(() => {
        getUser(id)
            .then(customer => {
                if (customer.role === "ROLE_SELLER") {
                    navigate("/sellers/" + customer.id)
                }
                setCustomer(customer);
                document.title = `${customer.name} ${customer.surname} | User Account | E-Shop Pet`;
            })
            .catch(() => setCustomer(mockSeller))
    }, []);
    const fetchPurchases = () => {
        /*setLoading(true);
        getCompanies(page, pageSize, {
            sellerIds: [id],
        })
            .then(companyPage => {
                setLoading(false);
                setPurchases(companyPage.companies);
                setPage(companyPage.currentPage);
                setTotal(companyPage.totalMatches);
            })
            .catch(() => {
                setLoading(false);
                setPurchases(mockCompanies);
                setPage(1);
            })*/
    }
    const onTablePaginationChange = (page, pageSize) => {
        setPage(page);
        setPageSize(pageSize);
    };
    useEffect(() => {
        fetchPurchases();
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
                    flexDirection: "column",
                }}>
                    <h1>{customer.name} {customer.surname}</h1>
                    <p>Phone: {customer.phone}</p>
                    <p>Email: <Link to={"mailto:" + customer.email}>{customer.email} <MailOutlined/></Link></p>
                </Flex>
                <Flex style={{
                    width: "100%",
                }}>
                    <h1>Purchases</h1>
                </Flex>
                <Table
                    dataSource={[]}
                    columns={companyColumns}
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
                        overflowY: "auto",
                    }}
                />
            </Flex>
        </MainLayout>
    )
}
