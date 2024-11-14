<template>
    <div class="dashboard-container">
      <h1>欢迎来到用户操作面板</h1>
      <ProductSearch />
    </div>
  </template>
  
  <script>
  import ProductSearch from '@/components/ProductSearch.vue';
  export default {
    name: 'DashboardPage',
    components: {
      ProductSearch
    }
  };
  </script>
  
  <style scoped>
  .dashboard-container {
    padding: 20px;
  }
  </style>