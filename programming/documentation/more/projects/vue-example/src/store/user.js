import { defineStore } from 'pinia';
import { userInfo } from '@/api/personal';

export const useUserStore = defineStore('user', {
  state: () => ({
    token: '',
    userInfo: {}
  }),
  getters: {
    isLogin: (state) => !!state.token || !!localStorage.getItem('pm_token')
  },
  actions: {
    setToken(token) {
      this.token = token;
      localStorage.setItem('pm_token', token);
    },
    clearToken() {
      this.token = '';
      localStorage.removeItem('pm_token');
    },
    setUserInfo(info) {
      this.userInfo = info || {};
    },
    clearUserInfo() {
      this.userInfo = {};
    },
    async refreshInfo() {
      const response = await userInfo();
      this.userInfo = response.data;
    }
  }
});
