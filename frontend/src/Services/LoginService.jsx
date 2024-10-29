import {securityUrl} from "../config";
import {postRequest} from "./RequestService";
import {sha256} from "js-sha256";

export async function login(login, password) {
    return postRequest(securityUrl + "/security/login", {
        login: login,
        password: sha256(password)
    })
}
