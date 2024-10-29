function handleError(response) {
    return response.json()
        .then(json => {
            console.error(`Error with code ${json.code}: ${json.description}`);
            throw new Error(`Error with code ${json.code}: ${json.description}`);
        })
}

export async function getRequest(url) {
    return fetch(url, {
        method: "GET",
    })
        .then(response => {
            if (!response.ok) {
                return handleError(response);
            }
            return response.json();
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
        .then(response => {
            if (!response.ok) {
                return handleError(response);
            }
            return response.json();
        })
}
