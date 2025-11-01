import request from '@/request';

export const userInfo = (data) => {
  return request({
    url: 'personal/user-info',
    method: 'get',
    data: data
  });
};

export const menuTree = (data) => {
  return request({
    url: 'personal/menu-tree',
    method: 'get',
    data: data
  });
};

export const changeProfile = (data) => {
  return request({
    url: 'personal/change-profile',
    method: 'post',
    data: data
  });
};

export const changePassword = (data) => {
  return request({
    url: 'personal/change-password',
    method: 'post',
    data: data
  });
};
