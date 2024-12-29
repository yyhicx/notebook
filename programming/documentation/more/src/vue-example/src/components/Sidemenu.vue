<script setup>
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute } from 'vue-router';

const { t } = useI18n();
const route = useRoute();

const navigations = computed(() => [
  {
    path: '/personal/profile',
    label: t('personal.profile')
  },
  {
    path: '/personal/change-password',
    label: t('personal.changePassword')
  },
  {
    path: '/personal/message',
    label: t('personal.messages')
  }
]);

const defaultActive = computed(() => route.path);
</script>

<template>
  <div class="personal-side">
    <h2 class="txt-c">{{ t('personal.title') }}</h2>
    <el-menu :default-active="defaultActive" router class="menu">
      <el-menu-item
        v-for="(item, i) in navigations"
        :key="i"
        :index="item.path"
        >{{ item.label }}</el-menu-item
      >
    </el-menu>
  </div>
</template>

<style lang="scss" scoped>
.personal-side {
  h2 {
    line-height: 40px;
    margin: 0;
    font-size: 18px;
    text-align: center;
    border-bottom: 1px solid #ccc;
  }

  .menu {
    border-right: 0;
  }
}
</style>
