<template>
  <div class="login-container">
    <el-form
      :rules="rules"
      :model="loginForm"
      ref="formRef"
      label-width="80px"
      class="login-form"
    >
      <h2>用户登录</h2>
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="loginForm.username"
          autocomplete="off"
          placeholder="请输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          v-model="loginForm.password"
          autocomplete="off"
          placeholder="请输入密码"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click.native.prevent="login">登录</el-button>
        <el-button type="primary" @click="toRegister">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { useUserInfoStore } from '@/stores/userInfo';

const router = useRouter();

// 校验器
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

// 校验规则
const rules = {
  username: [{ required: true, trigger: 'blur', validator: validateUsername }],
  password: [{ required: true, trigger: 'blur', validator: validatePassword }],
};

const loginForm = reactive({
  username: '',
  password: '',
});

const formRef = ref();

const userInfoStore = useUserInfoStore();
// const loading = ref(false);
// 点击登录
const login = async () => {
  // 验证表单
  await formRef.value?.validate();
  // loading.value = true;
  try {
    // 进行登录
    await userInfoStore.loginAction(loginForm);
    router.push({ name: 'Headlines' });
  } finally {
    // 登录失败
    // loading.value = false;
  }
};

// 点击去注册页面
const toRegister = () => {
  router.push({ name: 'Register' });
};
</script>

<style lang="scss" scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;

  .login-form {
    width: 400px;
    text-align: center;
  }
}
</style>
