<template>
  <div class="expense-form">
    <h2>Create Expense</h2>
    <form @submit.prevent="submitExpense">
      <div class="form-group">
        <label for="due_date">Due Date:</label>
        <input type="date" id="due_date" v-model="expense.due_date" required>
      </div>
      <div class="form-group">
        <label for="is_repeated">Is Repeated:</label>
        <input type="checkbox" id="is_repeated" v-model="expense.is_repeated">
      </div>
      <div class="form-group" v-if="expense.is_repeated">
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
      <div class="form-group">
        <label for="user_id">User ID:</label>
        <input type="text" id="user_id" v-model="expense.user_id" required>
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      expense: {
        due_date: '',
        is_repeated: false,
        frequency: '',
        name: '',
        amount: null,
        tag: '',
        user_id: ''
      }
    };
  },
  methods: {
    async submitExpense() {
      // Perform any additional validation or processing here
      // For simplicity, we'll just log the expense object
      const submitExpenseRequest = {
         method: "POST",
         headers: { "Content-Type": "application/json" },
         body: JSON.stringify(this.expense);
  };
      const response = await fetch("/expense/create", submitExpenseRequest);
      const data = await response.json();
      console.log(this.expense);
      console.log(data);
      // Clear the form after submission
      this.expense.due_date = '';
      this.expense.is_repeated = false;
      this.expense.frequency = '';
      this.expense.name = '';
      this.expense.amount = null;
      this.expense.tag = '';
      this.expense.user_id = '';
    }
  }
};
</script>

<style>
.expense-form {
  text-align: center;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  font-weight: bold;
}

input, select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button[type="submit"] {
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>