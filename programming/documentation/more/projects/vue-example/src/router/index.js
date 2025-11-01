import { createRouter, createWebHistory } from 'vue-router';
import { menuTree } from '@/api/personal';
import PageFrame from '@/components/PageFrame.vue';
import Layout from '@/layout/index.vue';
import { useGlobalStore } from '@/store/global';
import { useUserStore } from '@/store/user';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Layout,
    children: [
      {
        path: 'login',
        name: 'Login',
        component: () => import('@/views/login/index.vue')
      },
      {
        path: '404',
        name: 'NotFound',
        component: () => import('@/views/404.vue')
      },
      {
        path: 'personal',
        name: 'Personal',
        meta: {
          requiresAuth: true
        },
        component: () => import('@/views/personal/index.vue'),
        redirect: '/personal/profile',
        children: [
          {
            path: 'profile',
            name: 'PersonalProfile',
            meta: {
              requiresAuth: true
            },
            component: () => import('@/views/personal/Profile.vue')
          },
          {
            path: 'change-password',
            name: 'PersonalChangePassword',
            meta: {
              requiresAuth: true
            },
            component: () => import('@/views/personal/ChangePassword.vue')
          },
          {
            path: 'message',
            name: 'PersonalMessage',
            meta: {
              requiresAuth: true
            },
            component: () => import('@/views/personal/Message.vue')
          }
        ]
      }
      // {
      //   path: 'app',
      //   name: 'App',
      //   meta: {
      //     requiresAuth: true
      //   },
      //   component: PageFrame,
      //   redirect: '/app/user',
      //   children: [
      //     {
      //       path: 'user',
      //       name: 'AppUser',
      //       meta: {
      //         requiresAuth: true
      //       },
      //       component: () => import('@/views/app/User.vue')
      //     },
      //     {
      //       path: 'department',
      //       name: 'AppDepartment',
      //       meta: {
      //         requiresAuth: true
      //       },
      //       component: () => import('@/views/app/Department.vue')
      //     },
      //     {
      //       path: 'role',
      //       name: 'AppRole',
      //       meta: {
      //         requiresAuth: true
      //       },
      //       component: () => import('@/views/app/Role.vue')
      //     },
      //     {
      //       path: 'resource',
      //       name: 'AppResource',
      //       meta: {
      //         requiresAuth: true
      //       },
      //       component: () => import('@/views/app/Resource.vue')
      //     }
      //   ]
      // },
      // {
      //   path: 'sys',
      //   meta: {
      //     requiresAuth: true
      //   },
      //   component: PageFrame,
      //   redirect: '/sys/user',
      //   children: [
      //     {
      //       path: 'user',
      //       name: 'SysUser',
      //       meta: {
      //         requiresAuth: true
      //       },
      //       component: () => import('@/views/sys/User.vue')
      //     },
      //     {
      //       path: 'notice',
      //       name: 'SysNotice',
      //       meta: {
      //         requiresAuth: true
      //       },
      //       component: () => import('@/views/sys/Notice.vue')
      //     }
      //   ]
      // },
      // {
      //   path: 'logs',
      //   name: 'LogsManagement',
      //   meta: {
      //     requiresAuth: true
      //   },
      //   component: PageFrame,
      //   redirect: '/logs/visit',
      //   children: [
      //     {
      //       path: 'visit',
      //       name: 'LogsVisit',
      //       meta: {
      //         requiresAuth: true
      //       },
      //       component: () => import('@/views/logs/Visit.vue')
      //     },
      //     {
      //       path: 'operation',
      //       name: 'LogsOperation',
      //       meta: {
      //         requiresAuth: true
      //       },
      //       component: () => import('@/views/logs/Operation.vue')
      //     }
      //   ]
      // },
    ]
  }
  // {
  //   path: "/:pathMatch(.*)*",
  //   name: "404",
  //   redirect: '/404'
  // }
];

const route404 = {
  path: '/:pathMatch(.*)*',
  name: '404',
  redirect: '/404'
};

const router = createRouter({
  history: createWebHistory(import.meta.env.VITE_VUE_APP_PUBLIC_PATH),
  routes
});

router.beforeEach(async (to) => {
  const globalStore = useGlobalStore();
  const userStore = useUserStore();
  const isLogin = userStore.isLogin;

  if (to.path === '/login') {
    if (isLogin) {
      return { name: 'Home' };
    }
    return true;
  }
  if (to.meta.requiresAuth && !isLogin) {
    return { name: 'Login' };
  }

  await addDynamic();

  if (!to.name && hasRoute(to)) {
    return { ...to };
  }
  if (to.path === '/' && globalStore.firstRoute) {
    return globalStore.firstRoute;
  }

  return true;
});

function hasRoute(to) {
  const item = router.getRoutes().find((route) => route.path === to.path);
  return !!item;
}

function addDynamic() {
  const globalStore = useGlobalStore();
  if (globalStore.routeLoaded) {
    return;
  }

  return menuTree().then((response) => {
    if (response.data && response.data.length) {
      addDynamicRoutes(response.data);
    }
    router.addRoute(route404);
    globalStore.routeLoaded = true;
    globalStore.setMenuTree(response.data);
  });
}

const modules = import.meta.glob('@/views/**/*.vue');
function addDynamicRoutes(data, parent) {
  data.forEach((item, i) => {
    const route = {
      path: item.path,
      name: item.name,
      meta: {
        title: item.title,
        icon: item.icon
      },
      children: []
    };

    if (parent) {
      if (item.parentId !== 0) {
        const componentPathArray = item.path.replace('/', '').split('/');
        const l = componentPathArray.length - 1;
        const componentPath = componentPathArray
          .map((v, i) => {
            return i === l
              ? v.replace(/\w/, (L) => L.toUpperCase()) + '.vue'
              : v;
          })
          .join('/');
        route.path = componentPathArray[l];
        route.component = modules[`/src/views/${componentPath}`];
        parent.children.push(route);
      }
    } else {
      if (item.children && item.children.length) {
        route.redirect = item.children[0].path;
        addDynamicRoutes(item.children, route);
      }
      route.component = PageFrame;
      if (i === 0) {
        const globalStore = useGlobalStore();
        globalStore.firstRoute = route;
      }
      router.addRoute('Home', route);
    }
  });
}

export default router;
