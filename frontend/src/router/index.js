import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from "../views/Main.vue";
import groupMain from "../views/group/GroupMain.vue";
import groupCreate from "../views/group/GroupCreate.vue";
import groupDetail from "../views/group/GroupDetail.vue";

// post
import postMain from "../views/post/PostMain.vue";
import postDetail from "../views/post/PostDetail.vue";
import postManage from "../views/post/PostManage.vue";
import postCreate from "../views/post/PostCreate.vue";

// accout
import Profile from "../views/account/Profile.vue";

import { auth } from '@/store/auth.module';


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

    {
      path: "/group/detail",
      name: "groupdetail",
      component: groupDetail,
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
    // account
    {
      path: '/profile',
      name: 'Profile',
      component: Profile,
      beforeEnter(from, to, next) {
        if (!auth.state.status.loggedIn) {
          next('/auth')
        } else {
          next()
        }
      }
    },
  ]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
