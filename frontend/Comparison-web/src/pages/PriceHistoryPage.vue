<template>
  <el-container>
    <el-header class="custom-header">
      <h2>历史价格</h2>
    </el-header>

    <el-main>
      <nav class="nav-links">
        <router-link to="/cart">返回购物车</router-link>
      </nav>

      <div v-if="priceHistory.length">
        <el-card class="history-card">
          <el-table :data="formattedPriceHistory" style="width: 100%">
            <el-table-column prop="timestamp" label="时间" width="180" />
            <el-table-column prop="price" label="价格" width="120" />
          </el-table>
        </el-card>
        <div class="chart-container">
          <div ref="chart" style="width: 100%; height: 40vh;"></div>
        </div>
      </div>
      <div v-else>
        <p class="empty-tip">暂无历史价格数据，请稍后查看哦~</p>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import api from '@/api/api';
import { defineComponent, computed, nextTick } from 'vue';
import * as echarts from 'echarts';

export default defineComponent({
  data() {
    return {
      priceHistory: [],
      chartOptions: {
        backgroundColor: '#fff',
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        textStyle: {
          fontSize: 12,
          color: '#333'
        }
      }
    };
  },
  mounted() {
    this.fetchPriceHistory();
  },
  methods: {
    async fetchPriceHistory() {
      try {
        const { productId } = this.$route.query;
        console.log('productId:', productId);
        const response = await api.get(`/api/price-history/${productId}`);
        this.priceHistory = response.data;
        console.log('历史价格：', this.priceHistory);
        this.updateChartOptions();
      } catch (error) {
        console.error('获取历史价格失败：', error);
      }
    },
    updateChartOptions() {
      this.$nextTick(() => {
        const formattedData = this.formattedPriceHistory;
        const chartInstance = echarts.init(this.$refs.chart);
        if (chartInstance) {
          this.chartOptions = {
            title: {
              text: '价格历史趋势图',
              left: 'center'
            },
            xAxis: {
              type: 'category',
              data: formattedData.map(item => item.timestamp),
              name: '时间',
              nameLocation: 'middle',
              nameGap: 25,
              axisLabel: {
                rotate: 30
              }
            },
            yAxis: {
              type: 'value',
              name: '价格',
              nameLocation: 'middle',
              nameGap: 25,
              axisTick: {
                interval: 10
              }
            },
            series: [
              {
                data: this.priceHistory.map(item => item.price),
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 6,
                lineStyle: {
                  width: 2,
                  color: '#3398DB'
                },
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    { offset: 0, color: 'rgba(51, 152, 219, 0.3)' },
                    { offset: 1, color: 'rgba(51, 152, 219, 0)' }
                  ])
                },
                tooltip: {
                  trigger: 'axis',
                  formatter: function (params) {
                    return `时间: ${params[0].name}<br>价格: ${params[0].data}`;
                  }
                }
              }
            ]
          };
          chartInstance.setOption(this.chartOptions);
        }
      });
    }
  },
  computed: {
    formattedPriceHistory() {
      return this.priceHistory.map(item => ({
        ...item,
        timestamp: new Date(item.timestamp).toLocaleString(),
      }));
    }
  }
});
</script>

<style scoped>
.custom-header {
  background-color: #409eff;
  color: white;
  padding: 15px;
  text-align: center;
}
.nav-links {
  margin: 10px 0;
}
.history-card {
  margin-bottom: 20px;
}
.chart-container {
  width: 100%;
  height: 40vh;
}
.empty-tip {
  color: #999;
  text-align: center;
  margin-top: 20px;
  font-size: 14px;
}
</style>