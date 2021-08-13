<template>
  <div id="app">
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />
    <div id="nav">
      <div class="main">
        <div>
          <router-link class="home" v-bind:to="{ name: 'home' }"
            >Home</router-link
          >&nbsp;&nbsp;
        </div>
        <div>
          <router-link
            class="logout"
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            >Logout</router-link
          >
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>

<style>
.home {
  font-family: "Love Ya Like A Sister", sans-serif;
  font-size: 25px;
  display: inline-block;
}
.home:link {
  color: turquoise;
  text-decoration: none;
}
.home:visited {
  color: turquoise;
}
.logout {
  font-family: "Love Ya Like A Sister", sans-serif;
  font-size: 25px;
  display: inline-block;
}
.logout:visited {
  color: yellowgreen;
}
.home:hover {
  color: yellowgreen;
}
.logout:link {
  color: yellowgreen;
  text-decoration: none;
}
.logout:hover {
  color: turquoise;
}
.main {
  list-style-type: none;
  align-items: center;
  justify-content: space-between;
  display: flex;
}
</style>

