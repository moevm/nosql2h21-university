import {getCookie} from "./cookies";

const clientRequest = (
    path='',
    method='GET',
    body= null,
    headers={
        'Accept': '*/*',
        'Content-Type': 'application/json',
    },
    url= 'http://localhost:8080/api',
) => {
    const request = {
        method: method,
        headers: {
            ...headers,
            'authorization': `${getCookie("token")}`
        }
    }

    if (method !== 'GET' && method !== 'DELETE') {
        request.body = JSON.stringify(body)
    }

    return fetch(url + path, request)
}

export default clientRequest