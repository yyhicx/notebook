<template>
  <el-card class="add-headline-container">
    <el-form :rules="rules" :model="formData" ref="formRef" size="default">
      <el-form-item label="文章标题" prop="title">
        <el-input v-model="formData.title" placeholder="请输入标题"></el-input>
      </el-form-item>
      <el-form-item style="margin: 50px 0" label="文章内容" prop="article">
        <el-input
          v-model="formData.article"
          type="textarea"
          :rows="8"
          placeholder="请输入内容"
        ></el-input>
      </el-form-item>
      <el-form-item label="文章类型" prop="type">
        <el-select v-model="formData.type" placeholder="请选择类型">
          <el-option
            v-for="item in headlineTypeList"
            :key="item.tid"
            :label="item.tname"
            :value="item.tid"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="handlerCancel">取消</el-button>
        <el-button type="primary" @click="handlerSave">保存</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import { defineComponent, ref, reactive, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { cloneDeep } from 'lodash';
import {
  checkLogin,
  updateHeadline,
  publishHeadline,
  findHeadlineDetail,
} from '@/api';
import { useHeadlineTypesStore } from '@/stores/headlineTypes';

defineComponent({
  name: 'AddOrModifyHeadline',
});

const route = useRoute();
const router = useRouter();

// 验证器
const validateTitle = (rule, value, callback) => {
  if (value.length) {
    callback();
  } else {
    callback(new Error('文章标题是必须的'));
  }
};
const validateArticle = (rule, value, callback) => {
  if (value.length) {
    callback();
  } else {
    callback(new Error('文章内容是必须的'));
  }
};
const validateType = (rule, value, callback) => {
  if (typeof value === 'number') {
    callback();
  } else {
    callback(new Error('文章类型是必须的'));
  }
};

// 校验规则
const rules = {
  title: [{ required: true, trigger: 'blur', validator: validateTitle }],
  article: [{ required: true, trigger: 'blur', validator: validateArticle }],
  type: [{ required: true, trigger: 'blur', validator: validateType }],
};

const formData = reactive({
  hid: null,
  title: '',
  article: '',
  type: '',
});

const formRef = ref();

const headlineTypesStore = useHeadlineTypesStore();
const headlineTypeList = ref([]);
onMounted(async () => {
  await headlineTypesStore.getHeadlineTypesAction();
  const types = cloneDeep(headlineTypesStore.headlineTypes);
  headlineTypeList.value = types;
});

// 点击取消的回调
const handlerCancel = () => {
  router.back();
};

// 点击保存的回调
const handlerSave = async () => {
  // 校验表单
  await formRef.value?.validate();
  // 验证 Token
  await checkLogin();

  const obj = formData;
  // 让表单数据携带 hid
  obj.hid = route.query.hid;
  // 发送请求：根据 hid 是否存在来判断是修改还是新增
  if (route.query.hid) {
    await updateHeadline(obj);
    ElMessage.success('修改成功');
  } else {
    await publishHeadline(obj);
    ElMessage.success('添加成功');
  }

  // 返回首页
  router.push({ name: 'Headlines' });
};

// 如果是修改，那么路由会携带 hid 参数，并且需要从服务端获取该文章的信息，并回显到表单中
// 因为需要 types 的信息，所以该判断声明在最后
const clickModify = async () => {
  // 如果是添加，那么直接返回
  if (!route.query.hid) return;

  const result = await findHeadlineDetail(route.query.hid);
  formData.title = result.headlineDetail.title;
  formData.article = result.headlineDetail.article;
  formData.type = result.headlineDetail.type;
};
onMounted(() => {
  clickModify();
});
</script>

<style lang="scss" scoped>
.add-headline-container {
  width: 600px;
  margin: 150px auto;
}
</style>
