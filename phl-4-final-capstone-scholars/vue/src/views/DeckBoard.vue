<template>
  <div class="container">
    <my-decks-list />
    <deck-flashcards class="routerView" />
    <!-- <router-view :key="$route.path" class="routerView" />  -->
  </div>
</template>

<script>
import MyDecksList from "../components/MyDecksList.vue";
import DeckFlashcards from "./DeckFlashcards.vue";
export default {
  components: {
    MyDecksList,
    DeckFlashcards,
  },
};
</script>

<style>
body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
    "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
}
.container {
  width: 100%;
  max-width: 1100px;
  margin: 0 auto;
}
.loading {
  display: flex;
  justify-content: center;
  align-items: center;
}
/* .btn {
  display: inline-block;
  font-weight: 400;
  color: #212529;
  text-align: center;
  vertical-align: middle;
  border: 1px solid transparent;
  padding: .375rem .75rem;
  margin-right: 10px;
  margin-bottom: 5px;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: .25rem;
  cursor: pointer;
} */

.routerView {
  width: 79%;
  position: absolute;
  right: 0;
}
</style>