<template>
  <div class="register-container">
    <el-form
      :rules="rules"
      :model="registerForm"
      ref="formRef"
      label-width="80px"
      class="register-form"
    >
      <h2>用户注册</h2>
      <el-form-item label="昵称" prop="nickName">
        <el-input
          v-model="registerForm.nickName"
          autocomplete="off"
          placeholder="请输入昵称"
        ></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="registerForm.username"
          autocomplete="off"
          placeholder="请输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          v-model="registerForm.password"
          autocomplete="off"
          placeholder="请输入密码"
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input
          type="password"
          v-model="registerForm.confirmPassword"
          autocomplete="off"
          placeholder="请确认密码"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="register">注册</el-button>
        <el-button type="danger" @click="resetForm">重置</el-button>
        <el-button type="success" @click="toLogin">去登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { checkUsername, register as registerApi } from '@/api';

const router = useRouter();

// 校验器
const validateNickName = (rule, value, callback) => {
  if (value.length >= 2 && value.length <= 6) {
    callback();
  } else {
    callback(new Error('昵称长度在2-6位之间'));
  }
};
const validateUsername = (rule, value, callback) => {
  if (value.length < 4) {
    callback(new Error('用户名长度不能小于4位'));
  } else {
    callback();
  }
};
const validatePassword = (rule, value, callback) => {
  if (value.length < 6) {
    callback(new Error('密码长度不能小于6位'));
  } else {
    callback();
  }
};
const validateConfirmPassword = (rule, value, callback) => {
  if (value.length < 6) {
    callback(new Error('密码长度不能小于6位'));
  } else {
    callback();
  }
};

// 校验规则
const rules = {
  nickName: [{ required: true, trigger: 'blur', validator: validateNickName }],
  username: [{ required: true, trigger: 'blur', validator: validateUsername }],
  password: [{ required: true, trigger: 'blur', validator: validatePassword }],
  confirmPassword: [
    { required: true, trigger: 'blur', validator: validateConfirmPassword },
  ],
};

const registerForm = reactive({
  nickName: '',
  username: '',
  password: '',
  confirmPassword: '',
});

const formRef = ref();

// 点击注册
const register = async () => {
  // 校验表单
  await formRef.value?.validate();
  // 校验密码是否相等
  if (registerForm.password === registerForm.confirmPassword) {
    // 调用用户名校验接口
    await checkUsername(registerForm.username);

    const obj = {
      username: '',
      password: '',
      nickName: '',
    };
    obj.username = registerForm.username;
    obj.password = registerForm.password;
    obj.nickName = registerForm.nickName;

    // 调用注册接口
    await registerApi(obj);
    // 重置表单
    resetForm();
    ElMessage.success('注册成功');
  } else {
    ElMessage.error('密码和确定密码必须一致');
    return;
  }
};

// 点击重置表单
const resetForm = () => {
  formRef.value?.resetFields();
};

// 点击去登录
const toLogin = () => {
  router.push({ name: 'Login' });
};
</script>

<style lang="scss" scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;

  .register-form {
    width: 400px;
    text-align: center;
  }
}
</style>
