<template>
<router-view>
</router-view>
<div class="container">


    <h1>Your Receipts</h1>
     <table>
            <tr>
                <th>Delete</th>
                <th>Title</th>
                <th>Tag</th>
                <th>Amount</th>
                <th>Date</th>
            </tr>

            <tr v-for="receipt in receipts" :key="receipt.id">
            <td><input type="checkbox" v-model="receipt.selected" @change="updateSelectedReceipts" /></td>
            <td>{{ receipt.title }}</td>
            <td>{{ receipt.tag }}</td>
            <td>{{ receipt.amount }}</td>
            <td>{{ receipt.date }}</td>
            </tr>

        </table>
        <br>
            <button><router-link to="/receipt/add">Add</router-link></button>
        <br>
            <button @click="deleteSelectedReceipts">Delete</button>
 </div>
</template>

<style>
  h1 {
    text-align: center;
    font-family:monospace;
  }
  th {
    background-color: rgba(0, 153, 255, 1);;
    color: white;
  }
  table, th, td {
    border: 1px solid;
    padding: 10px;

  }
  td {
    text-align: center;
  }
  tr:nth-child(even) {
    background-color: #D6EEEE;
    }
  tr {
    border-bottom: 1px solid #ddd;
  }
  tr:hover {
  background-color: rgba(102, 204, 51, 0.5);
  }

</style>

<script>

 export default {

      data() {
        return {
          receipts: [],
          sortBy: "date",
          sortDirection: "asc",
        };
      },
      mounted() {
        this.viewReceipts();
      },
      methods: {
        async viewReceipts() {

            try {
                const response = await fetch("/api/receipt/view", {
                    method: "GET",
                    headers: { "Content-Type": "application/json" },
                    });

                if (response.ok) {
                    const data = await response.json();
                    this.receipts = data.map(receipt => ({ ...receipt, selected: false }));
                    this.sortTable();
                }
            } catch (error) {
                console.error("Error fetching receipts", error);
                this.errorMessage = "Please try again";
                }
            },

            sortTable() {
                const order = this.sortDirection === "asc" ? 1 : -1;
                      this.receipts.sort((a, b) => {
                        if (this.sortBy === "date") {
                          return new Date(a.date) - new Date(b.date) * order;
                        } else {
                          return a[this.sortBy].localeCompare(b[this.sortBy]) * order;
                        }
                });
            },

            deleteSelectedReceipts() {
                const selectedReceipts = this.receipts.filter(receipt => receipt.selected);
                if (selectedReceipts.length === 0) {
                    return;
                }

                const selectedIds = selectedReceipts.map(receipt => receipt.id);

                fetch("/api/receipt/delete", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(selectedIds)
                })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error("Error deleting receipts");
                    }
                })
                .then(updatedReceipts => {
                    this.receipts = updatedReceipts;
                })
                .catch(error => {
                    console.error("Error deleting receipts", error);
                    this.errorMessage = "Please try again";
                });
            }

        },
    };
 </script>