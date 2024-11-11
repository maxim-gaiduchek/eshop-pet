import {toast} from "react-toastify";
import {securityUrl} from "../config";

function handleError(response) {
    return response.json()
        .then(json => {
            throwError(json)
        })
}

function throwError(json) {
    console.error(`Error with code ${json.code}: ${json.description}`);
    toast.error(`Error with code ${json.code}: ${json.description}`);
    throw new Error(`Error with code ${json.code}: ${json.description}`);
}

function handleTokenExpiration() {
    return fetch(securityUrl + "/refresh", {
        method: "GET",
        credentials: "include",
    })
        .then(refreshResponse => {
            console.log(refreshResponse);
            if (refreshResponse.ok) {
                return refreshResponse.json();
            }
            localStorage.removeItem("loginUserId");
            localStorage.removeItem("loginUserRole");
            localStorage.removeItem("loginAccessToken");
            localStorage.removeItem("loginTokenType");
            return handleError(refreshResponse);
        })
        .then(credentials => {
            localStorage.getItem("loginUserId", credentials.userId);
            localStorage.getItem("loginUserRole", credentials.role);
            localStorage.getItem("loginAccessToken", credentials.accessToken);
            localStorage.setItem("loginTokenType", credentials.type);
        })
}

function buildHeaders() {
    const headers = new Map();
    const tokenType = localStorage.getItem("loginTokenType");
    const accessToken = localStorage.getItem("loginAccessToken");
    if (accessToken) {
        headers.set("Authorization", tokenType + " " + accessToken);
        // headers.set("Cookies", document.cookies);
    }
    return headers;
}

function handleResponse(response, jsonResponse, onTokenExpiration) {
    if (response.status === 403) {
        return handleTokenExpiration()
            .then(onTokenExpiration)
    }
    if (!response.ok) {
        return handleError(response);
    }
    if (jsonResponse) {
        return response.json();
    }
}

export async function getRequest(url, jsonResponse = true, queryParams = {}) {
    return fetch(url + buildQueryParams(queryParams), {
        method: "GET",
        credentials: "include",
        headers: buildHeaders(),
    })
        .then(response => {
            return handleResponse(response, jsonResponse, () => getRequest(url))
        })
}

function buildQueryParams(queryParams) {
    if (!queryParams) {
        return "";
    }
    const entries = Object.entries(queryParams);
    if (!entries || entries.length === 0) {
        return "";
    }
    const urlParams = new URLSearchParams();
    for (const [key, value] of entries) {
        urlParams.set(key, value);
    }
    return "?" + urlParams.toString();
}

export async function postRequest(url, body, jsonResponse = true) {
    const headers = buildHeaders();
    headers.set("Content-Type", "application/json");
    return fetch(url, {
        method: "POST",
        body: JSON.stringify(body),
        credentials: "include",
        headers: headers,
    })
        .then(response => {
            return handleResponse(response, jsonResponse, () => postRequest(url, body))
        })
}
