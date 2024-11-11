import {Link, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {Flex, Table} from "antd";
import {MainLayout} from "../Components/Layouts/MainLayout";
import {mockCompanies, mockSeller} from "../mock";
import {MailOutlined} from "@ant-design/icons";
import {getCompanies} from "../Services/CompanyService";
import {companyColumns} from "../table_columns";
import {getSeller} from "../Services/SellerService";
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../colors";
import {MenuButtons} from "../Components/Sider/MenuButtons";

export function SellerPage() {
    document.title = "Seller | E-Shop Pet";
    const {id} = useParams();
    const [seller, setSeller] = useState(mockSeller);
    const [companies, setCompanies] = useState([]);
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(1);
    const [pageSize, setPageSize] = useState(10);
    const [total, setTotal] = useState(companies.length);
    useEffect(() => {
        getSeller(id)
            .then(seller => {
                setSeller(seller);
                document.title = `${seller.name} ${seller.surname} | Seller | E-Shop Pet`;
            })
            .catch(() => setSeller(mockSeller))
    }, []);
    const fetchCompanies = () => {
        setLoading(true);
        getCompanies(page, pageSize, {
            sellerIds: [id],
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
                padding: "0 auto",
                overflowY: "auto",
                alignItems: "center",
                flexDirection: "column",
            }}>
                <Flex style={{
                    width: "100%",
                    flexDirection: "column",
                }}>
                    <h1>{seller.name} {seller.surname}</h1>
                    <p>Phone: {seller.phone}</p>
                    <p>Email: <Link to={"mailto:" + seller.email}>{seller.email} <MailOutlined/></Link></p>
                    <p>Address: {seller.address}</p>
                </Flex>
                <Flex style={{
                    width: "100%",
                }}>
                    <h1>Companies</h1>
                </Flex>
                <Table
                    dataSource={companies}
                    columns={companyColumns}
                    pagination={{
                        pageSize: pageSize,
                        total: total,
                        showSizeChanger: true,
                        onChange: onTablePaginationChange,
                        showTotal: (total) => "Companies: " + total,
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
