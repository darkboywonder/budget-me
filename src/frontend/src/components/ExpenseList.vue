<template>
  <div>
    <h2>Expense List</h2>

    <form @submit.prevent="addExpense">
      <label>Description:</label>
      <input v-model="newExpense.description" type="text" required>

      <label>Amount:</label>
      <input v-model="newExpense.amount" type="number" required>

      <button type="submit">Add Expense</button>
    </form>

    <ul>
      <li v-for="expense in expenses" :key="expense.id">
        {{ expense.description }} - ${{ expense.amount }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      expenses: [
        { id: 1, description: 'Groceries', amount: 50 },
        { id: 2, description: 'Gas', amount: 30 },
        { id: 3, description: 'Dinner', amount: 40 },
        // Add more expense items as needed
      ],
      newExpense: {
        description: '',
        amount: ''
      }
    };
  },

  methods: {
    addExpense() {
      // Generate a unique ID for the new expense
      const newExpenseId = Math.max(...this.expenses.map(expense => expense.id)) + 1;

      // Create a new expense object
      const newExpense = {
        id: newExpenseId,
        description: this.newExpense.description,
        amount: this.newExpense.amount
      };

      // Add the new expense to the expenses array
      this.expenses.push(newExpense);

      // Reset the newExpense object for next input
      this.newExpense.description = '';
      this.newExpense.amount = '';
    }
  }
};
</script>
<style>
  body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
  }

  div {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
  }

  h2 {
    margin-bottom: 20px;
    text-align: center;
  }

  form {
    margin-bottom: 20px;
  }

  label {
    display: block;
    margin-bottom: 5px;
  }

  input[type="text"],
  input[type="number"] {
    width: 100%;
    padding: 5px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }

  button[type="submit"] {
    display: block;
    width: 100%;
    padding: 10px;
    background-color: #4caf50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    margin-bottom: 5px;
  }
</style>