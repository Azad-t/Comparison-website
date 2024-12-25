<template>
    <div class="welcome-page">
      <h1>欢迎来到商品比价网站</h1>
      <p>在这里，你可以轻松对比各类商品价格，找到最实惠的选择哦！</p>
      <el-row>
        <el-button type="primary" @click="goToRegister">注册</el-button>
        <el-button type="success" @click="goToLogin">登录</el-button>
      </el-row>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      goToRegister() {
        // 这里可以配置具体的路由跳转逻辑，假设后续注册页面的路由名为'register'
        this.$router.push('/register');
      },
      goToLogin() {
        // 假设登录页面路由名为'login'
        this.$router.push('/login');
      }
    }
  };
  </script>
  
  <style scoped>
  .welcome-page {
    text-align: center;
    padding: 50px;
    background-color: #f8f9fa; /* 背景色，可自行调整 */
    border-radius: 10px; /* 圆角效果 */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  }
  
  h1 {
    color: #333; /* 标题颜色 */
  }
  
  p {
    color: #666; /* 段落文字颜色 */
    margin-bottom: 20px;
  }
  
  .el-row {
    justify-content: center; /* 按钮行水平居中 */
    margin-top: 30px;
  }
  
  .el-button {
    margin-right: 20px;
  }
  </style>