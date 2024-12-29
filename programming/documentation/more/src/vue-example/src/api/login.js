/* 系统登录模块 */
import request from '@/request';

export const login = (data) => {
  return request({
    url: 'login',
    method: 'post',
    data: data
  });
};

export const logout = () => {
  return request({
    url: 'logout',
    method: 'get'
  });
};
