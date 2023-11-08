<template>
  <div class="navbar">
    <ul>
        <li><button class="home-button">
            <router-link  to="/home" ><img src="../../../main/java/com/myPersonalFinance/budgetme/assets/budgetlogo.png" height="25" width="25"/>  Home</router-link>
        </button></li>

        <li><button class="transactions-button">
            <router-link to="/transactions">
                <img src="../../../main/java/com/myPersonalFinance/budgetme/assets/moneyImage.png" height="25" width="25"/>  Transactions</router-link>
        </button></li>

        <li><button class="receipts-button">
            <router-link to="/receipt/view">
                <img src="@/assets/receipt.png" height="25" width="25" />  Receipts</router-link>
        </button></li>

        <li><button class="logout-button">
            <router-link to="/logout"><img src="../../../main/java/com/myPersonalFinance/budgetme/assets/leave.png" height="25" width="25"/>  Logout</router-link>
        </button></li>

        <li class="title"><h1>Budget Me - Your friendly budgeting app!</h1></li>
    </ul>


  </div>
</template>

<style >
  .title {
        display: flex;
        align-items: center;
        flex: 1;
        justify-content: center;
  }

  .title h1 {
        font-family: Trebuchet MS, sans-serif;
        font-size: 25px;
        margin: 0;
        text-align: center;
  }

  ul {
        display: flex;
        align-items: center;
        overflow: hidden;
        background-color: #42cde1;
        position: fixed;
        top: 0;
        left: 0;
        margin: 0;
        padding: 0;
        width: 100%;
        list-style-type: none;
        overflow: hidden;
        background-color: rgba(102, 204, 51, 0.5);
        margin-left: 0;
  }

   ul li {
        list-style-type: none;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-right: 20px;
   }

   ul li a {
        display: block;
        color: #eeeeee;
        text-align: center;
        padding: 5px 20px;
        text-decoration: none;
        font-size: 18px;
        font-weight: bold;
        font-family: arial;
   }

  li a:hover {
        background-color: rgba(102, 204, 51, 0.5);
  }

  .home-button, .transactions-button, .receipts-button, .logout-button {
        float: left;
        display: block;
        text-align: center;
        padding: 5px 8px;
        text-decoration: none;
        font-size: 18px;
        border-style: ridge;
        border-color: rgba(102, 204, 51, 0.5);
        border-radius: 0px;

  }


  .navbar {
        display: flex;
        justify-content: space-around;
        align-items: center;
        overflow: hidden;
        position: fixed;
        top: 0;
        width: 100%;
        background-color: rgba(102, 204, 51, 0.5);
        max-width: 700px;
        padding: 0px;
        margin-left: 0;
  }

  .navbar .active-link {
        color: blue;
  }

  a {
        text-decoration: none;
  }
</style>

<script >
</script>