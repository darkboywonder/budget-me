<template>

  <router-view></router-view>
<div id="page">
 <div class="page"></div>

      <form @submit.prevent="registerUser">

        <h1>Sign Up</h1>

        <div>
          <label for="username">Username</label>
          <input v-model="username" type="text" placeholder="Username" />
          <p class="error" v-if="usernameError">{{ usernameError }}</p>
        </div>

        <div>
          <label for="password">Password</label>
          <input v-model="password" type="password" placeholder="Password" />
          <p class="error" v-if="passwordError">{{ passwordError }}</p>
        </div>

        <div>
          <label for="verifyPassword">Verify Password</label>
          <input v-model="verifyPassword" type="password" placeholder="Verify Password" />
          <p class="error" v-if="verifyError">{{ verifyError }}</p>
        </div>

        <button type="submit">Sign Up</button>

        <p>
           <router-link to="/login">Already have an account? Login Here!</router-link>
        </p>

      </form>
   </div>
 </template>


<style>
    .error {
    color: maroon;
    font-weight: bold;
    font-size: 14pt;
    }
</style>

<script>
    export default {
      data() {
        return {
          username: "",
          password: "",
          verifyPassword: "",
          usernameError: "",
          passwordError: "",
          verifyError: "",
        };
      },
      methods: {
        async registerUser() {
            this.usernameError = "";
            this.passwordError = "";
            this.verifyError = "";
          if (this.username == "" || this.username == null) {
            this.usernameError = "Username is required";
            return;
          }
          if (this.username.length < 5) {
            this.usernameError = "Username must be at least 5 characters long";
            return;
          }
          if (!this.password) {
            this.passwordError = "Password is required";
            return;
          }

          const passwordRegex = /^(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+$/;
            if (!passwordRegex.test(this.password) || this.password.length < 7) {
               this.passwordError = "Password must be at least 7 characters long and must contain at least one number and one symbol";
               return;
          }
          if (this.password !== this.verifyPassword) {
            this.verifyError = "Passwords do not match";
            return;
          }

          const user = {
            username: this.username,
            password: this.password,
          };

          try {
            const response = await fetch("/api/auth/register", {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(user),
            });

            if (response.ok) {
              this.$router.push('/home');

            } else if (response.status === 409) {
              alert("Username already exists. Please login or register for a new account.");

            } else {
              alert("Registration failed");

            }
          } catch (error) {
            console.error("Error during registration", error);

          }
        },
      },
    };
    </script>