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
      beforeEnter(from, to, next) {
        console.log(auth.state.status.loggedIn);
        if (auth.state.status.loggedIn) {
          alert('이미 로그인 상태입니다.')
          next('/group')
        } else {
          next()
        }
      }
    },
    {
      path: "/group",
      name: "groupmain",
      component: groupMain,
      beforeEnter(from, to, next) {
        if (!auth.state.status.loggedIn) {
          alert("로그인 후 이용해주세요!");
          next('/')
        } else {
          next()
        }
      }
    },
    
    {
      path: "/group/create",
      name: "groupcreate",
      component: groupCreate,
      beforeEnter(from, to, next) {
        if (!auth.state.status.loggedIn) {
          alert("로그인 후 이용해주세요!");
          next('/')
        } else {
          next()
        }
      }
    },

    {
      path: "/group/detail",
      name: "groupdetail",
      component: groupDetail,
      beforeEnter(from, to, next) {
        if (!auth.state.status.loggedIn) {
          alert("로그인 후 이용해주세요!");
          next('/')
        } else {
          next()
        }
      }
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
          alert("로그인 후 이용해주세요!");
          next('/')
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
