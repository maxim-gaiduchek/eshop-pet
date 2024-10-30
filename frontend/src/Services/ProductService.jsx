import {getRequest} from "./RequestService";
import {apiUrl} from "../config";

export async function getProduct(id) {
    return getRequest(apiUrl + "/products/" + id)
}

export async function getProducts() {
    return getRequest(apiUrl + "/products")
}
