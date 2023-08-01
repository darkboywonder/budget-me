<template>
  <div>
    <h1>Create a Budget Period</h1>

     <!-- Input fields for start date and end date -->
      <div class="date-inputs">
         <label for="start-date">Start Date:</label>
          <input type="date" id="start-date" v-model="startDate" />
            <label for="end-date">End Date:</label>
            <input type="date" id="end-date" v-model="endDate" />
          </div>

    <!-- Dropdown list to select an expense -->
       <div class="expense-select">
         <label for="expense">Select an Expense:</label>
         <select id="expense" v-model="selectedExpense">
           <option v-for="expense in expenses" :key="expense.id" :value="expense.id">
             {{ expense.name }}
           </option>
         </select>
         <button @click="addExpense">Add</button>
       </div>

       <!-- List of selected expenses -->
       <div v-if="selectedExpenses.length > 0" class="selected-expenses">
         <h2>Selected Expenses:</h2>
         <ul>
           <li v-for="expense in selectedExpenses" :key="expense.id">
             {{ expense.name }}
             <button @click="removeExpense(expense.id)">Remove</button>
           </li>
         </ul>
       </div>

       <!-- Expenses textarea -->
       <div class="expenses-textarea">
         <label for="expenses">Expenses:</label>
         <textarea
           id="expenses"
           v-model="expensesText"
           rows="5"
           readonly
         ></textarea>
       </div>


 <!-- Submit button to submit the form -->
    <button @click="submitForm">Submit</button>

  </div>

</template>

<style>
/* Center the content */
div {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
}

/* Style the dropdown list and "Add" button */
.expense-select {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 20px;
}

label {
  font-size: 16px;
}

select {
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 8px 12px;
  font-size: 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

/* Style the list of selected expenses */
.selected-expenses {
  margin-top: 20px;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  margin-bottom: 8px;
  display: flex;
  align-items: center;
}

/* Style the "Remove" button for each selected expense */
li button {
  margin-left: 10px;
  padding: 4px 8px;
  font-size: 14px;
  background-color: #dc3545;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

li button:hover {
  background-color: #c82333;
}

/* Style the Expenses textarea */
.expenses-textarea {
  margin-top: 20px;
}

textarea {
  width: 100%;
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>

<script>
export default {

 data() {
    return {
      selectedExpense: '', // Store the selected expense ID
      startDate: '', // Store the selected start date
      endDate: '', // Store the selected end date
      expenses: [
        { id: 1, name: 'Electric' },
        { id: 2, name: 'Rent' },
        { id: 3, name: 'Food' },
        // Add more expense items as needed
      ],
      selectedExpenses: [], // Store the list of selected expenses
      formData: {
       selectedExpenses: null,
                 startDate: null,
                 endDate: null,
      }
    };
  },
  computed: {
    // Computed property to generate the expenses text for the textarea
    expensesText() {
      return this.selectedExpenses.map((expense) => expense.name).join('\n');
    },
  },
  methods: {
    addExpense() {
      // Find the selected expense object based on the selectedExpense ID
      const selectedExpense = this.expenses.find((expense) => expense.id === this.selectedExpense);
      if (selectedExpense) {
        // Add the selected expense to the list of selectedExpenses
        this.selectedExpenses.push(selectedExpense);
        // Clear the selectedExpense to reset the dropdown to its default state
        this.selectedExpense = '';
      }
    },
    removeExpense(expenseId) {
      // Remove the selected expense from the list of selectedExpenses
      this.selectedExpenses = this.selectedExpenses.filter((expense) => expense.id !== expenseId);
    },
   async submitForm() {
         // Method to handle form submission
         // Implement the logic to submit the form data, e.g., sending data to the server
         // For this example, we will log the form data to the console
         const budgetPeriod = {
            method: "POST",
            header: {"Content-Type": "application/json"},
            body: JSON.stringify(this.budgetPeriod),
         };
         console.log('Form data:', budgetPeriod);
         const response = await fetch("/api/budget-period/create-budget-period", budgetPeriod);
         console.log(response);
         // Optionally, you can reset the form fields after submission
         this.selectedExpense = '';
         this.startDate = '';
         this.endDate = '';
         this.selectedExpenses = [];
       },
  },

};
</script>