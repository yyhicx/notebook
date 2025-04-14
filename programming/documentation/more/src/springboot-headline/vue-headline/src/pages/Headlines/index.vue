<template>
  <div class="headlines-container">
    <!-- 每一项头条 -->
    <div class="headline-container" v-for="item in pageData" :key="item.hid">
      <div>
        <span class="text">{{ item.title }}</span>
      </div>
      <div class="detail">
        <span> {{ headlineTypesStore.getTnameAction(item.type) }} </span>
        <span> {{ item.pageViews }} 浏览 </span>
        <span> {{ item.pastHours }} 小时前 </span>
      </div>

      <div>
        <el-button
          @click="toHeadlineDetail(item.hid)"
          size="small"
          style="background: #198754; margin-left: 15px; color: #bbd3dc"
          >查看全文</el-button
        >

        <el-popconfirm
          v-if="item.publisher === uid"
          @confirm="handlerDelete(item.hid)"
          :title="`您确定要删除${item.title}吗？`"
        >
          <template #reference>
            <el-button size="small" style="background: #dc3545; color: #bbd3dc"
              >删除</el-button
            >
          </template>
        </el-popconfirm>

        <el-button
          @click="handleModify(item.hid)"
          v-if="item.publisher === uid"
          size="small"
          style="background: #212529; color: #bbd3dc"
          >修改</el-button
        >
      </div>
    </div>

    <!-- 分页器 -->
    <div style="margin-top: 20px">
      <el-pagination
        v-model:current-page="queryInfo.pageNum"
        v-model:page-size="queryInfo.pageSize"
        @size-change="getPageData"
        @current-change="getPageData"
        :page-sizes="[5, 7, 10]"
        background
        layout="prev, pager, next, ->, sizes, total"
        :total="totalSize"
      />
    </div>
  </div>
</template>

<script setup>
import {
  defineComponent,
  getCurrentInstance,
  ref,
  reactive,
  onMounted,
  watch,
} from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { clone, cloneDeep } from 'lodash';
import { findHeadlines, removeHeadlineByHid } from '@/api';
import { useHeadlineTypesStore } from '@/stores/headlineTypes';
import { useUserInfoStore } from '@/stores/userInfo';

defineComponent({
  name: 'Headlines',
});

const router = useRouter();
const { mittBus } = getCurrentInstance().appContext.config.globalProperties;

const userInfoStore = useUserInfoStore();
const uid = cloneDeep(userInfoStore.userInfo.uid);

// 查询条件
const queryInfo = reactive({
  keywords: '',
  type: null,
  pageNum: 1,
  pageSize: 5,
});
// 接收 Header 组件传来的用户搜索数据
mittBus.on('keywords', (keywords) => {
  queryInfo.keywords = keywords;
});
mittBus.on('tid', (tid) => {
  queryInfo.type = tid;
});
// 监视初始化参数的变化，当发生改变时重新发送请求获取列表数据
watch(
  queryInfo,
  () => {
    getPageData();
  },
  { deep: true }
);

// 总条数
const totalSize = ref(0);

// 列表数据
const pageData = ref([
  {
    hid: null,
    title: '',
    type: null,
    pageViews: null,
    pastHours: null,
    publisher: null,
  },
]);

// 所有头条分类
const headlineTypesStore = useHeadlineTypesStore();
const headlineTypeList = ref([]);
onMounted(async () => {
  await headlineTypesStore.getHeadlineTypesAction();
  headlineTypeList.value = cloneDeep(headlineTypesStore.headlineTypes);
});

// 初始化分页列表数据
const getPageData = async () => {
  const result = await findHeadlines(queryInfo);
  pageData.value = result.pageInfo.pageData;
  queryInfo.pageNum = result.pageInfo.pageNum;
  queryInfo.pageSize = result.pageInfo.pageSize;
  totalSize.value = result.pageInfo.totalSize;
};
onMounted(() => {
  getPageData();
});

// 点击查看头条详情
const toHeadlineDetail = (hid) => {
  router.push({ name: 'HeadlineDetail', params: { hid } });
};

// 点击删除头条
const handlerDelete = async (hid) => {
  await removeHeadlineByHid(hid);
  ElMessage.success('删除成功');
  getPageData();
};

// 点击修改头条
const handleModify = (hid) => {
  router.push({ name: 'AddOrModifyHeadline', query: { hid } });
};
</script>

<style lang="scss" scoped>
.headlines-container {
  width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;

  .headline-container {
    margin-top: 20px;
    border-radius: 10px;
    border: 2px solid #ebebeb;
    width: 600px;
    height: 120px;

    div {
      margin-top: 10px;
    }

    .text {
      margin-left: 15px;
      color: #353a3f;
    }

    .detail {
      span {
        margin-left: 15px;
        color: #8b778a;
        font-size: 14px;
      }
    }
  }
}
</style>
