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
  import api from '@/api/api';

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
       handleLogin() {
        api.post('/api/users/login',{
            username: this.form.username,
            password: this.form.password,}
        ).then(response => {
          console.log("response: ", response.data);
          if (response.data) {
            this.$message.success('登录成功');
            this.$router.push('/dashboard');
          } else {
            alert('用户名或密码错误');
          }
        }).catch(error => {
          console.log(error);
          alert('登录失败，请稍后再试');
        });
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