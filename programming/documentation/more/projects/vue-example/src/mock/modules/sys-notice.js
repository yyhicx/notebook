/* 公告管理模块 */
export const listPage = () => {
  return {
    url: 'sys/notice/list-page',
    method: 'get',
    response: (options) => {
      const { pageNum, pageSize } = options.data;
      const totalSize = 4;
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
              'createdBy|1': ['admin', 'admin2'],
              title: '@ctitle(5, 20)',
              createdTime: '@date @time',
              content: '@cparagraph(1, 2)',
              'publishTime|1': ['', '@date @time']
            }
          ]
        }
      };
    }
  };
};

export const operations = () => {
  return {
    url: 'sys/notice/(save|update|delete)',
    method: 'post',
    response: {
      code: 200
    }
  };
};
