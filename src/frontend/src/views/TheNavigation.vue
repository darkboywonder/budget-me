<template>
  <div id = "nav">
    <ul>

      <li>
        <router-link  to="/home" ><img src="../../../main/java/com/myPersonalFinance/budgetme/assets/budgetlogo.png" height="48" width="48"/>
          Home</router-link>
      </li>

      <li>
      <router-link to="/transactions">
      <img src="../../../main/java/com/myPersonalFinance/budgetme/assets/moneyImage.png" height="48" width="48"/>
      Transactions </router-link>
      </li>

      <li>
        <router-link to="/"><img src="../../../main/java/com/myPersonalFinance/budgetme/assets/leave.png" height="48" width="48"/>
          Logout</router-link>
      </li>

    </ul>

  </div>
</template>

<style >
  #nav {
    background-color: rgba(102, 204, 51, 0.5);
    font-size: large;
    border-radius:.25em;

  }
  #nav .active-link {
    color: black;
  }
  a {
    text-decoration: none;
  }

</style>

<script >

</script>



