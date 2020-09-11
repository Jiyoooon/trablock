import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from "../views/Main.vue";
import groupMain from "../views/group/GroupMain.vue";

// post
import postMain from "../views/post/PostMain.vue";
import postDetail from "../views/post/PostDetail.vue";


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

    // post
    {
      path: "/post",
      name: "postpmain",
      component: postMain,
    },
    // post CRUD
    {
      path: "/post/detail",
      name: "postDetail",
      component: postDetail,
    },
  ]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
