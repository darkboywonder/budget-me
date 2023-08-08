<template>
  <h1>Home Page</h1>
  <form @submit.prevent="setupTimers">
  <button><router-link to="/receipt/add">Add a receipt here!</router-link></button>
  <button><router-link to="/login">Logout</router-link></button>
  </form>
</template>



export default {
      data() {
        return {

        };
      },
      methods: {
const timeoutInMS = 180000; // 3 minutes -> 3 * 60 * 1000
let timeoutId;

function handleInactive() {
    // Here you want to logout a user and/or ping your token
}

function startTimer() {
    // setTimeout returns an ID (can be used to start or clear a timer)
    timeoutId = setTimeout(handleInactive, timeoutInMS);
}

function resetTimer() {
    clearTimeout(timeoutId);
    startTimer();
}

function setupTimers () {
    document.addEventListener("keypress", resetTimer, false);
    document.addEventListener("mousemove", resetTimer, false);
    document.addEventListener("mousedown", resetTimer, false);
    document.addEventListener("touchmove", resetTimer, false);

    startTimer();
}

