<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />

    <form>
      <input
        class="field"
        required
        type="text"
        id="name"
        v-model="editedDeck.name"
      />
      <input
        class="field"
        required
        type="text"
        id="description"
        v-model="editedDeck.description"
      />
    </form>
    <button class="btn" v-on:click.prevent="editDeck">Submit</button>
    <button class="btn" v-on:click.prevent="cancelDeck">Cancel</button>
  </div>
</template>

<script>
import DeckService from "@/services/DeckService.js";
export default {
  name: "edit-deck-form",
  props: ["deckId", "name", "description"],
  data() {
    return {
      editedDeck: {
        deckId: this.deckId,
        userId: this.$store.state.user.id,
        name: this.name,
        description: this.description,
      },

      showForm: false,
    };
  },
  methods: {
    toggleForm() {
      this.showForm = !this.showForm;
    },
    editDeck() {
      if (this.editedDeck.name != "" && this.editedDeck.description != "") {
        DeckService.updateDeck(this.editedDeck).then((response) => {
          if (response.status === 200) {
            this.toggleForm();
            this.$router.push("/deckBoard");
          }
        });
      }
    },
    cancelDeck() {
      this.$router.push("/deckBoard");
    },
  },
};
</script>

<style>
.btn {
  display: inline-block;
  padding: 0.7em 1.7em;
  margin: 0 0.3em 0.3em 0;
  border-radius: 0.25rem;
  box-sizing: border-box;
  text-decoration: none;
  font-family: "Love Ya Like A Sister", sans-serif;
  /* font-family:'Roboto',sans-serif; */
  font-weight: 400;
  color: #ffffff;
  background-color: #3369ff;
  box-shadow: inset 0 -0.6em 1em -0.35em rgba(0, 0, 0, 0.17),
    inset 0 0.6em 2em -0.3em rgba(255, 255, 255, 0.15),
    inset 0 0 0em 0.05em rgba(255, 255, 255, 0.12);
  text-align: center;
  position: relative;
}

.field {
  height: 30px;
  width: 300px;
  background-color: white;
  display: inline-block;
  border-radius: 5px;
}
</style>