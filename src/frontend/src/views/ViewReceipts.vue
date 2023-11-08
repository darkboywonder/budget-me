<template>
<TheNavigation/>
<router-view>
</router-view>
<div id="page">
<div class="container">

<div class="search-container">
  <input type="text" v-model="searchTag" placeholder="Search by Category">

  <button @click="searchByTag">Search</button>

  <br>
  <button @click="clearSearch">Clear Search</button>
</div>

    <h1>My Receipts</h1>
    <div class="table-container">
     <table>
            <tr>
                <th class="deleteBox">Delete</th>
                <th>Title</th>
                <th>Category</th>
                <th>Amount</th>
                <th>Date</th>
            </tr>

            <tr v-for="receipt in receipts" :key="receipt.id">

        <td><input type="checkbox" v-model="receipt.selected"></td>
        <td>
             <div v-if=!receipt.isEdit>
                    {{ receipt.title }}
             </div>
             <div v-if=receipt.isEdit>
                    <input type="text" v-model="receipt.title" required>
             </div>
        </td>
        <td>
             <div class="tag-container">
                 <span v-for="tag in receipt.tags" :key="tag" class="tag">{{ tag }}</span>
             <div v-if=!receipt.isEdit>
                     {{ receipt.tag }}
             </div>
             <div v-if=receipt.isEdit>
                      <input type="text" v-model="receipt.category" required>
             </div>
             </div>
        </td>

        <td>
            <div v-if=!receipt.isEdit>
                  ${{ parseFloat(receipt.amount).toFixed(2) }}
            </div>

            <div v-if=receipt.isEdit>
                   <input type="number" min="0.00" step="0.01" v-model="receipt.amount" required>
            </div>
        </td>
        <td>
            <div v-if=!receipt.isEdit>
                    {{ receipt.date }}
            </div>
            <div v-if=receipt.isEdit>
                     <input type="date" id="date" v-model="receipt.date" required>
            </div>
        </td>
        <td>
                <div v-if=!receipt.isEdit>
                    <button @click="onEdit(receipt)">Edit</button>
                </div>
                <div v-if=receipt.isEdit>
                     <button @click="updateReceipt(receipt)">Save</button>
                </div>
            </td>
         </tr>


        </table>
        </div>
        <br>
            <button class="addButton"><router-link to="/receipt/add">Add a Receipt</router-link></button>
        <br>
            <button class="deleteButton" @click="deleteSelectedReceipts">Delete a Receipt</button>
        <br>


        </div>
    </div>
</template>

<style>
  .tag-container {
      flex-wrap: wrap;
  }

  .tag {
      margin-right: 4px;
      padding: 2px 4px;
      background-color: #3490dc;
      color: black;
      border-radius: 4px;
  }

  table {
       border: 1px solid;
       padding: 10px;
       margin-left: auto;
       margin-right: auto;
       text-align: center;
  }

  .table-container {
       display: flex;
       justify-content: center;
  }

 .addButton button {
       width: 100%;
       margin: auto;
       padding: 10px;
       background-color: #4caf50;
       color: white;
       border: none;
       border-radius: 4px;
       cursor: pointer;
       font-size: 16px;
       text-decoration: none;
  }

  button {
      display: block;
      width: 100%;
      margin: auto;
      padding: 10px;
      background-color: #4caf50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
      text-decoration: none;
  }

  .deleteBox {
      color: maroon;
  }

  .deleteButton {
      color: maroon;

  }

  h1 {
      text-align: center;
      font-family:monospace;
  }

  th {
      background-color: rgba(0, 153, 255, 1);;
      color: white;
  }

  th, td {
       border: 1px solid;
       padding: 10px;
       margin-left: auto;
       margin-right: auto;
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
import TheNavigation from "@/views/TheNavigation.vue";
 export default {
      data() {
        return {
          receipts: [],
          sortBy: "date",
          sortDirection: "asc",
          searchTag: "",
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
                    this.receipts = data.map(receipt => ({
                    ...receipt, selected: false,
                     tags: receipt.category.split(",").map(tag => tag.trim()), /*this splits categories into tags */
                     }));
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
            async searchByTag() {
                if (this.searchTag) {
                try {
                const response = await fetch(`/api/receipt/search?tag=${this.searchTag}`, {
                    method: "GET",
                    headers: { "Content-Type": "application/json" },
            });
            if (response.ok) {
                    const data = await response.json();
                    this.receipts = data.map(receipt => ({
                    ...receipt,
                          selected: false,
                          tags: receipt.category.split(',').map(tag => tag.trim()),
                        }));
                      } else {
                        console.error("Error searching receipts by tag");
                      }
                    } catch (error) {
                      console.error("Error searching receipts by tag", error);
                        }
                      }
                    },
            clearSearch() {
                    this.searchTag = "";
                    this.viewReceipts(); //Fetch and display all receipts again
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
                    this.viewReceipts();
                    this.sortTable();
                })
                .catch((error) => {
                    console.error("Error deleting receipts", error);
                    this.errorMessage = "Please try again";
                });
            },
             onEdit(receipt) {
                 this.receipts.forEach(element => {
                    element.isEdit = false;
                 });
                 receipt.isEdit = true;
                 },

             async viewReceipt() {
                  const response = await fetch("/api/receipt/view");

                  this.receipt = await response.json();
             },

             async updateReceipt(receipt) {
                if (this.receipt != "once") {
                    this.isRepeated = true;
                } else {
                    this.isRepeated = false;
                }

                this.receipt = receipt;

                const updateReceiptRequest = {
                    method: "PUT",
                    headers: { "Content-Type" : "application/json" },
                    body: JSON.stringify(this.receipt)
                };
                try {
                     const response = await fetch("/api/receipt/edit", updateReceiptRequest);
                     if (response.ok) {
                         this.isEdit = false;
                         await this.viewReceipts();
                         this.$router.push('/receipt/view')

                     } else {
                         alert("Receipt update failed.")
                     }
                 } catch (error) {
                     console.error("Error occurred during receipt update.", error);
                 }

        },
        },
        components: {
            TheNavigation,
            },

    };
 </script>