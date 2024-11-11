import {getRequest, postRequest} from "./RequestService";
import {apiUrl} from "../config";
import {sha256} from "js-sha256";

export async function getCustomer(id) {
    return getRequest(apiUrl + "/customers/" + id);
}

export async function createCustomer(name, surname, email, phone, address, password) {
    return postRequest(apiUrl + "/customers", {
        name: name,
        surname: surname,
        email: email,
        phone: phone,
        address: address,
        password: sha256(password)
    })
}
