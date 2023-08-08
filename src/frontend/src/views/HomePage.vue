<template>
  <h1>Home Page</h1>
  <form @submit.prevent="setupTimers">
  <button><router-link to="/receipt/add">Add a receipt here!</router-link></button>
  <br>
  <button><router-link to="/receipt/view">View Receipts</router-link></button>
  <br>
  <button><router-link to="/login">Logout</router-link></button>
  </form>
</template>



export default {
      data() {
        return {

        };
      },
      methods: {
}

