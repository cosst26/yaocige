<script setup>

import { useRouter, useRoute } from 'vue-router'
import { useStore } from 'vuex'
import { ref } from 'vue'
const router = useRouter()
const route = useRoute()
const store = useStore()


const asideMenus = [
    {
        id: 0,
        name: '主页',
        con: 'location',
        frontpath: "/"
    },
    {
        id: 2,
        name: '公告管理',
        con: 'location',
        child: [
            {
                id: 0,
                name: '公告列表',
                frontpath: "/announce"
            },
        ]
    },
    {
        id: 3,
        name: '系统管理',
        con: 'location',
        child: [
            {
                id: 0,
                name: '用户管理',
                frontpath: "/userlist"
            },
            {
                id: 1,
                name: '角色管理',
                frontpath: "/role"
            },
            {
                id: 2,
                name: '封面管理',
                frontpath: "/cover"
            },
        ]
    },
    {
        id: 4,
        name: '评论管理',
        con: 'location',
        child: [
            {
                id: 0,
                name: '评论列表',
                frontpath: "/comment"
            },
        ]
    },
    {
        id: 5,
        name: '每日一卦管理',
        con: 'location',
        child: [
            {
                id: 0,
                name: '每日一卦列表',
                frontpath: "/dailyHexagram"
            },
        ]
    },
    {
        id: 6,
        name: '消息管理',
        con: 'location',
        child: [
            {
                id: 0,
                name: '消息列表',
                frontpath: "/message"
            },
        ]
    },
    {
        id: 7,
        name: '用户档案管理',
        con: 'location',
        child: [
            {
                id: 0,
                name: '用户档案列表',
                frontpath: "/userProfile"
            },
        ]
    },
    {
        id: 8,
        name: '生肖结果管理',
        con: 'location',
        child: [
            {
                id: 0,
                name: '生肖结果列表',
                frontpath: "/zodiac"
            },
        ]
    },
    {
        id: 9,
        name: 'MBTI题管理',
        con: 'location',
        child: [
            {
                id: 0,
                name: 'MBTI题列表',
                frontpath: "/question"
            },
        ]
    },
    {
        id: 10,
        name: 'MBTI选项管理',
        con: 'location',
        child: [
            {
                id: 0,
                name: 'MBTI选项列表',
                frontpath: "/options"
            },
        ]
    },
    {
        id: 11,
        name: '文章',
        con: 'location',
        child: [
            {
                id: 0,
                name: '文章',
                frontpath: "/article"
            },
            {
                id: 1,
                name: '分类',
                frontpath: "/articleType"
            },
        ]
    }
]

// 选择菜单
const handleSelect = (e) => {
    console.log(e);
    router.push(e)
}


// 默认选中
const defaultActive = ref(route.path)


</script>


<template>
    <div class="f-menu fixed bg-light-50 shadow-2xl">
        <el-menu :default-active="defaultActive" class="border-0" @select="handleSelect">
            {{  store.user }}
            <template v-for="(item, i) in store.state.user.roleId == 1 ? asideMenus : doctorMenu" :key="i">
                <el-sub-menu v-if="item.child && item.child.length > 0" :index="item.id + ''">
                    <template #title>
                        <el-icon>
                            <el-icon>
                                <component :is="item.con"></component>
                            </el-icon>
                        </el-icon>
                        <span>{{ item.name }}</span>
                    </template>
                    <el-menu-item v-for="(item2, index2) in item.child" :key="index2" :index="item2.frontpath + ''">{{
                        item2.name
                    }}</el-menu-item>
                </el-sub-menu>
                <el-menu-item v-else :index="item.frontpath">
                    <el-icon>
                        <component :is="item.con"></component>
                    </el-icon>
                    <span>{{ item.name }}</span>
                </el-menu-item>
            </template>

        </el-menu>
    </div>
</template>



<style scoped>
.f-menu {
    width: 250px;
    top: 64px;
    bottom: 0;
    left: 0;
    overflow: auto;
}
</style>