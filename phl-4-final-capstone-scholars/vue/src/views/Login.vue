<template>
  <div id="login" class="text-center">
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Homemade+Apple"
    />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Mukta"
    />
    <h1 class="title">Scholars</h1>
    <img
      class="imgHat"
      src="@/images/hat.png"
      alt="An image of a graduation cap"
    />
    <h2 class="h3 mb-3 font-weight-normal">Please Sign In</h2>
    <form class="form-signin" @submit.prevent="login">
      <div
        id="invalid"
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >
        Invalid username and/or password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <div>
        <router-link :to="{ name: 'register' }" class="rLink"
          >CREATE ACCOUNT</router-link
        >
      </div>
      <button class="btnSubmit" type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
      hover: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style>
.sr-only {
  display: block;
  padding: 5px;
}
.rLink {
  padding: 10px;
}
.btnSubmit {
  text-align: center;
  margin: 10px;
  background-color: white;
  color: black;

  /* display:inline-block;
        padding:0.7em 1.7em;
        margin:0 0.3em 0.3em 0;
        margin-top: 17px;
        border-radius:0.25rem;
        box-sizing: border-box;
        text-decoration:none;
        font-family: "Love Ya Like A Sister", sans-serif;
        font-weight:400;
        color:#FFFFFF;
        background-color:#3369ff;
        box-shadow:inset 0 -0.6em 1em -0.35em rgba(0,0,0,0.17),inset 0 0.6em 2em -0.3em rgba(255,255,255,0.15),inset 0 0 0em 0.05em rgba(255,255,255,0.12);
        text-align:center;
        position:relative; */
}
.form-signin {
  text-align: center;
}
.title {
  padding-left: 18px;
  text-align: center;
}
.imgHat {
  display: block;
  margin: 0 auto;
  height: 200px;
}
body {
  background: url(../images/chalkboard.jpg);
  cursor: url(../images/cursor2.png), none;
}
button {
  cursor: url(../images/cursor2.png), none;
}
h2 {
  position: relative;
  display: inline-block;
  font-family: "Love Ya Like A Sister", sans-serif;
  color: white;
  padding-top: 10px;
  padding-bottom: 10px;
}
#invalid {
  color: red;
}

.rLink:link {
  font-family: "Love Ya Like A Sister", sans-serif;
  color: white;
}
.rLink:visited {
  color: white;
}
.sr-only {
  font-size: 16px;
  font-family: "Love Ya Like A Sister", sans-serif;
  padding: 0.25em 0.5em;
}
.btnSubmit {
  font-family: "Love Ya Like A Sister", sans-serif;
  color: black;
  background: linear-gradient(black, white 10%);
}

.title {
  font-family: "Homemade Apple", sans-serif;
  color: white;
  font-size: 75px;
  text-align: center;
  padding-left: 60px;
}
</style>