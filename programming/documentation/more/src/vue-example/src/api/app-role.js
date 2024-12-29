/*  角色管理模块 */
import request from '@/request';

export const listPage = (data) => {
  return request({
    url: 'app/role/list-page',
    method: 'get',
    data: data
  });
};

export const listSimple = () => {
  return request({
    url: 'app/role/list-simple',
    method: 'get'
  });
};

export const save = (data) => {
  return request({
    url: 'app/role/save',
    method: 'post',
    data: data
  });
};

export const update = (data) => {
  const { id } = data;
  delete data.id;
  return request({
    url: `app/role/update/${id}`,
    method: 'post',
    data: data
  });
};

export const remove = (data) => {
  return request({
    url: 'app/role/delete',
    method: 'post',
    data: data
  });
};

export const bindResource = (data) => {
  return request({
    url: 'app/role/bind-resource',
    method: 'post',
    data: data
  });
};
