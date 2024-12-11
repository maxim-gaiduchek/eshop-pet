import {MainLayout} from "../../../Components/Layouts/MainLayout";
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../../../colors";
import {MenuButtons} from "../../../Components/Sider/MenuButtons";
import {Button, Flex, Table} from "antd";
import {filterCategoryColumns} from "../../../table_columns";
import {useEffect, useState} from "react";
import {deleteFilterCategory, getFilterCategories} from "../../../Services/FilterCategoryService";
import {Link} from "react-router-dom";
import {DeleteOutlined} from "@ant-design/icons";

export function FilterCategoriesPage() {
    document.title = "Settings | Filters | E-Shop Pet";
    const [filterCategories, setFilterCategories] = useState([]);
    const [loading, setLoading] = useState(false);
    useEffect(() => {
        setLoading(true);
        getFilterCategories()
            .then(filterCategories => {
                setFilterCategories(filterCategories);
                setLoading(false);
            })
            .catch(() => {
                setFilterCategories([]);
                setLoading(false);
            })
    }, []);
    const deleteCategory = (id) => {
        deleteFilterCategory(id)
            .then(() => {
                const newFilterCategories = filterCategories.map(filterCategory => {
                    if (filterCategory.id === id) {
                        filterCategory.deleted = true;
                    }
                    return filterCategory;
                })
                setFilterCategories(newFilterCategories);
            })
    }
    const columns = filterCategoryColumns.concat({
        render: (_, {id, deleted}) => (
            <Button color="danger" variant="solid" disabled={deleted}
                    onClick={() => deleteCategory(id)}>
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
                    <h1>Filter Categories</h1>
                    <Link to={"/settings/filter-categories/new"}>
                        <Button>Add filter category</Button>
                    </Link>
                </Flex>
                <Table
                    dataSource={filterCategories}
                    columns={columns}
                    pagination={{
                        showSizeChanger: true,
                        showTotal: (total) => "Filter categories found: " + total,
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
