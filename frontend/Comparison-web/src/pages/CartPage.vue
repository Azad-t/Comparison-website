<template>
    <el-container>
      <el-header class="custom-header">
        <h2>购物车</h2>
      </el-header>
  
      <el-main>
        <nav class="nav-links">
          <router-link to="/dashboard">首页</router-link>
          <router-link to="/search">返回搜索</router-link>
        </nav>
        <div class="cart-grid">
          <el-card v-for="item in cartItems" :key="item.id" class="cart-item" shadow="hover">
            <img :src="item.product.imageUrl" alt="商品图片" class="cart-item-image" />
            <div class="cart-item-details">
              <h3>{{ item.product.name }}</h3>
              <p>价格：￥{{ item.product.price }}</p>
              <p>来源：{{ item.product.platform }}</p>
            </div>
            <el-button type="text" @click="goToDetail(item.product.fromUrl)">查看详情</el-button>
            <el-button type="danger" @click="removeFromCart(item.id)">移除</el-button>
            <el-button type="text" @click="viewPriceHistory(item.product.id)" >历史价格</el-button>
          </el-card>
        </div>
  
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
    background-color: #409eff;
    color: white;
    padding: 15px;
    text-align: center;
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
  .cart-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px;
    background-color: #f9f9f9;
    border-top: 1px solid #ddd;
  }
  </style>
  