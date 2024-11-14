import axios from 'axios';

export function registerUser(userData) {
  return axios.post('/api/register', userData);
}

export function loginUser(credentials) {
  return axios.post('/api/login', credentials);
}