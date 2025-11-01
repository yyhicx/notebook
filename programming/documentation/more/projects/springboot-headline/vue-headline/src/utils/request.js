import axios from 'axios';
import { ElMessage } from 'element-plus';
import { cloneDeep } from 'lodash';
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';
import { useUserInfoStore } from '@/stores/userInfo';

const service = axios.create({
  baseURL: import.meta.env.VITE_BASE_API,
  timeout: 10000,
});

// 添加请求拦截器
service.interceptors.request.use(
  (config) => {
    NProgress.start(); // 开启进度条
    const userInfoStore = useUserInfoStore();
    const userInfo = cloneDeep(userInfoStore.userInfo);
    const token = userInfo.token;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 添加响应拦截器
service.interceptors.response.use(
  (response) => {
    NProgress.done(); // 关闭进度条

    if (response.data.code !== 200) {
      // 判断响应状态码
      if (response.data.code == 501)
        return Promise.reject(ElMessage.error('用户名有误'));
      else if (response.data.code == 503)
        return Promise.reject(ElMessage.error('密码有误'));
      else if (response.data.code == 504)
        return Promise.reject(ElMessage.error('登录已过期'));
      else if (response.data.code == 505)
        return Promise.reject(ElMessage.error('用户名占用'));
      else if (response.data.code == 506)
        return Promise.reject(ElMessage.error('用户名为空'));
    } else {
      return response.data.data; // 返回成功响应数据中的data属性数据
    }
  },
  (error) => {
    NProgress.done(); // 关闭进度条
    return Promise.reject(error);
  }
);

export default service;
