/* 公告管理模块 */
import request from '@/request';

export const listPage = (data) => {
  return request({
    url: 'sys/notice/list-page',
    method: 'get',
    data: data
  });
};

export const save = (data) => {
  return request({
    url: 'sys/notice/save',
    method: 'post',
    data: data
  });
};

export const update = (data) => {
  const { id } = data;
  delete data.id;
  return request({
    url: `sys/notice/update/${id}`,
    method: 'post',
    data: data
  });
};

export const remove = (data) => {
  return request({
    url: 'sys/notice/delete',
    method: 'post',
    data: data
  });
};
