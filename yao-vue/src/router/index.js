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
import dailyHexagram from "~/pages/dailyHexagram.vue";
import announce from "~/pages/announce.vue";
import comment from "~/pages/comment.vue";
import message from "~/pages/message.vue";
import userProfile from "~/pages/userProfile.vue";
import zodiac from "~/pages/zodiac.vue";
import question from "~/pages/question.vue";
import options from "~/pages/options.vue";
import articleType from "~/pages/articleType.vue";



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
            path: "/options",
            component: options,
            meta: {
                title: "MBTI选项",
            },
        },
        {
            path: "/question",
            component: question,
            meta: {
                title: "MBTI题",
            },
        },
        {
            path: "/userProfile",
            component: userProfile,
            meta: {
                title: "档案",
            },
        },
        {
            path: "/zodiac",
            component: zodiac,
            meta: {
                title: "生肖结果",
            },
        },
        {
            path: "/dailyHexagram",
            component: dailyHexagram,
            meta: {
                title: "每日一卦",
            },
        },
        {
            path: "/message",
            component: message,
            meta: {
                title: "消息表",
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
      {
        path: "/articleType",
        component: articleType,
        meta: {
          title: "分类",
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
