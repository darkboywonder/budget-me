<template>
  <div>
    <h1>Create a Budget Period</h1>

     <!-- Input fields for start date and end date -->
      <div class="date-inputs">

            <div>
                <label for="startDate">Budget Start:</label>
                <input type="date" v-model="startDate" required />
              </div>

              <div>
                 <label for="endDate">Budget End:</label>
                 <input type="date" v-model="endDate" required />
              </div>
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
         <div class="budget-input">
              <label for="budget-amount">Budget Amount:</label>
              <input type="number" id="budget-amount" v-model="budgetAmount" placeholder="Enter budget amount" />
              <button @click="addBudgetAmount">Add</button>
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
.date-inputs {
  display: flex;
  gap:20px;
  align-items: center;
  justify-content: space-between;
  width: 60%;
  margin-bottom: 20px;
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
.budget-input {
   display: flex;
   align-items: center;
   margin-bottom: 20px;
   width: 50%;
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
  width: 50%;
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
        budgetAmount: 0, //Store the budget amount
        selectedExpense: '', // Store the selected expense ID
        startDate: '', // Store the selected month
        endDate: '', // Store the selected year
      months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
      expenses: [
        { id: 1, name: 'Electric' },
        { id: 2, name: 'Rent' },
        { id: 3, name: 'Food' },
        // Add more expense items as needed
      ],
      selectedExpenses: [], // Store the list of selected expenses
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
      const formData = {
        budgetAmount: this.budgetAmount,
        selectedExpenses: this.selectedExpenses,
        startDate: this.startDate,
        endDate: this.endDate,
         };

          // Create the request options with the correct headers
          try {
               const response = await fetch('/api/budget-period/create-budget-period', {
                 method: 'POST',
                 headers: { 'Content-Type': 'application/json' },
                 body: JSON.stringify(formData),
               });

         console.log('Form data:', formData);


                 // Send the data to the backend endpoint

                 if (response.ok) {
                   // Budget period was successfully saved in the backend
                   this.$router.push('/success'); // Redirect to a success page or wherever you want
                 } else {
                   // Handle errors or show appropriate error message
                   // You can display the error message returned by the server
                   const errorMessage = await response.text();
                   console.error('Error while saving', errorMessage);
                 }
               } catch (error) {
                 console.error('Error while saving', error);
               }
         },

      },
   }
   </script>

                 // Show a generic error message to the user
                 // Optionally, you can also log the error for debugging purposes
                 // You may want to display a more user-friendly error message
                 // based on the type of error encountered (e.g., net)
