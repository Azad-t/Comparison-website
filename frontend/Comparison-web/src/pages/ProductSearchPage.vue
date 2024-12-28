<template>
    <el-container>
      <!-- 导航栏 -->
      <el-header class="custom-header">
        <div class="logo">Comparison Site</div>
        <div class="search-container">
          <el-input placeholder="请输入商品名..." v-model="searchquery" class="search-bar" clearable></el-input>
          <el-button type="primary" @click="handleSearch" class="search-btn">搜索</el-button>
        </div>
        <nav class="nav-links">
          <router-link to="/dashboard" class="nav-link">首页</router-link>
        </nav>
      </el-header>
  
      <!-- 商品展示部分 -->
      <div class="product-grid">
        <el-card v-for="items in searchResults" :key="items.id" class="product-card" shadow="hover">
          <img :src="items.imageUrl" alt="商品图片" />
          <h3>商品名称 {{ items.name }}</h3>
          <p>￥价格 {{ items.price }}</p>
          <p>来源 {{ items.platform }}</p>
          <el-button type="success" @click="addToCart(items.id)">加入购物车</el-button>
          <el-button type="text" @click="goToDetail(items.fromUrl)">查看详情</el-button>
        </el-card>
      </div>
    </el-container>
  </template>
  
  <script>
  import api from '@/api/api';
  export default {
    data() {
      return {
        searchquery: '',
        searchResults: [],
        userInfo: {
          id: '',
          username: '',
          password: '',
          email: '',
        }
      };
    },
    created() {
      // 组件创建时，使用路由参数执行搜索
      this.searchquery = this.$route.query.query || '';
      this.handleSearch();
    },
    methods: {
      async handleSearch() {
        try {
          const response = await api.get(`/api/products/search?name=${this.searchquery}`);
          this.searchResults = response.data;
          console.log('搜索结果： ', this.searchResults);
        } catch (error) {
          console.log('搜索失败： ', error);
        }
      },
      goToDetail(url) {
        window.open(url, '_blank');
      },
      async addToCart(productId) {
        try {
          console.log('添加到购物车：', productId);
          const token = localStorage.getItem('userprofile');
          const parsedToken = JSON.parse(token);
          this.userInfo = parsedToken;
          await api.post('/api/cart', { userId: this.userInfo.id, productId });
          this.$router.push('/cart'); // 跳转至购物车页面
        } catch (error) {
          console.error('添加到购物车失败：', error);
        }
      }
    },
    watch: {
      // 监听路由变化，当搜索关键词变化时重新执行搜索
      '$route.query.query': function (newQuery) {
        this.searchquery = newQuery;
        this.handleSearch();
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
  
  .product-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 20px;
    margin-top: 20px; /* 增加导航栏与商品展示部分的间隙 */
  }
  
  .product-card {
    text-align: center;
    padding: 10px;
    background-color: white;
    border-radius: 8px;
    overflow: hidden;
  }
  
  .product-card img {
    width: 100%;
    height: auto;
    object-fit: cover;
    display: block;
  }
  
  .custom-header {
    background-color: #faf0f085;
    padding: 10px 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #ddd;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  }
  
  .logo {
    font-size: 28px;
    font-weight: bold;
    color: #409eff;
  }
  
  .search-container {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-grow: 1;
    margin: 0 20px;
  }
  
  .search-bar {
    width: 400px;
    margin-right: 10px;
  }
  
  .search-btn {
    background-color: #409eff;
    border-color: #409eff;
    color: white;
    transition: background-color 0.3s ease;
  }
  
  .search-btn:hover {
    background-color: #66b1ff;
    border-color: #66b1ff;
  }
  
  .nav-links {
    display: flex;
    gap: 20px;
  }
  
  .nav-link {
    text-decoration: none;
    color: #333;
    font-size: 16px;
    transition: color 0.3s ease;
  }
  
  .nav-link:hover {
    color: #409eff;
  }
  
  @media screen and (max-width: 768px) {
    .search-bar {
      width: 200px;
    }
  
    .product-grid {
      grid-template-columns: 1fr;
    }
  }
  </style>