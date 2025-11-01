<script setup>
import { ref, reactive, computed, watch } from 'vue';
import { useI18n } from 'vue-i18n';
import { ElMessage } from 'element-plus';
import { changeProfile } from '@/api/personal';
import { roles } from '@/mock/data';
import { useUserStore } from '@/store/user';

const { t } = useI18n();
const userStore = useUserStore();
const formRef = ref();
const form = reactive({
  name: '',
  roleId: ''
});
const profile = computed(() => userStore.userInfo);
const isSystemRole = computed(() => profile.value.createdBy === 'system');
const rules = computed(() => {
  return {
    roleId: { required: true, message: t('form.roleRequired'), trigger: 'blur' }
  };
});

watch(
  profile,
  () => {
    updateForm();
  },
  { immediate: true }
);

function updateForm() {
  form.name = profile.value.name;
  form.roleId = profile.value.roleId;
}

function submit() {
  formRef.value.validate((valid) => {
    if (!valid) return;
    changeProfile({ ...form }).then(() => {
      ElMessage.success(t('tips.success'));
    });
  });
}
</script>

<template>
  <el-form
    ref="formRef"
    :model="form"
    :rules="rules"
    label-width="80px"
    label-position="left"
  >
    <el-form-item :label="t('form.username')">
      <label>{{ form.name }}</label>
    </el-form-item>
    <el-form-item
      :label="t('form.group')"
      :prop="!isSystemRole ? 'roleId' : ''"
      style="width: 320px"
    >
      <el-select
        v-model="form.roleId"
        :disabled="isSystemRole"
        :placeholder="t('form.choose')"
        style="margin-right: 10px"
      >
        <el-option
          v-for="item in roles"
          :key="item.name"
          :label="item.label"
          :value="item.name"
        ></el-option>
      </el-select>
      <el-button v-if="!isSystemRole" type="primary" @click="submit">{{
        t('action.apply')
      }}</el-button>
    </el-form-item>
  </el-form>
</template>
