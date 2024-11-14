<template>
    <div class="product-detail">
      <h2>{{ product.name }}</h2>
      <img :src="product.image" alt="product image" />
      <p>条码: {{ product.barcode }}</p>
      <p>规格: {{ product.specification }}</p>
      <PriceHistoryChart :priceData="priceHistory" />
    </div>
  </template>
  
  <script>
  import { getProductDetails } from '@/api/product';
  import PriceHistoryChart from '@/components/PriceHistoryChart.vue';
  
  export default {
    name: 'ProductDetailPage',
    components: {
      PriceHistoryChart
    },
    data() {
      return {
        product: {},
        priceHistory: []
      };
    },
    async created() {
      const productId = this.$route.params.id;
      const productData = await getProductDetails(productId);
      this.product = productData.product;
      this.priceHistory = productData.priceHistory;
    }
  };
  </script>
  
  <style scoped>
  .product-detail {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 15px;
  }
  img {
    max-width: 300px;
    width: 100%;
  }
  </style>