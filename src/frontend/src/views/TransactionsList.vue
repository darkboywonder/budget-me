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
  </div>
</template>


<script >
import TheNavigation from "@/views/TheNavigation.vue";

export default {

  components: {
    TheNavigation,
  },
}
</script>