<template>
  <el-container>
    <!-- 顶部标题导航栏 -->
    <el-header class="custom-header">
      <div class="header-content">
        <h2 class="header-title">历史价格</h2>
        <nav class="nav-links">
          <router-link to="/cart" class="nav-link">返回购物车</router-link>
        </nav>
      </div>
    </el-header>

    <el-main>
      <!-- 历史价格表格 -->
        <el-card class="history-card">
          <el-table :data="formattedPriceHistory" style="width: 50%" stripe class="custom-table">
            <el-table-column prop="timestamp" label="时间" width="200" align="center" header-align="center" header-class-name="custom-theader" />
            <el-table-column prop="price" label="价格" width="120" align="center" header-align="center" header-class-name="custom-theader" />
          </el-table>
        </el-card>
      <div v-if="priceHistory.length">
        

        <!-- 价格趋势图 -->
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
              left: 'center',
              textStyle: {
                fontSize: 18,
                fontWeight: 'bold',
                color: '#333'
              }
            },
            xAxis: {
              type: 'category',
              data: formattedData.map(item => item.timestamp),
              name: '时间',
              nameLocation: 'middle',
              nameGap: 25,
              axisLabel: {
                rotate: 30,
                fontSize: 12
              }
            },
            yAxis: {
              type: 'value',
              name: '价格',
              nameLocation: 'middle',
              nameGap: 25,
              axisTick: {
                interval: 10
              },
              axisLabel: {
                fontSize: 12
              }
            },
            series: [
              {
                data: this.priceHistory.map(item => item.price),
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 8,
                lineStyle: {
                  width: 3,
                  color: '#409eff'
                },
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    { offset: 0, color: 'rgba(64, 158, 255, 0.3)' },
                    { offset: 1, color: 'rgba(64, 158, 255, 0)' }
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
  background-color: #faf0f085;
  padding: 15px 20px;
  border-bottom: 1px solid #ddd;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
}

.header-title {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
  margin: 0; /* 去除默认的 margin */
}

.nav-links {
  display: flex;
  gap: 20px;
  align-items: center; /* 确保导航链接垂直居中 */
}

.nav-link {
  text-decoration: none;
  color: #606266;
  font-size: 16px;
  transition: color 0.3s ease;
  padding: 8px 12px; /* 增加内边距，使链接更易点击 */
  border-radius: 4px; /* 添加圆角 */
}

.nav-link:hover {
  color: #409eff;
  background-color: rgba(64, 158, 255, 0.1); /* 悬停时添加背景色 */
}

.history-card {
  margin-bottom: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  width: 30%; /* 控制表格宽度 */
  margin: 0 auto; /* 居中显示 */
}

.chart-container {
  width: 80%; /* 控制图表宽度 */
  height: 40vh;
  margin: 20px auto; /* 居中显示 */
}

.empty-tip {
  color: #999;
  text-align: center;
  margin-top: 20px;
  font-size: 14px;
}

.custom-table {
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  width:  30%; /* 控制表格宽度 */
  margin: 0 auto; /* 居中显示 */
}

.custom-table th {
  background-color: #f5f7fa;
  font-weight: bold;
  color: #303133;
  text-align: center; /* 表头内容居中 */
}

.custom-table td {
  color: #606266;
  text-align: center; /* 表格内容居中 */
}

.custom-table--striped .el-table__body tr.el-table__row--striped td {
  background-color: #fafafa;
}

.custom-table .el-table__row:hover td {
  background-color: #f0f7ff; /* 悬停时添加背景色 */
}
.custom-theader .cell {
  text-align: center !important;
}
</style>