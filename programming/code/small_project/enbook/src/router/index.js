import { createRouter, createWebHistory } from "vue-router";
import WordList from "../views/WordList.vue";
import Knowledge from "../views/Knowledge.vue";

const routes = [
  { path: "/", component: WordList },
  { path: "/knowledge", component: Knowledge },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
