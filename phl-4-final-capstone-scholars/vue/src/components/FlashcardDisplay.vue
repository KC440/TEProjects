<template>
  <div>
    <div class="area" id="areaone">
      <span>Area One</span>
      <new-card-form />
    </div>

    <div class="area" id="areatwo">
      <span>Area Two</span>
      <my-cards-list />
    </div>
  </div>
</template>

<script>
// import MyCardsList from './MyCardsList.vue'
import NewCardForm from "./NewCardForm.vue";
export default {
  components: { NewCardForm },
};
</script>

<style>
cardForm {
  grid-area: cardForm;
}

body {
  margin: 0;
  padding: 0;
  display: grid;
  grid-template-columns: 1fr 3fr;
  gap: 15px;
}
</style>