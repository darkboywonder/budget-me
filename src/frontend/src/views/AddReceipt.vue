<template>
<Add />
<router-view>
</router-view>
<div class="vue-template">
 <form @submit.prevent="processReceipt">
            <h1>Add Receipts</h1>
            <div>
              <label for="title">Business Name:</label>
              <input v-model="title" type="text" placeholder="Name" required />
            </div>
            <div>
              <label for="tag">Tags:</label>
              <ul>
                <li>
                <input v-model="tag" type="text" placeholder="Tag"  />

                </li>
              </ul>
            </div>
            <div>
                <label for="total">Total:</label>
                <input v-model="amount" type="text" placeholder="$ total" />
            </div>
            <div>
              <label for="date">Date:</label>
              <input v-model="date" type="date" required />
            </div>


            <button>Submit</button>
            <br>
            <button><router-link to="/receipt/view">View Receipts</router-link></button>
 </form>
 </div>
</template>

 <script>
    export default {
      data() {
        return {
          title: "",
          tag: [],
          amount: "",
          date: "",
        };
      },
      methods: {
        async processReceipt() {
            const receipt = {
                title: this.title,
                tag: this.tag,
                amount: this.amount,
                date: this.date,
            };

            try {
                const response = await fetch("/api/receipt/add", {
                          method: "POST",
                          headers: { "Content-Type": "application/json" },
                          body: JSON.stringify(receipt),
                        });

                        if (response.ok) {

                         this.$router.push('/receipt/view');

                        }
            } catch (error) {

               console.error("Error posting receipt", error);

               this.errorMessage = "Please try again";
             }
        },
     },
 };
 </script>