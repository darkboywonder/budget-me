<template>
  <TheNavigation/>
  <br>
  <div id="page">
    <h1>Transaction List</h1>
    <button >View Transactions</button>
  </div>
  <br>
  <div id="page">
    <h1>Charts</h1>
    <apexcharts width="380" type="donut" :options="chartOptions" :series="series"></apexcharts>

  </div>
</template>


<script >
import TheNavigation from "@/views/TheNavigation.vue";
import VueApexCharts from 'vue3-apexcharts';


export default {
  name: 'ChartDisplay',
  components: {
    TheNavigation,
    apexcharts: VueApexCharts,
  },
  data: function() {
    return {
      chartOptions: {
        labels: ["Utilities", "Food", "Shopping", "Rent", "Car", "Restaurants"]
      },
      series: [8, 20, 25, 32, 10, 5],

    }
  },
}
</script>