<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />
    <h2>Edit This Card</h2>
    <div>
      <edit-flashcard-form
        :question="flashcard.question"
        :cardId="flashcard.flashCardId"
        :answer="flashcard.answer"
        :keywords="flashcard.keywords"
      />
    </div>
    <div
      class="flashcardDisplay"
      id="cardFront"
      v-show="this.showFront"
      @click="flipCard"
    >
      <h4 class="cardHead">Front</h4>
      <h1 class="cardText">{{ this.flashcard.question }}</h1>
    </div>
    <div
      class="flashcardDisplay"
      id="cardBack"
      v-show="!this.showFront"
      @click="flipCard"
    >
      <h4 class="cardHead">Back</h4>
      <h1 class="cardText">{{ this.flashcard.answer }}</h1>
    </div>
    <div>
      <div class="delete-btn">
        <button
          class="btn"
          v-show="!showDelConfirm"
          v-on:click="toggleDelConfirm"
        >
          Delete This Card
        </button>
      </div>
      <div class="nav-cards">
        <button class="cardBtn">
        <router-link :to="{ name: 'myflashcards' }" class="cardLink">Go To Flashcards</router-link>
        </button>
        <button class="cardBtn">
        <router-link :to="{ name: 'searchCard' }" class="search">Search Flashcards</router-link>
        </button>
      </div>
      <div v-show="showDelConfirm">
        <h2>Are you sure you want to delete this card?</h2>
        <div>
          <button class="btn" v-on:click="deleteThisCard">Yes</button>
          <button class="btn" v-on:click="toggleDelConfirm">No</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FlashcardService from "@/services/FlashcardService";
import EditFlashcardForm from "@/components/EditFlashcardForm";
export default {
  name: "modifyThisCard",
  components: {
    EditFlashcardForm,
  },
  data() {
    return {
      flashcard: {
        flashCardId: this.$route.params.id,
        question: "",
        answer: "",
        keywords: [],
      },
      showFront: true,
      showDelConfirm: false,
    };
  },
  created() {
    let thisCard = this.$store.state.flashcards.find((card) => {
      return card.flashCardId == this.$route.params.id;
    });
    this.flashcard.question = thisCard.question;
    this.flashcard.answer = thisCard.answer;
    this.flashcard.keywords = thisCard.keywords;
  },
  methods: {
    flipCard() {
      this.showFront = !this.showFront;
    },
    toggleDelConfirm() {
      this.showDelConfirm = !this.showDelConfirm;
    },
    deleteThisCard() {
      FlashcardService.deleteCard(this.flashcard.flashCardId).then(
        (response) => {
          if (response.status === 200 || response.status === 204) {
            this.$router.push("/myflashcards");
          }
        }
      );
    },
  },
};
</script>

<style>
.flashcardDisplay {
  border: 2px solid black;
  border-radius: 10px;
  width: 650px;
  height: 300px;
  margin: 0 auto;
  background-image: url("../images/noteCard.jpg");
  background-size: cover;
  cursor: pointer;
}
.delete-btn {
  margin-top: 25px;
}
.cardText {
  margin: 0 auto;
  font-size: 70px;
  color: navy;
  font-family: "Love Ya Like A Sister", sans-serif;
}
.keywordTable {
  margin: 0 auto;
  color: white;
  font-family: "Love Ya Like A Sister", sans-serif;
}
.cardHead {
  font-size: 30px;
  color: black;
  font-family: "Love Ya Like A Sister", sans-serif;
}
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
.cardBtn {
  display: inline-block;
  padding: 0.7em 1.7em;
  margin: 0 0.3em 0.3em 0;
  margin-top: 16px;
  border-radius: 0.2em;
  box-sizing: border-box;
  text-decoration: none;
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
.cardLink {
  color: white;
}
.cardLink:link {
  color: white;
  text-decoration: none;
}
.search {
  color: white;
}
.search:link {
  color: white;
  text-decoration: none;
}
</style>