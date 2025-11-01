<script setup>
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';
import { logout } from '@/api/login';
import { useUserStore } from '@/store/user';
import { onMounted } from 'vue';

const { locale, t } = useI18n();
const router = useRouter();
const userStore = useUserStore();
const isLogin = computed(() => userStore.isLogin);
const userInfo = computed(() => userStore.userInfo);
const username = computed(() => userInfo.value?.name);
const unReadCount = computed(() => userInfo.value?.unReadCount);
const commands = {
  toPersonal: () => {
    router.push('/personal');
  },
  toLogout: () => {
    logout().then((response) => {
      if (response.code === 200) {
        userStore.clearToken();
        userStore.clearUserInfo();
        router.push('/login');
      }
    });
  }
};

function changeLanguage(lang) {
  locale.value = lang;
  localStorage.setItem('locale', lang);
}

function handleCommand(command) {
  commands[command] && commands[command]();
}

onMounted(() => {
  userStore.refreshInfo();
});
</script>

<template>
  <div class="header-cont">
    <div class="left">
      <h1>
        <router-link :to="isLogin ? '/' : '/login'">{{
          t('sitename')
        }}</router-link>
      </h1>
    </div>
    <div class="right flex-center">
      <div class="lang gap">
        <span
          class="item"
          :class="{ active: locale === 'zh-cn' }"
          @click="changeLanguage('zh-cn')"
          >简体中文</span
        >
        /
        <span
          class="item"
          :class="{ active: locale === 'en' }"
          @click="changeLanguage('en')"
          >EN</span
        >
      </div>
      <template v-if="isLogin">
        <div class="gap">
          <router-link to="/personal/message">
            <el-badge :is-dot="!!unReadCount">
              <el-icon>
                <i-ep-message />
              </el-icon>
            </el-badge>
          </router-link>
        </div>
        <el-dropdown trigger="click" @command="handleCommand">
          <div class="flex-center cursor">
            {{ username }}
            <el-icon>
              <i-ep-caret-bottom />
            </el-icon>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="toPersonal">{{
                t('personalCenter')
              }}</el-dropdown-item>
              <el-dropdown-item command="toLogout">{{
                t('logout')
              }}</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </template>
      <template v-else-if="$route.name !== 'Login'">
        <router-link to="/login">{{ t('login') }}</router-link>
      </template>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.header-cont {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  height: 100%;

  a {
    color: inherit;
    text-decoration: none;
  }

  h1 {
    margin: 0;
    font-size: 20px;
  }

  .gap {
    margin-right: 20px;
  }

  .right {
    .lang {
      font-size: 14px;

      .item {
        cursor: pointer;

        &.active {
          font-size: 18px;
          font-weight: bold;
        }
      }
    }
  }

  .el-dropdown {
    color: inherit;
  }
}
</style>
