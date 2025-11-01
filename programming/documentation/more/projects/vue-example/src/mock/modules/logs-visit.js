/* 访问日志模块 */
export const listPage = () => {
  return {
    url: 'logs/visit/list-page',
    method: 'get',
    response: (options) => {
      const { pageNum, pageSize } = options.data;
      const totalSize = 105;
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
              createdTime: '@date @time',
              'username|1': ['admin', 'visitor', 'master', '@word'],
              'status|1': ['登录', '退出'],
              ip: '@ip',
              duration: '@integer(0, 1000)'
            }
          ]
        }
      };
    }
  };
};
