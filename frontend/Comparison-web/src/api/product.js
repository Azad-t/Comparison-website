import axios from 'axios';

export function searchProducts(query) {
  return axios.get(`/api/products?search=${query}`).then(response => response.data);
}

export function getProductDetails(productId) {
  return axios.get(`/api/products/${productId}`).then(response => response.data);
}