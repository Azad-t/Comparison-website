<template>
  <div id="dashboard" :class="auth-background">
    <el-container>
      <!-- 导航栏 -->
      <el-header class="custom-header">
        <div class="logo">Comparison Site</div>
        <div class="search-container">
          <el-input placeholder="搜索商品..." v-model="searchquery" class="search-bar" clearable></el-input>
          <el-button type="primary" @click="handleSearch" class="search-button">搜索</el-button>
        </div>
        <nav class="nav-links">
          <!-- <router-link to="/pricehistory" class="nav-link">历史价格</router-link> -->
          <router-link to="/profile" class="nav-link">个人中心</router-link>
          <router-link to="/cart" class="nav-link">购物车</router-link>
        </nav>
        <el-button type="danger" @click="handleLogout" class="logout-button">登出</el-button>
      </el-header>

      <el-main>
        <router-view />

        <!-- 走马灯（轮播图） -->
        <el-carousel :interval="5000" arrow="always" height="800px" class="carousel">
          <el-carousel-item v-for="(item, index) in carouselItems" :key="index">
            <el-card class="carousel-card" :body-style="{ padding: '0px' }">
              <img :src="item.image" class="carousel-image" alt="Carousel Image" />
              <div class="carousel-content">
                <h3>{{ item.title }}</h3>
                <p>{{ item.description }}</p>
              </div>
            </el-card>
          </el-carousel-item>
        </el-carousel>

        <!-- 商品推荐部分 -->
        <div class="content-section">
          <h2>热门商品推荐</h2>
          <el-row :gutter="20">
            <el-col :span="8" v-for="(product, index) in products" :key="index">
              <el-card class="product-card">
                <img :src="product.image" class="product-image" alt="Product Image" />
                <div class="product-info">
                  <h3>{{ product.name }}</h3>
                  <p class="price">{{ product.price }}</p>
                  <el-button type="primary" size="small" @click="goToDetail(product.fromUrl)">查看详情</el-button>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import api from '@/api/api';

export default {
  name: 'dashboard-page',
  data() {
    return {
      searchquery: '',
      // 走马灯内容
      carouselItems: [
        {
          image: 'https://www.tissotwatches.com/dw/image/v2/BKKD_PRD/on/demandware.static/-/Library-Sites-Tissot-SharedLibrary/default/dw9bc6ffaa/1-HOMEPAGE/2-IMAGE-TILE/DESKTOP/Tis-Digital-Gifting-4-HP-Collection-Banner-Desktop_vdef.jpg',
          title: '发现最优惠的价格',
          description: '轻松对比各大平台商品价格，省钱更省心！'
        },
        {
          image: 'https://www.sennheiser-hearing.com/_next/image/?url=https%3A%2F%2Fmedia.graphassets.com%2F0yFu1AjgSzGcqLrOsIoF&w=1920&q=75',
          title: '智能推荐',
          description: '根据你的需求，智能推荐最合适的商品！'
        },
        {
          image: 'https://canali.vtexassets.com/assets/vtex.file-manager-graphql/images/b7e455b7-4045-4972-82e9-f1d3e5291bde___ec87a1787a24c7d31a0a3992874429ca.jpg',
          title: '购物无忧',
          description: '享受便捷的购物体验，全程无忧！'
        }
      ],
      // 热门商品
      products: [
        {
          image: 'https://assets.tissotwatches.com/transform/Extend/ee3811c6-6d9e-44e1-889f-b0e6182bbc36/T137-907-97-201-00_Shadow?io=transform:fill,width:800,height:800,gravity:center',
          name: '经典腕表',
          price: 'HK$7900',
          fromUrl: 'https://www.tissotwatches.com/zht-hk/T1379079720100.html'
        },
        {
          image: 'https://www.sennheiser-hearing.com/_next/image/?url=https%3A%2F%2Fmedia.graphassets.com%2F5F31ijhdThKXZDDYNeuD&w=1920&q=75',
          name: '无线耳机',
          price: '¥799',
          fromUrl: 'https://www.sennheiser-hearing.com/zh-CN/p/hd-450bt/ckykejk3k0av30b47bi9hc68f/'
        },
        {
          image: 'https://www.apple.com.cn/mac/home/images/overview/augment/world_mac_iphone__mr1xfuchl56e_xlarge.jpg',
          name: '笔记本电脑',
          price: '¥12999+',
          fromUrl: 'https://www.apple.com.cn/macbook-pro/'
        }
      ]
    };
  },
  methods: {
    handleSearch() {
      // 使用 this.$router.push 跳转到搜索页面，并带上搜索关键词作为参数
      this.$router.push({ path: '/search', query: { query: this.searchquery } });
    },
    handleLogout() {
      localStorage.removeItem('authToken'); // 清除登录状态
      this.$router.push('/login');
      this.$message.success('已登出');
    },
    goToDetail(url) {
      window.open(url, '_blank');
    },
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
  background-color: rgba(231, 238, 207, 0.288);
}

.auth-background {
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

.search-button {
  background-color: #409eff;
  border-color: #409eff;
  color: white;
  transition: background-color 0.3s ease;
}

.search-button:hover {
  background-color: #66b1ff;
  border-color: #66b1ff;
}

.nav-links {
  display: flex;
  gap: 20px;
  margin-right: 10px;
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

.logout-button {
  background-color: #f56c6c;
  border-color: #f56c6c;
  color: white;
  transition: background-color 0.3s ease;
}

.logout-button:hover {
  background-color: #ff7875;
  border-color: #ff7875;
}

.carousel {
  margin-top: 20px;
}

.carousel-card {
  border-radius: 10px;
  overflow: hidden;
}

.carousel-image {
  width: 100%;
  height: 800px;
  object-fit: cover;
}

.carousel-content {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 20px;
  text-align: center;
}

.carousel-content h3 {
  margin: 0;
  font-size: 24px;
}

.carousel-content p {
  margin: 10px 0 0;
  font-size: 16px;
}

.content-section {
  max-width: 1200px;
  margin: 40px auto;
  padding: 0 20px;
}

.content-section h2 {
  font-size: 28px;
  color: #333;
  margin-bottom: 20px;
}

.product-card {
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.product-card:hover {
  transform: translateY(-5px);
}

.product-image {
  width: 100%;
  height: 300px;
  object-fit: cover;
}

.product-info {
  padding: 20px;
  text-align: center;
}

.product-info h3 {
  font-size: 24px;
  color: #333;
  margin-bottom: 15px;
}

.product-info .price {
  font-size: 22px;
  color: #409eff;
  margin-bottom: 20px;
}

.product-info .el-button {
  width: 100%;
  font-size: 16px;
  padding: 12px 0;
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