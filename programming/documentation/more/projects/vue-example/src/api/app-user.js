/* 用户管理模块 */
import request from '@/request';

export const listPage = (data) => {
  return request({
    url: 'app/user/list-page',
    method: 'post',
    data: data
  });
};

export const save = (data) => {
  return request({
    url: 'app/user/save',
    method: 'post',
    data: data
  });
};

export const update = (data) => {
  const { id } = data;
  delete data.id;
  return request({
    url: `app/user/update/${id}`,
    method: 'post',
    data: data
  });
};

export const remove = (data) => {
  return request({
    url: 'app/user/delete',
    method: 'post',
    data: data
  });
};
