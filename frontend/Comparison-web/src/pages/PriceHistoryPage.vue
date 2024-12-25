<template>
    <div class="history-page">
      <router-link to="/dashboard">首页</router-link>
      <h2>商品历史价格</h2>
      <el-table :data="priceHistory" style="width: 100%">
        <el-table-column prop="id" label="ID" width="100"></el-table-column>
        <el-table-column prop="name" label="商品名称" width="200"></el-table-column>
        <el-table-column prop="date" label="日期" width="180"></el-table-column>
        <el-table-column prop="price" label="价格" width="180"></el-table-column>
      </el-table>
    </div>
  </template>
  
  <script>
  export default {
    name: 'PriceHistory',
    data() {
      return {
        priceHistory: [
          { date: '2024-10-01', price: '￥210' },
          { date: '2024-10-15', price: '￥205' },
          { date: '2024-11-01', price: '￥200' }
        ]
      };
    }
  };
  </script>
  
  <style scoped>
  .history-page {
    padding: 20px;
  }
  </style>
  