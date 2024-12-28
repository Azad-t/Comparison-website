<template>
  <div class="welcome-page">
    <!-- 导航栏 -->
    <el-header class="navbar">
      <div class="navbar-content">
        <span class="logo">商品比价网</span>
        <div class="nav-buttons">
          <el-button type="primary" @click="goToRegister">注册</el-button>
          <el-button type="success" @click="goToLogin">登录</el-button>
        </div>
      </div>
    </el-header>

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

    <!-- 下方自由发挥内容 -->
    <div class="content-section">
      <h2>热门商品推荐</h2>
      <el-row :gutter="20">
        <el-col :span="8" v-for="(product, index) in products" :key="index">
          <el-card class="product-card">
            <img :src="product.image" class="product-image" alt="Product Image" />
            <div class="product-info">
              <h3>{{ product.name }}</h3>
              <p class="price">{{ product.price }}</p>
              <el-button type="primary" size="small">查看详情</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
          price: 'HK$7900'
        },
        {
          image: 'https://www.sennheiser-hearing.com/_next/image/?url=https%3A%2F%2Fmedia.graphassets.com%2F5F31ijhdThKXZDDYNeuD&w=1920&q=75',
          name: '无线耳机',
          price: '¥799'
        },
        {
          image: 'https://www.apple.com.cn/mac/home/images/overview/augment/world_mac_iphone__mr1xfuchl56e_xlarge.jpg',
          name: '笔记本电脑',
          price: '¥12999+'
        }
      ]
    };
  },
  methods: {
    goToRegister() {
      this.$router.push('/register');
    },
    goToLogin() {
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
.welcome-page {
  font-family: Arial, sans-serif;
}

.navbar {
  background-color: #409eff;
  color: white;
  padding: 0 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.navbar-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
  height: 60px;
}

.logo {
  font-size: 24px;
  font-weight: bold;
}

.nav-buttons .el-button {
  margin-left: 10px;
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
  height: 400px; /* 进一步增大图片高度 */
  object-fit: cover;
}

.product-info {
  padding: 20px;
  text-align: center;
}

.product-info h3 {
  font-size: 24px; /* 进一步增大标题字体 */
  color: #333;
  margin-bottom: 15px;
}

.product-info .price {
  font-size: 22px; /* 进一步增大价格字体 */
  color: #409eff;
  margin-bottom: 20px;
}

.product-info .el-button {
  width: 100%;
  font-size: 16px;
  padding: 12px 0;
}
</style>