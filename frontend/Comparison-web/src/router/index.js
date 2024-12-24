import { createRouter, createWebHistory } from 'vue-router';
import RegisterPage from '@/pages/RegisterPage.vue';
import LoginPage from '@/pages/LoginPage.vue';
import DashboardPage from '@/pages/DashboardPage.vue';
import ProductDetailPage from '@/pages/ProductDetailPage.vue';
import PriceHistoryPage from '@/pages/PriceHistoryPage.vue';
import ProductSearch from '@/pages/ProductSearchPage.vue';

const routes = [
  { path: '/register', component: RegisterPage },
  { path: '/login', component: LoginPage },
  { path: '/pricehistory', component: PriceHistoryPage },
  { path: '/product/:id', component: ProductDetailPage },
  { path: '/search', component: ProductSearch },
  { path: '/', component: DashboardPage },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;