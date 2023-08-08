<template>


 <div class="vue-template">
     <!-- Navigation -->
       <div class="container">
           <div class="nav-item"><router-link class="nav-link pr-3" to="/login">Log In</router-link></div>
           <div class="nav-item"><router-link to="/register">Register</router-link></div>
           <div class="nav-item"><router-link to="/login">Logout</router-link></div>



       </div>


     <!-- Main -->
     <div class="App">

       <div class="vertical-center">

         <div class="inner-block">
           <router-view />
         </div>
       </div>
     </div>
   </div>

</template>


<style>

</style>

<script>
export default {
  methods: {
    async logout() {
      try {
        const response = await fetch("/api/logout", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
        });

        if (response.ok) {

          this.$router.push("/login");
        } else {
          console.error("Logout failed");
        }
      } catch (error) {
        console.error("Error during logout", error);
      }
    },
  },
};
</script>


