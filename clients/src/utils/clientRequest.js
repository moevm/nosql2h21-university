import {getCookie} from "./cookies";

const clientRequest = (
    path='',
    method='GET',
    body= null,
    headers={

    },
    url= `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}/api`,
) => {
    const request = {
        method: method,
        headers: {
            ...headers,
            'authorization': `${getCookie("token")}`
        }
    }

    if (method !== 'GET' && method !== 'DELETE' && body.constructor.name !== 'FormData') {
        request.body = JSON.stringify(body)
        request.headers = {
            ...headers,
            'Content-Type': 'application/json'
        }
    } else if (body && body.constructor && body.constructor.name === 'FormData') {
        request.body = body
    }

    return fetch(url + path, request)
}

export default clientRequest