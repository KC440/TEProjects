<template>
  <div>
    <!-- <div class="mainContainer"> -->
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />
    <h2>My Flashcards</h2>
    <div class="keywords">
      <input
        class="field"
        type="text"
        placeholder="Search by Keywords"
        v-model="keywordToAdd"
      />
      <button class="btn" v-on:click.prevent="addSearchKeyword">
        +Keyword
      </button>
      <button class="btn" v-on:click.prevent="clearFilters">
        Reset Search Parameters
      </button>
    </div>
    <div class="miniCardContainer">
      <router-link
        class="miniCard"
        :to="{ path: `/myFlashcards/${card.flashCardId}` }"
        v-for="card in this.filteredList"
        v-bind:key="card.id"
      >
        {{ card.question }}
      </router-link>
    </div>
    <table class="keywordTable" v-show="this.filters.length != 0">
      <th>Search Parameters</th>
      <tr v-for="param in this.filters" v-bind:key="param.id">
        <td>{{ param }}</td>
      </tr>
    </table>
  </div>
  <!-- </div> -->
</template>

<script>
import FlashcardService from "@/services/FlashcardService";
export default {
  name: "my-cards-list",
  components: {},
  created() {
    FlashcardService.getMyCards(this.$store.state.user.id).then((response) => {
      const cards = response.data;
      this.$store.commit("SET_FLASHCARDS", cards);
      return cards;
    });
  },
  data() {
    return {
      filters: [],
      keywordToAdd: "",
    };
  },
  computed: {
    filteredList() {
      let newList = this.$store.state.flashcards;
      if (this.filters.length != 0) {
        for (let i = 0; i < this.filters.length; i++) {
          newList = newList.filter((card) => {
            return card.keywords.includes(this.filters[i]);
          });
        }
      }
      return newList;
    },
  },
  methods: {
    addSearchKeyword() {
      if (
        this.keywordToAdd != "" &&
        !this.filters.includes(this.keywordToAdd.toLocaleLowerCase())
      ) {
        this.filters.push(this.keywordToAdd.toLocaleLowerCase());
        this.keywordToAdd = "";
      }
    },
    clearFilters() {
      this.filters = [];
    },
  },
};
</script>

<style>
.miniCard {
  margin: 7px;
  padding: 10px;
  border: 1px solid black;
  width: 250px;
  height: 125px;
  align-content: center;
  border-radius: 3px;
  background-image: url("../images/noteCard.jpg");
  background-size: cover;
  color: navy;
  text-decoration: none;
  font-size: 40px;
  font-family: "Love Ya Like A Sister", sans-serif;
  cursor: pointer;
  line-height: 125px;
}
.miniCardContainer {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  border: 3px solid antiquewhite;
  border-radius: 20px;
  width: 75vw;
  margin: 0 auto;
}
h2 {
  font-size: 40px;
}
.keywords {
  margin-bottom: 22px;
}

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
  display: inline-block;
  height: 30px;
  width: 300px;
  background-color: white;
  display: inline-block;
  border-radius: 5px;
}

/*this is change*/

/* .mainContainer {
 display: grid;
 grid-template-columns: 300px 1fr 250px;
 grid-template-rows: 100px 1fr 1fr;
 gap: 15px;
  grid-template-areas:
 "header header header"
 "miniCardContainer miniCardContainer miniCardContainer"
 "miniCardContainer miniCardContainer miniCardContainer";

}
.miniCardContainer{
 grid-area: miniCardContainer;
}

.miniCardContainer {
 display: flex;
 justify-content: space-around;
}

.miniCardContainer {
 flex-direction: column;
} */
</style>