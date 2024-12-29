<script setup>
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';

const { t } = useI18n();
const router = useRouter();
</script>

<template>
  <div class="font-lg">404</div>
  <div class="font-sm">{{ t('tips404') }}</div>
  <p class="txt-c">
    <el-button link @click="router.push('/')">{{ t('backHome') }}</el-button>
  </p>
</template>

<style lang="scss" scoped>
.font-lg {
  margin-top: 40px;
  font-size: 150px;
  font-weight: bold;
  text-align: center;
  color: var(--el-color-primary);
}

.font-sm {
  font-size: 26px;
  text-align: center;
}
</style>
