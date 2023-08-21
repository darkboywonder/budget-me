<template>
  <div id = "nav">
    <ul>

      <li>
        <router-link to="/register"><img src="../../../main/java/com/myPersonalFinance/budgetme/assets/newUser.png" height="48" width="48"/>
          Register</router-link>
      </li>
      <li>
        <router-link to="/login"><img src="../../../main/java/com/myPersonalFinance/budgetme/assets/loginimage.png" height="48" width="48"/>
          Login</router-link>
      </li>
    </ul>
  </div>
  <br>
  <div id="page">
    <h1>Welcome to Budget Me</h1>
    <h2>Our budgeting app is designed to help you save money and reach your financial goals!</h2>
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
export default {

        created () {
        this.deleteCookie()
        },
        methods: {
        async deleteCookie(){
        const response = await fetch("/api/auth/logout", {credentials: 'same-origin'});
        console.log(response);
        },
        }
       }

</script>



