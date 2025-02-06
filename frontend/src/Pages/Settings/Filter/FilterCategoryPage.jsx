import {MainLayout} from "../../../Components/Layouts/MainLayout";
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../../../colors";
import {MenuButtons} from "../../../Components/Sider/MenuButtons";
import {Button, Flex, Table} from "antd";
import {filterColumns} from "../../../table_columns";
import {useEffect, useState} from "react";
import {getFilterCategory} from "../../../Services/FilterCategoryService";
import {Link, useParams} from "react-router-dom";
import {DeleteOutlined} from "@ant-design/icons";
import {deleteFilter} from "../../../Services/FilterService";

const defaultFilterCategory = {
    name: ""
}

export function FilterCategoryPage() {
    document.title = "Filter Category | Settings | E-Shop Pet";
    const {id} = useParams();
    const [filterCategory, setFilterCategory] = useState(defaultFilterCategory);
    const [filters, setFilters] = useState([]);
    const [loading, setLoading] = useState(false);
    useEffect(() => {
        setLoading(true);
        getFilterCategory(id)
            .then(filterCategory => {
                document.title = `"${filterCategory.name} | Filter Category | Settings | E-Shop Pet";`
                setFilterCategory(filterCategory);
                setFilters(filterCategory.filters)
                setLoading(false);
            })
            .catch(() => {
                document.title = "Filter Category | Settings | E-Shop Pet";
                setLoading(false);
            })
    }, []);
    const deleteCategoryFilter = (id) => {
        deleteFilter(id)
            .then(() => {
                let newFilters = filters.map(filter => {
                    if (filter.id === id) {
                        filter.deleted = true;
                    }
                    return filter
                });
                setFilters(newFilters);
            })
    }
    const columns = filterColumns.concat({
        render: (_, {id, deleted}) => (
            <Button color="danger" variant="solid" disabled={deleted}
                    onClick={() => deleteCategoryFilter(id)}>
                <DeleteOutlined/>
            </Button>
        )
    });
    return (
        <MainLayout>
            <Sider style={{
                height: "100%",
                backgroundColor: secondaryBackgroundColor,
                overflowY: "hidden",
                padding: "10px 10px",
            }}>
                <MenuButtons/>
            </Sider>
            <Flex style={{
                padding: "20px",
                flexDirection: "column",
                height: "100%",
                width: "90%",
                maxWidth: 1200,
                margin: "0 auto",
                justifyContent: "flex-start",
                flexWrap: "wrap",
                overflowY: "auto",
            }}>
                <Flex style={{
                    width: "100%",
                    alignItems: "center",
                    justifyContent: "space-between",
                }}>
                    <h1>{filterCategory.name} Filter Category | Filters</h1>
                    <Link to={`/settings/filter-categories/${filterCategory.id}/filters/new`}>
                        <Button>Add filter</Button>
                    </Link>
                </Flex>
                <Table
                    dataSource={filters}
                    columns={columns}
                    pagination={{
                        showSizeChanger: true,
                        showTotal: (total) => "Filters found: " + total,
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
