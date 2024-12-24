<template>
    <div class="comparison-page">
      <h2>商品价格比较</h2>
      <el-table :data="productPrices" style="width: 100%">
        <el-table-column prop="website" label="网站" width="180"></el-table-column>
        <el-table-column prop="price" label="价格" width="180"></el-table-column>
        <el-table-column prop="link" label="购买链接">
          <template v-slot="scope">
            <a :href="scope.row.link" target="_blank">点击购买</a>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ProductComparison',
    data() {
      return {
        productPrices: [
          { website: '淘宝', price: '￥200', link: 'https://www.taobao.com' },
          { website: '京东', price: '￥210', link: 'https://www.jd.com' },
          { website: '拼多多', price: '￥190', link: 'https://www.pinduoduo.com' }
        ]
      };
    }
  };
  </script>
  
  <style scoped>
  .comparison-page {
    padding: 20px;
  }
  </style>
  