import {securityUrl} from "../config";
import {getRequest, postRequest} from "./RequestService";
import {sha256} from "js-sha256";

export async function login(login, password) {
    return postRequest(securityUrl + "/login", {
        login: login,
        password: sha256(password)
    })
        .then(credentials => {
            console.log(credentials)
            localStorage.setItem("loginUserId", credentials.userId);
            localStorage.setItem("loginAccessToken", credentials.accessToken);
            localStorage.setItem("loginTokenType", credentials.type);
        })
}

export async function logout() {
    return getRequest(securityUrl + "/logout", false)
        .then(() => {
            localStorage.removeItem("loginUserId");
            localStorage.removeItem("loginAccessToken");
            localStorage.removeItem("loginTokenType");
        })
}
