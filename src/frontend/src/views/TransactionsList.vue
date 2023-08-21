<template>
  <TheNavigation/>
  <br>
  <div id="page">
    <h1>Transaction List</h1>
    <button @click="getTransactions">Update Transactions</button>
  </div>
    <table class="styled-table">
      <thead>
          <tr>
              <th>Merchant Name</th>
              <th>Amount</th>
              <th>Date</th>
              <th>Category</th>
          </tr>
      </thead>

      <tbody>
          <tr v-for="actualTransaction in transactions" :key="actualTransaction.id">
              <td>
                  <div>
                  {{ actualTransaction.merchantName }}
                  </div>

              </td>
              <td>
                  <div>
                  {{ actualTransaction.amount }}
                  </div>

              </td>
              <td>
                  <div>
                  {{ actualTransaction.frequency }}
                  </div>
              </td>
              <td>
                  <div>
                  {{ actualTransaction.date }}
                  </div>
              </td>
              <td>
                  <div>
                  {{ actualTransaction.category }}
                  </div>
              </td>
          </tr>
      </tbody>
    </table>
</template>


<script >
import TheNavigation from "@/views/TheNavigation.vue";

    export default {
        data(){
            return {
            transactions: null,

            };
        },
        created () {
        this.getTransactions()
        },
        methods: {
        async getTransactions(){
        const response = await fetch("/api/transactions/getTransactions", {credentials: 'same-origin'});
        this.transactions = await response.json();
        console.log(this.transactions);
        },
        },

  components: {
    TheNavigation,
  },
}
</script>