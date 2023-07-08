<template>
  <div>
    <h2>Expense List</h2>
    <ul>
      <li v-for="expense in expenses" :key="expense.id">
        {{ expense.description }} - ${{ expense.amount }} ({{ expense.category }})
      </li>
    </ul>
  </div>
</template>


<script>
export default {
  name: 'ExpenseList',
    data() {
      return {
        expenses: [
          { id: 1, description: 'Groceries', amount: 50, category: 'Food' },
          { id: 2, description: 'Gas', amount: 30, category: 'Transportation' },
          { id: 3, description: 'Dinner', amount: 40, category: 'Food' },
          // Add more expense items as needed
        ]
      }
    }
}
</script>


