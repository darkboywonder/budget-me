<template>
  <br>
  <div id="page">
    <h1>Budget Period List</h1>
<router-link to="/create-budget-period">
      <button>Create</button>
    </router-link>
        <div class="month-calendar">
      <div class="month-header">{{ selectedMonth }}</div>
      <div class="calendar">
        <div v-for="(week, weekIndex) in weeks" :key="weekIndex" class="week">
          <div
            v-for="(day, dayIndex) in week"
            :key="dayIndex"
            class="day"
            :class="{ 'selected-day': day.budgetPeriod }"
          >
            {{ day.dayNumber }}
            <div v-if="day.budgetPeriod">
              <span>Budget: {{ day.budgetPeriod.budgetAmount }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



    <!--  Add form here from Create Expense branch-->
    <router-link to="/create-budget-period">
    <button>Create</button>
    </router-link>


<style>

</style>
<script setup>
</script>
