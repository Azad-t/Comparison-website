<template>
  <div id="dashboard" :class="{ 'auth-background': isAuthPage }">
    <el-container>
      <el-header v-if="!isAuthPage" class="custom-header">
        <div class="logo">Comparison Site</div>
        <el-input placeholder="搜索商品..." v-model="searchquery" class="search-bar" clearable></el-input>
        <el-button type="primary" @click="handleSearch">搜索</el-button>
        <nav class="nav-links">
          <router-link to="/">首页</router-link>
          <router-link to="/pricehistory">历史价格</router-link>
          <router-link to="/login">登录</router-link>
          <router-link to="/register">注册</router-link>
          <router-link to="/product/:id">商品详情</router-link>
          <router-link to="/search">搜索</router-link>
        </nav>
      </el-header>

      <el-main>
        <router-view />
        <section v-if="!isAuthPage" class="featured-products">
          <h2>推荐商品</h2>
          <div class="product-grid">
            <el-card v-for="n in 12" :key="n" class="product-card" shadow="hover">
              <img src="https://via.placeholder.com/200" alt="商品图片" />
              <h3>商品名称 {{ n }}</h3>
              <p>￥价格</p>
              <el-button type="success">加入购物车</el-button>
            </el-card>
          </div>
        </section>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import api from '@/api/api';
export default {
  name: 'dashboard-page',
  computed: {
    isAuthPage() {
      return this.$route.path === '/login' || this.$route.path === '/register';
    }
  },
  data() {
    return {
      searchquery: '',
      products: []
    };
  },
  methods: {
    handleSearch() {
    // 使用 this.$router.push 跳转到搜索页面，并带上搜索关键词作为参数
    this.$router.push({ path: '/search', query: { query: this.searchquery } });
  }
  }
};
</script>

<style scoped>
#dashboard {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  /*background-image: url('https://s21.ax1x.com/2024/11/14/pAg74U0.png');*/

  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-color: rgba(231, 238, 207, 0.288);
}

.auth-background {
  /*background-image: url('https://s21.ax1x.com/2024/11/14/pAg74U0.png')；*/
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-color: rgba(236, 247, 245, 0.925);
}

.custom-header {
  background-color: #faf0f085;
  padding: 10px 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #ddd;
}

.logo {

  font-size: 28px;
  font-weight: bold;
}

.search-bar {
  margin-right: 10px;
  width: 300px;
}

.nav-links {
  display: flex;
  gap: 15px;
}

.featured-products {
  margin: 20px 0;
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
  max-width: 100%;
  border-radius: 4px;
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
