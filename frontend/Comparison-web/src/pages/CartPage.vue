<template>
  <el-container>
    <!-- 顶部标题导航栏 -->
    <el-header class="custom-header">
      <div class="header-content">
        <h2 class="header-title">购物车</h2>
        <nav class="nav-links">
          <router-link to="/dashboard" class="nav-link">首页</router-link>
          <router-link to="/search" class="nav-link">返回搜索</router-link>
        </nav>
      </div>
    </el-header>

    <el-main>
      <!-- 购物车商品列表 -->
      <div class="cart-grid">
        <el-card v-for="item in cartItems" :key="item.id" class="cart-item" shadow="hover">
          <img :src="item.product.imageUrl" alt="商品图片" class="cart-item-image" />
          <div class="cart-item-details">
            <h3>{{ item.product.name }}</h3>
            <p>价格：￥{{ item.product.price }}</p>
            <p>来源：{{ item.product.platform }}</p>
          </div>
          <div class="cart-item-actions">
            <el-button type="text" @click="goToDetail(item.product.fromUrl)">查看详情</el-button>
            <el-button type="danger" @click="removeFromCart(item.id)">移除</el-button>
            <el-button type="text" @click="viewPriceHistory(item.product.id)">历史价格</el-button>
          </div>
        </el-card>
      </div>

      <!-- 购物车底部结算部分 -->
      <div class="cart-footer">
        <h3>总价：￥{{ totalPrice }}</h3>
        <el-button type="primary" size="large">去结算</el-button>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import api from '@/api/api';
export default {
  data() {
    return {
      cartItems: [],
      userInfo: {
        id: '',
        username: '',
        password: '',
        email: '',
      }
    };
  },
  computed: {
    totalPrice() {
      return this.cartItems.reduce((sum, item) => sum + item.product.price, 0);
    }
  },
  created() {
    this.fetchCartItems();
  },
  methods: {
    async fetchCartItems() {
      try {
        const token = localStorage.getItem('userprofile');
        const parsedToken = JSON.parse(token);
        this.userInfo = parsedToken;
        const userId = this.userInfo.id;
        const response = await api.get(`/api/cart?userId=${userId}`);
        this.cartItems = response.data;
        console.log('购物车数据：', this.cartItems);
      } catch (error) {
        console.error('获取购物车数据失败：', error);
      }
    },
    async removeFromCart(itemId) {
      try {
        await api.delete(`/api/cart/${itemId}`);
        this.$message.success('已从购物车移除');
        this.$router.go(0); // 刷新购物车列表
      } catch (error) {
        console.error('移除失败：', error);
      }
    },
    goToDetail(url) {
      console.log('跳转到商品详情页：', url);
      window.open(url, '_blank');
    },
    viewPriceHistory(productId) {
      this.$router.push({
        path: '/pricehistory',
        query: { productId }
      });
    }
  }
};
</script>

<style scoped>
.custom-header {
  background-color: #faf0f085;
  padding: 15px 20px;
  border-bottom: 1px solid #ddd;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
}

.header-title {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
  margin: 0; /* 去除默认的 margin */
}

.nav-links {
  display: flex;
  gap: 20px;
  align-items: center; /* 确保导航链接垂直居中 */
}

.nav-link {
  text-decoration: none;
  color: #606266;
  font-size: 16px;
  transition: color 0.3s ease;
  padding: 8px 12px; /* 增加内边距，使链接更易点击 */
  border-radius: 4px; /* 添加圆角 */
}

.nav-link:hover {
  color: #409eff;
  background-color: rgba(64, 158, 255, 0.1); /* 悬停时添加背景色 */
}

.cart-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
  padding: 20px;
}

.cart-item {
  display: flex;
  align-items: center;
  gap: 15px;
  padding: 15px;
  border-radius: 8px;
  background-color: white;
}

.cart-item-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 8px;
}

.cart-item-details {
  flex: 1;
}

.cart-item-details h3 {
  font-size: 18px;
  color: #303133;
  margin-bottom: 10px;
}

.cart-item-details p {
  font-size: 14px;
  color: #606266;
  margin-bottom: 5px;
}

.cart-item-actions {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.cart-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background-color: #f9f9f9;
  border-top: 1px solid #ddd;
  border-radius: 8px;
  margin-top: 20px;
}

.cart-footer h3 {
  font-size: 20px;
  color: #303133;
}

.el-button--primary {
  background-color: #409eff;
  border-color: #409eff;
  color: white;
  transition: background-color 0.3s ease;
}

.el-button--primary:hover {
  background-color: #66b1ff;
  border-color: #66b1ff;
}

.el-button--danger {
  background-color: #f56c6c;
  border-color: #f56c6c;
  color: white;
  transition: background-color 0.3s ease;
}

.el-button--danger:hover {
  background-color: #ff7875;
  border-color: #ff7875;
}

.el-button--text {
  color: #409eff;
  transition: color 0.3s ease;
}

.el-button--text:hover {
  color: #66b1ff;
}
</style>