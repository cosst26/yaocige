<template>
    <view class="nav-box" :style="{'height':height+'px','background':bgColor}">
        <view :class="[fixed?'fixed':'']">
            <!-- 状态栏占位 -->
            <view class="status_bar" :style="{'height':statusBarHeight+'px'}"></view>
            <!-- 导航内容 -->
            <view class="nav-main" :style="{'height': navBarHeight+'px'}">
                <view class="nav">
                    <slot></slot>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    export default {
        //接收父组件传值(仅示例，其他业务场景可自行扩展)
        props: {
            // 导航栏背景色相关
            bgColor: {
                type: String,
                default: "#fff"
            },
            // 标题相关
            title: {
                type: String,
                default: "首页"
            },
            // 特定路径
            path: {
                type: String,
            },
            // 导航栏高度（不包含状态栏）
            navBarHeight: {
                type: Number,
                default: 0
            },
            fixed: {
                type: Boolean,
                default: false
            }
        },
        watch: {
            fixed(newVal, oldVal) {
                this.fixed = newVal
            }
        },
        data() {
            return {
                // 自定义导航栏高度总和
                height: 0,
                // 微信小程序胶囊布局位置信息
                menuButtonRect: {},
                // 状态栏高度
                statusBarHeight: 0
            }
        },
        created() {
            this.getHeight();
        },
        methods: {
            //计算导航栏总高度
            getHeight() {
                // #ifdef APP-PLUS || H5
                // 获取状态栏高度(电量时间通知等信息-单位px)
                let sysInfo = uni.getSystemInfoSync();
                this.statusBarHeight = sysInfo.statusBarHeight;
                this.height = this.statusBarHeight + this.navBarHeight;
                // #endif

                // 判断获取微信小程序胶囊API是否可用
                if (uni.canIUse('getMenuButtonBoundingClientRect')) {
                    // 获取微信小程序胶囊布局位置信息
                    // #ifndef H5 || APP-PLUS || MP-ALIPAY
                    let sysInfo = uni.getSystemInfoSync();
                    let rect = uni.getMenuButtonBoundingClientRect()
                    this.menuButtonRect = JSON.parse(JSON.stringify(rect));
                    // (胶囊上部高度-状态栏高度)*2 + 胶囊高度 = 导航栏高度（不包含状态栏）
                    //以此保证胶囊位于中间位置，多机型适配
                    this.navBarHeight = (rect.top - sysInfo.statusBarHeight) * 2 + rect.height;
                    this.statusBarHeight = sysInfo.statusBarHeight;
                    // 状态栏高度 + 导航栏高度 = 自定义导航栏高度总和
                    this.height = sysInfo.statusBarHeight + this.navBarHeight;
                    // #endif
                }
            },
        }
    }
</script>

<style lang="scss" scoped>
    .nav-box {
        position: relative;
        width: 100%;

        .nav-main {
            display: flex;
            padding: 0 25upx;
			
            .nav {
                display: flex;
                align-items: center;
                width: 100%;
                z-index: 5;
            }
        }
    }

    .fixed {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        background-color: rgba(240, 0, 0,0.9);
        z-index: 9;
    }
</style>