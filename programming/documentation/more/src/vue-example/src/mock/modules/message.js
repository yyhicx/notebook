const systemMessage = [
  {
    id: 4,
    title: '系统升级提示',
    date: '2021-10-23',
    content:
      '您好，系统将于2021-10-24 00:00:00 - 2021-10-24 08:00:00 进行服务升级，期间系统不可用，请谅解！',
    isRead: false
  },
  {
    id: 3,
    title: '系统升级提示',
    date: '2019-10-22',
    content:
      '您好，系统将于2019-10-23 00:00:00 - 2019-10-23 08:00:00 进行服务升级，期间系统不可用，请谅解！',
    isRead: true
  }
];

const privateMessage = [
  {
    id: 2,
    title: '修改资料成功',
    date: '2019-10-23',
    content: '您刚刚修改了用户头像！',
    isRead: false
  },
  {
    id: 1,
    title: '注册成功',
    date: '2019-10-22',
    content: '恭喜您注册权限管理系统成功！',
    isRead: true
  }
];

export const list = () => {
  return {
    url: 'message/list/(system|private)',
    type: 'get',
    response: (options) => {
      const { pageNum, pageSize } = options.data;
      const isPrivate = options?.url?.split('/').pop() === 'private';
      const responseData = isPrivate ? privateMessage : systemMessage;
      return {
        code: 200,
        data: {
          pageNum,
          pageSize,
          content: responseData,
          totalSize: responseData.length
        }
      };
    }
  };
};

export const read = () => {
  return {
    url: `message/read/(system|private)/.+$`,
    type: 'post',
    response: (options) => {
      let index = -1;
      const arr = options?.url.split('/');
      const id = arr.pop();
      const type = arr.pop();
      if (type === 'private') {
        index = privateMessage.findIndex((v) => (v.id = id));
        index > -1 && (privateMessage[index].isRead = true);
      } else {
        index = systemMessage.findIndex((v) => (v.id = id));
        index > -1 && (systemMessage[index].isRead = true);
      }
      return {
        code: 200,
        msg: null
      };
    }
  };
};

export const readAll = () => {
  return {
    url: 'message/read-all/.+',
    type: 'post',
    response: (options) => {
      if (options?.url.split('/').pop() === 'private') {
        privateMessage.forEach((v) => {
          !v.isRead && (v.isRead = true);
        });
      } else {
        systemMessage.forEach((v) => {
          !v.isRead && (v.isRead = true);
        });
      }
      return {
        code: 200,
        msg: null
      };
    }
  };
};

export const remove = () => {
  return {
    url: 'message/delete/(system|private)/.+$',
    method: 'post',
    response: (options) => {
      let index = -1;
      const arr = options?.url.split('/');
      const id = arr.pop();
      const type = arr.pop();
      if (type === 'private') {
        index = privateMessage.findIndex((v) => (v.id = id));
        index > -1 && privateMessage.splice(index, 1);
      } else {
        index = systemMessage.findIndex((v) => (v.id = id));
        index > -1 && systemMessage.splice(index, 1);
      }
      return {
        code: 200,
        msg: null
      };
    }
  };
};
