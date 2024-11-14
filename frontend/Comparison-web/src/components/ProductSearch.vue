<template>
    <div class="product-search">
      <input v-model="searchTerm" type="text" placeholder="输入商品名称进行查询" @keyup.enter="handleSearch" />
      <button @click="handleSearch">查询</button>
      <ul>
        <li v-for="product in searchResults" :key="product.id">
          <router-link :to="`/product/${product.id}`">{{ product.name }}</router-link>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { searchProducts } from '@/api/product';
  export default {
    data() {
      return {
        searchTerm: '',
        searchResults: []
      };
    },
    methods: {
      async handleSearch() {
        this.searchResults = await searchProducts(this.searchTerm);
      }
    }
  };
  </script>
  
  <style scoped>
  .product-search {
    display: flex;
    flex-direction: column;
    gap: 10px;
  }
  </style>