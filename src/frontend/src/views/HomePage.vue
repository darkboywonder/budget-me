<template>
  <TheNavigation/>
  <br>
  <div id="page">
    <h1>Welcome to Budget Me</h1>
      <router-link to="/login">
            <button @click="login">Login Here</button>
      </router-link>
      <router-link to="/register">
            <button @click="register">Register Here</button>
      </router-link>



    <h2>Choose an account to Link
      <button @click="connectToBank">Link Account</button>
      <button @click="getTransactions">Get Transactions</button>

    </h2>
  <br>
  <br>
    <h2>
      Learn how to manage your money and become <br>debt-free, while saving for your
      financial goals.
      <router-link to="/expenses" custom v-slot="{ navigate }"><button @click="navigate" role="link">Start my budget</button></router-link>

    </h2>
        <div>{{}}</div>
  </div>
</template>
<style>

</style>

<script>
import TheNavigation from "@/views/TheNavigation.vue";
export default {
  mounted(){
      let Plaid = document.createElement('script')
      Plaid.setAttribute('src', 'https://cdn.plaid.com/link/v2/stable/link-initialize.js')
      document.head.appendChild(Plaid)
    },

  name: 'HomePage',
  components: {
    TheNavigation,
  },

  data() {
    return {
     answer: "",
     pl: window.Plaid,


    };
  },
methods: {


//Gets the link token as a string and returns it

      async getLinkToken() {
         const linkTokenResponse =  await fetch("api/plaid/generateLinkToken");
         const data = await linkTokenResponse.json();
         const linkToken = data.linkToken;
         console.log(linkToken);
         return linkToken;
         },

//Link popup init and public token handler

    async connectToBank() {
      const handler = window.Plaid.create({
         token : await this.getLinkToken(),

         onSuccess: async (public_token) => {

            await fetch ("api/plaid/createAccessToken", {
                method: 'POST',
                credentials: 'include',
                headers: {'Content-Type': 'text/plain'},
                body: public_token
            });
           },
         onExit: async (err, metadata) => {
           console.log(
             `Exited early. Error: ${JSON.stringify(err)} Metadata: ${JSON.stringify(
               metadata
             )}`
           );
         },
         onEvent: (eventName, metadata) => {
           console.log(`Event ${eventName}, Metadata: ${JSON.stringify(metadata)}`);
         },
       });
       handler.open();

      },
      async getTransactions(){
            const response = await fetch("api/plaid/getTransactionData");
            console.log(response);

            },

    },

         }


</script>