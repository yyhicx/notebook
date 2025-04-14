import { createRouter, createWebHistory } from 'vue-router';

import Home from '@/pages/Home.vue';
import Add from '@/pages/Add.vue';
import Error from '@/pages/Error.vue';
import Error404 from '@/pages/Error404.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/add',
      name: 'add',
      component: Add,
    },
    {
      path: '/error',
      name: 'error',
      component: Error,
    },
    {
      path: '/404',
      name: 'error404',
      component: Error404,
    },
  ],
});

export default router;
