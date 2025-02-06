import {deleteRequest, getRequest, postRequest} from "./RequestService";
import {apiUrl} from "../config";

export async function getFilters() {
    return getRequest(apiUrl + "/filters");
}

export async function createFilter(name, filterCategoryId) {
    return postRequest(apiUrl + "/filters", {
        name: name,
        filterCategoryId: filterCategoryId
    })
}

export async function deleteFilter(id) {
    return deleteRequest(apiUrl + "/filters/" + id, false)
}
