import { createRouter, createWebHashHistory } from "vue-router";

// 后台管理
import Admin from "~/layouts/admin.vue";
import AdminIndex from "~/pages/index.vue";
import Login from "~/pages/login.vue";
import Register from "~/pages/register.vue";
import NotFound from "~/pages/404.vue";
import Userlist from "~/pages/user/userlist.vue";
import role from "~/pages/user/role.vue";


import cover from "~/pages/cover/cover.vue";

import article from "~/pages/article.vue";

import announce from "~/pages/announce.vue";
import comment from "~/pages/comment.vue";


const routes = [
  {
    path: "/",
    component: Admin,
    children: [
      {
        path: "/",
        component: AdminIndex,
        meta: {
          title: "后台首页",
        },
      },
      {
        path: "/userlist",
        component: Userlist,
        meta: {
          title: "用户列表",
        },
      },
      {
        path: "/role",
        component: role,
        meta: {
          title: "角色列表",
        },
      },
      {
        path: "/cover",
        component: cover,
        meta: {
          title: "封面列表",
        },
      },
      {
        path: "/article",
        component: article,
        meta: {
          title: "资讯",
        },
      },
      {
        path: "/announce",
        component: announce,
        meta: {
          title: "公告",
        },
      },
      {
        path: "/comment",
        component: comment,
        meta: {
          title: "评论",
        },
      },
    ],
  },
  {
    path: "/login",
    component: Login,
    meta: {
      title: "登录页面",
    },
  },
  {
    path: "/register",
    component: Register,
    meta: {
      title: "注册页面",
    },
  },
  {
    path: "/:pathMatch(.*)*",
    name: "NotFound",
    component: NotFound,
  },
 
];




const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
