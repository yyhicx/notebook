<template>
  <el-config-provider :locale="zhCn">
    <Header v-show="isShow"></Header>
    <router-view></router-view>
  </el-config-provider>
</template>

<script setup>
import { defineComponent, computed } from 'vue';
import { useRoute } from 'vue-router';
import { ElConfigProvider } from 'element-plus';
import zhCn from 'element-plus/es/locale/lang/zh-cn';
import Header from '@/components/Header.vue';

defineComponent({
  name: 'App',
});

const route = useRoute();
// 判断是否显示 Header 组件
const isShow = computed(() => {
  return (
    route.name !== 'Login' &&
    route.name !== 'Register' &&
    route.name !== 'addHeadlines'
  );
});
</script>

<style lang="scss" scoped>
</style>
