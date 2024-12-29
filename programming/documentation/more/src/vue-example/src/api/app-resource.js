/* 资源管理模块 */
import request from '@/request';

export const listTree = () => {
  return request({
    url: 'app/resource/list-tree',
    method: 'get'
  });
};

export const listTreeParents = (data) => {
  return request({
    url: 'app/resource/list-parents',
    method: 'get',
    data: data
  });
};

export const save = (data) => {
  return request({
    url: 'app/resource/save',
    method: 'post',
    data: data
  });
};

export const update = (data) => {
  const { id } = data;
  delete data.id;
  return request({
    url: `app/resource/update/${id}`,
    method: 'post',
    data: data
  });
};

export const remove = (data) => {
  return request({
    url: 'app/resource/delete',
    method: 'post',
    data: data
  });
};
