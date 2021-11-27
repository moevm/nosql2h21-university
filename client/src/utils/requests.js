import clientRequest from "./clientRequest";

const getStudentsByFilterRequest = async filter => {
    return clientRequest('/enrollees/filter', 'POST', filter)
}

const getAllStudentsRequest = async () => {
    return clientRequest('/enrollees')
}

const getStudentsForUniversityRequest = async universityId => {
    return clientRequest(`/enrollees/by-university/${universityId}`)
}

const getStudentsForUniversityByFilterRequest = async (universityId, filter) => {
    return clientRequest(`/enrollees/by-university/${universityId}/filter`, 'POST', filter)
}

const loginRequest = (loginInfo) => {
    return clientRequest('/auth/signin', 'POST', loginInfo)
}

const filterUniversitiesRequest = (filter) => {
    return clientRequest('/universities/filter', 'POST', filter)
}

const getUniversitiesRequest = () => {
    return clientRequest('/universities')
}

const importFileRequest = (file) => {
    const body = new FormData()
    body.append("file", file, 'file')
    return clientRequest('/employees/import', 'POST', body)
}

const registerStudentRequest = (registerInfo) => {
    return clientRequest('/enrollees/', 'POST', registerInfo)
}

export {getStudentsByFilterRequest, getAllStudentsRequest, getStudentsForUniversityRequest,
    getStudentsForUniversityByFilterRequest, loginRequest, filterUniversitiesRequest, getUniversitiesRequest, importFileRequest,
    registerStudentRequest
}