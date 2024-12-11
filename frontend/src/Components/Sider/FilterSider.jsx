import {Flex, InputNumber, Slider, Tree} from "antd";
import {useEffect, useState} from "react";
import {getProducts} from "../../Services/ProductService";
import {getFilters} from "../../Services/FilterService";
import {secondaryBackgroundColor} from "../../colors";

const setupData = (value, setter, defaultValue) => {
    if (!value) {
        value = defaultValue;
    }
    setter(value);
}

const fromAmountToPercents = (amount, maxAmount) => {
    return Math.round(amount / maxAmount * 100)
}

const fromPercentsToAmount = (percents, maxAmount) => {
    return Math.round(percents / 100 * maxAmount)
}

const setupRange = (min, max, setMin, setMax, defaultValue = 0) => {
    if (min > max) {
        const temp = min;
        setMin(max);
        setMax(temp);
    } else {
        setupData(min, setMin, defaultValue);
        setupData(max, setMax, defaultValue);
    }
}

export function FilterSider({costMin, setCostMin, costMax, setCostMax, selectedFilters, setSelectedFilters}) {
    const [maxPrice, setMaxPrice] = useState(999_999);
    const [filters, setFilters] = useState([]);
    const [filterData, setFilterData] = useState({});
    const marks = {
        0: "0",
        100: maxPrice,
    }
    useEffect(() => {
        getProducts(1, 1, {
            deleted: [false],
            sortBy: "cost",
            sortDirection: "desc",
        })
            .then(productPage => {
                if (productPage && productPage.products && productPage.products.length !== 0) {
                    setMaxPrice(productPage.products[0].cost);
                    setCostMax(productPage.products[0].cost);
                }
            })
    }, []);
    useEffect(() => {
        getFilters()
            .then(filters => setFilters(filters))
    }, []);
    useEffect(() => {
        setFilterData(filters.map(category => {
            return {
                title: category.name,
                key: category.id,
                children: category.filters.map(filter => {
                    return {
                        title: (
                            <span>{filter.name} <span>({filter.productsCount})</span></span>
                        ),
                        key: category.id + "-" + filter.id,
                    }
                })
            }
        }));
    }, [filters]);
    const onFilterCheck = (checkedKeys) => {
        const newSelectedFilters = checkedKeys
            .filter(key => typeof key === "string")
            .map(key => +key.split("-")[1])
            .map(filterId => filters
                .flatMap(category => category.filters)
                .find(filter => filter.id === filterId));
        setSelectedFilters(newSelectedFilters);
    };
    return (
        <Flex style={{
            height: "100%",
            flexDirection: "column",
        }}>
            <h3>Filters</h3>
            <Flex style={{
                flexDirection: "column",
            }}>
                <h4>Price</h4>
                <table>
                    <tr>
                        <td>From:</td>
                        <td>
                            <InputNumber value={costMin} min={0}
                                         onChange={value => setupRange(value, costMax, setCostMin, setCostMax)}/>
                        </td>
                    </tr>
                    <tr>
                        <td>To:</td>
                        <td>
                            <InputNumber value={costMax} min={0}
                                         onChange={value => setupRange(costMin, value, setCostMin, setCostMax)}/>
                        </td>
                    </tr>
                </table>
                <Slider range marks={marks} tooltip={{
                    formatter: (value) => fromPercentsToAmount(value, maxPrice)
                }}
                        value={[fromAmountToPercents(costMin, maxPrice), fromAmountToPercents(costMax, maxPrice)]}
                        onChange={(values) => {
                            setupData(fromPercentsToAmount(values[0], maxPrice), setCostMin, 0);
                            setupData(fromPercentsToAmount(values[1], maxPrice), setCostMax, 0);
                        }}
                        style={{width: "80%"}}/>
                {
                    filterData.length ? (
                        <Tree checkable defaultExpandedKeys={filters.map(category => category.id)}
                              defaultCheckedKeys={selectedFilters.map(filter => filter.filterCategoryId + "-" + filter.id)}
                              onCheck={onFilterCheck}
                              treeData={filterData}
                              style={{backgroundColor: secondaryBackgroundColor}}
                        />
                    ) : (
                        <></>
                    )
                }
            </Flex>
        </Flex>
    )
}
