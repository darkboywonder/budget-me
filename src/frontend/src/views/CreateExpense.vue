<template>
    <TheNavigation/>
    <br>
    <div id="page">
        <div class="expense-form">
        <h2>Create Expense</h2>
        <form @submit.prevent="submitExpense">
            <div class="form-group">
                <label for="due_date">Due Date:</label>
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
                <input type="text" id="expenseName" v-model="expense.expenseName" required>
            </div>
            <div class="form-group">
                <label for="amount">Amount:</label>
                <input type="number" min="0.01" step="0.01" id="amount" v-model="expense.amount" required>
            </div>
            <div class="form-group">
                <label for="category">Category:</label>
                <select id="category" v-model="expense.category" required>
                    <option value="Loan Payments">Loan Payments</option>
                    <option value="Entertainment">Entertainment</option>
                    <option value="Food and Drink">Food and Drink</option>
                    <option value="General Merchandise">General Merchandise</option>
                    <option value="Home Improvement">Home Improvement</option>
                    <option value="Medical">Medical</option>
                    <option value="Personal Care">Personal Care</option>
                    <option value="General Services">General Services</option>
                    <option value="Transportation">Transportation</option>
                    <option value="Travel">Travel</option>
                    <option value="Rent and Utilities">Rent and Utilities</option>
                </select>
            </div>
            <br>
            <button type="submit">Create Expense</button>
        </form>
      </div>
    </div>
</template>

<style>

</style>

<script>
    import TheNavigation from "@/views/TheNavigation.vue";

    export default {
        components: {
            TheNavigation,
        },

        name: "ExpenseList",
        data() {
            return {
                expense: {
                    dueDate: '',
                    isRepeated: false,
                    frequency: '',
                    expenseName: '',
                    amount: null,
                    category: ''
                }
            };
        },

        methods: {
            async submitExpense() {
                const createExpenseRequest = {
                    method: "POST",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify(this.expense)
                }

                try {
                    const response = await fetch("/api/expenses/create-expense", createExpenseRequest);

                    if (response.ok) {
                        this.$router.push('/expenses')
                    } else {
                        alert("Expense creation failed.")
                    }
                }

                catch (error) {
                    console.error("Error occurred during expense creation.", error);
                }
            }
        }
    }

</script>



