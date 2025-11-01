/*  角色管理模块 */
export const listPage = () => {
  return {
    url: 'app/role/list-page',
    method: 'get',
    response: (options) => {
      const { pageNum, pageSize } = options.data;
      const totalSize = 14;
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
              'lastUpdatedBy|1': ['admin', 'master', 'admin2'],
              lastUpdatedTime: '@date @time',
              name: '@word',
              'remark|1': ['测试人员', '开发人员', '项目经理', '@cword(3,6)'],
              'resourceIds|1': [
                '1, 22, 23, 24',
                '28',
                '39, 40, 43, 44, 45, 27, 28, 29, 30'
              ]
            }
          ]
        }
      };
    }
  };
};

export const listSimple = () => {
  return {
    url: 'app/role/list-simple',
    method: 'get',
    response: {
      code: 200,
      msg: null,
      data: [
        {
          id: 1,
          name: 'admin'
        },
        {
          id: 2,
          name: 'dev'
        },
        {
          id: 3,
          name: 'test'
        },
        {
          id: 4,
          name: 'mng'
        }
      ]
    }
  };
};

export const operations = () => {
  return {
    url: 'app/role/(save|update|delete|bind-resource)',
    method: 'post',
    response: {
      code: 200
    }
  };
};
