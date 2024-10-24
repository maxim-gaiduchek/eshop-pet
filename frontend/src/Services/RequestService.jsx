export function postRequest(url, body) {
    return fetch(url, {
        method: "POST",
        body: body
    })
}
