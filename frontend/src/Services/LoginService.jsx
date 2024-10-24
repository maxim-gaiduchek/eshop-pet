import {backendUrl} from "../config";
import {postRequest} from "./RequestService";
import {sha256} from "js-sha256";

export function login(login, password) {
    return postRequest(backendUrl + "security/login", {
        login: login,
        password: sha256(password)
    })
}
