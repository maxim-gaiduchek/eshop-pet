import {deleteRequest, getRequest, postRequest} from "./RequestService";
import {apiUrl} from "../config";

export async function getFilterCategory(id) {
    return getRequest(apiUrl + "/filter-categories/" + id);
}

export async function getFilterCategories() {
    return getRequest(apiUrl + "/filter-categories");
}

export async function createFilterCategory(name) {
    return postRequest(apiUrl + "/filter-categories", {
        name: name
    })
}

export async function deleteFilterCategory(id) {
    return deleteRequest(apiUrl + "/filter-categories/" + id, false)
}
