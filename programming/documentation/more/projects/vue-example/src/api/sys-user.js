/*  用户管理模块 */
import request from '@/request';

export const listPage = (data) => {
  return request({
    url: 'sys/user/list-page',
    method: 'get',
    data: data
  });
};

export const save = (data) => {
  return request({
    url: 'sys/user/save',
    method: 'post',
    data: data
  });
};

export const update = (data) => {
  const { id } = data;
  delete data.id;
  return request({
    url: `sys/user/update/${id}`,
    method: 'post',
    data: data
  });
};

export const setPassword = (data) => {
  const { id } = data;
  delete data.id;
  return request({
    url: `sys/user/password/${id}`,
    method: 'get',
    data: data
  });
};

export const remove = (data) => {
  return request({
    url: 'sys/user/delete',
    method: 'post',
    data: data
  });
};
