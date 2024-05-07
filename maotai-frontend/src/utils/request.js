import axios from "axios";

axios.defaults.headers.post["Content-Type"] = "application/json";

const service = axios.create({
    baseURL: "https://mock.apifox.com/m1/4446580-4092419-default",
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