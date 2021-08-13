<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />
    <button
      class="btn"
      v-show="showForm && editedFlashcard.keywords.length != 0"
      v-on:click.prevent="editCard"
    >
      Submit
    </button>
    <div class="btn-submit">
      <button
        class="btn"
        v-on:click.prevent="toggleForm"
        v-text="showForm ? 'Cancel' : 'Edit this Card'"
      ></button>
    </div>
    <form v-if="showForm">
      <input
        class="field"
        required
        type="text"
        id="editFront"
        v-model="editedFlashcard.question"
      />
      <input
        class="field"
        required
        type="text"
        id="editBack"
        v-model="editedFlashcard.answer"
      />
      <input
        class="field"
        type="text"
        placeholder="Add Keywords"
        v-model="keywordToAdd"
      />
      <button class="btn" v-on:click.prevent="addKeyword()">+Keyword</button>
      <h4 v-show="editedFlashcard.keywords.length === 0">
        At least 1 keyword required
      </h4>
      <table v-show="editedFlashcard.keywords.length != 0" class="keywordTable">
        <th>Keywords</th>
        <tr v-for="keyword in editedFlashcard.keywords" v-bind:key="keyword.id">
          <td>{{ keyword }}</td>
        </tr>
      </table>
      <button
        class="btn"
        v-on:click.prevent="resetKeywords"
        v-show="editedFlashcard.keywords.length != 0"
      >
        Clear Keywords
      </button>
    </form>
  </div>
</template>

<script>
import FlashcardService from "@/services/FlashcardService.js";
export default {
  name: "edit-flashcard-form",
  props: ["cardId", "question", "answer", "keywords"],
  data() {
    return {
      editedFlashcard: {
        flashCardId: this.cardId,
        userId: this.$store.state.user.id,
        question: this.question,
        answer: this.answer,
        keywords: this.keywords,
      },
      keywordToAdd: "",
      showForm: false,
    };
  },
  methods: {
    toggleForm() {
      this.showForm = !this.showForm;
    },
    resetKeywords() {
      this.editedFlashcard.keywords = [];
    },
    addKeyword() {
      if (
        this.keywordToAdd != "" &&
        !this.editedFlashcard.keywords.includes(
          this.keywordToAdd.toLocaleLowerCase()
        )
      ) {
        this.editedFlashcard.keywords.push(
          this.keywordToAdd.toLocaleLowerCase()
        );
        this.keywordToAdd = "";
      }
    },
    editCard() {
      if (
        this.editedFlashcard.question != "" &&
        this.editedFlashcard.answer != ""
      ) {
        FlashcardService.updateCard(this.editedFlashcard).then((response) => {
          if (response.status === 200) {
            this.toggleForm();
            this.$router.push("/myflashcards");
          }
        });
      }
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
.btn-submit {
  margin-bottom: 20px;
}

.field {
  height: 30px;
  width: 300px;
  background-color: white;
  display: inline-block;
  border-radius: 5px;
}
</style>