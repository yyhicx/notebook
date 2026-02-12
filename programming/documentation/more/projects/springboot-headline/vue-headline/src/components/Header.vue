<template>
  <div class="header-container">
    <!-- 头部左侧区域 -->
    <div class="left">
      <!-- 所有头条分类 -->
      <ul>
        <li v-for="(item, index) in headlineTypeList" :key="item.tid" @click="highlightHandler(index)">
          <a :class="{ active: item.isHihlight }" href="javascript:void(0)">
            {{ item.tname }}
          </a>
        </li>
      </ul>
    </div>

    <!-- 头部右侧区域 -->
    <div class="right">
      <!-- 头条搜索 -->
      <div class="right-input" style="margin-right: 50px">
        <el-input v-model="keywords" placeholder="搜索最新头条"></el-input>
        <!-- <el-button type="primary">搜索</el-button -->
      </div>

      <!-- 用户信息和用户登录 -->
      <div class="btn-dropdown">
        <!-- 用户登录后的展示 -->
        <div v-if="nickName" style="display: flex; justify-content: center; align-items: center">
          <el-dropdown>
            <el-button type="primary">
              您好：{{ nickName }}
              <el-icon class="el-icon--right"><i-ep-arrow-down /></el-icon>
            </el-button>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="toAddOrModifyHeadline">发布新闻</el-dropdown-item>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item>浏览记录</el-dropdown-item>
                <el-dropdown-item @click="toLogout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
        <!-- 用户没有登录时的展示 -->
        <div v-else class="container-button">
          <el-button size="small" style="background: #212529; color: #aea7a2" @click="toLogin">登录</el-button>
          <el-button size="small" style="background: #ffc107; color: #684802" @click="toRegister">注册</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {
  defineComponent,
  getCurrentInstance,
  ref,
  onMounted,
  onUpdated,
  watch,
} from 'vue';
import { useRouter } from 'vue-router';
import { cloneDeep } from 'lodash';
import { checkLogin } from '@/api';
import { useHeadlineTypesStore } from '@/stores/headlineTypes';
import { useUserInfoStore } from '@/stores/userInfo';
import { removeToken } from '@/utils/token';

defineComponent({
  name: 'Header',
});

const router = useRouter();
const { mittBus } = getCurrentInstance().appContext.config.globalProperties;

const headlineTypesStore = useHeadlineTypesStore();
const headlineTypeList = ref([]); // 所有头条分类
const getHeadlineTypeList = async () => {
  // 获取所有头条分类
  await headlineTypesStore.getHeadlineTypesAction();
  const headlineTypes = cloneDeep(headlineTypesStore.headlineTypes);
  headlineTypes.forEach((item) => {
    // 添加高亮标识
    item.isHihlight = false;
  });
  // 将 “微头条” 插入到数组最前面
  headlineTypes.unshift({
    tid: 0,
    tname: '微头条',
    isHihlight: true,
  });
  // 将所有头条分类赋值给 headlineTypeList
  headlineTypeList.value = headlineTypes;
};
onMounted(() => {
  // 页面加载时获取所有头条分类
  getHeadlineTypeList();
});

// 点击切换高亮的回调（排他思想）
const highlightHandler = (index) => {
  headlineTypeList.value.forEach((item, i) => {
    if (i === index) {
      item.isHihlight = true;
      mittBus.emit('tid', item.tid);
    } else {
      item.isHihlight = false;
    }
  });
};

const keywords = ref('');
watch(keywords, (newValue) => {
  mittBus.emit('keywords', newValue);
});

const userInfoStore = useUserInfoStore();
const nickName = ref('');
onUpdated(async () => {
  await userInfoStore.getUserInfoAction();
  const userInfo = cloneDeep(userInfoStore.userInfo);
  nickName.value = userInfo.nickName;
});

// 点击登录的回调
const toLogin = () => {
  router.push({ name: 'Login' });
};

// 点击注册的回调
const toRegister = () => {
  router.push({ name: 'Register' });
};

// 点击退出登录的回调
const toLogout = () => {
  removeToken();
  userInfoStore.resetUserInfoAction();
  nickName.value = '';
  router.go({ name: 'Headlines' });
};

// 点击发布头条的回调
const toAddOrModifyHeadline = async () => {
  // 发送请求判断用户是否 Token 过期
  // 如果 Token 过期，会通过 ElMessage 提示登录已过期
  await checkLogin();
  router.push({ name: 'AddOrModifyHeadline' });
};
</script>

<style lang="scss" scoped>
.header-container {
  width: 100%;
  height: 60px;
  background: #212529;
  display: flex;
  justify-content: space-around;

  .left {
    ul {
      display: flex;

      li {
        list-style: none;
        margin-left: 20px;

        a:-webkit-any-link {
          text-decoration: none;
          color: #59646b;

          &.active {
            color: #c0adab;
          }
        }
      }
    }
  }

  .right {
    line-height: 60px;
    display: flex;
    flex-wrap: nowrap;

    .right-input {
      display: flex;
      align-items: center;

      :deep(.el-input__inner) {
        height: 30px;
        width: 150px;
      }
    }

    .btn-dropdown {
      display: flex;
      align-items: center;
    }

    .container-button {
      display: flex;
      align-items: center;
    }

    :deep(.el-button) {
      margin: 0 0 0 10px;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
}

.el-dropdown {
  vertical-align: top;
  width: 100px;
}

.el-dropdown+.el-dropdown {
  margin-left: 15px;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.example-showcase .el-dropdown+.el-dropdown {
  margin-left: 15px;
}

.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>
