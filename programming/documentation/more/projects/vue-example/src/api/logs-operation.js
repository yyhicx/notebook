/* 操作日志模块 */
import request from '@/request';

export const listPage = (data) => {
  return request({
    url: 'logs/operation/list-page',
    method: 'get',
    data: data
  });
};
