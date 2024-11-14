<template>
    <div class="login-container">
      <LoginForm />
    </div>
  </template>
  
  <script>
  import LoginForm from '@/components/LoginForm.vue';
  export default {
    name: 'LoginPage',
    components: {
      LoginForm
    }
  };
  </script>
  
  <style scoped>
  .login-container {
    display: flex;
    justify-content: center;
    padding: 20px;
  }
  </style>