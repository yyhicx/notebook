/*  机构管理模块 */
import request from '@/request';

export const listTree = () => {
  return request({
    url: 'app/department/list-tree',
    method: 'get'
  });
};

export const listOtherTreeById = (data) => {
  return request({
    url: 'app/department/list-other-tree',
    method: 'get',
    data: data
  });
};

export const save = (data) => {
  return request({
    url: 'app/department/save',
    method: 'post',
    data: data
  });
};

export const update = (data) => {
  const { id } = data;
  delete data.id;
  return request({
    url: `app/department/update/${id}`,
    method: 'post',
    data: data
  });
};

export const remove = (data) => {
  return request({
    url: 'app/department/delete',
    method: 'post',
    data: data
  });
};
