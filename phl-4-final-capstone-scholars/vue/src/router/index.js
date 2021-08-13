import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import MyFlashcards from '../views/MyFlashcards'
import Deck from '../views/Deck'
import CardSearch from '../views/CardSearch'
import ModifyThisCard from '../views/ModifyThisCard'
import DisplayDeck from '../views/DisplayDeck.vue'
import DeckFlashcards from '../views/DeckFlashcards'
import DeckBoard from '../views/DeckBoard'
import NewCardForm from '../components/NewCardForm'
import AddFlashcardToDeck from '../components/AddFlashcardToDeck'
import SessionCurrentCard from '../views/SessionCurrentCard'
import NewSession from '../views/NewSession'
import ModifyThisDeck from '../components/ModifyThisDeck'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/myflashcards",
      name: "myflashcards",
      component: MyFlashcards,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/deck",
      name: "deck",
      component: Deck
    },
    {
      path: "/myDecks",
      name: "myDecks",
      component: DisplayDeck,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/myFlashcards/:id",
      name: "editFlashcard",
      component: ModifyThisCard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/searchCard",
      name: "searchCard",
      component: CardSearch
    },


    {
      path: "/session",
      name: "session",
      component: NewSession
    },

    {
      path: "/deck/:id",
      name: "viewDeckContents",
      component: DeckFlashcards
    },
    {
      path: "/deckBoard",
      name: "deckBoard",
      component: DeckBoard

    },

    {
      path: "/deckBoard/:id",
      name: "deckFlashcards",
      component: DeckFlashcards

    },

    {
      path: "/addFlashcard",
      name: "addFlashcard",
      component: NewCardForm

    },

    {
      path: "/addFlashcardToDeck",
      name: "addFlashcardToDeck",
      component: AddFlashcardToDeck

    },
    {
      path: "/session/:deckId/:cardIndex",
      name: "sessionThisCard",
      component: SessionCurrentCard
    },


    {
      path: "/deck/update",
      name: "modifyDeck",
      component: ModifyThisDeck
    }

  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;