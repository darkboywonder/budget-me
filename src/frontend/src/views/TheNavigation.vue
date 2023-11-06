<template>
  <div class="navbar">
    <ul>
        <li><button class="home-button">
            <router-link  to="/home" ><img src="../../../main/java/com/myPersonalFinance/budgetme/assets/budgetlogo.png" height="48" width="48"/>Home</router-link>
        </button></li>

        <li><button class="transactions-button">
            <router-link to="/transactions">
                <img src="../../../main/java/com/myPersonalFinance/budgetme/assets/moneyImage.png" height="48" width="48"/>Transactions</router-link>
        </button></li>

        <li><button class="receipts-button">
            <router-link to="/receipt/view">
                <img src="@/assets/receipt.png" height="48" width="48" />Receipts</router-link>
        </button></li>

        <li><button class="logout-button">
            <router-link to="/logout"><img src="../../../main/java/com/myPersonalFinance/budgetme/assets/leave.png" height="48" width="48"/>Logout</router-link>
        </button></li>
    </ul>


  </div>
</template>

<style >
  ul {
    overflow: hidden;
    background-color: #42cde1;
    position: fixed;
    top: 0;
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
          display: inline-block;

        }
        ul li a {
          display: block;
          color: #eeeeee;
          text-align: center;
          padding: 15px 30px 15px 20px;
          text-decoration: none;
          font-size: 18px;
          font-weight: bold;
          font-family: arial;
        }


  li a:hover {
    background-color: #00ff0070;
    border-radius: 20px;
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
    overflow: hidden;
    position: fixed;
    top: 0;
    width: 100%;
    background-color: rgba(102, 204, 51, 0.5);
    max-width: 700px;
    margin: 0 auto;
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



