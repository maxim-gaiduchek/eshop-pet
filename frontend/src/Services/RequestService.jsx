export async function getRequest(url) {
    return fetch(url, {
        method: "GET",
    })
}

export async function postRequest(url, body) {
    return fetch(url, {
        method: "POST",
        body: JSON.stringify(body),
        headers: {
            "Content-Type": "application/json"
        }
    })
}
