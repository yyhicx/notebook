/* 系统登录模块 */
import { users } from '@/mock/data';

export const login = () => {
  return {
    url: 'login',
    method: 'post',
    response: (options) => {
      const name = options.data.account;
      if (
        users.find(
          (v) => v.name === name && v.password === options.data.password
        )
      ) {
        return {
          code: 200,
          msg: '',
          data: {
            token: name + '@eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cC',
            name
          }
        };
      }
      return {
        code: -1,
        msg: '用户名或密码错误'
      };
    }
  };
};

export const logout = () => {
  return {
    url: 'logout',
    method: 'get',
    response: {
      code: 200,
      msg: null,
      data: {}
    }
  };
};
