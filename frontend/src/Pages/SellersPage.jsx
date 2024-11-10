import {MainLayout} from "../Components/Layouts/MainLayout";
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../colors";
import {Button, Flex, Table} from "antd";
import {Link} from "react-router-dom";
import {LinkOutlined, MailOutlined} from "@ant-design/icons";
import {useState} from "react";

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
        title: 'Middle Name',
        dataIndex: 'middleName',
        key: 'middleName',
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
        title: 'Address',
        dataIndex: 'address',
        key: 'address',
    },
];

const defaultSellers = [
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
    {
        id: 1,
        name: "Test",
        surname: "Test",
        middleName: "Test",
        phone: "+420607777777",
        email: "test@gmail.com",
        address: "Test str."
    },
]

export function SellersPage() {
    document.title = "Administrator | E-Shop Pet"
    const [loading, setLoading] = useState(false);
    const [tableParams, setTableParams] = useState({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });
    return (
        <MainLayout>
            <Sider style={{
                height: "100%",
                backgroundColor: secondaryBackgroundColor,
                overflowY: "hidden",
            }}>
                <Link to={"/sellers"}>
                    <Button>Sellers</Button>
                </Link>
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
                    <h1>Sellers</h1>
                    <Button>Add seller</Button>
                </Flex>
                <Table
                    dataSource={defaultSellers}
                    columns={columns}
                    pagination={tableParams.pagination}
                    loading={loading}
                    style={{
                        width: "100%",
                    }}/>
            </Flex>
        </MainLayout>
    )
}
