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
    display: flex;
    justify-content: center;
    padding: 20px;
  }
  </style>
