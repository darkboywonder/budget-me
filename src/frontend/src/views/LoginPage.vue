<template>
  <router-view></router-view>
  <div id="page">

        <form @submit.prevent="loginUser">
            <h1>Login</h1>
            <div>
              <label for="username">Username:</label>
              <input v-model="username" type="text" placeholder="Username" />
            </div>
            <div>
              <label for="password">Password:</label>
              <input v-model="password" type="password" placeholder="Password" />
            </div>
            <button type="submit">Login</button>

            <p class="errorMessage" v-if="errorMessage">{{ errorMessage }}</p>

            <p>
                <router-link to="/register">Don't have an account? Register Here!</router-link>
            </p>

        </form>
    </div>

</template>

<style>
    .errorMessage {
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
      errorMessage: "",
    };
  },
  methods: {
    async loginUser() {
      const user = {
        username: this.username,
        password: this.password,
      };

      try {
              const response = await fetch("/api/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(user),
              });


              if (response.ok) {

                    this.$router.push('/home');


                } else if (!response.ok) {

                  this.errorMessage = "Invalid credentials. Please try again or register.";
                }
               else {

                this.errorMessage = "Login failed. Please try again later.";

              }
            } catch (error) {

              console.error("Error during login", error);

              this.errorMessage = "Username not found. Please register for an account!";
            }
    },
  },
};
</script>