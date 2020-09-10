import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from "../views/Main.vue";
import groupMain from "../views/group/GroupMain.vue";

Vue.use(VueRouter)

  const routes = [
    {
      path: "/",
      name: "main",
      component: Main,
    },
    {
      path: "/group",
      name: "groupmain",
      component: groupMain,
    },
  ]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
