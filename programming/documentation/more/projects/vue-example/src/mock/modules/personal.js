import { users, menuTreeData } from '@/mock/data';

export const userInfo = () => {
  return {
    url: 'personal/user-info',
    method: 'get',
    response: () => {
      const token = localStorage.getItem('pm_token');
      if (token) {
        const info = { ...users.find((v) => v.name === token.split('@')[0]) };
        delete info.password;
        return {
          code: 200,
          data: {
            ...info,
            'unReadCount|0-10': 0
          }
        };
      } else {
        return {
          code: -2,
          msg: '请先登录！'
        };
      }
    }
  };
};

export const menuTree = () => {
  return {
    url: 'personal/menu-tree',
    method: 'get',
    response: () => {
      const token = localStorage.getItem('pm_token');
      if (!token) {
        return {
          code: 200,
          msg: ''
        };
      }
      const name = token.split('@')[0];
      const info = users.find((v) => v.name === name);
      const role = info.roleId;
      let treeData = [menuTreeData[2]];
      switch (role) {
        case 'admin':
          treeData = menuTreeData;
          break;
        case 'master':
          treeData = [menuTreeData[0], menuTreeData[2]];
          break;
        case 'visitor':
          treeData = [menuTreeData[2]];
          break;
        default:
          break;
      }

      return {
        code: 200,
        data: treeData
      };
    }
  };
};

export const changeProfile = () => {
  return {
    url: 'personal/change-profile',
    type: 'post',
    response: {
      code: 200,
      msg: null
    }
  };
};

export const changePassword = () => {
  return {
    url: 'personal/change-password',
    type: 'post',
    response: {
      code: 200,
      msg: null
    }
  };
};
