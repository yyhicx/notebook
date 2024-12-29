/* 资源管理模块 */
const treeData = [
  {
    id: 37,
    createdBy: 'admin',
    lastUpdatedBy: 'admin',
    createdTime: '@date @time',
    parentId: 0,
    lastUpdatedTime: '@date @time',
    name: 'monitor',
    displayName: '系统监控',
    url: '',
    type: 0,
    icon: 'info',
    orderNum: 4,
    level: 0,
    children: [
      {
        id: 38,
        createdBy: null,
        lastUpdatedBy: 'admin',
        createdTime: '@date @time',
        parentId: 37,
        lastUpdatedTime: '@date @time',
        name: 'summary',
        displayName: '统计',
        url: '/monitor/summary',
        type: 1,
        icon: 'warning',
        orderNum: 0,
        level: 1,
        children: []
      },
      {
        id: 39,
        createdBy: null,
        lastUpdatedBy: 'admin',
        createdTime: '@date @time',
        parentId: 37,
        lastUpdatedTime: '@date @time',
        name: 'data',
        displayName: '数据监控',
        url: '/monitor/data',
        type: 1,
        icon: 'warning',
        orderNum: 0,
        level: 1,
        children: [
          {
            id: 40,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 39,
            lastUpdatedTime: '@date @time',
            name: 'view',
            displayName: '查看',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          }
        ]
      },
      {
        id: 41,
        createdBy: 'admin',
        lastUpdatedBy: 'admin',
        createdTime: '@date @time',
        parentId: 37,
        lastUpdatedTime: '@date @time',
        name: 'service',
        displayName: '服务监控',
        url: '/monitor/service',
        type: 1,
        icon: 'view',
        orderNum: 1,
        level: 1,
        children: [
          {
            id: 42,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 41,
            lastUpdatedTime: '@date @time',
            name: 'view',
            displayName: '查看',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          }
        ]
      }
    ]
  },
  {
    id: 43,
    createdBy: 'admin',
    lastUpdatedBy: 'admin',
    createdTime: '@date @time',
    parentId: 0,
    lastUpdatedTime: '@date @time',
    name: 'job',
    displayName: '任务管理',
    url: '',
    type: 0,
    icon: 'service',
    orderNum: 2,
    level: 0,
    children: [
      {
        id: 44,
        createdBy: 'admin',
        createdTime: '@date @time',
        lastUpdatedBy: 'admin',
        parentId: 43,
        lastUpdatedTime: '@date @time',
        name: 'schedule',
        displayName: '定时调度',
        url: '/job/schedule',
        type: 1,
        icon: ' view',
        orderNum: 0,
        level: 1,
        children: [
          {
            id: 45,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 44,
            lastUpdatedTime: '@date @time',
            name: 'view',
            displayName: '查看',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          }
        ]
      }
    ]
  },
  {
    id: 1,
    createdBy: null,
    lastUpdatedBy: null,
    createdTime: '@date @time',
    parentId: 0,
    lastUpdatedTime: '@date @time',
    name: 'metadata',
    displayName: '元数据管理',
    url: null,
    type: 0,
    icon: 'setting',
    orderNum: 0,
    level: 0,
    children: [
      {
        id: 22,
        createdBy: null,
        lastUpdatedBy: null,
        createdTime: '@date @time',
        parentId: 1,
        lastUpdatedTime: '@date @time',
        name: 'dict',
        displayName: '字典管理',
        url: '/metadata/dict',
        type: 1,
        icon: 'edit-outline',
        orderNum: 7,
        level: 1,
        children: [
          {
            id: 23,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 22,
            lastUpdatedTime: '@date @time',
            name: 'view',
            displayName: '查看',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          },
          {
            id: 24,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 22,
            lastUpdatedTime: '@date @time',
            name: 'add',
            displayName: '新增',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          },
          {
            id: 25,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 22,
            lastUpdatedTime: '@date @time',
            name: 'update',
            displayName: '修改',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          },
          {
            id: 26,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 22,
            lastUpdatedTime: '@date @time',
            name: 'remove',
            displayName: '删除',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          }
        ]
      },
      {
        id: 27,
        createdBy: null,
        lastUpdatedBy: null,
        createdTime: '@date @time',
        parentId: 1,
        lastUpdatedTime: '@date @time',
        name: 'database',
        displayName: '数据库管理',
        url: '/metadata/database',
        type: 1,
        icon: 'edit-outline',
        orderNum: 7,
        level: 1,
        children: [
          {
            id: 28,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 27,
            lastUpdatedTime: '@date @time',
            name: 'view',
            displayName: '查看',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          },
          {
            id: 29,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 27,
            lastUpdatedTime: '@date @time',
            name: 'add',
            displayName: '新增',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          },
          {
            id: 30,
            createdBy: null,
            lastUpdatedBy: null,
            createdTime: '@date @time',
            parentId: 27,
            lastUpdatedTime: '@date @time',
            name: 'update',
            displayName: '修改',
            url: null,
            type: 2,
            icon: null,
            orderNum: null,
            level: 2,
            children: []
          }
        ]
      }
    ]
  }
];

export const listTree = () => {
  return {
    url: 'app/resource/list-tree',
    method: 'get',
    response: {
      code: 200,
      msg: null,
      data: treeData
    }
  };
};

export const listTreeParents = () => {
  return {
    url: 'app/resource/list-parents',
    method: 'get',
    response: () => {
      const filterTree = (data) => {
        const newTree = data.filter((v) => v.type !== 2);
        newTree.forEach(
          (v) => v.children && (v.children = filterTree(v.children))
        );
        return newTree;
      };
      return {
        code: 200,
        data: filterTree(JSON.parse(JSON.stringify(treeData)))
      };
    }
  };
};

export const operations = () => {
  return {
    url: 'app/resource/(save|update|remove)',
    method: 'post',
    response: {
      code: 200
    }
  };
};
