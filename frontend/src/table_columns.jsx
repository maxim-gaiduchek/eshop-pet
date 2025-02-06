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

export const filterCategoryColumns = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
        render: (id) => (
            <Link to={"/settings/filter-categories/" + id}>
                {id}
                <LinkOutlined/>
            </Link>
        ),
        showSorterTooltip: {
            target: 'full-header',
        },
        sorter: (a, b) => a.id - b.id,
        defaultSortOrder: 'ascend',
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
        showSorterTooltip: {
            target: 'full-header',
        },
        sorter: (a, b) => a.name.localeCompare(b.name),
    },
    {
        title: 'Created by',
        dataIndex: 'responsible',
        key: 'responsible',
        render: (responsible) => responsible.name + " " + responsible.surname,
        sorter: (a, b) => {
            const cmp = a.responsible.name.localeCompare(b.responsible.name)
            if (cmp !== 0) {
                return cmp
            }
            return a.responsible.surname.localeCompare(b.responsible.surname)
        },
    },
    {
        title: 'Filters',
        dataIndex: 'filters',
        key: 'filters',
        render: (_, {filters}) => (filters && filters.length !== 0 ?
                <>
                    {
                        filters.map(company => company.name).join(", ")
                    }
                </> :
                <>
                    <i style={{color: "grey"}}>No filters</i>
                </>
        ),
    },
    {
        title: 'Deleted',
        dataIndex: 'deleted',
        key: 'deleted',
        showSorterTooltip: {
            target: 'full-header',
        },
        render: (deleted) => deleted ? "true" : "false",
        sorter: (a, b) => a.deleted && b.deleted ? 1 : -1,
    },
];

export const filterColumns = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
        showSorterTooltip: {
            target: 'full-header',
        },
        sorter: (a, b) => a.id - b.id,
        defaultSortOrder: 'ascend',
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
        showSorterTooltip: {
            target: 'full-header',
        },
        sorter: (a, b) => a.name.localeCompare(b.name),
    },
    {
        title: 'Deleted',
        dataIndex: 'deleted',
        key: 'deleted',
        showSorterTooltip: {
            target: 'full-header',
        },
        render: (deleted) => deleted ? "true" : "false",
        sorter: (a, b) => a.deleted && b.deleted ? 1 : -1,
    },
];
