import {deleteRequest, getRequest, postRequest, putRequest} from "./RequestService";
import {apiUrl} from "../config";

export async function getProduct(id) {
    return getRequest(apiUrl + "/products/" + id);
}

export async function getProducts(page = 1, pageSize = 10, queryParams = {}) {
    return getRequest(apiUrl + "/products", true, {
        ...queryParams,
        page: page,
        pageSize: pageSize,
    });
}

export async function createProduct(name, description, cost, count, companyId) {
    return postRequest(apiUrl + "/products", {
        name: name,
        description: description,
        cost: cost,
        count: count,
        company: {
            id: companyId,
        }
    });
}

export async function updateProduct(id, description, cost, count, filterIds) {
    return putRequest(apiUrl + "/products/" + id, {
        description: description,
        cost: cost,
        count: count,
        filters: filterIds.map(filterId => {
            return {id: filterId}
        }),
    });
}

export async function deleteProduct(id) {
    return deleteRequest(apiUrl + "/products/" + id, false);
}
