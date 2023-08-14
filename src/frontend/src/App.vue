<template>
 <LandingPage/>

  <div class="bg"></div>
  <div class="bg bg2"></div>
  <div class="bg bg3"></div>


     <!-- Navigation -->
       <div class="container">
          <div class="nav-items">
            <div class="link-container">
              <router-link to="/login">
                        <div class="login-link">
                          <img src="@/assets/loginimage.png" alt="Login" />
                          <span>Login</span>
                        </div>
              </router-link>
              <router-link to="/register">
                        <div class="register-link">
                            <img src="@/assets/newUser.png" alt="Register Image" />
                            <span>Register</span>
                        </div>
              </router-link>
              <router-link to="/home">
                        <div class="budget-logo">
                            <img src="@/assets/budgetlogo.png" alt="Budget Logo" />
                            <span>Home</span>
                        </div>
              </router-link>
              <router-link to="/login">
                        <div class="logout-link">
                            <img src="@/assets/logout.png" alt="Logout Image" />
                            <span>Logout</span>
                        </div>
              </router-link>
          </div>
          </div>

        <router-view></router-view>
   </div>
</template>


<style>

.nav-items {
  display: flex;
  justify-content: center;
}
.link-container {
    display: flex;
    align-items: center;
  }
.login-link, .register-link, .budget-logo, .logout-link {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    margin-right: 10px;
}
.budget-logo img {
    width: 64px
}
#page {
  background-color: rgba(245, 245, 245, 0.75);
  border-radius: .25em;
}

html {
  height:100%;
}

body {
  margin:0;
}

.bg {
  animation:slide 3s ease-in-out infinite alternate;
  background-image: linear-gradient(-60deg, rgba(102, 204, 51, 0.5) 50%, rgba(0, 153, 255, 0.5) 50%);
  bottom:0;
  left:-100%;
  opacity:.5;
  position:fixed;
  right:-100%;
  top:0;
  z-index:-1;
}

.bg2 {
  animation-direction:alternate-reverse;
  animation-duration:4s;
}

.bg3 {
  animation-duration:5s;
}



h1 {
  font-family:monospace;
}

@keyframes slide {
  0% {
    transform:translateX(-25%);
  }
  100% {
    transform:translateX(25%);
  }
}

html {
  background-color: rgba(0, 153, 255, 0.25);
}
  body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
  }

  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
  }

  li {
    float: left;
    margin-bottom: 5px;

  }

  div {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
  }

  h1,h2 {
    margin-bottom: 20px;
    text-align: center;
  }

  form {
    margin-bottom: 20px;
  }

  label {
    display: block;
    margin-bottom: 5px;
  }

  input[type="text"],
  input[type="number"],
  input[type="password"] {
    width: 100%;
    padding: 5px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  button [type="submit"] {
    display: block;
    width: 50%;
    margin: auto;
    padding: 10px;
    background-color: #4caf50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
  }

</style>

<script>
export default {
  methods: {
}
}
</script>


