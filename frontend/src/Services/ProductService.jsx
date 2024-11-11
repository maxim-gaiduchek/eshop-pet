import {deleteRequest, getRequest, postRequest} from "./RequestService";
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

export async function createProducts(name, description, cost, count, companyId) {
    return postRequest(apiUrl + "/products", {
        name: name,
        description: description,
        cost: cost,
        count: count,
        company: {
            id: companyId,
        },
    });
}

export async function deleteProduct(id) {
    return deleteRequest(apiUrl + "/products/" + id, false);
}
