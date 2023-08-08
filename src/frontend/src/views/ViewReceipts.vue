<template>
<ViewReceipt />
<router-view>
</router-view>
<div class="container">

            <h1>Your Receipts</h1>

     <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>Tag</th>
                <th>Amount</th>
                <th>Date</th>

            </tr>
        </thead>
        <tbody>
            <tr v-for="receipt in receipts" :key="receipt.id">
            <td>{{ receipt.title }}</td>
            <td>{{ receipt.tag }}</td>
            <td>{{ receipt.amount }}</td>
            <td>{{ receipt.date }}</td>
            </tr>
        </tbody>
        </table>
            <button><router-link to="/receipt/add">Add</router-link></button>
            <button>Edit</button>
            <button>Delete</button>


 </div>
</template>

<script>

 export default {

      data() {
        return {
          title: "",
          tag: "",
          amount: "",
          date: "",
        };
      },
      methods: {
        async viewReceipt() {
                const receipt = {
                      title: this.title,
                      tag: this.tag,
                      amount: this.amount,
                      date: this.date,
                  };
            try {
                const response = await fetch("/api/receipt/view", {
                          method: "GET",
                          headers: { "Content-Type": "application/json" },
                          body: JSON.stringify(receipt),
                        });
                         if (response.ok) {
                            this.$router.push('/receipt');

                         }
            } catch (error) {

                console.error("Error posting receipt", error);

                this.errorMessage = "Please try again";
                }


}
     },
 };
 </script>