<template>
  <el-card class="login-card">
    <h2 class="login-title">用户登录</h2>
    <el-form @submit.prevent="handleLogin" :model="form" :rules="rules" ref="loginForm">
      <el-form-item label="用户名" prop="username" class="form-item">
        <el-input v-model="form.username" placeholder="请输入用户名" prefix-icon="el-icon-user"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password" class="form-item">
        <el-input v-model="form.password" type="password" placeholder="请输入密码" prefix-icon="el-icon-lock" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit" class="login-button">登录</el-button>
        <el-button type="text" @click="goToRegister" class="register-button">还没有账号？立即注册</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import api from '@/api/api';

export default {
  data() {
    return {
      form: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '密码长度至少为6位', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          api.post('/api/users/login', {
            username: this.form.username,
            password: this.form.password,
          }).then(response => {
            console.log("response: ", response.data);
            if (response.data) {
              localStorage.setItem('userprofile', JSON.stringify(response.data)); // 保存登录状态
              console.log("localStorage: ", localStorage.getItem('userprofile'));
              this.$message.success('登录成功');
              this.$router.push('/dashboard');
            } else {
              this.$message.error('用户名或密码错误');
            }
          }).catch(error => {
            console.log(error);
            this.$message.error('登录失败，请稍后再试');
          });
        } else {
          return false;
        }
      });
    },
    goToRegister() {
      this.$router.push('/register');
    }
  }
};
</script>

<style scoped>
.login-card {
  max-width: 500px; /* 增大卡片宽度 */
  margin: 100px auto; /* 调整卡片在页面中的位置 */
  padding: 30px; /* 增加内边距 */
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.login-title {
  text-align: center;
  margin-bottom: 30px; /* 增加标题与表单的间距 */
  font-size: 24px;
  color: #303133;
}

.form-item {
  display: flex;
  align-items: center; /* 使标签和输入框垂直居中 */
  margin-bottom: 20px; /* 增加表单项之间的间距 */
}

.form-item >>> .el-form-item__label {
  width: 80px; /* 固定标签宽度，使输入框对齐 */
  text-align: right; /* 标签右对齐 */
  padding-right: 10px; /* 标签与输入框的间距 */
}

.login-button {
  width: 100%;
  margin-top: 20px; /* 增加按钮与表单的间距 */
}

.register-button {
  width: 100%;
  text-align: center;
  margin-top: 10px;
}
</style>