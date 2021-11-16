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

export {getStudentsByFilterRequest, getAllStudentsRequest, getStudentsForUniversityRequest, getStudentsForUniversityByFilterRequest}