<template>
  <div id="app" :class="{ 'auth-background': isAuthPage }">
    <header v-if="!isAuthPage">
      <div class="logo">Comparison Site</div>
      <div class="search-bar">
        <input type="text" placeholder="搜索商品..." />
        <button>搜索</button>
      </div>
      <nav>
        <router-link to="/">首页</router-link>
        <router-link to="/dashboard">操作面板</router-link>
        <router-link to="/login">登录</router-link>
        <router-link to="/register">注册</router-link>
      </nav>
    </header>
    <aside class="sidebar" v-if="!isAuthPage">
      <h3>分类导航</h3>
      <ul>
        <li><a href="#">电子产品</a></li>
        <li><a href="#">家居用品</a></li>
        <li><a href="#">服装配饰</a></li>
        <li><a href="#">运动户外</a></li>
      </ul>
    </aside>
    <main>
      <router-view />
      <section v-if="!isAuthPage" class="featured-products">
        <h2>推荐商品</h2>
        <div class="product-grid">
          <div class="product-card" v-for="n in 12" :key="n">
            <img src="https://via.placeholder.com/150" alt="商品图片" />
            <h3>商品名称 {{ n }}</h3>
            <p>￥价格</p>
            <button>加入购物车</button>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
export default {
  name: 'App',
  computed: {
    isAuthPage() {
      return this.$route.path === '/login' || this.$route.path === '/register';
    }
  }
};
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  display: grid;
  grid-template-areas: "header header" "sidebar main";
  grid-template-columns: 250px 1fr;
  background-image: url('https://s21.ax1x.com/2024/11/14/pAg74U0.png');
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-color: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(5px);
}

.auth-background {
  background-image: url('https://s21.ax1x.com/2024/11/14/pAg74U0.png');
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-color: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(5px);
}

header {
  grid-area: header;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f5f5f5;
  padding: 10px 20px;
  border-bottom: 2px solid #ddd;
}

.logo {
  font-size: 24px;
  font-weight: bold;
}

.search-bar {
  display: flex;
}

.search-bar input {
  padding: 5px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.search-bar button {
  margin-left: 5px;
  padding: 5px 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

nav {
  display: flex;
  gap: 20px;
}

.sidebar {
  grid-area: sidebar;
  background-color: #eaeaea;
  padding: 20px;
  border-right: 2px solid #ddd;
}

.sidebar h3 {
  margin-bottom: 10px;
}

.sidebar ul {
  list-style-type: none;
  padding: 0;
}

.sidebar li {
  margin-bottom: 10px;
}

.sidebar a {
  text-decoration: none;
  color: #2c3e50;
}

main {
  grid-area: main;
  padding: 20px;
}

.featured-products {
  margin-top: 20px;
}

.product-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
}

.product-card {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: center;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.product-card img {
  max-width: 100%;
  border-radius: 4px;
}

.product-card button {
  margin-top: 10px;
  padding: 5px 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

@media screen and (max-width: 768px) {
  #app {
    grid-template-columns: 1fr;
    grid-template-areas: "header" "main";
  }
  .sidebar {
    display: none;
  }
  .product-grid {
    grid-template-columns: 1fr;
  }
}
</style>
