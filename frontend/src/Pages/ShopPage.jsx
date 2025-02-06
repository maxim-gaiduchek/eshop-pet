import {MainLayout} from "../Components/Layouts/MainLayout";
import {useEffect, useState} from "react";
import {getProducts} from "../Services/ProductService";
import {ProductItem} from "../Components/Product/ProductItem";
import {Flex, Pagination, Select} from "antd"
import Sider from "antd/lib/layout/Sider";
import {secondaryBackgroundColor} from "../colors";
import {MenuButtons} from "../Components/Sider/MenuButtons";
import Search from "antd/lib/input/Search";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";
import {FilterSider} from "../Components/Sider/FilterSider";

const sorts = {
    "newest": {
        sortBy: "createdAt",
        sortDirection: "desc",
    },
    "oldest": {
        sortBy: "createdAt",
        sortDirection: "asc",
    },
    "cheapest": {
        sortBy: "cost",
        sortDirection: "asc",
    },
    "expensive": {
        sortBy: "cost",
        sortDirection: "desc",
    },
}

export function ShopPage() {
    document.title = "Products | E-Shop Pet";
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const sortByParam = urlParams.get("sortBy");
    const sortDirectionParam = urlParams.get("sortDirection");
    const nameParam = urlParams.get("name");
    const costMinParam = urlParams.get("costMin");
    const costMaxParam = urlParams.get("costMax");
    const selectedFiltersParam = urlParams.get("selectedFilters");
    const pageParam = urlParams.get("page");
    const pageSizeParam = urlParams.get("pageSize");
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(pageParam ? +pageParam : 1);
    const [pageSize, setPageSize] = useState(pageSizeParam ? +pageSizeParam : 10);
    const [total, setTotal] = useState(products.length);
    const [sortBy, setSortBy] = useState(sortByParam ? sortByParam : "createdAt");
    const [sortDirection, setSortDirection] = useState(sortDirectionParam ? sortDirectionParam : "desc");
    const [name, setName] = useState(nameParam ? nameParam : "");
    const [costMin, setCostMin] = useState(costMinParam ? +costMinParam : 0);
    const [costMax, setCostMax] = useState(costMaxParam ? +costMaxParam : 999_999);
    const [selectedFilters, setSelectedFilters] = useState(selectedFiltersParam ? selectedFiltersParam
        .split(",").map(Number) : [])
    const getSort = () => {
        let sort = Object.entries(sorts)
            .filter(([_, sort]) => sort.sortBy === sortBy && sort.sortDirection === sortDirection);
        return sort.length > 0 ? sort[0][0] : "newest"
    };
    const setSort = (value) => {
        const sort = sorts[value];
        setSortBy(sort.sortBy);
        setSortDirection(sort.sortDirection);
    };
    const onTablePaginationChange = (page, pageSize) => {
        setPage(page);
        setPageSize(pageSize);
    };
    useEffect(() => {
        changePath(name, costMin, costMax, selectedFilters, sortBy, sortDirection, page, pageSize);
        getProducts(page, pageSize, {
            name: name,
            deleted: [false],
            costMin: costMin,
            costMax: costMax,
            filterIds: selectedFilters,
            sortBy: sortBy,
            sortDirection: sortDirection,
        })
            .then(productPage => {
                setProducts(productPage.products);
                setPage(productPage.currentPage);
                setTotal(productPage.totalMatches);
            })
            .catch(() => {
                setProducts([]);
                setPage(1);
            })
    }, [name, costMin, costMax, selectedFilters, sortBy, sortDirection, page, pageSize]);
    return (
        <MainLayout>
            <Sider style={{
                maxHeight: "100%",
                backgroundColor: secondaryBackgroundColor,
                overflowX: "hidden",
                overflowY: "auto",
                padding: "10px 10px",
            }}>
                <MenuButtons/>
                <FilterSider costMin={costMin} setCostMin={setCostMin} costMax={costMax} setCostMax={setCostMax}
                             selectedFilters={selectedFilters} setSelectedFilters={setSelectedFilters}
                             toSetCostMax={!costMaxParam}/>
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
            }}>
                <Flex style={{
                    width: "100%",
                    alignItems: "center",
                    justifyContent: "space-between",
                    flexWrap: "nowrap",
                }}>
                    <h1 style={{width: "100%"}}>Search</h1>
                    <Flex style={{
                        alignItems: "center",
                        justifyContent: "space-between",
                        flexWrap: "nowrap",
                        width: "50%"
                    }}>
                        <Select placeholder={"Newest"} value={getSort()} onSelect={value => setSort(value)}
                                options={[
                                    {label: "Newest", value: "newest"},
                                    {label: "Oldest", value: "oldest"},
                                    {label: "Cheapest", value: "cheapest"},
                                    {label: "Expensive", value: "expensive"},
                                ]} style={{width: "50%", margin: "10px 10px"}}/>
                        <Search placeholder={"Search name..."} value={name} allowClear={true}
                                onChange={e => setName(e.target.value)}
                                style={{width: "50%", margin: "10px 10px"}}/>
                    </Flex>
                </Flex>
                {
                    products && products.length !== 0 ?
                        products.map((product) => (
                            <ProductItem product={product}/>
                        )) :
                        <CenteredLayout>
                            <p style={{margin: "0 auto", fontSize: "16pt"}}>No items found</p>
                        </CenteredLayout>
                }
                <Pagination pageSize={pageSize} total={total} showSizeChanger={true}
                            showTotal={(total) => "Items found: " + total}
                            onChange={onTablePaginationChange}
                            align={"end"} style={{
                    width: "100%",
                    margin: "10px 10px",
                }}/>
            </Flex>
        </MainLayout>
    )
}

function changePath(name, costMin, costMax, selectedFilters, sortBy, sortDirection, page, pageSize) {
    let params = new URLSearchParams();
    if (name) {
        params.set("name", name);
    }
    if (costMin) {
        params.set("costMin", costMin);
    }
    if (costMax) {
        params.set("costMax", costMax);
    }
    if (selectedFilters.length > 0) {
        params.set("selectedFilters", selectedFilters.join(","));
    }
    if (sortBy) {
        params.set("sortBy", sortBy);
    }
    if (sortDirection) {
        params.set("sortDirection", sortDirection);
    }
    if (page) {
        params.set("page", page);
    }
    if (pageSize) {
        params.set("pageSize", pageSize);
    }
    const newUrl = `/shop?${params.toString()}`;
    window.history.replaceState(null, "", newUrl);
}
