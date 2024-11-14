<template>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="username">用户名</label>
        <input v-model="form.username" type="text" id="username" required />
      </div>
      <div>
        <label for="password">密码</label>
        <input v-model="form.password" type="password" id="password" required minlength="6" />
      </div>
      <button type="submit">登录</button>
    </form>
  </template>
  
  <script>
  export default {
    data() {
      return {
        form: {
          username: '',
          password: ''
        }
      };
    },
    methods: {
      async handleLogin() {
        try {
          // 调用 API 进行登录
          await this.$store.dispatch('loginUser', this.form);
          this.$router.push('/dashboard');
        } catch (error) {
          alert('登录失败，请重试');
        }
      }
    }
  };
  </script>
  
  <style scoped>
  form {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }
  </style>