<script setup>
import { ref, computed } from 'vue';
import { useI18n } from 'vue-i18n';
import MessageItem from '@/components/MessageItem.vue';

const { t } = useI18n();
const activeName = ref('system');
const tabs = computed(() => [
  {
    name: 'system',
    label: t('personal.messages')
  },
  {
    name: 'private',
    label: t('personal.siteMail')
  }
]);
</script>

<template>
  <el-tabs v-model="activeName" class="tabs">
    <el-tab-pane
      v-for="(item, i) in tabs"
      :key="i"
      :label="item.label"
      :name="item.name"
    >
      <MessageItem v-if="activeName === item.name" :type="item.name" />
    </el-tab-pane>
  </el-tabs>
</template>
