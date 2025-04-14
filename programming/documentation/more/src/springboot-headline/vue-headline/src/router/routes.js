export const staticRoutes = [
  {
    // 首页：自动跳转到头条首页
    path: '/',
    redirect: '/headlines',
  },
  {
    // 头条首页
    path: '/headlines',
    component: () => import('@/pages/Headlines/index.vue'),
    name: 'Headlines',
  },
  {
    // 头条详情
    path: '/headline-detail/:hid',
    component: () => import('@/pages/HeadlineDetail/index.vue'),
    name: 'HeadlineDetail',
    props: true, // 将路由参数映射到组件的 props 中
  },
  {
    // 发布头条
    path: '/add-or-modify-headline',
    component: () => import('@/pages/AddOrModifyHeadline/index.vue'),
    name: 'AddOrModifyHeadline',
  },
  {
    // 登录
    path: '/login',
    component: () => import('@/pages/Login/index.vue'),
    name: 'Login',
  },
  {
    // 注册
    path: '/register',
    component: () => import('@/pages/Register/index.vue'),
    name: 'Register',
  },
  {
    // 404
    path: '/:pathMatch(.*)*',
    component: () => import('@/pages/NotFound/index.vue'),
    name: 'NotFound',
  },
];
