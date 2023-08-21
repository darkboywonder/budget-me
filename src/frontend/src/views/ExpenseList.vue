<template>
    <TheNavigation/>
    <br>
    <div id="page">
        <div>
            <h2>Expense List</h2>
            <button v-if="!isHidden" v-on:click="isHidden = true" @click="getExpenseList">Display Expenses</button>
        </div>

        <table class="styled-table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Amount</th>
                <th>Frequency</th>
                <th>Due Date</th>
                <th>Category</th>
                <th>Edit</th>
            </tr>
            </thead>

            <tbody>
                <tr v-for="expense in expenses" :key="expense.expenseId">
                    <td>
                        <div v-if=!expense.isEdit>
                            {{ expense.expenseName }}
                        </div>
                        <div v-if=expense.isEdit>
                            <input type="text" v-model="expense.expenseName" required>
                        </div>
                    </td>
                    <td>
                        <div v-if=!expense.isEdit>
                            ${{ formatNumber(expense.amount) }}
                        </div>
                        <div v-if=expense.isEdit>
                            <input type="number" min="0.01" step="0.01" v-model="expense.amount" required>
                        </div>
                    </td>
                    <td>
                        <div v-if=!expense.isEdit>
                            {{ expense.frequency }}
                        </div>
                        <div v-if=expense.isEdit>
                            <select id="frequency" v-model="expense.frequency" required>
                                <option value="">Select Frequency</option>
                                <option value="">Once</option>
                                <option value="bi-weekly">Bi-Weekly</option>
                                <option value="weekly">Weekly</option>
                                <option value="monthly">Monthly</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div v-if=!expense.isEdit>
                            {{ expense.dueDate }}
                        </div>
                        <div v-if=expense.isEdit>
                            <input type="date" id="dueDate" v-model="expense.dueDate" required>
                        </div>
                    </td>
                    <td>
                        <div v-if=!expense.isEdit>
                            {{ expense.category }}
                        </div>
                        <div v-if=expense.isEdit>
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
                    </td>
                    <td>
                        <div v-if=!expense.isEdit>
                            <button @click="onEdit(expense)">Edit</button>
                        </div>
                        <div v-if=expense.isEdit>
                            <button @click="updateExpense(expense)">Save</button>
                            <button @click="deleteExpense(expense.expenseId)">Delete</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <br>
        <router-link to="/expenses/create" custom v-slot="{ navigate }"><button @click="navigate" role="link">Create an Expense</button></router-link>
    </div>
</template>

<style>

    table, th, td {
      border: 1px solid black;
    }
    table {
        text-align: center;
    }

    .styled-table {
        padding: 15px;
        border-collapse: collapse;
        margin-left: auto;
        margin-right: auto;
        font-size: 0.9em;
        font-family: sans-serif;
        min-width: 400px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    }

    .styled-table thead tr {
        background-color: #009879;
        color: #ffffff;
        text-align: left;
    }

    .styled-table th,
    .styled-table td {
        padding: 12px 15px;
    }

    .styled-table tbody tr {
        border-bottom: 1px solid #dddddd;
    }

    .styled-table tbody tr:nth-of-type(even) {
        background-color: #f3f3f3;
    }

    .styled-table tbody tr:last-of-type {
        border-bottom: 2px solid #009879;
    }
</style>

<script>
    import TheNavigation from "@/views/TheNavigation.vue";

    export default {

        created() {
            this.getExpenseList();
        },

        components: {
            TheNavigation,
        },

        data() {
            return {
                expense: {
                    dueDate: '',
                    isRepeated: false,
                    frequency: '',
                    expenseName: '',
                    amount: null,
                    category: ''
                },
                isHidden: false,
                expenses: null,
                expenseId: null,
            };
        },

        methods: {
            onEdit(expense){
                this.expenses.forEach(element => {
                    element.isEdit = false;
                });
                expense.isEdit = true;
            },

            async getExpenseList() {
                const response = await fetch("/api/expenses/expenses");

                this.expenses = await response.json();
            },

            async updateExpense(expense) {
                if (this.expense != "once") {
                    this.isRepeated = true;
                } else {
                    this.isRepeated = false;
                }

                this.expense = expense;
                console.log(expense.amount);
                const updateExpenseRequest = {
                    method: "PUT",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify(this.expense)
                };

                try {
                    const response = await fetch("/api/expenses/update", updateExpenseRequest);
                    if (response.ok) {
                        this.isEdit = false;
                        await this.getExpenseList();
                        this.$router.push('/expenses')
                    } else {
                        alert("Expense update failed.")
                    }
                } catch (error) {
                    console.error("Error occurred during expense update.", error);
                }
            },

            async deleteExpense(expenseId) {
                this.expenseId = expenseId;
                const deleteExpenseRequest = {
                    method: "DELETE",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify(this.expenseId)
                }

                try {
                    const response = await fetch("/api/expenses/delete", deleteExpenseRequest);
                    if (response.ok) {
                        this.isEdit = false;
                        await this.getExpenseList();
                        this.$router.push('/expenses')
                    } else {
                        alert("Expense deletion failed.")
                    }
                } catch (error) {
                    console.error("Error occurred during expense deletion.", error);
                }
            },

            formatNumber (num) {
                return parseFloat(num).toFixed(2);
            },
        },
    };
</script>