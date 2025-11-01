<script setup>
import { useI18n } from 'vue-i18n';
import { useRoute } from 'vue-router';
import { useGlobalStore } from '@/store/global';

const { t } = useI18n();
const route = useRoute();
const globalStore = useGlobalStore();
const treeData = computed(() => globalStore.menuTree);
const defaultActive = computed(() => route.path || treeData.value[0].path);
const isCollapsed = ref(false);
</script>

<template>
  <div class="page-sidebar">
    <div class="collapse-bar">
      <el-icon class="cursor" @click="isCollapsed = !isCollapsed">
        <i-ep-expand v-if="isCollapsed" />
        <i-ep-fold v-else />
      </el-icon>
    </div>
    <el-menu
      :default-active="defaultActive"
      router
      class="sidemenu"
      :collapse="isCollapsed"
    >
      <el-sub-menu v-for="(item, i) in treeData" :key="i" :index="item.path">
        <template #title>
          <el-icon v-if="item.icon">
            <component :is="item.icon"></component>
          </el-icon>
          <span>{{ t(`menu.${item.name}`) }}</span>
        </template>
        <template v-for="(child, i) in item.children" :key="i">
          <el-menu-item :index="child.path">
            <el-icon>
              <component :is="child.icon"></component>
            </el-icon>
            {{ t(`menu.${child.name}`) }}
          </el-menu-item>
        </template>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<style lang="scss" scoped>
$side-width: 200px;

.page-sidebar {
  .sidemenu.el-menu,
  .sidemenu .el-sub-menu > .el-menu {
    --el-menu-text-color: #ccc;
    --el-menu-hover-bg-color: #060251;
    --el-menu-border-color: transparent;
    --el-menu-bg-color: #000;

    .el-menu-item {
      &.is-active {
        background-color: var(--el-menu-hover-bg-color);
      }
    }
  }

  .sidemenu.el-menu:not(.el-menu--collapse) {
    width: $side-width;
  }

  .collapse-bar {
    color: #fff;
    font-size: 16px;
    line-height: 36px;
    text-align: center;

    .c-icon {
      cursor: pointer;
    }
  }
}
</style>
