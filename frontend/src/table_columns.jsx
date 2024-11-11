import {Link} from "react-router-dom";
import {LinkOutlined} from "@ant-design/icons";

export const companyColumns = [
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
