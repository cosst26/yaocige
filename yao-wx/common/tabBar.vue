
<template>
    <view>
		<view class="uni-tabbar" v-if="!showPage">
		    <view class="uni-tabbar__item" v-for="(item,index) in tabbar" :key="index" @tap="changeTab(item)"  >
		        <view class="icon" :class="[item.fontIcon , item.pagePath == pagePath?'uni-active':'']"></view>
		        <!-- 上面使用的是字体图标，解决切换页面的时候图标会闪的效果，毕竟每切换一个页面都会闪一下不太好看，可以切换使用下面的图片方式 -->
		        <view  class="uni-tabbar__bd">
		            <view class="uni-tabbar__icon">
		                <image v-if="item.pagePath == pagePath" class="uni-w-42 uni-h-42" mode="aspectFit" :src="item.selectedIconPath"></image>
		                <image v-else class="uni-w-42 uni-h-42" mode="aspectFit" :src="item.iconPath"></image>
		            </view>
		        </view>
		        <view class="uni-tabbar__label" :class="{'active': item.pagePath == pagePath}">
		            {{item.text}}
		        </view>
		    </view>
			
			
		</view>
		
		<view class="uni-tabbar" v-else>
		    <view class="uni-tabbar__item" v-for="(item,index) in tabbar2" :key="index" @tap="changeTab(item)"  >
		        <view class="icon" :class="[item.fontIcon , item.pagePath == pagePath?'uni-active':'']"></view>
		        <!-- 上面使用的是字体图标，解决切换页面的时候图标会闪的效果，毕竟每切换一个页面都会闪一下不太好看，可以切换使用下面的图片方式 -->
		        <view  class="uni-tabbar__bd">
		            <view class="uni-tabbar__icon">
		                <image v-if="item.pagePath == pagePath" class="uni-w-42 uni-h-42" mode="aspectFit" :src="item.selectedIconPath"></image>
		                <image v-else class="uni-w-42 uni-h-42" mode="aspectFit" :src="item.iconPath"></image>
		            </view>
		        </view>
		        <view class="uni-tabbar__label" :class="{'active': item.pagePath == pagePath}">
		            {{item.text}}
		        </view>
		    </view>
			
			
		</view>
	</view>
</template>

<script>
    export default {
        props: {
            pagePath: null
        },
        data() {
            return {
				roleIndex:'',
                page: 'contact',
                showPage: false,
                containerHeight: 400,
                tabbar: [
                   　{
					    "pagePath": "/pages/index/index",
					    "iconPath": "/static/newtabbar/Home.png",
					    "selectedIconPath": "/static/newtabbar/Home (1).png",
					    "text": "首页",
					 
					},　　　　
					
					{
					    "pagePath": "/pages/ai/ai",
					   "iconPath": "/static/newtabbar/分类.png",
					   "selectedIconPath": "/static/newtabbar/分类 (1).png",
					    "text": "消息",
					 
					},　　　　　　　　　　
					        
					{
					    "pagePath": "/pages/mine/mine",
					    "iconPath": "/static/newtabbar/我的.png",
					    "selectedIconPath": "/static/newtabbar/我的 (1).png",
					    "text": "我的",
					 
					},
                ],
				tabbar2: [
				   {
				       "pagePath": "/pages/liaotian2/liaotian2",
				       "iconPath": "/static/tabbar/home.png",
				       "selectedIconPath": "/static/tabbar/home.png",
				       "text": "聊天",
				    
				   },　　　　　　　　　　　　
				           
				   {
				       "pagePath": "/pages/mine/mine",
				       "iconPath": "/static/tabbar/user.png",
				       "selectedIconPath": "/static/tabbar/user.png",
				       "text": "我的",
				    
				   },
				],
				userData:{}
            };
        },
        mounted() {
        	this.getUserdata()
        	
        },
        methods: {
			getUserdata() {
				uni.request({
					url: this.$elyasApi + 'user/detailByToken',
					method: 'POST',
					data: {},
					header: {
						"content-type": "application/json",
						"accessToken": uni.getStorageSync('token')
					},
					success: (data) => {
						if (data.data.data.roleId == 2) {
							this.showPage = false
						}else {
							this.showPage = true
						}
					}
				
				});
			},
            changeTab(item) {
                this.page = item.pagePath;　　　　　　　　　　// 这里使用reLaunch关闭所有的页面，打开新的栏目页面
                uni.reLaunch({
                    url: this.page
                });
            },
        }
    }
</script>

<style lang="scss" scoped>
    [nvue] uni-scroll-view, [nvue] uni-swiper-item, [nvue] uni-view {
        flex-direction: unset;
    }
    [nvue-dir-column] uni-swiper-item, [nvue-dir-column] uni-view {
        flex-direction: unset;
    }
    .uni-tabbar {
        position: fixed;
        bottom: 0;
        z-index: 999;
        width: 100%;
        display: flex;
        justify-content: space-around;
		align-items: center;
        height: 105upx;
        padding: 16upx 0;
        box-sizing: border-box;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        background-color: #fff;
        // box-shadow: 0px 0px 17upx 1upx rgba(206, 206, 206, 0.32);
        .uni-tabbar__item {
            display: block;
            line-height: 24upx;
            font-size: 20upx;
            text-align: center;
        }
        .uni-tabbar__icon {
            height: 52upx;
       
            text-align: center;
			image {
				height: 100%;
				width: 52upx;
				margin-bottom: 3px;
			}
        }
        .icon {
            display: inline-block;
        }
        .uni-tabbar__label {
            line-height: 24upx;
            font-size: 24upx;
            color: #999;
            &.active {
                color: #ff7a45;
            }
        }
    }
</style>