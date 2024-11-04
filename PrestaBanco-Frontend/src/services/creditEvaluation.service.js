import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/v1/creditEvaluation/get');
}

const create = (data) => {
    return httpClient.post("/api/v1/creditEvaluation/",data);
}

const get = (id) => {
    return httpClient.get(`/api/v1/creditEvaluation/get/${id}`);
}

const update = (data) => {
    return httpClient.put("/api/v1/creditEvaluation/",data);
}

const remove = (id) => {
    return httpClient.delete(`/api/v1/creditEvaluation/${id}`);
}

export default { getAll, create, get, getById, update, remove };

