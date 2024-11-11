import {getRequest, postRequest} from "./RequestService";
import {apiUrl} from "../config";
import {sha256} from "js-sha256";

export async function getSeller(id) {
    return getRequest(apiUrl + "/sellers/" + id)
}

export async function getSellers(page = 1, pageSize = 10) {
    return getRequest(apiUrl + "/sellers", true, {
        page: page,
        pageSize: pageSize
    })
}

export async function createSeller(name, surname, email, phone, address, password) {
    return postRequest(apiUrl + "/sellers", {
        name: name,
        surname: surname,
        email: email,
        phone: phone,
        address: address,
        password: sha256(password)
    })
}
