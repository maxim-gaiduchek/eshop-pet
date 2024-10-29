import {getRequest} from "./RequestService";
import {apiUrl} from "../config";

export async function getProducts() {
    return getRequest(apiUrl + "/products")
}
