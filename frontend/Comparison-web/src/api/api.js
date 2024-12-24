import axios from 'axios';

// 配置 Axios 的基础 URL
const api = axios.create({
  baseURL: 'http://localhost:8080', // 后端的基础 URL
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;

// 注册用户
export function registerUser(userData) {
  return apiClient.post('/api/users/register', userData);
}

// 用户登录
export function loginUser(credentials) {
  return apiClient.post('/api/users/login', credentials);
}

// 搜索商品
export function searchProducts(query) {
  return apiClient.get(`/api/products?search=${query}`).then(response => response.data);
}

// 获取商品详情
export function getProductDetails(productId) {
  return apiClient.get(`/api/products/${productId}`).then(response => response.data);
}
