import { createRouter, createWebHistory } from 'vue-router';
import RegisterPage from '@/pages/RegisterPage.vue';
import LoginPage from '@/pages/LoginPage.vue';
import DashboardPage from '@/pages/DashboardPage.vue';
import PriceHistoryPage from '@/pages/PriceHistoryPage.vue';
import ProductSearch from '@/pages/ProductSearchPage.vue';
import WelcomPage from '@/pages/WelcomPage.vue';
const routes = [
  { path: '/register', component: RegisterPage },
  { path: '/login', component: LoginPage },
  { path: '/pricehistory', component: PriceHistoryPage },
  { path: '/search', component: ProductSearch },
  { path: '/dashboard', component: DashboardPage },
  { path: '/', component: WelcomPage }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;