/* 操作日志模块 */
export const findPage = () => {
  return {
    url: 'logs/operation/list-page',
    method: 'get',
    response: (options) => {
      const { pageNum, pageSize } = options.data;
      const totalSize = 45;
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
          totalSize: totalSize,
          [content]: [
            {
              id: '@increment',
              createdTime: '@date @time',
              'operator|1': ['admin', 'visitor', 'master', '@word'],
              'operation|1': '@cword(1,10)',
              ip: '@ip',
              duration: '@integer(0, 1000)'
            }
          ]
        }
      };
    }
  };
};
