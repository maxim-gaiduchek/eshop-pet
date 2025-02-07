import {deleteRequest, getRequest, postRequestFormData, putRequestFormData} from "./RequestService";
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

export async function createProduct(name, description, cost, count, companyId, imageFile) {
    const formData = new FormData();
    formData.append("product", new Blob([JSON.stringify({
        name: name,
        description: description,
        cost: cost,
        count: count,
        company: {
            id: companyId,
        }
    })], {type: "application/json"}));
    formData.append("productImageFile", imageFile);
    return postRequestFormData(apiUrl + "/products", formData);
}

export async function updateProduct(id, description, cost, count, filterIds, imageFile) {
    const formData = new FormData();
    formData.append("product", new Blob([JSON.stringify({
        description: description,
        cost: cost,
        count: count,
        filters: filterIds.map(filterId => {
            return {id: filterId}
        }),
    })], {type: "application/json"}));
    if (imageFile) {
        formData.append("productImageFile", imageFile);
    }
    return putRequestFormData(apiUrl + "/products/" + id, formData);
}

export async function deleteProduct(id) {
    return deleteRequest(apiUrl + "/products/" + id, false);
}
