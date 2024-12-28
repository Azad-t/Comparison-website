<template>
    <el-container>
        <el-header class="custom-header">
          <el-input placeholder="请输入商品名..." v-model="searchquery" class="search-bar" clearable></el-input>
          <el-button type="primary" @click="handleSearch" class="search-btn">搜索</el-button>
          <nav class="nav-links">
          <router-link to="/dashboard">首页</router-link>
        </nav>
        </el-header>
    
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
      // console.log('搜索成功： ', response);
      // console.log('搜索成功： ', response.data);
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
      await api.post('/api/cart', {userId: this.userInfo.id, productId});
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
}
.product-card {
  text-align: center;
  padding: 10px;
  background-color: white;
  border-radius: 8px;
  overflow: hidden;
}
.product-card img {
  width: 100%; /* 使图像宽度适应容器 */
  height: auto; /* 保持图像的纵横比 */
  object-fit: cover; /* 确保图像覆盖整个容器，可能会裁剪 */
  display: block; /* 去除图像下方的空白空间 */
}
.custom-header {
  background-color: #faf0f085;
  padding: 10px 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid #ddd;
}
.search-bar {
  margin-right: 10px;
  width: 300px;
}
.search-btn {
  background-color: #409eff;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  margin-left: 0px;
}
.nav-links {
  display: flex;
  gap: 20px;
  margin-right: 10px;
}

  </style>