import { reactive } from 'vue';
import { defineStore } from 'pinia';
import { login, getUserInfo } from '@/api';
import { getToken, setToken, removeToken } from '@/utils/token';

export const useUserInfoStore = defineStore('userInfo', () => {
  const userInfo = reactive({
    token: getToken(),
    nickName: '',
    uid: '',
  });

  const loginAction = async (loginForm) => {
    const result = await login(loginForm);
    const token = result.token;
    userInfo.token = token;
    setToken(token);
  };

  const getUserInfoAction = async () => {
    const result = await getUserInfo();
    userInfo.nickName = result.loginUser.nickName;
    userInfo.uid = result.loginUser.uid;
  };

  const resetUserInfoAction = () => {
    removeToken();
    userInfo.token = '';
    userInfo.nickName = '';
    userInfo.uid = '';
  };

  return { userInfo, loginAction, getUserInfoAction, resetUserInfoAction };
});
