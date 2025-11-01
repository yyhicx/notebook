<template>
  <div class="headline-detail-container">
    <div>
      <h4>{{ headlineDetail.title }}</h4>
    </div>
    <div style="margin-right: 250px">
      <span>{{ headlineDetail.typeName }}</span>
      <span>{{ headlineDetail.pageViews }}浏览</span>
      <span>{{ headlineDetail.pastHours }}小时前</span>
    </div>

    <div style="width: 500px; margin: 20px 0px 0px 70px">
      <p>
        {{ headlineDetail.article }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { defineComponent, onMounted, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { findHeadlineDetail } from '@/api';

defineComponent({
  name: 'HeadlineDetail',
});

const route = useRoute();

const headlineDetail = reactive({
  hid: null,
  title: '',
  article: '',
  type: null,
  typeName: '',
  pageViews: '',
  pastHours: '',
  publisher: null,
  author: '',
});

const initHeadlineDetail = async () => {
  const result = await findHeadlineDetail(route.params.hid);
  Object.assign(headlineDetail, result.headlineDetail);
};

onMounted(() => {
  initHeadlineDetail();
});
</script>

<style lang="scss" scoped>
.headline-detail-container {
  width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;

  div {
    span {
      padding-right: 15px;
      font-size: 14px;
      color: #8d91aa;
    }

    p {
      font-size: 14px;
      color: #2b2e30;
    }
  }
}
</style>
