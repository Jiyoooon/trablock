import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from "../views/Main.vue";
import groupMain from "../views/group/GroupMain.vue";
import groupCreate from "../views/group/GroupCreate.vue";

// post
import postMain from "../views/post/PostMain.vue";
import postMain2 from "../views/post/PostMain2.vue";
import postDetail from "../views/post/PostDetail.vue";
import postManage from "../views/post/PostManage.vue";
import postCreate from "../views/post/PostCreate.vue";


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
    
    {
      path: "/group/create",
      name: "groupcreate",
      component: groupCreate,
    },

    // post
    {
      path: "/post",
      name: "postpmain",
      component: postMain,
    },
    {
      path: "/post2",
      name: "postpmain2",
      component: postMain2,
    },
    // post CRUD
    {
      path: "/post/detail",
      name: "postdetail",
      component: postDetail,
    },
    {
      path: "/post/manage",
      name: "postmanage",
      component: postManage,
    },
    {
      path: "/post/create",
      name: "postcreate",
      component: postCreate,
    },
  ]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
