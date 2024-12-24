<template>
    <div class="register-container">
      <RegisterForm />
    </div>
  </template>
  
  <script>
  import RegisterForm from '@/components/RegisterForm.vue';
  export default {
    name: 'RegisterPage',
    components: {
      RegisterForm
    }
  };
  </script>
  
  <style scoped>
  .register-container {
    background-color: rgb(243, 243, 243);
    display: flex;
    justify-content: center;
    height: 100vh;
    align-items: center;
  }
  </style>
