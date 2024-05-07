import axios from "axios";

axios.defaults.headers.post["Content-Type"] = "application/json";

const service = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 10000, // request timeout
    async: true,
    crossDomain: true,
});

// request interceptor请求拦截器
service.interceptors.request.use(
    (config) => {
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// 响应拦截器
service.interceptors.response.use(
    (response) => {
        return response.data;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default service;