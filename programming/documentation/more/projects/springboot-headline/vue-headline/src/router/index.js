import { createRouter, createWebHistory } from 'vue-router';
import { cloneDeep } from 'lodash';
import { staticRoutes } from '@/router/routes';
import { useUserInfoStore } from '@/stores/userInfo';
import { getToken, removeToken } from '@/utils/token';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: staticRoutes,
});

// 全局前置守卫
router.beforeEach(async (to, from, next) => {
  const userInfoStore = useUserInfoStore();
  const userInfo = cloneDeep(userInfoStore.userInfo);

  // to 标识要跳转的路由对象
  // from 标识从哪个路由对象跳转而来
  // next() 继续导航
  // next({ path: 'xxx' }) 重定向到指定路径
  // next(false) 取消当前导航
  const token = getToken();
  // 如果 userInfo.nickName 存在，则 isHaveUserInfo 为 true，否则为 false
  const isHaveUserInfo = !!userInfo.nickName;
  if (token) {
    if (to.path === '/login') {
      next({ path: '/' });
    } else {
      if (isHaveUserInfo) {
        next();
      } else {
        try {
          await userInfoStore.getUserInfoAction();
          next();
        } catch (error) {
          removeToken();
        }
      }
    }
  } else {
    next();
  }
});

export default router;
