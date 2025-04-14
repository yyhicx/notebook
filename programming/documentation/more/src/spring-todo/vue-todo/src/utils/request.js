import axios from 'axios';
import router from '@/router';

// 创建 Axios 实例，设置一些基础属性
const request = axios.create({
  baseURL: 'http://localhost:8080/',
  timeout: 1000,
});

// 添加请求拦截器
axios.interceptors.request.use(
  (config) => {
    // 可以前置设置，例如请求头 token 等
    config.header.head1 = 'data-test';
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 添加响应拦截器
axios.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response.status === 404) {
      router.push('/404');
    } else {
      return Promise.reject(error);
    }
  }
);

export default request;
