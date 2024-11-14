<template>
    <div>
      <canvas id="priceChart"></canvas>
    </div>
  </template>
  
  <script>
  import { onMounted } from 'vue';
  import Chart from 'chart.js/auto';
  export default {
    props: ['priceData'],
    setup(props) {
      onMounted(() => {
        new Chart(document.getElementById('priceChart'), {
          type: 'line',
          data: {
            labels: props.priceData.map(item => item.date),
            datasets: [{
              label: '价格',
              data: props.priceData.map(item => item.price),
              borderColor: 'rgba(75, 192, 192, 1)',
              fill: false
            }]
          },
          options: {
            responsive: true,
            scales: {
              x: { display: true },
              y: { display: true }
            }
          }
        });
      });
    }
  };
  </script>
  
  <style scoped>
  canvas {
    width: 100%;
    max-width: 600px;
  }
  </style>