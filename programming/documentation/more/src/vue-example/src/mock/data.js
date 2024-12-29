export const roles = [
  {
    name: 'admin',
    label: '超级管理员'
  },
  {
    name: 'master',
    label: '应用管理员'
  },
  {
    name: 'visitor',
    label: '普通用户'
  }
];

export const users = [
  {
    id: 4,
    name: 'test',
    roleId: 'master',
    password: 'test123',
    createdBy: 'admin',
    createdTime: '@date @time'
  },
  {
    id: 3,
    name: 'visitor',
    roleId: 'visitor',
    password: 'visitor',
    createdBy: 'system',
    createdTime: '@date @time'
  },
  {
    id: 2,
    name: 'master',
    roleId: 'master',
    password: 'master',
    createdBy: 'system',
    createdTime: '@date @time'
  },
  {
    id: 1,
    name: 'admin',
    roleId: 'admin',
    password: 'admin123',
    createdBy: 'system',
    createdTime: '@date @time'
  }
];

export const menuTreeData = [
  {
    id: 1,
    parentId: 0,
    name: 'App',
    path: '/app',
    icon: 'i-ep-menu',
    children: [
      {
        id: 11,
        parentId: 1,
        name: 'AppUser',
        path: '/app/user',
        icon: 'i-ep-user'
      },
      {
        id: 12,
        parentId: 1,
        name: 'AppDepartment',
        path: '/app/department',
        icon: 'i-ep-office-building'
      },
      {
        id: 13,
        parentId: 1,
        name: 'AppRole',
        path: '/app/role',
        icon: 'i-ep-avatar'
      },
      {
        id: 14,
        parentId: 1,
        name: 'AppResource',
        path: '/app/resource',
        icon: 'i-ep-management'
      }
    ]
  },
  {
    id: 2,
    parentId: 0,
    name: 'Sys',
    path: '/sys',
    icon: 'i-ep-setting',
    children: [
      {
        id: 21,
        parentId: 2,
        name: 'SysUser',
        path: '/sys/user',
        icon: 'i-ep-user-filled'
      },
      {
        id: 22,
        parentId: 2,
        name: 'SysNotice',
        path: '/sys/notice',
        icon: 'i-ep-chat-dot-round'
      }
    ]
  },
  {
    id: 3,
    parentId: 0,
    name: 'Logs',
    path: '/logs',
    icon: 'i-ep-document',
    children: [
      {
        id: 31,
        parentId: 3,
        name: 'LogsVisit',
        path: '/logs/visit',
        icon: 'i-ep-tickets'
      },
      {
        id: 32,
        parentId: 3,
        name: 'LogsOperation',
        path: '/logs/operation',
        icon: 'i-ep-operation'
      }
    ]
  }
];
