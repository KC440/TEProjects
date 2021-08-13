<template>
  <div>
    <my-cards-list />
    <new-card-form />
  </div>
</template>

<script>
import NewCardForm from "../components/NewCardForm.vue";
import MyCardsList from "../components/MyCardsList.vue";
export default {
  name: "myFlashcards",
  components: {
    NewCardForm,
    MyCardsList,
  },
};
</script>

<style>
</style>