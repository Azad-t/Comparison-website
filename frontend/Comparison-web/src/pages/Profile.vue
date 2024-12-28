<template>
    <div class="profile-container">
      <el-card class="profile-card">
        <h1 class="profile-title">个人信息</h1>
        <div class="profile-info">
          <p><strong>用户名：</strong>{{ userInfo.username }}</p>
          <p><strong>邮箱：</strong>{{ userInfo.email }}</p>
        </div>
        <div class="profile-actions">
          <el-button type="primary" @click="$router.push('/dashboard')">返回</el-button>
          <el-button type="danger" @click="handleLogout">登出</el-button>
        </div>
      </el-card>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        userInfo: {
          id: '',
          username: '',
          password: '',
          email: '',
        }
      };
    },
    created() {
      const token = localStorage.getItem('userprofile');
      console.log('token: ', token);
      if (token) {
        try {
          // 尝试将获取到的数据解析为JSON格式，如果成功说明格式正确，可以使用
          const parsedToken = JSON.parse(token);
          if (parsedToken.id) {
            this.userInfo = parsedToken;
          } else {
            // 如果解析后的数据不符合预期（比如没有id属性），进行相应处理
            this.$router.push('/login');
            this.$message.error('用户信息格式不正确，请先登录');
          }
        } catch (error) {
          // 如果解析JSON数据失败，说明数据格式有问题，进行相应处理
          console.error('解析用户信息数据失败：', error);
          this.$router.push('/login');
          this.$message.error('请先登录，用户信息读取错误');
        }
      } else {
        this.$router.push('/login');
        this.$message.error('请先登录');
      }
    },
    methods: {
      handleLogout() {
        localStorage.removeItem('authToken');
        localStorage.removeItem('userprofile');
        this.$router.push('/login');
        this.$message.success('已登出');
      },
    },
  };
  </script>
  
  <style scoped>
  .profile-container {
    display: flex;
    justify-content: center;
    align-items: flex-start; /* 将卡片定位在页面中上部分 */
    padding-top: 100px; /* 增加顶部间距，使卡片居中偏上 */
    min-height: 100vh;
    background-color: transparent; /* 背景颜色改为透明 */
  }
  
  .profile-card {
    width: 400px;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* 卡片阴影效果 */
    background-color: white;
  }
  
  .profile-title {
    font-size: 24px;
    font-weight: bold;
    color: #303133; /* 标题颜色 */
    text-align: center;
    margin-bottom: 20px;
  }
  
  .profile-info {
    font-size: 16px;
    color: #606266; /* 信息文字颜色 */
    margin-bottom: 30px;
  }
  
  .profile-info p {
    margin: 10px 0;
  }
  
  .profile-actions {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
  }
  
  .el-button {
    width: 48%; /* 按钮宽度 */
    font-size: 16px;
    padding: 12px 0;
    border-radius: 6px;
    transition: background-color 0.3s ease;
  }
  
  .el-button--primary {
    background-color: #409eff;
    border-color: #409eff;
    color: white;
  }
  
  .el-button--primary:hover {
    background-color: #66b1ff;
    border-color: #66b1ff;
  }
  
  .el-button--danger {
    background-color: #f56c6c;
    border-color: #f56c6c;
    color: white;
  }
  
  .el-button--danger:hover {
    background-color: #ff7875;
    border-color: #ff7875;
  }
  </style>