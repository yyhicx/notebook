/*  用户管理模块 */
import { roles, users } from '@/mock/data';

export const listPage = () => {
  return {
    url: 'sys/user/list-page',
    method: 'get',
    response: (options) => {
      const { pageNum, pageSize, name } = options.data;
      let set = users.map((v) => {
        const o = { ...v };
        if (v.id <= 4) {
          o.password = o.name;
          o.roleName = roles.find((item) => item.name === o.roleId).label;
          o.ip = '@ip';
          o.visitTime = '@date @time';
        }
        return o;
      });
      if (name) {
        set = set.filter((v) => v.name === name);
      }
      const totalSize = set.length;
      const totalPages = Math.ceil(totalSize / pageSize);
      let lastIndex = pageNum * pageSize;
      if (lastIndex > totalSize) {
        lastIndex = totalSize;
      }
      let responseData = [];
      if (pageNum >= 1 && pageNum <= totalPages) {
        responseData = set.slice((pageNum - 1) * pageSize, lastIndex);
      }
      return {
        code: 200,
        msg: null,
        data: {
          pageNum,
          pageSize,
          totalSize,
          content: responseData
        }
      };
    }
  };
};

export const save = () => {
  return {
    url: 'sys/user/save',
    method: 'post',
    response: (options) => {
      return {
        code: 200,
        data: {
          name: options.data.name,
          password: '@word(6,16)'
        }
      };
    }
  };
};

export const operations = () => {
  return {
    url: 'sys/user/(update|delete)',
    method: 'post',
    response: {
      code: 200
    }
  };
};

export const setPassword = () => {
  return {
    url: 'sys/user/password',
    method: 'get',
    response: (options) => {
      return {
        code: 200,
        data: {
          name: options.data.name,
          password: '@word(6,16)'
        }
      };
    }
  };
};
