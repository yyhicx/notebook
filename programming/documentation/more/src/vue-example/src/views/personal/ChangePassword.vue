<script setup>
import { ref, reactive } from 'vue';
import { useI18n } from 'vue-i18n';
import { ElMessage } from 'element-plus';
import { changePassword } from '@/api/personal';

const { t } = useI18n();
const formRef = ref();
const form = reactive({
  password: '',
  newPassword: '',
  confirmPassword: ''
});

const rules = computed(() => {
  return {
    password: {
      required: true,
      min: 6,
      message: t('form.passwordError'),
      trigger: 'blur'
    },
    newPassword: [
      {
        required: true,
        min: 6,
        message: t('form.passwordError'),
        trigger: 'blur'
      }
    ],
    confirmPassword: [
      {
        required: true,
        min: 6,
        message: t('form.passwordError'),
        trigger: 'blur'
      },
      {
        validator: (rule, value, callback) => {
          if (value !== form.newPassword) {
            callback(new Error(t('form.confirmPasswordError')));
          } else {
            callback();
          }
        },
        trigger: 'blur'
      }
    ]
  };
});

function submit() {
  formRef.value.validate((valid) => {
    if (!valid) return;
    const { password, newPassword } = form;
    changePassword({ password, newPassword }).then(() => {
      ElMessage.success(t('tips.success'));
      formRef.value.resetFields();
    });
  });
}
</script>

<template>
  <el-form
    ref="formRef"
    :model="form"
    :rules="rules"
    label-width="150px"
    class="form"
  >
    <el-form-item :label="t('form.currentPassword')" prop="password">
      <el-input
        v-model="form.password"
        type="password"
        :placeholder="t('form.currentPassword')"
      ></el-input>
    </el-form-item>
    <el-form-item :label="t('form.newPassword')" prop="newPassword">
      <el-input
        v-model="form.newPassword"
        type="password"
        :placeholder="t('form.newPassword')"
      ></el-input>
    </el-form-item>
    <el-form-item :label="t('form.confirmPassword')" prop="confirmPassword">
      <el-input
        v-model="form.confirmPassword"
        type="password"
        :placeholder="t('form.confirmPassword')"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" class="w100p" @click="submit">{{
        t('action.submit')
      }}</el-button>
    </el-form-item>
  </el-form>
</template>

<style lang="scss" scoped>
.form {
  width: 450px;
}
</style>
