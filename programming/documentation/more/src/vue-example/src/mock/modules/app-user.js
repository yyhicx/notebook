/* 用户管理模块 */
export const listPage = () => {
  return {
    url: 'app/user/list-page',
    method: 'post',
    response: (options) => {
      const { pageNum, pageSize } = options.data;
      const totalSize = 64;
      const content =
        pageNum * pageSize < totalSize
          ? `content|${pageSize}`
          : `content|${totalSize % pageSize}`;
      return {
        code: 200,
        msg: null,
        data: {
          pageNum,
          pageSize,
          totalSize,
          [content]: [
            {
              id: '@increment',
              'createdBy|1': ['admin', 'master', 'admin2'],
              createdTime: '@date @time',
              lastUpdatedBy: ['admin', 'master', 'admin2'],
              lastUpdatedTime: '@date @time',
              name: '@word',
              avatar: null,
              password: 'fd80ebd493a655608dc893a9f897d845',
              email: '@email',
              mobile: /1(3|4|5|7|8|9)\d{9}/,
              'status|1': [0, 1],
              'departmentId|1-14': 10,
              'departmentName|1': [
                'wowo集团 / 北京分公司 / 市场部',
                'wowo集团 / 北京分公司 / 技术部',
                'wowo集团 / 上海分公司',
                '大具集团 / 北京分公司'
              ],
              'roleIds|1': ['2, 3', '1, 4', '4', '3', '2', '1'],
              'roleNames|1': ['项目经理', '开发人员', '测试人员', '管理员']
            }
          ]
        }
      };
    }
  };
};

export const save = () => {
  return {
    url: 'app/user/save',
    method: 'post',
    response: (options) => {
      return {
        code: 200,
        data: {
          name: options.data.name,
          password: '@word(8,16)'
        }
      };
    }
  };
};

export const operations = () => {
  return {
    url: 'app/user/(update|delete)',
    method: 'post',
    response: {
      code: 200
    }
  };
};
