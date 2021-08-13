<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />
    <table></table>
    <button
      class="btn-cancel"
      v-on:click="
        toggleForm();
        clearForm();
      "
      v-text="showForm ? 'Cancel' : 'Create New Card'"
    ></button>
    <form v-if="showForm">
      <input
        class="field"
        placeholder="Question"
        type="text"
        v-model="flashcard.question"
        required
      />
      <div>
        <input
          class="field"
          placeholder="Answer"
          type="text"
          v-model="flashcard.answer"
          required
        />
      </div>
      <input
        class="field"
        placeholder="Enter keyword"
        type="text"
        v-model="keywordToAdd"
      />
      <button class="btn" @click.prevent="addNewKeyword">+Keyword</button>
      <div>
        <h4 v-show="this.flashcard.keywords.length === 0">
          At least 1 keyword required
        </h4>
        <div class="finished">
        <button
          class="btn"
          v-show="this.flashcard.keywords.length !== 0"
          v-on:click.prevent="addNewFlashcard"
        >
          Finished
        </button>
        <button
          class="btn"
          v-show="this.flashcard.keywords.length !== 0"
          v-on:click.prevent="resetKeywords"
        >
          Reset Keywords
        </button>
        </div>
      </div>
      <table class="keywordTable" v-show="this.flashcard.keywords.length !== 0">
        <th>Keywords</th>
        <tr v-for="keyword in this.flashcard.keywords" v-bind:key="keyword.id">
          <td>{{ keyword }}</td>
        </tr>
      </table>
    </form>
  </div>
</template>

<script>
import FlashcardService from "@/services/FlashcardService";
export default {
  name: "new-card-form",
  components: {},
  data() {
    return {
      flashcard: {
        userId: this.$store.state.user.id,
        question: "",
        answer: "",
        keywords: [],
      },
      keywordToAdd: "",
      showForm: false,
    };
  },
  methods: {
    addNewFlashcard() {
      this.addNewKeyword();
      FlashcardService.addCard(this.flashcard).then((response) => {
        if (response.status === 201 || response.status === 200) {
          this.clearForm();
          this.toggleForm();
          location.reload();
        }
      });
    },
    addNewKeyword() {
      if (
        this.keywordToAdd != "" &&
        !this.flashcard.keywords.includes(this.keywordToAdd.toLocaleLowerCase())
      ) {
        this.flashcard.keywords.push(this.keywordToAdd.toLocaleLowerCase());
        this.keywordToAdd = "";
      }
    },
    toggleForm() {
      this.showForm = !this.showForm;
    },
    resetKeywords() {
      this.flashcard.keywords = [];
    },
    clearForm() {
      this.flashcard.question = "";
      this.flashcard.answer = "";
      this.resetKeywords();
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
  /* font-family:'Roboto',sans-serif; */
  font-family: "Love Ya Like A Sister", sans-serif;
  font-weight: 400;
  color: #ffffff;
  background-color: #3369ff;
  box-shadow: inset 0 -0.6em 1em -0.35em rgba(0, 0, 0, 0.17),
    inset 0 0.6em 2em -0.3em rgba(255, 255, 255, 0.15),
    inset 0 0 0em 0.05em rgba(255, 255, 255, 0.12);
  text-align: center;
  position: relative;
}
.btn-cancel {
  display: inline-block;
  padding: 0.7em 1.7em;
  margin: 0 0.3em 0.3em 0;
  margin-top: 22px;
  margin-bottom: 22px;
  border-radius: 0.25rem;
  box-sizing: border-box;
  text-decoration: none;
  /* font-family:'Roboto',sans-serif; */
  font-family: "Love Ya Like A Sister", sans-serif;
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
  display: inline-block;
  height: 30px;
  width: 300px;
  background-color: white;
  display: inline-block;
  border-radius: 5px;
}

.finished {
  padding-top: 17px;
  padding-bottom: 10px;
}
</style>