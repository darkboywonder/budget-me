<template>
<TheNavigation/>

  <br>
  <div id="page">
    <h1>You have successfully logged out!</h1>
    <h2>Please go "Home" to get started with your budget</h2>
    <p>
      To use our app your must register for an account by clicking the register link above or login in  if you have an
      existing account with the login link above. Once you have logged in you will be able to link any accounts you would
      like and see your expenses in real time. You will also have the chance to add any up coming expenses or create expenses
      to see how it will effect your budget.
    </p>
  </div>
</template>

<style >

</style>

<script>
import TheNavigation from "@/views/TheNavigation.vue";
export default {

        created () {
        this.deleteCookie()
        },
        methods: {
        async deleteCookie(){
        const response = await fetch("/api/auth/logout", {credentials: 'same-origin'});
        console.log(response);
        },
        },
         components: {
                    TheNavigation,
                    },
       }

</script>



