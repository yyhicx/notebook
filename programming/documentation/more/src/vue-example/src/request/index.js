import axios from 'axios';
import { ElMessage } from 'element-plus';
import config from '@/request/config';
import router from '@/router';

export default function request(options) {
  return new Promise((resolve, reject) => {
    const instance = axios.create({ ...config });

    // 请求拦截器
    instance.interceptors.request.use(
      (config) => {
        let token = localStorage.getItem('pm_token');
        if (token) {
          config.headers.token = token;
        } else {
          router.push('/login');
        }
        return config;
      },
      (error) => {
        console.log('request: ', error);
        if (
          error.code === 'ECONNABORTED' &&
          error.message.indexOf('timeout') !== -1
        ) {
          ElMessage({
            message: 'Request Timeout',
            type: 'error',
            showClose: true
          });
        }
        return Promise.reject(error);
      }
    );

    // 响应拦截器
    instance.interceptors.response.use(
      (response) => {
        return response.data;
      },
      (error) => {
        if (error && error.response) {
          switch (error.response.status) {
            case 400:
              error.message = 'Request Error';
              break;
            case 401:
              error.message = 'Unauthorized';
              break;
            case 403:
              error.message = 'Forbidden';
              break;
            case 404:
              error.message = `Request URL Not Found: ${error.response.config.url}`;
              break;
            case 408:
              error.message = 'Request Timeout';
              break;
            case 500:
              error.message = 'Server Error';
              break;
            case 501:
              error.message = 'Not Implemented';
              break;
            case 502:
              error.message = 'Bad Gateway';
              break;
            case 503:
              error.message = 'Service Unavailable';
              break;
            case 504:
              error.message = 'Gateway Timeout';
              break;
            case 505:
              error.message = 'HTTP Version Not Supported';
              break;
            default:
              error.message = `Error: ${error.response.status}`;
              break;
          }
        }
        if (error.message) {
          ElMessage({ message: error.message, type: 'error', showClose: true });
        }
        return Promise.reject(error);
      }
    );

    // 处理响应数据
    instance(options)
      .then((response) => {
        /**
         *  response 格式
         *  {
         *    code: 200,
         *    msg: '消息',
         *    data: '数据',
         *  }
         *  code 为 200 时，表示请求成功，data 为请求返回的数据
         *  code 为 -1 时，表示请求失败，可能网络不通，可以服务器异常或其他异常
         *  code 为 -2 时，表示未登录，需要跳转到登录页面
         */
        if (response.code === 200) {
          resolve(response);
        } else {
          if (response.code === -2) {
            router.push('/login');
          }
          ElMessage({
            message: response.msg || 'Operation failed',
            type: 'error',
            showClose: true
          });
          reject(response);
        }
      })
      .catch((error) => {
        reject(error);
      });
  });
}
