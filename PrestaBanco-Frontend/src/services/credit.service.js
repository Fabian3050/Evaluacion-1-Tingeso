import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/v1/credit/get');
}

const create = (id, data) => {
    return httpClient.post(`/api/v1/credit/${id}`, data);
}

// Simplificado el método `get` para que solo acepte `id`
const get = (id) => {
    return httpClient.get(`/api/v1/credit/${id}`);
}

const getById = (id) => {
    return httpClient.get(`/api/v1/credit/get/${id}`);
}

const remove = (id) => {
    return httpClient.delete(`/api/v1/credit/${id}`);
}

export default { getAll, create, get, getById, remove };

