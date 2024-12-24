<template>
  <div id="app" :class="{ 'auth-background': isAuthPage }">
    <el-container>
      <el-main>
  <router-view></router-view>
</el-main>
    </el-container>
    <el-footer style="text-align: center; margin-top: 20px;">
        <p align="center" class="text-muted">© 2024 Comparison Website</p>
    </el-footer>
  </div>
</template>

<script>
import api from '@/api/api';
export default {
  name: 'App',
  
  
};
</script>

<style scoped>
#app {
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

.nav-links {
  display: flex;
  gap: 15px;
}

</style>
