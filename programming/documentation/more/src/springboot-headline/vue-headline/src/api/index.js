import request from '@/utils/request';

// 登录
export const login = (info) => {
  return request.post('user/login', info);
};

// 获取用户数据
export const getUserInfo = () => {
  return request.get('user/getUserInfo');
};

// 注册
export const register = (info) => {
  return request.post('user/register', info);
};

// 重复名查询
export const checkUsername = (username) => {
  return request({
    method: 'post',
    url: 'user/checkUsername',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    },
    data: `username=${username}`,
  });
};

// 查询首页分类
export const findAllTypes = () => {
  return request.get('portal/findAllTypes');
};

// 查询首页头条信息（分页，带条件）
export const findHeadlines = (info) => {
  return request.post('portal/findHeadlines', info);
};

// 查询头条详情
export const findHeadlineDetail = (hid) => {
  return request({
    method: 'post',
    url: 'portal/findHeadlineDetail',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    },
    data: `hid=${hid}`,
  });
};

// 查询用户 Token 是否过期
export const checkLogin = () => {
  return request.get('user/checkLogin');
};

// 头条发布
export const publishHeadline = (info) => {
  return request.post('headline/publish', info);
};

// 修改头条回显
export const findHeadlineByHid = (hid) => {
  return request({
    method: 'post',
    url: 'headline/findHeadlineByHid',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    },
    data: `hid=${hid}`,
  });
};

// 头条修改实现
export const updateHeadline = (info) => {
  return request.post('headline/update', info);
};

// 删除头条
export const removeHeadlineByHid = (hid) => {
  return request({
    method: 'post',
    url: 'headline/removeByHid',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    },
    data: `hid=${hid}`,
  });
};
