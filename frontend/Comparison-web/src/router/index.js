import { createRouter, createWebHistory } from 'vue-router';
import RegisterPage from '@/pages/RegisterPage.vue';
import LoginPage from '@/pages/LoginPage.vue';
import DashboardPage from '@/pages/DashboardPage.vue';
import PriceHistoryPage from '@/pages/PriceHistoryPage.vue';
import ProductSearch from '@/pages/ProductSearchPage.vue';
import WelcomPage from '@/pages/WelcomPage.vue';
import Profile from '@/pages/Profile.vue';

// 路由定义
const routes = [
  { path: '/register', component: RegisterPage },
  { path: '/login', component: LoginPage },
  { 
    path: '/dashboard', 
    component: DashboardPage, 
    meta: { requiresAuth: true } // 需要登录
  },
  { 
    path: '/pricehistory', 
    component: PriceHistoryPage, 
    meta: { requiresAuth: true } // 需要登录
  },
  { 
    path: '/search', 
    component: ProductSearch, 
    meta: { requiresAuth: true } // 需要登录
  },
  { 
    path: '/profile', 
    component: Profile, 
    meta: { requiresAuth: true } // 需要登录
  },
  { path: '/', component: WelcomPage },
];

// 创建路由实例
const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 全局路由守卫
router.beforeEach((to, from, next) => {
  const isAuthenticated = !!localStorage.getItem('userprofile'); // 检查登录状态
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!isAuthenticated) {
      next('/login'); // 未登录重定向至登录页面
    } else {
      next(); // 已登录允许访问
    }
  } else {
    next(); // 不需要认证的页面直接放行
  }
});

export default router;
