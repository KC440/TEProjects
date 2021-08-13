<template>
  <div>
    <new-deck-form />
    <!-- <add-to-deck-list /> -->
  </div>
</template>

<script>
import NewDeckForm from "../components/NewDeckForm.vue";
// import AddToDeckList from '../components/AddToDeckList.vue'

export default {
  name: "deck",
  components: {
    NewDeckForm,
    // AddToDeckList,
  },
};
</script>

<style>
</style>