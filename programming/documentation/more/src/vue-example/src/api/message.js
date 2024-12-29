import request from '@/request';

export const list = (data) => {
  const type = data.type;
  delete data.type;
  return request({
    url: `message/list/${type}`,
    method: 'get',
    data: data
  });
};

export const read = (data) => {
  const { id, type } = data;
  delete data.id;
  delete data.type;
  return request({
    url: `message/read/${type}/${id}`,
    method: 'post',
    data: data
  });
};

export const readAll = (data) => {
  const type = data.type;
  delete data.type;
  return request({
    url: `message/read-all/${type}`,
    method: 'post',
    data: data
  });
};

export const remove = (data) => {
  const { id, type } = data;
  delete data.id;
  delete data.type;
  return request({
    url: `message/delete/${type}/${id}`,
    method: 'post',
    data: data
  });
};
