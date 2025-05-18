<template>
	<view style="padding: 20px;">
		
		
		<view class="aiItem" v-for="item in ailist" :key="item.id"  @click="tochatai(item.id)" style="display: flex;align-items: center;justify-content: space-between;">
			<view style="display: flex;align-items: center;
		gap: 20px;margin-bottom: 20px;">
				<image :src="item.avatar" mode="aspectFill" style="width: 50px;height: 50px;border-radius: 100%;"></image>
				
				<div class="aiinfo" style="flex: 1;">
					<div class="iname" style="font-weight: 700;margin-bottom: 5px;">{{item.name}}</div>
					<div class="icontent">{{item.aiDescribe}}</div>
				</div>
			</view>
			
			<view>
				<view class="bnt" @click.stop="toEditBc(item.id)">编辑</view>
				<view class="bnt" @click.stop="delteB(item.id)">删除</view>
			</view>
		</view>
		
		<view class="newbtn" @click="addai">+ 创建新的AI对话</view>
		
		<tabBar :pagePath="'/pages/ai/ai'"></tabBar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				ailist:[]
			}
		},
		onShow() {
			this.getAi()
		},
	
		methods: {
			toEditBc(id){
				uni.navigateTo({
					url:'/pages/editAI/editAI?id='+id
				})
			},
			addai(){
				uni.navigateTo({
					url:'/pages/addai/addai'
				})
			},
			getAi(){
				uni.request({
					url: this.$elyasApi + 'dialogue/findByModal',
					method: 'POST',
					data: {},
					header: {
						"content-type": "application/json",
						"accessToken": uni.getStorageSync('token')
					},
					success: (data) => {
				
				
						this.ailist = data.data.data.list
				
					}
				
				})
			},
			delteB(id){
				uni.request({
					url: this.$elyasApi + 'dialogue/delete?id='+id,
					method: 'POST',
					data: {},
					header: {
						"content-type": "application/json",
						"accessToken": uni.getStorageSync('token')
					},
					success: (data) => {
				
				
						this.getAi()
				
					}
				
				})
			},
			tochatai(id){
				uni.navigateTo({
					url:'/pages/aichat/aichat?id='+id
				})
			}
		}
	}
</script>

<style scoped>
	
	.icontent {
		    overflow: hidden;
		    text-overflow: ellipsis;
		    display: -webkit-box;
		    -webkit-line-clamp: 1;
		    -webkit-box-orient: vertical;
	}
	
	.newbtn{
		 background: linear-gradient(to right, 
		        #4C39FF, /* 最左边起始红色 */
		        #4C39FF, /* 过渡到偏浅一点的红色 */
		        #C73FFF, /* 再过渡到蓝色 */
		        #C73FFF /* 最右边的蓝色 */
		    );
	
		 color: #fff;
		 width: 200px;
		 height: 50px;
		 border-radius: 30px;
		 text-align: center;
		 line-height: 50px;
		 margin: 40px auto;
	}
	
	
	
	
	
	
.bnt {
	width: 80px;
	height: 30px;
	text-align: center;
	line-height: 30px;
	color: #fff;
}
	
	
	.bnt:nth-child(1){
		background-color: deepskyblue;
	}
	
	.bnt:nth-child(2){
		background-color: #ff434f;
	}
	

</style>
