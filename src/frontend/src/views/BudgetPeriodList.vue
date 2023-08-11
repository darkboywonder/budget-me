<template>
  <br>
  <div>
    <h1>Budget Period</h1>
    <!--  Add form here from Create Expense branch-->

    <form>
          <div class="form-group">
            <label for="dueDate">Due Date:</label>
            <input type="date" id="dueDate" v-model="expense.dueDate" required>
          </div>
          <div class="form-group">
            <label for="isRepeated">Is Repeated:</label>
            <input type="checkbox" id="isRepeated" v-model="expense.isRepeated">
          </div>
          <div class="form-group" v-if="expense.isRepeated">
            <label for="frequency">Frequency:</label>
            <select id="frequency" v-model="expense.frequency" required>
              <option value="">Select Frequency</option>
              <option value="bi-weekly">Bi-Weekly</option>
              <option value="weekly">Weekly</option>
              <option value="monthly">Monthly</option>
            </select>
          </div>
          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" v-model="expense.name" required>
          </div>
          <div class="form-group">
            <label for="amount">Amount:</label>
            <input type="number" id="amount" v-model="expense.amount" required>
          </div>
          <div class="form-group">
            <label for="tag">Tag:</label>
            <input type="text" id="tag" v-model="expense.tag" required>
          </div>
          <button @click="viewBudget">Submit</button>
        </form>

    <button><router-link to="/create-budget-period">Create</router-link></button>
  </div>
</template>

<style>

</style>
<script>
export default {
    data() {
        return {
            expense: {
            dueDate: '',
            isRepeated: false,
            frequency: '',
            name: '',
            amount: '',
            tag: '',
            }
        }
    },


  methods: {
  async viewBudget() {
        try {
               const response = await fetch('/api/budget-period/budget-period-list', {
                 method: 'GET',
                 headers: { 'Content-Type': 'application/json' },
               });

                 if (response.ok) {
                   this.$router.push('/budget-period-list');
                 } else {

                   const errorMessage = await response.text();
                   console.error('Error while saving', errorMessage);
                 }
               } catch (error) {
                 console.error('Error while saving', error);
               }
         },
        },
      };

</script>
