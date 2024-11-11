import {getRequest, postRequest} from "./RequestService";
import {apiUrl} from "../config";

export async function getCompany(id) {
    return getRequest(apiUrl + "/companies/" + id)
}

export async function getCompanies(page = 1, pageSize = 10) {
    return getRequest(apiUrl + "/companies", true, {
        page: page,
        pageSize: pageSize
    })
}

export async function createCompany(name) {
    return postRequest(apiUrl + "/companies", {
        name: name,
    })
}
